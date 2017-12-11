package com.shun.cms.core.service.impl;

import com.shun.cms.dao.mapper.CmsMenuPoMapper;
import com.shun.cms.dao.po.CmsMenuPo;
import com.shun.cms.dao.po.CmsMenuPoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.cms.api.CmsMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* User: mew <p />
* Time: 2017/11/27 09:59 <p />
* Version: V1.0  <p />
* Description: CmsMenuPoService实现 <p />
*/
@Service("cmsMenu")
@Transactional
@BaseService
public class CmsMenuServiceImpl extends BaseServiceImpl<CmsMenuPoMapper, CmsMenuPo, CmsMenuPoExample> implements CmsMenuService {

    private static Logger _log = LoggerFactory.getLogger(CmsMenuServiceImpl.class);

    @Autowired
    private CmsMenuPoMapper cmsMenuPoMapper;

}
