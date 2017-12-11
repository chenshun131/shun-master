package com.shun.framework.mq.handler.common;

import com.shun.framework.marshaller.json.JsonMarshaller;
import com.shun.framework.mq.core.MQException;
import com.shun.framework.mq.jms.JmsAccessor;
import com.shun.framework.mq.handler.impl.AbstractHandler;
import com.shun.framework.mq.request.MQRequest;
import com.shun.framework.mq.util.JsonMessageUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.JMSException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 11:21  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class RetryMessageHandler extends AbstractHandler {

    private static Log log = LogFactory.getLog(RetryMessageHandler.class);

    private JmsAccessor mqAccessor;

    public RetryMessageHandler() {
    }

    public void setMqAccessor(JmsAccessor mqAccessor) {
        this.mqAccessor = mqAccessor;
    }

    public void handleMessage(Object request) throws Exception {
        log.info("Retry message: " + request);
        String failedQueue;
        if (request instanceof MQRequest) {
            MQRequest mqRequest = (MQRequest) request;

            try {
                this.mqAccessor.sendMessage(mqRequest);
            } catch (MQException var8) {
                log.error("Retry to resend message failed.");
                if (mqRequest.getProperties() != null) {
                    failedQueue = (String) mqRequest.getProperties().get("failedQueue");
                    if (failedQueue != null) {
                        this.mqAccessor.sendMessage(failedQueue, 0, 2, false, 1, 2
                                , JsonMarshaller.marshall(request));
                    }
                }
            }
        } else if (request instanceof String) {
            try {
                this.retry((String) request);
            } catch (Exception var7) {
                log.error("Retry to resend message failed.");
                Map map = (Map) JsonMarshaller.unmarshall((String) request, LinkedHashMap.class, new Class[0]);
                map = (Map) map.get("properties");
                if (map != null && !map.isEmpty()) {
                    failedQueue = (String) map.get("failedQueue");
                    if (failedQueue != null) {
                        try {
                            this.mqAccessor.sendMessage(failedQueue, 0, 2, false, 1, 2
                                    , (String) request);
                        } catch (Exception var6) {
                            log.error("Send message to failedQueue failed.");
                        }
                    }
                }
            }
        }
    }

    private void retry(String request) throws Exception {
        try {
            Map map = (Map) JsonMarshaller.unmarshall(request, LinkedHashMap.class, new Class[0]);
            boolean transcated = false;
            String destination = null;
            int deliveryMode = JsonMessageUtils.getDeliveryMode(map);
            int acknowledgeMode = JsonMessageUtils.getAcknowledgeMode(map);
            int destinationType;
            if (map.containsKey("destination")) {
                transcated = ((Boolean) map.get("transacted")).booleanValue();
                destination = (String) map.get("destination");
                destinationType = ((Integer) map.get("destinationType")).intValue();
            } else {
                destination = (String) map.get("Destination");
                destinationType = ((Integer) map.get("DestinationType")).intValue();
                if (acknowledgeMode == 0) {
                    transcated = true;
                }
            }
            this.mqAccessor.sendMessage(destination, destinationType, 2, transcated, acknowledgeMode, deliveryMode
                    , request);
        } catch (JMSException var8) {
            log.error("send message to retry queue failed. Request: " + request, var8);
            throw var8;
        } catch (Exception var9) {
            log.warn("Unmarshalling mqrequest failed.", var9);
        }
    }

}
