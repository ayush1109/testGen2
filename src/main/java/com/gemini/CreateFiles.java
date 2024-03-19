package com.gemini;

import japa.parser.ast.CompilationUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
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

    public static void createLocatorFile() throws IOException, ClassNotFoundException {

        final HashMap<String, ArrayList<HashMap<String, String>>> features = LocatorPOJO.getFeatures();
        String fileName = null;
        String content;

        for (Map.Entry<String, ArrayList<HashMap<String, String>>> entry : features.entrySet()) {
            CompilationUnit c = Utils.createEnhancedCompilationUnit("com.gemini.locator");
            Utils.setTypeDeclaration(c, entry.getKey());
            fileName = entry.getKey();
            if (StringUtils.equalsIgnoreCase(Utils.readProperties("existingAutomation"), "true")) {
                String locatorPath = Utils.readProperties("projectPath") + "/src/test/java/com/gemini/locator/" + entry.getKey() + ".java";
                File locatorFile = new File(locatorPath);
                if (locatorFile.exists()) {
                    ArrayList<String> b = new ArrayList<>();
                    ArrayList<String> wholeLocatorValue = new ArrayList<>();
                    HashMap<String, String> locatorsMap = new HashMap<>();
                    content = FileUtils.readFileToString(locatorFile);
                    String[] aa = content.split("@LocatorType");
                    for (int i = 1; i < aa.length; i++) {
                        String value = aa[i].split("\"\\)")[0].split("= \"")[1].toUpperCase();
                        String locator = aa[i].split("\"\\)")[1].split(" = ")[0].replace("\n    public static By ", "");
                        String locatorValue = aa[i].split("\"\\)")[1].split(" = ")[1];
                        locatorsMap.put(locator + "-" + value, locatorValue);
//                        b.add(aa[i]);
                    }

                    for (Map.Entry<String, String> locator : locatorsMap.entrySet()) {
                            Utils.setStepDefinitionVariable(c, locator.getValue(), locator.getKey());
                    }

                    for (HashMap<String, String> locators : entry.getValue()
                    ) {
                        for (Map.Entry<String, String> locator : locators.entrySet()) {
                            if(!locatorsMap.containsKey(locator.getKey()))
                            Utils.setStepDefinitionVariable(c, locator.getValue(), locator.getKey());
                        }
                    }

                    new Utils().savePageObjectsOnFileSystem("com" + File.separator + "gemini" +
                                    File.separator + "locator" +
                                    File.separator,
                            fileName, c, false);

                    Utils.correctXpaths("com" + File.separator + "gemini" +
                                    File.separator + "locator" +
                                    File.separator,
                            fileName);

                } else {
                    for (HashMap<String, String> locators : entry.getValue()
                    ) {
                        for (Map.Entry<String, String> locator : locators.entrySet())
                            Utils.setStepDefinitionVariable(c, locator.getValue(), locator.getKey());
                    }
                    new Utils().savePageObjectsOnFileSystem("com" + File.separator + "gemini" +
                                    File.separator + "locator" +
                                    File.separator,
                            fileName, c, false);

                    Utils.correctXpaths("com" + File.separator + "gemini" +
                                    File.separator + "locator" +
                                    File.separator,
                            fileName);
                }
            } else {
                for (HashMap<String, String> locators : entry.getValue()
                ) {
                    for (Map.Entry<String, String> locator : locators.entrySet())
                        Utils.setStepDefinitionVariable(c, locator.getValue(), locator.getKey());
                }
                new Utils().savePageObjectsOnFileSystem("com" + File.separator + "gemini" +
                                File.separator + "locator" +
                                File.separator,
                        fileName, c, false);

                Utils.correctXpaths("com" + File.separator + "gemini" +
                                File.separator + "locator" +
                                File.separator,
                        fileName);
            }
        }
    }
}