package com.shun.cms.core.service.impl;

import com.shun.cms.dao.mapper.CmsPagePoMapper;
import com.shun.cms.dao.po.CmsPagePo;
import com.shun.cms.dao.po.CmsPagePoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.cms.api.CmsPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* User: mew <p />
* Time: 2017/11/27 09:59 <p />
* Version: V1.0  <p />
* Description: CmsPagePoService实现 <p />
*/
@Service("cmsPage")
@Transactional
@BaseService
public class CmsPageServiceImpl extends BaseServiceImpl<CmsPagePoMapper, CmsPagePo, CmsPagePoExample> implements CmsPageService {

    private static Logger _log = LoggerFactory.getLogger(CmsPageServiceImpl.class);

    @Autowired
    private CmsPagePoMapper cmsPagePoMapper;

}
