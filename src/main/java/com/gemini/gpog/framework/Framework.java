package com.gemini.gpog.framework;

import com.gemini.gpog.logger.LoggerUtils;
import com.gemini.gpog.reporting.Reporting;
import japa.parser.ast.CompilationUnit;

import java.lang.reflect.Field;

public interface Framework {

    void generateElementVerifySelected(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateInputTypeInto(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateInputVerifyValue(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateElementVerifyAttributeValue(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateInputClear(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateInputTypeAndEnter(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateInputTypeAndTab(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateInputVerifyClear(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateElementHoverOver(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateElementVerifyPresence(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateVerifyCountChildElements(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateVerifyCountElements(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);


    void generateDropdownSelect(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateDeselects(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodClickable(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateElementVerifyEnabled(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateElementVerifyVisible(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateElementVerifyText(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateElementVerifyTextContains(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateLaunchBrowser(CompilationUnit c, LoggerUtils loggerUtils);

    void generateElementVerifyAttributesContains(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateElementClick(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateElementDoubleClick(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateElementUpload(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);
    void generateSwitchToActiveElement(CompilationUnit c, LoggerUtils loggerUtils);

    void generateSwitchToParentFrame(CompilationUnit c, LoggerUtils loggerUtils);

    void generateSwitchToFrame(CompilationUnit c, boolean argumentType, LoggerUtils loggerUtils);

    void generateSwitchWindow(CompilationUnit c, LoggerUtils loggerUtils);

    void generateWait(CompilationUnit c, LoggerUtils loggerUtils);

    void generateClickAndHold(CompilationUnit c, LoggerUtils loggerUtils);

    void generateRightClick(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateSwitchToDefaultContent(CompilationUnit c, LoggerUtils loggerUtils);

    void generateQuit(CompilationUnit c, LoggerUtils loggerUtils);

    void generateNavigateForward(CompilationUnit c, LoggerUtils loggerUtils);

    void generateNavigateTo(CompilationUnit c, LoggerUtils loggerUtils);

    void generateVerifyUrl(CompilationUnit c, LoggerUtils loggerUtils);

    void generateGetUrl(CompilationUnit c, LoggerUtils loggerUtils);

    void generateGetBrowserSize(CompilationUnit c, LoggerUtils loggerUtils);

    void generateGetBrowserLocation(CompilationUnit c, LoggerUtils loggerUtils);

    void generateGetWindowHandle(CompilationUnit c, LoggerUtils loggerUtils);

    void generateGetWindowHandles(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodCopy(CompilationUnit c, LoggerUtils loggerUtils);

    void generateGetPageSource(CompilationUnit c, LoggerUtils loggerUtils);

    void generateCloseCurrentTab(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodSwitchToAlert(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodAcceptAlert(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodDismissAlert(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodAlertInput(CompilationUnit c, LoggerUtils loggerUtils);
    void generateElementScrollIntoView(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);
    void generateTypeGetter(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateElementScrollAndClick(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodScrollToTop(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodScrollToBottom(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodPageScroll(CompilationUnit c, LoggerUtils loggerUtils);
    void generateMethodScrollElementToPosition(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodRefresh(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodGetLogs(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodNoErrorLogs(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodClearConsole(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodNoLogs(CompilationUnit c, LoggerUtils loggerUtils);
    void generateMethodTakeSnapshot(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodGetLocatorWithName(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodClickUsingJS(CompilationUnit c, LoggerUtils loggerUtils);


    void generateMethodDragAndDrop(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodFileUpload(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodMinimizeBrowser(CompilationUnit c, LoggerUtils loggerUtils);
    void generateMethodMaximizeBrowserToDefault(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodSetBrowserPosition(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodSetBrowserSize(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodVerifyTitle(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodGetTitle(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodSelect(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodGetRowCount(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodGetColumnCount(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);
    void generateMethodValidateRowCount(String locator, CompilationUnit c, Field field, LoggerUtils
            loggerUtils, Reporting reporting);
    void generateMethodValidateColumnCount(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodIsColumnNamePresent(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodRightClickWebElement(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodChangeFocus(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodIsDisabled(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodIsImage(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodGetColValue(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodGetRowValue(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodGetAllValuesFromTable(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodPressEnter(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodPaste(CompilationUnit c, LoggerUtils loggerUtils);

    void generateMethodSelectAll(CompilationUnit c, LoggerUtils loggerUtils);
    void generateElementVerifyNotSelected(String locator, CompilationUnit c, Field field, LoggerUtils loggerUtils, Reporting reporting);

    void generateMethodIsFileDownloaded(CompilationUnit c, LoggerUtils loggerUtils);
    void generateMethodIsNotSelected(String locator,CompilationUnit c, Field field,LoggerUtils loggerUtils,Reporting reporting);
    void generateMethodAttributeGetter(String locator,CompilationUnit c, Field field,LoggerUtils loggerUtils,Reporting reporting);
    void generateMethodTypeSetter( String locator,CompilationUnit c, Field field,LoggerUtils loggerUtils,Reporting reporting);
    void generateMethodDropDown(String locator,CompilationUnit c, Field field,LoggerUtils loggerUtils,Reporting reporting);
    void generateMethodNavigateBack(CompilationUnit c,LoggerUtils loggerUtils);
    void generateMethodForVisibility(String locator,CompilationUnit c, Field field,LoggerUtils loggerUtils,Reporting reporting);


}
