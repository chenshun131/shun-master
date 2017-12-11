package com.shun.upms.api;

import com.shun.framework.base.BaseServiceMock;
import com.shun.upms.dao.mapper.UpmsUserPoMapper;
import com.shun.upms.dao.po.UpmsUserPo;
import com.shun.upms.dao.po.UpmsUserPoExample;

/**
 * User: mew <p />
 * Time: 17/11/10 17:12  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class UpmsUserServiceMock extends BaseServiceMock<UpmsUserPoMapper, UpmsUserPo, UpmsUserPoExample>
        implements UpmsUserService {

    @Override
    public UpmsUserPo createUser(UpmsUserPo upmsUser) {
        return null;
    }

}
