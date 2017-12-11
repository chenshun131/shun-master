package com.shun.upms.core.dao.mapper;

import com.shun.upms.dao.po.UpmsPermissionPo;
import com.shun.upms.dao.po.UpmsRolePo;

import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/10 10:05  <p />
 * Version: V1.0  <p />
 * Description: 用户VOMapper <p />
 */
public interface UpmsApiMapper {

    /**
     * 根据用户id获取所拥有的权限
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsPermissionPo> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据用户id获取所属的角色
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsRolePo> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

}
