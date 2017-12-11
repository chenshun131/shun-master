package com.shun.framework.util;

import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/6 16:30  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class UserIpUtil {

    public UserIpUtil() {
    }

    public static String getLocalIp() {
        String ip = "unknown";
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress().toString();
        } catch (UnknownHostException var3) {
            var3.printStackTrace();
        }
        return ip;
    }

    public static List<String> convertIpStrToList(String whiteIpStr) {
        if (whiteIpStr != null && !StringUtils.isBlank(whiteIpStr)) {
            String[] whiteIpArr = whiteIpStr.split(",");
            List<String> whiteIpList = Arrays.asList(whiteIpArr);
            return whiteIpList;
        } else {
            return null;
        }
    }

}
