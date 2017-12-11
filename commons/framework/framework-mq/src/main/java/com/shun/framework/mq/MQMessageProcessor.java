package com.shun.framework.mq;

import com.alibaba.fastjson.JSON;
import com.shun.framework.exception.BasicRuntimeException;
import com.shun.framework.mq.core.MQException;
import com.shun.framework.mq.core.MQService;
import com.shun.framework.mq.request.notify.DefaultNotifyRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: mew <p />
 * Time: 17/11/8 12:39  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MQMessageProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MQMessageProcessor.class);

    @Autowired
    private MQService mQService;

    public MQMessageProcessor() {
    }

    public void sendMessage(Object message, String destination, int messageFormat) throws BasicRuntimeException {
        DefaultNotifyRequest<Object> request = new DefaultNotifyRequest();
        request.setDestination(destination);
        request.setContent(message);
        request.setMessageFormat(messageFormat);
        request.setDestinationType(0);

        try {
            logger.info("Message Sending... message: " + JSON.toJSONString(request));
            this.mQService.sendMessage(request);
        } catch (MQException var6) {
            logger.error("send failed.", var6);
            throw new BasicRuntimeException("MQ Send Message Failed! Request:" + request);
        }
    }

    public void sendMessage(Object message, String destination) throws BasicRuntimeException {
        DefaultNotifyRequest<Object> request = new DefaultNotifyRequest();
        request.setDestination(destination);
        request.setContent(message);
        request.setMessageFormat(2);
        request.setDestinationType(0);

        try {
            logger.info("Message Sending... message: " + JSON.toJSONString(request));
            this.mQService.sendMessage(request);
        } catch (MQException var5) {
            logger.error("send failed.", var5);
            throw new BasicRuntimeException("MQ Send Message Failed! Request:" + request);
        }
    }

    public void setmQService(MQService mQService) {
        this.mQService = mQService;
    }

}
