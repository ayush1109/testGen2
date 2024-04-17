package com.gemini.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import com.gemini.implementation.Leavemanagement_reviewrequestImplementation;
import org.openqa.selenium.*;

public class Leavemanagement_reviewrequestStepDefinition {

    public Leavemanagement_reviewrequestImplementation leavemanagement_reviewrequest = new Leavemanagement_reviewrequestImplementation();

    @When("^for the Leavemanagement_reviewrequest page, user clicks on Delegate button$")
    public void userClicksOnDelegate() {
        leavemanagement_reviewrequest.clickOnDelegate();
    }

    @Then("^for the Leavemanagement_reviewrequest page, user is able to click Delegate element$")
    public void verifyUserIsClickableDelegate() {
        leavemanagement_reviewrequest.elementIsClickableDelegate();
    }

    @Then("^for the Leavemanagement_reviewrequest page, user verifies \"(.*)\" is present in Delegate element$")
    public void userVerifiesTextContainsInDelegate(String typeText) {
        leavemanagement_reviewrequest.verifyDelegateContainsText(typeText);
    }

    @Then("^for the Leavemanagement_reviewrequest page, user verifies Delegate button is visible$")
    public void verifyDelegateIsDisplayed() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        leavemanagement_reviewrequest.verifyDelegateIsDisplayed();
    }

    @Then("^for the Leavemanagement_reviewrequest page, user verifies \"(.*)\" is the text of Delegate button$")
    public void verifyDelegateText(String typeText) {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        leavemanagement_reviewrequest.verifyDelegateText(typeText);
    }

    @When("^for the Leavemanagement_reviewrequest page, user right clicks on \"(.*)\" Delegate$")
    public void rightClickOnDelegate() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        leavemanagement_reviewrequest.rightClickOnDelegate();
    }

    @Then("^for the Leavemanagement_reviewrequest page, User verifies \"(.*)\" is the value for Delegate$")
    public void userVerifyValueForDelegate(String valueOfElement) {
        //This function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        leavemanagement_reviewrequest.verifyValueFromDelegate(valueOfElement);
    }

    @Then("^for the Leavemanagement_reviewrequest page, user hovers over Delegate$")
    public void hoverOverDelegate() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        leavemanagement_reviewrequest.hoverOverDelegate();
    }

    @Then("^for the Leavemanagement_reviewrequest page, user scrolls to Delegate element$")
    public void scrollToDelegateElement() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        leavemanagement_reviewrequest.scrollToDelegateElement();
    }

    @Then("^for the Leavemanagement_reviewrequest page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Delegate$")
    public void verifyAttributeContainsValueForDelegate(String attribute, String value) {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
        leavemanagement_reviewrequest.verifyAttributeContainsValueForDelegate(attribute, value);
    }

    @When("^for the Leavemanagement_reviewrequest page, user verifies Delegate button is enabled$")
    public void userIsEnabledDelegate() {
        leavemanagement_reviewrequest.verifyDelegateIsEnabled();
    }

    @Then("^for the Leavemanagement_reviewrequest page, user changes focus to delegate element$")
    public void changeFocusToDelegate() {
        leavemanagement_reviewrequest.changeFocusToDelegate();
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Delegate);
    }

    @Then("^for the Leavemanagement_reviewrequest page, user verifies delegate element is disabled")
    public void verifyDelegateIsDisabled() {
        leavemanagement_reviewrequest.verifyDelegateIsDisabled();
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.delegate);
    }

    @Then("^for the Leavemanagement_reviewrequest page, user is able to click Service_Unavailable element$")
    public void verifyUserIsClickableServiceUnavailable() {
        leavemanagement_reviewrequest.elementIsClickableServiceUnavailable();
    }

    @Then("^for the Leavemanagement_reviewrequest page, user scrolls to Service_Unavailable element$")
    public void scrollToServiceUnavailableElement() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        leavemanagement_reviewrequest.scrollToServiceUnavailableElement();
    }

    @Then("^for the Leavemanagement_reviewrequest page, user verifies \"(.*)\" is present in Service_Unavailable element$")
    public void userVerifiesTextContainsInServiceUnavailable(String typeText) {
        leavemanagement_reviewrequest.verifyServiceUnavailableContainsText(typeText);
    }

    @Then("^for the Leavemanagement_reviewrequest page, user verifies Service_Unavailable div is visible$")
    public void verifyServiceUnavailableIsDisplayed() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        leavemanagement_reviewrequest.verifyServiceUnavailableIsDisplayed();
    }

    @Then("^for the Leavemanagement_reviewrequest page, user verifies \"(.*)\" is the text of Service_Unavailable div$")
    public void verifyServiceUnavailableText(String typeText) {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        leavemanagement_reviewrequest.verifyServiceUnavailableText(typeText);
    }

    @Then("^for the Leavemanagement_reviewrequest page, User verifies \"(.*)\" is the value for Service_Unavailable$")
    public void userVerifyValueForServiceUnavailable(String valueOfElement) {
        //This function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        leavemanagement_reviewrequest.verifyValueFromServiceUnavailable(valueOfElement);
    }

    @Then("^for the Leavemanagement_reviewrequest page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Service_Unavailable$")
    public void verifyAttributeContainsValueForServiceUnavailable(String attribute, String value) {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        leavemanagement_reviewrequest.verifyAttributeContainsValueForServiceUnavailable(attribute, value);
    }

    @Then("^for the Leavemanagement_reviewrequest page, user hovers over Service_Unavailable$")
    public void hoverOverServiceUnavailable() {
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        leavemanagement_reviewrequest.hoverOverServiceUnavailable();
    }

    @Then("^for the Leavemanagement_reviewrequest page, user verifies \"(.*)\" attribute for Service_Unavailable as \"(.*)\"$")
    public void userVerifyAttributeForServiceUnavailable(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
        leavemanagement_reviewrequest.verifyAttributeValueForServiceUnavailable(attributeName,attributeValue);
    }

    @Then("^for the Leavemanagement_reviewrequest page, user changes focus to service_unavailable element$")
    public void changeFocusToServiceUnavailable() {
        leavemanagement_reviewrequest.changeFocusToServiceUnavailable();
        //The below function is for web element @FindBy(Leavemanagement_reviewrequest.Service_Unavailable);
    }
}
