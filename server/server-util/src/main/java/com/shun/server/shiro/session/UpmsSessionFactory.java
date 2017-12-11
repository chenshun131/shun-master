package com.shun.server.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * User: mew <p />
 * Time: 17/11/9 09:03  <p />
 * Version: V1.0  <p />
 * Description: session工厂，创建会话工厂，默认提供 SimpleSessionFactory 用来创建 SimpleSession 会话 <p />
 */
public class UpmsSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext sessionContext) {
        UpmsSession session = new UpmsSession();
        if (null != sessionContext && sessionContext instanceof WebSessionContext) {
            WebSessionContext webSessionContext = (WebSessionContext) sessionContext;
            HttpServletRequest request = (HttpServletRequest) webSessionContext.getServletRequest();
            if (null != request) {
                session.setHost(request.getRemoteAddr()); // 用户访问时 IP 地址
                session.setUserAgent(request.getHeader("User-Agent")); // 用户浏览器类型
            }
        }
        return session;
    }

}
