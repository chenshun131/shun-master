package com.shun.framework.marshaller.json;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.util.StdDateFormat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: mew <p />
 * Time: 17/11/8 10:22  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

    public CustomDateDeserializer() {
    }

    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        try {
            if (t == JsonToken.VALUE_NUMBER_INT) {
                return new Date(jp.getLongValue());
            } else if (t == JsonToken.VALUE_STRING) {
                String str = jp.getText().trim();
                if (str.length() == 0) {
                    return null;
                } else if (str.contains("Date")) {
                    str = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
                    return new Date(Long.parseLong(str));
                } else {
                    try {
                        return StdDateFormat.instance.parse(str);
                    } catch (ParseException var10) {
                        try {
                            return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSS")).parse(str);
                        } catch (ParseException var9) {
                            try {
                                return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(str);
                            } catch (ParseException var8) {
                                return null;
                            }
                        }
                    }
                }
            } else {
                throw ctxt.mappingException(Date.class);
            }
        } catch (IllegalArgumentException var11) {
            throw ctxt.weirdStringException(Date.class, "not a valid representation (error: " + var11.getMessage()
                    + ")");
        }
    }

}
