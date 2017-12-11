package com.shun.upms.api;

import com.shun.framework.base.BaseService;
import com.shun.upms.dao.po.UpmsUserOrganizationPo;
import com.shun.upms.dao.po.UpmsUserOrganizationPoExample;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:09 <p />
 * Version: V1.0  <p />
 * Description: UpmsUserOrganizationPoService接口 <p />
 */
public interface UpmsUserOrganizationService extends BaseService<UpmsUserOrganizationPo,
        UpmsUserOrganizationPoExample> {

    /**
     * 用户组织
     *
     * @param organizationIds
     *         组织ids
     * @param id
     *         用户id
     * @return
     */
    int organization(String[] organizationIds, int id);

}
