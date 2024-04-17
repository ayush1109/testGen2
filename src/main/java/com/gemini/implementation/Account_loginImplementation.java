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
import com.gemini.locator.Account_login;
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

public class Account_loginImplementation extends PageObject {

    private LoggerUtils loggerUtils = new EnableSlf4jLogging();

    private Reporting reporting = new EnableSerenityReporting();

    public void elementIsClickableUsername() {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Account_login.Username));
        	assertTrue("Username is not clickable", $(Account_login.Username).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Username is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void typeTextIntoUsername(String typeText) {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Username));
        	typeInto(element,typeText);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Username", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndEnterForUsername(String typeText) {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Username));
        	$(element).typeAndEnter(typeText);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Username", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndTabForUsername(String typeText) {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Username));
        	typeInto(element,typeText);
        	new SerenityActions(getDriver()).sendKeys(Keys.TAB);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Username", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromUsername() {
        //The below function is for web element @FindBy(Account_login.Username);
        String text = "";
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Username));
        	text = element.getText();
			loggerUtils.log(LogLevel.INFO, "Successfully fetched the text of Username element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not get text of Username", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return text;
    }

    public void verifyUsernameIsEnabled() {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Username));
        	assertTrue("Username is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Username element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void clearUsername() {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Username));
        	element.clear();
        	loggerUtils.log(LogLevel.INFO, "User deletes the value for Username element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not clear Username", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyValueFromUsername(String typeText) {
        //This function is for web element @FindBy(Account_login.Username);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Username));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Username");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Username", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String verifyAttributeValueForUsername(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Account_login.Username);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Account_login.Username).getAttribute(attributeName)));
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        	Assert.fail(e.getMessage());
        	reporting.reportSteps("Failure","Could not verify attributeValue for Username", "");
        }
		return $(Account_login.Username).getAttribute(attributeName);
    }

    public void verifyValueClearedForUsername() {
        //This function is for web element @FindBy(Account_login.Username);
        assertTrue("Actual value: " + $(Account_login.Username).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Account_login.Username).getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value is cleared for Username");
    }

    public void hoverOverUsername() {
        //This function is for web element @FindBy(Account_login.Username);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyUsernameIsEnabled();
			serenityActions.moveToElement($(Account_login.Username)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToUsernameElement() {
        //This function is for web element @FindBy(Account_login.Username);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Account_login.Username));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Username element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Username element ");
        };
    }

    public void changeFocusToUsername() {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Account_login.Username));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Username successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Username");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyUsernameContainsText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			assertTrue("Actual text: " + $(Account_login.Username).getText(), StringUtils.containsIgnoreCase($(Account_login.Username).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Username contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForUsername(String attribute, String typeText) {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			assertTrue("Actual value: " + $(Account_login.Username).getAttribute(attribute), StringUtils.contains($(Account_login.Username).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Username contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyUsernameText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			assertTrue("Actual text: " + $(Account_login.Username).getText(), StringUtils.equalsIgnoreCase(typeText,$(Account_login.Username).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Username element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnUsername() {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			waitABit(3000);
			elementIsClickableUsername();
        	$(Account_login.Username).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Username element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Username", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyUsernameIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.Username);
        try{
			waitABit(3000);
			assertTrue("Username is not visible", $(Account_login.Username).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Username is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnLnsa() {
        //The below function is for web element @FindBy(Account_login.LNSA);
        try{
			waitABit(3000);
			elementIsClickableLnsa();
        	$(Account_login.LNSA).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the LNSA element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Lnsa", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableLnsa() {
        //The below function is for web element @FindBy(Account_login.LNSA);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Account_login.LNSA));
        	assertTrue("Lnsa is not clickable", $(Account_login.LNSA).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies LNSA is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromLnsa(String typeText) {
        //This function is for web element @FindBy(Account_login.LNSA);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.LNSA));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Lnsa");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for LNSA", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverLnsa() {
        //This function is for web element @FindBy(Account_login.LNSA);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyLnsaIsEnabled();
			serenityActions.moveToElement($(Account_login.LNSA)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToLnsaElement() {
        //This function is for web element @FindBy(Account_login.LNSA);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Account_login.LNSA));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to LNSA element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to LNSA element ");
        };
    }

    public void verifyLnsaIsEnabled() {
        //The below function is for web element @FindBy(Account_login.LNSA);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.LNSA));
        	assertTrue("Lnsa is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given LNSA element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnLnsa() {
        //This function is for web element @FindBy(Account_login.LNSA);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Account_login.LNSA)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on LNSA element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToLnsa() {
        //The below function is for web element @FindBy(Account_login.LNSA);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Account_login.LNSA));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Lnsa successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Lnsa");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyLnsaIsDisabled() {
        //The below function is for web element @FindBy(Account_login.LNSA);
        Boolean isElementDisabled = false;
			try{
			if($(Account_login.LNSA).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Lnsa element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Lnsa is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyLnsaContainsText(String typeText) {
        //The below function is for web element @FindBy(Account_login.LNSA);
        try{
			assertTrue("Actual text: " + $(Account_login.LNSA).getText(), StringUtils.containsIgnoreCase($(Account_login.LNSA).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies LNSA contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForLnsa(String attribute, String typeText) {
        //The below function is for web element @FindBy(Account_login.LNSA);
        try{
			assertTrue("Actual value: " + $(Account_login.LNSA).getAttribute(attribute), StringUtils.contains($(Account_login.LNSA).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies LNSA contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyLnsaText(String typeText) {
        //The below function is for web element @FindBy(Account_login.LNSA);
        try{
			assertTrue("Actual text: " + $(Account_login.LNSA).getText(), StringUtils.equalsIgnoreCase(typeText,$(Account_login.LNSA).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of LNSA element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyLnsaIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.LNSA);
        try{
			waitABit(3000);
			assertTrue("Lnsa is not visible", $(Account_login.LNSA).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Lnsa is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnForgotPassword() {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        try{
			waitABit(3000);
			elementIsClickableForgotPassword();
        	$(Account_login.forgot_password).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the forgot_password element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click ForgotPassword", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableForgotPassword() {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Account_login.forgot_password));
        	assertTrue("ForgotPassword is not clickable", $(Account_login.forgot_password).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies forgot_password is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromForgotPassword(String typeText) {
        //This function is for web element @FindBy(Account_login.forgot_password);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.forgot_password));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for ForgotPassword");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for forgot_password", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverForgotPassword() {
        //This function is for web element @FindBy(Account_login.forgot_password);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyForgotPasswordIsEnabled();
			serenityActions.moveToElement($(Account_login.forgot_password)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToForgotPasswordElement() {
        //This function is for web element @FindBy(Account_login.forgot_password);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Account_login.forgot_password));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to forgot_password element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to forgot_password element ");
        };
    }

    public void verifyForgotPasswordIsEnabled() {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.forgot_password));
        	assertTrue("ForgotPassword is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given forgot_password element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnForgotPassword() {
        //This function is for web element @FindBy(Account_login.forgot_password);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Account_login.forgot_password)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on forgot_password element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToForgotPassword() {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Account_login.forgot_password));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to ForgotPassword successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to ForgotPassword");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyForgotPasswordIsDisabled() {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        Boolean isElementDisabled = false;
			try{
			if($(Account_login.forgot_password).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "ForgotPassword element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "ForgotPassword is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyForgotPasswordContainsText(String typeText) {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        try{
			assertTrue("Actual text: " + $(Account_login.forgot_password).getText(), StringUtils.containsIgnoreCase($(Account_login.forgot_password).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies forgot_password contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForForgotPassword(String attribute, String typeText) {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        try{
			assertTrue("Actual value: " + $(Account_login.forgot_password).getAttribute(attribute), StringUtils.contains($(Account_login.forgot_password).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies forgot_password contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyForgotPasswordText(String typeText) {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        try{
			assertTrue("Actual text: " + $(Account_login.forgot_password).getText(), StringUtils.equalsIgnoreCase(typeText,$(Account_login.forgot_password).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of forgot_password element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyForgotPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        try{
			waitABit(3000);
			assertTrue("ForgotPassword is not visible", $(Account_login.forgot_password).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "ForgotPassword is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnResetPassword() {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        try{
			waitABit(3000);
			elementIsClickableResetPassword();
        	$(Account_login.Reset_Password).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Reset_Password element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click ResetPassword", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableResetPassword() {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Account_login.Reset_Password));
        	assertTrue("ResetPassword is not clickable", $(Account_login.Reset_Password).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Reset_Password is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromResetPassword(String typeText) {
        //This function is for web element @FindBy(Account_login.Reset_Password);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Reset_Password));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for ResetPassword");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Reset_Password", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverResetPassword() {
        //This function is for web element @FindBy(Account_login.Reset_Password);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyResetPasswordIsEnabled();
			serenityActions.moveToElement($(Account_login.Reset_Password)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToResetPasswordElement() {
        //This function is for web element @FindBy(Account_login.Reset_Password);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Account_login.Reset_Password));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Reset_Password element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Reset_Password element ");
        };
    }

    public void verifyResetPasswordIsEnabled() {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Reset_Password));
        	assertTrue("ResetPassword is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Reset_Password element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnResetPassword() {
        //This function is for web element @FindBy(Account_login.Reset_Password);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Account_login.Reset_Password)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Reset_Password element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToResetPassword() {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Account_login.Reset_Password));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to ResetPassword successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to ResetPassword");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyResetPasswordIsDisabled() {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        Boolean isElementDisabled = false;
			try{
			if($(Account_login.Reset_Password).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "ResetPassword element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "ResetPassword is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyResetPasswordContainsText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        try{
			assertTrue("Actual text: " + $(Account_login.Reset_Password).getText(), StringUtils.containsIgnoreCase($(Account_login.Reset_Password).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Reset_Password contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForResetPassword(String attribute, String typeText) {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        try{
			assertTrue("Actual value: " + $(Account_login.Reset_Password).getAttribute(attribute), StringUtils.contains($(Account_login.Reset_Password).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Reset_Password contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyResetPasswordText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        try{
			assertTrue("Actual text: " + $(Account_login.Reset_Password).getText(), StringUtils.equalsIgnoreCase(typeText,$(Account_login.Reset_Password).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Reset_Password element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyResetPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        try{
			waitABit(3000);
			assertTrue("ResetPassword is not visible", $(Account_login.Reset_Password).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "ResetPassword is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void elementIsClickableEnterYourUsernameAndPassword() {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Account_login.Enter_your_username_and_password));
        	assertTrue("EnterYourUsernameAndPassword is not clickable", $(Account_login.Enter_your_username_and_password).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Enter_your_username_and_password is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyEnterYourUsernameAndPasswordIsEnabled() {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Enter_your_username_and_password));
        	assertTrue("EnterYourUsernameAndPassword is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Enter_your_username_and_password element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromEnterYourUsernameAndPassword(String typeText) {
        //This function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Enter_your_username_and_password));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for EnterYourUsernameAndPassword");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Enter_your_username_and_password", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String verifyAttributeValueForEnterYourUsernameAndPassword(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Account_login.Enter_your_username_and_password).getAttribute(attributeName)));
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        	Assert.fail(e.getMessage());
        	reporting.reportSteps("Failure","Could not verify attributeValue for Enter_your_username_and_password", "");
        }
		return $(Account_login.Enter_your_username_and_password).getAttribute(attributeName);
    }

    public void hoverOverEnterYourUsernameAndPassword() {
        //This function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyEnterYourUsernameAndPasswordIsEnabled();
			serenityActions.moveToElement($(Account_login.Enter_your_username_and_password)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToEnterYourUsernameAndPasswordElement() {
        //This function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Account_login.Enter_your_username_and_password));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Enter_your_username_and_password element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Enter_your_username_and_password element ");
        };
    }

    public void changeFocusToEnterYourUsernameAndPassword() {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Account_login.Enter_your_username_and_password));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to EnterYourUsernameAndPassword successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to EnterYourUsernameAndPassword");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyEnterYourUsernameAndPasswordContainsText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        try{
			assertTrue("Actual text: " + $(Account_login.Enter_your_username_and_password).getText(), StringUtils.containsIgnoreCase($(Account_login.Enter_your_username_and_password).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Enter_your_username_and_password contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForEnterYourUsernameAndPassword(String attribute, String typeText) {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        try{
			assertTrue("Actual value: " + $(Account_login.Enter_your_username_and_password).getAttribute(attribute), StringUtils.contains($(Account_login.Enter_your_username_and_password).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Enter_your_username_and_password contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyEnterYourUsernameAndPasswordText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        try{
			assertTrue("Actual text: " + $(Account_login.Enter_your_username_and_password).getText(), StringUtils.equalsIgnoreCase(typeText,$(Account_login.Enter_your_username_and_password).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Enter_your_username_and_password element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnEnterYourUsernameAndPassword() {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        try{
			waitABit(3000);
			elementIsClickableEnterYourUsernameAndPassword();
        	$(Account_login.Enter_your_username_and_password).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Enter_your_username_and_password element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click EnterYourUsernameAndPassword", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyEnterYourUsernameAndPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        try{
			waitABit(3000);
			assertTrue("EnterYourUsernameAndPassword is not visible", $(Account_login.Enter_your_username_and_password).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "EnterYourUsernameAndPassword is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnSignIn() {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        try{
			waitABit(3000);
			elementIsClickableSignIn();
        	$(Account_login.Sign_in).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Sign_in element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click SignIn", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableSignIn() {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Account_login.Sign_in));
        	assertTrue("SignIn is not clickable", $(Account_login.Sign_in).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Sign_in is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromSignIn(String typeText) {
        //This function is for web element @FindBy(Account_login.Sign_in);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Sign_in));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for SignIn");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Sign_in", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverSignIn() {
        //This function is for web element @FindBy(Account_login.Sign_in);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifySignInIsEnabled();
			serenityActions.moveToElement($(Account_login.Sign_in)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToSignInElement() {
        //This function is for web element @FindBy(Account_login.Sign_in);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Account_login.Sign_in));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Sign_in element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Sign_in element ");
        };
    }

    public void verifySignInIsEnabled() {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Sign_in));
        	assertTrue("SignIn is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Sign_in element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnSignIn() {
        //This function is for web element @FindBy(Account_login.Sign_in);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Account_login.Sign_in)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Sign_in element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToSignIn() {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Account_login.Sign_in));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to SignIn successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to SignIn");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySignInIsDisabled() {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        Boolean isElementDisabled = false;
			try{
			if($(Account_login.Sign_in).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "SignIn element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "SignIn is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySignInContainsText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        try{
			assertTrue("Actual text: " + $(Account_login.Sign_in).getText(), StringUtils.containsIgnoreCase($(Account_login.Sign_in).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Sign_in contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForSignIn(String attribute, String typeText) {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        try{
			assertTrue("Actual value: " + $(Account_login.Sign_in).getAttribute(attribute), StringUtils.contains($(Account_login.Sign_in).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Sign_in contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySignInText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        try{
			assertTrue("Actual text: " + $(Account_login.Sign_in).getText(), StringUtils.equalsIgnoreCase(typeText,$(Account_login.Sign_in).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Sign_in element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySignInIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        try{
			waitABit(3000);
			assertTrue("SignIn is not visible", $(Account_login.Sign_in).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "SignIn is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void elementIsClickablePassword() {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Account_login.Password));
        	assertTrue("Password is not clickable", $(Account_login.Password).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Password is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void typeTextIntoPassword(String typeText) {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Password));
        	typeInto(element,typeText);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Password", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndEnterForPassword(String typeText) {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Password));
        	$(element).typeAndEnter(typeText);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Password", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndTabForPassword(String typeText) {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Password));
        	typeInto(element,typeText);
        	new SerenityActions(getDriver()).sendKeys(Keys.TAB);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Password", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromPassword() {
        //The below function is for web element @FindBy(Account_login.Password);
        String text = "";
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Password));
        	text = element.getText();
			loggerUtils.log(LogLevel.INFO, "Successfully fetched the text of Password element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not get text of Password", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return text;
    }

    public void verifyPasswordIsEnabled() {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Password));
        	assertTrue("Password is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Password element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void clearPassword() {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Password));
        	element.clear();
        	loggerUtils.log(LogLevel.INFO, "User deletes the value for Password element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not clear Password", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyValueFromPassword(String typeText) {
        //This function is for web element @FindBy(Account_login.Password);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Account_login.Password));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Password");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Password", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String verifyAttributeValueForPassword(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Account_login.Password);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Account_login.Password).getAttribute(attributeName)));
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        	Assert.fail(e.getMessage());
        	reporting.reportSteps("Failure","Could not verify attributeValue for Password", "");
        }
		return $(Account_login.Password).getAttribute(attributeName);
    }

    public void verifyValueClearedForPassword() {
        //This function is for web element @FindBy(Account_login.Password);
        assertTrue("Actual value: " + $(Account_login.Password).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Account_login.Password).getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value is cleared for Password");
    }

    public void hoverOverPassword() {
        //This function is for web element @FindBy(Account_login.Password);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyPasswordIsEnabled();
			serenityActions.moveToElement($(Account_login.Password)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToPasswordElement() {
        //This function is for web element @FindBy(Account_login.Password);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Account_login.Password));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Password element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Password element ");
        };
    }

    public void changeFocusToPassword() {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Account_login.Password));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Password successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Password");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyPasswordContainsText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			assertTrue("Actual text: " + $(Account_login.Password).getText(), StringUtils.containsIgnoreCase($(Account_login.Password).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Password contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForPassword(String attribute, String typeText) {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			assertTrue("Actual value: " + $(Account_login.Password).getAttribute(attribute), StringUtils.contains($(Account_login.Password).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Password contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyPasswordText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			assertTrue("Actual text: " + $(Account_login.Password).getText(), StringUtils.equalsIgnoreCase(typeText,$(Account_login.Password).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Password element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnPassword() {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			waitABit(3000);
			elementIsClickablePassword();
        	$(Account_login.Password).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Password element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Password", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.Password);
        try{
			waitABit(3000);
			assertTrue("Password is not visible", $(Account_login.Password).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Password is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }
}
