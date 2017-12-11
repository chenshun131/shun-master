package com.shun.framework.mq.handler.impl;

import com.shun.framework.mq.handler.MessageHandlerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: mew <p />
 * Time: 17/11/8 11:26  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MessageHandlerFactoryImpl implements MessageHandlerFactory {

    private static Log log = LogFactory.getLog(MessageHandlerFactoryImpl.class);

    private static MessageHandlerFactory factory = new MessageHandlerFactoryImpl();

    private ConcurrentMap<String, AbstractMessageHandler> messageHandlers = new ConcurrentHashMap();

    private MessageHandlerFactoryImpl() {
    }

    public static MessageHandlerFactory getInstance() {
        return factory;
    }

    public void addMessageHandler(String destination, AbstractMessageHandler messageHandler) {
        if (messageHandler == null) {
            throw new IllegalArgumentException("Message handler cannot be null.");
        } else {
            this.messageHandlers.putIfAbsent(destination, messageHandler);
        }
    }

    public AbstractMessageHandler getMessageHandler(String destination) {
        log.info("Get message handler, destination is: " + destination);
        if (!StringUtils.hasText(destination)) {
            throw new IllegalArgumentException("Handler class name cannot be empty.");
        } else {
            AbstractMessageHandler messageHandler = (AbstractMessageHandler) this.messageHandlers.get(destination);
            return messageHandler;
        }
    }

}
