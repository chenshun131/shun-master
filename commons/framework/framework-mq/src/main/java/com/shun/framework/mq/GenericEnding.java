package com.shun.framework.mq;

import com.godmonth.util.lock.lockmap.expression.LockId;
import com.shun.framework.exception.BusinessLogicException;

import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 11:55  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class GenericEnding implements GenericHandler {

    protected String queueName;

    public GenericEnding() {
    }

    @LockId(expression = "#root[0].businessId")
    public void handleResult(GenericResult genericResult) throws BusinessLogicException {
        if (genericResult.isSuccess()) {
            this.notifySuccess(genericResult);
        } else {
            this.notifyFailure(genericResult);
        }
    }

    protected void notifySuccess(GenericResult genericResult) throws BusinessLogicException {
        this.notifySuccess(genericResult.getBusinessId(), genericResult.getExtensions());
    }

    protected void notifyFailure(GenericResult genericResult) throws BusinessLogicException {
        this.notifyFailure(genericResult.getBusinessId(), genericResult.getExtensions());
    }

    protected void notifySuccess(String businessId, Map<String, String> extensions) throws BusinessLogicException {
    }

    protected void notifyFailure(String businessId, Map<String, String> extensions) throws BusinessLogicException {
    }

    public String getQueueName() {
        return this.queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

}
