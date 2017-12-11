package com.shun.framework.mq.failover;

import com.shun.framework.mq.request.MQRequest;

/**
 * User: mew <p />
 * Time: 17/11/8 09:32  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface RetryStrategy {

    void retry(MQRequest var1);

}
