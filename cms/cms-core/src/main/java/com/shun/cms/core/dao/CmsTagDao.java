package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsTagPoMapper;
import com.shun.cms.dao.po.CmsTagPo;
import com.shun.cms.dao.po.CmsTagPoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsTagDao")
public class CmsTagDao extends GenericDao<CmsTagPoExample, CmsTagPo, CmsTagPoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsTagPoMapper cmsTagPoMapper) {
        this.mapper = cmsTagPoMapper;
    }

}
