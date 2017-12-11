package com.shun.upms.dao.mapper;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.upms.dao.po.UpmsOrganizationPo;
import com.shun.upms.dao.po.UpmsOrganizationPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsOrganizationPoMapper extends GenericMapper<UpmsOrganizationPo, UpmsOrganizationPoExample> {
    long countByExample(UpmsOrganizationPoExample example);

    int deleteByExample(UpmsOrganizationPoExample example);

    int deleteByPrimaryKey(Integer organizationId);

    int insert(UpmsOrganizationPo record);

    int insertSelective(UpmsOrganizationPo record);

    List<UpmsOrganizationPo> selectByExample(UpmsOrganizationPoExample example);

    UpmsOrganizationPo selectByPrimaryKey(Integer organizationId);

    int updateByExampleSelective(@Param("record") UpmsOrganizationPo record, @Param("example") UpmsOrganizationPoExample example);

    int updateByExample(@Param("record") UpmsOrganizationPo record, @Param("example") UpmsOrganizationPoExample example);

    int updateByPrimaryKeySelective(UpmsOrganizationPo record);

    int updateByPrimaryKey(UpmsOrganizationPo record);
}