package com.shun.framework.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * User: mew <p />
 * Time: 17/11/6 10:44  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class BasicException extends RuntimeException implements ErrorInfo {

    private static final long serialVersionUID = -8191613077422425908L;

    private String errorCode;

    private String trace;

    private String message;

    public BasicException() {
    }

    public BasicException(ErrorInfo errorInfo) {
        this(errorInfo.getErrorCode(), errorInfo.getMessage(), (Throwable) null);
        this.trace = errorInfo.getTrace();
    }

    public BasicException(String message, Throwable throwable) {
        this((String) null, message, throwable);
    }

    public BasicException(String message) {
        this((String) null, message, (Throwable) null);
    }

    public BasicException(String errorCode, String message) {
        this(errorCode, message, (Throwable) null);
    }

    public BasicException(Throwable throwable) {
        this((String) null, (String) null, throwable);
    }

    public BasicException(String errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
        this.message = message;
        if (throwable != null) {
            this.trace = ExceptionUtils.getStackTrace(throwable);
        }
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static long getSerialversionuid() {
        return -8191613077422425908L;
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

}
