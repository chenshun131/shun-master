package com.shun.upms.dao.mapper;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.upms.dao.po.UpmsUserPo;
import com.shun.upms.dao.po.UpmsUserPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsUserPoMapper extends GenericMapper<UpmsUserPo, UpmsUserPoExample> {
    long countByExample(UpmsUserPoExample example);

    int deleteByExample(UpmsUserPoExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UpmsUserPo record);

    int insertSelective(UpmsUserPo record);

    List<UpmsUserPo> selectByExample(UpmsUserPoExample example);

    UpmsUserPo selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UpmsUserPo record, @Param("example") UpmsUserPoExample example);

    int updateByExample(@Param("record") UpmsUserPo record, @Param("example") UpmsUserPoExample example);

    int updateByPrimaryKeySelective(UpmsUserPo record);

    int updateByPrimaryKey(UpmsUserPo record);
}