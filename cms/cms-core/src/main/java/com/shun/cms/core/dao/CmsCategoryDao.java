package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsCategoryPoMapper;
import com.shun.cms.dao.po.CmsCategoryPo;
import com.shun.cms.dao.po.CmsCategoryPoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsCategoryDao")
public class CmsCategoryDao extends GenericDao<CmsCategoryPoExample, CmsCategoryPo, CmsCategoryPoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsCategoryPoMapper cmsCategoryPoMapper) {
        this.mapper = cmsCategoryPoMapper;
    }

}
