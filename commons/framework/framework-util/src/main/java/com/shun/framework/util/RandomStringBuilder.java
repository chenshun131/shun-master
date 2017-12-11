package com.shun.framework.util;

import java.util.Random;

/**
 * User: mew <p />
 * Time: 17/11/6 16:43  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class RandomStringBuilder {

    private static final String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public RandomStringBuilder() {
    }

    public static String getString(int length) {
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int num = random.nextInt(62);
            buf.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(num));
        }
        return buf.toString();
    }

}
