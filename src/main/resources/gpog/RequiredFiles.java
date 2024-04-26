package com.gemini.gpog;

import java.util.stream.Stream;

public enum RequiredFiles {
    LocatorsModel("gpog"),
    Settings("gpog"),
    featureFile("feature");

    private String folder;

    RequiredFiles(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    public static Stream<RequiredFiles> stream() {
        return Stream.of(RequiredFiles.values());
    }
}
