package com.shun.framework.persistent.dao;

import com.shun.framework.persistent.mapper.GenericMapper;
import com.shun.framework.persistent.po.MutablePo;

/**
 * User: mew <p />
 * Time: 17/11/8 08:59  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MutableDao<EXAMPLE, PO extends MutablePo,
        MAPPER extends GenericMapper<PO, EXAMPLE>> extends GenericDao<EXAMPLE, PO, MAPPER> {

    private boolean enforceLastUpdatedTime = true;

    public MutableDao() {
    }

    public int updateByPrimaryKey(PO po, boolean selective) {
        return super.updateByPrimaryKey(this.enforceLastUpdatedTime(po, selective), selective);
    }

    public int insert(PO po, boolean selective) {
        return super.insert(this.enforceLastUpdatedTime(po, selective), selective);
    }

    protected PO enforceLastUpdatedTime(PO po, boolean selective) {
        if (this.enforceLastUpdatedTime) {
            po.setLastUpdatedTime(this.getCurrentDate());
        }

        return po;
    }

    public boolean isEnforceLastUpdatedTime() {
        return this.enforceLastUpdatedTime;
    }

    public void setEnforceLastUpdatedTime(boolean enforceLastUpdatedTime) {
        this.enforceLastUpdatedTime = enforceLastUpdatedTime;
    }

}
