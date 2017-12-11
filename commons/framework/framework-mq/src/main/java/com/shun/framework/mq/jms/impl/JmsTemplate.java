package com.shun.framework.mq.jms.impl;

import org.springframework.jms.JmsException;
import org.springframework.jms.connection.ConnectionFactoryUtils;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.util.Assert;

import javax.jms.*;

/**
 * User: mew <p />
 * Time: 17/11/8 10:58  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class JmsTemplate extends org.springframework.jms.core.JmsTemplate {

    public JmsTemplate() {
    }

    protected Session createSession(Connection connection, boolean transacted, int acknowledgeMode)
            throws JMSException {
        return connection.createSession(transacted, acknowledgeMode);
    }

    public Object execute(SessionCallback action, boolean transacted, int acknowledgeMode, boolean startConnection)
            throws JmsException {
        Assert.notNull(action, "Callback object must not be null");
        Connection conToClose = null;
        Session sessionToClose = null;
        Object var7;
        try {
            conToClose = this.createConnection();
            sessionToClose = this.createSession(conToClose);
            if (startConnection) {
                conToClose.start();
            }
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Executing callback on JMS Session: " + sessionToClose);
            }
            var7 = action.doInJms(sessionToClose);
        } catch (JMSException var11) {
            throw this.convertJmsAccessException(var11);
        } finally {
            JmsUtils.closeSession(sessionToClose);
            ConnectionFactoryUtils.releaseConnection(conToClose, this.getConnectionFactory(), startConnection);
        }
        return var7;
    }

    public void send(final String destinationName, final int destinationType, boolean transacted, int acknowledgeMode,
                     final int deliveryMode, final MessageCreator messageCreator) throws JmsException {
        this.execute(new SessionCallback() {
            @Override
            public Object doInJms(Session session) throws JMSException {
                Destination destination = JmsTemplate.this.resolveDestinationName(session, destinationName,
                        destinationType);
                JmsTemplate.this.doSend(session, destination, messageCreator, deliveryMode);
                return null;
            }
        }, transacted, acknowledgeMode, false);
    }

    protected void doSend(Session session, Destination destination, MessageCreator messageCreator, int deliveryMode)
            throws JMSException {
        Assert.notNull(messageCreator, "MessageCreator must not be null");
        MessageProducer producer = this.createProducer(session, destination);
        try {
            Message message = messageCreator.createMessage(session);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Sending created message: " + message);
            }
            producer.send(message, deliveryMode, this.getPriority(), this.getTimeToLive());
            if (session.getTransacted() && this.isSessionLocallyTransacted(session)) {
                JmsUtils.commitIfNecessary(session);
            }
        } finally {
            JmsUtils.closeMessageProducer(producer);
        }

    }

    public Message receiveSelected(final String destinationName, final int destinationType, boolean transacted,
                                   int acknowledgeMode, final String messageSelector) throws JmsException {
        return (Message) this.execute(new SessionCallback() {
            @Override
            public Message doInJms(Session session) throws JMSException {
                Destination destination = JmsTemplate.this.resolveDestinationName(session, destinationName,
                        destinationType);
                return JmsTemplate.this.doReceive(session, destination, messageSelector);
            }
        }, transacted, acknowledgeMode, false);
    }

    protected Destination resolveDestinationName(Session session, String destinationName, int destinationType)
            throws JMSException {
        boolean pubSubDomain = destinationType == 3;
        return this.getDestinationResolver().resolveDestinationName(session, destinationName, pubSubDomain);
    }

}
