package com.shun.framework.mq.jms;

import com.shun.framework.mq.core.MQAccessor;
import com.shun.framework.mq.core.MQException;
import com.shun.framework.mq.request.MQRequest;

import javax.jms.JMSException;

/**
 * User: mew <p />
 * Time: 17/11/8 10:58  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface JmsAccessor extends MQAccessor {

    void sendMessage(String var1, int var2, MQRequest var3) throws JMSException;

    void sendMessage(String var1, int var2, int var3, boolean var4, int var5, int var6, Object var7)
            throws JMSException;

    Object receiveMessage(String var1) throws MQException;

    Object receiveMessage(String var1, int var2, boolean var3, int var4, String var5) throws MQException;

}
