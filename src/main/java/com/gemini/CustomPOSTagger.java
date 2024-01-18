package com.gemini;//package org.example;//package org.example;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import fetcher.DOMFetcher;
import fetcher.Healer;
import fetcher.NodeInfo;
import fetcher.NodeManager;
import opennlp.tools.postag.*;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.*;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static fetcher.Utilities.parseTree;

public class CustomPOSTagger {
    static String modelFile = "";
    static String pageName = "";

    public static void main(String[] args) {
        try {

            Map<Integer, List<String>> data = ExcelUtilities.readBySheetNumber("C:\\Users\\ayush.garg\\Downloads\\gmail_scenario.xlsx", 0);
            List<Testcase> testcaseList = ExcelUtilities.getTestCases(data);
            List<String> steps = List.of("navigate to jewel", "switch to apply button");
            List<String> stepsGPOG = new ArrayList<>();
            StringBuilder content = new StringBuilder();
            content.append("Feature: ").append(testcaseList.get(0).getFeatureName());
            for (Testcase testcase : testcaseList
            ) {
                SeleniumActions.open();
                ArrayList<HashMap<String, String>> scenarioLocators = new ArrayList<>();
                content.append("\n\n  Scenario: ").append(testcase.getScenarioName());

                for (String sampleString : testcase.getSteps()
                ) {
                    String keyword = testcase.getSteps().indexOf(sampleString) % 2 != 0 ? "Then" : "When";
                    CreateFeatureFile.create(testcaseList.get(0).getFeatureName(), content);
                    if (sampleString.startsWith("log") || sampleString.startsWith("launch") || sampleString.startsWith("login") || sampleString.startsWith("navigate"))
                        modelFile = "custom-pos-model-url.bin";
                    else if (StringUtils.contains(sampleString, "enter") || StringUtils.contains(sampleString, "input") || StringUtils.contains(sampleString, "type"))
                        modelFile = "custom-pos-model-input.bin";
                    else modelFile = "custom-pos-model-all.bin";

                    //  Test the model
                    testModel(sampleString, modelFile);

                    List<TokenWithTag> result = testModel(sampleString, modelFile);
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
//                        By element = findXpath(tokenToKeys.get("AIN"));
                        SeleniumActions.performAction(tokenToKeys.get("AIN"), action, inputData);
                    }


                    stepsGPOG.add(GenerateStep.generate(tokenToKeys, keyword));
                    content.append("\n    ").append(GenerateStep.generate(tokenToKeys, keyword));


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
            stepsGPOG.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readSentencesFromFile(String filePath) throws IOException {
        List<String> sentences = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sentences.add(line);
            }
        }
        return sentences;
    }

    private static void createTrainingFile(List<String> sentences, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String sentence : sentences) {
                writer.write(sentence);
                writer.newLine();
            }
        }
    }

    private static List<TokenWithTag> testModel(String testSentence, String modelFile) throws IOException {
        List<TokenWithTag> result = new ArrayList<>();
        try (InputStream tokenModelIn = new FileInputStream("src/main/resources/trainingmodels/en-token.bin");
             InputStream posModelIn = new FileInputStream("src/main/resources/models/" + modelFile)) {

            TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
            Tokenizer tokenizer = new TokenizerME(tokenModel);
            String[] tokens = tokenizer.tokenize(testSentence);

            POSModel posModel = new POSModel(posModelIn);
            POSTaggerME posTagger = new POSTaggerME(posModel);

            String[] tags = posTagger.tag(tokens);
            double[] probs = posTagger.probs();

            for (int i = 0; i < tokens.length; i++) {
                TokenWithTag tokenWithTag = new TokenWithTag(tokens[i], tags[i], probs[i]);
                result.add(tokenWithTag);
            }
        }
        return result;
    }
}






