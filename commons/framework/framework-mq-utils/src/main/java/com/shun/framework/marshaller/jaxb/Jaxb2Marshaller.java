package com.shun.framework.marshaller.jaxb;

import com.shun.framework.marshaller.MarshallException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * User: mew <p />
 * Time: 17/11/8 10:14  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Jaxb2Marshaller {

    public static final String ENCODING_GBK = "GBK";

    public static final String ENCODING_UTF8 = "UTF-8";

    public Jaxb2Marshaller() {
    }

    private static String getEncodName(String msg) {
        return msg.toUpperCase().contains("UTF-8") ? "UTF-8" : "GBK";
    }

    public static Object unmarshall(String msg, Class<?> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ByteArrayInputStream bain = new ByteArrayInputStream(msg.trim().getBytes(getEncodName(msg)));
            return unmarshaller.unmarshal(bain);
        } catch (Exception var6) {
            throw new MarshallException("Unmarshall xml to object by jaxb failed.", var6);
        }
    }

    public static Object unmarshall(InputStream in, Class<?> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(in);
        } catch (Exception var5) {
            throw new MarshallException("Unmarshall xml to object by jaxb failed.", var5);
        }
    }

    public static String marshall(Object object) {
        Writer writer = null;
        String xmlStr = null;
        try {
            writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
            marshaller.setProperty("jaxb.encoding", "GBK");
            marshaller.marshal(object, writer);
            xmlStr = writer.toString();
        } catch (JAXBException var12) {
            throw new MarshallException("Marshall object to xml by jaxb failed.", var12);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException var11) {
                }
            }
        }
        return xmlStr;
    }

}
