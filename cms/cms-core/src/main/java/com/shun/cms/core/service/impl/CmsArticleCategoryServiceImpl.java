package com.shun.cms.core.service.impl;

import com.shun.cms.dao.mapper.CmsArticleCategoryPoMapper;
import com.shun.cms.dao.po.CmsArticleCategoryPo;
import com.shun.cms.dao.po.CmsArticleCategoryPoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.cms.api.CmsArticleCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* User: mew <p />
* Time: 2017/11/27 09:59 <p />
* Version: V1.0  <p />
* Description: CmsArticleCategoryPoService实现 <p />
*/
@Service("cmsArticleCategory")
@Transactional
@BaseService
public class CmsArticleCategoryServiceImpl extends BaseServiceImpl<CmsArticleCategoryPoMapper, CmsArticleCategoryPo, CmsArticleCategoryPoExample> implements CmsArticleCategoryService {

    private static Logger _log = LoggerFactory.getLogger(CmsArticleCategoryServiceImpl.class);

    @Autowired
    private CmsArticleCategoryPoMapper cmsArticleCategoryPoMapper;

}
