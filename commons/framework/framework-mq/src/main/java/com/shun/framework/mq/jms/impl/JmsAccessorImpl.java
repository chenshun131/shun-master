package com.shun.framework.mq.jms.impl;

import com.shun.framework.mq.core.MQException;
import com.shun.framework.mq.jms.JmsAccessor;
import com.shun.framework.mq.jms.surport.MessageConvert;
import com.shun.framework.mq.management.model.DestinationInfo;
import com.shun.framework.mq.request.MQRequest;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.MessageCreator;
import org.springframework.util.Assert;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * User: mew <p />
 * Time: 17/11/8 11:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class JmsAccessorImpl implements JmsAccessor {
    private JmsTemplate jmsTemplate;
    private DestinationInfoFactory destinationInfoFactory;

    public JmsAccessorImpl() {
    }

    public void sendMessage(MQRequest request) throws MQException {
        DestinationInfo destinationInfo = this.resolveDestination(request.getActionId());
        request.setDestination(destinationInfo.getDestination());
        request.setDestinationType(destinationInfo.getDestinationType());

        try {
            this.sendMessage(request.getDestination(), request.getDestinationType(), request);
        } catch (JMSException var4) {
            throw new MQException("Send message failed.", var4);
        }
    }

    public void sendMessage(String destination, int destinationType, MQRequest request) throws JMSException {
        this.sendMessage(destination, destinationType, request.getMessageFormat(), request.isTransacted(), request.getAcknowledgeMode(), request.getDeliveryMode(), request);
    }

    public void sendMessage(String destination, int destinationType, final int messageFormat, boolean transacted, int acknowledgeMode, final int deliveryMode, final Object request) throws JMSException {
        this.jmsTemplate.send(destination, destinationType, transacted, acknowledgeMode, deliveryMode, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Message message = MessageConvert.toMessage(session, request, messageFormat);
                message.setJMSDeliveryMode(deliveryMode);
                if (request instanceof MQRequest) {
                    JmsAccessorImpl.this.setMessageProperties(message, ((MQRequest)request).getProperties());
                }

                return message;
            }
        });
    }

    public Object receiveMessage(int actionId) throws MQException {
        return this.receiveMessage(actionId, false, 3, (String)null);
    }

    public Object receiveMessage(int actionId, boolean transacted, int acknowledgeMode, String selector) throws MQException {
        Assert.notNull(actionId, "MQRequest action id cannot be null.");
        Assert.isTrue(actionId > 0, "MQRequest action id must be greater than zero.");
        DestinationInfo destinationInfo = this.resolveDestination(actionId);
        return this.receiveMessage(destinationInfo.getDestination(), destinationInfo.getDestinationType(), transacted, acknowledgeMode, selector);
    }

    public Object receiveMessage(String queueName) throws MQException {
        return this.receiveMessage(queueName, 0);
    }

    public Object receiveMessage(String destination, int destinationType) throws MQException {
        return this.receiveMessage(destination, destinationType, (String)null);
    }

    public Object receiveMessage(String destination, int destinationType, String selector) throws MQException {
        return this.receiveMessage(destination, destinationType, false, 1, selector);
    }

    public Object receiveMessage(String destination, int destinationType, boolean transacted, int acknowledgeMode, String selector) throws MQException {
        try {
            Message message = this.jmsTemplate.receiveSelected(destination, destinationType, transacted, acknowledgeMode, selector);
            return message == null ? null : MessageConvert.fromMessage(message);
        } catch (JmsException var7) {
            throw new MQException("Receive message failed.", var7);
        } catch (JMSException var8) {
            throw new MQException("Message received is invalid.", var8);
        }
    }

    private void setMessageProperties(Message message, Map<String, Object> props) throws JMSException {
        if (props != null && !props.isEmpty()) {
            Set<String> keys = props.keySet();
            Iterator i$ = keys.iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                Object value = props.get(key);
                if (value instanceof String) {
                    message.setStringProperty(key, (String)value);
                } else if (value instanceof Integer) {
                    message.setIntProperty(key, ((Integer)value).intValue());
                } else if (value instanceof Boolean) {
                    message.setBooleanProperty(key, ((Boolean)value).booleanValue());
                } else if (value instanceof Long) {
                    message.setLongProperty(key, ((Long)value).longValue());
                } else if (value instanceof Double) {
                    message.setDoubleProperty(key, ((Double)value).doubleValue());
                } else if (value instanceof Float) {
                    message.setFloatProperty(key, ((Float)value).floatValue());
                } else {
                    message.setObjectProperty(key, value);
                }
            }

        }
    }

    private DestinationInfo resolveDestination(Integer actionId) {
        Assert.notNull(actionId, "MQRequest action id cannot be null.");
        Assert.isTrue(actionId.intValue() > 0, "MQRequest action id must be greater than zero.");
        DestinationInfo destinationInfo = this.destinationInfoFactory.getDestinationInfo(actionId);
        if (destinationInfo == null) {
            throw new IllegalArgumentException("Invalid actionId.");
        } else {
            return destinationInfo;
        }
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setDestinationInfoFactory(DestinationInfoFactory destinationInfoFactory) {
        this.destinationInfoFactory = destinationInfoFactory;
    }

}
