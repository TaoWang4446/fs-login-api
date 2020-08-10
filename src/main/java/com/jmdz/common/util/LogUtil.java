package com.jmdz.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * log工具类
 *
 * @author LiCongLu
 * @date 2019-12-13 14:14
 */
public final class LogUtil {
    private static final Log logger = LogFactory.getLog(LogUtil.class);

    public static void fatal(Object message) {
        logger.fatal(message);
    }

    public static void fatal(Object message, Throwable exception) {
        logger.fatal(message, exception);
    }

    public static void error(Object message) {
        logger.error(message);
    }

    public static void ex(Throwable exception) {
        logger.error(exception.getMessage(), exception);
    }

    public static void error(Object message, Throwable exception) {
        logger.error(message, exception);
    }

    public static void warn(Object message) {
        logger.warn(message);
    }

    public static void warn(Object message, Throwable exception) {
        logger.warn(message, exception);
    }

    public static void info(Object message) {
        logger.info(message);
    }

    public static void line(Object message) {
        logger.info("\r\n" + message);
    }

    public static void info(Object message, Throwable exception) {
        logger.info(message, exception);
    }

    public static void debug(Object message) {
        logger.debug(message);
    }

    public static void debug(Object message, Throwable exception) {
        logger.error(message, exception);
    }

    public static void trace(Object message) {
        logger.trace(message);
    }

    public static void trace(Object message, Throwable exception) {
        logger.trace(message, exception);
    }

}