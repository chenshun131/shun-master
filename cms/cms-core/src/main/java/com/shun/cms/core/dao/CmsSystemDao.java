package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsSystemPoMapper;
import com.shun.cms.dao.po.CmsSystemPo;
import com.shun.cms.dao.po.CmsSystemPoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsSystemDao")
public class CmsSystemDao extends GenericDao<CmsSystemPoExample, CmsSystemPo, CmsSystemPoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsSystemPoMapper cmsSystemPoMapper) {
        this.mapper = cmsSystemPoMapper;
    }

}
