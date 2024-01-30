package com.gemini.gpog.pageobjectgenerator.stepdefinitiongenerator;

import com.gemini.gpog.framework.HelperFunctions;
import com.gemini.gpog.logger.EnableSlf4jLogging;
import com.gemini.gpog.logger.LoggerUtils;
import com.gemini.gpog.pageobjectgenerator.LocatorsModel;
import com.gemini.gpog.pageobjectgenerator.Settings;
import japa.parser.ast.CompilationUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import static com.gemini.Utils.readProperties;

public class GenericStepDefinitionGenerator {

    /**
     * @author - Sajith, Hem, Ayush, Jasleen, Priyanshu, Rahul Tagra and Siddhanshi
     * @version - 1.0
     * @since - 5/16/2023
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericStepDefinitionGenerator.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
    }

    public void generateGenericStepMethods() throws IOException {
        String logging = LocatorsModel.getLogging();

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

        CompilationUnit c;

        if(StringUtils.equalsIgnoreCase("EJ", LocatorsModel.getEnv())) {
             c = HelperFunctions.createEnhancedCompilationUnitStepDefinitions("stepdefinition", "EJStepDefinition", "Utils");
        } else {
             c = HelperFunctions.createEnhancedCompilationUnitStepDefinitions("stepdefinition", "StepDefinition", "Utils");

        }
        UtilsStepDefinitionCodeGenerator.setTypeDeclaration(c,  "UtilsStepDefinition");
        UtilsStepDefinitionCodeGenerator.setStepDefinitionVariable(c, "UtilsImplementation", "utils");
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetUrl(c);
//        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionPressEnter(c);
//        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSelectAll(c);
//        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionCopy(c);
//        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionPaste(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionVerifyUrl(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetTitle(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionVerifyTitle(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNavigateTo(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNavigateForward(c);
//        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNavigateBack(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMaximizeBrowserToDefault(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMinimizeBrowser(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetBrowserSize(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSetBrowserSize(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSetBrowserPosition(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetBrowserLocation(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetPageSource(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionCloseCurrentTab(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSwitchToAlert(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAcceptAlert(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionDismissAlert(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAlertInput(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToTop(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToBottom(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionPageScroll(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollElementToPosition(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionRefresh(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTakeSnapshot(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionActiveElement(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionParentFrame(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionDefaultContent(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSwitchFrame(c, true);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSwitchFrame(c, false);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSwitchWindow(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetLogs(c, iLoggerUtils);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNoErrorLogs(c, iLoggerUtils);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNoLogs(c, iLoggerUtils);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionClearConsole(c, iLoggerUtils);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionWait(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionJSClick(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionClickAndHold(c, iLoggerUtils);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionDragAndDrop(c, iLoggerUtils);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionFileUpload(c, iLoggerUtils);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionQuit(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodGiven(c, "homePage");
        UtilsStepDefinitionCodeGenerator.savePageObjectsOnFileSystem(Settings.STEP_DEFINITION_PO_DIR,
                "UtilsStepDefinition", c, true);
    }
}
