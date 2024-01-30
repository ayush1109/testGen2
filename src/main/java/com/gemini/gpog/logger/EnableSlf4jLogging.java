package com.gemini.gpog.logger;

import io.netty.handler.logging.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnableSlf4jLogging implements LoggerUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnableSlf4jLogging.class);
//    @Override
//    public void logInfo(String message) {
//        logger.info(message);
//    }
//
//    @Override
//    public void logError(String message) {
//        logger.error(message);
//    }
//
//    @Override
//    public void logDebug(String message) {
//        logger.debug(message);
//    }
//
//    @Override
//    public void logWarning(String message) {
//        logger.warn(message);
//    }
//
//    @Override
//    public void logTrace(String message) {
//        logger.trace(message);
//    }
@Override
public void log(LogLevel level, String message) {
    switch (level) {
        case INFO:
            LOGGER.info(message);
            break;
        case ERROR:
            LOGGER.error(message);
            break;
        case DEBUG:
            LOGGER.debug(message);
            break;
        case WARN:
            LOGGER.warn(message);
            break;
        case TRACE:
            LOGGER.trace(message);
            break;
        default:
            LOGGER.warn("Unsupported log level: " + message);
    }
}
}
