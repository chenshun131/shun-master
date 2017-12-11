package com.shun.server.shiro.realm;

import com.shun.framework.util.MD5Util;
import com.shun.framework.util.PropertiesFileUtil;
import com.shun.server.shiro.enums.LoginType;
import com.shun.upms.api.UpmsApiService;
import com.shun.upms.dao.po.UpmsPermissionPo;
import com.shun.upms.dao.po.UpmsRolePo;
import com.shun.upms.dao.po.UpmsUserPo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: mew <p />
 * Time: 17/11/9 08:48  <p />
 * Version: V1.0  <p />
 * Description: 用户认证和授权 使用继承 AuthorizingRealm 的方式用户就不必再次获取授权信息 <p />
 */
public class UpmsRealm extends BaseRealm {

    private static Logger _log = LoggerFactory.getLogger(UpmsRealm.class);

    @Autowired
    private UpmsApiService upmsApiService;

    /** 限制时间尝试登录次数 */
//    private Cache<String, AtomicInteger> passwordRetryCache;

//    public UpmsRealm(CacheManager cacheManager) {
//        _log.info("UpmsRealm -> name : " + getName());
//        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
//    }
    @Override
    public String getName() {
        return LoginType.ADMIN.getName();
    }

    /**
     * 授权：验证权限时调用
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        UpmsUserPo upmsUser = upmsApiService.selectUpmsUserByUsername(username);

        // 当前用户所有角色
        List<UpmsRolePo> upmsRolePos = upmsApiService.selectUpmsRoleByUpmsUserId(upmsUser.getUserId());
        Set<String> roles = new HashSet<>();
        for (UpmsRolePo upmsRolePo : upmsRolePos) {
            if (StringUtils.isNotBlank(upmsRolePo.getName())) {
                roles.add(upmsRolePo.getName());
            }
        }

        // 当前用户所有权限
        List<UpmsPermissionPo> upmsPermissionPos =
                upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        Set<String> permissions = new HashSet<>();
        for (UpmsPermissionPo upmsPermissionPo : upmsPermissionPos) {
            if (StringUtils.isNotBlank(upmsPermissionPo.getPermissionValue())) {
                permissions.add(upmsPermissionPo.getPermissionValue());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证：登录时调用
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException { // 此处抛出的一场会在 login 方法处继续抛出
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        // client无密认证
        String upmsType = PropertiesFileUtil.getInstance("application").get("zheng.upms.type");
        if ("client".equals(upmsType)) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }

//        // 限制1小时内，密码登录次数
//        AtomicInteger retryCount = passwordRetryCache.get(username);
//        if (retryCount == null) {
//            retryCount = new AtomicInteger(0);
//            passwordRetryCache.put(username, retryCount);
//        }
//        if (retryCount.incrementAndGet() > 5) { // 登录失败次数过多
//            throw new ExcessiveAttemptsException();
//        }

        // 查询用户信息
        UpmsUserPo upmsUserPo = upmsApiService.selectUpmsUserByUsername(username);
        if (null == upmsUserPo) { // 帐号不存在
            throw new UnknownAccountException();
        }
        if (!upmsUserPo.getPassword().equals(MD5Util.MD5(password + upmsUserPo.getSalt()))) { // 密码错误
            throw new IncorrectCredentialsException();
        }
        if (upmsUserPo.getLocked() == 1) { // 帐号已锁定 0:正常,1:锁定
            throw new LockedAccountException();
        }

        // 身份认证验证成功，返回一个 AuthenticationInfo 实现
//        passwordRetryCache.remove(username); // 清除密码限制登录次数限制
        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
