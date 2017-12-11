package com.shun.framework.mq.listener;

import com.shun.framework.mq.handler.impl.AbstractHandler;

/**
 * User: mew <p />
 * Time: 17/11/8 11:39  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DefaultMessageListenerContainer extends AbstractMessageListenerContainer implements
        MessageListenerContainer {

    public DefaultMessageListenerContainer() {
    }

    @Override
    public void initMessageListener() {
        DefaultMessageListener messageListener = new DefaultMessageListener();
        AbstractHandler messageHandler = (AbstractHandler) this.getMessageHandler();
        messageListener.setMessageHandler(messageHandler);
        if (messageHandler.getConcurrentConsumers() > 1) {
            this.setConcurrentConsumers(messageHandler.getConcurrentConsumers());
        }
        this.setMessageListener(messageListener);
    }

}
