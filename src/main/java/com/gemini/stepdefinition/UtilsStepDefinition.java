package com.gemini.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import com.gemini.implementation.UtilsImplementation;
import org.openqa.selenium.*;

public class UtilsStepDefinition {

    public UtilsImplementation utils = new UtilsImplementation();

    @Then("^user gets current url of the page$")
    public void getUrl() {
        utils.getURL();
    }

    @And("^user verifies \"(.*)\" is the current url$")
    public void verifyUrl(String url) {
        utils.verifyURL(url);
    }

    @Then("^user gets title of the page$")
    public void getTitle() {
        utils.getTitle();
    }

    @And("^user verifies \"(.*)\" is the title of the page$")
    public void verifyTitle(String title) {
        utils.verifyTitle(title);
    }

    @Given("^user navigates to \"(.*)\"$")
    public void navigateTo(String url) {
        utils.navigateTo(url);
    }

    @When("^user navigates forward to next page$")
    public void navigateForward() {
        utils.forwardNavigation();
    }

    @When("^user maximizes window to default$")
    public void maximizeBrowserToDefault() {
        utils.maximizeBrowserToDefault();
    }

    @When("^user minimizes window to default$")
    public void minimizeGivenBrowser() {
        utils.minimizeGivenBrowser();
    }

    @When("^user gets browser size$")
    public void browserSize() {
        utils.browserSize();
    }

    @When("^user sets browser size to \"(.*)\" width and \"(.*)\" height$")
    public void setSizeOfBrowser(Integer width, Integer height) {
        utils.setSizeOfBrowser(width,height);
    }

    @When("^user sets browser position to \"(.*)\" x coordinate and \"(.*)\" y coordinate$")
    public void setPositionOfBrowser(Integer x, Integer y) {
        utils.setPositionOfBrowser(x,y);
    }

    @When("^user gets browser positions$")
    public void browserPosition() {
        utils.browserPosition();
    }

    @When("^user gets page source$")
    public void pageSource() {
        utils.pageSource();
    }

    @When("^user closes current tab$")
    public void closeTab() {
        utils.closeTab();
    }

    @When("^user switches to alert$")
    public void alertSwitch() {
        utils.alertSwitch();
    }

    @When("^user accepts alert$")
    public void alertAccept() {
        utils.alertAccept();
    }

    @When("^user dismisses alert$")
    public void alertDismiss() {
        utils.alertDismiss();
    }

    @When("^user enters \"(.*)\" as alert input$")
    public void inputForAlert(String input) {
        utils.inputForAlert(input);
    }

    @When("^user scrolls page up$")
    public void scrollUp() {
        utils.scrollUp();
    }

    @When("^user scrolls page down$")
    public void scrollDown() {
        utils.scrollDown();
    }

    @When("^user scrolls page to \"(.*)\" x coordinate and \"(.*)\" y coordinate$")
    public void scrollPage(Integer x, Integer y) {
        utils.scrollPage(x,y);
    }

    @When("^user scrolls element to \"(.*)\" x coordinate and \"(.*)\" y coordinate$")
    public void elementScroll(Integer x, Integer y) {
        utils.elementScroll(x,y);
    }

    @When("^user refreshes page$")
    public void refreshPage() {
        utils.refreshPage();
    }

    @When("^user takes snapshot$")
    public void takeScreenshot(String filePath) {
        utils.takeScreenshot(filePath);
    }

    @When("^user switches to active element$")
    public void switchActiveElement() {
        utils.switchActiveElement();
    }

    @When("^user switches to parent frame$")
    public void switchParentFrame() {
        utils.switchParentFrame();
    }

    @When("^user switches to default content$")
    public void switchDefaultContent() {
        utils.switchDefaultContent();
    }

    @When("^user switches to \"(.*)\" frame$")
    public void switchFrame(String nameOrId) {
        utils.switchFrame(nameOrId);
    }

    @When("^user switches to (.*) frame$")
    public void switchFrame(int index) {
        utils.switchFrame(index);
    }

    @When("^user switches to \"(.*)\" window$")
    public void switchWindow(String nameOrHandle) {
        utils.switchWindow(nameOrHandle);
    }

    @When("^user gets console messages$")
    public void getLogs() {
        utils.getLogs();
    }

    @When("^user verifies there are no console error messages$")
    public void verifiesNoErrorLogs() {
        utils.verifyNoErrorLogs();
    }

    @When("^user verifies there are no console messages$")
    public void verifyNoLogs() {
        utils.verifyNoLogs();
    }

    @When("^user clears the console$")
    public void clearConsole() {
        utils.clearConsole();
    }

    @When("^user waits for (.*) seconds$")
    public void waitFor(int duration) {
        utils.wait(duration*1000);
    }

    @When("^user clicks \"(.*?)\"$")
    public void clickUsingJS(String locatorName) {
        utils.clickUsingJS(locatorName);
    }

    @Then("^user clicks and holds \"(.*?)\" element$")
    public void userClicksAndHoldsElement(String locator) {
        utils.clicksAndHold(locator);
    }

    @Then("^user drags from \"(.*?)\" and drops to \"(.*?)\" position$")
    public void userDragsAndDrops(String from, String to) {
        utils.dragAndDrop(from, to);
    }

    @Then("^user uploads file from \"(.*?)\" path to \"(.*?)\" element$")
    public void userUploadsFile(String filePath, String locator) {
        utils.fileUpload(filePath, locator);
    }

    @Then("^user closes browser$")
    public void closeBrowser() {
        utils.tearDown();
    }

    @Given("^user is on homepage$")
    public void open() {
        utils.open();
    }
}
