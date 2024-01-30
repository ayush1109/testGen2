package com.gemini.gpog.pageobjectgenerator.stepdefinitiongenerator;//package pageobjectgenerator.stepdefinitiongenerator;
//
//import annotation.LocatorType;
//import framework.Framework;
//import framework.HelperFunctions;
////import framework.SeleniumMethodCodeGenerator;
//import framework.SerenityMethodCodeGenerator;
//import japa.parser.ast.CompilationUnit;
//import logger.EnableSlf4jLogging;
//import logger.LoggerUtils;
//import org.apache.commons.lang3.StringUtils;
//import pageobjectgenerator.LocatorsModel;
//import pageobjectgenerator.methodgenerator.GeneralImplementationsGenerator;
//import reporting.EnableGemReporting;
//import reporting.EnableSerenityReporting;
//import reporting.Reporting;
//import serializer.LocatorSerializer;
//
//import java.io.IOException;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.util.List;
//
//import static framework.HelperFunctions.readProperties;
//
//public class EJStepDefinitionGenerator implements GenerateStepDefinitions{
//
//    @Override
//    public void generateStepDefinitions() {
//        ClassLoader classLoader = GeneralImplementationsGenerator.class.getClassLoader();
//        List<String> locatorsList = LocatorsModel.getLocatorsList();
//        String logging = LocatorsModel.getLogging();
//
//        try {
//            if (logging == null) logging = readProperties("Logger");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        LoggerUtils iLoggerUtils = null;
//
//        if (StringUtils.equalsIgnoreCase("JLC", logging)) {
////            iLoggerUtils = new EnableJLCLogging();
//        } else if (StringUtils.equalsIgnoreCase("SLF4J", logging)) {
//            iLoggerUtils = new EnableSlf4jLogging();
//        }
//        for (String locator: locatorsList
//        ) {
//            try {
//                Class aClass = classLoader.loadClass("frontend.pageobjects." + locator + "PageObjects");
//                Field[] fields = aClass.getFields();
//                CompilationUnit c = HelperFunctions.createEnhancedCompilationUnitStepDefinitions("stepdefinition", "EJStepDefinition", locator);
//                HelperFunctions.setTypeDeclaration(c, aClass.getSimpleName().replace("PageObjects", "") + "StepDefinitions");
//
//                for (Field field : fields) {
//                    String locatorType = "";
//
//                    Annotation[] ann = field.getDeclaredAnnotations();
//                    for (Annotation an : ann) {
//                        if (field.isAnnotationPresent(LocatorType.class)) {
//                            locatorType = LocatorSerializer.getSerializedKey(field);
//                        }
//                    }
//
//                        if (StringUtils.equalsIgnoreCase(locatorType, "button")) {
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionRightClickElement(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator, locatorType);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionIsDisabled(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                        }
//                        if (StringUtils.equalsIgnoreCase(locatorType, "checkbox")) {
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodForSelect(c, field, locatorType, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenSelected(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNotChecked(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator, locatorType);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                        }
//                    if (StringUtils.equalsIgnoreCase(locatorType, "input")) {
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionClear(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator, locatorType);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTextGetter(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTypeTextAndEnter(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTypeTextAndTab(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.generateElementBasicFunctions(c, field, locatorType, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator, locatorType);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                        UtilsStepDefinitionCodeGenerator.generateElementValueVerify(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeVerification(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionVerifyClear(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator, locatorType);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                    }
//                        if (StringUtils.equalsIgnoreCase(locatorType, "image")) {
//                            UtilsStepDefinitionCodeGenerator.setClickStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator, locatorType);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenSelected(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionIsImage(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                        }
//                        if (StringUtils.equalsIgnoreCase(locatorType, "a")) {
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator, locatorType);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                        }
//                        if (StringUtils.equalsIgnoreCase(locatorType, "radio button")) {
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodForSelect(c, field, locatorType, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenSelected(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator, locatorType);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                        }
//                        if (StringUtils.equalsIgnoreCase(locatorType, "dropdown")) {
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator, locatorType);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTextGetter(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);// Radio and CheckBox and Normal Click Operation
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodDeselects(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator, locatorType);// Radio and CheckBox and Normal Click Operation
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                        }
//                        if (StringUtils.equalsIgnoreCase(locatorType, "div") || StringUtils.equalsIgnoreCase(locatorType, "span")) {
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeVerification(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                        }
//                        if (StringUtils.equalsIgnoreCase(locatorType, "table")) {
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionValidateColumnNameOfTable(c, field, locator, iLoggerUtils);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetColumnValue(c, field, locator, iLoggerUtils);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetAllCellsValue(c, field, locator, iLoggerUtils);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionChangeFocus(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locatorType, locator);
//                            UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetRowValue(c, field, aClass.getSimpleName().replace("PageObjects", ""), iLoggerUtils, locator);
//
//                        }
//                    UtilsStepDefinitionCodeGenerator.savePageObjectsOnFileSystem(pageobjectgenerator.Settings.STEP_DEFINITION_PO_DIR,
//                            aClass.getSimpleName().replace("PageObjects", "") + "StepDefinitions", c, true);
//                }
//            } catch (ClassNotFoundException | IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
