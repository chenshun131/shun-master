package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsPagePoMapper;
import com.shun.cms.dao.po.CmsPagePo;
import com.shun.cms.dao.po.CmsPagePoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsPageDao")
public class CmsPageDao extends GenericDao<CmsPagePoExample, CmsPagePo, CmsPagePoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsPagePoMapper cmsPagePoMapper) {
        this.mapper = cmsPagePoMapper;
    }

}
