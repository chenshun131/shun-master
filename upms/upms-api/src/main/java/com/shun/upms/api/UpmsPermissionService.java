package com.shun.upms.api;

import com.alibaba.fastjson.JSONArray;
import com.shun.framework.base.BaseService;
import com.shun.upms.dao.po.UpmsPermissionPo;
import com.shun.upms.dao.po.UpmsPermissionPoExample;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:09 <p />
 * Version: V1.0  <p />
 * Description: UpmsPermissionPoService接口 <p />
 */
public interface UpmsPermissionService extends BaseService<UpmsPermissionPo, UpmsPermissionPoExample> {

    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer usereId, Byte type);

}
