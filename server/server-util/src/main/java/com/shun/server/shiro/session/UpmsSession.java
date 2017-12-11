package com.shun.server.shiro.session;

import org.apache.shiro.session.mgt.SimpleSession;

/**
 * User: mew <p />
 * Time: 17/11/9 08:55  <p />
 * Version: V1.0  <p />
 * Description: 重写 session , 新增 保存当前登录用户的在线状态支持如离线等状态的控制 以及 用户浏览器类型 <p />
 */
public class UpmsSession extends SimpleSession {

    private static final long serialVersionUID = 1445596775028459275L;

    public enum OnlineStatus {
        on_line("在线"),
        off_line("离线"),
        force_logout("强制退出");

        private final String info;

        OnlineStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    /** 用户浏览器类型 */
    private String userAgent;

    /** 在线状态 */
    private OnlineStatus status = OnlineStatus.off_line;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

}
