package com.shun.cms.api;

import com.shun.cms.dao.mapper.CmsArticleCategoryPoMapper;
import com.shun.cms.dao.po.CmsArticleCategoryPo;
import com.shun.cms.dao.po.CmsArticleCategoryPoExample;
import com.shun.framework.base.BaseServiceMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* User: mew <p />
* Time: 2017/11/27 09:51 <p />
* Version: V1.0  <p />
* Description: 当无法从Dubbo 中获取服务室，就从改 Mock 中获取模拟数据，实现 CmsArticleCategoryPoService接口 <p />
*/
public class CmsArticleCategoryServiceMock extends BaseServiceMock<CmsArticleCategoryPoMapper, CmsArticleCategoryPo, CmsArticleCategoryPoExample> implements CmsArticleCategoryService {

    private static Logger _log = LoggerFactory.getLogger(CmsArticleCategoryServiceMock.class);

}
