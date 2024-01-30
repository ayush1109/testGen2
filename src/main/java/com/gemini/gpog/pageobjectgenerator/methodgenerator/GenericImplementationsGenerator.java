package com.gemini.gpog.pageobjectgenerator.methodgenerator;

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

import japa.parser.ast.CompilationUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

import static com.gemini.Utils.readProperties;

public class GenericImplementationsGenerator {


    /**
     * @author - Sajith, Hem, Ayush, Jasleen, Priyanshu, Rahul Tagra and Siddhanshi
     * @version - 1.0
     * @since - 5/16/2023
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericImplementationsGenerator.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {

    }

    public void generateGenericImplementations() {

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
            if (StringUtils.equalsIgnoreCase("EJ", env))
                iFramework = new EJMethodCodeGenerator();
            else if (StringUtils.equalsIgnoreCase("Gemini", env))
                iFramework = new SerenityMethodCodeGenerator();
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
        try {
            CompilationUnit c = null;
            if (StringUtils.equalsIgnoreCase("EJ", env)) {
                c = HelperFunctions.createEnhancedCompilationUnit("frontend.pages", "EJ", "Utils");
                HelperFunctions.setTypeDeclaration(c, "UtilsImplementation");
                HelperFunctions.setStepDefinitionVariable(c, "Logger", "LOGGER", "UtilsImplementation");
            } else {
                c = HelperFunctions.createEnhancedCompilationUnit("implementation", "Gemini", "Utils");
                HelperFunctions.setTypeDeclaration(c, "UtilsImplementation");
                HelperFunctions.setStepDefinitionVariable(c, "Reporting", "reporting", reporting);
                HelperFunctions.setStepDefinitionVariable(c, "LoggerUtils", "loggerUtils", logging);
            }

            iFramework.generateGetUrl(c, iLoggerUtils);
            iFramework.generateVerifyUrl(c, iLoggerUtils);
            iFramework.generateMethodGetTitle(c, iLoggerUtils);
            iFramework.generateMethodVerifyTitle(c, iLoggerUtils);
            iFramework.generateNavigateTo(c, iLoggerUtils);
            iFramework.generateNavigateForward(c, iLoggerUtils);
            iFramework.generateMethodNavigateBack(c, iLoggerUtils);
            iFramework.generateSwitchToActiveElement(c, iLoggerUtils);
            iFramework.generateSwitchToDefaultContent(c, iLoggerUtils);
            iFramework.generateSwitchToParentFrame(c, iLoggerUtils);
            iFramework.generateSwitchToFrame(c, true, iLoggerUtils);
            iFramework.generateSwitchToFrame(c, false, iLoggerUtils);
            iFramework.generateSwitchWindow(c, iLoggerUtils);
            iFramework.generateWait(c, iLoggerUtils);
            iFramework.generateClickAndHold(c, iLoggerUtils);
            iFramework.generateQuit(c, iLoggerUtils);
//        iFramework.generateOpenHomePage(c,iLoggerUtils);
            iFramework.generateMethodMaximizeBrowserToDefault(c, iLoggerUtils);
            iFramework.generateMethodMinimizeBrowser(c, iLoggerUtils);
            iFramework.generateGetBrowserSize(c, iLoggerUtils);
            iFramework.generateMethodSetBrowserSize(c, iLoggerUtils);
            iFramework.generateMethodSetBrowserPosition(c, iLoggerUtils);
            iFramework.generateGetBrowserLocation(c, iLoggerUtils);
            iFramework.generateGetWindowHandle(c, iLoggerUtils);
            iFramework.generateGetWindowHandles(c, iLoggerUtils);
            iFramework.generateGetPageSource(c, iLoggerUtils);
            iFramework.generateCloseCurrentTab(c, iLoggerUtils);
            iFramework.generateMethodSwitchToAlert(c, iLoggerUtils);
            iFramework.generateMethodAcceptAlert(c, iLoggerUtils);
            iFramework.generateMethodDismissAlert(c, iLoggerUtils);
            iFramework.generateMethodAlertInput(c, iLoggerUtils);
            iFramework.generateMethodScrollToTop(c, iLoggerUtils);
            iFramework.generateMethodScrollToBottom(c, iLoggerUtils);
            iFramework.generateMethodPageScroll(c, iLoggerUtils);
            iFramework.generateMethodScrollElementToPosition(c, iLoggerUtils);
            iFramework.generateMethodRefresh(c, iLoggerUtils);
            iFramework.generateMethodGetLogs(c, iLoggerUtils);
            iFramework.generateMethodNoErrorLogs(c, iLoggerUtils);
            iFramework.generateMethodNoLogs(c, iLoggerUtils);
            iFramework.generateMethodClearConsole(c, iLoggerUtils);
            iFramework.generateMethodTakeSnapshot(c, iLoggerUtils);
            iFramework.generateMethodGetLocatorWithName(c, iLoggerUtils);
            iFramework.generateMethodClickUsingJS(c, iLoggerUtils);
            iFramework.generateMethodDragAndDrop(c, iLoggerUtils);
            iFramework.generateMethodFileUpload(c, iLoggerUtils);
            iFramework.generateMethodIsFileDownloaded(c, iLoggerUtils);
            iFramework.generateMethodCopy(c, iLoggerUtils);
            iFramework.generateMethodPressEnter(c, iLoggerUtils);
            iFramework.generateMethodPaste(c, iLoggerUtils);
            iFramework.generateMethodSelectAll(c, iLoggerUtils);


            if (StringUtils.equalsIgnoreCase("Gemini", env))
                UtilsStepDefinitionCodeGenerator.savePageObjectsOnFileSystem(Settings.IMPLEMENTATION_PO_DIR,
                        "UtilsImplementation", c, false);
            else if (StringUtils.equalsIgnoreCase("EJ", env)) {
                UtilsStepDefinitionCodeGenerator.savePageObjectsOnFileSystem(Settings.EJ_IMPLEMENTATION_DIR,
                        "UtilsImplementation", c, false);
                HelperFunctions.correctLoggerStatement("UtilsImplementation");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
