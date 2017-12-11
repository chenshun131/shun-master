package com.shun.server.shiro.token;

import com.shun.server.shiro.enums.LoginType;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/23 10:50  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class CustomToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 3073414437228896360L;

    /** 登录类型 */
    private LoginType loginType;

    private Map<String, ?> params;

    public CustomToken(String username, String password, LoginType loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public CustomToken(String username, String password, LoginType loginType, Map<String, ?> params) {
        super(username, password);
        this.loginType = loginType;
        this.params = params;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

}
