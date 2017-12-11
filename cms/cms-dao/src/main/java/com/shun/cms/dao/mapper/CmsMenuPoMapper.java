package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsMenuPo;
import com.shun.cms.dao.po.CmsMenuPoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsMenuPoMapper extends GenericMapper<CmsMenuPo, CmsMenuPoExample> {
    long countByExample(CmsMenuPoExample example);

    int deleteByExample(CmsMenuPoExample example);

    int deleteByPrimaryKey(Integer menuId);

    int insert(CmsMenuPo record);

    int insertSelective(CmsMenuPo record);

    List<CmsMenuPo> selectByExample(CmsMenuPoExample example);

    CmsMenuPo selectByPrimaryKey(Integer menuId);

    int updateByExampleSelective(@Param("record") CmsMenuPo record, @Param("example") CmsMenuPoExample example);

    int updateByExample(@Param("record") CmsMenuPo record, @Param("example") CmsMenuPoExample example);

    int updateByPrimaryKeySelective(CmsMenuPo record);

    int updateByPrimaryKey(CmsMenuPo record);
}
