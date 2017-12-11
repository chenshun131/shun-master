package com.shun.framework.exception.extend;

import org.apache.commons.lang3.StringUtils;

/**
 * User: mew <p />
 * Time: 17/11/6 10:55  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2599817063600637097L;

    private String code;

    private Object params;

    private String userMsg;

    public BusinessException(String message) {
        super(message);
        this.code = message;
    }

    public BusinessException(String message, String code) {
        this(StringUtils.isBlank(message) ? code : message);
        this.code = code;
    }

    public BusinessException(String message, String code, Object params) {
        this(StringUtils.isBlank(message) ? code : message, code);
        this.params = params;
    }

    public BusinessException(String message, String code, Object params, String userMsg) {
        this(StringUtils.isBlank(message) ? code : message, code, params);
        this.userMsg = userMsg;
    }

    public BusinessException(String message, String code, Throwable e) {
        this(StringUtils.isBlank(message) ? code : message, e);
        this.code = code;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
    }

    public String getCode() {
        return this.code;
    }

    public BusinessException setCode(String code) {
        this.code = code;
        return this;
    }

    public Object getParams() {
        return this.params;
    }

    public BusinessException setParams(String params) {
        this.params = params;
        return this;
    }

    public String getUserMsg() {
        return this.userMsg;
    }

    public BusinessException setUserMsg(String userMsg) {
        this.userMsg = userMsg;
        return this;
    }

}
