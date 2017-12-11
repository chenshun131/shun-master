package com.shun.framework.common;

import java.io.Serializable;

/**
 * User: mew <p />
 * Time: 17/11/6 11:08  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 2552127509574205157L;

    private Integer currentPage = Integer.valueOf(1);

    private Integer pageSize = Integer.valueOf(20);

    private Integer totalPage = null;

    private Integer count = Integer.valueOf(0);

    public PageInfo() {
    }

    public int getBegin() {
        return (this.currentPage.intValue() - 1) * this.pageSize.intValue();
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPage() {
        if (this.totalPage == null || this.totalPage.intValue() == 0) {
            if (this.count.intValue() != 0 && this.count.intValue() % this.pageSize.intValue() == 0) {
                this.totalPage = this.count.intValue() / this.pageSize.intValue();
            } else {
                this.totalPage = this.count.intValue() / this.pageSize.intValue() + 1;
            }
        }

        return this.totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

}
