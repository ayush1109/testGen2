package com.gemini.gpog.logger;

import io.netty.handler.logging.LogLevel;

import static com.gemini.gpog.pageobjectgenerator.Settings.LOG4J;


public class EnableLog4jLogging implements LoggerUtils {
    @Override
    public void log(LogLevel level, String message) {
        switch (level) {
            case INFO:
                LOG4J.info(message);
                break;
            case ERROR:
                LOG4J.error(message);
                break;
            case DEBUG:
                LOG4J.debug(message);
                break;
            case WARN:
                LOG4J.warn(message);
                break;
            case TRACE:
                LOG4J.trace(message);
                break;
            default:
                LOG4J.warn("Unsupported log level: " + message);
        }
    }
}
