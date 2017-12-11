package com.shun.server.shiro.filter;

import com.shun.server.constant.StatelessConstant;
import com.shun.server.shiro.enums.LoginType;
import com.shun.server.shiro.token.CustomToken;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/16 15:26  <p />
 * Version: V1.0  <p />
 * Description: 无状态 Web 过滤器 <br/>
 * 获取客户端传入的用户名、请求参数、消息摘要，生成 StatelessToken；然后交给相应的 Realm 进行认证 <p />
 */
public class StatelessAuthcFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 客户端生成的消息摘要
        String clientDigest = request.getParameter(StatelessConstant.PARAM_DIGEST);
        // 客户端传入的用户身份
        String username = request.getParameter(StatelessConstant.PARAM_USERNAME);
        // 客户端请求的参数列表
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
        params.remove(StatelessConstant.PARAM_DIGEST);
        // 生成无状态 Token
        CustomToken token = new CustomToken(username, clientDigest, LoginType.STATELESS, params);
        try {
            // 委托给 Realm 进行登录
            getSubject(request, response).login(token);
        } catch (Exception e) {
            e.printStackTrace();
            onLoginFail(response); // 登录失败
            return false;
        }
        return true;
    }

    /**
     * 登录失败时默认返回 401 状态码
     *
     * @param response
     * @throws IOException
     */
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }

}
