package com.shun.framework.mq.request.notify;

/**
 * User: mew <p />
 * Time: 17/11/8 10:47  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DefaultTransacatedNotifyRequest<T> extends DefaultNotifyRequest<T> implements NotifyRequest<T> {

    private static final long serialVersionUID = -8907492637100399932L;

    public DefaultTransacatedNotifyRequest() {
        this.setAcknowledgeMode(0);
        this.setDeliveryMode(2);
        this.setTransacted(true);
    }

    /** @deprecated  */
    @Deprecated
    public DefaultTransacatedNotifyRequest(Integer actionId, T content) {
        this(actionId, content, 1);
    }

    /** @deprecated  */
    @Deprecated
    public DefaultTransacatedNotifyRequest(Integer actionId, T content, int messageFormat) {
        super(actionId, content, messageFormat);
        this.setAcknowledgeMode(0);
        this.setDeliveryMode(2);
        this.setTransacted(true);
    }

}
