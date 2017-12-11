package com.shun.framework.mq.exception;

/**
 * User: mew <p />
 * Time: 17/11/8 09:27  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class CreateMessageHandlerException extends MessageHandleException {

    private static final long serialVersionUID = -3332333784318565571L;

    public CreateMessageHandlerException() {
    }

    public CreateMessageHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateMessageHandlerException(String message) {
        super(message);
    }

    public CreateMessageHandlerException(Throwable cause) {
        super(cause);
    }

}
