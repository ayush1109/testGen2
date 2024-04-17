package com.gemini.implementation;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.PageObject;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.File;
import org.apache.commons.io.FileUtils;
import net.serenitybdd.core.Serenity;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import com.gemini.locator.Timesheet_configuretimesheet;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import java.util.Objects;
import org.openqa.selenium.*;
import net.serenitybdd.core.pages.SerenityActions;
import org.openqa.selenium.By;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LogEntry;
import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import com.gemini.gpog.logger.EnableSlf4jLogging;
import com.gemini.gpog.logger.LoggerUtils;
import com.gemini.gpog.reporting.EnableSerenityReporting;
import com.gemini.gpog.reporting.Reporting;
import com.gemini.gpog.framework.HelperFunctions;
import net.serenitybdd.core.pages.PageObject;
import io.netty.handler.logging.LogLevel;

public class Timesheet_configuretimesheetImplementation extends PageObject {

    private LoggerUtils loggerUtils = new EnableSlf4jLogging();

    private Reporting reporting = new EnableSerenityReporting();

    public void clickOnSave() {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        try{
			waitABit(3000);
			elementIsClickableSave();
        	$(Timesheet_configuretimesheet.Save).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Save element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Save", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableSave() {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Timesheet_configuretimesheet.Save));
        	assertTrue("Save is not clickable", $(Timesheet_configuretimesheet.Save).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Save is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromSave(String typeText) {
        //This function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Timesheet_configuretimesheet.Save));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Save");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Save", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverSave() {
        //This function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifySaveIsEnabled();
			serenityActions.moveToElement($(Timesheet_configuretimesheet.Save)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToSaveElement() {
        //This function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Timesheet_configuretimesheet.Save));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Save element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Save element ");
        };
    }

    public void verifySaveIsEnabled() {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Timesheet_configuretimesheet.Save));
        	assertTrue("Save is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Save element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnSave() {
        //This function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Timesheet_configuretimesheet.Save)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Save element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToSave() {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Timesheet_configuretimesheet.Save));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Save successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Save");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySaveIsDisabled() {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        Boolean isElementDisabled = false;
			try{
			if($(Timesheet_configuretimesheet.Save).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Save element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Save is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySaveContainsText(String typeText) {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        try{
			assertTrue("Actual text: " + $(Timesheet_configuretimesheet.Save).getText(), StringUtils.containsIgnoreCase($(Timesheet_configuretimesheet.Save).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Save contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForSave(String attribute, String typeText) {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        try{
			assertTrue("Actual value: " + $(Timesheet_configuretimesheet.Save).getAttribute(attribute), StringUtils.contains($(Timesheet_configuretimesheet.Save).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Save contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySaveText(String typeText) {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        try{
			assertTrue("Actual text: " + $(Timesheet_configuretimesheet.Save).getText(), StringUtils.equalsIgnoreCase(typeText,$(Timesheet_configuretimesheet.Save).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Save element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySaveIsDisplayed() {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        try{
			waitABit(3000);
			assertTrue("Save is not visible", $(Timesheet_configuretimesheet.Save).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Save is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }
}
