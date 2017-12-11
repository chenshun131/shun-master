package com.shun.framework.marshaller.json;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.util.StdDateFormat;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * User: mew <p />
 * Time: 17/11/8 10:20  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class CustomSqlDateDeserializer extends JsonDeserializer<Date> {

    public CustomSqlDateDeserializer() {
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
                    java.util.Date date = null;
                    try {
                        date = StdDateFormat.instance.parse(str);
                    } catch (ParseException var11) {
                        try {
                            date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSS")).parse(str);
                        } catch (ParseException var10) {
                            try {
                                date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(str);
                            } catch (ParseException var9) {
                            }
                        }
                    }
                    return date != null ? new Date(date.getTime()) : null;
                }
            } else {
                throw ctxt.mappingException(Date.class);
            }
        } catch (IllegalArgumentException var12) {
            throw ctxt.weirdStringException(Date.class, "not a valid representation (error: " + var12.getMessage()
                    + ")");
        }
    }

}
