package org.cosmos.modules.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * User: mew <p />
 * Time: 17/11/6 14:18  <p />
 * Version: V1.0  <p />
 * Description: 异常的工具类 <p />
 */
public class Exceptions {

    /**
     * 将 CheckedException 转换为 UncheckedException
     *
     * @param e
     * @return
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 将ErrorStack转化为String
     *
     * @param e
     * @return
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /**
     * 判断异常是否由某些底层的异常引起
     *
     * @param ex
     * @param causeExceptionClasses
     * @return
     */
    public static boolean isCausedBy(Exception ex, @SuppressWarnings("unchecked") Class<? extends Exception>...
            causeExceptionClasses) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }

}
