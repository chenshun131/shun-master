package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsSystemPo;
import com.shun.cms.dao.po.CmsSystemPoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsSystemPoMapper extends GenericMapper<CmsSystemPo, CmsSystemPoExample> {
    long countByExample(CmsSystemPoExample example);

    int deleteByExample(CmsSystemPoExample example);

    int deleteByPrimaryKey(Integer systemId);

    int insert(CmsSystemPo record);

    int insertSelective(CmsSystemPo record);

    List<CmsSystemPo> selectByExample(CmsSystemPoExample example);

    CmsSystemPo selectByPrimaryKey(Integer systemId);

    int updateByExampleSelective(@Param("record") CmsSystemPo record, @Param("example") CmsSystemPoExample example);

    int updateByExample(@Param("record") CmsSystemPo record, @Param("example") CmsSystemPoExample example);

    int updateByPrimaryKeySelective(CmsSystemPo record);

    int updateByPrimaryKey(CmsSystemPo record);
}
