package com.shun.framework.mq.core;

/**
 * User: mew <p />
 * Time: 17/11/8 10:40  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MQException extends Exception {

    private static final long serialVersionUID = -3213764527954458025L;

    public MQException() {
    }

    public MQException(Throwable cause) {
        super(cause);
    }

    public MQException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQException(String message) {
        super(message);
    }

}
