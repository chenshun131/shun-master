package com.shun.framework.persistent.dao;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.framework.persistent.po.OptimisticLockPo;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;

/**
 * User: mew <p />
 * Time: 17/11/8 09:00  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class OptimisticLockDao<KT, EXAMPLE, PO extends OptimisticLockPo<KT>, MAPPER extends GenericMapper<PO, EXAMPLE>>
        extends MutableDao<EXAMPLE, PO, MAPPER> {

    private static final Logger logger = LoggerFactory.getLogger(OptimisticLockDao.class);

    public static final int INITIAL_OPTIMISTIC_LOCK_VALUE = 1;

    private boolean enforceOptimisticLock = true;

    public OptimisticLockDao() {
    }

    public int updateByPrimaryKey(PO po, boolean selective) {
        Validate.notNull(po, "po is null", new Object[0]);
        Integer optimisticLock = po.getOptimisticLock();
        try {
            this.enforceLastUpdatedTime(po, selective);
            EXAMPLE example = this.exampleClass.newInstance();
            Object criteria = this.createCriteria.invoke(example);
            Class<?> c2 = criteria.getClass();
            if (po.getId().getClass() == Long.class) {
                c2.getMethod("andIdEqualTo", Long.class).invoke(criteria, po.getId());
            } else {
                c2.getMethod("andIdEqualTo", String.class).invoke(criteria, po.getId());
            }

            if (this.enforceOptimisticLock) {
                c2.getMethod("andOptimisticLockEqualTo", Integer.class).invoke(criteria, optimisticLock);
                if (po.getOptimisticLock() == null || po.getOptimisticLock().intValue() <= 0) {
                    throw new OptimisticLockingFailureException(
                            String.format("class:%s,id:%d,optimisticLock can not be:%d", po.getClass().getName(),
                                    po.getId(), optimisticLock));
                }

                po.setOptimisticLock(po.getOptimisticLock().intValue() + 1);
            }

            int updateByExampleSelective = this.getMapper().updateByExampleSelective(po, example);
            logger.debug("updateByExampleSelective:{}", updateByExampleSelective);
            if (updateByExampleSelective == 0) {
                throw new OptimisticLockingFailureException(String.format("class:%s,id:%d,expected optimisticLock:%d",
                        po.getClass().getName(), po.getId(), optimisticLock));
            } else {
                return updateByExampleSelective;
            }
        } catch (OptimisticLockingFailureException var8) {
            throw var8;
        } catch (Exception var9) {
            throw new ContextedRuntimeException(var9);
        }
    }

    public int insert(PO po, boolean selective) {
        if (this.enforceOptimisticLock) {
            po.setOptimisticLock(Integer.valueOf(1));
        }

        return super.insert(po, selective);
    }

    public boolean isEnforceOptimisticLock() {
        return this.enforceOptimisticLock;
    }

    public void setEnforceOptimisticLock(boolean enforceOptimisticLock) {
        this.enforceOptimisticLock = enforceOptimisticLock;
    }

}
