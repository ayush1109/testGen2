package com.gemini.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import com.gemini.implementation.Timesheet_configuretimesheetImplementation;
import org.openqa.selenium.*;

public class Timesheet_configuretimesheetStepDefinition {

    public Timesheet_configuretimesheetImplementation timesheet_configuretimesheet = new Timesheet_configuretimesheetImplementation();

    @When("^for the Timesheet_configuretimesheet page, user clicks on Save button$")
    public void userClicksOnSave() {
        timesheet_configuretimesheet.clickOnSave();
    }

    @Then("^for the Timesheet_configuretimesheet page, user is able to click Save element$")
    public void verifyUserIsClickableSave() {
        timesheet_configuretimesheet.elementIsClickableSave();
    }

    @Then("^for the Timesheet_configuretimesheet page, user verifies \"(.*)\" is present in Save element$")
    public void userVerifiesTextContainsInSave(String typeText) {
        timesheet_configuretimesheet.verifySaveContainsText(typeText);
    }

    @Then("^for the Timesheet_configuretimesheet page, user verifies Save button is visible$")
    public void verifySaveIsDisplayed() {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        timesheet_configuretimesheet.verifySaveIsDisplayed();
    }

    @Then("^for the Timesheet_configuretimesheet page, user verifies \"(.*)\" is the text of Save button$")
    public void verifySaveText(String typeText) {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        timesheet_configuretimesheet.verifySaveText(typeText);
    }

    @When("^for the Timesheet_configuretimesheet page, user right clicks on \"(.*)\" Save$")
    public void rightClickOnSave() {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        timesheet_configuretimesheet.rightClickOnSave();
    }

    @Then("^for the Timesheet_configuretimesheet page, User verifies \"(.*)\" is the value for Save$")
    public void userVerifyValueForSave(String valueOfElement) {
        //This function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        timesheet_configuretimesheet.verifyValueFromSave(valueOfElement);
    }

    @Then("^for the Timesheet_configuretimesheet page, user hovers over Save$")
    public void hoverOverSave() {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        timesheet_configuretimesheet.hoverOverSave();
    }

    @Then("^for the Timesheet_configuretimesheet page, user scrolls to Save element$")
    public void scrollToSaveElement() {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        timesheet_configuretimesheet.scrollToSaveElement();
    }

    @Then("^for the Timesheet_configuretimesheet page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Save$")
    public void verifyAttributeContainsValueForSave(String attribute, String value) {
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
        timesheet_configuretimesheet.verifyAttributeContainsValueForSave(attribute, value);
    }

    @When("^for the Timesheet_configuretimesheet page, user verifies Save button is enabled$")
    public void userIsEnabledSave() {
        timesheet_configuretimesheet.verifySaveIsEnabled();
    }

    @Then("^for the Timesheet_configuretimesheet page, user changes focus to save element$")
    public void changeFocusToSave() {
        timesheet_configuretimesheet.changeFocusToSave();
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.Save);
    }

    @Then("^for the Timesheet_configuretimesheet page, user verifies save element is disabled")
    public void verifySaveIsDisabled() {
        timesheet_configuretimesheet.verifySaveIsDisabled();
        //The below function is for web element @FindBy(Timesheet_configuretimesheet.save);
    }
}
