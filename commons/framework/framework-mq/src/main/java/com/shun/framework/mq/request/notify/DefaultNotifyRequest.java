package com.shun.framework.mq.request.notify;

import com.shun.framework.mq.request.MQRequest;
import com.shun.framework.mq.request.common.AbstractRequest;

/**
 * User: mew <p />
 * Time: 17/11/8 10:36  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DefaultNotifyRequest<T> extends AbstractRequest implements NotifyRequest<T> {
    private static final long serialVersionUID = 1720395367972841633L;
    private T content;
    private String referenceId;

    public DefaultNotifyRequest() {
    }

    /** @deprecated */
    @Deprecated
    public DefaultNotifyRequest(Integer actionId, T content) {
        this.setActionId(actionId.intValue());
        this.content = content;
    }

    public DefaultNotifyRequest(String queueName, T content) {
        this.setDestination(queueName);
        this.content = content;
    }

    public DefaultNotifyRequest(String destination, int destinationType, T content) {
        this.setDestination(destination);
        this.setDestinationType(destinationType);
        this.content = content;
    }

    /** @deprecated */
    @Deprecated
    public DefaultNotifyRequest(Integer actionId, T content, int messageFormat) {
        this(actionId, content);
        this.setMessageFormat(messageFormat);
    }

    public DefaultNotifyRequest(String queueName, T content, int messageFormat) {
        this.setDestination(queueName);
        this.content = content;
    }

    public DefaultNotifyRequest(String destination, int destinationType, T content, int messageFormat) {
        this.setDestination(destination);
        this.setDestinationType(destinationType);
        this.content = content;
    }

    /** @deprecated */
    @Deprecated
    public DefaultNotifyRequest(Integer actionId, T content, int messageFormat, boolean transcated, int deliveryMode) {
        this(actionId, content, messageFormat);
        if (transcated) {
            this.setAcknowledgeMode(0);
        }

        this.setTransacted(transcated);
        this.setDeliveryMode(deliveryMode);
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getReferenceId() {
        return this.referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public MQRequest deepCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String toString() {
        return "DefaultNotifyRequest [content=" + this.content + ", referenceId=" + this.referenceId + "[" + super.toString() + "]]";
    }
}
