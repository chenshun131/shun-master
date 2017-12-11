package com.shun.framework.mq.request.common;

import com.shun.framework.mq.request.MQRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 10:43  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public abstract class AbstractRequest implements MQRequest {

    private static final long serialVersionUID = 4618099280290649932L;

    private int actionId;

    private String destination;

    private int destinationType = 0;

    private int deliveryMode = 1;

    private int acknowledgeMode = 1;

    private boolean transacted = false;

    private Date requestTime = new Date();

    private int requestType;

    private long expirationTime;

    private int messageFormat = 1;

    private Map<String, Object> properties;

    public AbstractRequest() {
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

    public int getDeliveryMode() {
        return this.deliveryMode;
    }

    public void setDeliveryMode(int deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public Date getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public int getAcknowledgeMode() {
        return this.acknowledgeMode;
    }

    public void setAcknowledgeMode(int acknowledgeMode) {
        this.acknowledgeMode = acknowledgeMode;
    }

    public boolean isTransacted() {
        return this.transacted;
    }

    public void setTransacted(boolean transacted) {
        this.transacted = transacted;
    }

    public int getRequestType() {
        return this.requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public long getExpirationTime() {
        return this.expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public int getMessageFormat() {
        return this.messageFormat;
    }

    public void setMessageFormat(int format) {
        this.messageFormat = format;
    }

    public Map<String, Object> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public void addProperty(String key, Object value) {
        if (this.properties == null) {
            this.properties = new HashMap();
        }

        this.properties.put(key, value);
    }

    public Object getProperty(String key) {
        return this.properties == null ? null : this.properties.get(key);
    }

    public boolean containsProperty(String key) {
        return this.properties == null ? false : this.properties.containsKey(key);
    }

    protected void copyProperties(MQRequest dest) {
        if (dest != null) {
            dest.setAcknowledgeMode(this.acknowledgeMode);
            dest.setActionId(this.actionId);
            dest.setDeliveryMode(this.deliveryMode);
            dest.setDestination(this.destination + "");
            dest.setDestinationType(this.destinationType);
            dest.setExpirationTime(this.expirationTime);
            dest.setMessageFormat(this.messageFormat);
            dest.setProperties(this.properties);
            dest.setRequestTime(this.requestTime);
            dest.setRequestType(this.requestType);
            dest.setTransacted(this.transacted);
        }
    }

    public String toString() {
        return "AbstractRequest [actionId=" + this.actionId + ", destination=" + this.destination + ", destinationType="
                + this.destinationType + ", deliveryMode=" + this.deliveryMode + ", acknowledgeMode="
                + this.acknowledgeMode + ", transacted=" + this.transacted + ", requestTime=" + this.requestTime
                + ", requestType=" + this.requestType + ", expirationTime=" + this.expirationTime + ", messageFormat="
                + this.messageFormat + ", properties=" + this.properties + "]";
    }

}
