package com.shun.cms.api;

import com.shun.cms.dao.mapper.CmsCommentPoMapper;
import com.shun.cms.dao.po.CmsCommentPo;
import com.shun.cms.dao.po.CmsCommentPoExample;
import com.shun.framework.base.BaseServiceMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* User: mew <p />
* Time: 2017/11/27 09:51 <p />
* Version: V1.0  <p />
* Description: 当无法从Dubbo 中获取服务室，就从改 Mock 中获取模拟数据，实现 CmsCommentPoService接口 <p />
*/
public class CmsCommentServiceMock extends BaseServiceMock<CmsCommentPoMapper, CmsCommentPo, CmsCommentPoExample> implements CmsCommentService {

    private static Logger _log = LoggerFactory.getLogger(CmsCommentServiceMock.class);

}
