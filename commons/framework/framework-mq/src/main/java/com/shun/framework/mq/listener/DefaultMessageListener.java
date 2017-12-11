package com.shun.framework.mq.listener;

import com.shun.framework.mq.exception.MessageHandleException;
import com.shun.framework.mq.handler.impl.AbstractHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import javax.jms.*;

/**
 * User: mew <p />
 * Time: 17/11/8 09:38  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DefaultMessageListener implements MessageListener {

    private static Log log = LogFactory.getLog(DefaultMessageListener.class);

    private AbstractHandler messageHandler;

    DefaultMessageListener() {
    }

    @Override
    public void onMessage(Message message) {
        Assert.notNull(this.messageHandler, "MessageHandler cannot be null.");
        if (log.isDebugEnabled()) {
            log.debug("The handler is: " + this.messageHandler.getClass());
        }
        Object request = null;
        if (message instanceof ObjectMessage) {
            ObjectMessage objMessage = (ObjectMessage) message;
            try {
                request = objMessage.getObject();
            } catch (JMSException var6) {
                log.error("Listenning message error. Message: " + message, var6);
            }
        } else {
            if (!(message instanceof TextMessage)) {
                log.error("Incorrect message type. Message: " + message);
                throw new MessageHandleException("Incorrect message type. Message: " + message);
            }
            TextMessage textMessage = (TextMessage) message;
            try {
                request = textMessage.getText();
            } catch (JMSException var5) {
                log.error("Listenning message error. Message: " + message, var5);
            }
        }
        log.info("Listen on message: " + request);
        if (request != null) {
            this.messageHandler.handle(request);
        }
    }

    public AbstractHandler getMessageHandler() {
        return this.messageHandler;
    }

    public void setMessageHandler(AbstractHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

}
