package com.shun.server.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: mew <p />
 * Time: 17/11/9 08:48  <p />
 * Version: V1.0  <p />
 * Description: 会话监听器用于监听会话创建、过期及停止事件 <p />
 */
public class UpmsSessionListener implements SessionListener {

    private static Logger _log = LoggerFactory.getLogger(UpmsSessionListener.class);

    @Override
    public void onStart(Session session) { // 会话创建时触发
        _log.debug("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) { // 会话过期时触发
        _log.debug("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) { // 退出/会话过期时触发
        _log.debug("会话过期：" + session.getId());
    }

}
