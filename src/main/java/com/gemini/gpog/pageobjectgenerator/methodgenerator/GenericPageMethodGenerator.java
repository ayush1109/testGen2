package com.gemini.gpog.pageobjectgenerator.methodgenerator;//
//package pageobjectgenerator.methodgenerator;
//
//import japa.parser.ast.CompilationUnit;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import pog.pageobjectgenerator.pageobjectgenerator.Settings;
//import pog.utils.UtilsFunctionsGenerator;
//import pog.utils.UtilsMethodCodeGenerator;
//
//import java.io.IOException;
//
//public class GenericPageMethodGenerator {
//
//    /**
//     * @author - Sajith, Hem, Ayush, Jasleen, Priyanshu, Rahul Tagra and Siddhanshi
//     * @version - 1.0
//     * @since - 5/16/2023
//     */
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(GenericPageMethodGenerator.class);
//
//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        ClassLoader classLoader = GeneralImplementationsGenerator.class.getClassLoader();
//        Class aClass = classLoader.loadClass("locators" + "." + pageobjectgenerator.Settings.LOCATOR_FILE_NAME);
//        generateGenericPageMethods();
////        generateGenericUtilsMethods();
//    }
//
//    public static void generateGenericPageMethods() throws IOException {
//
//        CompilationUnit c = UtilsMethodCodeGenerator.createEnhancedCompilationUnit("implementation", "Method");
//        UtilsMethodCodeGenerator.setTypeDeclaration(c, "UtilsImplementation");
//
//        UtilsMethodCodeGenerator.setLinkMethodsGetUrl(c);
//        UtilsMethodCodeGenerator.setLinkMethodsVerifyUrl(c);
//        UtilsMethodCodeGenerator.setLinkMethodsGetTitle(c);
//        UtilsMethodCodeGenerator.setLinkMethodsVerifyTitle(c);
//        UtilsMethodCodeGenerator.setLinkMethodsNavigateTo(c);
//        UtilsMethodCodeGenerator.setLinkMethodsNavigateForward(c);
//        UtilsMethodCodeGenerator.setLinkMethodsNavigateBack(c);
//        UtilsMethodCodeGenerator.setLinkMethodsSwitchToActiveElement(c);
//        UtilsMethodCodeGenerator.setLinkMethodsSwitchToDefaultContent(c);
//        UtilsMethodCodeGenerator.setLinkMethodsSwitchToParentFrame(c);
//        UtilsMethodCodeGenerator.setLinkMethodsSwitchToFrame(c, true);
//        UtilsMethodCodeGenerator.setLinkMethodsSwitchToFrame(c, false);
//        UtilsMethodCodeGenerator.setLinkMethodsSwitchWindow(c);
//        UtilsMethodCodeGenerator.setLinkMethodsWait(c);
//        UtilsMethodCodeGenerator.setLinkMethodsClickAndHold(c);
//        UtilsMethodCodeGenerator.setLinkMethodsQuit(c);
//        UtilsMethodCodeGenerator.setLinkMethodsOpenHomePage(c);
//        UtilsMethodCodeGenerator.setLinkMethodsMaximizeBrowserToDefault(c);
//        UtilsMethodCodeGenerator.setLinkMethodsMinimizeBrowser(c);
//        UtilsMethodCodeGenerator.setLinkMethodsGetBrowserSize(c);
//        UtilsMethodCodeGenerator.setLinkMethodsSetBrowserSize(c);
//        UtilsMethodCodeGenerator.setLinkMethodsSetBrowserPosition(c);
//        UtilsMethodCodeGenerator.setLinkMethodsGetBrowserLocation(c);
//        UtilsMethodCodeGenerator.setLinkMethodsGetWindowHandle(c);
//        UtilsMethodCodeGenerator.setLinkMethodsGetWindowHandles(c);
//        UtilsMethodCodeGenerator.setLinkMethodsGetPageSource(c);
//        UtilsMethodCodeGenerator.setLinkMethodsCloseCurrentTab(c);
//        UtilsMethodCodeGenerator.setLinkMethodSwitchToAlert(c);
//        UtilsMethodCodeGenerator.setLinkMethodAcceptAlert(c);
//        UtilsMethodCodeGenerator.setLinkMethodDismissAlert(c);
//        UtilsMethodCodeGenerator.setLinkMethodAlertInput(c);
//        UtilsMethodCodeGenerator.setLinkMethodScrollToTop(c);
//        UtilsMethodCodeGenerator.setLinkMethodScrollToBottom(c);
//        UtilsMethodCodeGenerator.setLinkMethodPageScroll(c);
//        UtilsMethodCodeGenerator.setLinkMethodScrollElementToPosition(c);
////        UtilsMethodCodeGenerator.setLinkMethodNavigateToUrl(c);
//        UtilsMethodCodeGenerator.setLinkMethodRefresh(c);
//        UtilsMethodCodeGenerator.setLinkMethodGetLogs(c);
//        UtilsMethodCodeGenerator.setLinkMethodNoErrorLogs(c);
//        UtilsMethodCodeGenerator.setLinkMethodNoLogs(c);
//        UtilsMethodCodeGenerator.setLinkMethodClearConsole(c);
//        UtilsMethodCodeGenerator.setLinkMethodTakeSnapshot(c);
//        UtilsMethodCodeGenerator.setLinkMethodGetLocatorWithName(c);
//        UtilsMethodCodeGenerator.setLinkMethodClickUsingJS(c);
//        UtilsMethodCodeGenerator.setLinkMethodDragAndDrop(c);
//        UtilsMethodCodeGenerator.setLinkMethodFileUpload(c);
//        UtilsFunctionsGenerator.setLinkMethodsIsFileDownloaded(c);
//        UtilsFunctionsGenerator.setLinkMethodsCopy(c);
//        UtilsFunctionsGenerator.setLinkMethodsPressEnter(c);
//        UtilsFunctionsGenerator.setLinkMethodsPaste(c);
//        UtilsFunctionsGenerator.setLinkMethodsSelectAll(c);
//        UtilsMethodCodeGenerator.savePageObjectsOnFileSystem(pageobjectgenerator.Settings.IMPLEMENTATION_PO_DIR,
//                "UtilsImplementation", c, false);
//
//    }
//}