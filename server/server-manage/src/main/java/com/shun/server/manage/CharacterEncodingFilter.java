package com.shun.server.manage;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * User: mew <p />
 * Time: 17/11/8 18:04  <p />
 * Version: V1.0  <p />
 * Description: 强制进行转码过滤器 <p />
 */
@WebFilter(filterName = "CharacterEncodingFilter",
        urlPatterns = "/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class CharacterEncodingFilter extends org.springframework.web.filter.CharacterEncodingFilter {

}

