package com.shun.upms.core.service.impl;

import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.upms.api.UpmsSystemService;
import com.shun.upms.dao.mapper.UpmsSystemPoMapper;
import com.shun.upms.dao.po.UpmsSystemPo;
import com.shun.upms.dao.po.UpmsSystemPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:11 <p />
 * Version: V1.0  <p />
 * Description: UpmsSystemPoService实现 <p />
 */
@Service("upmsSystemService")
@Transactional
@BaseService
public class UpmsSystemServiceImpl extends BaseServiceImpl<UpmsSystemPoMapper, UpmsSystemPo, UpmsSystemPoExample>
        implements UpmsSystemService {

    private static Logger _log = LoggerFactory.getLogger(UpmsSystemServiceImpl.class);

    @Autowired
    UpmsSystemPoMapper upmsSystemPoMapper;

}
