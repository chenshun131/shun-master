package com.shun.framework.mq.handler.impl;

import com.shun.framework.mq.request.MQRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: mew <p />
 * Time: 17/11/8 11:26  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public abstract class AbstractMessageHandler extends AbstractHandler {

    private static Log log = LogFactory.getLog(AbstractMessageHandler.class);

    private int actionId;

    private String destination;

    private int destinationType = 0;

    public AbstractMessageHandler() {
    }

    public boolean isListening(int actionId) {
        return this.actionId == actionId;
    }

    public MQRequest convertToMQRequest(Object request) {
        return MQRequest.class.isAssignableFrom(request.getClass()) ? (MQRequest) request : null;
    }

    public int getActionId() {
        return this.actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDestinationType() {
        return this.destinationType;
    }

    public void setDestinationType(int destinationType) {
        this.destinationType = destinationType;
    }

}
