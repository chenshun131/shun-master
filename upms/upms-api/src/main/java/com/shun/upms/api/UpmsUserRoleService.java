package com.shun.upms.api;

import com.shun.framework.base.BaseService;
import com.shun.upms.dao.po.UpmsUserRolePo;
import com.shun.upms.dao.po.UpmsUserRolePoExample;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:09 <p />
 * Version: V1.0  <p />
 * Description: UpmsUserRolePoService接口 <p />
 */
public interface UpmsUserRoleService extends BaseService<UpmsUserRolePo, UpmsUserRolePoExample> {

    /**
     * 用户角色
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, int id);

}
