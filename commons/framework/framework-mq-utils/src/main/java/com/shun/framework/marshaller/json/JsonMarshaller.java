package com.shun.framework.marshaller.json;

import com.shun.framework.marshaller.MarshallException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.deser.CustomDeserializerFactory;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 10:17  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class JsonMarshaller {

    private static Log log = LogFactory.getLog(JsonMarshaller.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JsonMarshaller() {
    }

    public static String marshall(Object obj) {
        if (log.isInfoEnabled()) {
            log.info("Transform object to json: " + obj);
        }

        StringWriter sw = new StringWriter();

        try {
            objectMapper.writeValue(sw, obj);
        } catch (Exception var3) {
            log.error("Marshall object to json failed. Object: " + obj);
            throw new MarshallException("Marshall object to json failed.", var3);
        }

        if (log.isInfoEnabled()) {
            log.info("After transforming the json: " + sw.toString());
        }

        return sw.toString().replaceAll("\\\\\\\\", "\\\\");
    }

    public static <T> T unmarshall(String content, Class<T> mainClass, Class... genericClass) {
        if (log.isInfoEnabled()) {
            log.info("Transform json to object: " + content);
        }

        try {
            return genericClass != null && genericClass.length != 0 && genericClass[0] != null ? objectMapper
                    .readValue(new StringReader(content), TypeFactory.parametricType(mainClass, genericClass)) :
                    objectMapper.readValue(new StringReader(content), mainClass);
        } catch (Exception var4) {
            log.error("Unmarshall json to object failed. Content: " + content);
            throw new MarshallException("Unmarshall json to object failed.", var4);
        }
    }

    /** @deprecated  */
    @Deprecated
    public static Object convertAndUnmarshall(String content, Class<?> mainClass, Class... genericClass) {
        content = convert(content);

        try {
            return genericClass != null && genericClass.length != 0 ? objectMapper.readValue(new StringReader
                    (content), TypeFactory.parametricType(mainClass, genericClass)) : objectMapper.readValue(new
                    StringReader(content), mainClass);
        } catch (Exception var4) {
            log.error("Unmarshall json to object failed. Content: " + content);
            throw new MarshallException("Unmarshall json to object failed.", var4);
        }
    }

    private static String convert(String content) {
        Map<String, Object> map = (Map) unmarshall(content, Map.class);
        convert(map);
        return marshall(map);
    }

    private static void convert(Map<String, Object> map) {
        String[] keys = (String[]) ((String[]) ((String[]) map.keySet().toArray(new String[0])).clone());
        String[] arr$ = keys;
        int len$ = keys.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            String key = arr$[i$];
            Object v = map.get(key);
            if (v instanceof Map) {
                convert((Map) v);
            }

            if (key.charAt(0) < '`') {
                map.put(initialLowwerCase(key), v);
                map.remove(key);
            }
        }

    }

    private static String initialLowwerCase(String key) {
        return key.substring(0, 1).toLowerCase() + key.substring(1);
    }

    static {
        objectMapper.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        CustomDeserializerFactory cdsf = new CustomDeserializerFactory();
        objectMapper.setDeserializerProvider(new StdDeserializerProvider(cdsf));
        cdsf.addSpecificMapping(Date.class, new CustomDateDeserializer());
        cdsf.addSpecificMapping(java.sql.Date.class, new CustomSqlDateDeserializer());
        CustomSerializerFactory csf = new CustomSerializerFactory();
        objectMapper.setSerializerFactory(csf);
        csf.addSpecificMapping(Date.class, new CustomDateSerializer());
        csf.addSpecificMapping(java.sql.Date.class, new CustomSqlDateSerializer());
        objectMapper.getDeserializationConfig().addHandler(new CustomDeserializationProblemHandler());
    }
}
