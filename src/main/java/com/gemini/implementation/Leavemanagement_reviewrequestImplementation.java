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
import com.gemini.locator.Leavemanagement_reviewrequest;
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

public class Leavemanagement_reviewrequestImplementation extends PageObject {

    private LoggerUtils loggerUtils = new EnableSlf4jLogging();

    private Reporting reporting = new EnableSerenityReporting();

    public void clickOnDelegate() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        try{
			waitABit(3000);
			elementIsClickableDelegate();
        	$(Leavemanagement_reviewrequest.Delegate).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Delegate element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Delegate", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableDelegate() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Leavemanagement_reviewrequest.Delegate));
        	assertTrue("Delegate is not clickable", $(Leavemanagement_reviewrequest.Delegate).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Delegate is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromDelegate(String typeText) {
        //This function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Leavemanagement_reviewrequest.Delegate));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Delegate");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Delegate", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverDelegate() {
        //This function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyDelegateIsEnabled();
			serenityActions.moveToElement($(Leavemanagement_reviewrequest.Delegate)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToDelegateElement() {
        //This function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Leavemanagement_reviewrequest.Delegate));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Delegate element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Delegate element ");
        };
    }

    public void verifyDelegateIsEnabled() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Leavemanagement_reviewrequest.Delegate));
        	assertTrue("Delegate is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Delegate element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnDelegate() {
        //This function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Leavemanagement_reviewrequest.Delegate)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Delegate element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToDelegate() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Leavemanagement_reviewrequest.Delegate));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Delegate successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Delegate");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyDelegateIsDisabled() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        Boolean isElementDisabled = false;
			try{
			if($(Leavemanagement_reviewrequest.Delegate).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Delegate element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Delegate is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyDelegateContainsText(String typeText) {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        try{
			assertTrue("Actual text: " + $(Leavemanagement_reviewrequest.Delegate).getText(), StringUtils.containsIgnoreCase($(Leavemanagement_reviewrequest.Delegate).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Delegate contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForDelegate(String attribute, String typeText) {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        try{
			assertTrue("Actual value: " + $(Leavemanagement_reviewrequest.Delegate).getAttribute(attribute), StringUtils.contains($(Leavemanagement_reviewrequest.Delegate).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Delegate contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyDelegateText(String typeText) {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        try{
			assertTrue("Actual text: " + $(Leavemanagement_reviewrequest.Delegate).getText(), StringUtils.equalsIgnoreCase(typeText,$(Leavemanagement_reviewrequest.Delegate).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Delegate element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyDelegateIsDisplayed() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        try{
			waitABit(3000);
			assertTrue("Delegate is not visible", $(Leavemanagement_reviewrequest.Delegate).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Delegate is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void elementIsClickableServiceUnavailable() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Leavemanagement_reviewrequest.Service_Unavailable));
        	assertTrue("ServiceUnavailable is not clickable", $(Leavemanagement_reviewrequest.Service_Unavailable).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Service_Unavailable is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyServiceUnavailableIsEnabled() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Leavemanagement_reviewrequest.Service_Unavailable));
        	assertTrue("ServiceUnavailable is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Service_Unavailable element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromServiceUnavailable(String typeText) {
        //This function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Leavemanagement_reviewrequest.Service_Unavailable));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for ServiceUnavailable");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Service_Unavailable", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String verifyAttributeValueForServiceUnavailable(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Leavemanagement_reviewrequest.Service_Unavailable).getAttribute(attributeName)));
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        	Assert.fail(e.getMessage());
        	reporting.reportSteps("Failure","Could not verify attributeValue for Service_Unavailable", "");
        }
		return $(Leavemanagement_reviewrequest.Service_Unavailable).getAttribute(attributeName);
    }

    public void hoverOverServiceUnavailable() {
        //This function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyServiceUnavailableIsEnabled();
			serenityActions.moveToElement($(Leavemanagement_reviewrequest.Service_Unavailable)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToServiceUnavailableElement() {
        //This function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Leavemanagement_reviewrequest.Service_Unavailable));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Service_Unavailable element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Service_Unavailable element ");
        };
    }

    public void changeFocusToServiceUnavailable() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Leavemanagement_reviewrequest.Service_Unavailable));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to ServiceUnavailable successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to ServiceUnavailable");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyServiceUnavailableContainsText(String typeText) {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        try{
			assertTrue("Actual text: " + $(Leavemanagement_reviewrequest.Service_Unavailable).getText(), StringUtils.containsIgnoreCase($(Leavemanagement_reviewrequest.Service_Unavailable).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Service_Unavailable contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForServiceUnavailable(String attribute, String typeText) {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        try{
			assertTrue("Actual value: " + $(Leavemanagement_reviewrequest.Service_Unavailable).getAttribute(attribute), StringUtils.contains($(Leavemanagement_reviewrequest.Service_Unavailable).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Service_Unavailable contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyServiceUnavailableText(String typeText) {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        try{
			assertTrue("Actual text: " + $(Leavemanagement_reviewrequest.Service_Unavailable).getText(), StringUtils.equalsIgnoreCase(typeText,$(Leavemanagement_reviewrequest.Service_Unavailable).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Service_Unavailable element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnServiceUnavailable() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        try{
			waitABit(3000);
			elementIsClickableServiceUnavailable();
        	$(Leavemanagement_reviewrequest.Service_Unavailable).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Service_Unavailable element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click ServiceUnavailable", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyServiceUnavailableIsDisplayed() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        try{
			waitABit(3000);
			assertTrue("ServiceUnavailable is not visible", $(Leavemanagement_reviewrequest.Service_Unavailable).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "ServiceUnavailable is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }
}
