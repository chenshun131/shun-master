package com.shun.framework.mq.listener.concurrent;

import com.shun.framework.mq.handler.MessageHandler;

/**
 * User: mew <p />
 * Time: 17/11/8 11:34  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MessageHandleTask implements Runnable {

    private MessageHandler messageHandler;

    private MessageHandleCallback messageHandleCallback;

    private Object request;

    private String taskId;

    public MessageHandleTask(MessageHandler messageHandler, Object request, MessageHandleCallback
            messageHandleCallback) {
        this.messageHandler = messageHandler;
        this.request = request;
        this.messageHandleCallback = messageHandleCallback;
        this.taskId = "" + System.nanoTime();
    }

    @Override
    public void run() {
        this.messageHandler.handle(this.request);
        this.messageHandleCallback.handleRequestFinished(this.taskId, true);
    }

    public String getTaskId() {
        return this.taskId;
    }

}
