package com.shun.framework.mq.jms.surport;

import com.shun.framework.marshaller.json.JsonMarshaller;
import com.shun.framework.mq.request.MQRequest;
import com.shun.framework.mq.request.notify.NotifyRequest;

import javax.jms.*;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 11:48  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MessageConvert {

    public MessageConvert() {
    }

    public static Message toMessage(Session session, Object request, int messageFormat) throws JMSException {
        if (request instanceof Message) {
            return (Message) request;
        } else if (request instanceof MQRequest) {
            MQRequest mqRequest = (MQRequest) request;
            if (messageFormat == 2) {
                return createTextMessage(session, JsonMarshaller.marshall(request));
            } else if (messageFormat == 1) {
                return createObjectMessage(session, mqRequest);
            } else {
                NotifyRequest<?> notifyRequest = (NotifyRequest) mqRequest;
                if (messageFormat == 3) {
                    return createTextMessage(session, (String) notifyRequest.getContent());
                } else if (messageFormat == 4) {
                    return createMapMessage(session, (Map) notifyRequest.getContent());
                } else if (messageFormat == 5) {
                    return createByteArrayMessage((byte[]) ((byte[]) notifyRequest.getContent()), session);
                } else {
                    throw new IllegalArgumentException("Invalid message type. Message support object, json and map.");
                }
            }
        } else if (request instanceof String) {
            return createTextMessage(session, (String) request);
        } else if (request instanceof byte[]) {
            return createByteArrayMessage((byte[]) ((byte[]) request), session);
        } else if (request instanceof Map) {
            return createMapMessage(session, (Map) request);
        } else if (request instanceof Serializable) {
            return createObjectMessage(session, (Serializable) request);
        } else {
            throw new IllegalArgumentException("Invalid message type. Message support object, json and map.");
        }
    }

    public static Object fromMessage(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            return extractStringFromMessage((TextMessage) message);
        } else if (message instanceof BytesMessage) {
            return extractByteArrayFromMessage((BytesMessage) message);
        } else if (message instanceof MapMessage) {
            return extractMapFromMessage((MapMessage) message);
        } else {
            return message instanceof ObjectMessage ? extractSerializableFromMessage((ObjectMessage) message) : message;
        }
    }

    private static Message createTextMessage(Session session, String text) throws JMSException {
        TextMessage message = session.createTextMessage();
        message.setText(text);
        return message;
    }

    private static Message createByteArrayMessage(byte[] bytes, Session session) throws JMSException {
        BytesMessage message = session.createBytesMessage();
        message.writeBytes(bytes);
        return message;
    }

    private static Message createMapMessage(Session session, Map<?, ?> map) throws JMSException {
        MapMessage message = session.createMapMessage();
        Iterator i$ = map.keySet().iterator();
        while (i$.hasNext()) {
            Object key = i$.next();
            if (!(key instanceof String)) {
                throw new IllegalArgumentException("Cannot convert non-String key of type [" + key.getClass().getName
                        () + "] to JMS MapMessage entry");
            }
            message.setObject((String) key, map.get(key));
        }
        return message;
    }

    private static Message createObjectMessage(Session session, Serializable object) throws JMSException {
        ObjectMessage objMessage = session.createObjectMessage();
        objMessage.setObject(object);
        return objMessage;
    }

    private static String extractStringFromMessage(TextMessage message) throws JMSException {
        return message.getText();
    }

    private static byte[] extractByteArrayFromMessage(BytesMessage message) throws JMSException {
        byte[] bytes = new byte[(int) message.getBodyLength()];
        message.readBytes(bytes);
        return bytes;
    }

    private static Map<String, Object> extractMapFromMessage(MapMessage message) throws JMSException {
        Map<String, Object> map = new HashMap();
        Enumeration en = message.getMapNames();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            map.put(key, message.getObject(key));
        }
        return map;
    }

    private static Serializable extractSerializableFromMessage(ObjectMessage message) throws JMSException {
        return message.getObject();
    }

}
