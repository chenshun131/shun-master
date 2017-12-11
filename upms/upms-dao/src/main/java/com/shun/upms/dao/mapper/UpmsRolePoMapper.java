package com.shun.upms.dao.mapper;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.upms.dao.po.UpmsRolePo;
import com.shun.upms.dao.po.UpmsRolePoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsRolePoMapper extends GenericMapper<UpmsRolePo, UpmsRolePoExample> {
    long countByExample(UpmsRolePoExample example);

    int deleteByExample(UpmsRolePoExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(UpmsRolePo record);

    int insertSelective(UpmsRolePo record);

    List<UpmsRolePo> selectByExample(UpmsRolePoExample example);

    UpmsRolePo selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") UpmsRolePo record, @Param("example") UpmsRolePoExample example);

    int updateByExample(@Param("record") UpmsRolePo record, @Param("example") UpmsRolePoExample example);

    int updateByPrimaryKeySelective(UpmsRolePo record);

    int updateByPrimaryKey(UpmsRolePo record);
}