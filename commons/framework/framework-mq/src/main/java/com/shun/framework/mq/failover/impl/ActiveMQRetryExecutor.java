package com.shun.framework.mq.failover.impl;

import com.shun.framework.mq.jms.JmsAccessor;
import com.shun.framework.mq.failover.RetryExecutor;
import com.shun.framework.mq.request.MQRequest;

/**
 * User: mew <p />
 * Time: 17/11/8 11:11  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ActiveMQRetryExecutor implements RetryExecutor {

    private JmsAccessor mqAccessor;

    public ActiveMQRetryExecutor(JmsAccessor mqAccessor) {
        this.mqAccessor = mqAccessor;
    }

    public JmsAccessor getMqAccessor() {
        return this.mqAccessor;
    }

    public void retrySend(MQRequest request) throws Exception {
        this.getMqAccessor().sendMessage(request.getDestination(), request.getDestinationType(),
                request.getMessageFormat(), request.isTransacted(), request.getAcknowledgeMode(),
                request.getDeliveryMode(), request);
    }

}
