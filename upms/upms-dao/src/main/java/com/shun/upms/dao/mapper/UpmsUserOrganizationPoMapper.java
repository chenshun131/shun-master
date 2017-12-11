package com.shun.upms.dao.mapper;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.upms.dao.po.UpmsUserOrganizationPo;
import com.shun.upms.dao.po.UpmsUserOrganizationPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsUserOrganizationPoMapper extends GenericMapper<UpmsUserOrganizationPo, UpmsUserOrganizationPoExample> {
    long countByExample(UpmsUserOrganizationPoExample example);

    int deleteByExample(UpmsUserOrganizationPoExample example);

    int deleteByPrimaryKey(Integer userOrganizationId);

    int insert(UpmsUserOrganizationPo record);

    int insertSelective(UpmsUserOrganizationPo record);

    List<UpmsUserOrganizationPo> selectByExample(UpmsUserOrganizationPoExample example);

    UpmsUserOrganizationPo selectByPrimaryKey(Integer userOrganizationId);

    int updateByExampleSelective(@Param("record") UpmsUserOrganizationPo record, @Param("example") UpmsUserOrganizationPoExample example);

    int updateByExample(@Param("record") UpmsUserOrganizationPo record, @Param("example") UpmsUserOrganizationPoExample example);

    int updateByPrimaryKeySelective(UpmsUserOrganizationPo record);

    int updateByPrimaryKey(UpmsUserOrganizationPo record);
}