package com.shun.framework.persistent.mapper;

import com.shun.framework.persistent.po.GenericPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/8 09:00  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface GenericMapper<PO extends GenericPo, EXAMPLE> {

    int insert(PO po);

    long countByExample(EXAMPLE example);
//
//    int deleteByExample(EXAMPLE example);
//
//    int deleteByPrimaryKey(Long id);
//
    int insertSelective(PO po);

    List<PO> selectByExample(EXAMPLE example);
//
//    PO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PO po, @Param("example") EXAMPLE example);

//    int updateByExample(@Param("record") PO po, @Param("example") EXAMPLE example);
//
    int updateByPrimaryKeySelective(PO po);

    int updateByPrimaryKey(PO po);

}
