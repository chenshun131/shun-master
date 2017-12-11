package com.shun.framework.mq.listener;

/**
 * User: mew <p />
 * Time: 17/11/8 11:38  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public enum MQMessageListenerStatus {

    INACTIVE(0),
    RUNNING(1),
    STOPPED(2);

    private int value;

    private MQMessageListenerStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
