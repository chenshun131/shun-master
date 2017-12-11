package com.shun.framework.mq;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 11:56  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class GenericResult implements Serializable {

    private static final long serialVersionUID = 2695232051151828055L;

    private String businessId;

    private Map<String, String> extensions;

    private boolean isSuccess;

    private Date resultTime;

    public GenericResult() {
    }

    public String getBusinessId() {
        return this.businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Map<String, String> getExtensions() {
        return this.extensions;
    }

    public void setExtensions(Map<String, String> extensions) {
        this.extensions = extensions;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Date getResultTime() {
        return this.resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }

}
