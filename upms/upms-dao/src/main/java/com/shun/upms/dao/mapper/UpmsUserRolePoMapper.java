package com.shun.upms.dao.mapper;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.upms.dao.po.UpmsUserRolePo;
import com.shun.upms.dao.po.UpmsUserRolePoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsUserRolePoMapper extends GenericMapper<UpmsUserRolePo, UpmsUserRolePoExample> {
    long countByExample(UpmsUserRolePoExample example);

    int deleteByExample(UpmsUserRolePoExample example);

    int deleteByPrimaryKey(Integer userRoleId);

    int insert(UpmsUserRolePo record);

    int insertSelective(UpmsUserRolePo record);

    List<UpmsUserRolePo> selectByExample(UpmsUserRolePoExample example);

    UpmsUserRolePo selectByPrimaryKey(Integer userRoleId);

    int updateByExampleSelective(@Param("record") UpmsUserRolePo record, @Param("example") UpmsUserRolePoExample example);

    int updateByExample(@Param("record") UpmsUserRolePo record, @Param("example") UpmsUserRolePoExample example);

    int updateByPrimaryKeySelective(UpmsUserRolePo record);

    int updateByPrimaryKey(UpmsUserRolePo record);
}