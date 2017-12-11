package com.shun.server.manage.constant;

/**
 * User: mew <p />
 * Time: 17/11/9 08:40  <p />
 * Version: V1.0  <p />
 * Description: upms系统接口结果常量枚举类 <p />
 */
public enum UpmsResultConstant {

    FAILED(0, "failed"),
    SUCCESS(1, "success"),

    INVALID_LENGTH(10001, "Invalid length"),

    EMPTY_USERNAME(10101, "Username cannot be empty"),
    EMPTY_PASSWORD(10102, "Password cannot be empty"),
    INVALID_USERNAME(10103, "Account does not exist"),
    INVALID_PASSWORD(10104, "Password error"),
    INVALID_ACCOUNT(10105, "Invalid account"),
    INVALID_LOGINFAIL(10106, "Login Fail"),
    DISABLE_ACCOUNT(10107, "Disable account"),
    ATTEMPT_TO_MANY(10108, "Attempt login to many times");

    public int code;

    public String message;

    UpmsResultConstant(int code, String message) {
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
