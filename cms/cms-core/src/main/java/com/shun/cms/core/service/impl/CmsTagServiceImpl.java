package com.shun.cms.core.service.impl;

import com.shun.cms.dao.mapper.CmsTagPoMapper;
import com.shun.cms.dao.po.CmsTagPo;
import com.shun.cms.dao.po.CmsTagPoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.cms.api.CmsTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* User: mew <p />
* Time: 2017/11/27 09:59 <p />
* Version: V1.0  <p />
* Description: CmsTagPoService实现 <p />
*/
@Service("cmsTag")
@Transactional
@BaseService
public class CmsTagServiceImpl extends BaseServiceImpl<CmsTagPoMapper, CmsTagPo, CmsTagPoExample> implements CmsTagService {

    private static Logger _log = LoggerFactory.getLogger(CmsTagServiceImpl.class);

    @Autowired
    private CmsTagPoMapper cmsTagPoMapper;

}
