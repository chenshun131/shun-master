package com.shun.framework.api;

import java.io.Serializable;

/**
 * User: mew <p />
 * Time: 17/11/6 11:07  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class UserIp implements Serializable {

    private static final long serialVersionUID = -1776549305217673147L;

    private String userIp;

    public UserIp() {
    }

    public String getUserIp() {
        return this.userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

}
