package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsArticleCategoryPo;
import com.shun.cms.dao.po.CmsArticleCategoryPoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsArticleCategoryPoMapper extends GenericMapper<CmsArticleCategoryPo, CmsArticleCategoryPoExample> {
    long countByExample(CmsArticleCategoryPoExample example);

    int deleteByExample(CmsArticleCategoryPoExample example);

    int deleteByPrimaryKey(Integer articleCategoryId);

    int insert(CmsArticleCategoryPo record);

    int insertSelective(CmsArticleCategoryPo record);

    List<CmsArticleCategoryPo> selectByExample(CmsArticleCategoryPoExample example);

    CmsArticleCategoryPo selectByPrimaryKey(Integer articleCategoryId);

    int updateByExampleSelective(@Param("record") CmsArticleCategoryPo record, @Param("example") CmsArticleCategoryPoExample example);

    int updateByExample(@Param("record") CmsArticleCategoryPo record, @Param("example") CmsArticleCategoryPoExample example);

    int updateByPrimaryKeySelective(CmsArticleCategoryPo record);

    int updateByPrimaryKey(CmsArticleCategoryPo record);
}
