package com.shun.upms.dao.mapper;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.upms.dao.po.UpmsPermissionPo;
import com.shun.upms.dao.po.UpmsPermissionPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsPermissionPoMapper extends GenericMapper<UpmsPermissionPo, UpmsPermissionPoExample> {
    long countByExample(UpmsPermissionPoExample example);

    int deleteByExample(UpmsPermissionPoExample example);

    int deleteByPrimaryKey(Integer permissionId);

    int insert(UpmsPermissionPo record);

    int insertSelective(UpmsPermissionPo record);

    List<UpmsPermissionPo> selectByExample(UpmsPermissionPoExample example);

    UpmsPermissionPo selectByPrimaryKey(Integer permissionId);

    int updateByExampleSelective(@Param("record") UpmsPermissionPo record, @Param("example") UpmsPermissionPoExample example);

    int updateByExample(@Param("record") UpmsPermissionPo record, @Param("example") UpmsPermissionPoExample example);

    int updateByPrimaryKeySelective(UpmsPermissionPo record);

    int updateByPrimaryKey(UpmsPermissionPo record);
}