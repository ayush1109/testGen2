package com.gemini.gpog;

import java.util.stream.Stream;

public enum RequiredDirectories {
    framework("gpog"),
    logger("gpog"),
    reporting("gpog"),
    annotation("gpog"),
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
