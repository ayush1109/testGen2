package com.gemini;//package org.example;//package org.example;


import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import com.gemini.gpog.pageobjectgenerator.CodeGeneratorRunner;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.TypeDeclaration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import static com.gemini.Utils.readProperties;

public class CustomPOSTagger {
    static String modelFile = "";
    static String pageName = "";

    public static void main(String[] args) {
        try {

            LinkedHashMap<Integer, List<String>> data = ExcelUtilities.readBySheetNumber(readProperties("filePath"), 0);
            List<Testcase> testcaseList = ExcelUtilities.getTestCases(data);
            List<String> stepsGPOG = new ArrayList<>();
            StringBuilder content = new StringBuilder();
            String step = "";
            String stepToTrain = "";

            if (StringUtils.equalsIgnoreCase(readProperties("existingAutomation"), "true")) {
                String featureFilePath = readProperties("projectPath") + "/src/test/resources/features/" + Testcase.getFeatureName() + ".feature";
                File featureFile = new File(featureFilePath);
                if (featureFile.exists()) {
                    content = new StringBuilder(FileUtils.readFileToString(featureFile));
                } else {
                    content.append("Feature: ").append(Testcase.getFeatureName());
                    content.append("\n\n  Background:\n    Given user is on homepage");
                }
            } else {
                content.append("Feature: ").append(Testcase.getFeatureName());
                content.append("\n\n  Background:\n    Given user is on homepage");
            }
            for (Testcase testcase : testcaseList
            ) {
                boolean customFailed = false;
                boolean newScenarioFound = false;
                List<TokenWithTag> result;
                HashMap<String, String> tokenToKeys = new HashMap<>();
                SeleniumActions.open();

                if (StringUtils.equalsIgnoreCase(readProperties("existingAutomation"), "true")) {
                    if (!StringUtils.containsIgnoreCase(content, testcase.getScenarioName())) {
                        newScenarioFound = true;
                        content.append("\n\n  Scenario: ").append(testcase.getScenarioName());
                    }
                } else {
                    content.append("\n\n  Scenario: ").append(testcase.getScenarioName());
                }


                for (String sampleString : testcase.getSteps()
                ) {
                    String keyword = sampleString.split("----")[1];


//                    if (customFailed) {
                        if ((sampleString.contains("verifies") || sampleString.contains("verify") || sampleString.contains("should")) && (keyword.equals("Then"))) {
                            modelFile = "custom-pos-model-temp-verify.bin";
                        } else if (StringUtils.contains(sampleString, "enter") || StringUtils.contains(sampleString, "input") || StringUtils.contains(sampleString, "type") || (StringUtils.contains(sampleString, "write")))
                            modelFile = "custom-pos-model-input.bin";
                        else modelFile = "custom-pos-model-temp.bin";

                        //  Test the model

                        result = Utils.testModel(sampleString.split("----")[0], modelFile);
                        // Display the result
                        System.out.println("Token\t:\tTag\t:\tProbability\n---------------------------------------------");
                        for (TokenWithTag tokenWithTag : result) {
                            System.out.println(tokenWithTag.token + "\t:\t" + tokenWithTag.tag + "\t:\t" + tokenWithTag.probability);
                        }

                        tokenToKeys = MapTokenToKeys.map(result);
                        step = GenerateStep.generate(tokenToKeys, keyword);
                        if (StringUtils.equalsIgnoreCase(readProperties("userInterventionNeeded"), "true"))
                            if (StringUtils.contains(step, '<') || StringUtils.contains(step, '>')) {

                                modelFile = "custom-pos-model-custom.bin";
                                result = Utils.testModel(sampleString.split("----")[0], modelFile);
                                if (result == null || result.size() < 1) {
                                    customFailed = true;
                                } else {
                                    tokenToKeys = MapTokenToKeys.map(result);
                                    step = GenerateStep.generate(tokenToKeys, keyword);
                                    if (StringUtils.contains(step, '<') || StringUtils.contains(step, '>')) {
                                        customFailed = true;
                                    }
                                }

                                if(customFailed) {


                                    Scanner sc = new Scanner(System.in);
                                    System.out.println("GemGen could not understand \"" + sampleString + "\". Please enter statement to write in feature file");
                                    step = sc.nextLine();

                                    if(StringUtils.containsIgnoreCase(step, "custom")) {
                                        File destinationDirectoryImp = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\gemini\\implementation");
                                        File destinationDirectoryStep = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\gemini\\stepdefinition");
                                        File checkingDirectory = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\gemini\\implementation");

                                        if(!checkingDirectory.isDirectory()) {
                                            File sourceDirectoryImp = new File(readProperties("projectPath") + File.separator + "src\\test\\java\\com\\gemini\\implementation");
                                            File sourceDirectoryStep = new File(readProperties("projectPath") + File.separator + "src\\test\\java\\com\\gemini\\stepdefinition");
                                            FileUtils.copyDirectory(sourceDirectoryImp, destinationDirectoryImp);
                                            FileUtils.copyDirectory(sourceDirectoryStep, destinationDirectoryStep);
                                        }

//
                                    }
                                    else {
                                        System.out.println("GemGen could not understand \"" + sampleString + "\". Please enter statement to teach our model");
                                        stepToTrain = sc.nextLine();
                                        boolean fileCreated = Utils.createTrainingFile(stepToTrain); //text file to create
                                        TrainModel.trainModel("src/main/java/com/gemini/training_data_custom.txt", "src/main/resources/models/custom-pos-model-custom.bin");
                                        modelFile = "custom-pos-model-custom.bin";
//                        boolean jsonCreated = createJsonFile();  //json to store custom mapping
                                        result = Utils.testModel(step, modelFile);
                                        tokenToKeys = MapTokenToKeys.map(result);
                                    }
                                }
//                        step = GenerateStep.generate(tokenToKeys, keyword);
                            }
//                    }
                    stepsGPOG.add(step);
                    if (StringUtils.equalsIgnoreCase(readProperties("existingAutomation"), "true")) {
                        if (newScenarioFound) {
                            content.append("\n    ").append(step);
                        }
                    } else {
                        content.append("\n    ").append(step);
                    }
                    if(!StringUtils.containsIgnoreCase(step, "custom")) {
                        String inputData = null;
                        if (keyword.equals("When")) {
                            String action = GenerateStep.identifyAction(tokenToKeys);
                            if (action == null) action = tokenToKeys.get("A");
                            String finalAction = action;
                            if (Utils.clickCommands.stream().anyMatch(e -> e.equalsIgnoreCase(finalAction)))
                                action = "click";
                            else if (Utils.typeCommands.stream().anyMatch(e -> e.equalsIgnoreCase(finalAction)))
                                action = "input";
                            if (!StringUtils.equalsIgnoreCase("click", action))
                                try {
                                    inputData = tokenToKeys.get("DATA");
                                } catch (NullPointerException ignored) {
                                }
                            SeleniumActions.performAction(tokenToKeys.get("AIN").trim().replace(" .", ".").replace(" ?", "?"), action, inputData);
                        } else {
                            try {
                                inputData = tokenToKeys.get("DATA");
                            } catch (NullPointerException ignored) {
                            }
                            SeleniumActions.performAction(tokenToKeys.get("AIN").trim().replace(" .", ".").replace(" ?", "?"), "verify", inputData);
                        }
                    }

                    CreateFiles.createFeatureFile(testcaseList.get(0).getFeatureName(), content);


//
//                String action = tokenToKeys.get("A");
//                switch (action) {
//                    case "open":
//                        Actions.initialiseBrowser(tokenToKeys.get("AIN"));
//                        break;
//
//                    case "load":
//                        Actions.openWebsite(tokenToKeys.get("AD"));
//                        break;
//                }
                }
                SeleniumActions.close();
            }
            System.out.println(LocatorPOJO.getFeatures());
            CreateFiles.createLocatorFile();
            System.out.println();
            CodeGeneratorRunner.run();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}