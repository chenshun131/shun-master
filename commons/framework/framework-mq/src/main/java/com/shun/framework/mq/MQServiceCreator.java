package com.shun.framework.mq;

import com.shun.framework.mq.core.MQService;
import com.shun.framework.mq.jms.DestinationResolver;
import com.shun.framework.mq.jms.impl.*;
import com.shun.framework.mq.management.service.DestinationInfoService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.jms.remoting.JmsInvokerProxyFactoryBean;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 09:48  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MQServiceCreator {

    private static Object monitor = new Object();

    private static final String DESTINATION_INFO_QUEUE = "weihui.amo.destination";

    private static Map<Hashtable<String, String>, MQService> mqServiceMap = new HashMap<>();

    private static MQService mqService = null;

    private MQServiceCreator() {
    }

    public static MQService getMQService() {
        if (mqService == null) {
            Object var0 = monitor;
            synchronized (monitor) {
                if (mqService == null) {
                    mqService = createMQService((Hashtable) null);
                }
            }
        }
        return mqService;
    }

    public static MQService getMQService(Hashtable<String, String> environment) {
        MQService mqService = (MQService) mqServiceMap.get(environment);
        if (mqService == null) {
            Object var2 = monitor;
            synchronized (monitor) {
                mqService = (MQService) mqServiceMap.get(environment);
                if (mqService == null) {
                    mqService = createMQService(environment);
                    mqServiceMap.put(copyHashtable(environment), mqService);
                }
            }
        }
        return mqService;
    }

    private static MQService createMQService(Hashtable<String, String> environment) {
        ConnectionFactory connectionFactory = createConnectionFactory(environment);
        DestinationResolver destinationResolver = DestinationResolverImpl.getInstance();
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setDestinationResolver(destinationResolver);
        JmsAccessorImpl jmsAccessor = new JmsAccessorImpl();
        jmsAccessor.setJmsTemplate(jmsTemplate);
        jmsAccessor.setDestinationInfoFactory(createDestinationInfoFactory(connectionFactory));
        JmsService mqService = new JmsService();
        mqService.setMqAccessor(jmsAccessor);
        return mqService;
    }

    private static ConnectionFactory createConnectionFactory(Hashtable<String, String> environment) {
        if (environment == null) {
            throw new NullPointerException();
        } else {
            String url = (String) environment.get("java.naming.provider.url");
            if (url == null) {
                throw new NullPointerException();
            } else {
                ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);
                if (environment.containsKey("java.naming.security.principal")) {
                    activeMQConnectionFactory.setUserName((String) environment.get("java.naming.security.principal"));
                    activeMQConnectionFactory.setPassword((String) environment.get("java.naming.security.credentials"));
                }
                return new PooledConnectionFactory(activeMQConnectionFactory);
            }
        }
    }

    private static DestinationInfoFactory createDestinationInfoFactory(ConnectionFactory connectionFactory) {
        DestinationInfoFactory destinationInfoFactory = new DestinationInfoFactory();
        JmsInvokerProxyFactoryBean proxy = new JmsInvokerProxyFactoryBean();
        proxy.setConnectionFactory(connectionFactory);
        proxy.setQueueName("weihui.amo.destination");
        proxy.setServiceInterface(DestinationInfoService.class);
        proxy.afterPropertiesSet();
        destinationInfoFactory.setDestinationInfoService((DestinationInfoService) proxy.getObject());
        return destinationInfoFactory;
    }

    private static Hashtable<String, String> copyHashtable(Hashtable<String, String> environment) {
        Hashtable<String, String> env = new Hashtable<>();
        for (Object key : environment.keySet()) {
            env.put((String) key, environment.get(key));
        }
        return env;
    }

}
