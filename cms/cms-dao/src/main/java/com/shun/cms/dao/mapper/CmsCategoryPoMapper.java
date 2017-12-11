package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsCategoryPo;
import com.shun.cms.dao.po.CmsCategoryPoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsCategoryPoMapper extends GenericMapper<CmsCategoryPo, CmsCategoryPoExample> {
    long countByExample(CmsCategoryPoExample example);

    int deleteByExample(CmsCategoryPoExample example);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(CmsCategoryPo record);

    int insertSelective(CmsCategoryPo record);

    List<CmsCategoryPo> selectByExample(CmsCategoryPoExample example);

    CmsCategoryPo selectByPrimaryKey(Integer categoryId);

    int updateByExampleSelective(@Param("record") CmsCategoryPo record, @Param("example") CmsCategoryPoExample example);

    int updateByExample(@Param("record") CmsCategoryPo record, @Param("example") CmsCategoryPoExample example);

    int updateByPrimaryKeySelective(CmsCategoryPo record);

    int updateByPrimaryKey(CmsCategoryPo record);
}
