package com.shun.framework.util;

import java.util.UUID;

/**
 * User: mew <p />
 * Time: 17/11/6 16:46  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TokenUtil {

    private TokenUtil() {
    }

    public static String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

}
