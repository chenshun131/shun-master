package com.shun.server.manage;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * User: mew <p />
 * Time: 17/11/8 18:00  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@WebFilter(filterName = "shiroFilter",
        urlPatterns = "/*",
        initParams = {@WebInitParam(name = "targetFilterLifecycle", value = "true")})
public class ShiroFilter extends org.springframework.web.filter.DelegatingFilterProxy {

}
