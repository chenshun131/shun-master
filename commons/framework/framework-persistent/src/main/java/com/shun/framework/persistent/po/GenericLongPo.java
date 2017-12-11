package com.shun.framework.persistent.po;

import java.util.Date;

/**
 * User: mew <p />
 * Time: 17/11/8 14:23  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class GenericLongPo implements GenericPo<Long> {

    private Long id;

    private Date createdTime;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

}
