package com.shun.framework.marshaller.json;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializationProblemHandler;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.deser.BeanDeserializer;
import org.codehaus.jackson.map.deser.SettableBeanProperty;

import java.io.IOException;
import java.util.Iterator;

/**
 * User: mew <p />
 * Time: 17/11/8 10:21  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class CustomDeserializationProblemHandler extends DeserializationProblemHandler {

    private static Log log = LogFactory.getLog(CustomDeserializationProblemHandler.class);

    public CustomDeserializationProblemHandler() {
    }

    public boolean handleUnknownProperty(DeserializationContext ctxt, JsonDeserializer<?> deserializer, Object bean,
                                         String propName) throws IOException, JsonProcessingException {
        if (deserializer instanceof BeanDeserializer) {
            BeanDeserializer beanDeserializer = (BeanDeserializer) deserializer;
            Iterator it = beanDeserializer.properties();
            while (it.hasNext()) {
                SettableBeanProperty prop = (SettableBeanProperty) it.next();
                if (prop.getName().equalsIgnoreCase(propName)) {
                    try {
                        prop.deserializeAndSet(ctxt.getParser(), ctxt, bean);
                    } catch (Exception var9) {
                        beanDeserializer.wrapAndThrow(var9, bean, propName, ctxt);
                    }
                    return true;
                }
            }
        }
        log.warn("The property \"" + propName + "\" is not found.");
        return true;
    }

}
