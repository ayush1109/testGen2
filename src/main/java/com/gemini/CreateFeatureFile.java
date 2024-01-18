package com.gemini;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFeatureFile {
    public static void create(String name, StringBuilder content) throws IOException {

//        StringBuilder content = new StringBuilder();

        File featureFile = new File(name + ".feature");
        FileWriter fileWriter = new FileWriter(featureFile);

//        content.append("Feature: ").append(testcase.getFeatureName());
//        content.append("\n\n  Scenario: ").append(testcase.getScenarioName());
//        content.append("\n    ").append(testcase.getSteps());

        fileWriter.write(content.toString());

        fileWriter.close();

    }
}
