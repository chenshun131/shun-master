package com.shun.framework.util;

import org.slf4j.Logger;

/**
 * User: mew <p />
 * Time: 17/11/6 16:30  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class LoggerUtil {

    public LoggerUtil() {
    }

    public static void info(Logger logger, String format, Object... arguments) {
        if (logger.isInfoEnabled()) {
            logger.info(format, arguments);
        }
    }

    public static void warn(Logger logger, String format, Object... arguments) {
        if (logger.isInfoEnabled()) {
            logger.warn(format, arguments);
        }
    }

    public static void error(Logger logger, String format, Object... arguments) {
        if (logger.isInfoEnabled()) {
            logger.error(format, arguments);
        }
    }

    public static void error(Logger logger, String msg, Throwable t) {
        if (logger.isInfoEnabled()) {
            logger.error(msg, t);
        }
    }

}
