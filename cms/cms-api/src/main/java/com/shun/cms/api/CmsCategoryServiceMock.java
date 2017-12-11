package com.shun.cms.api;

import com.shun.cms.dao.mapper.CmsCategoryPoMapper;
import com.shun.cms.dao.po.CmsCategoryPo;
import com.shun.cms.dao.po.CmsCategoryPoExample;
import com.shun.framework.base.BaseServiceMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* User: mew <p />
* Time: 2017/11/27 09:51 <p />
* Version: V1.0  <p />
* Description: 当无法从Dubbo 中获取服务室，就从改 Mock 中获取模拟数据，实现 CmsCategoryPoService接口 <p />
*/
public class CmsCategoryServiceMock extends BaseServiceMock<CmsCategoryPoMapper, CmsCategoryPo, CmsCategoryPoExample> implements CmsCategoryService {

    private static Logger _log = LoggerFactory.getLogger(CmsCategoryServiceMock.class);

}
