package com.gemini.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import com.gemini.implementation.Dashboard_indexImplementation;
import org.openqa.selenium.*;

public class Dashboard_indexStepDefinition {

    public Dashboard_indexImplementation dashboard_index = new Dashboard_indexImplementation();

    @When("^for the Dashboard_index page, user clicks on LNSA button$")
    public void userClicksOnLnsa() {
        dashboard_index.clickOnLnsa();
    }

    @Then("^for the Dashboard_index page, user is able to click LNSA element$")
    public void verifyUserIsClickableLnsa() {
        dashboard_index.elementIsClickableLnsa();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in LNSA element$")
    public void userVerifiesTextContainsInLnsa(String typeText) {
        dashboard_index.verifyLnsaContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies LNSA button is visible$")
    public void verifyLnsaIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        dashboard_index.verifyLnsaIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of LNSA button$")
    public void verifyLnsaText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        dashboard_index.verifyLnsaText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" LNSA$")
    public void rightClickOnLnsa() {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        dashboard_index.rightClickOnLnsa();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for LNSA$")
    public void userVerifyValueForLnsa(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.LNSA);
        dashboard_index.verifyValueFromLnsa(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over LNSA$")
    public void hoverOverLnsa() {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        dashboard_index.hoverOverLnsa();
    }

    @Then("^for the Dashboard_index page, user scrolls to LNSA element$")
    public void scrollToLnsaElement() {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        dashboard_index.scrollToLnsaElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for LNSA$")
    public void verifyAttributeContainsValueForLnsa(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
        dashboard_index.verifyAttributeContainsValueForLnsa(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies LNSA button is enabled$")
    public void userIsEnabledLnsa() {
        dashboard_index.verifyLnsaIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to lnsa element$")
    public void changeFocusToLnsa() {
        dashboard_index.changeFocusToLnsa();
        //The below function is for web element @FindBy(Dashboard_index.LNSA);
    }

    @Then("^for the Dashboard_index page, user verifies lnsa element is disabled")
    public void verifyLnsaIsDisabled() {
        dashboard_index.verifyLnsaIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.lnsa);
    }

    @When("^for the Dashboard_index page, user clicks on Feedback button$")
    public void userClicksOnFeedback() {
        dashboard_index.clickOnFeedback();
    }

    @Then("^for the Dashboard_index page, user is able to click Feedback element$")
    public void verifyUserIsClickableFeedback() {
        dashboard_index.elementIsClickableFeedback();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Feedback element$")
    public void userVerifiesTextContainsInFeedback(String typeText) {
        dashboard_index.verifyFeedbackContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Feedback button is visible$")
    public void verifyFeedbackIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        dashboard_index.verifyFeedbackIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Feedback button$")
    public void verifyFeedbackText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        dashboard_index.verifyFeedbackText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Feedback$")
    public void rightClickOnFeedback() {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        dashboard_index.rightClickOnFeedback();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Feedback$")
    public void userVerifyValueForFeedback(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Feedback);
        dashboard_index.verifyValueFromFeedback(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Feedback$")
    public void hoverOverFeedback() {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        dashboard_index.hoverOverFeedback();
    }

    @Then("^for the Dashboard_index page, user scrolls to Feedback element$")
    public void scrollToFeedbackElement() {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        dashboard_index.scrollToFeedbackElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Feedback$")
    public void verifyAttributeContainsValueForFeedback(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
        dashboard_index.verifyAttributeContainsValueForFeedback(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Feedback button is enabled$")
    public void userIsEnabledFeedback() {
        dashboard_index.verifyFeedbackIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to feedback element$")
    public void changeFocusToFeedback() {
        dashboard_index.changeFocusToFeedback();
        //The below function is for web element @FindBy(Dashboard_index.Feedback);
    }

    @Then("^for the Dashboard_index page, user verifies feedback element is disabled")
    public void verifyFeedbackIsDisabled() {
        dashboard_index.verifyFeedbackIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.feedback);
    }

    @When("^for the Dashboard_index page, user clicks on Reimbursement button$")
    public void userClicksOnReimbursement() {
        dashboard_index.clickOnReimbursement();
    }

    @Then("^for the Dashboard_index page, user is able to click Reimbursement element$")
    public void verifyUserIsClickableReimbursement() {
        dashboard_index.elementIsClickableReimbursement();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Reimbursement element$")
    public void userVerifiesTextContainsInReimbursement(String typeText) {
        dashboard_index.verifyReimbursementContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Reimbursement button is visible$")
    public void verifyReimbursementIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        dashboard_index.verifyReimbursementIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Reimbursement button$")
    public void verifyReimbursementText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        dashboard_index.verifyReimbursementText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Reimbursement$")
    public void rightClickOnReimbursement() {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        dashboard_index.rightClickOnReimbursement();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Reimbursement$")
    public void userVerifyValueForReimbursement(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Reimbursement);
        dashboard_index.verifyValueFromReimbursement(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Reimbursement$")
    public void hoverOverReimbursement() {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        dashboard_index.hoverOverReimbursement();
    }

    @Then("^for the Dashboard_index page, user scrolls to Reimbursement element$")
    public void scrollToReimbursementElement() {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        dashboard_index.scrollToReimbursementElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Reimbursement$")
    public void verifyAttributeContainsValueForReimbursement(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
        dashboard_index.verifyAttributeContainsValueForReimbursement(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Reimbursement button is enabled$")
    public void userIsEnabledReimbursement() {
        dashboard_index.verifyReimbursementIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to reimbursement element$")
    public void changeFocusToReimbursement() {
        dashboard_index.changeFocusToReimbursement();
        //The below function is for web element @FindBy(Dashboard_index.Reimbursement);
    }

    @Then("^for the Dashboard_index page, user verifies reimbursement element is disabled")
    public void verifyReimbursementIsDisabled() {
        dashboard_index.verifyReimbursementIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.reimbursement);
    }

    @When("^for the Dashboard_index page, user clicks on employee_directory button$")
    public void userClicksOnEmployeeDirectory() {
        dashboard_index.clickOnEmployeeDirectory();
    }

    @Then("^for the Dashboard_index page, user is able to click employee_directory element$")
    public void verifyUserIsClickableEmployeeDirectory() {
        dashboard_index.elementIsClickableEmployeeDirectory();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in employee_directory element$")
    public void userVerifiesTextContainsInEmployeeDirectory(String typeText) {
        dashboard_index.verifyEmployeeDirectoryContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies employee_directory button is visible$")
    public void verifyEmployeeDirectoryIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        dashboard_index.verifyEmployeeDirectoryIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of employee_directory button$")
    public void verifyEmployeeDirectoryText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        dashboard_index.verifyEmployeeDirectoryText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" employee_directory$")
    public void rightClickOnEmployeeDirectory() {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        dashboard_index.rightClickOnEmployeeDirectory();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for employee_directory$")
    public void userVerifyValueForEmployeeDirectory(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.employee_directory);
        dashboard_index.verifyValueFromEmployeeDirectory(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over employee_directory$")
    public void hoverOverEmployeeDirectory() {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        dashboard_index.hoverOverEmployeeDirectory();
    }

    @Then("^for the Dashboard_index page, user scrolls to employee_directory element$")
    public void scrollToEmployeeDirectoryElement() {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        dashboard_index.scrollToEmployeeDirectoryElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for employee_directory$")
    public void verifyAttributeContainsValueForEmployeeDirectory(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
        dashboard_index.verifyAttributeContainsValueForEmployeeDirectory(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies employee_directory button is enabled$")
    public void userIsEnabledEmployeeDirectory() {
        dashboard_index.verifyEmployeeDirectoryIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to employee_directory element$")
    public void changeFocusToEmployeeDirectory() {
        dashboard_index.changeFocusToEmployeeDirectory();
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
    }

    @Then("^for the Dashboard_index page, user verifies employee_directory element is disabled")
    public void verifyEmployeeDirectoryIsDisabled() {
        dashboard_index.verifyEmployeeDirectoryIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.employee_directory);
    }

    @When("^for the Dashboard_index page, user clicks on Lunch button$")
    public void userClicksOnLunch() {
        dashboard_index.clickOnLunch();
    }

    @Then("^for the Dashboard_index page, user is able to click Lunch element$")
    public void verifyUserIsClickableLunch() {
        dashboard_index.elementIsClickableLunch();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Lunch element$")
    public void userVerifiesTextContainsInLunch(String typeText) {
        dashboard_index.verifyLunchContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Lunch button is visible$")
    public void verifyLunchIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        dashboard_index.verifyLunchIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Lunch button$")
    public void verifyLunchText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        dashboard_index.verifyLunchText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Lunch$")
    public void rightClickOnLunch() {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        dashboard_index.rightClickOnLunch();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Lunch$")
    public void userVerifyValueForLunch(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Lunch);
        dashboard_index.verifyValueFromLunch(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Lunch$")
    public void hoverOverLunch() {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        dashboard_index.hoverOverLunch();
    }

    @Then("^for the Dashboard_index page, user scrolls to Lunch element$")
    public void scrollToLunchElement() {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        dashboard_index.scrollToLunchElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Lunch$")
    public void verifyAttributeContainsValueForLunch(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
        dashboard_index.verifyAttributeContainsValueForLunch(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Lunch button is enabled$")
    public void userIsEnabledLunch() {
        dashboard_index.verifyLunchIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to lunch element$")
    public void changeFocusToLunch() {
        dashboard_index.changeFocusToLunch();
        //The below function is for web element @FindBy(Dashboard_index.Lunch);
    }

    @Then("^for the Dashboard_index page, user verifies lunch element is disabled")
    public void verifyLunchIsDisabled() {
        dashboard_index.verifyLunchIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.lunch);
    }

    @Then("^for the Dashboard_index page, user is able to click Canaan_Tower element$")
    public void verifyUserIsClickableCanaanTower() {
        dashboard_index.elementIsClickableCanaanTower();
    }

    @Then("^for the Dashboard_index page, user scrolls to Canaan_Tower element$")
    public void scrollToCanaanTowerElement() {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        dashboard_index.scrollToCanaanTowerElement();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Canaan_Tower element$")
    public void userVerifiesTextContainsInCanaanTower(String typeText) {
        dashboard_index.verifyCanaanTowerContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Canaan_Tower div is visible$")
    public void verifyCanaanTowerIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        dashboard_index.verifyCanaanTowerIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Canaan_Tower div$")
    public void verifyCanaanTowerText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        dashboard_index.verifyCanaanTowerText(typeText);
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Canaan_Tower$")
    public void userVerifyValueForCanaanTower(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        dashboard_index.verifyValueFromCanaanTower(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Canaan_Tower$")
    public void verifyAttributeContainsValueForCanaanTower(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        dashboard_index.verifyAttributeContainsValueForCanaanTower(attribute, value);
    }

    @Then("^for the Dashboard_index page, user hovers over Canaan_Tower$")
    public void hoverOverCanaanTower() {
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        dashboard_index.hoverOverCanaanTower();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" attribute for Canaan_Tower as \"(.*)\"$")
    public void userVerifyAttributeForCanaanTower(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Dashboard_index.Canaan_Tower);
        dashboard_index.verifyAttributeValueForCanaanTower(attributeName,attributeValue);
    }

    @Then("^for the Dashboard_index page, user changes focus to canaan_tower element$")
    public void changeFocusToCanaanTower() {
        dashboard_index.changeFocusToCanaanTower();
        //The below function is for web element @FindBy(Dashboard_index.Canaan_Tower);
    }

    @Then("^for the Dashboard_index page, user is able to click Location element$")
    public void verifyUserIsClickableLocation() {
        dashboard_index.elementIsClickableLocation();
    }

    @Then("^for the Dashboard_index page, user scrolls to Location element$")
    public void scrollToLocationElement() {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        dashboard_index.scrollToLocationElement();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Location element$")
    public void userVerifiesTextContainsInLocation(String typeText) {
        dashboard_index.verifyLocationContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Location div is visible$")
    public void verifyLocationIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        dashboard_index.verifyLocationIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Location div$")
    public void verifyLocationText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        dashboard_index.verifyLocationText(typeText);
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Location$")
    public void userVerifyValueForLocation(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Location);
        dashboard_index.verifyValueFromLocation(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Location$")
    public void verifyAttributeContainsValueForLocation(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        dashboard_index.verifyAttributeContainsValueForLocation(attribute, value);
    }

    @Then("^for the Dashboard_index page, user hovers over Location$")
    public void hoverOverLocation() {
        //The below function is for web element @FindBy(Dashboard_index.Location);
        dashboard_index.hoverOverLocation();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" attribute for Location as \"(.*)\"$")
    public void userVerifyAttributeForLocation(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Dashboard_index.Location);
        dashboard_index.verifyAttributeValueForLocation(attributeName,attributeValue);
    }

    @Then("^for the Dashboard_index page, user changes focus to location element$")
    public void changeFocusToLocation() {
        dashboard_index.changeFocusToLocation();
        //The below function is for web element @FindBy(Dashboard_index.Location);
    }

    @When("^for the Dashboard_index page, user clicks on view_policies button$")
    public void userClicksOnViewPolicies() {
        dashboard_index.clickOnViewPolicies();
    }

    @Then("^for the Dashboard_index page, user is able to click view_policies element$")
    public void verifyUserIsClickableViewPolicies() {
        dashboard_index.elementIsClickableViewPolicies();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in view_policies element$")
    public void userVerifiesTextContainsInViewPolicies(String typeText) {
        dashboard_index.verifyViewPoliciesContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies view_policies button is visible$")
    public void verifyViewPoliciesIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        dashboard_index.verifyViewPoliciesIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of view_policies button$")
    public void verifyViewPoliciesText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        dashboard_index.verifyViewPoliciesText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" view_policies$")
    public void rightClickOnViewPolicies() {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        dashboard_index.rightClickOnViewPolicies();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for view_policies$")
    public void userVerifyValueForViewPolicies(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.view_policies);
        dashboard_index.verifyValueFromViewPolicies(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over view_policies$")
    public void hoverOverViewPolicies() {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        dashboard_index.hoverOverViewPolicies();
    }

    @Then("^for the Dashboard_index page, user scrolls to view_policies element$")
    public void scrollToViewPoliciesElement() {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        dashboard_index.scrollToViewPoliciesElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for view_policies$")
    public void verifyAttributeContainsValueForViewPolicies(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
        dashboard_index.verifyAttributeContainsValueForViewPolicies(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies view_policies button is enabled$")
    public void userIsEnabledViewPolicies() {
        dashboard_index.verifyViewPoliciesIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to view_policies element$")
    public void changeFocusToViewPolicies() {
        dashboard_index.changeFocusToViewPolicies();
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
    }

    @Then("^for the Dashboard_index page, user verifies view_policies element is disabled")
    public void verifyViewPoliciesIsDisabled() {
        dashboard_index.verifyViewPoliciesIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.view_policies);
    }

    @When("^for the Dashboard_index page, user clicks on policy button$")
    public void userClicksOnPolicy() {
        dashboard_index.clickOnPolicy();
    }

    @Then("^for the Dashboard_index page, user is able to click policy element$")
    public void verifyUserIsClickablePolicy() {
        dashboard_index.elementIsClickablePolicy();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in policy element$")
    public void userVerifiesTextContainsInPolicy(String typeText) {
        dashboard_index.verifyPolicyContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies policy button is visible$")
    public void verifyPolicyIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        dashboard_index.verifyPolicyIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of policy button$")
    public void verifyPolicyText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        dashboard_index.verifyPolicyText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" policy$")
    public void rightClickOnPolicy() {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        dashboard_index.rightClickOnPolicy();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for policy$")
    public void userVerifyValueForPolicy(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.policy);
        dashboard_index.verifyValueFromPolicy(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over policy$")
    public void hoverOverPolicy() {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        dashboard_index.hoverOverPolicy();
    }

    @Then("^for the Dashboard_index page, user scrolls to policy element$")
    public void scrollToPolicyElement() {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        dashboard_index.scrollToPolicyElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for policy$")
    public void verifyAttributeContainsValueForPolicy(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.policy);
        dashboard_index.verifyAttributeContainsValueForPolicy(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies policy button is enabled$")
    public void userIsEnabledPolicy() {
        dashboard_index.verifyPolicyIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to policy element$")
    public void changeFocusToPolicy() {
        dashboard_index.changeFocusToPolicy();
        //The below function is for web element @FindBy(Dashboard_index.policy);
    }

    @Then("^for the Dashboard_index page, user verifies policy element is disabled")
    public void verifyPolicyIsDisabled() {
        dashboard_index.verifyPolicyIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.policy);
    }

    @When("^for the Dashboard_index page, user clicks on Leave_Management button$")
    public void userClicksOnLeaveManagement() {
        dashboard_index.clickOnLeaveManagement();
    }

    @Then("^for the Dashboard_index page, user is able to click Leave_Management element$")
    public void verifyUserIsClickableLeaveManagement() {
        dashboard_index.elementIsClickableLeaveManagement();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Leave_Management element$")
    public void userVerifiesTextContainsInLeaveManagement(String typeText) {
        dashboard_index.verifyLeaveManagementContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Leave_Management button is visible$")
    public void verifyLeaveManagementIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        dashboard_index.verifyLeaveManagementIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Leave_Management button$")
    public void verifyLeaveManagementText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        dashboard_index.verifyLeaveManagementText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Leave_Management$")
    public void rightClickOnLeaveManagement() {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        dashboard_index.rightClickOnLeaveManagement();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Leave_Management$")
    public void userVerifyValueForLeaveManagement(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Leave_Management);
        dashboard_index.verifyValueFromLeaveManagement(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Leave_Management$")
    public void hoverOverLeaveManagement() {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        dashboard_index.hoverOverLeaveManagement();
    }

    @Then("^for the Dashboard_index page, user scrolls to Leave_Management element$")
    public void scrollToLeaveManagementElement() {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        dashboard_index.scrollToLeaveManagementElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Leave_Management$")
    public void verifyAttributeContainsValueForLeaveManagement(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
        dashboard_index.verifyAttributeContainsValueForLeaveManagement(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Leave_Management button is enabled$")
    public void userIsEnabledLeaveManagement() {
        dashboard_index.verifyLeaveManagementIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to leave_management element$")
    public void changeFocusToLeaveManagement() {
        dashboard_index.changeFocusToLeaveManagement();
        //The below function is for web element @FindBy(Dashboard_index.Leave_Management);
    }

    @Then("^for the Dashboard_index page, user verifies leave_management element is disabled")
    public void verifyLeaveManagementIsDisabled() {
        dashboard_index.verifyLeaveManagementIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.leave_management);
    }

    @When("^for the Dashboard_index page, user clicks on timesheet button$")
    public void userClicksOnTimesheet() {
        dashboard_index.clickOnTimesheet();
    }

    @Then("^for the Dashboard_index page, user is able to click timesheet element$")
    public void verifyUserIsClickableTimesheet() {
        dashboard_index.elementIsClickableTimesheet();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in timesheet element$")
    public void userVerifiesTextContainsInTimesheet(String typeText) {
        dashboard_index.verifyTimesheetContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies timesheet button is visible$")
    public void verifyTimesheetIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        dashboard_index.verifyTimesheetIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of timesheet button$")
    public void verifyTimesheetText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        dashboard_index.verifyTimesheetText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" timesheet$")
    public void rightClickOnTimesheet() {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        dashboard_index.rightClickOnTimesheet();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for timesheet$")
    public void userVerifyValueForTimesheet(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.timesheet);
        dashboard_index.verifyValueFromTimesheet(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over timesheet$")
    public void hoverOverTimesheet() {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        dashboard_index.hoverOverTimesheet();
    }

    @Then("^for the Dashboard_index page, user scrolls to timesheet element$")
    public void scrollToTimesheetElement() {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        dashboard_index.scrollToTimesheetElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for timesheet$")
    public void verifyAttributeContainsValueForTimesheet(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
        dashboard_index.verifyAttributeContainsValueForTimesheet(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies timesheet button is enabled$")
    public void userIsEnabledTimesheet() {
        dashboard_index.verifyTimesheetIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to timesheet element$")
    public void changeFocusToTimesheet() {
        dashboard_index.changeFocusToTimesheet();
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
    }

    @Then("^for the Dashboard_index page, user verifies timesheet element is disabled")
    public void verifyTimesheetIsDisabled() {
        dashboard_index.verifyTimesheetIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.timesheet);
    }

    @When("^for the Dashboard_index page, user clicks on Customize_your_holidays button$")
    public void userClicksOnCustomizeYourHolidays() {
        dashboard_index.clickOnCustomizeYourHolidays();
    }

    @Then("^for the Dashboard_index page, user is able to click Customize_your_holidays element$")
    public void verifyUserIsClickableCustomizeYourHolidays() {
        dashboard_index.elementIsClickableCustomizeYourHolidays();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Customize_your_holidays element$")
    public void userVerifiesTextContainsInCustomizeYourHolidays(String typeText) {
        dashboard_index.verifyCustomizeYourHolidaysContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Customize_your_holidays button is visible$")
    public void verifyCustomizeYourHolidaysIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        dashboard_index.verifyCustomizeYourHolidaysIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Customize_your_holidays button$")
    public void verifyCustomizeYourHolidaysText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        dashboard_index.verifyCustomizeYourHolidaysText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Customize_your_holidays$")
    public void rightClickOnCustomizeYourHolidays() {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        dashboard_index.rightClickOnCustomizeYourHolidays();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Customize_your_holidays$")
    public void userVerifyValueForCustomizeYourHolidays(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        dashboard_index.verifyValueFromCustomizeYourHolidays(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Customize_your_holidays$")
    public void hoverOverCustomizeYourHolidays() {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        dashboard_index.hoverOverCustomizeYourHolidays();
    }

    @Then("^for the Dashboard_index page, user scrolls to Customize_your_holidays element$")
    public void scrollToCustomizeYourHolidaysElement() {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        dashboard_index.scrollToCustomizeYourHolidaysElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Customize_your_holidays$")
    public void verifyAttributeContainsValueForCustomizeYourHolidays(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
        dashboard_index.verifyAttributeContainsValueForCustomizeYourHolidays(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Customize_your_holidays button is enabled$")
    public void userIsEnabledCustomizeYourHolidays() {
        dashboard_index.verifyCustomizeYourHolidaysIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to customize_your_holidays element$")
    public void changeFocusToCustomizeYourHolidays() {
        dashboard_index.changeFocusToCustomizeYourHolidays();
        //The below function is for web element @FindBy(Dashboard_index.Customize_your_holidays);
    }

    @Then("^for the Dashboard_index page, user verifies customize_your_holidays element is disabled")
    public void verifyCustomizeYourHolidaysIsDisabled() {
        dashboard_index.verifyCustomizeYourHolidaysIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.customize_your_holidays);
    }

    @When("^for the Dashboard_index page, user clicks on btnUpdateProfile button$")
    public void userClicksOnBtnupdateprofile() {
        dashboard_index.clickOnBtnupdateprofile();
    }

    @Then("^for the Dashboard_index page, user is able to click btnUpdateProfile element$")
    public void verifyUserIsClickableBtnupdateprofile() {
        dashboard_index.elementIsClickableBtnupdateprofile();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in btnUpdateProfile element$")
    public void userVerifiesTextContainsInBtnupdateprofile(String typeText) {
        dashboard_index.verifyBtnupdateprofileContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies btnUpdateProfile button is visible$")
    public void verifyBtnupdateprofileIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        dashboard_index.verifyBtnupdateprofileIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of btnUpdateProfile button$")
    public void verifyBtnupdateprofileText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        dashboard_index.verifyBtnupdateprofileText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" btnUpdateProfile$")
    public void rightClickOnBtnupdateprofile() {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        dashboard_index.rightClickOnBtnupdateprofile();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for btnUpdateProfile$")
    public void userVerifyValueForBtnupdateprofile(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        dashboard_index.verifyValueFromBtnupdateprofile(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over btnUpdateProfile$")
    public void hoverOverBtnupdateprofile() {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        dashboard_index.hoverOverBtnupdateprofile();
    }

    @Then("^for the Dashboard_index page, user scrolls to btnUpdateProfile element$")
    public void scrollToBtnupdateprofileElement() {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        dashboard_index.scrollToBtnupdateprofileElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for btnUpdateProfile$")
    public void verifyAttributeContainsValueForBtnupdateprofile(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
        dashboard_index.verifyAttributeContainsValueForBtnupdateprofile(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies btnUpdateProfile button is enabled$")
    public void userIsEnabledBtnupdateprofile() {
        dashboard_index.verifyBtnupdateprofileIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to btnupdateprofile element$")
    public void changeFocusToBtnupdateprofile() {
        dashboard_index.changeFocusToBtnupdateprofile();
        //The below function is for web element @FindBy(Dashboard_index.btnUpdateProfile);
    }

    @Then("^for the Dashboard_index page, user verifies btnupdateprofile element is disabled")
    public void verifyBtnupdateprofileIsDisabled() {
        dashboard_index.verifyBtnupdateprofileIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.btnupdateprofile);
    }

    @When("^for the Dashboard_index page, user clicks on Apply button$")
    public void userClicksOnApply() {
        dashboard_index.clickOnApply();
    }

    @Then("^for the Dashboard_index page, user is able to click Apply element$")
    public void verifyUserIsClickableApply() {
        dashboard_index.elementIsClickableApply();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Apply element$")
    public void userVerifiesTextContainsInApply(String typeText) {
        dashboard_index.verifyApplyContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Apply button is visible$")
    public void verifyApplyIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        dashboard_index.verifyApplyIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Apply button$")
    public void verifyApplyText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        dashboard_index.verifyApplyText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Apply$")
    public void rightClickOnApply() {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        dashboard_index.rightClickOnApply();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Apply$")
    public void userVerifyValueForApply(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Apply);
        dashboard_index.verifyValueFromApply(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Apply$")
    public void hoverOverApply() {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        dashboard_index.hoverOverApply();
    }

    @Then("^for the Dashboard_index page, user scrolls to Apply element$")
    public void scrollToApplyElement() {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        dashboard_index.scrollToApplyElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Apply$")
    public void verifyAttributeContainsValueForApply(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Apply);
        dashboard_index.verifyAttributeContainsValueForApply(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Apply button is enabled$")
    public void userIsEnabledApply() {
        dashboard_index.verifyApplyIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to apply element$")
    public void changeFocusToApply() {
        dashboard_index.changeFocusToApply();
        //The below function is for web element @FindBy(Dashboard_index.Apply);
    }

    @Then("^for the Dashboard_index page, user verifies apply element is disabled")
    public void verifyApplyIsDisabled() {
        dashboard_index.verifyApplyIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.apply);
    }

    @Then("^for the Dashboard_index page, user is able to click Search element$")
    public void verifyUserIsClickableSearch() {
        dashboard_index.elementIsClickableSearch();
    }

    @When("^for the Dashboard_index page, user clears text for Search input element$")
    public void userClearSearch() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        dashboard_index.clearSearch();
    }

    @When("^for the Dashboard_index page, user gets the text of Search element$")
    public void userGetTextSearch() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        dashboard_index.getTextFromSearch();
    }

    @When("^for the Dashboard_index page, user enters \"(.*)\" as Search element and presses enter$")
    public void typeTextAndEnterForSearch(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        dashboard_index.typeTextAndEnterForSearch(typeText);
    }

    @When("^for the Dashboard_index page, user enters \"(.*)\" as Search element and presses tab$")
    public void typeTextAndTabForSearch(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        dashboard_index.typeTextAndTabForSearch(typeText);
    }

    @When("^for the Dashboard_index page, user enters \"(.*)\" as input for Search$")
    public void userEntersAsSearch(String typeText) {
        dashboard_index.typeTextIntoSearch(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Search input is visible$")
    public void verifySearchIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        dashboard_index.verifySearchIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Search input$")
    public void verifySearchText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        dashboard_index.verifySearchText(typeText);
    }

    @When("^for the Dashboard_index page, user verifies Search input is enabled$")
    public void userIsEnabledSearch() {
        dashboard_index.verifySearchIsEnabled();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the value for Search$")
    public void userVerifyValueForSearch(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Search);
        dashboard_index.verifyValueFromSearch(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" attribute for Search as \"(.*)\"$")
    public void userVerifyAttributeForSearch(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Dashboard_index.Search);
        dashboard_index.verifyAttributeValueForSearch(attributeName,attributeValue);
    }

    @Then("^for the Dashboard_index page, user verifies value for Search input element is cleared$")
    public void verifyValueClearedForSearch() {
        dashboard_index.verifyValueClearedForSearch();
    }

    @Then("^for the Dashboard_index page, user hovers over Search$")
    public void hoverOverSearch() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        dashboard_index.hoverOverSearch();
    }

    @Then("^for the Dashboard_index page, user scrolls to Search element$")
    public void scrollToSearchElement() {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        dashboard_index.scrollToSearchElement();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Search element$")
    public void userVerifiesTextContainsInSearch(String typeText) {
        dashboard_index.verifySearchContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Search$")
    public void verifyAttributeContainsValueForSearch(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Search);
        dashboard_index.verifyAttributeContainsValueForSearch(attribute, value);
    }

    @Then("^for the Dashboard_index page, user changes focus to search element$")
    public void changeFocusToSearch() {
        dashboard_index.changeFocusToSearch();
        //The below function is for web element @FindBy(Dashboard_index.Search);
    }

    @When("^for the Dashboard_index page, user clicks on Apply_LNSA button$")
    public void userClicksOnApplyLnsa() {
        dashboard_index.clickOnApplyLnsa();
    }

    @Then("^for the Dashboard_index page, user is able to click Apply_LNSA element$")
    public void verifyUserIsClickableApplyLnsa() {
        dashboard_index.elementIsClickableApplyLnsa();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Apply_LNSA element$")
    public void userVerifiesTextContainsInApplyLnsa(String typeText) {
        dashboard_index.verifyApplyLnsaContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Apply_LNSA button is visible$")
    public void verifyApplyLnsaIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        dashboard_index.verifyApplyLnsaIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Apply_LNSA button$")
    public void verifyApplyLnsaText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        dashboard_index.verifyApplyLnsaText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Apply_LNSA$")
    public void rightClickOnApplyLnsa() {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        dashboard_index.rightClickOnApplyLnsa();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Apply_LNSA$")
    public void userVerifyValueForApplyLnsa(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        dashboard_index.verifyValueFromApplyLnsa(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Apply_LNSA$")
    public void hoverOverApplyLnsa() {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        dashboard_index.hoverOverApplyLnsa();
    }

    @Then("^for the Dashboard_index page, user scrolls to Apply_LNSA element$")
    public void scrollToApplyLnsaElement() {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        dashboard_index.scrollToApplyLnsaElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Apply_LNSA$")
    public void verifyAttributeContainsValueForApplyLnsa(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
        dashboard_index.verifyAttributeContainsValueForApplyLnsa(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Apply_LNSA button is enabled$")
    public void userIsEnabledApplyLnsa() {
        dashboard_index.verifyApplyLnsaIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to apply_lnsa element$")
    public void changeFocusToApplyLnsa() {
        dashboard_index.changeFocusToApplyLnsa();
        //The below function is for web element @FindBy(Dashboard_index.Apply_LNSA);
    }

    @Then("^for the Dashboard_index page, user verifies apply_lnsa element is disabled")
    public void verifyApplyLnsaIsDisabled() {
        dashboard_index.verifyApplyLnsaIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.apply_lnsa);
    }

    @When("^for the Dashboard_index page, user clicks on Organization_Structure button$")
    public void userClicksOnOrganizationStructure() {
        dashboard_index.clickOnOrganizationStructure();
    }

    @Then("^for the Dashboard_index page, user is able to click Organization_Structure element$")
    public void verifyUserIsClickableOrganizationStructure() {
        dashboard_index.elementIsClickableOrganizationStructure();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Organization_Structure element$")
    public void userVerifiesTextContainsInOrganizationStructure(String typeText) {
        dashboard_index.verifyOrganizationStructureContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Organization_Structure button is visible$")
    public void verifyOrganizationStructureIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        dashboard_index.verifyOrganizationStructureIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Organization_Structure button$")
    public void verifyOrganizationStructureText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        dashboard_index.verifyOrganizationStructureText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Organization_Structure$")
    public void rightClickOnOrganizationStructure() {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        dashboard_index.rightClickOnOrganizationStructure();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Organization_Structure$")
    public void userVerifyValueForOrganizationStructure(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Organization_Structure);
        dashboard_index.verifyValueFromOrganizationStructure(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Organization_Structure$")
    public void hoverOverOrganizationStructure() {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        dashboard_index.hoverOverOrganizationStructure();
    }

    @Then("^for the Dashboard_index page, user scrolls to Organization_Structure element$")
    public void scrollToOrganizationStructureElement() {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        dashboard_index.scrollToOrganizationStructureElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Organization_Structure$")
    public void verifyAttributeContainsValueForOrganizationStructure(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
        dashboard_index.verifyAttributeContainsValueForOrganizationStructure(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Organization_Structure button is enabled$")
    public void userIsEnabledOrganizationStructure() {
        dashboard_index.verifyOrganizationStructureIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to organization_structure element$")
    public void changeFocusToOrganizationStructure() {
        dashboard_index.changeFocusToOrganizationStructure();
        //The below function is for web element @FindBy(Dashboard_index.Organization_Structure);
    }

    @Then("^for the Dashboard_index page, user verifies organization_structure element is disabled")
    public void verifyOrganizationStructureIsDisabled() {
        dashboard_index.verifyOrganizationStructureIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.organization_structure);
    }

    @When("^for the Dashboard_index page, user clicks on Review_Request button$")
    public void userClicksOnReviewRequest() {
        dashboard_index.clickOnReviewRequest();
    }

    @Then("^for the Dashboard_index page, user is able to click Review_Request element$")
    public void verifyUserIsClickableReviewRequest() {
        dashboard_index.elementIsClickableReviewRequest();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Review_Request element$")
    public void userVerifiesTextContainsInReviewRequest(String typeText) {
        dashboard_index.verifyReviewRequestContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Review_Request button is visible$")
    public void verifyReviewRequestIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        dashboard_index.verifyReviewRequestIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Review_Request button$")
    public void verifyReviewRequestText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        dashboard_index.verifyReviewRequestText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Review_Request$")
    public void rightClickOnReviewRequest() {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        dashboard_index.rightClickOnReviewRequest();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Review_Request$")
    public void userVerifyValueForReviewRequest(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Review_Request);
        dashboard_index.verifyValueFromReviewRequest(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Review_Request$")
    public void hoverOverReviewRequest() {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        dashboard_index.hoverOverReviewRequest();
    }

    @Then("^for the Dashboard_index page, user scrolls to Review_Request element$")
    public void scrollToReviewRequestElement() {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        dashboard_index.scrollToReviewRequestElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Review_Request$")
    public void verifyAttributeContainsValueForReviewRequest(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
        dashboard_index.verifyAttributeContainsValueForReviewRequest(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Review_Request button is enabled$")
    public void userIsEnabledReviewRequest() {
        dashboard_index.verifyReviewRequestIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to review_request element$")
    public void changeFocusToReviewRequest() {
        dashboard_index.changeFocusToReviewRequest();
        //The below function is for web element @FindBy(Dashboard_index.Review_Request);
    }

    @Then("^for the Dashboard_index page, user verifies review_request element is disabled")
    public void verifyReviewRequestIsDisabled() {
        dashboard_index.verifyReviewRequestIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.review_request);
    }

    @When("^for the Dashboard_index page, user clicks on Submit_Feedback button$")
    public void userClicksOnSubmitFeedback() {
        dashboard_index.clickOnSubmitFeedback();
    }

    @Then("^for the Dashboard_index page, user is able to click Submit_Feedback element$")
    public void verifyUserIsClickableSubmitFeedback() {
        dashboard_index.elementIsClickableSubmitFeedback();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Submit_Feedback element$")
    public void userVerifiesTextContainsInSubmitFeedback(String typeText) {
        dashboard_index.verifySubmitFeedbackContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Submit_Feedback button is visible$")
    public void verifySubmitFeedbackIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        dashboard_index.verifySubmitFeedbackIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Submit_Feedback button$")
    public void verifySubmitFeedbackText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        dashboard_index.verifySubmitFeedbackText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Submit_Feedback$")
    public void rightClickOnSubmitFeedback() {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        dashboard_index.rightClickOnSubmitFeedback();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Submit_Feedback$")
    public void userVerifyValueForSubmitFeedback(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        dashboard_index.verifyValueFromSubmitFeedback(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Submit_Feedback$")
    public void hoverOverSubmitFeedback() {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        dashboard_index.hoverOverSubmitFeedback();
    }

    @Then("^for the Dashboard_index page, user scrolls to Submit_Feedback element$")
    public void scrollToSubmitFeedbackElement() {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        dashboard_index.scrollToSubmitFeedbackElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Submit_Feedback$")
    public void verifyAttributeContainsValueForSubmitFeedback(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
        dashboard_index.verifyAttributeContainsValueForSubmitFeedback(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Submit_Feedback button is enabled$")
    public void userIsEnabledSubmitFeedback() {
        dashboard_index.verifySubmitFeedbackIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to submit_feedback element$")
    public void changeFocusToSubmitFeedback() {
        dashboard_index.changeFocusToSubmitFeedback();
        //The below function is for web element @FindBy(Dashboard_index.Submit_Feedback);
    }

    @Then("^for the Dashboard_index page, user verifies submit_feedback element is disabled")
    public void verifySubmitFeedbackIsDisabled() {
        dashboard_index.verifySubmitFeedbackIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.submit_feedback);
    }

    @When("^for the Dashboard_index page, user clicks on System_Upgrade_Request button$")
    public void userClicksOnSystemUpgradeRequest() {
        dashboard_index.clickOnSystemUpgradeRequest();
    }

    @Then("^for the Dashboard_index page, user is able to click System_Upgrade_Request element$")
    public void verifyUserIsClickableSystemUpgradeRequest() {
        dashboard_index.elementIsClickableSystemUpgradeRequest();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in System_Upgrade_Request element$")
    public void userVerifiesTextContainsInSystemUpgradeRequest(String typeText) {
        dashboard_index.verifySystemUpgradeRequestContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies System_Upgrade_Request button is visible$")
    public void verifySystemUpgradeRequestIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        dashboard_index.verifySystemUpgradeRequestIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of System_Upgrade_Request button$")
    public void verifySystemUpgradeRequestText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        dashboard_index.verifySystemUpgradeRequestText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" System_Upgrade_Request$")
    public void rightClickOnSystemUpgradeRequest() {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        dashboard_index.rightClickOnSystemUpgradeRequest();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for System_Upgrade_Request$")
    public void userVerifyValueForSystemUpgradeRequest(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        dashboard_index.verifyValueFromSystemUpgradeRequest(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over System_Upgrade_Request$")
    public void hoverOverSystemUpgradeRequest() {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        dashboard_index.hoverOverSystemUpgradeRequest();
    }

    @Then("^for the Dashboard_index page, user scrolls to System_Upgrade_Request element$")
    public void scrollToSystemUpgradeRequestElement() {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        dashboard_index.scrollToSystemUpgradeRequestElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for System_Upgrade_Request$")
    public void verifyAttributeContainsValueForSystemUpgradeRequest(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
        dashboard_index.verifyAttributeContainsValueForSystemUpgradeRequest(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies System_Upgrade_Request button is enabled$")
    public void userIsEnabledSystemUpgradeRequest() {
        dashboard_index.verifySystemUpgradeRequestIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to system_upgrade_request element$")
    public void changeFocusToSystemUpgradeRequest() {
        dashboard_index.changeFocusToSystemUpgradeRequest();
        //The below function is for web element @FindBy(Dashboard_index.System_Upgrade_Request);
    }

    @Then("^for the Dashboard_index page, user verifies system_upgrade_request element is disabled")
    public void verifySystemUpgradeRequestIsDisabled() {
        dashboard_index.verifySystemUpgradeRequestIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.system_upgrade_request);
    }

    @When("^for the Dashboard_index page, user clicks on configure_timesheet button$")
    public void userClicksOnConfigureTimesheet() {
        dashboard_index.clickOnConfigureTimesheet();
    }

    @Then("^for the Dashboard_index page, user is able to click configure_timesheet element$")
    public void verifyUserIsClickableConfigureTimesheet() {
        dashboard_index.elementIsClickableConfigureTimesheet();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in configure_timesheet element$")
    public void userVerifiesTextContainsInConfigureTimesheet(String typeText) {
        dashboard_index.verifyConfigureTimesheetContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies configure_timesheet button is visible$")
    public void verifyConfigureTimesheetIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        dashboard_index.verifyConfigureTimesheetIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of configure_timesheet button$")
    public void verifyConfigureTimesheetText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        dashboard_index.verifyConfigureTimesheetText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" configure_timesheet$")
    public void rightClickOnConfigureTimesheet() {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        dashboard_index.rightClickOnConfigureTimesheet();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for configure_timesheet$")
    public void userVerifyValueForConfigureTimesheet(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.configure_timesheet);
        dashboard_index.verifyValueFromConfigureTimesheet(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over configure_timesheet$")
    public void hoverOverConfigureTimesheet() {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        dashboard_index.hoverOverConfigureTimesheet();
    }

    @Then("^for the Dashboard_index page, user scrolls to configure_timesheet element$")
    public void scrollToConfigureTimesheetElement() {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        dashboard_index.scrollToConfigureTimesheetElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for configure_timesheet$")
    public void verifyAttributeContainsValueForConfigureTimesheet(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
        dashboard_index.verifyAttributeContainsValueForConfigureTimesheet(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies configure_timesheet button is enabled$")
    public void userIsEnabledConfigureTimesheet() {
        dashboard_index.verifyConfigureTimesheetIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to configure_timesheet element$")
    public void changeFocusToConfigureTimesheet() {
        dashboard_index.changeFocusToConfigureTimesheet();
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
    }

    @Then("^for the Dashboard_index page, user verifies configure_timesheet element is disabled")
    public void verifyConfigureTimesheetIsDisabled() {
        dashboard_index.verifyConfigureTimesheetIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.configure_timesheet);
    }

    @When("^for the Dashboard_index page, user clicks on Next button$")
    public void userClicksOnNext() {
        dashboard_index.clickOnNext();
    }

    @Then("^for the Dashboard_index page, user is able to click Next element$")
    public void verifyUserIsClickableNext() {
        dashboard_index.elementIsClickableNext();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Next element$")
    public void userVerifiesTextContainsInNext(String typeText) {
        dashboard_index.verifyNextContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Next button is visible$")
    public void verifyNextIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        dashboard_index.verifyNextIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Next button$")
    public void verifyNextText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        dashboard_index.verifyNextText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Next$")
    public void rightClickOnNext() {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        dashboard_index.rightClickOnNext();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Next$")
    public void userVerifyValueForNext(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Next);
        dashboard_index.verifyValueFromNext(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Next$")
    public void hoverOverNext() {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        dashboard_index.hoverOverNext();
    }

    @Then("^for the Dashboard_index page, user scrolls to Next element$")
    public void scrollToNextElement() {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        dashboard_index.scrollToNextElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Next$")
    public void verifyAttributeContainsValueForNext(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Next);
        dashboard_index.verifyAttributeContainsValueForNext(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Next button is enabled$")
    public void userIsEnabledNext() {
        dashboard_index.verifyNextIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to next element$")
    public void changeFocusToNext() {
        dashboard_index.changeFocusToNext();
        //The below function is for web element @FindBy(Dashboard_index.Next);
    }

    @Then("^for the Dashboard_index page, user verifies next element is disabled")
    public void verifyNextIsDisabled() {
        dashboard_index.verifyNextIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.next);
    }

    @Then("^for the Dashboard_index page, user is able to click contactNo element$")
    public void verifyUserIsClickableContactno() {
        dashboard_index.elementIsClickableContactno();
    }

    @When("^for the Dashboard_index page, user clears text for contactNo input element$")
    public void userClearContactno() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        dashboard_index.clearContactno();
    }

    @When("^for the Dashboard_index page, user gets the text of contactNo element$")
    public void userGetTextContactno() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        dashboard_index.getTextFromContactno();
    }

    @When("^for the Dashboard_index page, user enters \"(.*)\" as contactNo element and presses enter$")
    public void typeTextAndEnterForContactno(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        dashboard_index.typeTextAndEnterForContactno(typeText);
    }

    @When("^for the Dashboard_index page, user enters \"(.*)\" as contactNo element and presses tab$")
    public void typeTextAndTabForContactno(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        dashboard_index.typeTextAndTabForContactno(typeText);
    }

    @When("^for the Dashboard_index page, user enters \"(.*)\" as input for contactNo$")
    public void userEntersAsContactno(String typeText) {
        dashboard_index.typeTextIntoContactno(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies contactNo input is visible$")
    public void verifyContactnoIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        dashboard_index.verifyContactnoIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of contactNo input$")
    public void verifyContactnoText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        dashboard_index.verifyContactnoText(typeText);
    }

    @When("^for the Dashboard_index page, user verifies contactNo input is enabled$")
    public void userIsEnabledContactno() {
        dashboard_index.verifyContactnoIsEnabled();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the value for contactNo$")
    public void userVerifyValueForContactno(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.contactNo);
        dashboard_index.verifyValueFromContactno(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" attribute for contactNo as \"(.*)\"$")
    public void userVerifyAttributeForContactno(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Dashboard_index.contactNo);
        dashboard_index.verifyAttributeValueForContactno(attributeName,attributeValue);
    }

    @Then("^for the Dashboard_index page, user verifies value for contactNo input element is cleared$")
    public void verifyValueClearedForContactno() {
        dashboard_index.verifyValueClearedForContactno();
    }

    @Then("^for the Dashboard_index page, user hovers over contactNo$")
    public void hoverOverContactno() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        dashboard_index.hoverOverContactno();
    }

    @Then("^for the Dashboard_index page, user scrolls to contactNo element$")
    public void scrollToContactnoElement() {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        dashboard_index.scrollToContactnoElement();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in contactNo element$")
    public void userVerifiesTextContainsInContactno(String typeText) {
        dashboard_index.verifyContactnoContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for contactNo$")
    public void verifyAttributeContainsValueForContactno(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
        dashboard_index.verifyAttributeContainsValueForContactno(attribute, value);
    }

    @Then("^for the Dashboard_index page, user changes focus to contactno element$")
    public void changeFocusToContactno() {
        dashboard_index.changeFocusToContactno();
        //The below function is for web element @FindBy(Dashboard_index.contactNo);
    }

    @When("^for the Dashboard_index page, user clicks on Update button$")
    public void userClicksOnUpdate() {
        dashboard_index.clickOnUpdate();
    }

    @Then("^for the Dashboard_index page, user is able to click Update element$")
    public void verifyUserIsClickableUpdate() {
        dashboard_index.elementIsClickableUpdate();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Update element$")
    public void userVerifiesTextContainsInUpdate(String typeText) {
        dashboard_index.verifyUpdateContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Update button is visible$")
    public void verifyUpdateIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        dashboard_index.verifyUpdateIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Update button$")
    public void verifyUpdateText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        dashboard_index.verifyUpdateText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Update$")
    public void rightClickOnUpdate() {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        dashboard_index.rightClickOnUpdate();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Update$")
    public void userVerifyValueForUpdate(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Update);
        dashboard_index.verifyValueFromUpdate(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Update$")
    public void hoverOverUpdate() {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        dashboard_index.hoverOverUpdate();
    }

    @Then("^for the Dashboard_index page, user scrolls to Update element$")
    public void scrollToUpdateElement() {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        dashboard_index.scrollToUpdateElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Update$")
    public void verifyAttributeContainsValueForUpdate(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Update);
        dashboard_index.verifyAttributeContainsValueForUpdate(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Update button is enabled$")
    public void userIsEnabledUpdate() {
        dashboard_index.verifyUpdateIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to update element$")
    public void changeFocusToUpdate() {
        dashboard_index.changeFocusToUpdate();
        //The below function is for web element @FindBy(Dashboard_index.Update);
    }

    @Then("^for the Dashboard_index page, user verifies update element is disabled")
    public void verifyUpdateIsDisabled() {
        dashboard_index.verifyUpdateIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.update);
    }

    @When("^for the Dashboard_index page, user clicks on Skills button$")
    public void userClicksOnSkills() {
        dashboard_index.clickOnSkills();
    }

    @Then("^for the Dashboard_index page, user is able to click Skills element$")
    public void verifyUserIsClickableSkills() {
        dashboard_index.elementIsClickableSkills();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Skills element$")
    public void userVerifiesTextContainsInSkills(String typeText) {
        dashboard_index.verifySkillsContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Skills button is visible$")
    public void verifySkillsIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        dashboard_index.verifySkillsIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Skills button$")
    public void verifySkillsText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        dashboard_index.verifySkillsText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Skills$")
    public void rightClickOnSkills() {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        dashboard_index.rightClickOnSkills();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Skills$")
    public void userVerifyValueForSkills(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Skills);
        dashboard_index.verifyValueFromSkills(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Skills$")
    public void hoverOverSkills() {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        dashboard_index.hoverOverSkills();
    }

    @Then("^for the Dashboard_index page, user scrolls to Skills element$")
    public void scrollToSkillsElement() {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        dashboard_index.scrollToSkillsElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Skills$")
    public void verifyAttributeContainsValueForSkills(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Skills);
        dashboard_index.verifyAttributeContainsValueForSkills(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Skills button is enabled$")
    public void userIsEnabledSkills() {
        dashboard_index.verifySkillsIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to skills element$")
    public void changeFocusToSkills() {
        dashboard_index.changeFocusToSkills();
        //The below function is for web element @FindBy(Dashboard_index.Skills);
    }

    @Then("^for the Dashboard_index page, user verifies skills element is disabled")
    public void verifySkillsIsDisabled() {
        dashboard_index.verifySkillsIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.skills);
    }

    @When("^for the Dashboard_index page, user clicks on Save button$")
    public void userClicksOnSave() {
        dashboard_index.clickOnSave();
    }

    @Then("^for the Dashboard_index page, user is able to click Save element$")
    public void verifyUserIsClickableSave() {
        dashboard_index.elementIsClickableSave();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is present in Save element$")
    public void userVerifiesTextContainsInSave(String typeText) {
        dashboard_index.verifySaveContainsText(typeText);
    }

    @Then("^for the Dashboard_index page, user verifies Save button is visible$")
    public void verifySaveIsDisplayed() {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        dashboard_index.verifySaveIsDisplayed();
    }

    @Then("^for the Dashboard_index page, user verifies \"(.*)\" is the text of Save button$")
    public void verifySaveText(String typeText) {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        dashboard_index.verifySaveText(typeText);
    }

    @When("^for the Dashboard_index page, user right clicks on \"(.*)\" Save$")
    public void rightClickOnSave() {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        dashboard_index.rightClickOnSave();
    }

    @Then("^for the Dashboard_index page, User verifies \"(.*)\" is the value for Save$")
    public void userVerifyValueForSave(String valueOfElement) {
        //This function is for web element @FindBy(Dashboard_index.Save);
        dashboard_index.verifyValueFromSave(valueOfElement);
    }

    @Then("^for the Dashboard_index page, user hovers over Save$")
    public void hoverOverSave() {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        dashboard_index.hoverOverSave();
    }

    @Then("^for the Dashboard_index page, user scrolls to Save element$")
    public void scrollToSaveElement() {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        dashboard_index.scrollToSaveElement();
    }

    @Then("^for the Dashboard_index page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Save$")
    public void verifyAttributeContainsValueForSave(String attribute, String value) {
        //The below function is for web element @FindBy(Dashboard_index.Save);
        dashboard_index.verifyAttributeContainsValueForSave(attribute, value);
    }

    @When("^for the Dashboard_index page, user verifies Save button is enabled$")
    public void userIsEnabledSave() {
        dashboard_index.verifySaveIsEnabled();
    }

    @Then("^for the Dashboard_index page, user changes focus to save element$")
    public void changeFocusToSave() {
        dashboard_index.changeFocusToSave();
        //The below function is for web element @FindBy(Dashboard_index.Save);
    }

    @Then("^for the Dashboard_index page, user verifies save element is disabled")
    public void verifySaveIsDisabled() {
        dashboard_index.verifySaveIsDisabled();
        //The below function is for web element @FindBy(Dashboard_index.save);
    }
}
