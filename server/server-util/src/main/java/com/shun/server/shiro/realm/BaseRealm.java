package com.shun.server.shiro.realm;

import org.apache.shiro.realm.AuthorizingRealm;

/**
 * User: mew <p />
 * Time: 17/11/23 11:39  <p />
 * Version: V1.0  <p />
 * Description: 所有自定义 Realm 都需继承此类 <p />
 */
public abstract class BaseRealm extends AuthorizingRealm {

//    @Override
//    public boolean supports(AuthenticationToken token) {
//        // 仅支持 CustomToken 类型的 Token，这样就要求所有的地方使用的 Token 都必须使用 CustomToken 进行登录操作
//        return token instanceof CustomToken;
//    }

}
