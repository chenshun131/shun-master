package com.shun.server.manage.controller.stateless;

import com.shun.framework.base.BaseController;
import com.shun.server.manage.constant.UpmsResult;
import com.shun.server.manage.constant.UpmsResultConstant;
import com.shun.server.shiro.enums.LoginType;
import com.shun.server.shiro.token.CustomToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: mew <p />
 * Time: 17/11/23 15:21  <p />
 * Version: V1.0  <p />
 * Description: 无状态 Web 用户信息 <p />
 */
@Controller
@Api(value = "无状态 Web", description = "无状态 Web 用户信息")
@RequestMapping("/stateless")
public class StatelessController extends BaseController {

    private final static Logger _log = LoggerFactory.getLogger(StatelessController.class);

    @ApiOperation(value = "GET请求，跳转到登录页面")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "/stateless/login/login.jsp";
    }

    @ApiOperation(value = "POST请求，进行登录验证")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isBlank(username)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME, "帐号不能为空！");
        }
        if (StringUtils.isBlank(password)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_PASSWORD, "密码不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        // 使用shiro认证
        CustomToken token = new CustomToken(username, password, LoginType.STATELESS);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) { // 帐号不存在
            return new UpmsResult(UpmsResultConstant.INVALID_USERNAME, "用户名或密码错误！");
        } catch (IncorrectCredentialsException e) { // 密码错误
            return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD, "用户名或密码错误！");
        } catch (LockedAccountException e) { // 帐号已锁定
            return new UpmsResult(UpmsResultConstant.INVALID_ACCOUNT, "帐号已锁定，请联系管理员！");
        } catch (DisabledAccountException e) { // 禁用的帐号
            return new UpmsResult(UpmsResultConstant.DISABLE_ACCOUNT, "帐号已禁用，请联系管理员！");
        } catch (ExcessiveAttemptsException e) { // 登录失败次数过多
            return new UpmsResult(UpmsResultConstant.ATTEMPT_TO_MANY, " 登录失败次数过多！");
        } catch (AuthenticationException e) { // 登录失败，AuthenticationException 是所有认证时异常的父类
            return new UpmsResult(UpmsResultConstant.INVALID_LOGINFAIL, "登录失败！");
        }

        // 回跳登录前地址
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(backurl)) {
            return new UpmsResult(UpmsResultConstant.SUCCESS, "/");
        } else {
            return new UpmsResult(UpmsResultConstant.SUCCESS, backurl);
        }
    }

}
