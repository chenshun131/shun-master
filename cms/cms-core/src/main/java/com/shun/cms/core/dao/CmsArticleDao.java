package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsArticlePoMapper;
import com.shun.cms.dao.po.CmsArticlePo;
import com.shun.cms.dao.po.CmsArticlePoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsArticleDao")
public class CmsArticleDao extends GenericDao<CmsArticlePoExample, CmsArticlePo, CmsArticlePoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsArticlePoMapper cmsArticlePoMapper) {
        this.mapper = cmsArticlePoMapper;
    }

}
