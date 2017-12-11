package com.shun.framework.util;

import java.util.Random;

/**
 * User: mew <p />
 * Time: 17/11/6 16:43  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class RandomNumberBuilder {

    private static final String str = "0123456789";

    public RandomNumberBuilder() {
    }

    public static String getString(int length) {
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int num = random.nextInt(9);
            buf.append("0123456789".charAt(num));
        }
        return buf.toString();
    }

}
