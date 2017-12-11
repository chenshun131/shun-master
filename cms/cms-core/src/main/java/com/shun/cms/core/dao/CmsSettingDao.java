package com.shun.cms.core.dao;

import com.shun.cms.dao.mapper.CmsSettingPoMapper;
import com.shun.cms.dao.po.CmsSettingPo;
import com.shun.cms.dao.po.CmsSettingPoExample;
import com.shun.framework.persistent.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* User: mew <p />
* Time: 2017/11/27 09:47 <p />
* Version: V1.0  <p />
* Description:  <p />
*/
@Repository("cmsSettingDao")
public class CmsSettingDao extends GenericDao<CmsSettingPoExample, CmsSettingPo, CmsSettingPoMapper> {

    @Override
    @Autowired
    public void setMapper(CmsSettingPoMapper cmsSettingPoMapper) {
        this.mapper = cmsSettingPoMapper;
    }

}
