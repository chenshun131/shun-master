package com.shun.framework.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * User: mew <p />
 * Time: 17/11/6 10:53  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ErrorInfoBean implements ErrorInfo {

    private String errorCode;

    private String trace;

    private String message;

    public ErrorInfoBean() {
    }

    public ErrorInfoBean(String errorCode, String message, String trace) {
        this.errorCode = errorCode;
        this.message = message;
        this.trace = trace;
    }

    public String getDescription() {
        return String.format("result code:%s, message:%s", this.errorCode, this.message);
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

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("message", this.message)
                .append("trace", this.trace).append("errorCode", this.errorCode).toString();
    }

}
