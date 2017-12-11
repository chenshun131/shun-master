package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsPagePo;
import com.shun.cms.dao.po.CmsPagePoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsPagePoMapper extends GenericMapper<CmsPagePo, CmsPagePoExample> {
    long countByExample(CmsPagePoExample example);

    int deleteByExample(CmsPagePoExample example);

    int deleteByPrimaryKey(Integer pageId);

    int insert(CmsPagePo record);

    int insertSelective(CmsPagePo record);

    List<CmsPagePo> selectByExampleWithBLOBs(CmsPagePoExample example);

    List<CmsPagePo> selectByExample(CmsPagePoExample example);

    CmsPagePo selectByPrimaryKey(Integer pageId);

    int updateByExampleSelective(@Param("record") CmsPagePo record, @Param("example") CmsPagePoExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsPagePo record, @Param("example") CmsPagePoExample example);

    int updateByExample(@Param("record") CmsPagePo record, @Param("example") CmsPagePoExample example);

    int updateByPrimaryKeySelective(CmsPagePo record);

    int updateByPrimaryKeyWithBLOBs(CmsPagePo record);

    int updateByPrimaryKey(CmsPagePo record);
}
