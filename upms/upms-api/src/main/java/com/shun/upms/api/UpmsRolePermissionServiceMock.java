package com.shun.upms.api;

import com.alibaba.fastjson.JSONArray;
import com.shun.framework.base.BaseServiceMock;
import com.shun.upms.dao.mapper.UpmsRolePermissionPoMapper;
import com.shun.upms.dao.po.UpmsRolePermissionPo;
import com.shun.upms.dao.po.UpmsRolePermissionPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: mew <p />
 * Time: 17/11/10 17:07  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class UpmsRolePermissionServiceMock extends BaseServiceMock<UpmsRolePermissionPoMapper, UpmsRolePermissionPo,
        UpmsRolePermissionPoExample> implements UpmsRolePermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsRolePermissionServiceMock.class);

    @Override
    public int rolePermission(JSONArray datas, int id) {
        _log.info("UpmsRolePermissionServiceMock => rolePermission");
        return 0;
    }

}
