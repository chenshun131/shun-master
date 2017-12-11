package com.shun.framework.exception;

/**
 * User: mew <p />
 * Time: 17/11/6 10:52  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class BusinessLogicException extends BasicException {

    private static final long serialVersionUID = -8141950412487935167L;

    public BusinessLogicException() {
    }

    public BusinessLogicException(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    public BusinessLogicException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    public BusinessLogicException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BusinessLogicException(String message) {
        super(message);
    }

    public BusinessLogicException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessLogicException(Throwable throwable) {
        super(throwable);
    }

}
