package com.shun.framework.marshaller.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * User: mew <p />
 * Time: 17/11/8 10:24  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class XStreamMarshaller {

    private static XStream xstream = new XStream(new DomDriver());

    public XStreamMarshaller() {
    }

    public static String marshall(Object obj) {
        return xstream.toXML(obj);
    }

    public static Object unmarshall(String xml, Object obj) {
        return xstream.fromXML(xml, obj);
    }

}
