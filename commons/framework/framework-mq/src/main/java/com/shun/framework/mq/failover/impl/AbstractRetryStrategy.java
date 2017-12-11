package com.shun.framework.mq.failover.impl;

import com.shun.framework.marshaller.json.JsonMarshaller;
import com.shun.framework.mq.jms.JmsAccessor;
import com.shun.framework.mq.failover.RetryExecutor;
import com.shun.framework.mq.failover.RetryStrategy;
import com.shun.framework.mq.request.MQRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.JMSException;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 10:55  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public abstract class AbstractRetryStrategy implements RetryStrategy {

    private static Log log = LogFactory.getLog(AbstractRetryStrategy.class);

    private JmsAccessor mqAccessor;

    private RetryExecutor retryExecutor;

    private long interval = 120L;

    public AbstractRetryStrategy() {
    }

    public void retry(MQRequest request) {
        if (this.needRetry(request)) {
            this.beforeProcess(request);

            try {
                this.sendToOriginalQueue(request);
            } catch (Exception var3) {
                log.error("Fail to send message to original queue.", var3);
                this.sendToFailedQueue(request);
            }
        } else {
            this.sendToFailedQueue(request);
        }

    }

    protected void beforeProcess(MQRequest request) {
        request.addProperty("AMQ_SCHEDULED_DELAY", this.getInterval() * 1000L);
    }

    protected abstract boolean needRetry(MQRequest var1);

    protected void sendToOriginalQueue(MQRequest request) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Retry to send MQRequest to original queue: " + request.toString());
        }

        this.retryExecutor.retrySend(request);
    }

    protected void sendToFailedQueue(MQRequest request) {
        Map<String, Object> props = request.getProperties();
        this.sendToFailedQueue((String) props.get("failedQueue"), request);
    }

    private void sendToFailedQueue(String failedQueue, Object request) {
        if (log.isInfoEnabled()) {
            log.info("Send to failed queue: " + request.toString());
        }

        try {
            this.mqAccessor.sendMessage(failedQueue, 0, 1, false, 1, 2, JsonMarshaller.marshall(request));
        } catch (JMSException var4) {
            log.error("Send message to failed queue failed.", var4);
        }

    }

    public JmsAccessor getMqAccessor() {
        return this.mqAccessor;
    }

    public void setMqAccessor(JmsAccessor mqAccessor) {
        this.mqAccessor = mqAccessor;
    }

    public void setInterval(long interval) {
        if (interval > 0L) {
            this.interval = interval;
        }

    }

    public long getInterval() {
        return this.interval;
    }

    public RetryExecutor getRetryExecutor() {
        return this.retryExecutor;
    }

    public void setRetryExecutor(RetryExecutor retryExecutor) {
        this.retryExecutor = retryExecutor;
    }

}
