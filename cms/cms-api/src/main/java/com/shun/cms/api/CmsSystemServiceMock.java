package com.shun.cms.api;

import com.shun.cms.dao.mapper.CmsSystemPoMapper;
import com.shun.cms.dao.po.CmsSystemPo;
import com.shun.cms.dao.po.CmsSystemPoExample;
import com.shun.framework.base.BaseServiceMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* User: mew <p />
* Time: 2017/11/27 09:51 <p />
* Version: V1.0  <p />
* Description: 当无法从Dubbo 中获取服务室，就从改 Mock 中获取模拟数据，实现 CmsSystemPoService接口 <p />
*/
public class CmsSystemServiceMock extends BaseServiceMock<CmsSystemPoMapper, CmsSystemPo, CmsSystemPoExample> implements CmsSystemService {

    private static Logger _log = LoggerFactory.getLogger(CmsSystemServiceMock.class);

}
