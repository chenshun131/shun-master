package com.shun.cms.dao.mapper;

import com.shun.cms.dao.po.CmsTopicPo;
import com.shun.cms.dao.po.CmsTopicPoExample;
import com.shun.framework.persistent.mapper.GenericMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsTopicPoMapper extends GenericMapper<CmsTopicPo, CmsTopicPoExample> {
    long countByExample(CmsTopicPoExample example);

    int deleteByExample(CmsTopicPoExample example);

    int deleteByPrimaryKey(Integer topicId);

    int insert(CmsTopicPo record);

    int insertSelective(CmsTopicPo record);

    List<CmsTopicPo> selectByExample(CmsTopicPoExample example);

    CmsTopicPo selectByPrimaryKey(Integer topicId);

    int updateByExampleSelective(@Param("record") CmsTopicPo record, @Param("example") CmsTopicPoExample example);

    int updateByExample(@Param("record") CmsTopicPo record, @Param("example") CmsTopicPoExample example);

    int updateByPrimaryKeySelective(CmsTopicPo record);

    int updateByPrimaryKey(CmsTopicPo record);
}
