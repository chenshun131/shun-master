package com.shun.framework.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/16 15:53  <p />
 * Version: V1.0  <p />
 * Description: 加密工具类,对 Map 生成消息摘要主要用于对客户端/服务器端来回传递的参数生成消息摘要 <p />
 */
public class HmacSHA256Utils {

    /**
     * 使用指定的密码对内容生成消息摘要（散列值）
     *
     * @param key
     * @param content
     * @return
     */
    public static String digest(String key, String content) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            byte[] secretByte = key.getBytes("utf-8");
            byte[] dataBytes = content.getBytes("utf-8");

            SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");
            mac.init(secret);

            byte[] doFinal = mac.doFinal(dataBytes);
            byte[] hexB = new Hex().encode(doFinal);
            return new String(hexB, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用指定的密码对整个 Map 的内容生成消息摘要（散列值）
     *
     * @param key
     * @param map
     * @return
     */
    public static String digest(String key, Map<String, ?> map) {
        StringBuilder s = new StringBuilder();
        for (Object values : map.values()) {
            if (values instanceof String[]) {
                for (String value : (String[]) values) {
                    s.append(value);
                }
            } else if (values instanceof List) {
                for (String value : (List<String>) values) {
                    s.append(value);
                }
            } else {
                s.append(values);
            }
        }
        return digest(key, s.toString());
    }

}
