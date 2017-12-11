package com.shun.upms.core.service.impl;

import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.upms.api.UpmsLogService;
import com.shun.upms.dao.mapper.UpmsLogPoMapper;
import com.shun.upms.dao.po.UpmsLogPo;
import com.shun.upms.dao.po.UpmsLogPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:11 <p />
 * Version: V1.0  <p />
 * Description: UpmsLogPoService实现 <p />
 */
@Service("upmsLogService")
@Transactional
@BaseService
public class UpmsLogServiceImpl extends BaseServiceImpl<UpmsLogPoMapper, UpmsLogPo, UpmsLogPoExample> implements
        UpmsLogService {

    private static Logger _log = LoggerFactory.getLogger(UpmsLogServiceImpl.class);

    @Autowired
    UpmsLogPoMapper upmsLogPoMapper;

}
