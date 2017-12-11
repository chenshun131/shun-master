package com.shun.upms.api;

import com.alibaba.fastjson.JSONArray;
import com.shun.framework.base.BaseServiceMock;
import com.shun.upms.dao.mapper.UpmsPermissionPoMapper;
import com.shun.upms.dao.po.UpmsPermissionPo;
import com.shun.upms.dao.po.UpmsPermissionPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: mew <p />
 * Time: 17/11/10 17:07  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class UpmsPermissionServiceMock extends BaseServiceMock<UpmsPermissionPoMapper, UpmsPermissionPo,
        UpmsPermissionPoExample> implements UpmsPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsPermissionServiceMock.class);

    @Override
    public JSONArray getTreeByRoleId(Integer roleId) {
        _log.info("UpmsPermissionServiceMock => getTreeByRoleId");
        return null;
    }

    @Override
    public JSONArray getTreeByUserId(Integer usereId, Byte type) {
        _log.info("UpmsPermissionServiceMock => getTreeByUserId");
        return null;
    }

}
