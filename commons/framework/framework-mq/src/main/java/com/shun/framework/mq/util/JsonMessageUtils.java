package com.shun.framework.mq.util;

import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 09:33  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class JsonMessageUtils {

    public JsonMessageUtils() {
    }

    public static int getDeliveryMode(Map<String, Object> jsonMessageMap) {
        if (jsonMessageMap.containsKey("deliveryMode")) {
            return ((Integer) jsonMessageMap.get("deliveryMode")).intValue();
        } else {
            int deliveryMode = ((Integer) jsonMessageMap.get("DeliveryMode")).intValue();
            return deliveryMode == 0 ? 2 : deliveryMode;
        }
    }

    public static int getAcknowledgeMode(Map<String, Object> jsonMessageMap) {
        if (jsonMessageMap.containsKey("acknowledgeMode")) {
            return ((Integer) jsonMessageMap.get("acknowledgeMode")).intValue();
        } else {
            int acknowledgeMode = ((Integer) jsonMessageMap.get("AcknowledgeMode")).intValue() + 1;
            if (acknowledgeMode == 4) {
                acknowledgeMode = 0;
            } else if (acknowledgeMode == 5) {
                acknowledgeMode = 1;
            }
            return acknowledgeMode;
        }
    }

}
