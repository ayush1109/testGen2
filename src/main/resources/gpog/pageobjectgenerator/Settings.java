package com.gemini.gpog.pageobjectgenerator;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Settings {
//file name settings
	/**
	 * @author - Sajith, Hem, Ayush, Jasleen, Priyanshu, Rahul Tagra and Siddhanshi
	 * @version - 1.0
	 * @since - 5/16/2023
	 */

	public static String LOCATOR_FILE_NAME;

	//Used For Logging the steps
	public static final Logger LOGGER= LoggerFactory.getLogger(Settings.class);
	public static final org.apache.logging.log4j.Logger LOG4J= LogManager.getLogger(Settings.class);
	// Output pageobjectgenerator.Settings
	public static final String FILESEPARATOR = File.separator;
	public static final String IMPLEMENTATION_PO_DIR = "implementation" + FILESEPARATOR;
	public static final String EJ_IMPLEMENTATION_DIR = "frontend" + FILESEPARATOR + "pages" + FILESEPARATOR;
	public static final String STEP_DEFINITION_PO_DIR = "stepdefinition" + FILESEPARATOR;
	public static final String OUT_DIR = "output" + FILESEPARATOR;

	public static final String USER_CLICK_ANNOTATION ="user clicks on";
	public static final String USER_NAVIGATE_FUNCTION ="AndNavigateBack";
	public static final String USER_NAVIGATE_ANNOTATION ="and navigates back";
	public static final String USER_GETTEXT_FUNCTION ="userGetText";
	public static final String USER_TYPE_AND_ENTER_FUNCTION ="typeTextAndEnterFor";
	public static final String USER_TYPE_AND_TAB_FUNCTION ="typeTextAndTabFor";
	public static final String USER_GET_ATTRIBUTE_FUNCTION ="userGetsAttribute";
	public static final String USER_VERIFIES_VALUE="userVerifyValueFor";
	public static final String USER_CLICK_HOLD="userClicksAndHoldsElement";
	public static final String USER_DRAG_DROP="userDragsAndDrops";
	public static final String USER_UPLOAD_FILE="userUploadsFile";
	public static final String USER_VERIFIES_VALUE_ATTRIBUTE="userVerifyAttributeFor";
	public static final String VERIFY_ROW_COUNT ="user verifies row count is (.*) for ";
	public static final String VERIFY_COLUMN_COUNT ="user verifies column count is (.*) for ";
	public static final String CHANGE_FOCUS ="user changes focus to ";
	public static final String USER_CLEAR_ANNOTATION ="user clears";
	public static final String VERIFY_COLUMN_NAME ="^user verifies " +"\\\"(.*)\\\""+ " column is present in ";
	public static final String GET_COLUMN_VALUE ="^user gets column value for";
	public static final String GET_ALL_CELLS_VALUE ="^user gets all cell values for";
	public static final String USER_VERIFY_CLEAR_ANNOTATION ="user verifies";
	public static final String USER_CLEAR_FUNCTION ="userClear";
	public static final String USER_VERIFY_CLEAR_FUNCTION ="verifyValueClearedFor";
	public static final String USER_GETTEXT_ANNOTATION ="user gets the text of";
	public static final String USER_GET_ATTRIBUTE_ANNOTATION ="user gets " +"\\\"(.*)\\\""+ " attribute of ";
	public static final String USER_INPUT_ANNOTATION ="user enters "+"\\\"(.*)\\\""+" as";
	public static final String USER_TYPE_AND_ENTER_ANNOTATION ="user enters "+"\\\"(.*)\\\""+" as";
	public static final String USER_TYPE_AND_TAB_ANNOTATION ="user enters "+"\\\"(.*)\\\""+" as";
	public static final String USER_SELECT_FUNCTION ="userSelectFromDropdown";
	public static final String USER_DESELECT_FUNCTION ="userDeselectFromDropdown";
	public static final String USER_CONTAINS_FUNCTION ="userVerifiesTextContainsIn";
	public static final String USER_CONTAINS_ANNOTATION ="user verifies \\\"(.*)\\\" is present in";
	public static final String USER_CLICK_FUNCTION ="userClicksOn";
	public static final String USER_CLICKABLE_FUNCTION ="verifyUserIsClickable";
	public static final String USER_COUNT_CHILD_ELEMENTS ="verifyChildElementsCount";
	public static final String USER_COUNT_ELEMENTS ="verifyCountOfElementsFor";
	public static final String USER_CLICKABLE_ANNOTATION ="user is able to click";
	public static final String USER_VERIFY_CHILD_ELEMENTS_COUNT_ANNOTATION ="user verifies (.*) is the count of child elements for";
	public static final String USER_VERIFY_ELEMENTS_COUNT_ANNOTATION ="user verifies (.*) is the count of elements for";
	public static final String USER_DOUBLE_CLICK_FUNCTION ="userDoubleCLickON";
	public static final String USER_DOUBLE_CLICK_ANNOTATION ="user double click on";
	public static final String USER_ENABLED_FUNCTION ="userIsEnabled";
	public static final String USER_ENABLED_ANNOTATION ="user verifies";
	public static final String USER_IMAGE_FUNCTION ="userUploadImage";
	public static final String USER_INPUT_FUNCTION ="userEntersAs";
	public static final String USER_IMAGE_ANNOTATION ="user uploads image having path \\\"(.*)\\\" for";
	public static final String USER_HOME_PAGE ="open";
	public static final String USER_SCROLL_CLICK_FUNCTION ="userScrollCLickOn";
	public static final String USER_SCROLL_CLICK_ANNOTATION ="user scroll and clicks on ";

	public static final String USER_HOME_PAGE_ANNOTATION ="^user is on homepage";
	public static final String USER_SELECT_ANNOTATION="user selects \\\"(.*)\\\" from";
	public static final String USER_DESELECT_ANNOTATION="user deselects \\\"(.*)\\\" from";
	public static final String USER_NAVIGATE_BACK ="^user navigates back to previous page";
	public static final String SWITCH_ACTIVE_ELEMENT = "^user switches to active element";
	public static final String SWITCH_PARENT_FRAME = "^user switches to parent frame";
	public static final String SWITCH_DEFAULT_CONTENT = "^user switches to default content";
	public static final String SWITCH_FRAME_STRING = "^user switches to \\\"(.*)\\\" frame";
	public static final String SWITCH_FRAME_INT = "^user switches to (.*) frame";
	public static final String SWITCH_WINDOW = "^user switches to \\\"(.*)\\\" window";
	public static final String GET_LOGS = "^user gets console messages";
	public static final String NO_LOGS = "^user verifies there are no console messages";
	public static final String CLEAR_CONSOLE = "^user clears the console";
	public static final String NO_ERROR_LOGS = "^user verifies there are no console error messages";
	public static final String USER_CLICKS_JS = "^user clicks \\\"(.*?)\\\"";
	public static final String USER_WAITS = "^user waits for (.*) seconds";
	public static final String USER_CLOSES_BROWSER ="^user closes browser";
	public static final String MAXIMIZE_TO_DEFAULT ="^user maximizes window to default$";
	public static final String MINIMIZE_TO_DEFAULT ="^user minimizes window to default$";
	public static final String GET_PAGE_SOURCE ="^user gets page source$";
	public static final String SWITCH_ALERT ="^user switches to alert$";
	public static final String ACCEPT_ALERT ="^user accepts alert$";
	public static final String DISMISS_ALERT ="^user dismisses alert$";
	public static final String SCROLL_UP ="^user scrolls page up$";
	public static final String SCROLL_DOWN ="^user scrolls page down$";
	public static final String REFRESH_PAGE ="^user refreshes page$";
	public static final String TAKE_SNAPSHOT ="^user takes snapshot$";
	public static final String SCROLL_PAGE ="^user scrolls page to \\\"(.*)\\\" x coordinate and \\\"(.*)\\\" y coordinate$";
	public static final String SCROLL_ELEMENT ="^user scrolls element to \\\"(.*)\\\" x coordinate and \\\"(.*)\\\" y coordinate$";
	public static final String NAVIGATE_URL ="^user navigates to \\\"(.*)\\\" url$";
	public static final String ALERT_INPUT ="^user enters \\\"(.*)\\\" as alert input$";
	public static final String BROWSER_SIZE ="^user gets browser size$";
	public static final String BROWSER_POSITION ="^user gets browser positions$";
	public static final String GET_WINDOW_HANDLE ="^user gets window handle$";
	public static final String GET_WINDOW_HANDLES ="^user gets window handles$";
	public static final String SET_BROWSER_SIZE ="^user sets browser size to \\\"(.*)\\\" width and \\\"(.*)\\\" height$";
	public static final String SET_BROWSER_POSITION ="^user sets browser position to \\\"(.*)\\\" x coordinate and \\\"(.*)\\\" y coordinate$";
	public static final String CLOSE_CURRENT_TAB ="^user closes current tab$";
	public static final String CLOSE_TAB_AND_SWITCH ="^user closes current tab and switches to \\\"(.*)\\\" tab$";
	public static final String USER_NAVIGATE_FORWARD ="^user navigates forward to next page";
	public static final String USER_NAVIGATE_TO ="^user navigates to \\\"(.*)\\\"$";
	public static final String USER_VERIFY_URL ="^user verifies \\\"(.*)\\\" is the current url$";
	public static final String USER_VERIFY_TITLE ="^user verifies \\\"(.*)\\\" is the title of the page$";
	public static final String USER_PRESSES_ENTER ="^user presses enter key$";
	public static final String USER_COPY ="^user performs copy action$";
	public static final String USER_PASTE ="^user performs paste action$";
	public static final String USER_WINDOW_FOCUS ="^user shifts focus to \\\"(.*)\\\" window$";
	public static final String USER_SELECT_ALL ="^user performs select all action$";
	public static final String USER_GET_URL ="^user gets current url of the page";
	public static final String USER_GET_TITLE ="^user gets title of the page";
	public static String BEFORE_FUNCTION="setDriverInitialisation";
	public static final String USER_SELECTS_FUNCTION ="userSelects";
	public static final String USER_SELECTS_ANNOTATION ="user selects";
	public static final String USER_RIGHT_CLICK_FUNCTION ="rightClickOn";
	public static final String USER_RIGHT_CLICK_ANNOTATION ="user right clicks on \\\"(.*)\\\"";

}
