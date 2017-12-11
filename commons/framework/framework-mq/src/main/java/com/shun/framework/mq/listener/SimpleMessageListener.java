package com.shun.framework.mq.listener;

import com.shun.framework.mq.handler.impl.AbstractHandler;
import com.shun.framework.mq.handler.impl.AbstractMessageHandler;
import com.shun.framework.mq.jms.impl.DestinationInfoFactory;
import com.shun.framework.mq.jms.impl.DestinationResolverImpl;
import com.shun.framework.mq.management.model.DestinationInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * User: mew <p />
 * Time: 17/11/8 11:38  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Deprecated
public class SimpleMessageListener extends DefaultMessageListenerContainer implements MessageListener {

    private static Log log = LogFactory.getLog(SimpleMessageListener.class);

    private AbstractHandler messageHandler;

    private DestinationInfoFactory destinationInfoFactory;

    public SimpleMessageListener() {
    }

    public void setMessageHandler(AbstractHandler messageHandler) {
        this.messageHandler = messageHandler;
        Assert.notNull("messageHandler", "Message handler cannot be null.");
    }

    public void setDestinationInfoFactory(DestinationInfoFactory destinationInfoFactory) {
        this.destinationInfoFactory = destinationInfoFactory;
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
        if (this.getDestinationName() == null) {
            Assert.notNull("messageHandler", "Message handler cannot be null.");
            Assert.notNull("destinationInfoFactory", "Need to get destination form actionId, destinationInfoFactory " +
                    "cannot be null.");
            if (this.messageHandler != null && this.messageHandler instanceof AbstractMessageHandler) {
                AbstractMessageHandler handler = (AbstractMessageHandler) this.messageHandler;
                String destination = handler.getDestination();
                int destinationType;
                if (destination != null) {
                    destinationType = handler.getDestinationType();
                    if (destinationType != 0 && destinationType != 3) {
                        throw new IllegalArgumentException("Invalid destination type:" + destinationType);
                    }

                    this.setDestinationName(destination);
                    this.setPubSubDomain(destinationType == 3);
                } else {
                    destinationType = handler.getActionId();
                    if (destinationType > 0) {
                        DestinationInfo destinationInfo = this.destinationInfoFactory.getDestinationInfo
                                (destinationType);
                        if (destinationInfo != null) {
                            this.setDestinationName(destinationInfo.getDestination());
                            this.setPubSubDomain(destinationInfo.getDestinationType() == 3);
                        }
                    }
                }

                if (this.getDestinationName() == null) {
                    throw new IllegalArgumentException("please assignment the destion to listenned.");
                }
            }
        }

        if (this.messageHandler != null && this.getMessageListener() == null) {
            DefaultMessageListener messageListener = new DefaultMessageListener();
            messageListener.setMessageHandler(this.messageHandler);
            this.setConcurrentConsumers(this.messageHandler.getConcurrentConsumers());
            this.setMessageListener(messageListener);
        }

        this.setDestinationResolver(DestinationResolverImpl.getInstance());
        super.initialize();
    }

    @Override
    public void afterPropertiesSet() {
    }

    public void startListener() {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (SimpleMessageListener.this.getListenerStatus() == MQMessageListenerStatus.INACTIVE
                                .getValue()) {
                            SimpleMessageListener.super.afterPropertiesSet();
                        } else {
                            if (SimpleMessageListener.this.getListenerStatus() != MQMessageListenerStatus.STOPPED
                                    .getValue()) {
                                if (SimpleMessageListener.log.isInfoEnabled()) {
                                    SimpleMessageListener.log.info("Listener on " + SimpleMessageListener.this
                                            .getDestinationName() + " is started.");
                                }

                                return;
                            }

                            SimpleMessageListener.super.start();
                            if (SimpleMessageListener.log.isInfoEnabled()) {
                                SimpleMessageListener.log.info("Listener on " + SimpleMessageListener.this
                                        .getDestinationName() + " is started.");
                            }
                        }
                    } catch (Exception var2) {
                        SimpleMessageListener.log.warn("Init the listener failed, and application will retry later.",
                                var2);
                    }
                }
            }
        })).start();
    }

    public void stopListener() {
        if (this.getListenerStatus() == MQMessageListenerStatus.RUNNING.getValue()) {
            if (log.isInfoEnabled()) {
                log.info("Stopping listener on " + this.getDestinationName() + "...");
            }

            super.stop();
        }

    }

    @Override
    public void destroy() {
        super.destroy();
        log.info("Listener on " + this.getDestinationName() + " is destroyed.");
    }

    public int getListenerStatus() {
        if (!this.isActive()) {
            return MQMessageListenerStatus.INACTIVE.getValue();
        } else {
            return this.isRunning() ? MQMessageListenerStatus.RUNNING.getValue() :
                    MQMessageListenerStatus.STOPPED.getValue();
        }
    }

    @Override
    public void onMessage(Message message) {
    }

}
