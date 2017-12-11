package com.shun.upms.api;

import com.alibaba.fastjson.JSONArray;
import com.shun.framework.base.BaseServiceMock;
import com.shun.upms.dao.mapper.UpmsUserPermissionPoMapper;
import com.shun.upms.dao.po.UpmsUserPermissionPo;
import com.shun.upms.dao.po.UpmsUserPermissionPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: mew <p />
 * Time: 17/11/10 17:11  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class UpmsUserPermissionServiceMock extends BaseServiceMock<UpmsUserPermissionPoMapper, UpmsUserPermissionPo,
        UpmsUserPermissionPoExample> implements UpmsUserPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserPermissionServiceMock.class);

    @Override
    public int permission(JSONArray datas, int id) {
        _log.info("UpmsUserPermissionServiceMock => permission");
        return 0;
    }

}
