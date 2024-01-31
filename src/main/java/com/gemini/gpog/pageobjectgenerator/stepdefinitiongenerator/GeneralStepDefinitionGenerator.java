package com.gemini.gpog.pageobjectgenerator.stepdefinitiongenerator;

import com.gemini.gpog.annotation.LocatorType;
import com.gemini.gpog.framework.HelperFunctions;
import com.gemini.gpog.logger.EnableSlf4jLogging;
import com.gemini.gpog.logger.LoggerUtils;
import com.gemini.gpog.pageobjectgenerator.LocatorsModel;
import com.gemini.gpog.pageobjectgenerator.Settings;
import com.gemini.gpog.pageobjectgenerator.featuregenerator.FeatureCodeGenerator;
import com.gemini.gpog.pageobjectgenerator.methodgenerator.GeneralImplementationsGenerator;
import com.gemini.gpog.serializer.LocatorSerializer;
import japa.parser.ast.CompilationUnit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import static com.gemini.Utils.readProperties;

public class GeneralStepDefinitionGenerator implements GenerateStepDefinitions {

    /**
     * @author - Sajith, Hem, Ayush, Jasleen, Priyanshu, Rahul Tagra and Siddhanshi
     * @version - 1.0
     * @since - 5/16/2023
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralStepDefinitionGenerator.class);

    public static void main(String[] args) {
    }

    @Override
    public void generateStepDefinitions() {

        ClassLoader classLoader = GeneralImplementationsGenerator.class.getClassLoader();
        List<String> locatorsList = LocatorsModel.getLocatorsList();
        String logging = LocatorsModel.getLogging();
        String env = LocatorsModel.getEnv();

        try {
            if (logging == null) logging = readProperties("Logger");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoggerUtils iLoggerUtils = null;

        if (StringUtils.equalsIgnoreCase("JLC", logging)) {
//            iLoggerUtils = new EnableJLCLogging();
        } else if (StringUtils.equalsIgnoreCase("SLF4J", logging)) {
            iLoggerUtils = new EnableSlf4jLogging();
        }
        for (String locator : locatorsList
        ) {
            try {
                Class aClass = null;
                CompilationUnit c = null;
                if (StringUtils.equalsIgnoreCase("Gemini", env))
                    aClass = classLoader.loadClass("com.gemini.locator." + locator);
                else if (StringUtils.equalsIgnoreCase("EJ", env))
                    aClass = classLoader.loadClass("frontend.pageobjects." + locator + "PageObjects");
                Field[] fields = aClass.getFields();
                FeatureCodeGenerator.className = aClass.getSimpleName();
                if(StringUtils.equalsIgnoreCase("EJ", LocatorsModel.getEnv())) {
                    c = HelperFunctions.createEnhancedCompilationUnitStepDefinitions("stepdefinition", "EJStepDefinition", locator);
                } else {
                    c = HelperFunctions.createEnhancedCompilationUnitStepDefinitions("stepdefinition", "StepDefinition", locator);

                }
                HelperFunctions.setTypeDeclarationStepDefinition(c, aClass.getSimpleName() + "StepDefinition");
                if (StringUtils.equalsIgnoreCase("Gemini", env))
                    UtilsStepDefinitionCodeGenerator.setStepDefinitionVariable(c, locator + "Implementation", locator);
                else if (StringUtils.equalsIgnoreCase("EJ", env))
                    UtilsStepDefinitionCodeGenerator.setStepDefinitionVariable(c, locator + "Page", locator);


                for (Field field : fields) {
                        String locatorType = "";

                        Annotation[] ann = field.getDeclaredAnnotations();
                        for (Annotation an : ann) {
                            if (field.isAnnotationPresent(LocatorType.class)) {
                                locatorType = LocatorSerializer.getSerializedKey(field);
                            }
                        }

                        if (StringUtils.equalsIgnoreCase(locatorType, "button")) {
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName(), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionRightClickElement(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), iLoggerUtils, locator, locatorType);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionIsDisabled(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                        }
                        if (StringUtils.equalsIgnoreCase(locatorType, "checkbox")) {
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodForSelect(c, field, locatorType, aClass.getSimpleName(), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName(), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenSelected(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNotChecked(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), iLoggerUtils, locator, locatorType);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                        }
                        if (StringUtils.equalsIgnoreCase(locatorType, "input")) {
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionClear(c, field, aClass.getSimpleName(), iLoggerUtils, locator, locatorType);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTextGetter(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTypeTextAndEnter(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTypeTextAndTab(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.generateElementBasicFunctions(c, field, locatorType, aClass.getSimpleName(), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), iLoggerUtils, locator, locatorType);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.generateElementValueVerify(c, field, true, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeVerification(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionVerifyClear(c, field, aClass.getSimpleName(), iLoggerUtils, locator, locatorType);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                        }
                        if (StringUtils.equalsIgnoreCase(locatorType, "image")) {
                            UtilsStepDefinitionCodeGenerator.setClickStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), iLoggerUtils, locator, locatorType);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName(), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenSelected(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionIsImage(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
                        }
                        if (StringUtils.equalsIgnoreCase(locatorType, "a")) {
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName(), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), iLoggerUtils, locator, locatorType);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                        }
                        if (StringUtils.equalsIgnoreCase(locatorType, "radio button")) {
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodForSelect(c, field, locatorType, aClass.getSimpleName(), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenSelected(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), iLoggerUtils, locator, locatorType);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                        }
                        if (StringUtils.equalsIgnoreCase(locatorType, "dropdown")) {
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), iLoggerUtils, locator, locatorType);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTextGetter(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName(), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodDeselects(c, field, aClass.getSimpleName(), iLoggerUtils, locator, locatorType);// Radio and CheckBox and Normal Click Operation
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                        }
                        if (StringUtils.equalsIgnoreCase(locatorType, "div") || StringUtils.equalsIgnoreCase(locatorType, "span")) {
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeVerification(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                        }
                        if (StringUtils.equalsIgnoreCase(locatorType, "table")) {
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionValidateColumnNameOfTable(c, field, locator, iLoggerUtils);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetColumnValue(c, field, locator, iLoggerUtils);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetAllCellsValue(c, field, locator, iLoggerUtils);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName(), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), iLoggerUtils, locatorType, locator);
                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetRowValue(c, field, aClass.getSimpleName(), iLoggerUtils, locator);

                        }
                        UtilsStepDefinitionCodeGenerator.savePageObjectsOnFileSystem(Settings.STEP_DEFINITION_PO_DIR,
                                aClass.getSimpleName() + "StepDefinition", c, true);
                    }
            } catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //setting before class for driver initialisation
//        UtilsStepDefinitionCodeGenerator.setBeforeClass(c);

}