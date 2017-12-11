package com.shun.framework.mq.failover.impl;

import com.shun.framework.mq.failover.RetryStrategy;
import com.shun.framework.mq.request.MQRequest;

/**
 * User: mew <p />
 * Time: 17/11/8 11:15  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DurationRetryStrategy extends AbstractRetryStrategy implements RetryStrategy {

    private long duration = 2880L;

    public DurationRetryStrategy() {
    }

    public void setDuration(long duration) {
        if (duration > 0L) {
            this.duration = duration;
        }

    }

    public long getDuration() {
        return this.duration;
    }

    public void retry(MQRequest request) {
        if (!request.containsProperty("duration")) {
            request.addProperty("duration", System.currentTimeMillis() + this.duration * 60000L);
        }

        super.retry(request);
    }

    protected boolean needRetry(MQRequest request) {
        if (this.duration <= 0L) {
            return true;
        } else {
            return request.containsProperty("duration") &&
                    Long.parseLong(request.getProperty("duration").toString()) > System.currentTimeMillis();
        }
    }

}
