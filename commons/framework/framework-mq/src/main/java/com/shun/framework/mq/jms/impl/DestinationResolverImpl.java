package com.shun.framework.mq.jms.impl;

import com.shun.framework.mq.jms.DestinationResolver;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 11:03  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DestinationResolverImpl implements DestinationResolver {

    private Map<String, Destination> cachedDestination = new HashMap();

    private static DestinationResolver destinationResolver = new DestinationResolverImpl();

    private DestinationResolverImpl() {
    }

    public static DestinationResolver getInstance() {
        return destinationResolver;
    }

    public Destination getDestination(Session session, String destinationName, int destinationType) throws
            JMSException {
        Destination destination = (Destination) this.cachedDestination.get(destinationName);
        if (destination == null) {
            if (destinationType == 0) {
                destination = session.createQueue(destinationName);
            } else if (destinationType == 3) {
                destination = session.createTopic(destinationName);
            }
            this.cachedDestination.put(destinationName, destination);
        }
        return (Destination) destination;
    }

    public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain)
            throws JMSException {
        Destination destination = (Destination) this.cachedDestination.get(destinationName);
        if (destination == null) {
            if (pubSubDomain) {
                destination = session.createTopic(destinationName);
            } else {
                destination = session.createQueue(destinationName);
            }
            this.cachedDestination.put(destinationName, destination);
        }
        return (Destination) destination;
    }

}
