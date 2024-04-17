package com.gemini;//package org.example;//package org.example;


import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import com.gemini.gpog.pageobjectgenerator.CodeGeneratorRunner;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.*;

import static com.gemini.Utils.featureFileNames;
import static com.gemini.Utils.readProperties;

public class CustomPOSTagger {
    static String modelFile = "";
    static String pageName = "";

    public static void main(String[] args) {
        try {

            HashMap<String, LinkedHashMap<Integer, List<String>>> data = ExcelUtilities.read(readProperties("filePath"));
            List<FeaturePOJO> featurePOJOList = ExcelUtilities.getTestCases(data);
            List<String> stepsGPOG = new ArrayList<>();
            String step = "";
            String stepToTrain = "";

            for (FeaturePOJO feature: featurePOJOList
                 ) {
                StringBuilder content = new StringBuilder();
                if (StringUtils.equalsIgnoreCase(readProperties("existingAutomation"), "true")) {
                    String featureFilePath = readProperties("projectPath") + "/src/test/resources/features/" + feature.getFeatureName() + ".feature";
                    File featureFile = new File(featureFilePath);
                    if (featureFile.exists()) {
                        content = new StringBuilder(FileUtils.readFileToString(featureFile));
                    } else {
                        content.append("Feature: ").append(feature.getFeatureName());
                        content.append("\n\n  Background:\n    Given user is on homepage");
                    }
                } else {
                    content.append("Feature: ").append(feature.getFeatureName());
                    content.append("\n\n  Background:\n    Given user is on homepage");
                }


                for (Testcase testcase : feature.getTestcase()
                ) {

                    boolean customFailed = false;
                    boolean newScenarioFound = false;
                    List<TokenWithTag> result;
                    HashMap<String, String> tokenToKeys = new HashMap<>();
                    SeleniumActions.openBrowser();

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
                        boolean isComposite = false;
                        String keyword = sampleString.split("----")[1];

                        if (StringUtils.containsIgnoreCase(sampleString, "\"")) {
                            isComposite = true;
                            step = sampleString.split("----")[0];

                            File destinationDirectoryImp = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\gemini\\implementation");

                            File destinationDirectoryStep = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\gemini\\stepdefinition");
                            File destinationDirectoryLocator = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\gemini\\locator");
                            File checkingDirectory = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\gemini\\implementation");
                            String finalStep = step;
                            if (!checkingDirectory.isDirectory()) {
                                File sourceDirectoryImp = new File(readProperties("projectPath") + File.separator + "src\\test\\java\\com\\gemini\\implementation");
                                File sourceDirectoryStep = new File(readProperties("projectPath") + File.separator + "src\\test\\java\\com\\gemini\\stepdefinition");
                                File sourceDirectoryLocator = new File(readProperties("projectPath") + File.separator + "src\\test\\java\\com\\gemini\\locator");
                                FileUtils.copyDirectory(sourceDirectoryImp, destinationDirectoryImp);
                                FileUtils.copyDirectory(sourceDirectoryStep, destinationDirectoryStep);
                                FileUtils.copyDirectory(sourceDirectoryLocator, destinationDirectoryLocator);

                                Stream.of(Objects.requireNonNull(new File("src/main/java/com/gemini/locator").listFiles()))
                                        .filter(file -> !file.isDirectory())
                                        .forEach(file -> {
                                            Utils.compileJavaFile(System.getProperty("user.dir") + File.separator + file);
                                            try {
                                                String fileName = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                                                        + File.separator + "java" + File.separator + "com/gemini/locator/" + StringUtils.capitalize(file.getName().replace(".java", ""));
                                                String tarDir = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes"
                                                        + File.separator + "com" + File.separator + "gemini" + File.separator + "locator" + File.separator + StringUtils.capitalize(file.getName().replace("java", "")) + "class";
                                                Utils.shiftClassFileToTarget(fileName, tarDir);
                                                Utils.deleteClassFiles(fileName);
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        });

                                Stream.of(Objects.requireNonNull(new File("src/main/java/com/gemini/implementation").listFiles()))
                                        .filter(file -> !file.isDirectory())
                                        .forEach(file -> {
                                            Utils.compileJavaFile(System.getProperty("user.dir") + File.separator + file);
                                            try {
                                                String fileName = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                                                        + File.separator + "java" + File.separator + "com/gemini/implementation/" + StringUtils.capitalize(file.getName().replace(".java", ""));
                                                String tarDir = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes"
                                                        + File.separator + "com" + File.separator + "gemini" + File.separator + "implementation" + File.separator + StringUtils.capitalize(file.getName().replace("java", "")) + "class";
                                                Utils.shiftClassFileToTarget(fileName, tarDir);
                                                Utils.deleteClassFiles(fileName);
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        });

                                Stream.of(Objects.requireNonNull(new File("src/main/java/com/gemini/stepdefinition").listFiles()))
                                        .filter(file -> !file.isDirectory())
                                        .forEach(file -> {
                                            Utils.compileJavaFile(System.getProperty("user.dir") + File.separator + file);
                                            try {
                                                String fileName = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                                                        + File.separator + "java" + File.separator + "com/gemini/stepdefinition/" + StringUtils.capitalize(file.getName().replace(".java", ""));
                                                String tarDir = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes"
                                                        + File.separator + "com" + File.separator + "gemini" + File.separator + "stepdefinition" + File.separator + StringUtils.capitalize(file.getName().replace("java", "")) + "class";
                                                Utils.shiftClassFileToTarget(fileName, tarDir);
                                                Utils.deleteClassFiles(fileName);
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        });
                            }
                            Stream.of(Objects.requireNonNull(new File("src/main/java/com/gemini/stepdefinition").listFiles()))
                                    .filter(file -> !file.isDirectory())
                                    .map(File::getName)
                                    .filter(fileName -> StringUtils.containsIgnoreCase(fileName, "composite"))
                                    .forEach(file -> {
                                        File file1 = new File("src/main/java/com/gemini/stepdefinition/" + file);
                                        try {
//                                                        List<TypeDeclaration> methodDeclarations = cu.getTypes();
//                                                        Object methodDeclarations = cu.getData();

                                            ASTParser parser = ASTParser.newParser(AST.JLS8);
                                            parser.setKind(ASTParser.K_COMPILATION_UNIT);
                                            parser.setResolveBindings(true); // Enable binding resolution
                                            parser.setBindingsRecovery(true); // Enable binding recovery
                                            parser.setEnvironment( // apply classpath
                                                    new String[]{System.getProperty("user.dir") + "\\target"}, //
                                                    null, null, true);
                                            parser.setUnitName("unitName");
                                            parser.setSource(FileUtils.readFileToString(file1).toCharArray());
                                            CompilationUnit cu = (CompilationUnit) parser.createAST(null);
                                            cu.accept(new ASTVisitor() {
                                                @Override
                                                public boolean visit(MethodDeclaration node) {
                                                    String astep = finalStep;
                                                    astep = Utils.replaceStringsInDoubleQuotes(astep);
                                                    if (node.toString().contains(astep.replace("composite", "").trim())) {
                                                        try {
                                                            ArrayList<String> a = new ArrayList<>();
                                                            int countOfParameters = 0;
                                                            java.util.regex.Pattern p = java.util.regex.Pattern.compile("\"");
                                                            Matcher m = p.matcher(finalStep);
                                                            String stepNew = finalStep;
                                                            while (m.find()) {
                                                                countOfParameters++;
                                                            }
                                                            for (int j = 0; j < countOfParameters / 2; j++) {
                                                                a.add(stepNew.split("\"")[1].split("\"")[0]);
                                                                stepNew = stepNew.replaceFirst("\"", "").replaceFirst("\"", "");
                                                            }
                                                            Utils.executeMethod(node, a);
                                                        } catch (Exception e) {
                                                            e.getCause();
                                                            e.printStackTrace();
                                                            throw new RuntimeException(e);
                                                        }
                                                    }
                                                    System.out.println("Function: " + node.getName().getFullyQualifiedName());
                                                    return super.visit(node);
                                                }
                                            });
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                        } else {
//                    if (customFailed) {
                            if ((sampleString.contains("verifies") || sampleString.contains("verify") || sampleString.contains("should")) && (keyword.equals("Then"))) {
                                modelFile = "custom-pos-model-temp-verify.bin";
                            } else if (StringUtils.contains(sampleString, "enter") || StringUtils.contains(sampleString, "input") || StringUtils.contains(sampleString, "type") || (StringUtils.contains(sampleString, "write") || StringUtils.contains(sampleString, "enters")))
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

                                    if (customFailed) {
                                        Scanner sc = new Scanner(System.in);
                                        System.out.println("GemGen could not understand \"" + sampleString + "\". Please enter statement to write in feature file");
                                        step = sc.nextLine();
                                        System.out.println("GemGen could not understand \"" + sampleString + "\". Please enter statement to teach our model");
                                        stepToTrain = sc.nextLine();
                                        boolean fileCreated = Utils.createTrainingFile(stepToTrain); //text file to create
                                        TrainModel.trainModel("src/main/java/com/gemini/training_data_custom.txt", "src/main/resources/models/custom-pos-model-custom.bin");
                                        modelFile = "custom-pos-model-custom.bin";
                                        result = Utils.testModel(step, modelFile);
                                        tokenToKeys = MapTokenToKeys.map(result);
                                    }
//                        step = GenerateStep.generate(tokenToKeys, keyword);
                                }
//                    }
                        }
//                    stepsGPOG.add(step.replace("composite", ""));
                        if (StringUtils.equalsIgnoreCase(readProperties("existingAutomation"), "true")) {
                            if (newScenarioFound) {
                                if (step.contains("When") || step.contains("Then")) {
                                    content.append("\n    ").append(step);
                                } else
                                    content.append("\n    ").append(keyword).append(" ").append(step);
                            }
                        } else {
                            if (step.contains("When") || step.contains("Then")) {
                                content.append("\n    ").append(step);
                            } else
                                content.append("\n    ").append(keyword).append(" ").append(step);
                        }
                        if (!isComposite) {
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
                                SeleniumActions.performAction(tokenToKeys.get("AIN").trim().replace(" .", ".").replace(" ?", "?"), action, inputData, testcase.getScenarioName());
                            } else {
                                try {
                                    inputData = tokenToKeys.get("DATA");
                                } catch (NullPointerException ignored) {
                                }
                                SeleniumActions.performAction(tokenToKeys.get("AIN").trim().replace(" .", ".").replace(" ?", "?"), "verify", inputData, testcase.getScenarioName());
                            }
                        }

                        CreateFiles.createFeatureFile(feature.getFeatureName(), content);
                        featureFileNames.add(feature.getFeatureName());

                    }
                    SeleniumActions.close();
                    CreateFiles.createLocatorFile();
                }
            }

            System.out.println(LocatorPOJO.getFeatures());
            CreateFiles.createFailedList();
            CodeGeneratorRunner.run();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}