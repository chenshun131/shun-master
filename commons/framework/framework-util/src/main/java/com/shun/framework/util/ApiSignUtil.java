package com.shun.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * User: mew <p />
 * Time: 17/11/6 16:34  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ApiSignUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiSignUtil.class);

    public ApiSignUtil() {
    }

    public static String getSign(TreeMap<String, Object> treeMap, String signKey) {
        if (treeMap == null) {
            return "";
        } else {
            String ascStr = toAscString(treeMap) + String.format("&key=%s", signKey);
            return md5(ascStr);
        }
    }

    private static String toAscString(TreeMap<String, Object> treeMap) {
        StringBuilder buf = new StringBuilder();
        try {
            Iterator it = treeMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) it.next();
                buf.append(String.format("%s=%s", entry.getKey(),
                        URLEncoder.encode(entry.getValue().toString(), "UTF-8")));
            }
        } catch (Exception var4) {
            LOGGER.error("getAscString.Exception:", var4);
        }
        return buf.toString();
    }

    private static String md5(String s) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }
            return (new String(str)).toLowerCase();
        } catch (Exception var10) {
            LOGGER.error("md5.Exception", var10);
            return null;
        }
    }

    public static String buildSign(TreeMap<String, String> treeMap, String signKey) {
        if (treeMap == null) {
            return "";
        } else {
            String ascStr = toAscString2(treeMap) + signKey;
            return md5(ascStr);
        }
    }

    private static String toAscString2(TreeMap<String, String> treeMap) {
        StringBuilder buf = new StringBuilder();
        try {
            Iterator it = treeMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry) it.next();
                buf.append(String.format("%s=%s&", entry.getKey(),
                        URLEncoder.encode((String) entry.getValue(), "UTF-8")));
            }
        } catch (Exception var4) {
            LOGGER.error("getAscString2.Exception:", var4);
        }
        return buf.substring(0, buf.lastIndexOf("&"));
    }

}
