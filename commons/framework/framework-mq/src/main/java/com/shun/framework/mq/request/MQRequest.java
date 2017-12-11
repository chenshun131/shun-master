package com.shun.framework.mq.request;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 10:36  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface MQRequest extends Serializable {

    int getActionId();

    void setActionId(int var1);

    String getDestination();

    void setDestination(String var1);

    int getDestinationType();

    void setDestinationType(int var1);

    int getDeliveryMode();

    void setDeliveryMode(int var1);

    int getAcknowledgeMode();

    void setAcknowledgeMode(int var1);

    boolean isTransacted();

    void setTransacted(boolean var1);

    Date getRequestTime();

    void setRequestTime(Date var1);

    int getRequestType();

    void setRequestType(int var1);

    long getExpirationTime();

    void setExpirationTime(long var1);

    int getMessageFormat();

    void setMessageFormat(int var1);

    Map<String, Object> getProperties();

    void setProperties(Map<String, Object> var1);

    void addProperty(String var1, Object var2);

    Object getProperty(String var1);

    boolean containsProperty(String var1);

    MQRequest deepCopy();

}
