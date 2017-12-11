package com.shun.cms.core.service.impl;

import com.shun.cms.dao.mapper.CmsSystemPoMapper;
import com.shun.cms.dao.po.CmsSystemPo;
import com.shun.cms.dao.po.CmsSystemPoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.cms.api.CmsSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* User: mew <p />
* Time: 2017/11/27 09:59 <p />
* Version: V1.0  <p />
* Description: CmsSystemPoService实现 <p />
*/
@Service("cmsSystem")
@Transactional
@BaseService
public class CmsSystemServiceImpl extends BaseServiceImpl<CmsSystemPoMapper, CmsSystemPo, CmsSystemPoExample> implements CmsSystemService {

    private static Logger _log = LoggerFactory.getLogger(CmsSystemServiceImpl.class);

    @Autowired
    private CmsSystemPoMapper cmsSystemPoMapper;

}
