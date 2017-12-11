package com.shun.framework.mq.listener;

import com.shun.framework.mq.handler.MessageHandler;
import com.shun.framework.mq.handler.impl.AbstractMessageHandler;
import com.shun.framework.mq.jms.impl.DestinationResolverImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * User: mew <p />
 * Time: 17/11/8 09:37  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public abstract class AbstractMessageListenerContainer extends DefaultMessageListenerContainer implements
        MessageListenerContainer {

    private static Log log = LogFactory.getLog(AbstractMessageListenerContainer.class);

    private MessageHandler messageHandler;

    public AbstractMessageListenerContainer() {
    }

    @Override
    protected void validateConfiguration() {
        if (this.isSubscriptionDurable() && !this.isPubSubDomain()) {
            throw new IllegalArgumentException("A durable subscription requires a topic (pub-sub domain)");
        } else if (this.messageHandler == null && this.getMessageListener() == null) {
            throw new IllegalArgumentException("MessageHandler and MessageListener cannot be null at same time");
        } else if (this.isSubscriptionDurable() && this.getConcurrentConsumers() != 1) {
            throw new IllegalArgumentException("Only 1 concurrent consumer supported for durable subscription");
        }
    }

    @Override
    public void initialize() {
        if (this.getDestinationName() == null && this.messageHandler != null
                && this.messageHandler instanceof AbstractMessageHandler) {
            AbstractMessageHandler handler = (AbstractMessageHandler) this.messageHandler;
            String destination = handler.getDestination();
            if (destination != null) {
                int destinationType = handler.getDestinationType();
                if (destinationType != 0 && destinationType != 3) {
                    throw new IllegalArgumentException("Invalid destination type:" + destinationType);
                }
                this.setDestinationName(destination);
                this.setPubSubDomain(destinationType == 3);
            }
            if (this.getDestinationName() == null) {
                throw new NullPointerException("Destination cann't be null.");
            }
        }
        if (this.messageHandler != null && this.getMessageListener() == null) {
            this.initMessageListener();
        }
        this.setDestinationResolver(DestinationResolverImpl.getInstance());
        super.initialize();
    }

    public abstract void initMessageListener();

    @Override
    public int getContainerStatus() {
        if (!this.isActive()) {
            return MQMessageListenerStatus.INACTIVE.getValue();
        } else {
            return this.isRunning() ? MQMessageListenerStatus.RUNNING.getValue() :
                    MQMessageListenerStatus.STOPPED.getValue();
        }
    }

    @Override
    public void startContainer() {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (AbstractMessageListenerContainer.this.getContainerStatus() == MQMessageListenerStatus
                                .INACTIVE.getValue()) {
                            AbstractMessageListenerContainer.super.afterPropertiesSet();
                        } else {
                            if (AbstractMessageListenerContainer.this.getContainerStatus() != MQMessageListenerStatus
                                    .STOPPED.getValue()) {
                                if (AbstractMessageListenerContainer.log.isInfoEnabled()) {
                                    AbstractMessageListenerContainer.log.info("Listener on " +
                                            AbstractMessageListenerContainer.this.getDestinationName()
                                            + " is started.");
                                }
                                return;
                            }
                            AbstractMessageListenerContainer.super.start();
                            if (AbstractMessageListenerContainer.log.isInfoEnabled()) {
                                AbstractMessageListenerContainer.log.info("Listener on " +
                                        AbstractMessageListenerContainer.this.getDestinationName() + " is started.");
                            }
                        }
                    } catch (Exception var2) {
                        AbstractMessageListenerContainer.log
                                .warn("Init the listener failed, and application will retry later.", var2);
                    }
                }
            }
        })).start();
    }

    @Override
    public void stopContainer() {
        if (this.getContainerStatus() == MQMessageListenerStatus.RUNNING.getValue()) {
            if (log.isInfoEnabled()) {
                log.info("Stopping listener on " + this.getDestinationName() + "...");
            }
            super.stop();
        }

    }

    public MessageHandler getMessageHandler() {
        return this.messageHandler;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        if (messageHandler == null) {
            throw new IllegalArgumentException("Message handler cannot be null.");
        } else {
            this.messageHandler = messageHandler;
        }
    }

}
