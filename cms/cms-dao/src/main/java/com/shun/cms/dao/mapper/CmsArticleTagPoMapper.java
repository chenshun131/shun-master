package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsArticleTagPo;
import com.shun.cms.dao.po.CmsArticleTagPoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsArticleTagPoMapper extends GenericMapper<CmsArticleTagPo, CmsArticleTagPoExample> {
    long countByExample(CmsArticleTagPoExample example);

    int deleteByExample(CmsArticleTagPoExample example);

    int deleteByPrimaryKey(Integer articleTagId);

    int insert(CmsArticleTagPo record);

    int insertSelective(CmsArticleTagPo record);

    List<CmsArticleTagPo> selectByExample(CmsArticleTagPoExample example);

    CmsArticleTagPo selectByPrimaryKey(Integer articleTagId);

    int updateByExampleSelective(@Param("record") CmsArticleTagPo record, @Param("example") CmsArticleTagPoExample example);

    int updateByExample(@Param("record") CmsArticleTagPo record, @Param("example") CmsArticleTagPoExample example);

    int updateByPrimaryKeySelective(CmsArticleTagPo record);

    int updateByPrimaryKey(CmsArticleTagPo record);
}
