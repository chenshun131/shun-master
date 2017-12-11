package com.shun.cms.api;

import com.shun.cms.dao.mapper.CmsArticlePoMapper;
import com.shun.cms.dao.po.CmsArticlePo;
import com.shun.cms.dao.po.CmsArticlePoExample;
import com.shun.framework.base.BaseServiceMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User: mew <p />
 * Time: 2017/11/27 09:51 <p />
 * Version: V1.0  <p />
 * Description: 当无法从Dubbo 中获取服务室，就从改 Mock 中获取模拟数据，实现 CmsArticlePoService接口 <p />
 */
public class CmsArticleServiceMock extends BaseServiceMock<CmsArticlePoMapper, CmsArticlePo, CmsArticlePoExample>
        implements CmsArticleService {

    private static Logger _log = LoggerFactory.getLogger(CmsArticleServiceMock.class);

    @Override
    public List<CmsArticlePo> selectCmsArticlesByCategoryId(Integer categoryId, Integer offset, Integer limit) {
        _log.info("CmsArticleServiceMock => getCmsArticlesByCategoryId");
        return null;
    }

    @Override
    public long countByCategoryId(Integer categoryId) {
        _log.info("CmsArticleServiceMock => countByCategoryId");
        return 0;
    }

    @Override
    public List<CmsArticlePo> selectCmsArticlesByTagId(Integer tagId, Integer offset, Integer limit) {
        _log.info("CmsArticleServiceMock => getCmsArticlesByCategoryId");
        return null;
    }

    @Override
    public long countByTagId(Integer tagId) {
        _log.info("CmsArticleServiceMock => countByTagId");
        return 0;
    }

}
