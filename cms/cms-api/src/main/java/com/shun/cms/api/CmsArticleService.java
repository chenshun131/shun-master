package com.shun.cms.api;

import com.shun.cms.dao.po.CmsArticlePo;
import com.shun.cms.dao.po.CmsArticlePoExample;
import com.shun.framework.base.BaseService;

import java.util.List;

/**
 * User: mew <p />
 * Time: 2017/11/27 09:51 <p />
 * Version: V1.0  <p />
 * Description: CmsArticlePoService接口 <p />
 */
public interface CmsArticleService extends BaseService<CmsArticlePo, CmsArticlePoExample> {

    /** 根据类目获取文章列表 */
    List<CmsArticlePo> selectCmsArticlesByCategoryId(Integer categoryId, Integer offset, Integer limit);

    /** 根据类目获取文章数量 */
    long countByCategoryId(Integer categoryId);

    /** 根据标签获取文章列表 */
    List<CmsArticlePo> selectCmsArticlesByTagId(Integer tagId, Integer offset, Integer limit);

    /** 根据标签获取文章数量 */
    long countByTagId(Integer tagId);

}
