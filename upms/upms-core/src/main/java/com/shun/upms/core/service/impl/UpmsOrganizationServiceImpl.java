package com.shun.upms.core.service.impl;

import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.upms.api.UpmsOrganizationService;
import com.shun.upms.dao.mapper.UpmsOrganizationPoMapper;
import com.shun.upms.dao.po.UpmsOrganizationPo;
import com.shun.upms.dao.po.UpmsOrganizationPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:11 <p />
 * Version: V1.0  <p />
 * Description: UpmsOrganizationPoService实现 <p />
 */
@Service("upmsOrganizationService")
@Transactional
@BaseService
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationPoMapper, UpmsOrganizationPo,
        UpmsOrganizationPoExample> implements UpmsOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpmsOrganizationServiceImpl.class);

    @Autowired
    UpmsOrganizationPoMapper upmsOrganizationPoMapper;

}
