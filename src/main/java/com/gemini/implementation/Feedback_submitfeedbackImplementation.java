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
import com.gemini.locator.Feedback_submitfeedback;
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

public class Feedback_submitfeedbackImplementation extends PageObject {

    private LoggerUtils loggerUtils = new EnableSlf4jLogging();

    private Reporting reporting = new EnableSerenityReporting();

    public void clickOnProvideFeedback() {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        try{
			waitABit(3000);
			elementIsClickableProvideFeedback();
        	$(Feedback_submitfeedback.Provide_Feedback).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Provide_Feedback element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click ProvideFeedback", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableProvideFeedback() {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Feedback_submitfeedback.Provide_Feedback));
        	assertTrue("ProvideFeedback is not clickable", $(Feedback_submitfeedback.Provide_Feedback).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Provide_Feedback is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromProvideFeedback(String typeText) {
        //This function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Feedback_submitfeedback.Provide_Feedback));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for ProvideFeedback");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Provide_Feedback", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverProvideFeedback() {
        //This function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyProvideFeedbackIsEnabled();
			serenityActions.moveToElement($(Feedback_submitfeedback.Provide_Feedback)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToProvideFeedbackElement() {
        //This function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Feedback_submitfeedback.Provide_Feedback));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Provide_Feedback element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Provide_Feedback element ");
        };
    }

    public void verifyProvideFeedbackIsEnabled() {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Feedback_submitfeedback.Provide_Feedback));
        	assertTrue("ProvideFeedback is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Provide_Feedback element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnProvideFeedback() {
        //This function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Feedback_submitfeedback.Provide_Feedback)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Provide_Feedback element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToProvideFeedback() {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Feedback_submitfeedback.Provide_Feedback));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to ProvideFeedback successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to ProvideFeedback");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyProvideFeedbackIsDisabled() {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        Boolean isElementDisabled = false;
			try{
			if($(Feedback_submitfeedback.Provide_Feedback).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "ProvideFeedback element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "ProvideFeedback is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyProvideFeedbackContainsText(String typeText) {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        try{
			assertTrue("Actual text: " + $(Feedback_submitfeedback.Provide_Feedback).getText(), StringUtils.containsIgnoreCase($(Feedback_submitfeedback.Provide_Feedback).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Provide_Feedback contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForProvideFeedback(String attribute, String typeText) {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        try{
			assertTrue("Actual value: " + $(Feedback_submitfeedback.Provide_Feedback).getAttribute(attribute), StringUtils.contains($(Feedback_submitfeedback.Provide_Feedback).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Provide_Feedback contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyProvideFeedbackText(String typeText) {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        try{
			assertTrue("Actual text: " + $(Feedback_submitfeedback.Provide_Feedback).getText(), StringUtils.equalsIgnoreCase(typeText,$(Feedback_submitfeedback.Provide_Feedback).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Provide_Feedback element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyProvideFeedbackIsDisplayed() {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        try{
			waitABit(3000);
			assertTrue("ProvideFeedback is not visible", $(Feedback_submitfeedback.Provide_Feedback).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "ProvideFeedback is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }
}
