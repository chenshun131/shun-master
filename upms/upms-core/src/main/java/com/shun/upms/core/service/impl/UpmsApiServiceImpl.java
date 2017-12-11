package com.shun.upms.core.service.impl;

import com.shun.upms.api.UpmsApiService;
import com.shun.upms.core.dao.mapper.UpmsApiMapper;
import com.shun.upms.dao.mapper.*;
import com.shun.upms.dao.po.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/10 10:10  <p />
 * Version: V1.0  <p />
 * Description: UpmsApiService实现 <p />
 */
@Service("upmsApiService")
@Transactional
public class UpmsApiServiceImpl implements UpmsApiService {

    private static Logger _log = LoggerFactory.getLogger(UpmsApiServiceImpl.class);

    @Autowired
    private UpmsUserPoMapper upmsUserMapper;

    @Autowired
    private UpmsApiMapper upmsApiMapper;

    @Autowired
    private UpmsRolePermissionPoMapper upmsRolePermissionMapper;

    @Autowired
    private UpmsUserPermissionPoMapper upmsUserPermissionMapper;

    @Autowired
    private UpmsSystemPoMapper upmsSystemMapper;

    @Autowired
    private UpmsOrganizationPoMapper upmsOrganizationMapper;

    @Autowired
    private UpmsLogPoMapper upmsLogMapper;

    /**
     * 根据用户id获取所拥有的权限
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsPermissionPo> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        if (upmsUserId == null) {
            return new ArrayList<>();
        }
        // 用户不存在或锁定状态
        UpmsUserPo upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            _log.info("selectUpmsPermissionByUpmsUserId : upmsUserId={}", upmsUserId);
            return new ArrayList<>();
        }
        return upmsApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId);
    }

    /**
     * 根据用户id获取所拥有的权限
     *
     * @param upmsUserId
     * @return
     */
    @Override
    @Cacheable(value = "zheng-upms-rpc-service-ehcache", key = "'selectUpmsPermissionByUpmsUserId_' + #upmsUserId")
    public List<UpmsPermissionPo> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId) {
        if (upmsUserId == null) {
            return new ArrayList<>();
        }
        return selectUpmsPermissionByUpmsUserId(upmsUserId);
    }

    /**
     * 根据用户id获取所属的角色
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsRolePo> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        if (upmsUserId == null) {
            return new ArrayList<>();
        }
        // 用户不存在或锁定状态
        UpmsUserPo upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            _log.info("selectUpmsRoleByUpmsUserId : upmsUserId={}", upmsUserId);
            return new ArrayList<>();
        }
        return upmsApiMapper.selectUpmsRoleByUpmsUserId(upmsUserId);
    }

    /**
     * 根据用户id获取所属的角色
     *
     * @param upmsUserId
     * @return
     */
    @Override
    @Cacheable(value = "zheng-upms-rpc-service-ehcache", key = "'selectUpmsRoleByUpmsUserId_' + #upmsUserId")
    public List<UpmsRolePo> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId) {
        if (upmsUserId == null) {
            return new ArrayList<>();
        }
        return selectUpmsRoleByUpmsUserId(upmsUserId);
    }

    /**
     * 根据角色id获取所拥有的权限
     *
     * @param upmsRoleId
     * @return
     */
    @Override
    public List<UpmsRolePermissionPo> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        UpmsRolePermissionPoExample upmsRolePermissionExample = new UpmsRolePermissionPoExample();
        upmsRolePermissionExample.createCriteria().andRoleIdEqualTo(upmsRoleId);
        return upmsRolePermissionMapper.selectByExample(upmsRolePermissionExample);
    }

    /**
     * 根据用户id获取所拥有的权限
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsUserPermissionPo> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        UpmsUserPermissionPoExample upmsUserPermissionExample = new UpmsUserPermissionPoExample();
        upmsUserPermissionExample.createCriteria().andUserIdEqualTo(upmsUserId);
        return upmsUserPermissionMapper.selectByExample(upmsUserPermissionExample);
    }

    /**
     * 根据条件获取系统数据
     *
     * @param upmsSystemExample
     * @return
     */
    @Override
    public List<UpmsSystemPo> selectUpmsSystemByExample(UpmsSystemPoExample upmsSystemExample) {
        return upmsSystemMapper.selectByExample(upmsSystemExample);
    }

    /**
     * 根据条件获取组织数据
     *
     * @param upmsOrganizationExample
     * @return
     */
    @Override
    public List<UpmsOrganizationPo> selectUpmsOrganizationByExample(UpmsOrganizationPoExample upmsOrganizationExample) {
        return upmsOrganizationMapper.selectByExample(upmsOrganizationExample);
    }

    /**
     * 根据username获取UpmsUser
     *
     * @param username
     * @return
     */
    @Override
    public UpmsUserPo selectUpmsUserByUsername(String username) {
        if (StringUtils.isNotBlank(username)) {
            UpmsUserPoExample upmsUserExample = new UpmsUserPoExample();
            upmsUserExample.createCriteria().andUsernameEqualTo(username);
            List<UpmsUserPo> upmsUsers = upmsUserMapper.selectByExample(upmsUserExample);
            if (null != upmsUsers && upmsUsers.size() > 0) {
                return upmsUsers.get(0);
            }
        }
        return null;
    }

    /**
     * 写入操作日志
     *
     * @param record
     * @return
     */
    @Override
    public int insertUpmsLogSelective(UpmsLogPo record) {
        return upmsLogMapper.insertSelective(record);
    }

}
