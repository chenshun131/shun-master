package com.shun.server.manage.interceptor;

import com.shun.framework.util.PropertiesFileUtil;
import com.shun.upms.api.UpmsApiService;
import com.shun.upms.dao.po.UpmsUserPo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: mew <p />
 * Time: 17/11/9 15:15  <p />
 * Version: V1.0  <p />
 * Description: 登录信息拦截器 <p />
 */
public class UpmsInterceptor extends HandlerInterceptorAdapter {

    private static Logger _log = LoggerFactory.getLogger(UpmsInterceptor.class);

    private static final String ZHENG_OSS_ALIYUN_OSS_POLICY =
            PropertiesFileUtil.getInstance("application").get("zheng.oss.aliyun.oss.policy");

    @Autowired
    private UpmsApiService upmsApiService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute("ZHENG_OSS_ALIYUN_OSS_POLICY", ZHENG_OSS_ALIYUN_OSS_POLICY);
        // 过滤ajax
        if (null != request.getHeader("X-Requested-With") &&
                request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        }
        // 登录信息
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UpmsUserPo upmsUserPo = upmsApiService.selectUpmsUserByUsername(username);
        if (upmsUserPo == null) {
            return false;
        }
        request.setAttribute("upmsUser", upmsUserPo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
                                               Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}
