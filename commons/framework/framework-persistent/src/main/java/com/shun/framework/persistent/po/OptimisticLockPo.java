package com.shun.framework.persistent.po;

/**
 * User: mew <p />
 * Time: 17/11/8 09:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface OptimisticLockPo<T> extends MutablePo<T> {

    void setOptimisticLock(Integer var1);

    Integer getOptimisticLock();

}
