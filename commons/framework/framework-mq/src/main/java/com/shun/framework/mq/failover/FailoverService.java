package com.shun.framework.mq.failover;

import com.shun.framework.mq.request.MQRequest;

/**
 * User: mew <p />
 * Time: 17/11/8 09:31  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface FailoverService {

    String AMQ_SCHEDULED_DELAY = "AMQ_SCHEDULED_DELAY";

    void retry(MQRequest var1);

    /** @deprecated  */
    @Deprecated
    void retry(Object var1, int var2, String var3, String var4);

}
