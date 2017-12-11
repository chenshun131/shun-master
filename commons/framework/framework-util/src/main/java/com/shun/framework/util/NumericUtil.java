package com.shun.framework.util;

/**
 * User: mew <p />
 * Time: 17/11/6 16:42  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class NumericUtil {

    private NumericUtil() {
    }

    public static Long longValue(Integer num) {
        return num == null ? null : num.longValue();
    }

    public static Byte byteValue(Integer num) {
        return num == null ? null : num.byteValue();
    }

    public static Boolean booleanValue(Byte num) {
        return num != null && num.byteValue() != 0 ? true : false;
    }

    public static Integer intValue(Long num) {
        return num == null ? null : num.intValue();
    }

}
