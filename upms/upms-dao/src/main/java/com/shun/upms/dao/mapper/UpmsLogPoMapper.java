package com.shun.upms.dao.mapper;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.upms.dao.po.UpmsLogPo;
import com.shun.upms.dao.po.UpmsLogPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsLogPoMapper extends GenericMapper<UpmsLogPo, UpmsLogPoExample> {
    long countByExample(UpmsLogPoExample example);

    int deleteByExample(UpmsLogPoExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(UpmsLogPo record);

    int insertSelective(UpmsLogPo record);

    List<UpmsLogPo> selectByExampleWithBLOBs(UpmsLogPoExample example);

    List<UpmsLogPo> selectByExample(UpmsLogPoExample example);

    UpmsLogPo selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") UpmsLogPo record, @Param("example") UpmsLogPoExample example);

    int updateByExampleWithBLOBs(@Param("record") UpmsLogPo record, @Param("example") UpmsLogPoExample example);

    int updateByExample(@Param("record") UpmsLogPo record, @Param("example") UpmsLogPoExample example);

    int updateByPrimaryKeySelective(UpmsLogPo record);

    int updateByPrimaryKeyWithBLOBs(UpmsLogPo record);

    int updateByPrimaryKey(UpmsLogPo record);
}