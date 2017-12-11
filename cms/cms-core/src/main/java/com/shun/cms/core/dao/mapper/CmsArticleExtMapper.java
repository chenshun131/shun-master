package com.shun.cms.core.dao.mapper;

import com.shun.cms.dao.po.CmsArticlePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/27 10:04  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface CmsArticleExtMapper {

    int up(Integer articleId);

    int down(Integer articleId);

    List<CmsArticlePo> selectCmsArticlesByCategoryId(@Param("categoryId") Integer categoryId,
                                                     @Param("offset") Integer offset,
                                                     @Param("limit") Integer limit);

    long countByCategoryId(@Param("categoryId") Integer categoryId);

    List<CmsArticlePo> selectCmsArticlesByTagId(@Param("tagId") Integer tagId,
                                                @Param("offset") Integer offset,
                                                @Param("limit") Integer limit);

    long countByTagId(@Param("tagId") Integer tagId);

}
