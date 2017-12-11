package com.shun.upms.api;

import com.alibaba.fastjson.JSONArray;
import com.shun.framework.base.BaseService;
import com.shun.upms.dao.po.UpmsRolePermissionPo;
import com.shun.upms.dao.po.UpmsRolePermissionPoExample;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:09 <p />
 * Version: V1.0  <p />
 * Description: UpmsRolePermissionPoService接口 <p />
 */
public interface UpmsRolePermissionService extends BaseService<UpmsRolePermissionPo, UpmsRolePermissionPoExample> {

    /**
     * 角色权限
     *
     * @param datas
     *         权限数据
     * @param id
     *         角色id
     * @return
     */
    int rolePermission(JSONArray datas, int id);

}
