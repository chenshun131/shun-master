package com.shun.framework.common;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * User: mew <p />
 * Time: 17/11/6 11:09  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class QueryResult<T> implements Serializable {

    private static final long serialVersionUID = 8404041332397650032L;

    @XmlTransient
    private T[] data;

    private PageInfo pageInfo;

    public QueryResult() {
    }

    public PageInfo getPageInfo() {
        return this.pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public T[] getData() {
        return this.data;
    }

    public void setData(T[] data) {
        this.data = data;
    }

    public boolean hasData() {
        return this.data != null && this.data.length != 0;
    }

}
