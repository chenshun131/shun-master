package com.shun.framework.mq.management.model;

import java.io.Serializable;
import java.util.Date;

/**
 * User: mew <p />
 * Time: 17/11/8 10:41  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class FailedMessage implements Serializable {

    private static final long serialVersionUID = 8273773635960523067L;

    private Long id;

    private Integer actionId;

    private String destination;

    private int destinationType;

    private String mqRequest;

    private int failedType;

    private int status;

    private Date createdTime;

    private String desc;

    public FailedMessage() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActionId() {
        return this.actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getMqRequest() {
        return this.mqRequest;
    }

    public void setMqRequest(String mqRequest) {
        this.mqRequest = mqRequest;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDestinationType() {
        return this.destinationType;
    }

    public void setDestinationType(int destinationType) {
        this.destinationType = destinationType;
    }

    public int getFailedType() {
        return this.failedType;
    }

    public void setFailedType(int failedType) {
        this.failedType = failedType;
    }

}
