package com.shun.framework.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * User: mew <p />
 * Time: 17/11/6 10:52  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class BasicRuntimeException extends RuntimeException implements ErrorInfo {

    private static final long serialVersionUID = -8808822482719918020L;

    private String errorCode;

    private String trace;

    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTrace() {
        return this.trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public BasicRuntimeException() {
    }

    public BasicRuntimeException(ErrorInfo errorInfo) {
        this(errorInfo.getErrorCode(), errorInfo.getMessage());
        this.trace = errorInfo.getTrace();
    }

    public BasicRuntimeException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public BasicRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
        this.trace = ExceptionUtils.getStackTrace(throwable);
    }

    public BasicRuntimeException(String message) {
        super(message);
        this.message = message;
    }

    public BasicRuntimeException(Throwable throwable) {
        super(throwable);
        this.trace = ExceptionUtils.getStackTrace(throwable);
    }

}
