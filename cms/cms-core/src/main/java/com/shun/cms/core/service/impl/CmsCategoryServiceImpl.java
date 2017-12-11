package com.shun.cms.core.service.impl;

import com.shun.cms.dao.mapper.CmsCategoryPoMapper;
import com.shun.cms.dao.po.CmsCategoryPo;
import com.shun.cms.dao.po.CmsCategoryPoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.cms.api.CmsCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* User: mew <p />
* Time: 2017/11/27 09:59 <p />
* Version: V1.0  <p />
* Description: CmsCategoryPoService实现 <p />
*/
@Service("cmsCategory")
@Transactional
@BaseService
public class CmsCategoryServiceImpl extends BaseServiceImpl<CmsCategoryPoMapper, CmsCategoryPo, CmsCategoryPoExample> implements CmsCategoryService {

    private static Logger _log = LoggerFactory.getLogger(CmsCategoryServiceImpl.class);

    @Autowired
    private CmsCategoryPoMapper cmsCategoryPoMapper;

}
