package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsCategoryTagPoMapper;
import com.shun.cms.dao.po.CmsCategoryTagPo;
import com.shun.cms.dao.po.CmsCategoryTagPoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsCategoryTagDao")
public class CmsCategoryTagDao extends GenericDao<CmsCategoryTagPoExample, CmsCategoryTagPo, CmsCategoryTagPoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsCategoryTagPoMapper cmsCategoryTagPoMapper) {
        this.mapper = cmsCategoryTagPoMapper;
    }

}
