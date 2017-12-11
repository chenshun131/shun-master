package com.shun.upms.core.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.upms.api.UpmsRolePermissionService;
import com.shun.upms.dao.mapper.UpmsRolePermissionPoMapper;
import com.shun.upms.dao.po.UpmsRolePermissionPo;
import com.shun.upms.dao.po.UpmsRolePermissionPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:11 <p />
 * Version: V1.0  <p />
 * Description: UpmsRolePermissionPoService实现 <p />
 */
@Service("upmsRolePermissionService")
@Transactional
@BaseService
public class UpmsRolePermissionServiceImpl extends BaseServiceImpl<UpmsRolePermissionPoMapper, UpmsRolePermissionPo,
        UpmsRolePermissionPoExample> implements UpmsRolePermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsRolePermissionServiceImpl.class);

    @Autowired
    UpmsRolePermissionPoMapper upmsRolePermissionPoMapper;

    @Override
    public int rolePermission(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增权限
                UpmsRolePermissionPo upmsRolePermission = new UpmsRolePermissionPo();
                upmsRolePermission.setRoleId(id);
                upmsRolePermission.setPermissionId(json.getIntValue("id"));
                upmsRolePermissionPoMapper.insertSelective(upmsRolePermission);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UpmsRolePermissionPoExample upmsRolePermissionExample = new UpmsRolePermissionPoExample();
            upmsRolePermissionExample.createCriteria().andPermissionIdIn(deleteIds).andRoleIdEqualTo(id);
            upmsRolePermissionPoMapper.deleteByExample(upmsRolePermissionExample);
        }
        return datas.size();
    }

}
