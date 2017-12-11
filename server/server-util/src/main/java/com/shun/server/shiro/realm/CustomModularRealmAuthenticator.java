package com.shun.server.shiro.realm;

import com.shun.server.shiro.enums.LoginType;
import com.shun.server.shiro.token.CustomToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: mew <p />
 * Time: 17/11/23 10:48  <p />
 * Version: V1.0  <p />
 * Description: 自定义Authenticator <br/>
 * 根据不同的登录类型，访问不同的 Realm <p />
 */
public class CustomModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        assertRealmsConfigured(); // 判断getRealms()是否返回为空
        CustomToken customToken = (CustomToken) authenticationToken; // 强制转换回自定义 Token
        LoginType loginType = customToken.getLoginType(); // 获取登录类型
        Collection<Realm> realms = getRealms(); // 所有 Realm

        // 登录类型对应的所有Realm
        Collection<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
            if (realm.getName().contains(loginType.getName())) {
                typeRealms.add(realm);
            }
        }

        // 判断是单 Realm还是多Realm
        if (typeRealms.size() == 1) {
            return doSingleRealmAuthentication(typeRealms.iterator().next(), authenticationToken);
        } else {
            return doMultiRealmAuthentication(typeRealms, authenticationToken);
        }
    }

}
