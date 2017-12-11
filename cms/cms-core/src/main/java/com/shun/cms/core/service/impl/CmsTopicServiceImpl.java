package com.shun.cms.core.service.impl;

import com.shun.cms.dao.mapper.CmsTopicPoMapper;
import com.shun.cms.dao.po.CmsTopicPo;
import com.shun.cms.dao.po.CmsTopicPoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.cms.api.CmsTopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* User: mew <p />
* Time: 2017/11/27 09:59 <p />
* Version: V1.0  <p />
* Description: CmsTopicPoService实现 <p />
*/
@Service("cmsTopic")
@Transactional
@BaseService
public class CmsTopicServiceImpl extends BaseServiceImpl<CmsTopicPoMapper, CmsTopicPo, CmsTopicPoExample> implements CmsTopicService {

    private static Logger _log = LoggerFactory.getLogger(CmsTopicServiceImpl.class);

    @Autowired
    private CmsTopicPoMapper cmsTopicPoMapper;

}
