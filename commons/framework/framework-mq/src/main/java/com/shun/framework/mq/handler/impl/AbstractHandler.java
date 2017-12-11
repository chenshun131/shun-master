package com.shun.framework.mq.handler.impl;

import com.shun.framework.mq.failover.FailoverService;
import com.shun.framework.mq.handler.MessageHandler;
import com.shun.framework.mq.request.MQRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: mew <p />
 * Time: 17/11/8 11:23  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public abstract class AbstractHandler implements MessageHandler {

    private static Log log = LogFactory.getLog(AbstractHandler.class);

    private static final String DEFAULT_RETRY_QUEUE = "weihui.amo.retryQueue";

    private static final String DEFAULT_FAILED_QUEUE = "weihui.amo.failedQueue";

    private static final int DEFAULT_MAX_RETRY_TIMES = 3;

    private FailoverService failoverService;

    private int concurrentConsumers = 1;

    private String retryQueue = "weihui.amo.retryQueue";

    private String failedQueue = "weihui.amo.failedQueue";

    private int retryTimes = 3;

    public AbstractHandler() {
    }

    public void handle(Object request) {
        if (log.isDebugEnabled()) {
            log.debug("Handle message: " + request);
        }
        MQRequest mqRequest = this.convertToMQRequest(request);
        try {
            if (mqRequest == null) {
                this.handleMessage(request);
                return;
            }
            Object content = this.getMessageContent(mqRequest);
            if (content == null) {
                if (log.isWarnEnabled()) {
                    log.warn("The content is null. Message: " + request);
                }
                return;
            }
            if (log.isInfoEnabled()) {
                log.info("Message content: " + content);
            }
            this.handleMessage(content);
        } catch (Throwable var4) {
            if (log.isWarnEnabled()) {
                log.warn("Handling message failed, and retry it...", var4);
            }
            if (mqRequest != null) {
                if (mqRequest.getDestinationType() == 3) {
                    log.warn("Topic messages cannot retry." + mqRequest);
                } else {
                    if (!mqRequest.containsProperty("retryQueue")) {
                        mqRequest.addProperty("retryQueue", this.retryQueue);
                        mqRequest.addProperty("retryTimes", this.retryTimes);
                        mqRequest.addProperty("failedQueue", this.failedQueue);
                        mqRequest.addProperty("failedTimes", Integer.valueOf(0));
                    }
                    this.getFailoverService().retry(mqRequest);
                }
            } else {
                this.getFailoverService().retry(request, this.retryTimes, this.retryQueue, this.failedQueue);
            }
        }
    }

    protected Object getMessageContent(MQRequest request) {
        return request;
    }

    public MQRequest convertToMQRequest(Object request) {
        return null;
    }

    public FailoverService getFailoverService() {
        return this.failoverService;
    }

    public void setFailoverService(FailoverService failoverService) {
        this.failoverService = failoverService;
    }

    public String getRetryQueue() {
        return this.retryQueue;
    }

    public void setRetryQueue(String retryQueue) {
        this.retryQueue = retryQueue;
    }

    public String getFailedQueue() {
        return this.failedQueue;
    }

    public void setFailedQueue(String failedQueue) {
        this.failedQueue = failedQueue;
    }

    public int getRetryTimes() {
        return this.retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public int getConcurrentConsumers() {
        return this.concurrentConsumers;
    }

    public void setConcurrentConsumers(int concurrentConsumers) {
        this.concurrentConsumers = concurrentConsumers;
    }

}
