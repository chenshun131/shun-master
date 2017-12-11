package com.shun.framework.mq.listener.concurrent;


import com.shun.framework.mq.listener.AbstractMessageListener;
import com.shun.framework.mq.listener.MessageListenerContainer;

import javax.jms.MessageListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: mew <p />
 * Time: 17/11/8 11:34  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ConcurrentMessageListener extends AbstractMessageListener implements MessageListener,
        MessageHandleCallback {

    private MessageListenerContainer messageListenerContainer;

    private ExecutorService executorService;

    private ConcurrentMap<String, MessageHandleTask> runningTasks = new ConcurrentHashMap();

    private int maxThreadsHold;

    private int coreThreadsHold;

    private boolean toBeStared = true;

    private boolean toBeStopped = false;

    public ConcurrentMessageListener() {
    }

    @Override
    public void handleRequest(Object request) {
        MessageHandleTask task = new MessageHandleTask(this.getMessageHandler(), request, this);
        this.addTask(task);
        this.getExecutorService().execute(task);
        this.checkRunningThreads();
    }

    @Override
    public void handleRequestFinished(String taskId, boolean succ) {
        this.runningTasks.remove(taskId);
        this.checkRunningThreads();
    }

    private void addTask(MessageHandleTask task) {
        this.runningTasks.put(task.getTaskId(), task);
    }

    private synchronized void checkRunningThreads() {
        int size = this.runningTasks.size();
        if (size >= this.maxThreadsHold && !this.toBeStopped) {
            this.fireStopListeningEvent();
            this.toBeStopped = true;
            this.toBeStared = false;
        } else if (size < this.coreThreadsHold && !this.toBeStared) {
            this.fireStartListeningEvent();
            this.toBeStared = true;
            this.toBeStopped = false;
        }
    }

    private void fireStopListeningEvent() {
        this.messageListenerContainer.stopContainer();
    }

    private void fireStartListeningEvent() {
        this.messageListenerContainer.startContainer();
    }

    public MessageListenerContainer getMessageListenerContainer() {
        return this.messageListenerContainer;
    }

    public void setMessageListenerContainer(MessageListenerContainer messageListenerContainer) {
        this.messageListenerContainer = messageListenerContainer;
    }

    public ExecutorService getExecutorService() {
        if (this.executorService == null) {
            this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 1);
        }
        return this.executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public int getMaxThreadsHold() {
        return this.maxThreadsHold;
    }

    public void setMaxThreadsHold(int maxThreadsHold) {
        this.maxThreadsHold = maxThreadsHold;
    }

    public int getCoreThreadsHold() {
        return this.coreThreadsHold;
    }

    public void setCoreThreadsHold(int coreThreadsHold) {
        this.coreThreadsHold = coreThreadsHold;
    }

}
