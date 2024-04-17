package com.gemini.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import com.gemini.implementation.Policy_viewpolicyImplementation;
import org.openqa.selenium.*;

public class Policy_viewpolicyStepDefinition {

    public Policy_viewpolicyImplementation policy_viewpolicy = new Policy_viewpolicyImplementation();

    @Then("^for the Policy_viewpolicy page, user is able to click Access_Policy element$")
    public void verifyUserIsClickableAccessPolicy() {
        policy_viewpolicy.elementIsClickableAccessPolicy();
    }

    @Then("^for the Policy_viewpolicy page, user scrolls to Access_Policy element$")
    public void scrollToAccessPolicyElement() {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        policy_viewpolicy.scrollToAccessPolicyElement();
    }

    @Then("^for the Policy_viewpolicy page, user verifies \"(.*)\" is present in Access_Policy element$")
    public void userVerifiesTextContainsInAccessPolicy(String typeText) {
        policy_viewpolicy.verifyAccessPolicyContainsText(typeText);
    }

    @Then("^for the Policy_viewpolicy page, user verifies Access_Policy div is visible$")
    public void verifyAccessPolicyIsDisplayed() {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        policy_viewpolicy.verifyAccessPolicyIsDisplayed();
    }

    @Then("^for the Policy_viewpolicy page, user verifies \"(.*)\" is the text of Access_Policy div$")
    public void verifyAccessPolicyText(String typeText) {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        policy_viewpolicy.verifyAccessPolicyText(typeText);
    }

    @Then("^for the Policy_viewpolicy page, User verifies \"(.*)\" is the value for Access_Policy$")
    public void userVerifyValueForAccessPolicy(String valueOfElement) {
        //This function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        policy_viewpolicy.verifyValueFromAccessPolicy(valueOfElement);
    }

    @Then("^for the Policy_viewpolicy page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Access_Policy$")
    public void verifyAttributeContainsValueForAccessPolicy(String attribute, String value) {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        policy_viewpolicy.verifyAttributeContainsValueForAccessPolicy(attribute, value);
    }

    @Then("^for the Policy_viewpolicy page, user hovers over Access_Policy$")
    public void hoverOverAccessPolicy() {
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        policy_viewpolicy.hoverOverAccessPolicy();
    }

    @Then("^for the Policy_viewpolicy page, user verifies \"(.*)\" attribute for Access_Policy as \"(.*)\"$")
    public void userVerifyAttributeForAccessPolicy(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
        policy_viewpolicy.verifyAttributeValueForAccessPolicy(attributeName,attributeValue);
    }

    @Then("^for the Policy_viewpolicy page, user changes focus to access_policy element$")
    public void changeFocusToAccessPolicy() {
        policy_viewpolicy.changeFocusToAccessPolicy();
        //The below function is for web element @FindBy(Policy_viewpolicy.Access_Policy);
    }
}
