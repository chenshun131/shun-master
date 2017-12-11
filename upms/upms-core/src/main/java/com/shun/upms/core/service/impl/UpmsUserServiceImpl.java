package com.shun.upms.core.service.impl;

import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.upms.api.UpmsUserService;
import com.shun.upms.dao.mapper.UpmsUserPoMapper;
import com.shun.upms.dao.po.UpmsUserPo;
import com.shun.upms.dao.po.UpmsUserPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:11 <p />
 * Version: V1.0  <p />
 * Description: UpmsUserPoService实现 <p />
 */
@Service("upmsUserService")
@Transactional
@BaseService
public class UpmsUserServiceImpl extends BaseServiceImpl<UpmsUserPoMapper, UpmsUserPo, UpmsUserPoExample>
        implements UpmsUserService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserServiceImpl.class);

    @Autowired
    UpmsUserPoMapper upmsUserPoMapper;

    @Override
    public UpmsUserPo createUser(UpmsUserPo upmsUser) {
        UpmsUserPoExample upmsUserExample = new UpmsUserPoExample();
        upmsUserExample.createCriteria().andUsernameEqualTo(upmsUser.getUsername());
        long count = upmsUserPoMapper.countByExample(upmsUserExample);
        if (count > 0) {
            return null;
        }
        upmsUserPoMapper.insert(upmsUser);
        return upmsUser;
    }

}
