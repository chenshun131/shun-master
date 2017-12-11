package com.shun.framework.mq.constant;

/**
 * User: mew <p />
 * Time: 17/11/8 10:37  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface AcknowledgeMode {

    int SESSION_TRANSACTED = 0;

    int AUTO_ACKNOWLEDGE = 1;

    int CLIENT_ACKNOWLEDGE = 2;

    int DUPS_OK_ACKNOWLEDGE = 3;

    int ACK = 11;

    int NON_ACK = 12;

}
