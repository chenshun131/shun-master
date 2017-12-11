package com.shun.framework.mq.request.dispatch;

import com.shun.framework.mq.request.notify.NotifyRequest;

import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/8 10:45  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface DispatchRequest<T> extends NotifyRequest<T>, Cloneable {

    List<Integer> getDispatchActionIds();

    void setDispatchActionIds(List<Integer> var1);

    void addDispatchActionId(Integer var1);

}
