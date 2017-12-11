package com.shun.upms.dao.mapper;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.upms.dao.po.UpmsSystemPo;
import com.shun.upms.dao.po.UpmsSystemPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsSystemPoMapper extends GenericMapper<UpmsSystemPo, UpmsSystemPoExample> {
    long countByExample(UpmsSystemPoExample example);

    int deleteByExample(UpmsSystemPoExample example);

    int deleteByPrimaryKey(Integer systemId);

    int insert(UpmsSystemPo record);

    int insertSelective(UpmsSystemPo record);

    List<UpmsSystemPo> selectByExample(UpmsSystemPoExample example);

    UpmsSystemPo selectByPrimaryKey(Integer systemId);

    int updateByExampleSelective(@Param("record") UpmsSystemPo record, @Param("example") UpmsSystemPoExample example);

    int updateByExample(@Param("record") UpmsSystemPo record, @Param("example") UpmsSystemPoExample example);

    int updateByPrimaryKeySelective(UpmsSystemPo record);

    int updateByPrimaryKey(UpmsSystemPo record);
}