package com.shun.upms.api;

import com.shun.framework.base.BaseService;
import com.shun.upms.dao.po.UpmsUserPo;
import com.shun.upms.dao.po.UpmsUserPoExample;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:09 <p />
 * Version: V1.0  <p />
 * Description: UpmsUserPoService接口 <p />
 */
public interface UpmsUserService extends BaseService<UpmsUserPo, UpmsUserPoExample> {

    UpmsUserPo createUser(UpmsUserPo upmsUser);

}
