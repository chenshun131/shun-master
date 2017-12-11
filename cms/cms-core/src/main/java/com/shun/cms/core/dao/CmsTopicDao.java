package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsTopicPoMapper;
import com.shun.cms.dao.po.CmsTopicPo;
import com.shun.cms.dao.po.CmsTopicPoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsTopicDao")
public class CmsTopicDao extends GenericDao<CmsTopicPoExample, CmsTopicPo, CmsTopicPoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsTopicPoMapper cmsTopicPoMapper) {
        this.mapper = cmsTopicPoMapper;
    }

}
