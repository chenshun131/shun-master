package com.shun.framework.mq.management.service;

import com.shun.framework.mq.management.model.FailedMessage;

import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/8 10:43  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface FailedMessageService {

    int save(FailedMessage var1);

    FailedMessage getFailedMessage(Long var1);

    List<FailedMessage> findFailedMessageByActionId(Integer var1);

}
