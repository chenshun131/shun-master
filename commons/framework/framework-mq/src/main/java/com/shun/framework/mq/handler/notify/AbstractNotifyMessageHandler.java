package com.shun.framework.mq.handler.notify;

import com.shun.framework.marshaller.json.JsonMarshaller;
import com.shun.framework.mq.handler.impl.AbstractMessageHandler;
import com.shun.framework.mq.request.MQRequest;
import com.shun.framework.mq.request.notify.DefaultNotifyRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: mew <p />
 * Time: 17/11/8 11:32  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public abstract class AbstractNotifyMessageHandler extends AbstractMessageHandler {

    private static Log log = LogFactory.getLog(AbstractNotifyMessageHandler.class);

    private Class<?> genericClass = Object.class;

    public AbstractNotifyMessageHandler() {
    }

    public MQRequest convertToMQRequest(Object request) {
        if (log.isDebugEnabled()) {
            log.debug("ConvertToMQRequest: " + request);
        }
        return MQRequest.class.isAssignableFrom(request.getClass()) ? (MQRequest) request :
                (MQRequest) JsonMarshaller.unmarshall((String) request, DefaultNotifyRequest.class,
                        new Class[]{this.genericClass});
    }

    protected Object getMessageContent(MQRequest request) {
        DefaultNotifyRequest<?> notifyRequest = (DefaultNotifyRequest) request;
        return notifyRequest.getContent();
    }

    public void setGenericClass(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

}
