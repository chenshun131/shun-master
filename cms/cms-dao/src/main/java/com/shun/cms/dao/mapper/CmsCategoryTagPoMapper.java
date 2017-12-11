package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsCategoryTagPo;
import com.shun.cms.dao.po.CmsCategoryTagPoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsCategoryTagPoMapper extends GenericMapper<CmsCategoryTagPo, CmsCategoryTagPoExample> {
    long countByExample(CmsCategoryTagPoExample example);

    int deleteByExample(CmsCategoryTagPoExample example);

    int deleteByPrimaryKey(Integer categoryTagId);

    int insert(CmsCategoryTagPo record);

    int insertSelective(CmsCategoryTagPo record);

    List<CmsCategoryTagPo> selectByExample(CmsCategoryTagPoExample example);

    CmsCategoryTagPo selectByPrimaryKey(Integer categoryTagId);

    int updateByExampleSelective(@Param("record") CmsCategoryTagPo record, @Param("example") CmsCategoryTagPoExample example);

    int updateByExample(@Param("record") CmsCategoryTagPo record, @Param("example") CmsCategoryTagPoExample example);

    int updateByPrimaryKeySelective(CmsCategoryTagPo record);

    int updateByPrimaryKey(CmsCategoryTagPo record);
}
