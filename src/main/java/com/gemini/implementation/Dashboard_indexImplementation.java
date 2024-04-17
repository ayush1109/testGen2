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
import com.gemini.locator.Dashboard_index;
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

public class Dashboard_indexImplementation extends PageObject {

    private LoggerUtils loggerUtils = new EnableSlf4jLogging();

    private Reporting reporting = new EnableSerenityReporting();

    public void clickOnLnsa() {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        try{
			waitABit(3000);
			elementIsClickableLnsa();
        	$(Dashboard_index.LNSA).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the LNSA element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Lnsa", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableLnsa() {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.LNSA));
        	assertTrue("Lnsa is not clickable", $(Dashboard_index.LNSA).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies LNSA is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromLnsa(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.LNSA);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.LNSA));
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
        //This function is for web element @FindBy(Dashboard_index.LNSA);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyLnsaIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.LNSA)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToLnsaElement() {
        //This function is for web element @FindBy(Dashboard_index.LNSA);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.LNSA));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to LNSA element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to LNSA element ");
        };
    }

    public void verifyLnsaIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.LNSA));
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
        //This function is for web element @FindBy(Dashboard_index.LNSA);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.LNSA)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on LNSA element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToLnsa() {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.LNSA));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Lnsa successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Lnsa");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyLnsaIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.LNSA).getAttribute("disabled") != null)
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
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.LNSA).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.LNSA).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies LNSA contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForLnsa(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.LNSA).getAttribute(attribute), StringUtils.contains($(Dashboard_index.LNSA).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies LNSA contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyLnsaText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.LNSA).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.LNSA).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of LNSA element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyLnsaIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        try{
			waitABit(3000);
			assertTrue("Lnsa is not visible", $(Dashboard_index.LNSA).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Lnsa is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnFeedback() {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        try{
			waitABit(3000);
			elementIsClickableFeedback();
        	$(Dashboard_index.Feedback).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Feedback element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Feedback", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableFeedback() {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Feedback));
        	assertTrue("Feedback is not clickable", $(Dashboard_index.Feedback).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Feedback is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromFeedback(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Feedback);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Feedback));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Feedback");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Feedback", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverFeedback() {
        //This function is for web element @FindBy(Dashboard_index.Feedback);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyFeedbackIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Feedback)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToFeedbackElement() {
        //This function is for web element @FindBy(Dashboard_index.Feedback);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Feedback));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Feedback element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Feedback element ");
        };
    }

    public void verifyFeedbackIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Feedback));
        	assertTrue("Feedback is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Feedback element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnFeedback() {
        //This function is for web element @FindBy(Dashboard_index.Feedback);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Feedback)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Feedback element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToFeedback() {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Feedback));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Feedback successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Feedback");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyFeedbackIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Feedback).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Feedback element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Feedback is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyFeedbackContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Feedback).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Feedback).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Feedback contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForFeedback(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Feedback).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Feedback).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Feedback contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyFeedbackText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Feedback).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Feedback).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Feedback element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyFeedbackIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        try{
			waitABit(3000);
			assertTrue("Feedback is not visible", $(Dashboard_index.Feedback).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Feedback is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnReimbursement() {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        try{
			waitABit(3000);
			elementIsClickableReimbursement();
        	$(Dashboard_index.Reimbursement).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Reimbursement element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Reimbursement", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableReimbursement() {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Reimbursement));
        	assertTrue("Reimbursement is not clickable", $(Dashboard_index.Reimbursement).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Reimbursement is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromReimbursement(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Reimbursement);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Reimbursement));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Reimbursement");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Reimbursement", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverReimbursement() {
        //This function is for web element @FindBy(Dashboard_index.Reimbursement);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyReimbursementIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Reimbursement)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToReimbursementElement() {
        //This function is for web element @FindBy(Dashboard_index.Reimbursement);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Reimbursement));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Reimbursement element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Reimbursement element ");
        };
    }

    public void verifyReimbursementIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Reimbursement));
        	assertTrue("Reimbursement is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Reimbursement element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnReimbursement() {
        //This function is for web element @FindBy(Dashboard_index.Reimbursement);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Reimbursement)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Reimbursement element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToReimbursement() {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Reimbursement));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Reimbursement successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Reimbursement");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyReimbursementIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Reimbursement).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Reimbursement element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Reimbursement is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyReimbursementContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Reimbursement).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Reimbursement).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Reimbursement contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForReimbursement(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Reimbursement).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Reimbursement).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Reimbursement contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyReimbursementText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Reimbursement).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Reimbursement).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Reimbursement element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyReimbursementIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        try{
			waitABit(3000);
			assertTrue("Reimbursement is not visible", $(Dashboard_index.Reimbursement).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Reimbursement is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnEmployeeDirectory() {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        try{
			waitABit(3000);
			elementIsClickableEmployeeDirectory();
        	$(Dashboard_index.employee_directory).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the employee_directory element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click EmployeeDirectory", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableEmployeeDirectory() {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.employee_directory));
        	assertTrue("EmployeeDirectory is not clickable", $(Dashboard_index.employee_directory).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies employee_directory is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromEmployeeDirectory(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.employee_directory);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.employee_directory));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for EmployeeDirectory");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for employee_directory", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverEmployeeDirectory() {
        //This function is for web element @FindBy(Dashboard_index.employee_directory);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyEmployeeDirectoryIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.employee_directory)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToEmployeeDirectoryElement() {
        //This function is for web element @FindBy(Dashboard_index.employee_directory);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.employee_directory));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to employee_directory element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to employee_directory element ");
        };
    }

    public void verifyEmployeeDirectoryIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.employee_directory));
        	assertTrue("EmployeeDirectory is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given employee_directory element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnEmployeeDirectory() {
        //This function is for web element @FindBy(Dashboard_index.employee_directory);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.employee_directory)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on employee_directory element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToEmployeeDirectory() {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.employee_directory));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to EmployeeDirectory successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to EmployeeDirectory");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyEmployeeDirectoryIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.employee_directory).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "EmployeeDirectory element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "EmployeeDirectory is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyEmployeeDirectoryContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.employee_directory).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.employee_directory).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies employee_directory contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForEmployeeDirectory(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.employee_directory).getAttribute(attribute), StringUtils.contains($(Dashboard_index.employee_directory).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies employee_directory contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyEmployeeDirectoryText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.employee_directory).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.employee_directory).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of employee_directory element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyEmployeeDirectoryIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        try{
			waitABit(3000);
			assertTrue("EmployeeDirectory is not visible", $(Dashboard_index.employee_directory).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "EmployeeDirectory is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnLunch() {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        try{
			waitABit(3000);
			elementIsClickableLunch();
        	$(Dashboard_index.Lunch).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Lunch element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Lunch", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableLunch() {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Lunch));
        	assertTrue("Lunch is not clickable", $(Dashboard_index.Lunch).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Lunch is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromLunch(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Lunch);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Lunch));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Lunch");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Lunch", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverLunch() {
        //This function is for web element @FindBy(Dashboard_index.Lunch);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyLunchIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Lunch)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToLunchElement() {
        //This function is for web element @FindBy(Dashboard_index.Lunch);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Lunch));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Lunch element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Lunch element ");
        };
    }

    public void verifyLunchIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Lunch));
        	assertTrue("Lunch is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Lunch element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnLunch() {
        //This function is for web element @FindBy(Dashboard_index.Lunch);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Lunch)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Lunch element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToLunch() {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Lunch));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Lunch successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Lunch");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyLunchIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Lunch).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Lunch element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Lunch is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyLunchContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Lunch).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Lunch).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Lunch contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForLunch(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Lunch).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Lunch).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Lunch contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyLunchText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Lunch).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Lunch).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Lunch element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyLunchIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        try{
			waitABit(3000);
			assertTrue("Lunch is not visible", $(Dashboard_index.Lunch).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Lunch is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void elementIsClickableCanaanTower() {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Canaan_Tower));
        	assertTrue("CanaanTower is not clickable", $(Dashboard_index.Canaan_Tower).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Canaan_Tower is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyCanaanTowerIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Canaan_Tower));
        	assertTrue("CanaanTower is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Canaan_Tower element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromCanaanTower(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Canaan_Tower));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for CanaanTower");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Canaan_Tower", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String verifyAttributeValueForCanaanTower(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Dashboard_index.Canaan_Tower).getAttribute(attributeName)));
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        	Assert.fail(e.getMessage());
        	reporting.reportSteps("Failure","Could not verify attributeValue for Canaan_Tower", "");
        }
		return $(Dashboard_index.Canaan_Tower).getAttribute(attributeName);
    }

    public void hoverOverCanaanTower() {
        //This function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyCanaanTowerIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Canaan_Tower)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToCanaanTowerElement() {
        //This function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Canaan_Tower));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Canaan_Tower element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Canaan_Tower element ");
        };
    }

    public void changeFocusToCanaanTower() {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Canaan_Tower));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to CanaanTower successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to CanaanTower");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyCanaanTowerContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Canaan_Tower).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Canaan_Tower).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Canaan_Tower contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForCanaanTower(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Canaan_Tower).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Canaan_Tower).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Canaan_Tower contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyCanaanTowerText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Canaan_Tower).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Canaan_Tower).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Canaan_Tower element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnCanaanTower() {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        try{
			waitABit(3000);
			elementIsClickableCanaanTower();
        	$(Dashboard_index.Canaan_Tower).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Canaan_Tower element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click CanaanTower", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyCanaanTowerIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        try{
			waitABit(3000);
			assertTrue("CanaanTower is not visible", $(Dashboard_index.Canaan_Tower).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "CanaanTower is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void elementIsClickableLocation() {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Location));
        	assertTrue("Location is not clickable", $(Dashboard_index.Location).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Location is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyLocationIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Location));
        	assertTrue("Location is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Location element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromLocation(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Location);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Location));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Location");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Location", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String verifyAttributeValueForLocation(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Dashboard_index.Location);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Dashboard_index.Location).getAttribute(attributeName)));
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        	Assert.fail(e.getMessage());
        	reporting.reportSteps("Failure","Could not verify attributeValue for Location", "");
        }
		return $(Dashboard_index.Location).getAttribute(attributeName);
    }

    public void hoverOverLocation() {
        //This function is for web element @FindBy(Dashboard_index.Location);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyLocationIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Location)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToLocationElement() {
        //This function is for web element @FindBy(Dashboard_index.Location);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Location));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Location element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Location element ");
        };
    }

    public void changeFocusToLocation() {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Location));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Location successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Location");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyLocationContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Location).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Location).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Location contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForLocation(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Location).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Location).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Location contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyLocationText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Location).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Location).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Location element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnLocation() {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        try{
			waitABit(3000);
			elementIsClickableLocation();
        	$(Dashboard_index.Location).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Location element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Location", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyLocationIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        try{
			waitABit(3000);
			assertTrue("Location is not visible", $(Dashboard_index.Location).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Location is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnViewPolicies() {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        try{
			waitABit(3000);
			elementIsClickableViewPolicies();
        	$(Dashboard_index.view_policies).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the view_policies element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click ViewPolicies", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableViewPolicies() {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.view_policies));
        	assertTrue("ViewPolicies is not clickable", $(Dashboard_index.view_policies).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies view_policies is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromViewPolicies(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.view_policies);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.view_policies));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for ViewPolicies");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for view_policies", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverViewPolicies() {
        //This function is for web element @FindBy(Dashboard_index.view_policies);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyViewPoliciesIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.view_policies)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToViewPoliciesElement() {
        //This function is for web element @FindBy(Dashboard_index.view_policies);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.view_policies));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to view_policies element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to view_policies element ");
        };
    }

    public void verifyViewPoliciesIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.view_policies));
        	assertTrue("ViewPolicies is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given view_policies element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnViewPolicies() {
        //This function is for web element @FindBy(Dashboard_index.view_policies);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.view_policies)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on view_policies element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToViewPolicies() {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.view_policies));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to ViewPolicies successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to ViewPolicies");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyViewPoliciesIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.view_policies).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "ViewPolicies element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "ViewPolicies is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyViewPoliciesContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.view_policies).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.view_policies).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies view_policies contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForViewPolicies(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.view_policies).getAttribute(attribute), StringUtils.contains($(Dashboard_index.view_policies).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies view_policies contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyViewPoliciesText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.view_policies).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.view_policies).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of view_policies element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyViewPoliciesIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        try{
			waitABit(3000);
			assertTrue("ViewPolicies is not visible", $(Dashboard_index.view_policies).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "ViewPolicies is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnPolicy() {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        try{
			waitABit(3000);
			elementIsClickablePolicy();
        	$(Dashboard_index.policy).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the policy element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Policy", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickablePolicy() {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.policy));
        	assertTrue("Policy is not clickable", $(Dashboard_index.policy).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies policy is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromPolicy(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.policy);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.policy));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Policy");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for policy", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverPolicy() {
        //This function is for web element @FindBy(Dashboard_index.policy);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyPolicyIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.policy)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToPolicyElement() {
        //This function is for web element @FindBy(Dashboard_index.policy);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.policy));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to policy element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to policy element ");
        };
    }

    public void verifyPolicyIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.policy));
        	assertTrue("Policy is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given policy element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnPolicy() {
        //This function is for web element @FindBy(Dashboard_index.policy);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.policy)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on policy element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToPolicy() {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.policy));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Policy successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Policy");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyPolicyIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.policy).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Policy element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Policy is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyPolicyContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.policy).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.policy).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies policy contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForPolicy(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.policy).getAttribute(attribute), StringUtils.contains($(Dashboard_index.policy).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies policy contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyPolicyText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.policy).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.policy).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of policy element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyPolicyIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        try{
			waitABit(3000);
			assertTrue("Policy is not visible", $(Dashboard_index.policy).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Policy is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnLeaveManagement() {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        try{
			waitABit(3000);
			elementIsClickableLeaveManagement();
        	$(Dashboard_index.Leave_Management).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Leave_Management element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click LeaveManagement", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableLeaveManagement() {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Leave_Management));
        	assertTrue("LeaveManagement is not clickable", $(Dashboard_index.Leave_Management).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Leave_Management is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromLeaveManagement(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Leave_Management);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Leave_Management));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for LeaveManagement");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Leave_Management", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverLeaveManagement() {
        //This function is for web element @FindBy(Dashboard_index.Leave_Management);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyLeaveManagementIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Leave_Management)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToLeaveManagementElement() {
        //This function is for web element @FindBy(Dashboard_index.Leave_Management);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Leave_Management));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Leave_Management element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Leave_Management element ");
        };
    }

    public void verifyLeaveManagementIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Leave_Management));
        	assertTrue("LeaveManagement is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Leave_Management element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnLeaveManagement() {
        //This function is for web element @FindBy(Dashboard_index.Leave_Management);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Leave_Management)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Leave_Management element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToLeaveManagement() {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Leave_Management));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to LeaveManagement successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to LeaveManagement");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyLeaveManagementIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Leave_Management).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "LeaveManagement element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "LeaveManagement is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyLeaveManagementContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Leave_Management).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Leave_Management).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Leave_Management contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForLeaveManagement(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Leave_Management).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Leave_Management).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Leave_Management contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyLeaveManagementText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Leave_Management).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Leave_Management).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Leave_Management element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyLeaveManagementIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        try{
			waitABit(3000);
			assertTrue("LeaveManagement is not visible", $(Dashboard_index.Leave_Management).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "LeaveManagement is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnTimesheet() {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        try{
			waitABit(3000);
			elementIsClickableTimesheet();
        	$(Dashboard_index.timesheet).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the timesheet element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Timesheet", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableTimesheet() {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.timesheet));
        	assertTrue("Timesheet is not clickable", $(Dashboard_index.timesheet).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies timesheet is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromTimesheet(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.timesheet);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.timesheet));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Timesheet");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for timesheet", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverTimesheet() {
        //This function is for web element @FindBy(Dashboard_index.timesheet);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyTimesheetIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.timesheet)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToTimesheetElement() {
        //This function is for web element @FindBy(Dashboard_index.timesheet);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.timesheet));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to timesheet element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to timesheet element ");
        };
    }

    public void verifyTimesheetIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.timesheet));
        	assertTrue("Timesheet is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given timesheet element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnTimesheet() {
        //This function is for web element @FindBy(Dashboard_index.timesheet);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.timesheet)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on timesheet element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToTimesheet() {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.timesheet));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Timesheet successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Timesheet");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyTimesheetIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.timesheet).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Timesheet element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Timesheet is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyTimesheetContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.timesheet).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.timesheet).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies timesheet contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForTimesheet(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.timesheet).getAttribute(attribute), StringUtils.contains($(Dashboard_index.timesheet).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies timesheet contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyTimesheetText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.timesheet).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.timesheet).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of timesheet element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyTimesheetIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        try{
			waitABit(3000);
			assertTrue("Timesheet is not visible", $(Dashboard_index.timesheet).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Timesheet is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnCustomizeYourHolidays() {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        try{
			waitABit(3000);
			elementIsClickableCustomizeYourHolidays();
        	$(Dashboard_index.Customize_your_holidays).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Customize_your_holidays element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click CustomizeYourHolidays", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableCustomizeYourHolidays() {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Customize_your_holidays));
        	assertTrue("CustomizeYourHolidays is not clickable", $(Dashboard_index.Customize_your_holidays).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Customize_your_holidays is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromCustomizeYourHolidays(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Customize_your_holidays));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for CustomizeYourHolidays");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Customize_your_holidays", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverCustomizeYourHolidays() {
        //This function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyCustomizeYourHolidaysIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Customize_your_holidays)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToCustomizeYourHolidaysElement() {
        //This function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Customize_your_holidays));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Customize_your_holidays element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Customize_your_holidays element ");
        };
    }

    public void verifyCustomizeYourHolidaysIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Customize_your_holidays));
        	assertTrue("CustomizeYourHolidays is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Customize_your_holidays element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnCustomizeYourHolidays() {
        //This function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Customize_your_holidays)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Customize_your_holidays element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToCustomizeYourHolidays() {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Customize_your_holidays));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to CustomizeYourHolidays successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to CustomizeYourHolidays");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyCustomizeYourHolidaysIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Customize_your_holidays).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "CustomizeYourHolidays element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "CustomizeYourHolidays is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyCustomizeYourHolidaysContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Customize_your_holidays).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Customize_your_holidays).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Customize_your_holidays contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForCustomizeYourHolidays(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Customize_your_holidays).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Customize_your_holidays).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Customize_your_holidays contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyCustomizeYourHolidaysText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Customize_your_holidays).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Customize_your_holidays).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Customize_your_holidays element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyCustomizeYourHolidaysIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        try{
			waitABit(3000);
			assertTrue("CustomizeYourHolidays is not visible", $(Dashboard_index.Customize_your_holidays).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "CustomizeYourHolidays is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnBtnupdateprofile() {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        try{
			waitABit(3000);
			elementIsClickableBtnupdateprofile();
        	$(Dashboard_index.btnUpdateProfile).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the btnUpdateProfile element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Btnupdateprofile", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableBtnupdateprofile() {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.btnUpdateProfile));
        	assertTrue("Btnupdateprofile is not clickable", $(Dashboard_index.btnUpdateProfile).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies btnUpdateProfile is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromBtnupdateprofile(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.btnUpdateProfile));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Btnupdateprofile");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for btnUpdateProfile", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverBtnupdateprofile() {
        //This function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyBtnupdateprofileIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.btnUpdateProfile)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToBtnupdateprofileElement() {
        //This function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.btnUpdateProfile));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to btnUpdateProfile element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to btnUpdateProfile element ");
        };
    }

    public void verifyBtnupdateprofileIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.btnUpdateProfile));
        	assertTrue("Btnupdateprofile is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given btnUpdateProfile element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnBtnupdateprofile() {
        //This function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.btnUpdateProfile)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on btnUpdateProfile element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToBtnupdateprofile() {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.btnUpdateProfile));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Btnupdateprofile successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Btnupdateprofile");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyBtnupdateprofileIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.btnUpdateProfile).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Btnupdateprofile element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Btnupdateprofile is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyBtnupdateprofileContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.btnUpdateProfile).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.btnUpdateProfile).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies btnUpdateProfile contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForBtnupdateprofile(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.btnUpdateProfile).getAttribute(attribute), StringUtils.contains($(Dashboard_index.btnUpdateProfile).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies btnUpdateProfile contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyBtnupdateprofileText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.btnUpdateProfile).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.btnUpdateProfile).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of btnUpdateProfile element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyBtnupdateprofileIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        try{
			waitABit(3000);
			assertTrue("Btnupdateprofile is not visible", $(Dashboard_index.btnUpdateProfile).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Btnupdateprofile is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnApply() {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        try{
			waitABit(3000);
			elementIsClickableApply();
        	$(Dashboard_index.Apply).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Apply element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Apply", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableApply() {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Apply));
        	assertTrue("Apply is not clickable", $(Dashboard_index.Apply).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Apply is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromApply(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Apply);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Apply));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Apply");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Apply", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverApply() {
        //This function is for web element @FindBy(Dashboard_index.Apply);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyApplyIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Apply)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToApplyElement() {
        //This function is for web element @FindBy(Dashboard_index.Apply);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Apply));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Apply element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Apply element ");
        };
    }

    public void verifyApplyIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Apply));
        	assertTrue("Apply is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Apply element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnApply() {
        //This function is for web element @FindBy(Dashboard_index.Apply);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Apply)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Apply element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToApply() {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Apply));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Apply successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Apply");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyApplyIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Apply).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Apply element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Apply is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyApplyContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Apply).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Apply).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Apply contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForApply(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Apply).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Apply).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Apply contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyApplyText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Apply).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Apply).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Apply element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyApplyIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        try{
			waitABit(3000);
			assertTrue("Apply is not visible", $(Dashboard_index.Apply).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Apply is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void elementIsClickableSearch() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Search));
        	assertTrue("Search is not clickable", $(Dashboard_index.Search).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Search is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void typeTextIntoSearch(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Search));
        	typeInto(element,typeText);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Search", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndEnterForSearch(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Search));
        	$(element).typeAndEnter(typeText);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Search", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndTabForSearch(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Search));
        	typeInto(element,typeText);
        	new SerenityActions(getDriver()).sendKeys(Keys.TAB);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Search", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromSearch() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        String text = "";
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Search));
        	text = element.getText();
			loggerUtils.log(LogLevel.INFO, "Successfully fetched the text of Search element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not get text of Search", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return text;
    }

    public void verifySearchIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Search));
        	assertTrue("Search is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Search element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void clearSearch() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Search));
        	element.clear();
        	loggerUtils.log(LogLevel.INFO, "User deletes the value for Search element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not clear Search", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyValueFromSearch(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Search);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Search));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Search");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Search", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String verifyAttributeValueForSearch(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Dashboard_index.Search);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Dashboard_index.Search).getAttribute(attributeName)));
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        	Assert.fail(e.getMessage());
        	reporting.reportSteps("Failure","Could not verify attributeValue for Search", "");
        }
		return $(Dashboard_index.Search).getAttribute(attributeName);
    }

    public void verifyValueClearedForSearch() {
        //This function is for web element @FindBy(Dashboard_index.Search);
        assertTrue("Actual value: " + $(Dashboard_index.Search).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Dashboard_index.Search).getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value is cleared for Search");
    }

    public void hoverOverSearch() {
        //This function is for web element @FindBy(Dashboard_index.Search);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifySearchIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Search)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToSearchElement() {
        //This function is for web element @FindBy(Dashboard_index.Search);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Search));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Search element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Search element ");
        };
    }

    public void changeFocusToSearch() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Search));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Search successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Search");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySearchContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Search).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Search).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Search contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForSearch(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Search).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Search).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Search contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySearchText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Search).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Search).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Search element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnSearch() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			waitABit(3000);
			elementIsClickableSearch();
        	$(Dashboard_index.Search).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Search element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Search", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifySearchIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        try{
			waitABit(3000);
			assertTrue("Search is not visible", $(Dashboard_index.Search).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Search is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnApplyLnsa() {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        try{
			waitABit(3000);
			elementIsClickableApplyLnsa();
        	$(Dashboard_index.Apply_LNSA).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Apply_LNSA element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click ApplyLnsa", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableApplyLnsa() {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Apply_LNSA));
        	assertTrue("ApplyLnsa is not clickable", $(Dashboard_index.Apply_LNSA).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Apply_LNSA is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromApplyLnsa(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Apply_LNSA));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for ApplyLnsa");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Apply_LNSA", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverApplyLnsa() {
        //This function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyApplyLnsaIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Apply_LNSA)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToApplyLnsaElement() {
        //This function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Apply_LNSA));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Apply_LNSA element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Apply_LNSA element ");
        };
    }

    public void verifyApplyLnsaIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Apply_LNSA));
        	assertTrue("ApplyLnsa is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Apply_LNSA element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnApplyLnsa() {
        //This function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Apply_LNSA)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Apply_LNSA element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToApplyLnsa() {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Apply_LNSA));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to ApplyLnsa successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to ApplyLnsa");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyApplyLnsaIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Apply_LNSA).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "ApplyLnsa element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "ApplyLnsa is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyApplyLnsaContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Apply_LNSA).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Apply_LNSA).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Apply_LNSA contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForApplyLnsa(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Apply_LNSA).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Apply_LNSA).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Apply_LNSA contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyApplyLnsaText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Apply_LNSA).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Apply_LNSA).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Apply_LNSA element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyApplyLnsaIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        try{
			waitABit(3000);
			assertTrue("ApplyLnsa is not visible", $(Dashboard_index.Apply_LNSA).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "ApplyLnsa is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnOrganizationStructure() {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        try{
			waitABit(3000);
			elementIsClickableOrganizationStructure();
        	$(Dashboard_index.Organization_Structure).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Organization_Structure element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click OrganizationStructure", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableOrganizationStructure() {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Organization_Structure));
        	assertTrue("OrganizationStructure is not clickable", $(Dashboard_index.Organization_Structure).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Organization_Structure is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromOrganizationStructure(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Organization_Structure);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Organization_Structure));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for OrganizationStructure");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Organization_Structure", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverOrganizationStructure() {
        //This function is for web element @FindBy(Dashboard_index.Organization_Structure);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyOrganizationStructureIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Organization_Structure)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToOrganizationStructureElement() {
        //This function is for web element @FindBy(Dashboard_index.Organization_Structure);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Organization_Structure));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Organization_Structure element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Organization_Structure element ");
        };
    }

    public void verifyOrganizationStructureIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Organization_Structure));
        	assertTrue("OrganizationStructure is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Organization_Structure element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnOrganizationStructure() {
        //This function is for web element @FindBy(Dashboard_index.Organization_Structure);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Organization_Structure)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Organization_Structure element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToOrganizationStructure() {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Organization_Structure));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to OrganizationStructure successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to OrganizationStructure");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyOrganizationStructureIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Organization_Structure).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "OrganizationStructure element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "OrganizationStructure is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyOrganizationStructureContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Organization_Structure).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Organization_Structure).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Organization_Structure contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForOrganizationStructure(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Organization_Structure).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Organization_Structure).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Organization_Structure contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyOrganizationStructureText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Organization_Structure).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Organization_Structure).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Organization_Structure element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyOrganizationStructureIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        try{
			waitABit(3000);
			assertTrue("OrganizationStructure is not visible", $(Dashboard_index.Organization_Structure).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "OrganizationStructure is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnReviewRequest() {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        try{
			waitABit(3000);
			elementIsClickableReviewRequest();
        	$(Dashboard_index.Review_Request).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Review_Request element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click ReviewRequest", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableReviewRequest() {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Review_Request));
        	assertTrue("ReviewRequest is not clickable", $(Dashboard_index.Review_Request).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Review_Request is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromReviewRequest(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Review_Request);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Review_Request));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for ReviewRequest");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Review_Request", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverReviewRequest() {
        //This function is for web element @FindBy(Dashboard_index.Review_Request);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyReviewRequestIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Review_Request)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToReviewRequestElement() {
        //This function is for web element @FindBy(Dashboard_index.Review_Request);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Review_Request));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Review_Request element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Review_Request element ");
        };
    }

    public void verifyReviewRequestIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Review_Request));
        	assertTrue("ReviewRequest is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Review_Request element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnReviewRequest() {
        //This function is for web element @FindBy(Dashboard_index.Review_Request);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Review_Request)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Review_Request element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToReviewRequest() {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Review_Request));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to ReviewRequest successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to ReviewRequest");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyReviewRequestIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Review_Request).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "ReviewRequest element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "ReviewRequest is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyReviewRequestContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Review_Request).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Review_Request).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Review_Request contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForReviewRequest(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Review_Request).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Review_Request).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Review_Request contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyReviewRequestText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Review_Request).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Review_Request).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Review_Request element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyReviewRequestIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        try{
			waitABit(3000);
			assertTrue("ReviewRequest is not visible", $(Dashboard_index.Review_Request).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "ReviewRequest is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnSubmitFeedback() {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        try{
			waitABit(3000);
			elementIsClickableSubmitFeedback();
        	$(Dashboard_index.Submit_Feedback).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Submit_Feedback element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click SubmitFeedback", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableSubmitFeedback() {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Submit_Feedback));
        	assertTrue("SubmitFeedback is not clickable", $(Dashboard_index.Submit_Feedback).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Submit_Feedback is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromSubmitFeedback(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Submit_Feedback));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for SubmitFeedback");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Submit_Feedback", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverSubmitFeedback() {
        //This function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifySubmitFeedbackIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Submit_Feedback)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToSubmitFeedbackElement() {
        //This function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Submit_Feedback));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Submit_Feedback element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Submit_Feedback element ");
        };
    }

    public void verifySubmitFeedbackIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Submit_Feedback));
        	assertTrue("SubmitFeedback is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Submit_Feedback element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnSubmitFeedback() {
        //This function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Submit_Feedback)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Submit_Feedback element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToSubmitFeedback() {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Submit_Feedback));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to SubmitFeedback successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to SubmitFeedback");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySubmitFeedbackIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Submit_Feedback).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "SubmitFeedback element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "SubmitFeedback is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySubmitFeedbackContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Submit_Feedback).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Submit_Feedback).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Submit_Feedback contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForSubmitFeedback(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Submit_Feedback).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Submit_Feedback).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Submit_Feedback contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySubmitFeedbackText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Submit_Feedback).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Submit_Feedback).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Submit_Feedback element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySubmitFeedbackIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        try{
			waitABit(3000);
			assertTrue("SubmitFeedback is not visible", $(Dashboard_index.Submit_Feedback).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "SubmitFeedback is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnSystemUpgradeRequest() {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        try{
			waitABit(3000);
			elementIsClickableSystemUpgradeRequest();
        	$(Dashboard_index.System_Upgrade_Request).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the System_Upgrade_Request element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click SystemUpgradeRequest", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableSystemUpgradeRequest() {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.System_Upgrade_Request));
        	assertTrue("SystemUpgradeRequest is not clickable", $(Dashboard_index.System_Upgrade_Request).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies System_Upgrade_Request is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromSystemUpgradeRequest(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.System_Upgrade_Request));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for SystemUpgradeRequest");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for System_Upgrade_Request", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverSystemUpgradeRequest() {
        //This function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifySystemUpgradeRequestIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.System_Upgrade_Request)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToSystemUpgradeRequestElement() {
        //This function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.System_Upgrade_Request));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to System_Upgrade_Request element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to System_Upgrade_Request element ");
        };
    }

    public void verifySystemUpgradeRequestIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.System_Upgrade_Request));
        	assertTrue("SystemUpgradeRequest is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given System_Upgrade_Request element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnSystemUpgradeRequest() {
        //This function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.System_Upgrade_Request)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on System_Upgrade_Request element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToSystemUpgradeRequest() {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.System_Upgrade_Request));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to SystemUpgradeRequest successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to SystemUpgradeRequest");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySystemUpgradeRequestIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.System_Upgrade_Request).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "SystemUpgradeRequest element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "SystemUpgradeRequest is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySystemUpgradeRequestContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.System_Upgrade_Request).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.System_Upgrade_Request).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies System_Upgrade_Request contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForSystemUpgradeRequest(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.System_Upgrade_Request).getAttribute(attribute), StringUtils.contains($(Dashboard_index.System_Upgrade_Request).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies System_Upgrade_Request contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySystemUpgradeRequestText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.System_Upgrade_Request).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.System_Upgrade_Request).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of System_Upgrade_Request element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySystemUpgradeRequestIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        try{
			waitABit(3000);
			assertTrue("SystemUpgradeRequest is not visible", $(Dashboard_index.System_Upgrade_Request).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "SystemUpgradeRequest is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnConfigureTimesheet() {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        try{
			waitABit(3000);
			elementIsClickableConfigureTimesheet();
        	$(Dashboard_index.configure_timesheet).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the configure_timesheet element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click ConfigureTimesheet", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableConfigureTimesheet() {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.configure_timesheet));
        	assertTrue("ConfigureTimesheet is not clickable", $(Dashboard_index.configure_timesheet).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies configure_timesheet is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromConfigureTimesheet(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.configure_timesheet);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.configure_timesheet));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for ConfigureTimesheet");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for configure_timesheet", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverConfigureTimesheet() {
        //This function is for web element @FindBy(Dashboard_index.configure_timesheet);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyConfigureTimesheetIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.configure_timesheet)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToConfigureTimesheetElement() {
        //This function is for web element @FindBy(Dashboard_index.configure_timesheet);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.configure_timesheet));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to configure_timesheet element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to configure_timesheet element ");
        };
    }

    public void verifyConfigureTimesheetIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.configure_timesheet));
        	assertTrue("ConfigureTimesheet is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given configure_timesheet element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnConfigureTimesheet() {
        //This function is for web element @FindBy(Dashboard_index.configure_timesheet);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.configure_timesheet)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on configure_timesheet element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToConfigureTimesheet() {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.configure_timesheet));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to ConfigureTimesheet successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to ConfigureTimesheet");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyConfigureTimesheetIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.configure_timesheet).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "ConfigureTimesheet element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "ConfigureTimesheet is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyConfigureTimesheetContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.configure_timesheet).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.configure_timesheet).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies configure_timesheet contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForConfigureTimesheet(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.configure_timesheet).getAttribute(attribute), StringUtils.contains($(Dashboard_index.configure_timesheet).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies configure_timesheet contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyConfigureTimesheetText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.configure_timesheet).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.configure_timesheet).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of configure_timesheet element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyConfigureTimesheetIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        try{
			waitABit(3000);
			assertTrue("ConfigureTimesheet is not visible", $(Dashboard_index.configure_timesheet).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "ConfigureTimesheet is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnNext() {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        try{
			waitABit(3000);
			elementIsClickableNext();
        	$(Dashboard_index.Next).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Next element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Next", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableNext() {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Next));
        	assertTrue("Next is not clickable", $(Dashboard_index.Next).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Next is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromNext(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Next);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Next));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Next");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Next", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverNext() {
        //This function is for web element @FindBy(Dashboard_index.Next);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyNextIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Next)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToNextElement() {
        //This function is for web element @FindBy(Dashboard_index.Next);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Next));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Next element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Next element ");
        };
    }

    public void verifyNextIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Next));
        	assertTrue("Next is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Next element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnNext() {
        //This function is for web element @FindBy(Dashboard_index.Next);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Next)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Next element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToNext() {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Next));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Next successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Next");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyNextIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Next).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Next element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Next is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyNextContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Next).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Next).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Next contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForNext(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Next).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Next).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Next contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyNextText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Next).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Next).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Next element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyNextIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        try{
			waitABit(3000);
			assertTrue("Next is not visible", $(Dashboard_index.Next).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Next is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void elementIsClickableContactno() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.contactNo));
        	assertTrue("Contactno is not clickable", $(Dashboard_index.contactNo).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies contactNo is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void typeTextIntoContactno(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.contactNo));
        	typeInto(element,typeText);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Contactno", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndEnterForContactno(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.contactNo));
        	$(element).typeAndEnter(typeText);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value and presses enter");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Contactno", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void typeTextAndTabForContactno(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.contactNo));
        	typeInto(element,typeText);
        	new SerenityActions(getDriver()).sendKeys(Keys.TAB);
        	loggerUtils.log(LogLevel.INFO, "User enters "+typeText+" as value and presses Tab");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not enter " + typeText + " into Contactno", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String getTextFromContactno() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        String text = "";
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.contactNo));
        	text = element.getText();
			loggerUtils.log(LogLevel.INFO, "Successfully fetched the text of contactNo element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not get text of Contactno", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        }
		return text;
    }

    public void verifyContactnoIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.contactNo));
        	assertTrue("Contactno is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given contactNo element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void clearContactno() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.contactNo));
        	element.clear();
        	loggerUtils.log(LogLevel.INFO, "User deletes the value for contactNo element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not clear Contactno", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyValueFromContactno(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.contactNo);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.contactNo));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Contactno");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for contactNo", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public String verifyAttributeValueForContactno(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			assertTrue(StringUtils.equalsIgnoreCase(attributeValue,$(Dashboard_index.contactNo).getAttribute(attributeName)));
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        	Assert.fail(e.getMessage());
        	reporting.reportSteps("Failure","Could not verify attributeValue for contactNo", "");
        }
		return $(Dashboard_index.contactNo).getAttribute(attributeName);
    }

    public void verifyValueClearedForContactno() {
        //This function is for web element @FindBy(Dashboard_index.contactNo);
        assertTrue("Actual value: " + $(Dashboard_index.contactNo).getAttribute("value"), StringUtils.equalsIgnoreCase("", $(Dashboard_index.contactNo).getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value is cleared for Contactno");
    }

    public void hoverOverContactno() {
        //This function is for web element @FindBy(Dashboard_index.contactNo);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyContactnoIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.contactNo)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToContactnoElement() {
        //This function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.contactNo));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to contactNo element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to contactNo element ");
        };
    }

    public void changeFocusToContactno() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.contactNo));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Contactno successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Contactno");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyContactnoContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.contactNo).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.contactNo).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies contactNo contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForContactno(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.contactNo).getAttribute(attribute), StringUtils.contains($(Dashboard_index.contactNo).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies contactNo contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyContactnoText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.contactNo).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.contactNo).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of contactNo element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnContactno() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			waitABit(3000);
			elementIsClickableContactno();
        	$(Dashboard_index.contactNo).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the contactNo element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Contactno", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void verifyContactnoIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        try{
			waitABit(3000);
			assertTrue("Contactno is not visible", $(Dashboard_index.contactNo).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Contactno is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnUpdate() {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        try{
			waitABit(3000);
			elementIsClickableUpdate();
        	$(Dashboard_index.Update).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Update element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Update", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableUpdate() {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Update));
        	assertTrue("Update is not clickable", $(Dashboard_index.Update).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Update is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromUpdate(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Update);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Update));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Update");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Update", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverUpdate() {
        //This function is for web element @FindBy(Dashboard_index.Update);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifyUpdateIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Update)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToUpdateElement() {
        //This function is for web element @FindBy(Dashboard_index.Update);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Update));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Update element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Update element ");
        };
    }

    public void verifyUpdateIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Update));
        	assertTrue("Update is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Update element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnUpdate() {
        //This function is for web element @FindBy(Dashboard_index.Update);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Update)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Update element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToUpdate() {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Update));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Update successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Update");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyUpdateIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Update).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Update element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Update is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifyUpdateContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Update).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Update).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Update contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForUpdate(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Update).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Update).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Update contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyUpdateText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Update).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Update).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Update element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyUpdateIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        try{
			waitABit(3000);
			assertTrue("Update is not visible", $(Dashboard_index.Update).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Update is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnSkills() {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        try{
			waitABit(3000);
			elementIsClickableSkills();
        	$(Dashboard_index.Skills).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Skills element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Skills", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableSkills() {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Skills));
        	assertTrue("Skills is not clickable", $(Dashboard_index.Skills).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Skills is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromSkills(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Skills);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Skills));
		assertTrue("Actual value: " + element.getAttribute("value"), StringUtils.equalsIgnoreCase(typeText,element.getAttribute("value")));
        loggerUtils.log(LogLevel.INFO, "User verifies value: " + typeText + "for Skills");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not verify value for Skills", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void hoverOverSkills() {
        //This function is for web element @FindBy(Dashboard_index.Skills);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifySkillsIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Skills)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToSkillsElement() {
        //This function is for web element @FindBy(Dashboard_index.Skills);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Skills));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Skills element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Skills element ");
        };
    }

    public void verifySkillsIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Skills));
        	assertTrue("Skills is not enabled", element.isEnabled());
        	loggerUtils.log(LogLevel.INFO, "User verifies the given Skills element is enabled");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Element is not enabled", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void rightClickOnSkills() {
        //This function is for web element @FindBy(Dashboard_index.Skills);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Skills)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Skills element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToSkills() {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Skills));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Skills successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Skills");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySkillsIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Skills).getAttribute("disabled") != null)
			{;
        	loggerUtils.log(LogLevel.INFO, "Skills element is disabled");
        	}}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Skills is not disabled");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySkillsContainsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Skills).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Skills).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Skills contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForSkills(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Skills).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Skills).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Skills contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySkillsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Skills).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Skills).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Skills element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySkillsIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        try{
			waitABit(3000);
			assertTrue("Skills is not visible", $(Dashboard_index.Skills).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Skills is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void clickOnSave() {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        try{
			waitABit(3000);
			elementIsClickableSave();
        	$(Dashboard_index.Save).click();
        	loggerUtils.log(LogLevel.INFO, "User click on the Save element");
        }
		catch(Exception e){
			reporting.reportSteps("Failure", "Could not click Save", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        	Assert.fail(e.getMessage());
        };
    }

    public void elementIsClickableSave() {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        try{
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(Dashboard_index.Save));
        	assertTrue("Save is not clickable", $(Dashboard_index.Save).isClickable());
        	loggerUtils.log(LogLevel.INFO, "User verifies Save is clickable");
        }
		catch(Exception e){
			Assert.fail(e.getMessage());
			reporting.reportSteps("Failure", "Unable to click", "");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e.getMessage());
        };
    }

    public void verifyValueFromSave(String typeText) {
        //This function is for web element @FindBy(Dashboard_index.Save);
        try{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Save));
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
        //This function is for web element @FindBy(Dashboard_index.Save);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			verifySaveIsEnabled();
			serenityActions.moveToElement($(Dashboard_index.Save)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully hovers over");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void scrollToSaveElement() {
        //This function is for web element @FindBy(Dashboard_index.Save);
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", $(Dashboard_index.Save));
        	loggerUtils.log(LogLevel.INFO, " Successfully scrolled to Save element ");}	
			catch(Exception e){;
        		Assert.fail(" Unable to scroll to Save element ");
        };
    }

    public void verifySaveIsEnabled() {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut"))));
        	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard_index.Save));
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
        //This function is for web element @FindBy(Dashboard_index.Save);
        SerenityActions serenityActions = new SerenityActions(getDriver());
        try{
			serenityActions.moveToElement($(Dashboard_index.Save)).contextClick().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User right clicks on Save element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void changeFocusToSave() {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        try{
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].focus();", $(Dashboard_index.Save));
        	loggerUtils.log(LogLevel.INFO, "User is able to change focus to Save successfully");
        	}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "User is unable to change focus to Save");
			Assert.fail(e.getMessage());
        };
    }

    public void verifySaveIsDisabled() {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        Boolean isElementDisabled = false;
			try{
			if($(Dashboard_index.Save).getAttribute("disabled") != null)
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
        //The below function is for web element @FindBy(Dashboard_index.Save);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Save).getText(), StringUtils.containsIgnoreCase($(Dashboard_index.Save).getText(), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Save contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifyAttributeContainsValueForSave(String attribute, String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        try{
			assertTrue("Actual value: " + $(Dashboard_index.Save).getAttribute(attribute), StringUtils.contains($(Dashboard_index.Save).getAttribute(attribute), typeText));
        	loggerUtils.log(LogLevel.INFO, "User verifies Save contains " + typeText);
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySaveText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        try{
			assertTrue("Actual text: " + $(Dashboard_index.Save).getText(), StringUtils.equalsIgnoreCase(typeText,$(Dashboard_index.Save).getText()));
        	loggerUtils.log(LogLevel.INFO, "User gets the text of Save element");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }

    public void verifySaveIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        try{
			waitABit(3000);
			assertTrue("Save is not visible", $(Dashboard_index.Save).isDisplayed());
        	loggerUtils.log(LogLevel.INFO, "Save is visible");
        }
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        };
    }
}
