package com.shun.framework.mq.handler;

import com.shun.framework.mq.handler.impl.AbstractMessageHandler;

/**
 * User: mew <p />
 * Time: 17/11/8 11:20  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface MessageHandlerFactory {

    void addMessageHandler(String var1, AbstractMessageHandler var2);

    AbstractMessageHandler getMessageHandler(String var1);

}
