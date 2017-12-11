package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsMenuPoMapper;
import com.shun.cms.dao.po.CmsMenuPo;
import com.shun.cms.dao.po.CmsMenuPoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsMenuDao")
public class CmsMenuDao extends GenericDao<CmsMenuPoExample, CmsMenuPo, CmsMenuPoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsMenuPoMapper cmsMenuPoMapper) {
        this.mapper = cmsMenuPoMapper;
    }

}
