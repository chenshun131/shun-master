package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsCommentPo;
import com.shun.cms.dao.po.CmsCommentPoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsCommentPoMapper extends GenericMapper<CmsCommentPo, CmsCommentPoExample> {
    long countByExample(CmsCommentPoExample example);

    int deleteByExample(CmsCommentPoExample example);

    int deleteByPrimaryKey(Integer commentId);

    int insert(CmsCommentPo record);

    int insertSelective(CmsCommentPo record);

    List<CmsCommentPo> selectByExampleWithBLOBs(CmsCommentPoExample example);

    List<CmsCommentPo> selectByExample(CmsCommentPoExample example);

    CmsCommentPo selectByPrimaryKey(Integer commentId);

    int updateByExampleSelective(@Param("record") CmsCommentPo record, @Param("example") CmsCommentPoExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsCommentPo record, @Param("example") CmsCommentPoExample example);

    int updateByExample(@Param("record") CmsCommentPo record, @Param("example") CmsCommentPoExample example);

    int updateByPrimaryKeySelective(CmsCommentPo record);

    int updateByPrimaryKeyWithBLOBs(CmsCommentPo record);

    int updateByPrimaryKey(CmsCommentPo record);
}
