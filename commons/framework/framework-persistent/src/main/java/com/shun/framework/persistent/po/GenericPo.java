package com.shun.framework.persistent.po;

import java.util.Date;

/**
 * User: mew <p />
 * Time: 17/11/8 09:01  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface GenericPo<T> {

    T getId();

    void setId(T id);

    Date getCreatedTime();

    void setCreatedTime(Date createdTime);

}
