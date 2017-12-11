package com.shun.server.cms.constant;

/**
 * User: mew <p />
 * Time: 17/11/27 11:03  <p />
 * Version: V1.0  <p />
 * Description: cms系统接口结果常量枚举类 <p />
 */
public enum CmsResultConstant {

    FAILED(0, "failed"),
    SUCCESS(1, "success"),

    FILE_TYPE_ERROR(20001, "File type not supported!"),
    INVALID_LENGTH(20002, "Invalid length"),
    INVALID_PARAMETER(20003, "Invalid parameter");

    public int code;

    public String message;

    CmsResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
