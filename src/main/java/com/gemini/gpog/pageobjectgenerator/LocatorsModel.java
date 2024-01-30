package com.gemini.gpog.pageobjectgenerator;

import java.util.ArrayList;
import java.util.List;

public class LocatorsModel {

    private static ArrayList<String> locatorsList;

    private static String env;

    private static String framework;

    private static String logging;

    private static String reporting;

    public LocatorsModel(ArrayList<String> locatorsList, String framework, String logging, String reporting, String env) {
        this.locatorsList = locatorsList;
        this.framework = framework;
        this.logging = logging;
        this.reporting = reporting;
        this.env = env;
    }

    public LocatorsModel() {

    }

    public static List<String> getLocatorsList() {
        return locatorsList;
    }

    public static void setLocatorsList(ArrayList<String> locatorsList) {
        LocatorsModel.locatorsList = locatorsList;
    }

    public static String getEnv() {
        return env;
    }

    public static void setEnv(String env) {
        LocatorsModel.env = env;
    }

    public static String getFramework() {
        return framework;
    }

    public static void setFramework(String framework) {
        LocatorsModel.framework = framework;
    }

    public static String getLogging() {
        return logging;
    }

    public static void setLogging(String logging) {
        LocatorsModel.logging = logging;
    }

    public static String getReporting() {
        return reporting;
    }

    public static void setReporting(String reporting) {
        LocatorsModel.reporting = reporting;
    }

    @Override
    public String toString() {
        return "LocatorsModel{" +
                "locatorsList=" + locatorsList +
                ", framework='" + framework + '\'' +
                ", logging='" + logging + '\'' +
                ", reporting='" + reporting + '\'' +
                '}';
    }
}
