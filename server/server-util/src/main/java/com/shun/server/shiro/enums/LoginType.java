package com.shun.server.shiro.enums;

/**
 * User: mew <p />
 * Time: 17/11/23 11:08  <p />
 * Version: V1.0  <p />
 * Description: 用户登录类型 <p />
 */
public enum LoginType {

    USER("User", "普通用户"),
    ADMIN("Admin", "管理员登录"),
    STATELESS("Stateless", "无状态登录");

    private String name;

    private String desc;

    LoginType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
