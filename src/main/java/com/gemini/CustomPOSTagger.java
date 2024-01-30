package com.gemini;//package org.example;//package org.example;


import java.io.*;
import java.util.*;

import com.gemini.gpog.pageobjectgenerator.CodeGeneratorRunner;
import opennlp.tools.postag.*;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.apache.commons.lang3.StringUtils;

public class CustomPOSTagger {
    static String modelFile = "";
    static String pageName = "";

    public static void main(String[] args) {
        try {

            Map<Integer, List<String>> data = ExcelUtilities.read(Utils.readProperties("filePath"));
            List<Testcase> testcaseList = ExcelUtilities.getTestCases(data);
            List<String> stepsGPOG = new ArrayList<>();
            StringBuilder content = new StringBuilder();
            content.append("Feature: ").append(testcaseList.get(0).getFeatureName());
            for (Testcase testcase : testcaseList
            ) {
                SeleniumActions.open();
                content.append("\n\n  Scenario: ").append(testcase.getScenarioName());

                for (String sampleString : testcase.getSteps()
                ) {
                    String keyword = testcase.getSteps().indexOf(sampleString) % 2 != 0 ? "Then" : "When";
                    if (sampleString.startsWith("log") || sampleString.startsWith("launch") || sampleString.startsWith("login") || sampleString.startsWith("navigate"))
                        modelFile = "custom-pos-model-url.bin";
                    if ((sampleString.contains("verifies") || sampleString.contains("verify") || sampleString.contains("should")) && (keyword.equals("Then"))) {
                        modelFile = "custom-pos-model-input-when.bin";
                    } else if (StringUtils.contains(sampleString, "enter") || StringUtils.contains(sampleString, "input") || StringUtils.contains(sampleString, "type"))
                        modelFile = "custom-pos-model-input.bin";
                    else modelFile = "custom-pos-model-all.bin";

                    //  Test the model
                    Utils.testModel(sampleString, modelFile);

                    List<TokenWithTag> result = Utils.testModel(sampleString, modelFile);
                    HashMap<String, String> tokenToKeys;
                    // Display the result
                    System.out.println("Token\t:\tTag\t:\tProbability\n---------------------------------------------");
                    for (TokenWithTag tokenWithTag : result) {
                        System.out.println(tokenWithTag.token + "\t:\t" + tokenWithTag.tag + "\t:\t" + tokenWithTag.probability);
                    }

                    tokenToKeys = MapTokenToKeys.map(result);

                    if (keyword.equals("When")) {
                        String inputData = null;
                        String action = GenerateStep.identifyAction(tokenToKeys);
                        if (!StringUtils.equalsIgnoreCase("click", action))
                            try {
                                inputData = tokenToKeys.get("DATA");
                            } catch (NullPointerException ignored) {
                            }
                        SeleniumActions.performAction(tokenToKeys.get("AIN"), action, inputData);
                    } else {
                        String inputData = null;
                        inputData = tokenToKeys.get("DATA");
                        SeleniumActions.performAction(tokenToKeys.get("AIN"), "verify", inputData);
                    }
                    stepsGPOG.add(GenerateStep.generate(tokenToKeys, keyword));
                    content.append("\n    ").append(GenerateStep.generate(tokenToKeys, keyword));
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
            }
            System.out.println(LocatorPOJO.getFeatures());
            CreateFiles.createLocatorFile();
            SeleniumActions.close();
            CodeGeneratorRunner.run();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}