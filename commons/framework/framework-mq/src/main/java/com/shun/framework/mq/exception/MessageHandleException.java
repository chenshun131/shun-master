package com.shun.framework.mq.exception;

/**
 * User: mew <p />
 * Time: 17/11/8 09:28  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MessageHandleException extends RuntimeException {

    private static final long serialVersionUID = 780500232284965587L;

    public MessageHandleException() {
    }

    public MessageHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageHandleException(String message) {
        super(message);
    }

    public MessageHandleException(Throwable cause) {
        super(cause);
    }

}
