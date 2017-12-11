package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsCommentPoMapper;
import com.shun.cms.dao.po.CmsCommentPo;
import com.shun.cms.dao.po.CmsCommentPoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsCommentDao")
public class CmsCommentDao extends GenericDao<CmsCommentPoExample, CmsCommentPo, CmsCommentPoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsCommentPoMapper cmsCommentPoMapper) {
        this.mapper = cmsCommentPoMapper;
    }

}
