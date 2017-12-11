package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsArticleTagPoMapper;
import com.shun.cms.dao.po.CmsArticleTagPo;
import com.shun.cms.dao.po.CmsArticleTagPoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsArticleTagDao")
public class CmsArticleTagDao extends GenericDao<CmsArticleTagPoExample, CmsArticleTagPo, CmsArticleTagPoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsArticleTagPoMapper cmsArticleTagPoMapper) {
        this.mapper = cmsArticleTagPoMapper;
    }

}
