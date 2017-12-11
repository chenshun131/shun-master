package com.shun.framework.mq.handler.dispatch;

import com.shun.framework.mq.jms.JmsAccessor;
import com.shun.framework.mq.jms.impl.DestinationInfoFactory;
import com.shun.framework.mq.handler.MessageHandler;
import com.shun.framework.mq.handler.impl.AbstractMessageHandler;
import com.shun.framework.mq.management.model.DestinationInfo;
import com.shun.framework.mq.request.MQRequest;
import com.shun.framework.mq.request.dispatch.DispatchRequest;

import javax.jms.JMSException;
import java.util.Iterator;
import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/8 11:25  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DispatchMessageHandler extends AbstractMessageHandler implements MessageHandler {

    private JmsAccessor mqAccessor;

    private DestinationInfoFactory destinationInfoFactory;

    public DispatchMessageHandler() {
    }

    public void setMqAccessor(JmsAccessor mqAccessor) {
        this.mqAccessor = mqAccessor;
    }

    public void handleMessage(Object request) throws Exception {
        DispatchRequest<?> dispatchRequest = (DispatchRequest) request;
        List<Integer> actionIds = dispatchRequest.getDispatchActionIds();
        Iterator i$ = actionIds.iterator();
        while (i$.hasNext()) {
            Integer actionId = (Integer) i$.next();
            MQRequest req = dispatchRequest.deepCopy();
            DestinationInfo destinationInfo = this.destinationInfoFactory.getDestinationInfo(actionId);
            req.setActionId(actionId.intValue());
            req.setDestination(destinationInfo.getDestination());
            req.setDestinationType(destinationInfo.getDestinationType());
            req.setRequestType(1);

            try {
                this.mqAccessor.sendMessage(destinationInfo.getDestination(), destinationInfo.getDestinationType(),
                        req);
            } catch (JMSException var9) {
                var9.printStackTrace();
            }
        }
    }

    public DestinationInfoFactory getDestinationInfoFactory() {
        return this.destinationInfoFactory;
    }

    public void setDestinationInfoFactory(DestinationInfoFactory destinationInfoFactory) {
        this.destinationInfoFactory = destinationInfoFactory;
    }

}
