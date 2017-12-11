package com.shun.framework.mq.jms.impl;


import com.shun.framework.mq.management.model.DestinationInfo;
import com.shun.framework.mq.management.service.DestinationInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 11:03  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DestinationInfoFactory {

    private Log logger = LogFactory.getLog(DestinationInfoFactory.class);

    private Map<Integer, DestinationInfo> cachedDestinationInfo = new HashMap(100);

    private DestinationInfoService destinationInfoService;

    public DestinationInfoFactory() {
    }

    public DestinationInfo getDestinationInfo(Integer actionId) {
        DestinationInfo destinationInfo = (DestinationInfo) this.cachedDestinationInfo.get(actionId);
        if (destinationInfo == null) {
            try {
                destinationInfo = this.getDestinationInfoService().findByActionId(actionId);
            } catch (Exception var4) {
                this.logger.warn("Cannot find destination from actionId: " + actionId);
            }

            if (destinationInfo == null) {
                return null;
            }

            this.cachedDestinationInfo.put(actionId, destinationInfo);
        }

        return destinationInfo;
    }

    public void setDestinationInfoService(DestinationInfoService destinationInfoService) {
        this.destinationInfoService = destinationInfoService;
    }

    public DestinationInfoService getDestinationInfoService() {
        return this.destinationInfoService;
    }

}
