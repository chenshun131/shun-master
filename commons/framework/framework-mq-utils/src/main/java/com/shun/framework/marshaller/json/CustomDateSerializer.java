package com.shun.framework.marshaller.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * User: mew <p />
 * Time: 17/11/8 10:22  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

    public CustomDateSerializer() {
    }

    public void serialize(Date date, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        jgen.writeString("\\/Date(" + date.getTime() + ")\\/");
    }

}
