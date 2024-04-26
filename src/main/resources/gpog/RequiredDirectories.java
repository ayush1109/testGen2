package com.gemini.gpog;

import java.util.stream.Stream;

public enum RequiredDirectories {
    framework_HelperFunctions("gpog"),
    logger_EnableJLCLogging("gpog"),
    logger_EnableLog4jLogging("gpog"),
    logger_EnableSlf4jLogging("gpog"),
    logger_LoggerUtils("gpog"),
    logger_LoggingLevels("gpog"),
    reporting_EnableSerenityReporting("gpog"),
    reporting_Reporting("gpog"),
    annotation_LocatorType("gpog"),
    locator("project");

    private String folder;

    RequiredDirectories(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    public static Stream<RequiredDirectories> stream() {
        return Stream.of(RequiredDirectories.values());
    }
}
