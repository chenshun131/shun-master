package com.shun.framework.mq.listener;

import com.shun.framework.mq.handler.impl.AbstractMessageHandler;
import com.shun.framework.mq.jms.impl.DestinationInfoFactory;

import javax.jms.ConnectionFactory;
import java.util.*;

/**
 * User: mew <p />
 * Time: 17/11/8 09:37  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DefaultMessageHandlerContainer {
    public List<SimpleMessageListener> messageListeners = new LinkedList();
    private List<AbstractMessageHandler> messageHandlers;
    private DestinationInfoFactory destinationInfoFactory;
    private ConnectionFactory connectionFactory;
    private Map<Integer, List<SimpleMessageListener>> actionIdMessageHandlers = new HashMap();

    public DefaultMessageHandlerContainer() {
    }

    public List<AbstractMessageHandler> getMessageHandlers() {
        return this.messageHandlers;
    }

    public void setMessageHandlers(List<AbstractMessageHandler> messageHandlers) {
        this.messageHandlers = messageHandlers;
    }

    public void addMessageHandler(AbstractMessageHandler messageHandler) {
        if (this.messageHandlers == null || this.messageHandlers.isEmpty()) {
            this.messageHandlers = new LinkedList();
        }

        this.messageHandlers.add(messageHandler);
    }

    public DestinationInfoFactory getDestinationInfoFactory() {
        return this.destinationInfoFactory;
    }

    public void setDestinationInfoFactory(DestinationInfoFactory destinationInfoFactory) {
        this.destinationInfoFactory = destinationInfoFactory;
    }

    public ConnectionFactory getConnectionFactory() {
        return this.connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void init() {
        if (this.messageHandlers != null && !this.messageHandlers.isEmpty()) {
            Iterator i$ = this.messageHandlers.iterator();

            while(i$.hasNext()) {
                AbstractMessageHandler messageHandler = (AbstractMessageHandler)i$.next();
                SimpleMessageListener listener = this.buildMessageListener(messageHandler);
                this.messageListeners.add(listener);
                if (messageHandler.getActionId() > 0) {
                    this.addActionIdMessageHandler(messageHandler.getActionId(), listener);
                }
            }

            this.fireStartListeningEvent();
        }
    }

    private void addActionIdMessageHandler(int actionId, SimpleMessageListener messageListener) {
        if (actionId > 0) {
            List<SimpleMessageListener> listeners = (List)this.actionIdMessageHandlers.get(actionId);
            if (listeners != null && !listeners.isEmpty()) {
                listeners.add(messageListener);
            }
        }
    }

    private SimpleMessageListener buildMessageListener(AbstractMessageHandler messageHandler) {
        SimpleMessageListener listener = new SimpleMessageListener();
        listener.setDestinationInfoFactory(this.destinationInfoFactory);
        listener.setConnectionFactory(this.connectionFactory);
        listener.setMessageHandler(messageHandler);
        return listener;
    }

    public void fireStartListeningEvent() {
        Iterator i$ = this.messageListeners.iterator();

        while(i$.hasNext()) {
            SimpleMessageListener listener = (SimpleMessageListener)i$.next();
            listener.startListener();
        }

    }

    public void fireStartListeningEvent(int actionId) {
        List<SimpleMessageListener> listeners = (List)this.actionIdMessageHandlers.get(actionId);
        Iterator i$ = listeners.iterator();

        while(i$.hasNext()) {
            SimpleMessageListener listener = (SimpleMessageListener)i$.next();
            listener.startListener();
        }

    }

    public void fireStopListeningEvent() {
        Iterator i$ = this.messageListeners.iterator();

        while(i$.hasNext()) {
            SimpleMessageListener messageListener = (SimpleMessageListener)i$.next();
            messageListener.stopListener();
        }

    }

    public void fireStopListeningEvent(int actionId) {
        List<SimpleMessageListener> listeners = (List)this.actionIdMessageHandlers.get(actionId);
        Iterator i$ = listeners.iterator();

        while(i$.hasNext()) {
            SimpleMessageListener listener = (SimpleMessageListener)i$.next();
            listener.stopListener();
        }

    }
}