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

public class UtilsImplementation extends PageObject {

    private Reporting reporting = new EnableSerenityReporting();

    private LoggerUtils loggerUtils = new EnableSlf4jLogging();

    public String getURL() {
        String currentUrl = "";
        try{
			currentUrl = getDriver().getCurrentUrl();
        	loggerUtils.log(LogLevel.INFO, "User successfully gets current "+currentUrl);
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to get "+currentUrl);
			reporting.reportSteps("Failure", "Unable to get current URL", "");
			Assert.fail(e.getMessage());
        }
		return currentUrl;
    }

    public void verifyURL(String expectedURL) {
        try{
			String actualURL = getURL();
        	assertTrue("Actual URL: " + getURL(), actualURL.equals(expectedURL));
        	loggerUtils.log(LogLevel.INFO, "URL verified successfully");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Actual URL: " + getURL());
			reporting.reportSteps("Failure", "Actual URL: " + getURL(), "");
			Assert.fail(e.getMessage());
        };
    }

    public String getTitle() {
        String title = "";
        try{
			title = getDriver().getTitle();
        	loggerUtils.log(LogLevel.INFO, "Title fetched successfully: "+title);
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to get current title");
			Assert.fail(e.getMessage());
        }
		return title;
    }

    public void verifyTitle(String expectedTitle) {
        try{
			String actualTitle = getTitle();
        	assertTrue("Actual title: " + actualTitle, actualTitle.equals(expectedTitle));
        	loggerUtils.log(LogLevel.INFO, "User successfully verifies title");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Actual Title: " + getTitle());
			Assert.fail(e.getMessage());
        };
    }

    public void navigateTo(String url) {
        try{
			getDriver().navigate().to(url);
        	loggerUtils.log(LogLevel.INFO, "User successfully navigated to URL: " + url);
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not navigate to URL: " + url);
			reporting.reportSteps("Failure", "Could not navigate to URL: " + url, "");
			Assert.fail(e.getMessage());
        };
    }

    public void forwardNavigation() {
        try{
			getDriver().navigate().forward();
        	loggerUtils.log(LogLevel.INFO, "User successfully navigated Forward");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not navigate forward");
			reporting.reportSteps("Failure", "Could not navigate forward", "");
			Assert.fail(e.getMessage());
        };
    }

    public void switchActiveElement() {
        try{
			getDriver().switchTo().activeElement();
        	loggerUtils.log(LogLevel.INFO, "User successfully switched to active element");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not switch to active element");
			reporting.reportSteps("Failure", "Could not switch to active element", "");
			Assert.fail(e.getMessage());
        };
    }

    public void switchDefaultContent() {
        try{
			getDriver().switchTo().defaultContent();
        	loggerUtils.log(LogLevel.INFO, "User successfully switched to default content");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not switch to default element");
			reporting.reportSteps("Failure", "Could not switch to default element", "");
			Assert.fail(e.getMessage());
        };
    }

    public void switchParentFrame() {
        try{
			getDriver().switchTo().parentFrame();
        	loggerUtils.log(LogLevel.INFO, "User successfully switched to parent frame");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not switch to parent frame");
			reporting.reportSteps("Failure", "Could not switch to parent frame", "");
			Assert.fail(e.getMessage());
        };
    }

    public void switchFrame(String nameOrId) {
        try{
			getDriver().switchTo().frame(nameOrId);
        	loggerUtils.log(LogLevel.INFO, "User successfully switched to frame" + nameOrId);
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not switch to frame: " + nameOrId);
			reporting.reportSteps("Failure", "Could not switch to frame: " + nameOrId, "");
			Assert.fail(e.getMessage());
        };
    }

    public void switchFrame(int index) {
        try{
			getDriver().switchTo().frame(index);
        	loggerUtils.log(LogLevel.INFO, "User successfully switched to frame" + index);
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not switch to frame: " + index);
			reporting.reportSteps("Failure", "Could not switch to frame: " + index, "");
			Assert.fail(e.getMessage());
        };
    }

    public void switchWindow(String nameOrHandle) {
        try{
			getDriver().switchTo().window(nameOrHandle);
        	loggerUtils.log(LogLevel.INFO, "User successfully switched to window" + nameOrHandle);
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not switch to window: " + nameOrHandle);
			reporting.reportSteps("Failure", "Could not switch to window: " + nameOrHandle, "");
			Assert.fail(e.getMessage());
        };
    }

    public void wait(int duration) {
        try{
			waitABit(duration);
        	loggerUtils.log(LogLevel.INFO, "User waits for " + duration + " milliseconds");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not wait for : " + duration + " milliseconds");
			reporting.reportSteps("Failure", "Could not wait for : " + duration + " milliseconds", "");
			Assert.fail(e.getMessage());
        };
    }

    public void clicksAndHold(String locatorName) {
        try{
			By locator = getLocator(locatorName);
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(ExpectedConditions.elementToBeClickable(locator));
        	new SerenityActions(getDriver()).moveToElement($(locator)).clickAndHold().build().perform();
        	loggerUtils.log(LogLevel.INFO, "User successfully clicks and holds " + locator + " element");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not click and hold: " + locatorName);
			reporting.reportSteps("Failure", "Could not click and hold: " + locatorName, "");
			Assert.fail(e.getMessage());
        };
    }

    public void tearDown() {
        try{
			getDriver().quit();
        	loggerUtils.log(LogLevel.INFO, "User successfully closed driver");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not close driver");
			reporting.reportSteps("Failure", "Could not close driver", "");
			Assert.fail(e.getMessage());
        };
    }

    public void maximizeBrowserToDefault() {
        try{
			getDriver().manage().window().maximize();
        	loggerUtils.log(LogLevel.INFO, "Browser Maximization to default successful");
		} catch(Exception e){;
        		loggerUtils.log(LogLevel.INFO, "Unable to maximize browser to default");
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        	Serenity.recordReportData().withTitle("Failure").andContents("Could not maximize browser to default");
			Assert.fail(e.getMessage());
        };
    }

    public void minimizeGivenBrowser() {
        try{
			getDriver().manage().window().minimize();
        	loggerUtils.log(LogLevel.INFO, "Browser Minimization successful.");
		}
		catch(Exception e){;
        	loggerUtils.log(LogLevel.INFO, "Unable to minimize browser.");
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        	Serenity.recordReportData().withTitle("Failure").andContents("Could not minimize browser");
			Assert.fail(e.getMessage());
        };
    }

    public Object browserSize() {
        Integer sizeOfBrowser = null;
        try { 
			Object size = getDriver().manage().window().getSize();
        	loggerUtils.log(LogLevel.INFO, "Browser Size is  "+sizeOfBrowser);
        	if(size!=null);
        	sizeOfBrowser = Integer.valueOf(size.toString());
        
		} catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not get size of browser");
			reporting.reportSteps("Failure", "Could not get size of browser", "");
			Assert.fail(e.getMessage());
		};
        return sizeOfBrowser;
    }

    public void setSizeOfBrowser(int width, int height) {
        try{
			Dimension newDimension = new Dimension(width, height);
			getDriver().manage().window().setSize(newDimension)		;
        	loggerUtils.log(LogLevel.INFO, "User successfully set size of browser");
        } 
			catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not set size of browser");
			Assert.fail(e.getMessage());
		};
    }

    public void setPositionOfBrowser(int x, int y) {
        try{
			getDriver().manage().window().setPosition(new Point(x,y));
        	loggerUtils.log(LogLevel.INFO, "User successfully set position of browser");
        } 
			catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Could not set position of browser");
			Assert.fail(e.getMessage());
		};
    }

    public Object browserPosition() {
        Integer positionOfBrowser = null;
        try{
		Object position = getDriver().manage().window().getPosition();
        if(position!=null){	;
        	positionOfBrowser = Integer.valueOf(position.toString());
			loggerUtils.log(LogLevel.INFO, "Browser Position is "+positionOfBrowser);
	};
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to get browser position");
			reporting.reportSteps("Failure", "Unable to get browser position", "");
			Assert.fail(e.getMessage());
        }
		return positionOfBrowser;
    }

    public Object windowHandle() {
        String windowHandle = null;
        try { 
			windowHandle = getDriver().getWindowHandle();
        	loggerUtils.log(LogLevel.INFO, "Window Handle is "+windowHandle);
        	if(windowHandle==null){
			throw new NullPointerException();
        	}
		}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to get window handle");
			reporting.reportSteps("Failure", "Unable to get window handle", "");
			Assert.fail(e.getMessage());
		};
        return windowHandle;
    }

    public String windowHandles() {
        String windowHandles = null;
        try {
			windowHandles = getDriver().getWindowHandles().toString();
        	loggerUtils.log(LogLevel.INFO, "Window Handles fetched successfully.");
        	if(windowHandles==null){
			throw new NullPointerException();
        	} 
		}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to get window handles");
			reporting.reportSteps("Failure", "Unable to get window handles", "");
			Assert.fail(e.getMessage());
		};
        return windowHandles;
    }

    public String pageSource() {
        String pageSource = null;
        try { 
			pageSource = getDriver().getPageSource();
        	loggerUtils.log(LogLevel.INFO, "Page Source is  "+pageSource);
        	if(pageSource==null){
			throw new NullPointerException();
        	}
		}
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to get page source");
			Assert.fail(e.getMessage());
		};
        return pageSource;
    }

    public void closeTab() {
        try{
			getDriver().close();
        	loggerUtils.log(LogLevel.INFO, "User successfully closes current tab.");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to close current tab");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void alertSwitch() {
        try{
			getDriver().switchTo().alert();
        	loggerUtils.log(LogLevel.INFO, "User is unable to switch to alert.");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to switch to Alert");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void alertAccept() {
        try{
			getDriver().switchTo().alert().accept();
        	loggerUtils.log(LogLevel.INFO, "User successfully accepts alert.");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to accept Alert");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void alertDismiss() {
        try{
			getDriver().switchTo().alert().dismiss();
        	loggerUtils.log(LogLevel.INFO, "User successfully dismiss alert.");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to dismiss Alert");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void inputForAlert(String input) {
        try{
			getDriver().switchTo().alert().sendKeys(input);
        	loggerUtils.log(LogLevel.INFO, "User successfully enters " + input + "into alert.");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to enter " + input + "into Alert");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void scrollUp() {
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        	loggerUtils.log(LogLevel.INFO, "User is able to scroll up successfully");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to scroll to top of page");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void scrollDown() {
        try{
			 JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        	loggerUtils.log(LogLevel.INFO, "User is able to scroll down successfully");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to scroll to bottom of page");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void scrollPage(int x, int y) {
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy("+x+","+y+")");
        	loggerUtils.log(LogLevel.INFO, "User is able to scroll page to x: "+x+" and y: "+y+" coordinates");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to scroll page");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void elementScroll(int x, int y) {
        try{
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy("+x+","+y+")");
        	loggerUtils.log(LogLevel.INFO, "User is able to scroll page to x: "+x+" and y: "+y+" coordinates");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        }
		;
    }

    public void refreshPage() {
        try{
			getDriver().navigate().refresh();
        	loggerUtils.log(LogLevel.INFO, "User successfully refreshes page.");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to refresh page");
			Assert.fail(e.getMessage());
        }
		;
    }

    public LogEntries getLogs() {
        LogEntries logs = null;
        try {
			new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(HelperFunctions.readProperties("timeOut")))).until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
			logs = getDriver().manage().logs().get(LogType.BROWSER);
        	loggerUtils.log(LogLevel.INFO, "Log messages are present in console.");
        } catch (Exception e) { 
			Assert.fail(e.getMessage());
        	loggerUtils.log(LogLevel.INFO, "Unable to get logs.");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: " + e);
        } 
		return logs;
    }

    public void verifyNoErrorLogs() {
        LogEntries logs = getLogs();
        try{
			for (LogEntry log : logs
			) {
			assertFalse("Error Log messages are present in console." + log.getMessage(), StringUtils.equalsIgnoreCase("SEVERE", log.getLevel().getName()));
        	loggerUtils.log(LogLevel.INFO, "Error Log messages are present in console." + log.getMessage());
        	}
		} catch (Exception e) { 
			Assert.fail(e.getMessage());
        	loggerUtils.log(LogLevel.INFO, "Unable to get logs.");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: " + e) ;
        	};
    }

    public void verifyNoLogs() {
        LogEntries logs = getLogs();
        try{
			Assert.assertNotEquals("Log messages are present in console.", 0, logs.getAll().size());
        	loggerUtils.log(LogLevel.INFO, "Error Log messages are present in console.");
        } catch (Exception e) { 
			Assert.fail(e.getMessage());
        	loggerUtils.log(LogLevel.INFO, "Unable to get logs.");
        	loggerUtils.log(LogLevel.INFO, "User gets an exception: " + e) ;
        	};
    }

    public void clearConsole() {
        try{
			JavascriptExecutor executor = (JavascriptExecutor)getDriver();
			executor.executeScript("console.clear();");
        	loggerUtils.log(LogLevel.INFO, "Cleared the console.");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to clear console");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void takeScreenshot(String filePath) {
        try{
			TakesScreenshot scrShot =((TakesScreenshot)getDriver());
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(filePath);
			FileUtils.copyFile(SrcFile, DestFile);
        	loggerUtils.log(LogLevel.INFO, "User successfully takes snapshot.");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
        }
		;
    }

    public By getLocator(String locatorName) {
        By locator = null;
        try{
			String className = locatorName.split("\\.")[0];
			Class<?> clazz = Class.forName("locators." + className);
			Field loc = clazz.getField(locatorName.split("\\.")[1]);
			locator = (By) loc.get(className);
        	loggerUtils.log(LogLevel.INFO, "User successfully gets locator- " + locator);
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to get locator");
			Assert.fail(e.getMessage());
        } 
		return locator;
    }

    public void clickUsingJS(String locatorName) {
        try{
			By locator = getLocator(locatorName);
			JavascriptExecutor executor = (JavascriptExecutor)getDriver();
			executor.executeScript("arguments[0].click();", $(locator));
        	loggerUtils.log(LogLevel.INFO, "User is able to click element ");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to click using JS");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void dragAndDrop(String from, String to) {
        try{
			By fromLocator = getLocator(from);
			By toLocator = getLocator(to);
			new SerenityActions(getDriver()).dragAndDrop($(fromLocator), $(toLocator)).build().perform();
        	loggerUtils.log(LogLevel.INFO, "User is able to drag and drop element ");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to drag and drop element");
			Assert.fail(e.getMessage());
        }
		;
    }

    public void fileUpload(String filePath, String locatorName) {
        try{
			By locator = getLocator(locatorName);
			$(locator).sendKeys(filePath);
        	loggerUtils.log(LogLevel.INFO, "User is able to upload file to element");
        } 
		catch(Exception e){
			loggerUtils.log(LogLevel.INFO, "User gets an exception: "+e);
			loggerUtils.log(LogLevel.INFO, "Unable to upload file");
			Assert.fail(e.getMessage());
        }
		;
    }
}
