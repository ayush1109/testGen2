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
import com.gemini.locator.Policy_viewpolicy;
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

public class Policy_viewpolicyImplementation extends PageObject {

    private LoggerUtils loggerUtils = new EnableSlf4jLogging();

    private Reporting reporting = new EnableSerenityReporting();

    public void elementIsClickableAccessPolicy() {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Policy_viewpolicy.Access_Policy));
        	assertTrue("AccessPolicy is not clickable", $(Policy_viewpolicy.Access_Policy).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Access_Policy is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyAccessPolicyIsEnabled() {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Policy_viewpolicy.Access_Policy));
        	assertTrue("AccessPolicy is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Access_Policy element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromAccessPolicy(String typeText) {
        //This function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Policy_viewpolicy.Access_Policy));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for AccessPolicy");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Access_Policy", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String verifyAttributeValueForAccessPolicy(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Policy_viewpolicy.Access_Policy).getAttribute(attributeName)));
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        	Assert.fail(e.getMessage());
        	reporting.reportSteps("Failure","Could not verify attributeValue for Access_Policy", "");
        }
		return $(Policy_viewpolicy.Access_Policy).getAttribute(attributeName);
    }

    public void hoverOverAccessPolicy() {
        //This function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyAccessPolicyIsEnabled();
			serenityActions.moveToElement($(Policy_viewpolicy.Access_Policy)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToAccessPolicyElement() {
        //This function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Policy_viewpolicy.Access_Policy));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Access_Policy element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Access_Policy element ");
        };
    }

    public void changeFocusToAccessPolicy() {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Policy_viewpolicy.Access_Policy));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to AccessPolicy successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to AccessPolicy");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyAccessPolicyContainsText(String typeText) {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        try{
			assertTrue("Actual text: " + $(Policy_viewpolicy.Access_Policy).getText(), StringUtils.containsIgnoreCase($(Policy_viewpolicy.Access_Policy).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Access_Policy contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForAccessPolicy(String attribute, String typeText) {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        try{
			assertTrue("Actual value: " + $(Policy_viewpolicy.Access_Policy).getAttribute(attribute), StringUtils.contains($(Policy_viewpolicy.Access_Policy).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Access_Policy contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAccessPolicyText(String typeText) {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        try{
			assertTrue("Actual text: " + $(Policy_viewpolicy.Access_Policy).getText(), StringUtils.equalsIgnoreCase(typeText,$(Policy_viewpolicy.Access_Policy).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Access_Policy element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnAccessPolicy() {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        try{
			waitABit(3000);
			elementIsClickableAccessPolicy();
        	$(Policy_viewpolicy.Access_Policy).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Access_Policy element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click AccessPolicy", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyAccessPolicyIsDisplayed() {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        try{
			waitABit(3000);
			assertTrue("AccessPolicy is not visible", $(Policy_viewpolicy.Access_Policy).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "AccessPolicy is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }
}
