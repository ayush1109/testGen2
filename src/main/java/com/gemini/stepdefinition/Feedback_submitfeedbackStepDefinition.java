package com.gemini.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import com.gemini.implementation.Feedback_submitfeedbackImplementation;
import org.openqa.selenium.*;

public class Feedback_submitfeedbackStepDefinition {

    public Feedback_submitfeedbackImplementation feedback_submitfeedback = new Feedback_submitfeedbackImplementation();

    @When("^for the Feedback_submitfeedback page, user clicks on Provide_Feedback button$")
    public void userClicksOnProvideFeedback() {
        feedback_submitfeedback.clickOnProvideFeedback();
    }

    @Then("^for the Feedback_submitfeedback page, user is able to click Provide_Feedback element$")
    public void verifyUserIsClickableProvideFeedback() {
        feedback_submitfeedback.elementIsClickableProvideFeedback();
    }

    @Then("^for the Feedback_submitfeedback page, user verifies \"(.*)\" is present in Provide_Feedback element$")
    public void userVerifiesTextContainsInProvideFeedback(String typeText) {
        feedback_submitfeedback.verifyProvideFeedbackContainsText(typeText);
    }

    @Then("^for the Feedback_submitfeedback page, user verifies Provide_Feedback button is visible$")
    public void verifyProvideFeedbackIsDisplayed() {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        feedback_submitfeedback.verifyProvideFeedbackIsDisplayed();
    }

    @Then("^for the Feedback_submitfeedback page, user verifies \"(.*)\" is the text of Provide_Feedback button$")
    public void verifyProvideFeedbackText(String typeText) {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        feedback_submitfeedback.verifyProvideFeedbackText(typeText);
    }

    @When("^for the Feedback_submitfeedback page, user right clicks on \"(.*)\" Provide_Feedback$")
    public void rightClickOnProvideFeedback() {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        feedback_submitfeedback.rightClickOnProvideFeedback();
    }

    @Then("^for the Feedback_submitfeedback page, User verifies \"(.*)\" is the value for Provide_Feedback$")
    public void userVerifyValueForProvideFeedback(String valueOfElement) {
        //This function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        feedback_submitfeedback.verifyValueFromProvideFeedback(valueOfElement);
    }

    @Then("^for the Feedback_submitfeedback page, user hovers over Provide_Feedback$")
    public void hoverOverProvideFeedback() {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        feedback_submitfeedback.hoverOverProvideFeedback();
    }

    @Then("^for the Feedback_submitfeedback page, user scrolls to Provide_Feedback element$")
    public void scrollToProvideFeedbackElement() {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        feedback_submitfeedback.scrollToProvideFeedbackElement();
    }

    @Then("^for the Feedback_submitfeedback page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Provide_Feedback$")
    public void verifyAttributeContainsValueForProvideFeedback(String attribute, String value) {
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
        feedback_submitfeedback.verifyAttributeContainsValueForProvideFeedback(attribute, value);
    }

    @When("^for the Feedback_submitfeedback page, user verifies Provide_Feedback button is enabled$")
    public void userIsEnabledProvideFeedback() {
        feedback_submitfeedback.verifyProvideFeedbackIsEnabled();
    }

    @Then("^for the Feedback_submitfeedback page, user changes focus to provide_feedback element$")
    public void changeFocusToProvideFeedback() {
        feedback_submitfeedback.changeFocusToProvideFeedback();
        //The below function is for web element @FindBy(Feedback_submitfeedback.Provide_Feedback);
    }

    @Then("^for the Feedback_submitfeedback page, user verifies provide_feedback element is disabled")
    public void verifyProvideFeedbackIsDisabled() {
        feedback_submitfeedback.verifyProvideFeedbackIsDisabled();
        //The below function is for web element @FindBy(Feedback_submitfeedback.provide_feedback);
    }
}
