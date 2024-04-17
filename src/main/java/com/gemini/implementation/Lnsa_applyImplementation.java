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
import com.gemini.locator.Lnsa_apply;
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

public class Lnsa_applyImplementation extends PageObject {

    private LoggerUtils loggerUtils = new EnableSlf4jLogging();

    private Reporting reporting = new EnableSerenityReporting();

    public void clickOnMonday() {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        try{
			waitABit(3000);
			elementIsClickableMonday();
        	$(Lnsa_apply.Monday).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Monday element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Monday", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableMonday() {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Lnsa_apply.Monday));
        	assertTrue("Monday is not clickable", $(Lnsa_apply.Monday).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Monday is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromMonday(String typeText) {
        //This function is for web element @FindBy(Lnsa_apply.Monday);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Lnsa_apply.Monday));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Monday");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Monday", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverMonday() {
        //This function is for web element @FindBy(Lnsa_apply.Monday);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyMondayIsEnabled();
			serenityActions.moveToElement($(Lnsa_apply.Monday)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToMondayElement() {
        //This function is for web element @FindBy(Lnsa_apply.Monday);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Lnsa_apply.Monday));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Monday element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Monday element ");
        };
    }

    public void verifyMondayIsEnabled() {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Lnsa_apply.Monday));
        	assertTrue("Monday is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Monday element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnMonday() {
        //This function is for web element @FindBy(Lnsa_apply.Monday);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Lnsa_apply.Monday)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Monday element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToMonday() {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Lnsa_apply.Monday));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Monday successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Monday");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyMondayIsDisabled() {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        Boolean isElementDisabled = false;
			try{
			if($(Lnsa_apply.Monday).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Monday element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Monday is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyMondayContainsText(String typeText) {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        try{
			assertTrue("Actual text: " + $(Lnsa_apply.Monday).getText(), StringUtils.containsIgnoreCase($(Lnsa_apply.Monday).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Monday contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForMonday(String attribute, String typeText) {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        try{
			assertTrue("Actual value: " + $(Lnsa_apply.Monday).getAttribute(attribute), StringUtils.contains($(Lnsa_apply.Monday).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Monday contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyMondayText(String typeText) {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        try{
			assertTrue("Actual text: " + $(Lnsa_apply.Monday).getText(), StringUtils.equalsIgnoreCase(typeText,$(Lnsa_apply.Monday).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Monday element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyMondayIsDisplayed() {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        try{
			waitABit(3000);
			assertTrue("Monday is not visible", $(Lnsa_apply.Monday).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Monday is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnSubmit() {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        try{
			waitABit(3000);
			elementIsClickableSubmit();
        	$(Lnsa_apply.Submit).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Submit element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Submit", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableSubmit() {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Lnsa_apply.Submit));
        	assertTrue("Submit is not clickable", $(Lnsa_apply.Submit).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Submit is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromSubmit(String typeText) {
        //This function is for web element @FindBy(Lnsa_apply.Submit);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Lnsa_apply.Submit));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Submit");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Submit", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverSubmit() {
        //This function is for web element @FindBy(Lnsa_apply.Submit);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifySubmitIsEnabled();
			serenityActions.moveToElement($(Lnsa_apply.Submit)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToSubmitElement() {
        //This function is for web element @FindBy(Lnsa_apply.Submit);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Lnsa_apply.Submit));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Submit element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Submit element ");
        };
    }

    public void verifySubmitIsEnabled() {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Lnsa_apply.Submit));
        	assertTrue("Submit is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Submit element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnSubmit() {
        //This function is for web element @FindBy(Lnsa_apply.Submit);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Lnsa_apply.Submit)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Submit element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToSubmit() {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Lnsa_apply.Submit));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Submit successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Submit");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySubmitIsDisabled() {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        Boolean isElementDisabled = false;
			try{
			if($(Lnsa_apply.Submit).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Submit element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Submit is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySubmitContainsText(String typeText) {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        try{
			assertTrue("Actual text: " + $(Lnsa_apply.Submit).getText(), StringUtils.containsIgnoreCase($(Lnsa_apply.Submit).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Submit contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForSubmit(String attribute, String typeText) {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        try{
			assertTrue("Actual value: " + $(Lnsa_apply.Submit).getAttribute(attribute), StringUtils.contains($(Lnsa_apply.Submit).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Submit contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySubmitText(String typeText) {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        try{
			assertTrue("Actual text: " + $(Lnsa_apply.Submit).getText(), StringUtils.equalsIgnoreCase(typeText,$(Lnsa_apply.Submit).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Submit element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySubmitIsDisplayed() {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        try{
			waitABit(3000);
			assertTrue("Submit is not visible", $(Lnsa_apply.Submit).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Submit is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }
}
