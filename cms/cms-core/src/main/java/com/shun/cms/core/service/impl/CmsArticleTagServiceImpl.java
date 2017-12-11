package com.shun.cms.core.service.impl;

import com.shun.cms.dao.mapper.CmsArticleTagPoMapper;
import com.shun.cms.dao.po.CmsArticleTagPo;
import com.shun.cms.dao.po.CmsArticleTagPoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.cms.api.CmsArticleTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* User: mew <p />
* Time: 2017/11/27 09:59 <p />
* Version: V1.0  <p />
* Description: CmsArticleTagPoService实现 <p />
*/
@Service("cmsArticleTag")
@Transactional
@BaseService
public class CmsArticleTagServiceImpl extends BaseServiceImpl<CmsArticleTagPoMapper, CmsArticleTagPo, CmsArticleTagPoExample> implements CmsArticleTagService {

    private static Logger _log = LoggerFactory.getLogger(CmsArticleTagServiceImpl.class);

    @Autowired
    private CmsArticleTagPoMapper cmsArticleTagPoMapper;

}
