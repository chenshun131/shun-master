package com.shun.cms.core.service.impl;

import com.shun.cms.api.CmsArticleService;
import com.shun.cms.core.dao.mapper.CmsArticleExtMapper;
import com.shun.cms.dao.mapper.CmsArticlePoMapper;
import com.shun.cms.dao.po.CmsArticlePo;
import com.shun.cms.dao.po.CmsArticlePoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: mew <p />
 * Time: 2017/11/27 09:59 <p />
 * Version: V1.0  <p />
 * Description: CmsArticlePoService实现 <p />
 */
@Service("cmsArticle")
@Transactional
@BaseService
public class CmsArticleServiceImpl extends BaseServiceImpl<CmsArticlePoMapper, CmsArticlePo, CmsArticlePoExample>
        implements CmsArticleService {

    private static Logger _log = LoggerFactory.getLogger(CmsArticleServiceImpl.class);

    @Autowired
    private CmsArticlePoMapper cmsArticlePoMapper;

    @Autowired
    private CmsArticleExtMapper cmsArticleExtMapper;

    @Override
    public List<CmsArticlePo> selectCmsArticlesByCategoryId(Integer categoryId, Integer offset, Integer limit) {
        return cmsArticleExtMapper.selectCmsArticlesByCategoryId(categoryId, offset, limit);
    }

    @Override
    public long countByCategoryId(Integer categoryId) {
        return cmsArticleExtMapper.countByCategoryId(categoryId);
    }

    @Override
    public List<CmsArticlePo> selectCmsArticlesByTagId(Integer tagId, Integer offset, Integer limit) {
        return cmsArticleExtMapper.selectCmsArticlesByTagId(tagId, offset, limit);
    }

    @Override
    public long countByTagId(Integer tagId) {
        return cmsArticleExtMapper.countByTagId(tagId);
    }

}
