package com.gemini.gpog.pageobjectgenerator.featuregenerator;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class FeatureCodeGenerator {

    public static Map<String, String> stepsList = new LinkedHashMap<>();

    public static String className;

    public static void main(String[] args) {

    }

    public static void generateFeature() {
            try {
                File featureFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\features\\" + className + ".feature");
                FileWriter fw = new FileWriter(featureFile, false);
                fw.write("Feature: " + className + " feature\n\n");
                //more code
                fw.write("  Background :\n");
                //more code
                fw.write("  Given: user is on homepage\n\n");

                        for (var step : stepsList.entrySet()
                        ) {
                            fw.write("  Scenario: " + step.getKey() + "\n");
                            fw.write("\t" + step.getValue().replace("\"", "")
                                    .replace("^", "")
                                    .replace("$", "")+ "\n\n");
                        }

                fw.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }



    }
}
