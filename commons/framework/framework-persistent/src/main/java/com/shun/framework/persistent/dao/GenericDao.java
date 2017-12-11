package com.shun.framework.persistent.dao;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.framework.persistent.po.GenericPo;
import com.shun.framework.persistent.po.MutablePo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/8 08:58  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class GenericDao<EXAMPLE, PO extends GenericPo, MAPPER extends GenericMapper<PO, EXAMPLE>> implements
        InitializingBean {

    protected MAPPER mapper;

    protected Class<EXAMPLE> exampleClass;

    protected Method createCriteria;

    private boolean enforceCreatedTimeWhenInsert = true;

    public GenericDao() {
    }

    public void afterPropertiesSet() throws Exception {
        if (this.exampleClass != null) {
            this.createCriteria = this.exampleClass.getDeclaredMethod("createCriteria");
        }

    }

    public int updateByPrimaryKey(PO po, boolean selective) {
        return this.updateByPrimaryKeyDelegately(po, selective);
    }

    protected final int updateByPrimaryKeyDelegately(PO po, boolean selective) {
        if (po instanceof MutablePo) {
            MutablePo mpo = (MutablePo) po;
            mpo.setLastUpdatedTime(new Date());
        }
        return selective ? this.getMapper().updateByPrimaryKeySelective(po) : this.getMapper().updateByPrimaryKey(po);
    }

    public int insert(PO po, boolean selective) {
        return this.insertDelegately(this.enforceCreatedTime(po), selective);
    }

    protected PO enforceCreatedTime(PO po) {
        if (this.enforceCreatedTimeWhenInsert && po.getCreatedTime() == null) {
            po.setCreatedTime(this.getCurrentDate());
        }
        return po;
    }

    protected final int insertDelegately(PO po, boolean selective) {
        Date now = new Date();
        po.setCreatedTime(now);
        if (po instanceof MutablePo) {
            MutablePo mpo = (MutablePo) po;
            mpo.setLastUpdatedTime(now);
        }
        return selective ? this.getMapper().insertSelective(po) : this.getMapper().insert(po);
    }

    public PO selectByUniqueProperty(String propertyName, Object propertyValue) {
        Validate.notEmpty(propertyName);
        Validate.notNull(propertyValue);
        return this.selectOneByCompositeConditions(Pair.of(propertyName, propertyValue));
    }

    public <GenericPo> PO selectOneByCompositeConditions(Pair... condition) {
        try {
            EXAMPLE example = this.exampleClass.newInstance();
            Object criteria = this.createCriteria.invoke(example);
            Pair[] arr = condition;
            Class<?> c2 = criteria.getClass();
            for (int i = 0, len = condition.length; i < len; ++i) {
                Pair<String, ?> pair = arr[i];
                if (pair.getValue() != null) {
                    Method declaredMethod = c2.getMethod(String.format("and%sEqualTo",
                            StringUtils.capitalize((String) pair.getKey())), pair.getValue().getClass());
                    declaredMethod.invoke(criteria, pair.getValue());
                }
            }
            List<PO> selectByExample = this.getMapper().selectByExample(example);
            if (selectByExample != null && selectByExample.size() == 1) {
                return selectByExample.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new ContextedRuntimeException(e);
        }
    }

    public List<PO> selectListByCompositeConditions(Pair... condition) {
        try {
            EXAMPLE example = this.exampleClass.newInstance();
            Object criteria = this.createCriteria.invoke(example);
            Class<?> c2 = criteria.getClass();
            Pair[] arr = condition;
            for (int i = 0, len = condition.length; i < len; ++i) {
                Pair<String, ?> pair = arr[i];
                if (pair.getValue() != null) {
                    Method declaredMethod = c2.getMethod(String.format("and%sEqualTo",
                            StringUtils.capitalize(pair.getKey())), pair.getValue().getClass());
                    declaredMethod.invoke(criteria, pair.getValue());
                }
            }
            return this.getMapper().selectByExample(example);
        } catch (Exception var10) {
            throw new ContextedRuntimeException(var10);
        }
    }

    public long countByCompositeConditions(Pair... condition) {
        try {
            EXAMPLE example = this.exampleClass.newInstance();
            Object criteria = this.createCriteria.invoke(example);
            Class<?> c2 = criteria.getClass();
            Pair[] arr = condition;
            for (int i = 0, len = condition.length; i < len; ++i) {
                Pair<String, ?> pair = arr[i];
                if (pair.getValue() != null) {
                    Method declaredMethod = c2.getMethod(String.format("and%sEqualTo",
                            StringUtils.capitalize(pair.getKey())), pair.getValue().getClass());
                    declaredMethod.invoke(criteria, pair.getValue());
                }
            }
            return this.getMapper().countByExample(example);
        } catch (Exception e) {
            throw new ContextedRuntimeException(e);
        }
    }

    protected Date getCurrentDate() {
        return new Date();
    }

    public void setExampleClass(Class<EXAMPLE> exampleClass) {
        this.exampleClass = exampleClass;
    }

    public MAPPER getMapper() {
        return this.mapper;
    }

    public void setMapper(MAPPER mapper) {
        this.mapper = mapper;
    }

    public boolean isEnforceCreatedTimeWhenInsert() {
        return this.enforceCreatedTimeWhenInsert;
    }

    public void setEnforceCreatedTimeWhenInsert(boolean enforceCreatedTimeWhenInsert) {
        this.enforceCreatedTimeWhenInsert = enforceCreatedTimeWhenInsert;
    }

}
