package com.shun.cms.api;

import com.shun.cms.dao.mapper.CmsPagePoMapper;
import com.shun.cms.dao.po.CmsPagePo;
import com.shun.cms.dao.po.CmsPagePoExample;
import com.shun.framework.base.BaseServiceMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* User: mew <p />
* Time: 2017/11/27 09:51 <p />
* Version: V1.0  <p />
* Description: 当无法从Dubbo 中获取服务室，就从改 Mock 中获取模拟数据，实现 CmsPagePoService接口 <p />
*/
public class CmsPageServiceMock extends BaseServiceMock<CmsPagePoMapper, CmsPagePo, CmsPagePoExample> implements CmsPageService {

    private static Logger _log = LoggerFactory.getLogger(CmsPageServiceMock.class);

}
