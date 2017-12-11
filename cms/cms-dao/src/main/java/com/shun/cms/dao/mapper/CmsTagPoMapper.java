package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsTagPo;
import com.shun.cms.dao.po.CmsTagPoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsTagPoMapper extends GenericMapper<CmsTagPo, CmsTagPoExample> {
    long countByExample(CmsTagPoExample example);

    int deleteByExample(CmsTagPoExample example);

    int deleteByPrimaryKey(Integer tagId);

    int insert(CmsTagPo record);

    int insertSelective(CmsTagPo record);

    List<CmsTagPo> selectByExample(CmsTagPoExample example);

    CmsTagPo selectByPrimaryKey(Integer tagId);

    int updateByExampleSelective(@Param("record") CmsTagPo record, @Param("example") CmsTagPoExample example);

    int updateByExample(@Param("record") CmsTagPo record, @Param("example") CmsTagPoExample example);

    int updateByPrimaryKeySelective(CmsTagPo record);

    int updateByPrimaryKey(CmsTagPo record);
}
