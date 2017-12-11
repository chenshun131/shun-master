package com.shun.framework.mq.handler;

import com.shun.framework.mq.request.MQRequest;

/**
 * User: mew <p />
 * Time: 17/11/8 11:20  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface MessageHandler {

    void handle(Object var1);

    void handleMessage(Object var1) throws Exception;

    MQRequest convertToMQRequest(Object var1);

}
