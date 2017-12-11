package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsSettingPo;
import com.shun.cms.dao.po.CmsSettingPoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsSettingPoMapper extends GenericMapper<CmsSettingPo, CmsSettingPoExample> {
    long countByExample(CmsSettingPoExample example);

    int deleteByExample(CmsSettingPoExample example);

    int deleteByPrimaryKey(Integer settingId);

    int insert(CmsSettingPo record);

    int insertSelective(CmsSettingPo record);

    List<CmsSettingPo> selectByExample(CmsSettingPoExample example);

    CmsSettingPo selectByPrimaryKey(Integer settingId);

    int updateByExampleSelective(@Param("record") CmsSettingPo record, @Param("example") CmsSettingPoExample example);

    int updateByExample(@Param("record") CmsSettingPo record, @Param("example") CmsSettingPoExample example);

    int updateByPrimaryKeySelective(CmsSettingPo record);

    int updateByPrimaryKey(CmsSettingPo record);
}
