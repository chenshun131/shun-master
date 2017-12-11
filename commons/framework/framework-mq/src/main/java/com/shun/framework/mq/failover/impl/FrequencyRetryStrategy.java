package com.shun.framework.mq.failover.impl;

import com.shun.framework.mq.failover.RetryStrategy;
import com.shun.framework.mq.request.MQRequest;

/**
 * User: mew <p />
 * Time: 17/11/8 11:18  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class FrequencyRetryStrategy extends AbstractRetryStrategy implements RetryStrategy {

    private int frequency = 10;

    public FrequencyRetryStrategy() {
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    protected boolean needRetry(MQRequest request) {
        if (this.frequency <= 0) {
            return true;
        } else {
            int failedTimes = 1;
            if (request.containsProperty("failedTimes")) {
                failedTimes = ((Integer) request.getProperty("failedTimes")).intValue() + 1;
            }
            request.addProperty("failedTimes", failedTimes);
            return failedTimes <= this.frequency;
        }
    }

}
