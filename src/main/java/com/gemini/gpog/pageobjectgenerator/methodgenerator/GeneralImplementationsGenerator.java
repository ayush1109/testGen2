package com.gemini.gpog.pageobjectgenerator.methodgenerator;
//package pageobjectgenerator.methodgenerator;

import com.gemini.gpog.annotation.LocatorType;
import com.gemini.gpog.framework.EJMethodCodeGenerator;
import com.gemini.gpog.framework.Framework;
import com.gemini.gpog.framework.HelperFunctions;
import com.gemini.gpog.framework.SerenityMethodCodeGenerator;
import com.gemini.gpog.logger.EnableSlf4jLogging;
import com.gemini.gpog.logger.LoggerUtils;
import com.gemini.gpog.pageobjectgenerator.LocatorsModel;
import com.gemini.gpog.pageobjectgenerator.Settings;
import com.gemini.gpog.pageobjectgenerator.stepdefinitiongenerator.UtilsStepDefinitionCodeGenerator;
import com.gemini.gpog.reporting.EnableSerenityReporting;
import com.gemini.gpog.reporting.Reporting;
import com.gemini.gpog.serializer.LocatorSerializer;
import japa.parser.ast.CompilationUnit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import static com.gemini.Utils.readProperties;

public class GeneralImplementationsGenerator implements GenerateImplementations {

    /**
     * @author - Sajith, Hem, Ayush, Jasleen, Priyanshu, Rahul Tagra and Siddhanshi
     * @version - 1.0
     * @since - 5/16/2023
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralImplementationsGenerator.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        Class aClass = classLoader.loadClass("locators" + "." + pageobjectgenerator.Settings.LOCATOR_FILE_NAME);
//        generatePageMethods();
    }

    @Override
    public void generateImplementations() {
        String framework = LocatorsModel.getFramework();
        String reporting = LocatorsModel.getReporting();
        String logging = LocatorsModel.getLogging();
        String env = LocatorsModel.getEnv();
        Framework iFramework = null;
        LoggerUtils iLoggerUtils = null;
        Reporting iReporting = null;

        try {
            if (framework == null) framework = readProperties("Framework");
            if (reporting == null) reporting = readProperties("ReportingType");
            if (logging == null) logging = readProperties("Logger");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (StringUtils.equalsIgnoreCase("Serenity", framework)) {
            if (StringUtils.equalsIgnoreCase("Gemini", env))
                iFramework = new SerenityMethodCodeGenerator();
            else if (StringUtils.equalsIgnoreCase("EJ", env))
                iFramework = new EJMethodCodeGenerator();
        } else if (StringUtils.equalsIgnoreCase("Selenium", framework)) {
//            iFramework = new SeleniumMethodCodeGenerator();
        }
        if (StringUtils.equalsIgnoreCase("JLC", logging)) {
//            iLoggerUtils = new EnableJLCLogging();
        } else if (StringUtils.equalsIgnoreCase("SLF4J", logging)) {
            iLoggerUtils = new EnableSlf4jLogging();
        }
         else if (StringUtils.equalsIgnoreCase("Serenity", reporting)) {
            iReporting = new EnableSerenityReporting();
        }
        ClassLoader classLoader = getClass().getClassLoader();
        List<String> locatorsList = LocatorsModel.getLocatorsList();
        for (String locator : locatorsList
        ) {
            try {
                Field[] fields = null;
                CompilationUnit c = null;
                Class<?> aClass = null;
                if (StringUtils.equalsIgnoreCase("Gemini", env)) {
//                    Object newObject = Class.forName("com.gemini.locator." + locator).newInstance();
                    aClass = getClass().getClassLoader().loadClass("com.gemini.locator." + locator);
                    fields = aClass.getFields();
                    c = HelperFunctions.createEnhancedCompilationUnit("implementation", "Method", locator);
                    HelperFunctions.setTypeDeclaration(c, aClass.getSimpleName() + "Implementation");

                    HelperFunctions.setStepDefinitionVariable(c, "LoggerUtils", "loggerUtils", logging);
                    HelperFunctions.setStepDefinitionVariable(c, "Reporting", "reporting", reporting);
                } else if (StringUtils.equalsIgnoreCase("EJ", env)) {
                    aClass = classLoader.loadClass("frontend.pageobjects." + locator + "PageObjects");
                    fields = aClass.getFields();
                    c = HelperFunctions.createEnhancedCompilationUnit("frontend.pages", "EJ", locator);
                    HelperFunctions.setTypeDeclaration(c, StringUtils.replace(aClass.getSimpleName(), "Objects", ""));
                    HelperFunctions.createConstructor(c, StringUtils.replace(aClass.getSimpleName(), "Objects", ""));

                    HelperFunctions.setStepDefinitionVariable(c, "Logger", "LOGGER", StringUtils.replace(aClass.getSimpleName(), "Objects", ""));
//
                } else {
                    System.out.println("env is not correct. Use EJ or Gemini");
                    System.exit(0);
                }

                for (Field field : fields) {
                    String locatorType = "";

                    Annotation[] ann = field.getDeclaredAnnotations();
                    for (Annotation an : ann) {
                        if (field.isAnnotationPresent(LocatorType.class)) {
                            locatorType = LocatorSerializer.getSerializedKey(field);
                        }
                    }
                    if (StringUtils.contains(locatorType, "input")) {
                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodTypeSetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputTypeAndEnter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputTypeAndTab(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateTypeGetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputClear(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyAttributeValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputVerifyClear(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyText(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                    }
                    if (StringUtils.contains(locatorType, "div") || StringUtils.contains(locatorType, "span")) {
                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyAttributeValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyText(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                    }
                    if (StringUtils.equalsIgnoreCase(locatorType, "button")) {
                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateRightClick(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodIsDisabled(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyText(locator, c, field, iLoggerUtils, iReporting);
                    }
                    if (StringUtils.equalsIgnoreCase(locatorType, "checkbox")) {
                        iFramework.generateMethodSelect(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifySelected(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodIsNotSelected(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyText(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                    }
                    if (StringUtils.equalsIgnoreCase(locatorType, "a")) {
                        iFramework.generateMethodNavigateBack(c, iLoggerUtils);// Radio and CheckBox and Normal Click Operation
                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyText(locator, c, field, iLoggerUtils, iReporting);
                    }
                    if (StringUtils.equalsIgnoreCase(locatorType, "dropdown")) {
                        iFramework.generateDropdownSelect(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateDeselects(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateTypeGetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyText(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                    }
                    if (StringUtils.equalsIgnoreCase(locatorType, "image")) {
                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementUpload(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifySelected(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodIsImage(locator, c, field, iLoggerUtils, iReporting);
                    }
                    if (StringUtils.equalsIgnoreCase(locatorType, "table")) {
                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodGetRowValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodGetColValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodGetAllValuesFromTable(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodIsColumnNamePresent(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                    }
                    if (StringUtils.equalsIgnoreCase(locatorType, "file")) {
                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementUpload(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifySelected(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
                    }
                    if (StringUtils.equalsIgnoreCase(locatorType, "radio button")) {
                        iFramework.generateMethodSelect(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                        iFramework.generateElementVerifyEnabled(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifySelected(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodAttributeGetter(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateInputVerifyValue(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementHoverOver(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementScrollIntoView(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodClickable(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateMethodChangeFocus(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyTextContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyAttributesContains(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementVerifyText(locator, c, field, iLoggerUtils, iReporting);
                        iFramework.generateElementClick(locator, c, field, iLoggerUtils, iReporting);// Radio and CheckBox and Normal Click Operation
                    }

                    if (!StringUtils.equalsIgnoreCase(field.getName(), "driver")) {
                        iFramework.generateMethodForVisibility(locator, c, field, iLoggerUtils, iReporting);
                    }
                    if (StringUtils.equalsIgnoreCase("Gemini", env))
                        UtilsStepDefinitionCodeGenerator.savePageObjectsOnFileSystem(Settings.IMPLEMENTATION_PO_DIR,
                                aClass.getSimpleName() + "Implementation", c, true);
                    else if (StringUtils.equalsIgnoreCase("EJ", env)) {
                        UtilsStepDefinitionCodeGenerator.savePageObjectsOnFileSystem(Settings.EJ_IMPLEMENTATION_DIR,
                            StringUtils.replace(aClass.getSimpleName(), "Objects", ""), c, false);
                    HelperFunctions.correctLoggerStatement(StringUtils.replace(aClass.getSimpleName(), "Objects", ""));
                    }
                }
            } catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
