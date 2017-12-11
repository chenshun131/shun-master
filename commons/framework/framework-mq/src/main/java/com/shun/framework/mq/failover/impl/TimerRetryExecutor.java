package com.shun.framework.mq.failover.impl;

import com.shun.framework.mq.jms.JmsAccessor;
import com.shun.framework.mq.failover.RetryExecutor;
import com.shun.framework.mq.request.MQRequest;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * User: mew <p />
 * Time: 17/11/8 09:44  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TimerRetryExecutor implements RetryExecutor {

    private JmsAccessor mqAccessor;

    private ScheduledExecutorService executorService;

    public TimerRetryExecutor(JmsAccessor mqAccessor) {
        this.mqAccessor = mqAccessor;
    }

    public JmsAccessor getMqAccessor() {
        return this.mqAccessor;
    }

    public void retrySend(MQRequest request) throws Exception {
        if (this.executorService == null) {
            throw new Exception("Must assign the executorService when it doesn't support schedule message.");
        } else {
            long retryInterval = 0L;
            if (request.containsProperty("AMQ_SCHEDULED_DELAY")) {
                retryInterval = Long.parseLong(request.getProperty("AMQ_SCHEDULED_DELAY").toString());
            }

            this.executorService.schedule(new RetryTask(this.getMqAccessor(), request), retryInterval, TimeUnit
                    .MILLISECONDS);
        }
    }

    public void setExecutorService(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

}
