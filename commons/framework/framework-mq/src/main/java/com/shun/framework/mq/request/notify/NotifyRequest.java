package com.shun.framework.mq.request.notify;

import com.shun.framework.mq.request.MQRequest;

/**
 * User: mew <p />
 * Time: 17/11/8 10:45  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface NotifyRequest<T> extends MQRequest {

    T getContent();

    void setContent(T var1);

    void setReferenceId(String var1);

    String getReferenceId();

}
