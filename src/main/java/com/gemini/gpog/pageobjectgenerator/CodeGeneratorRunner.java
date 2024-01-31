package com.gemini.gpog.pageobjectgenerator;

import com.gemini.LocatorPOJO;
import com.gemini.Utils;
import com.gemini.gpog.pageobjectgenerator.featuregenerator.FeatureCodeGenerator;
import com.gemini.gpog.pageobjectgenerator.methodgenerator.GeneralImplementationsGenerator;
import com.gemini.gpog.pageobjectgenerator.methodgenerator.GenerateImplementations;
import com.gemini.gpog.pageobjectgenerator.methodgenerator.GenericImplementationsGenerator;
import com.gemini.gpog.pageobjectgenerator.stepdefinitiongenerator.GeneralStepDefinitionGenerator;
import com.gemini.gpog.pageobjectgenerator.stepdefinitiongenerator.GenerateStepDefinitions;
import com.gemini.gpog.pageobjectgenerator.stepdefinitiongenerator.GenericStepDefinitionGenerator;
import org.apache.commons.lang3.StringUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.gemini.Utils.readProperties;
//import pageobjectgenerator.methodgenerator.GeneralImplementationsGenerator;

public class CodeGeneratorRunner {

    public static void main(String[] args) {

        //Fetching arguments from command line
        try {
//            LocatorsModel.setLocatorsList(new ArrayList<>(LocatorPOJO.getFeatures().keySet()).stream().map(StringUtils::capitalize).collect(Collectors.toList()));
            LocatorsModel.setLocatorsList(new ArrayList<>(List.of((readProperties("locator").split(",")))));

            LocatorsModel.setEnv(readProperties("env"));

            LocatorsModel.setFramework(readProperties("framework"));

            LocatorsModel.setReporting(readProperties("reporting"));

            LocatorsModel.setLogging(readProperties("logging"));

            GenerateImplementations implementations = new GeneralImplementationsGenerator();
            GenerateStepDefinitions stepDefinitions = new GeneralStepDefinitionGenerator();

            GenericImplementationsGenerator genericImplementations = new GenericImplementationsGenerator();
            GenericStepDefinitionGenerator genericStepDefinitions = new GenericStepDefinitionGenerator();

            implementations.generateImplementations();
            genericImplementations.generateGenericImplementations();

            stepDefinitions.generateStepDefinitions();
            genericStepDefinitions.generateGenericStepMethods();

            Utils.copyGPOGDirectoriesToProject();

            Utils.copyGPOGFilesToProject();

            Utils.writePOMFile();

//            FeatureCodeGenerator.generateFeature();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
