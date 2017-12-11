package com.shun.upms.core.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.upms.api.UpmsUserPermissionService;
import com.shun.upms.dao.mapper.UpmsUserPermissionPoMapper;
import com.shun.upms.dao.po.UpmsUserPermissionPo;
import com.shun.upms.dao.po.UpmsUserPermissionPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:11 <p />
 * Version: V1.0  <p />
 * Description: UpmsUserPermissionPoService实现 <p />
 */
@Service("upmsUserPermissionService")
@Transactional
@BaseService
public class UpmsUserPermissionServiceImpl extends BaseServiceImpl<UpmsUserPermissionPoMapper, UpmsUserPermissionPo,
        UpmsUserPermissionPoExample> implements UpmsUserPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserPermissionServiceImpl.class);

    @Autowired
    UpmsUserPermissionPoMapper upmsUserPermissionPoMapper;

    @Override
    public int permission(JSONArray datas, int id) {
        for (int i = 0; i < datas.size(); i++) {
            JSONObject json = datas.getJSONObject(i);
            if (json.getBoolean("checked")) {
                // 新增权限
                UpmsUserPermissionPo upmsUserPermission = new UpmsUserPermissionPo();
                upmsUserPermission.setUserId(id);
                upmsUserPermission.setPermissionId(json.getIntValue("id"));
                upmsUserPermission.setType(json.getByte("type"));
                upmsUserPermissionPoMapper.insertSelective(upmsUserPermission);
            } else {
                // 删除权限
                UpmsUserPermissionPoExample upmsUserPermissionExample = new UpmsUserPermissionPoExample();
                upmsUserPermissionExample.createCriteria()
                        .andPermissionIdEqualTo(json.getIntValue("id"))
                        .andTypeEqualTo(json.getByte("type"));
                upmsUserPermissionPoMapper.deleteByExample(upmsUserPermissionExample);
            }
        }
        return datas.size();
    }

}
