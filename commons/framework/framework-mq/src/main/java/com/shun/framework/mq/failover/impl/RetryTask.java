package com.shun.framework.mq.failover.impl;

import com.shun.framework.marshaller.json.JsonMarshaller;
import com.shun.framework.mq.jms.JmsAccessor;
import com.shun.framework.mq.request.MQRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.JMSException;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 11:18  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class RetryTask implements Runnable {

    private static Log log = LogFactory.getLog(RetryTask.class);

    private JmsAccessor mqAccessor;

    private MQRequest request;

    public RetryTask(JmsAccessor mqAccessor, MQRequest request) {
        this.mqAccessor = mqAccessor;
        this.request = request;
    }

    public void run() {
        if (log.isInfoEnabled()) {
            log.info("Retry to send MQRequest to original queue: " + this.request.toString());
        }
        try {
            this.mqAccessor.sendMessage(this.request.getDestination(), this.request.getDestinationType(),
                    this.request.getMessageFormat(), this.request.isTransacted(), this.request.getAcknowledgeMode(),
                    this.request.getDeliveryMode(), this.request);
        } catch (JMSException var5) {
            log.error("Fail to send message to original queue.", var5);
            Map props = this.request.getProperties();
            try {
                this.mqAccessor.sendMessage((String) props.get("failedQueue"), 0, 1, false, 1,
                        2, JsonMarshaller.marshall(this.request));
            } catch (JMSException var4) {
                log.error("Send message to failed queue failed.", var4);
            }
        }
    }

}
