package com.shun.framework.mq.listener.concurrent;

import com.shun.framework.mq.listener.AbstractMessageListenerContainer;

import java.util.concurrent.ExecutorService;

/**
 * User: mew <p />
 * Time: 17/11/8 11:34  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ConcurrentMessageListenerContainer extends AbstractMessageListenerContainer {

    private int maxThreadsHold = Runtime.getRuntime().availableProcessors() * 2;

    private int coreThreadsHold = Runtime.getRuntime().availableProcessors() / 2;

    private ExecutorService executorService;

    public ConcurrentMessageListenerContainer() {
    }

    @Override
    public void initMessageListener() {
        ConcurrentMessageListener messageListener = new ConcurrentMessageListener();
        messageListener.setCoreThreadsHold(this.coreThreadsHold);
        messageListener.setMaxThreadsHold(this.maxThreadsHold);
        messageListener.setMessageHandler(this.getMessageHandler());
        messageListener.setMessageListenerContainer(this);
        if (this.executorService != null) {
            messageListener.setExecutorService(this.executorService);
        }
        this.setMessageListener(messageListener);
    }

    public void setMaxThreadsHold(int maxThreadsHold) {
        this.maxThreadsHold = maxThreadsHold;
    }

    public void setCoreThreadsHold(int coreThreadsHold) {
        this.coreThreadsHold = coreThreadsHold;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}