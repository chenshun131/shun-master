package com.shun.framework.marshaller.jibx;

import com.shun.framework.marshaller.MarshallException;
import org.jibx.runtime.*;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: mew <p />
 * Time: 17/11/8 10:16  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class JibxMarshaller {

    public static final ConcurrentMap<String, IBindingFactory> cacheMap = new ConcurrentHashMap();

    public JibxMarshaller() {
    }

    public static <T> T unmarshall(Class<T> type, String xml) {
        try {
            IUnmarshallingContext context = getJiBXUnmarshallingContext(type);
            StringReader sr = new StringReader(xml);
            return (T) context.unmarshalDocument(sr);
        } catch (Exception var5) {
            throw new MarshallException("Unmarshall xml to object by jibx failed.", var5);
        }
    }

    public static <T> String marshall(T object) {
        try {
            IMarshallingContext context = getJiBXMarshallingContext(object.getClass());
            StringWriter sw = new StringWriter();
            context.setOutput(sw);
            context.marshalDocument(object);
            return sw.toString();
        } catch (Exception var3) {
            throw new MarshallException("Marshall object to xml by jibx failed.", var3);
        }
    }

    private static IUnmarshallingContext getJiBXUnmarshallingContext(Class clazz) throws JiBXException {
        IUnmarshallingContext context = getBindingFactory(clazz).createUnmarshallingContext();
        return context;
    }

    private static IMarshallingContext getJiBXMarshallingContext(Class clazz) throws JiBXException {
        IMarshallingContext context = getBindingFactory(clazz).createMarshallingContext();
        return context;
    }

    private static IBindingFactory getBindingFactory(Class clazz) throws JiBXException {
        String className = clazz.getName();
        IBindingFactory bindingFactory = null;
        ConcurrentMap var3 = cacheMap;
        synchronized (cacheMap) {
            if (cacheMap.containsKey(className)) {
                bindingFactory = (IBindingFactory) cacheMap.get(className);
            } else {
                bindingFactory = BindingDirectory.getFactory(clazz.getSimpleName(), clazz.getPackage().getName());
                cacheMap.put(className, bindingFactory);
            }
            return bindingFactory;
        }
    }

}
