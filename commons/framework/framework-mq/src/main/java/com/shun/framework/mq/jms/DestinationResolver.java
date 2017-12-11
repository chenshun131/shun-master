package com.shun.framework.mq.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * User: mew <p />
 * Time: 17/11/8 09:48  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface DestinationResolver extends org.springframework.jms.support.destination.DestinationResolver {

    Destination getDestination(Session var1, String var2, int var3) throws JMSException;

}
