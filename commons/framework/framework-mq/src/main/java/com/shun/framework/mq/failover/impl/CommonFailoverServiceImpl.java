package com.shun.framework.mq.failover.impl;

import com.shun.framework.mq.jms.JmsAccessor;
import com.shun.framework.mq.failover.FailoverService;
import com.shun.framework.mq.failover.RetryExecutor;
import com.shun.framework.mq.failover.RetryStrategy;
import com.shun.framework.mq.request.MQRequest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * User: mew <p />
 * Time: 17/11/8 11:14  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class CommonFailoverServiceImpl implements FailoverService {
    private static final String pkg = "com.weihui.mq.failover.impl";
    private JmsAccessor mqAccessor;
    private long interval = 60L;
    private long duration = 2880L;
    private int frequency = 5;
    private float attenuationFactor = 2.0F;
    private boolean supportScheduleMessage = false;
    private String retryStrategy = "AttenuationRetryStrategy";
    private ScheduledExecutorService executorService;
    private RetryExecutor retryExecutor;
    private RetryStrategy strategy;

    public CommonFailoverServiceImpl() {
    }

    public void retry(MQRequest request) {
        if (this.strategy == null) {
            this.strategy = this.createRetryStrategy();
            this.init(this.strategy);
        }

        this.strategy.retry(request);
    }

    private RetryStrategy createRetryStrategy() {
        String className = null;
        if (this.retryStrategy.contains(".")) {
            className = this.retryStrategy;
        } else {
            className = "com.weihui.mq.failover.impl." + this.retryStrategy;
        }

        try {
            Class<?> clazz = Class.forName(className);
            return (RetryStrategy)clazz.newInstance();
        } catch (Exception var3) {
            return null;
        }
    }

    private void init(RetryStrategy strategy) {
        AbstractRetryStrategy retryStrategy = (AbstractRetryStrategy)strategy;
        retryStrategy.setInterval(this.interval);
        retryStrategy.setMqAccessor(this.mqAccessor);
        if (strategy instanceof FrequencyRetryStrategy) {
            ((FrequencyRetryStrategy)strategy).setFrequency(this.frequency);
        } else if (strategy instanceof AttenuationRetryStrategy) {
            ((AttenuationRetryStrategy)strategy).setDuration(this.duration);
            ((AttenuationRetryStrategy)strategy).setAttenuationFactor(this.attenuationFactor);
        } else if (strategy instanceof DurationRetryStrategy) {
            ((DurationRetryStrategy)strategy).setDuration(this.duration);
        }

        retryStrategy.setRetryExecutor(this.getRetryExecutor());
    }

    private RetryExecutor getRetryExecutor() {
        if (this.retryExecutor != null) {
            return this.retryExecutor;
        } else {
            if (this.supportScheduleMessage) {
                this.retryExecutor = new ActiveMQRetryExecutor(this.mqAccessor);
            } else {
                TimerRetryExecutor timerRetryExecutor = new TimerRetryExecutor(this.mqAccessor);
                timerRetryExecutor.setExecutorService(this.getExecutorService());
                this.retryExecutor = timerRetryExecutor;
            }

            return this.retryExecutor;
        }
    }

    public void retry(Object request, int retryTimes, String retryQueue, String failedQueue) {
        throw new UnsupportedOperationException();
    }

    public void setMqAccessor(JmsAccessor mqAccessor) {
        this.mqAccessor = mqAccessor;
    }

    public void setInterval(long interval) {
        if (interval <= 0L) {
            throw new IllegalArgumentException("Interval must be greater than 0.");
        } else {
            this.interval = interval;
        }
    }

    public void setDuration(long duration) {
        if (duration <= 0L) {
            throw new IllegalArgumentException("Duration must be greater than 0.");
        } else {
            this.duration = duration;
        }
    }

    public void setFrequency(int frequency) {
        if (frequency <= 0) {
            throw new IllegalArgumentException("Frequency must be greater than 0.");
        } else {
            this.frequency = frequency;
        }
    }

    public void setAttenuationFactor(float attenuationFactor) {
        if (attenuationFactor < 1.0F) {
            throw new IllegalArgumentException("Attenuation factor must be greater than 1.");
        } else {
            this.attenuationFactor = attenuationFactor;
        }
    }

    public void setRetryStrategy(String retryStrategy) {
        this.retryStrategy = retryStrategy;
    }

    public boolean isSupportScheduleMessage() {
        return this.supportScheduleMessage;
    }

    public void setSupportScheduleMessage(boolean supportScheduleMessage) {
        this.supportScheduleMessage = supportScheduleMessage;
    }

    public void setExecutorService(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

    public ScheduledExecutorService getExecutorService() {
        if (this.executorService == null) {
            this.executorService = Executors.newScheduledThreadPool(10);
        }
        return this.executorService;
    }

}
