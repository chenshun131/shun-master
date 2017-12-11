package com.shun.upms.core.service.impl;

import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.upms.api.UpmsUserRoleService;
import com.shun.upms.dao.mapper.UpmsUserRolePoMapper;
import com.shun.upms.dao.po.UpmsUserRolePo;
import com.shun.upms.dao.po.UpmsUserRolePoExample;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:11 <p />
 * Version: V1.0  <p />
 * Description: UpmsUserRolePoService实现 <p />
 */
@Service("upmsUserRoleService")
@Transactional
@BaseService
public class UpmsUserRoleServiceImpl extends BaseServiceImpl<UpmsUserRolePoMapper, UpmsUserRolePo,
        UpmsUserRolePoExample> implements UpmsUserRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserRoleServiceImpl.class);

    @Autowired
    UpmsUserRolePoMapper upmsUserRolePoMapper;

    @Override
    public int role(String[] roleIds, int id) {
        int result = 0;
        // 删除旧记录
        UpmsUserRolePoExample upmsUserRoleExample = new UpmsUserRolePoExample();
        upmsUserRoleExample.createCriteria().andUserIdEqualTo(id);
        upmsUserRolePoMapper.deleteByExample(upmsUserRoleExample);

        // 增加新记录
        if (null != roleIds) {
            for (String roleId : roleIds) {
                if (StringUtils.isBlank(roleId)) {
                    continue;
                }
                UpmsUserRolePo upmsUserRole = new UpmsUserRolePo();
                upmsUserRole.setUserId(id);
                upmsUserRole.setRoleId(NumberUtils.toInt(roleId));
                result = upmsUserRolePoMapper.insertSelective(upmsUserRole);
            }
        }
        return result;
    }

}
