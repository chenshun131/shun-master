package com.shun.framework.mq.management.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * User: mew <p />
 * Time: 17/11/8 10:41  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DestinationInfo implements Serializable {

    private static final long serialVersionUID = -8533012801203184259L;

    private Integer id;

    private Integer actionId;

    private String actionName;

    private int conId;

    private String conName;

    private String destination;

    private int destinationType = 0;

    private int status;

    private Date createdTime;

    private String createdBy;

    private Date lastUpdatedTime;

    private String lastUpdatedBy;

    private String remarks;

    public DestinationInfo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActionId() {
        return this.actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
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

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getConName() {
        return this.conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

}
