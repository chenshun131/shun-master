package com.shun.upms.api;

import com.shun.framework.base.BaseServiceMock;
import com.shun.upms.dao.mapper.UpmsUserRolePoMapper;
import com.shun.upms.dao.po.UpmsUserRolePo;
import com.shun.upms.dao.po.UpmsUserRolePoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: mew <p />
 * Time: 17/11/10 17:11  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class UpmsUserRoleServiceMock extends BaseServiceMock<UpmsUserRolePoMapper, UpmsUserRolePo,
        UpmsUserRolePoExample> implements UpmsUserRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserRoleServiceMock.class);

    @Override
    public int role(String[] roleIds, int id) {
        _log.info("UpmsUserRoleServiceMock => role");
        return 0;
    }

}
