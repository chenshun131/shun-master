package com.shun.framework.persistent.po;

import java.util.Date;

/**
 * User: mew <p />
 * Time: 17/11/8 09:01  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface MutablePo<T> extends GenericPo<T> {

    Date getLastUpdatedTime();

    void setLastUpdatedTime(Date var1);

}
