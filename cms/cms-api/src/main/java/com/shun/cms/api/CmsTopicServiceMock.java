package com.shun.cms.api;

import com.shun.cms.dao.mapper.CmsTopicPoMapper;
import com.shun.cms.dao.po.CmsTopicPo;
import com.shun.cms.dao.po.CmsTopicPoExample;
import com.shun.framework.base.BaseServiceMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* User: mew <p />
* Time: 2017/11/27 09:51 <p />
* Version: V1.0  <p />
* Description: 当无法从Dubbo 中获取服务室，就从改 Mock 中获取模拟数据，实现 CmsTopicPoService接口 <p />
*/
public class CmsTopicServiceMock extends BaseServiceMock<CmsTopicPoMapper, CmsTopicPo, CmsTopicPoExample> implements CmsTopicService {

    private static Logger _log = LoggerFactory.getLogger(CmsTopicServiceMock.class);

}
