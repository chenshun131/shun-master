package com.shun.cms.api;

import com.shun.cms.dao.mapper.CmsArticleTagPoMapper;
import com.shun.cms.dao.po.CmsArticleTagPo;
import com.shun.cms.dao.po.CmsArticleTagPoExample;
import com.shun.framework.base.BaseServiceMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* User: mew <p />
* Time: 2017/11/27 09:51 <p />
* Version: V1.0  <p />
* Description: 当无法从Dubbo 中获取服务室，就从改 Mock 中获取模拟数据，实现 CmsArticleTagPoService接口 <p />
*/
public class CmsArticleTagServiceMock extends BaseServiceMock<CmsArticleTagPoMapper, CmsArticleTagPo, CmsArticleTagPoExample> implements CmsArticleTagService {

    private static Logger _log = LoggerFactory.getLogger(CmsArticleTagServiceMock.class);

}
