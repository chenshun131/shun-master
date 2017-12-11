package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsArticlePo;
import com.shun.cms.dao.po.CmsArticlePoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsArticlePoMapper extends GenericMapper<CmsArticlePo, CmsArticlePoExample> {
    long countByExample(CmsArticlePoExample example);

    int deleteByExample(CmsArticlePoExample example);

    int deleteByPrimaryKey(Integer articleId);

    int insert(CmsArticlePo record);

    int insertSelective(CmsArticlePo record);

    List<CmsArticlePo> selectByExampleWithBLOBs(CmsArticlePoExample example);

    List<CmsArticlePo> selectByExample(CmsArticlePoExample example);

    CmsArticlePo selectByPrimaryKey(Integer articleId);

    int updateByExampleSelective(@Param("record") CmsArticlePo record, @Param("example") CmsArticlePoExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsArticlePo record, @Param("example") CmsArticlePoExample example);

    int updateByExample(@Param("record") CmsArticlePo record, @Param("example") CmsArticlePoExample example);

    int updateByPrimaryKeySelective(CmsArticlePo record);

    int updateByPrimaryKeyWithBLOBs(CmsArticlePo record);

    int updateByPrimaryKey(CmsArticlePo record);
}
