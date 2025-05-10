package am.staff.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Log {
    private static Logger logger = LoggerFactory.getLogger(getCurrentClassName());

    static {
        SetupLogBackLogger.setupLogbackLoggerDefaultPattern();
    }

    private static String getCurrentClassName() {
        return Thread.currentThread().getStackTrace()[3].getClassName();
    }

    public static void debug(String message) {
        log("DEBUG", message);
    }

    public static void info(String message) {
        log("INFO", message);
    }

    public static void warning(String message) {
        log("WARN", message);
    }

    public static void error(String message) {
        log("ERROR", message);
    }

    private static void log(String logType, String message) {
        logger
                .atLevel(org.slf4j.event.Level.valueOf(logType))
                .log(message);
    }
}

