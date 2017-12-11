package com.shun.cms.core.service.impl;

import com.shun.cms.dao.mapper.CmsSettingPoMapper;
import com.shun.cms.dao.po.CmsSettingPo;
import com.shun.cms.dao.po.CmsSettingPoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.cms.api.CmsSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* User: mew <p />
* Time: 2017/11/27 09:59 <p />
* Version: V1.0  <p />
* Description: CmsSettingPoService实现 <p />
*/
@Service("cmsSetting")
@Transactional
@BaseService
public class CmsSettingServiceImpl extends BaseServiceImpl<CmsSettingPoMapper, CmsSettingPo, CmsSettingPoExample> implements CmsSettingService {

    private static Logger _log = LoggerFactory.getLogger(CmsSettingServiceImpl.class);

    @Autowired
    private CmsSettingPoMapper cmsSettingPoMapper;

}
