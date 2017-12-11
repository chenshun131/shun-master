package com.shun.upms.dao.mapper;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.upms.dao.po.UpmsUserPermissionPo;
import com.shun.upms.dao.po.UpmsUserPermissionPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsUserPermissionPoMapper extends GenericMapper<UpmsUserPermissionPo, UpmsUserPermissionPoExample>{
    long countByExample(UpmsUserPermissionPoExample example);

    int deleteByExample(UpmsUserPermissionPoExample example);

    int deleteByPrimaryKey(Integer userPermissionId);

    int insert(UpmsUserPermissionPo record);

    int insertSelective(UpmsUserPermissionPo record);

    List<UpmsUserPermissionPo> selectByExample(UpmsUserPermissionPoExample example);

    UpmsUserPermissionPo selectByPrimaryKey(Integer userPermissionId);

    int updateByExampleSelective(@Param("record") UpmsUserPermissionPo record, @Param("example") UpmsUserPermissionPoExample example);

    int updateByExample(@Param("record") UpmsUserPermissionPo record, @Param("example") UpmsUserPermissionPoExample example);

    int updateByPrimaryKeySelective(UpmsUserPermissionPo record);

    int updateByPrimaryKey(UpmsUserPermissionPo record);
}