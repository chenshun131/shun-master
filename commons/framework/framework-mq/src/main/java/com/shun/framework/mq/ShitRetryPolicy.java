package com.shun.framework.mq;

import com.shun.framework.mq.failover.impl.AttenuationRetryStrategy;
import com.shun.framework.mq.request.MQRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: mew <p />
 * Time: 17/11/8 12:39  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ShitRetryPolicy extends AttenuationRetryStrategy {

    private int maxInterval = 3600000;

    private static final Logger logger = LoggerFactory.getLogger(ShitRetryPolicy.class);

    public ShitRetryPolicy() {
    }

    @Override
    protected void beforeProcess(MQRequest request) {
        super.beforeProcess(request);
        long intveral = ((Long) request.getProperty("AMQ_SCHEDULED_DELAY")).longValue();
        intveral = Math.min(intveral, (long) this.maxInterval);
        logger.trace("intveral:{}", intveral);
        request.addProperty("AMQ_SCHEDULED_DELAY", intveral);
    }

}
