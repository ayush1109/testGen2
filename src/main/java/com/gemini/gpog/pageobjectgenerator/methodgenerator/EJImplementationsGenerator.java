package com.gemini.gpog.pageobjectgenerator.methodgenerator;//package pageobjectgenerator.methodgenerator;
//
//import annotation.LocatorType;
//import framework.*;
//import japa.parser.ASTHelper;
//import japa.parser.ast.CompilationUnit;
//import japa.parser.ast.body.FieldDeclaration;
//import japa.parser.ast.body.TypeDeclaration;
//import japa.parser.ast.body.VariableDeclarator;
//import japa.parser.ast.body.VariableDeclaratorId;
//import japa.parser.ast.stmt.BlockStmt;
//import logger.EnableSlf4jLogging;
//import logger.LoggerUtils;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.htmlunit.corejs.javascript.ast.VariableDeclaration;
//import pageobjectgenerator.LocatorsModel;
//import pageobjectgenerator.Settings;
//import pageobjectgenerator.stepdefinitiongenerator.UtilsStepDefinitionCodeGenerator;
//import reporting.EnableGemReporting;
//import reporting.EnableSerenityReporting;
//import reporting.Reporting;
//import serializer.LocatorSerializer;
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//import static framework.HelperFunctions.readProperties;
//
//public class EJImplementationsGenerator implements GenerateImplementations{
//
//
//    @Override
//    public void generateImplementations() {
//        String framework = LocatorsModel.getFramework();
//        String reporting = LocatorsModel.getReporting();
//        String logging = LocatorsModel.getLogging();
//        Framework iFramework = null;
//        LoggerUtils iLoggerUtils = null;
//        Reporting iReporting = null;
//
//        try {
//            if (framework == null) framework = readProperties("Framework");
//            if (reporting == null) reporting = readProperties("ReportingType");
//            if (logging == null) logging = readProperties("Logger");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        if (StringUtils.equalsIgnoreCase("Serenity", framework)) {
//            iFramework = new SerenityMethodCodeGenerator();
//        } else if (StringUtils.equalsIgnoreCase("Selenium", framework)) {
////            iFramework = new SeleniumMethodCodeGenerator();
//        } else if (StringUtils.equalsIgnoreCase("EJ", framework)) {
//            iFramework = new EJMethodCodeGenerator();
//        }
//        if (StringUtils.equalsIgnoreCase("JLC", logging)) {
////            iLoggerUtils = new EnableJLCLogging();
//        } else if (StringUtils.equalsIgnoreCase("SLF4J", logging)) {
//            iLoggerUtils = new EnableSlf4jLogging();
//        }
//        if (StringUtils.equalsIgnoreCase("Gem", reporting)) {
//            iReporting = new EnableGemReporting();
//        } else if (StringUtils.equalsIgnoreCase("Serenity", reporting)) {
//            iReporting = new EnableSerenityReporting();
//        }
//
//        ClassLoader classLoader = GeneralImplementationsGenerator.class.getClassLoader();
//        List<String> locatorsList = LocatorsModel.getLocatorsList();
//        for (String locator: locatorsList
//        ) {
//            try {
//                Class<?> aClass = classLoader.loadClass("frontend.pageobjects." + locator + "PageObjects");
//                Field[] fields = aClass.getFields();
//                CompilationUnit c = HelperFunctions.createEnhancedCompilationUnit("frontend.pages", "EJ", locator);
//                HelperFunctions.setTypeDeclaration(c, StringUtils.replace(aClass.getSimpleName(), "Objects", ""));
//                HelperFunctions.createConstructor(c, StringUtils.replace(aClass.getSimpleName(), "Objects", ""));
//
//                HelperFunctions.setStepDefinitionVariable(c, "Logger", "LOGGER", StringUtils.replace(aClass.getSimpleName(), "Objects", ""));
//
//                for (Field field : fields) {
//                    String locatorType = "";
//                    String locators = "";
//
//                    Annotation[] ann = field.getDeclaredAnnotations();
//                    for (Annotation an : ann) {
//
//                        if (field.isAnnotationPresent(LocatorType.class)) {
//                            locatorType = LocatorSerializer.getSerializedKey(field);
//                        }
//                    }
//
//                    ////////////////////////////////////
//
//                    if (StringUtils.contains(locatorType, "input")) {
//                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodTypeSetter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateInputTypeAndEnter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateInputTypeAndTab(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateTypeGetter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateInputClear(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyAttributeValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateInputVerifyClear(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyText (locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                    }
//                    if (StringUtils.contains(locatorType, "div") || StringUtils.contains(locatorType, "span")) {
//                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyAttributeValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyText (locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                    }
//                    if (StringUtils.equalsIgnoreCase(locatorType, "button")) {
//                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateRightClick(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodIsDisabled(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyText (locator, c, field, iLoggerUtils, iReporting);
//                    }
//                    if (StringUtils.equalsIgnoreCase(locatorType, "checkbox")) {
//                        iFramework.generateMethodSelect(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifySelected(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodIsNotSelected(locator, c, field, iLoggerUtils,iReporting);
//                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils,iReporting);
//                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyText (locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                    }
//                    if (StringUtils.equalsIgnoreCase(locatorType, "a")) {
//                        iFramework.generateMethodNavigateBack(c,iLoggerUtils);// Radio and CheckBox and Normal Click Operation
//                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils,iReporting);
//                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils,iReporting);
//                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyText (locator, c, field, iLoggerUtils, iReporting);
//                    }
//                    if (StringUtils.equalsIgnoreCase(locatorType, "dropdown")) {
//                        iFramework.generateDropdownSelect(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateDeselects(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateTypeGetter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyText (locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                    }
//                    if (StringUtils.equalsIgnoreCase(locatorType, "image")) {
//                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementUpload(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifySelected(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodIsImage(locator, c, field, iLoggerUtils, iReporting);
//                    }
//                    if (StringUtils.equalsIgnoreCase(locatorType, "table")) {
//                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodGetRowValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodGetColValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodGetAllValuesFromTable(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodIsColumnNamePresent(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                    }
//                    if (StringUtils.equalsIgnoreCase(locatorType, "file"))
//                    {
//                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementUpload(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifySelected(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
//                    }
//                    if (StringUtils.equalsIgnoreCase(locatorType, "radio button")) {
//                        iFramework.generateMethodSelect(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifySelected(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementVerifyText (locator, c, field, iLoggerUtils, iReporting);
//                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
//                    }
//
//                    if (!StringUtils.equalsIgnoreCase(field.getName(), "driver")) {
//                        iFramework.generateMethodForVisibility(locator, c, field, iLoggerUtils, iReporting);
//                    }
//
//
//
//                    ///////////////////////////////////
//
//                    UtilsStepDefinitionCodeGenerator.savePageObjectsOnFileSystem(Settings.EJ_IMPLEMENTATION_DIR,
//                            StringUtils.replace(aClass.getSimpleName(), "Objects", ""), c, false);
//
//                    HelperFunctions.correctLoggerStatement(StringUtils.replace(aClass.getSimpleName(), "Objects", ""));
//                }
//            } catch (ClassNotFoundException | IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
