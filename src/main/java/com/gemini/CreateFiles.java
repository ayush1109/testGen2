package com.gemini;

import japa.parser.ast.CompilationUnit;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateFiles {
    public static void createFeatureFile(String name, StringBuilder content) throws IOException {

//        StringBuilder content = new StringBuilder();

        File featureFile = new File(name + ".feature");
        FileWriter fileWriter = new FileWriter(featureFile);

//        content.append("Feature: ").append(testcase.getFeatureName());
//        content.append("\n\n  Scenario: ").append(testcase.getScenarioName());
//        content.append("\n    ").append(testcase.getSteps());

        fileWriter.write(content.toString());

        fileWriter.close();

    }

    public static void createLocatorFile() throws IOException {

        final HashMap<String, ArrayList<HashMap<String, String>>> features = LocatorPOJO.getFeatures();
        String fileName = null;
        for (Map.Entry<String, ArrayList<HashMap<String, String>>> entry : features.entrySet()) {
            CompilationUnit c = Utils.createEnhancedCompilationUnit("com.gemini");
            Utils.setTypeDeclaration(c, entry.getKey());
            fileName = entry.getKey();
            for (HashMap<String, String> locators : entry.getValue()
            ) {
                for (Map.Entry<String, String> locator : locators.entrySet())
                    Utils.setStepDefinitionVariable(c, locator.getValue(), locator.getKey());
            }
            new Utils().savePageObjectsOnFileSystem("com" + File.separator + "gemini" +
//                            File.separator + "locator" +
                            File.separator,
                    fileName, c, false);

            Utils.correctXpaths("com" + File.separator + "gemini" +
//                            File.separator + "locator" +
                            File.separator,
                    fileName);
        }


    }
}