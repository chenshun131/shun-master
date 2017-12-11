package com.shun.upms.api;

import com.alibaba.fastjson.JSONArray;
import com.shun.framework.base.BaseService;
import com.shun.upms.dao.po.UpmsUserPermissionPo;
import com.shun.upms.dao.po.UpmsUserPermissionPoExample;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:09 <p />
 * Version: V1.0  <p />
 * Description: UpmsUserPermissionPoService接口 <p />
 */
public interface UpmsUserPermissionService extends BaseService<UpmsUserPermissionPo, UpmsUserPermissionPoExample> {

    /**
     * 用户权限
     *
     * @param datas
     *         权限数据
     * @param id
     *         用户id
     * @return
     */
    int permission(JSONArray datas, int id);

}
