package com.gemini.gpog.pageobjectgenerator.stepdefinitiongenerator;

import com.gemini.gpog.framework.HelperFunctions;
import com.gemini.gpog.pageobjectgenerator.LocatorsModel;
import com.gemini.gpog.pageobjectgenerator.Settings;
import japa.parser.ast.CompilationUnit;

import java.io.IOException;
import java.util.List;

public class EJStepsObjectSubs {

    public static void generateStepsObjectSubs() {
        List<String> locatorsList = LocatorsModel.getLocatorsList();

        for (String locator: locatorsList
        ) {
            try {
                CompilationUnit c = HelperFunctions.createEnhancedCompilationUnitStepDefinitions("stepdefinition", "objectSubs", locator);
                HelperFunctions.setTypeDeclaration(c, "StepsObjectsSub");
                UtilsStepDefinitionCodeGenerator.setStepDefinitionVariable(c, locator + "Page", locator);

                UtilsStepDefinitionCodeGenerator.savePageObjectsOnFileSystem(Settings.STEP_DEFINITION_PO_DIR,
                        "StepsObjectsSub", c, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
