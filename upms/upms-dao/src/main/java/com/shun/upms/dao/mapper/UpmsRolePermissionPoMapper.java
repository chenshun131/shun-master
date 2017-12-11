package com.shun.upms.dao.mapper;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.upms.dao.po.UpmsRolePermissionPo;
import com.shun.upms.dao.po.UpmsRolePermissionPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsRolePermissionPoMapper extends GenericMapper<UpmsRolePermissionPo, UpmsRolePermissionPoExample> {
    long countByExample(UpmsRolePermissionPoExample example);

    int deleteByExample(UpmsRolePermissionPoExample example);

    int deleteByPrimaryKey(Integer rolePermissionId);

    int insert(UpmsRolePermissionPo record);

    int insertSelective(UpmsRolePermissionPo record);

    List<UpmsRolePermissionPo> selectByExample(UpmsRolePermissionPoExample example);

    UpmsRolePermissionPo selectByPrimaryKey(Integer rolePermissionId);

    int updateByExampleSelective(@Param("record") UpmsRolePermissionPo record, @Param("example") UpmsRolePermissionPoExample example);

    int updateByExample(@Param("record") UpmsRolePermissionPo record, @Param("example") UpmsRolePermissionPoExample example);

    int updateByPrimaryKeySelective(UpmsRolePermissionPo record);

    int updateByPrimaryKey(UpmsRolePermissionPo record);
}