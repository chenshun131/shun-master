package com.shun.cms.core.service.impl;

import com.shun.cms.dao.mapper.CmsCommentPoMapper;
import com.shun.cms.dao.po.CmsCommentPo;
import com.shun.cms.dao.po.CmsCommentPoExample;
import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.cms.api.CmsCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* User: mew <p />
* Time: 2017/11/27 09:59 <p />
* Version: V1.0  <p />
* Description: CmsCommentPoService实现 <p />
*/
@Service("cmsComment")
@Transactional
@BaseService
public class CmsCommentServiceImpl extends BaseServiceImpl<CmsCommentPoMapper, CmsCommentPo, CmsCommentPoExample> implements CmsCommentService {

    private static Logger _log = LoggerFactory.getLogger(CmsCommentServiceImpl.class);

    @Autowired
    private CmsCommentPoMapper cmsCommentPoMapper;

}
