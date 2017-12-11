package com.shun.framework.mq.jms.impl;

import com.shun.framework.mq.core.MQException;
import com.shun.framework.mq.core.MQService;
import com.shun.framework.mq.jms.JmsAccessor;
import com.shun.framework.mq.request.MQRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.JMSException;

/**
 * User: mew <p />
 * Time: 17/11/8 11:00  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class JmsService implements MQService {

    private static Log log = LogFactory.getLog(JmsService.class);

    private JmsAccessor mqAccessor;

    public JmsService() {
    }

    public void sendMessage(MQRequest request) throws MQException {
        if (log.isInfoEnabled()) {
            log.info("Executing to send message: " + request);
        }
        int destinationType;
        if (request.getDestination() != null) {
            destinationType = request.getDestinationType();
            if (destinationType != 0 && destinationType != 3) {
                throw new IllegalArgumentException("Invalid destination type:" + destinationType);
            }
            try {
                this.mqAccessor.sendMessage(request.getDestination(), destinationType, request);
            } catch (JMSException var4) {
                throw new MQException(var4);
            }
        } else {
            destinationType = request.getActionId();
            if (destinationType < 0) {
                throw new IllegalArgumentException("ActionId and destination cannot be null at same time.");
            }
            this.mqAccessor.sendMessage(request);
        }
    }

    public Object receiveMessage(String queueName) throws MQException {
        return this.mqAccessor.receiveMessage(queueName);
    }

    public Object receiveMessage(int actionId) throws MQException {
        return this.receiveMessage(actionId, (String) null);
    }

    public Object receiveMessage(int actionId, String selector) throws MQException {
        return this.receiveMessage(actionId, false, 1, (String) null);
    }

    public Object receiveMessage(int actionId, boolean transacted, int acknowledgeMode, String selector)
            throws MQException {
        if (log.isDebugEnabled()) {
            log.debug("Executing to receive message: " + actionId);
        }
        if (actionId <= 0) {
            throw new IllegalArgumentException("Action id must be greater than zero.");
        } else {
            return this.mqAccessor.receiveMessage(actionId, transacted, acknowledgeMode, selector);
        }
    }

    public void setMqAccessor(JmsAccessor mqAccessor) {
        this.mqAccessor = mqAccessor;
    }

}
