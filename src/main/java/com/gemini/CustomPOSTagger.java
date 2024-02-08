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

            LinkedHashMap<Integer, List<String>> data = ExcelUtilities.readBySheetNumber(Utils.readProperties("filePath"), 1);
            List<Testcase> testcaseList = ExcelUtilities.getTestCases(data);
            List<String> stepsGPOG = new ArrayList<>();
            StringBuilder content = new StringBuilder();
            content.append("Feature: ").append(Testcase.getFeatureName());
            for (Testcase testcase : testcaseList
            ) {
                if(testcase.getScenarioName().contains("login")) SeleniumActions.openForLogin();
                else SeleniumActions.open();
                content.append("\n\n  Scenario: ").append(testcase.getScenarioName());

                for (String sampleString : testcase.getSteps()
                ) {
                    String keyword = sampleString.split("----")[1];
                    if ((sampleString.contains("verifies") || sampleString.contains("verify") || sampleString.contains("should")) && (keyword.equals("Then"))) {
                        modelFile = "custom-pos-model-temp-verify.bin";
                    } else if (StringUtils.contains(sampleString, "enter") || StringUtils.contains(sampleString, "input") || StringUtils.contains(sampleString, "type") || (StringUtils.contains(sampleString, "write")))
                        modelFile = "custom-pos-model-input.bin";
                    else modelFile = "custom-pos-model-temp.bin";

                    //  Test the model

                    List<TokenWithTag> result = Utils.testModel(sampleString.split("----")[0], modelFile);
                    HashMap<String, String> tokenToKeys;
                    // Display the result
                    System.out.println("Token\t:\tTag\t:\tProbability\n---------------------------------------------");
                    for (TokenWithTag tokenWithTag : result) {
                        System.out.println(tokenWithTag.token + "\t:\t" + tokenWithTag.tag + "\t:\t" + tokenWithTag.probability);
                    }

                    tokenToKeys = MapTokenToKeys.map(result);
                    stepsGPOG.add(GenerateStep.generate(tokenToKeys, keyword));
                    content.append("\n    ").append(GenerateStep.generate(tokenToKeys, keyword));
                    String inputData = null;
                    if (keyword.equals("When")) {
                        String action = GenerateStep.identifyAction(tokenToKeys);
                        if(action == null) action = tokenToKeys.get("A");
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
            throw new RuntimeException(e);
        }
    }
}