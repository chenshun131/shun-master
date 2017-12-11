package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsArticleCategoryPoMapper;
import com.shun.cms.dao.po.CmsArticleCategoryPo;
import com.shun.cms.dao.po.CmsArticleCategoryPoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsArticleCategoryDao")
public class CmsArticleCategoryDao extends GenericDao<CmsArticleCategoryPoExample, CmsArticleCategoryPo, CmsArticleCategoryPoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsArticleCategoryPoMapper cmsArticleCategoryPoMapper) {
        this.mapper = cmsArticleCategoryPoMapper;
    }

}
