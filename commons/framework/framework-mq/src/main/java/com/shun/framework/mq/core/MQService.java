package com.shun.framework.mq.core;

import com.shun.framework.mq.request.MQRequest;

/**
 * User: mew <p />
 * Time: 17/11/8 10:40  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface MQService {

    void sendMessage(MQRequest var1) throws MQException;

    Object receiveMessage(String var1) throws MQException;

    Object receiveMessage(int var1) throws MQException;

    Object receiveMessage(int var1, String var2) throws MQException;

    Object receiveMessage(int var1, boolean var2, int var3, String var4) throws MQException;

}
