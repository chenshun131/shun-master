package com.shun.framework.mq.listener.concurrent;

/**
 * User: mew <p />
 * Time: 17/11/8 11:34  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface MessageHandleCallback {

    void handleRequestFinished(String var1, boolean var2);

}
