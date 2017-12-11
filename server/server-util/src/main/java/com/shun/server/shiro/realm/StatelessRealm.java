package com.shun.server.shiro.realm;

import com.shun.framework.util.HmacSHA256Utils;
import com.shun.server.shiro.enums.LoginType;
import com.shun.server.shiro.token.CustomToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * User: mew <p />
 * Time: 17/11/16 15:46  <p />
 * Version: V1.0  <p />
 * Description: 无状态 Web 的 Realm <br/>
 * 首先根据客户端传入的用户名获取相应的密钥，然后使用密钥对请求参数生成服务器端的消息摘要；然后与客户端的消息摘要进行匹配；
 * 如果匹配说明是合法客户端传入的；否则是非法的。这种方式是有漏洞的，一旦别人获取到该请求，可以重复请求<p />
 */
public class StatelessRealm extends BaseRealm {

    @Override
    public String getName() {
        return LoginType.STATELESS.getName();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 根据用户名查找角色，请根据需求实现
        String username = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("admin");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        CustomToken customToken = (CustomToken) token;
        String username = customToken.getUsername();
        String key = getKey(username);//根据用户名获取密钥（和客户端的一样）
        // 在服务器端生成客户端参数消息摘要
        String serverDigest = HmacSHA256Utils.digest(key, customToken.getParams());
        // 然后进行客户端消息摘要和服务器端消息摘要的匹配
        return new SimpleAuthenticationInfo(username, serverDigest, getName());
    }

    /**
     * 得到密钥，此处硬编码一个
     *
     * @param username
     * @return
     */
    private String getKey(String username) {
        if ("admin".equals(username)) {
            return "dadadswdewq2ewdwqdwadsadasd";
        }
        return null;
    }

}
