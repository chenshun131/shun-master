package com.shun.framework.mq.request.dispatch;

import com.shun.framework.mq.request.notify.DefaultNotifyRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/8 10:44  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DefaultDispatchRequest<T> extends DefaultNotifyRequest<T> implements DispatchRequest<T> {
    private static final long serialVersionUID = 4649275646450378656L;
    private List<Integer> dispatchActionIds = new ArrayList();

    public DefaultDispatchRequest() {
    }

    public List<Integer> getDispatchActionIds() {
        return this.dispatchActionIds;
    }

    public void setDispatchActionIds(List<Integer> dispatchActionIds) {
        this.dispatchActionIds = dispatchActionIds;
    }

    public void addDispatchActionId(Integer actionId) {
        this.dispatchActionIds.add(actionId);
    }

    protected Object clone() throws CloneNotSupportedException {
        DefaultDispatchRequest<T> clone = (DefaultDispatchRequest)super.clone();
        Object actionIds = ((ArrayList)this.getDispatchActionIds()).clone();
        clone.setDispatchActionIds((List)actionIds);
        return super.clone();
    }
}