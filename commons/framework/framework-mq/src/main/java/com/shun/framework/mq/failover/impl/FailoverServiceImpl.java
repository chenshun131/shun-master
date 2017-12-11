package com.shun.framework.mq.failover.impl;

import com.shun.framework.marshaller.json.JsonMarshaller;
import com.shun.framework.mq.jms.JmsAccessor;
import com.shun.framework.mq.failover.FailoverService;
import com.shun.framework.mq.request.MQRequest;
import com.shun.framework.mq.util.JsonMessageUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.JMSException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 11:16  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class FailoverServiceImpl implements FailoverService {

    private static Log log = LogFactory.getLog(FailoverServiceImpl.class);

    private JmsAccessor mqAccessor;

    public FailoverServiceImpl() {
    }

    public void setMqAccessor(JmsAccessor mqAccessor) {
        this.mqAccessor = mqAccessor;
    }

    public void retry(MQRequest request) {
        Map<String, Object> props = request.getProperties();
        int failedTimes = ((Integer) props.get("failedTimes")).intValue();
        if (-1 != failedTimes) {
            ++failedTimes;
            props.put("failedTimes", failedTimes);
        }
        int retryTimes = ((Integer) props.get("retryTimes")).intValue();
        if (-1 != failedTimes && failedTimes <= retryTimes) {
            this.sendToRetryQueue((String) props.get("retryQueue"), request);
        } else {
            this.sendToFailedQueue((String) props.get("failedQueue"), request);
        }
    }

    private void sendToRetryQueue(String queue, MQRequest request) {
        log.warn("Retry to send MQRequest: " + request.toString());
        try {
            this.mqAccessor.sendMessage(request.getDestination(), request.getDestinationType(), request);
        } catch (Exception var4) {
            log.error("Send message to original queue fail.", var4);
            this.sendToFailedQueue((String) null, request);
        }
    }

    private void sendToFailedQueue(String destination, MQRequest request) {
        log.warn("Send to failed queue: " + request.toString());
        if (destination == null) {
            Map<String, Object> props = request.getProperties();
            destination = (String) props.get("failedQueue");
        }
        try {
            this.mqAccessor.sendMessage(destination, 0, 1, false, 1, 2,
                    JsonMarshaller.marshall(request));
        } catch (JMSException var4) {
            log.error("Send message to failed queue failed.", var4);
        }
    }

    public void retry(Object request, int retryTimes, String retryQueue, String failedQueue) {
        log.info("Retry messge: " + request);
        if (request instanceof MQRequest) {
            MQRequest mqRequest = (MQRequest) request;
            if (!mqRequest.containsProperty("retryQueue")) {
                mqRequest.addProperty("retryTimes", retryTimes);
                mqRequest.addProperty("retryQueue", retryQueue);
                mqRequest.addProperty("failedQueue", failedQueue);
                mqRequest.addProperty("failedTimes", Integer.valueOf(0));
            }
            this.retry(mqRequest);
        } else if (request instanceof String) {
            this.retryString((String) request, retryTimes, retryQueue, failedQueue);
        } else {
            log.error("Unsupported request type. Request: " + request);
        }
    }

    private void retryString(String request, int retryTimes, String retryQueue, String failedQueue) {
        LinkedHashMap map = null;
        try {
            map = (LinkedHashMap) JsonMarshaller.unmarshall(request, Object.class, new Class[0]);
        } catch (Exception var13) {
            log.error("Unmarshall message failed. Message may not json type. Request: " + request);
            throw new UnsupportedOperationException(var13);
        }
        int deliveryMode = JsonMessageUtils.getDeliveryMode(map);
        int acknowledgeMode = JsonMessageUtils.getAcknowledgeMode(map);
        boolean transcated = false;
        if (map.containsKey("transacted")) {
            transcated = ((Boolean) map.get("transacted")).booleanValue();
        } else if (acknowledgeMode == 0) {
            transcated = true;
        }
        String destination = this.getDestination(map, retryTimes, retryQueue, failedQueue);
        try {
            request = JsonMarshaller.marshall(map);
        } catch (Exception var12) {
            var12.printStackTrace();
        }
        try {
            this.mqAccessor.sendMessage(destination, 0, 2, transcated, acknowledgeMode, deliveryMode, request);
        } catch (JMSException var11) {
            log.error("Retry failed. Request: " + request);
        }
    }

    private String getDestination(Map<String, Object> map, int retryTimes, String retryQueue, String failedQueue) {
        Map<String, Object> props = (Map) map.get("properties");
        if (props == null) {
            props = new LinkedHashMap();
            map.put("properties", props);
        }
        if (!((Map) props).containsKey("retryTimes")) {
            ((Map) props).put("retryTimes", retryTimes);
            ((Map) props).put("retryQueue", retryQueue);
            ((Map) props).put("failedTimes", Integer.valueOf(0));
            ((Map) props).put("failedQueue", failedQueue);
        }
        return this.getDestination(map);
    }

    private String getDestination(Map<String, Object> map) {
        Map<String, Object> props = (Map) map.get("properties");
        int failedTimes = ((Integer) props.get("failedTimes")).intValue();
        if (-1 != failedTimes) {
            ++failedTimes;
            props.put("failedTimes", failedTimes);
        }
        int retryTimes = ((Integer) props.get("retryTimes")).intValue();
        return -1 != failedTimes && failedTimes <= retryTimes ? (String) props.get("retryQueue") :
                (String) props.get("failedQueue");
    }

}
