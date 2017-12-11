package com.shun.framework.mq.failover.impl;

import com.shun.framework.mq.failover.RetryStrategy;
import com.shun.framework.mq.request.MQRequest;

/**
 * User: mew <p />
 * Time: 17/11/8 11:14  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class AttenuationRetryStrategy extends DurationRetryStrategy implements RetryStrategy {
    private static final long DEFAULT_ATTENUATION_INTERVAL = 30L;
    private float attenuationFactor = 2.0F;

    public AttenuationRetryStrategy() {
        this.setInterval(30L);
    }

    protected void beforeProcess(MQRequest request) {
        long retryInterval = 0L;
        if (request.containsProperty("AMQ_SCHEDULED_DELAY")) {
            retryInterval = (long)((float)Long.parseLong(request.getProperty("AMQ_SCHEDULED_DELAY").toString()) * this.attenuationFactor);
        } else {
            retryInterval = this.getInterval() * 1000L;
        }

        request.addProperty("AMQ_SCHEDULED_DELAY", retryInterval);
    }

    public float getAttenuationFactor() {
        return this.attenuationFactor;
    }

    public void setAttenuationFactor(float attenuationFactor) {
        if (attenuationFactor < 1.0F) {
            throw new IllegalArgumentException("Attenuation factor must be greater than 1.");
        } else {
            if (attenuationFactor > 0.0F) {
                this.attenuationFactor = attenuationFactor;
            }

        }
    }

}
