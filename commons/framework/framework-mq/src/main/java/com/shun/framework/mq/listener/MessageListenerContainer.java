package com.shun.framework.mq.listener;

/**
 * User: mew <p />
 * Time: 17/11/8 11:37  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface MessageListenerContainer {

    void startContainer();

    void stopContainer();

    int getContainerStatus();

}
