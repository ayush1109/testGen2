package com.gemini.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import com.gemini.implementation.Lnsa_applyImplementation;
import org.openqa.selenium.*;

public class Lnsa_applyStepDefinition {

    public Lnsa_applyImplementation lnsa_apply = new Lnsa_applyImplementation();

    @When("^for the Lnsa_apply page, user clicks on Monday button$")
    public void userClicksOnMonday() {
        lnsa_apply.clickOnMonday();
    }

    @Then("^for the Lnsa_apply page, user is able to click Monday element$")
    public void verifyUserIsClickableMonday() {
        lnsa_apply.elementIsClickableMonday();
    }

    @Then("^for the Lnsa_apply page, user verifies \"(.*)\" is present in Monday element$")
    public void userVerifiesTextContainsInMonday(String typeText) {
        lnsa_apply.verifyMondayContainsText(typeText);
    }

    @Then("^for the Lnsa_apply page, user verifies Monday button is visible$")
    public void verifyMondayIsDisplayed() {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        lnsa_apply.verifyMondayIsDisplayed();
    }

    @Then("^for the Lnsa_apply page, user verifies \"(.*)\" is the text of Monday button$")
    public void verifyMondayText(String typeText) {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        lnsa_apply.verifyMondayText(typeText);
    }

    @When("^for the Lnsa_apply page, user right clicks on \"(.*)\" Monday$")
    public void rightClickOnMonday() {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        lnsa_apply.rightClickOnMonday();
    }

    @Then("^for the Lnsa_apply page, User verifies \"(.*)\" is the value for Monday$")
    public void userVerifyValueForMonday(String valueOfElement) {
        //This function is for web element @FindBy(Lnsa_apply.Monday);
        lnsa_apply.verifyValueFromMonday(valueOfElement);
    }

    @Then("^for the Lnsa_apply page, user hovers over Monday$")
    public void hoverOverMonday() {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        lnsa_apply.hoverOverMonday();
    }

    @Then("^for the Lnsa_apply page, user scrolls to Monday element$")
    public void scrollToMondayElement() {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        lnsa_apply.scrollToMondayElement();
    }

    @Then("^for the Lnsa_apply page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Monday$")
    public void verifyAttributeContainsValueForMonday(String attribute, String value) {
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
        lnsa_apply.verifyAttributeContainsValueForMonday(attribute, value);
    }

    @When("^for the Lnsa_apply page, user verifies Monday button is enabled$")
    public void userIsEnabledMonday() {
        lnsa_apply.verifyMondayIsEnabled();
    }

    @Then("^for the Lnsa_apply page, user changes focus to monday element$")
    public void changeFocusToMonday() {
        lnsa_apply.changeFocusToMonday();
        //The below function is for web element @FindBy(Lnsa_apply.Monday);
    }

    @Then("^for the Lnsa_apply page, user verifies monday element is disabled")
    public void verifyMondayIsDisabled() {
        lnsa_apply.verifyMondayIsDisabled();
        //The below function is for web element @FindBy(Lnsa_apply.monday);
    }

    @When("^for the Lnsa_apply page, user clicks on Submit button$")
    public void userClicksOnSubmit() {
        lnsa_apply.clickOnSubmit();
    }

    @Then("^for the Lnsa_apply page, user is able to click Submit element$")
    public void verifyUserIsClickableSubmit() {
        lnsa_apply.elementIsClickableSubmit();
    }

    @Then("^for the Lnsa_apply page, user verifies \"(.*)\" is present in Submit element$")
    public void userVerifiesTextContainsInSubmit(String typeText) {
        lnsa_apply.verifySubmitContainsText(typeText);
    }

    @Then("^for the Lnsa_apply page, user verifies Submit button is visible$")
    public void verifySubmitIsDisplayed() {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        lnsa_apply.verifySubmitIsDisplayed();
    }

    @Then("^for the Lnsa_apply page, user verifies \"(.*)\" is the text of Submit button$")
    public void verifySubmitText(String typeText) {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        lnsa_apply.verifySubmitText(typeText);
    }

    @When("^for the Lnsa_apply page, user right clicks on \"(.*)\" Submit$")
    public void rightClickOnSubmit() {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        lnsa_apply.rightClickOnSubmit();
    }

    @Then("^for the Lnsa_apply page, User verifies \"(.*)\" is the value for Submit$")
    public void userVerifyValueForSubmit(String valueOfElement) {
        //This function is for web element @FindBy(Lnsa_apply.Submit);
        lnsa_apply.verifyValueFromSubmit(valueOfElement);
    }

    @Then("^for the Lnsa_apply page, user hovers over Submit$")
    public void hoverOverSubmit() {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        lnsa_apply.hoverOverSubmit();
    }

    @Then("^for the Lnsa_apply page, user scrolls to Submit element$")
    public void scrollToSubmitElement() {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        lnsa_apply.scrollToSubmitElement();
    }

    @Then("^for the Lnsa_apply page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Submit$")
    public void verifyAttributeContainsValueForSubmit(String attribute, String value) {
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
        lnsa_apply.verifyAttributeContainsValueForSubmit(attribute, value);
    }

    @When("^for the Lnsa_apply page, user verifies Submit button is enabled$")
    public void userIsEnabledSubmit() {
        lnsa_apply.verifySubmitIsEnabled();
    }

    @Then("^for the Lnsa_apply page, user changes focus to submit element$")
    public void changeFocusToSubmit() {
        lnsa_apply.changeFocusToSubmit();
        //The below function is for web element @FindBy(Lnsa_apply.Submit);
    }

    @Then("^for the Lnsa_apply page, user verifies submit element is disabled")
    public void verifySubmitIsDisabled() {
        lnsa_apply.verifySubmitIsDisabled();
        //The below function is for web element @FindBy(Lnsa_apply.submit);
    }
}
