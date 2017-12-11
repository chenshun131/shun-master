package com.shun.cms.core.service.impl;

import com.shun.cms.dao.mapper.CmsCategoryTagPoMapper;
import com.shun.cms.dao.po.CmsCategoryTagPo;
import com.shun.cms.dao.po.CmsCategoryTagPoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.cms.api.CmsCategoryTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* User: mew <p />
* Time: 2017/11/27 09:59 <p />
* Version: V1.0  <p />
* Description: CmsCategoryTagPoService实现 <p />
*/
@Service("cmsCategoryTag")
@Transactional
@BaseService
public class CmsCategoryTagServiceImpl extends BaseServiceImpl<CmsCategoryTagPoMapper, CmsCategoryTagPo, CmsCategoryTagPoExample> implements CmsCategoryTagService {

    private static Logger _log = LoggerFactory.getLogger(CmsCategoryTagServiceImpl.class);

    @Autowired
    private CmsCategoryTagPoMapper cmsCategoryTagPoMapper;

}
