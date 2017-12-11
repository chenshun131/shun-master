package com.shun.framework.mq.failover.impl;

import com.shun.framework.mq.failover.RetryExecutor;
import com.shun.framework.mq.jms.JmsAccessor;

/**
 * User: mew <p />
 * Time: 17/11/8 09:32  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public abstract class AbstractRetryExecutor implements RetryExecutor {

    private JmsAccessor mqAccessor;

    public AbstractRetryExecutor() {
    }

    public JmsAccessor getMqAccessor() {
        if (this.mqAccessor == null) {
            throw new IllegalArgumentException("MqAccessor can't be null.");
        } else {
            return this.mqAccessor;
        }
    }

    public void setMqAccessor(JmsAccessor mqAccessor) {
        this.mqAccessor = mqAccessor;
    }

}
