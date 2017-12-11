package com.shun.server.cms.interceptor;

import com.shun.cms.api.CmsMenuService;
import com.shun.cms.dao.po.CmsMenuPo;
import com.shun.cms.dao.po.CmsMenuPoExample;
import com.shun.framework.util.PropertiesFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/27 11:36  <p />
 * Version: V1.0  <p />
 * Description: 公共拦截器 <p />
 */
public class CmsWebInterceptor extends HandlerInterceptorAdapter {

    private static Logger _log = LoggerFactory.getLogger(CmsWebInterceptor.class);

    @Autowired
    private CmsMenuService cmsMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        // 过滤ajax
        if (null != request.getHeader("X-Requested-With") &&
                request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        }

        // zheng-ui静态资源配置信息
        String appName = PropertiesFileUtil.getInstance().get("app.name");
        String uiPath = PropertiesFileUtil.getInstance().get("zheng.ui.path");
        request.setAttribute("appName", appName);
        request.setAttribute("uiPath", uiPath);

        // 菜单
        CmsMenuPoExample cmsMenuExample = new CmsMenuPoExample();
        cmsMenuExample.setOrderByClause("orders asc");
        List<CmsMenuPo> menus = cmsMenuService.selectByExample(cmsMenuExample);
        request.setAttribute("menus", menus);
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
