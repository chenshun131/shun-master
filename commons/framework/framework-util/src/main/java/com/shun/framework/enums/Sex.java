package com.shun.framework.enums;

/**
 * User: mew <p />
 * Time: 17/11/6 11:14  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public enum Sex {

    Male("1"),
    Female("0");

    private String code;

    private Sex(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
