package com.shun.upms.api;

import com.shun.upms.dao.po.*;

import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/9 14:13  <p />
 * Version: V1.0  <p />
 * Description: upms系统接口 <p />
 */
public interface UpmsApiService {

    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsPermissionPo> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsPermissionPo> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId);

    /**
     * 根据用户id获取所属的角色
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsRolePo> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

    /**
     * 根据用户id获取所属的角色
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsRolePo> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId);

    /**
     * 根据角色id获取所拥有的权限
     *
     * @param upmsRoleId
     * @return
     */
    List<UpmsRolePermissionPo> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId);

    /**
     * 根据用户id获取所拥有的权限
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsUserPermissionPo> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据条件获取系统数据
     *
     * @param upmsSystemExample
     * @return
     */
    List<UpmsSystemPo> selectUpmsSystemByExample(UpmsSystemPoExample upmsSystemExample);

    /**
     * 根据条件获取组织数据
     *
     * @param upmsOrganizationExample
     * @return
     */
    List<UpmsOrganizationPo> selectUpmsOrganizationByExample(UpmsOrganizationPoExample upmsOrganizationExample);

    /**
     * 根据username获取UpmsUser
     *
     * @param username
     * @return
     */
    UpmsUserPo selectUpmsUserByUsername(String username);

    /**
     * 写入操作日志
     *
     * @param record
     * @return
     */
    int insertUpmsLogSelective(UpmsLogPo record);

}
