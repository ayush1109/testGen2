package com.gemini.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import com.gemini.implementation.Account_loginImplementation;
import org.openqa.selenium.*;

public class Account_loginStepDefinition {

    public Account_loginImplementation account_login = new Account_loginImplementation();

    @Then("^for the Account_login page, user is able to click Username element$")
    public void verifyUserIsClickableUsername() {
        account_login.elementIsClickableUsername();
    }

    @When("^for the Account_login page, user clears text for Username input element$")
    public void userClearUsername() {
        //The below function is for web element @FindBy(Account_login.Username);
        account_login.clearUsername();
    }

    @When("^for the Account_login page, user gets the text of Username element$")
    public void userGetTextUsername() {
        //The below function is for web element @FindBy(Account_login.Username);
        account_login.getTextFromUsername();
    }

    @When("^for the Account_login page, user enters \"(.*)\" as Username element and presses enter$")
    public void typeTextAndEnterForUsername(String typeText) {
        //The below function is for web element @FindBy(Account_login.Username);
        account_login.typeTextAndEnterForUsername(typeText);
    }

    @When("^for the Account_login page, user enters \"(.*)\" as Username element and presses tab$")
    public void typeTextAndTabForUsername(String typeText) {
        //The below function is for web element @FindBy(Account_login.Username);
        account_login.typeTextAndTabForUsername(typeText);
    }

    @When("^for the Account_login page, user enters \"(.*)\" as input for Username$")
    public void userEntersAsUsername(String typeText) {
        account_login.typeTextIntoUsername(typeText);
    }

    @Then("^for the Account_login page, user verifies Username input is visible$")
    public void verifyUsernameIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.Username);
        account_login.verifyUsernameIsDisplayed();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is the text of Username input$")
    public void verifyUsernameText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Username);
        account_login.verifyUsernameText(typeText);
    }

    @When("^for the Account_login page, user verifies Username input is enabled$")
    public void userIsEnabledUsername() {
        account_login.verifyUsernameIsEnabled();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is the value for Username$")
    public void userVerifyValueForUsername(String valueOfElement) {
        //This function is for web element @FindBy(Account_login.Username);
        account_login.verifyValueFromUsername(valueOfElement);
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" attribute for Username as \"(.*)\"$")
    public void userVerifyAttributeForUsername(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Account_login.Username);
        account_login.verifyAttributeValueForUsername(attributeName,attributeValue);
    }

    @Then("^for the Account_login page, user verifies value for Username input element is cleared$")
    public void verifyValueClearedForUsername() {
        account_login.verifyValueClearedForUsername();
    }

    @Then("^for the Account_login page, user hovers over Username$")
    public void hoverOverUsername() {
        //The below function is for web element @FindBy(Account_login.Username);
        account_login.hoverOverUsername();
    }

    @Then("^for the Account_login page, user scrolls to Username element$")
    public void scrollToUsernameElement() {
        //The below function is for web element @FindBy(Account_login.Username);
        account_login.scrollToUsernameElement();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is present in Username element$")
    public void userVerifiesTextContainsInUsername(String typeText) {
        account_login.verifyUsernameContainsText(typeText);
    }

    @Then("^for the Account_login page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Username$")
    public void verifyAttributeContainsValueForUsername(String attribute, String value) {
        //The below function is for web element @FindBy(Account_login.Username);
        account_login.verifyAttributeContainsValueForUsername(attribute, value);
    }

    @Then("^for the Account_login page, user changes focus to username element$")
    public void changeFocusToUsername() {
        account_login.changeFocusToUsername();
        //The below function is for web element @FindBy(Account_login.Username);
    }

    @When("^for the Account_login page, user clicks on LNSA button$")
    public void userClicksOnLnsa() {
        account_login.clickOnLnsa();
    }

    @Then("^for the Account_login page, user is able to click LNSA element$")
    public void verifyUserIsClickableLnsa() {
        account_login.elementIsClickableLnsa();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is present in LNSA element$")
    public void userVerifiesTextContainsInLnsa(String typeText) {
        account_login.verifyLnsaContainsText(typeText);
    }

    @Then("^for the Account_login page, user verifies LNSA button is visible$")
    public void verifyLnsaIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.LNSA);
        account_login.verifyLnsaIsDisplayed();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is the text of LNSA button$")
    public void verifyLnsaText(String typeText) {
        //The below function is for web element @FindBy(Account_login.LNSA);
        account_login.verifyLnsaText(typeText);
    }

    @When("^for the Account_login page, user right clicks on \"(.*)\" LNSA$")
    public void rightClickOnLnsa() {
        //The below function is for web element @FindBy(Account_login.LNSA);
        account_login.rightClickOnLnsa();
    }

    @Then("^for the Account_login page, User verifies \"(.*)\" is the value for LNSA$")
    public void userVerifyValueForLnsa(String valueOfElement) {
        //This function is for web element @FindBy(Account_login.LNSA);
        account_login.verifyValueFromLnsa(valueOfElement);
    }

    @Then("^for the Account_login page, user hovers over LNSA$")
    public void hoverOverLnsa() {
        //The below function is for web element @FindBy(Account_login.LNSA);
        account_login.hoverOverLnsa();
    }

    @Then("^for the Account_login page, user scrolls to LNSA element$")
    public void scrollToLnsaElement() {
        //The below function is for web element @FindBy(Account_login.LNSA);
        account_login.scrollToLnsaElement();
    }

    @Then("^for the Account_login page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for LNSA$")
    public void verifyAttributeContainsValueForLnsa(String attribute, String value) {
        //The below function is for web element @FindBy(Account_login.LNSA);
        account_login.verifyAttributeContainsValueForLnsa(attribute, value);
    }

    @When("^for the Account_login page, user verifies LNSA button is enabled$")
    public void userIsEnabledLnsa() {
        account_login.verifyLnsaIsEnabled();
    }

    @Then("^for the Account_login page, user changes focus to lnsa element$")
    public void changeFocusToLnsa() {
        account_login.changeFocusToLnsa();
        //The below function is for web element @FindBy(Account_login.LNSA);
    }

    @Then("^for the Account_login page, user verifies lnsa element is disabled")
    public void verifyLnsaIsDisabled() {
        account_login.verifyLnsaIsDisabled();
        //The below function is for web element @FindBy(Account_login.lnsa);
    }

    @When("^for the Account_login page, user clicks on forgot_password button$")
    public void userClicksOnForgotPassword() {
        account_login.clickOnForgotPassword();
    }

    @Then("^for the Account_login page, user is able to click forgot_password element$")
    public void verifyUserIsClickableForgotPassword() {
        account_login.elementIsClickableForgotPassword();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is present in forgot_password element$")
    public void userVerifiesTextContainsInForgotPassword(String typeText) {
        account_login.verifyForgotPasswordContainsText(typeText);
    }

    @Then("^for the Account_login page, user verifies forgot_password button is visible$")
    public void verifyForgotPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        account_login.verifyForgotPasswordIsDisplayed();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is the text of forgot_password button$")
    public void verifyForgotPasswordText(String typeText) {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        account_login.verifyForgotPasswordText(typeText);
    }

    @When("^for the Account_login page, user right clicks on \"(.*)\" forgot_password$")
    public void rightClickOnForgotPassword() {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        account_login.rightClickOnForgotPassword();
    }

    @Then("^for the Account_login page, User verifies \"(.*)\" is the value for forgot_password$")
    public void userVerifyValueForForgotPassword(String valueOfElement) {
        //This function is for web element @FindBy(Account_login.forgot_password);
        account_login.verifyValueFromForgotPassword(valueOfElement);
    }

    @Then("^for the Account_login page, user hovers over forgot_password$")
    public void hoverOverForgotPassword() {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        account_login.hoverOverForgotPassword();
    }

    @Then("^for the Account_login page, user scrolls to forgot_password element$")
    public void scrollToForgotPasswordElement() {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        account_login.scrollToForgotPasswordElement();
    }

    @Then("^for the Account_login page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for forgot_password$")
    public void verifyAttributeContainsValueForForgotPassword(String attribute, String value) {
        //The below function is for web element @FindBy(Account_login.forgot_password);
        account_login.verifyAttributeContainsValueForForgotPassword(attribute, value);
    }

    @When("^for the Account_login page, user verifies forgot_password button is enabled$")
    public void userIsEnabledForgotPassword() {
        account_login.verifyForgotPasswordIsEnabled();
    }

    @Then("^for the Account_login page, user changes focus to forgot_password element$")
    public void changeFocusToForgotPassword() {
        account_login.changeFocusToForgotPassword();
        //The below function is for web element @FindBy(Account_login.forgot_password);
    }

    @Then("^for the Account_login page, user verifies forgot_password element is disabled")
    public void verifyForgotPasswordIsDisabled() {
        account_login.verifyForgotPasswordIsDisabled();
        //The below function is for web element @FindBy(Account_login.forgot_password);
    }

    @When("^for the Account_login page, user clicks on Reset_Password button$")
    public void userClicksOnResetPassword() {
        account_login.clickOnResetPassword();
    }

    @Then("^for the Account_login page, user is able to click Reset_Password element$")
    public void verifyUserIsClickableResetPassword() {
        account_login.elementIsClickableResetPassword();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is present in Reset_Password element$")
    public void userVerifiesTextContainsInResetPassword(String typeText) {
        account_login.verifyResetPasswordContainsText(typeText);
    }

    @Then("^for the Account_login page, user verifies Reset_Password button is visible$")
    public void verifyResetPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        account_login.verifyResetPasswordIsDisplayed();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is the text of Reset_Password button$")
    public void verifyResetPasswordText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        account_login.verifyResetPasswordText(typeText);
    }

    @When("^for the Account_login page, user right clicks on \"(.*)\" Reset_Password$")
    public void rightClickOnResetPassword() {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        account_login.rightClickOnResetPassword();
    }

    @Then("^for the Account_login page, User verifies \"(.*)\" is the value for Reset_Password$")
    public void userVerifyValueForResetPassword(String valueOfElement) {
        //This function is for web element @FindBy(Account_login.Reset_Password);
        account_login.verifyValueFromResetPassword(valueOfElement);
    }

    @Then("^for the Account_login page, user hovers over Reset_Password$")
    public void hoverOverResetPassword() {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        account_login.hoverOverResetPassword();
    }

    @Then("^for the Account_login page, user scrolls to Reset_Password element$")
    public void scrollToResetPasswordElement() {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        account_login.scrollToResetPasswordElement();
    }

    @Then("^for the Account_login page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Reset_Password$")
    public void verifyAttributeContainsValueForResetPassword(String attribute, String value) {
        //The below function is for web element @FindBy(Account_login.Reset_Password);
        account_login.verifyAttributeContainsValueForResetPassword(attribute, value);
    }

    @When("^for the Account_login page, user verifies Reset_Password button is enabled$")
    public void userIsEnabledResetPassword() {
        account_login.verifyResetPasswordIsEnabled();
    }

    @Then("^for the Account_login page, user changes focus to reset_password element$")
    public void changeFocusToResetPassword() {
        account_login.changeFocusToResetPassword();
        //The below function is for web element @FindBy(Account_login.Reset_Password);
    }

    @Then("^for the Account_login page, user verifies reset_password element is disabled")
    public void verifyResetPasswordIsDisabled() {
        account_login.verifyResetPasswordIsDisabled();
        //The below function is for web element @FindBy(Account_login.reset_password);
    }

    @Then("^for the Account_login page, user is able to click Enter_your_username_and_password element$")
    public void verifyUserIsClickableEnterYourUsernameAndPassword() {
        account_login.elementIsClickableEnterYourUsernameAndPassword();
    }

    @Then("^for the Account_login page, user scrolls to Enter_your_username_and_password element$")
    public void scrollToEnterYourUsernameAndPasswordElement() {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        account_login.scrollToEnterYourUsernameAndPasswordElement();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is present in Enter_your_username_and_password element$")
    public void userVerifiesTextContainsInEnterYourUsernameAndPassword(String typeText) {
        account_login.verifyEnterYourUsernameAndPasswordContainsText(typeText);
    }

    @Then("^for the Account_login page, user verifies Enter_your_username_and_password div is visible$")
    public void verifyEnterYourUsernameAndPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        account_login.verifyEnterYourUsernameAndPasswordIsDisplayed();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is the text of Enter_your_username_and_password div$")
    public void verifyEnterYourUsernameAndPasswordText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        account_login.verifyEnterYourUsernameAndPasswordText(typeText);
    }

    @Then("^for the Account_login page, User verifies \"(.*)\" is the value for Enter_your_username_and_password$")
    public void userVerifyValueForEnterYourUsernameAndPassword(String valueOfElement) {
        //This function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        account_login.verifyValueFromEnterYourUsernameAndPassword(valueOfElement);
    }

    @Then("^for the Account_login page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Enter_your_username_and_password$")
    public void verifyAttributeContainsValueForEnterYourUsernameAndPassword(String attribute, String value) {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        account_login.verifyAttributeContainsValueForEnterYourUsernameAndPassword(attribute, value);
    }

    @Then("^for the Account_login page, user hovers over Enter_your_username_and_password$")
    public void hoverOverEnterYourUsernameAndPassword() {
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        account_login.hoverOverEnterYourUsernameAndPassword();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" attribute for Enter_your_username_and_password as \"(.*)\"$")
    public void userVerifyAttributeForEnterYourUsernameAndPassword(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Account_login.Enter_your_username_and_password);
        account_login.verifyAttributeValueForEnterYourUsernameAndPassword(attributeName,attributeValue);
    }

    @Then("^for the Account_login page, user changes focus to enter_your_username_and_password element$")
    public void changeFocusToEnterYourUsernameAndPassword() {
        account_login.changeFocusToEnterYourUsernameAndPassword();
        //The below function is for web element @FindBy(Account_login.Enter_your_username_and_password);
    }

    @When("^for the Account_login page, user clicks on Sign_in button$")
    public void userClicksOnSignIn() {
        account_login.clickOnSignIn();
    }

    @Then("^for the Account_login page, user is able to click Sign_in element$")
    public void verifyUserIsClickableSignIn() {
        account_login.elementIsClickableSignIn();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is present in Sign_in element$")
    public void userVerifiesTextContainsInSignIn(String typeText) {
        account_login.verifySignInContainsText(typeText);
    }

    @Then("^for the Account_login page, user verifies Sign_in button is visible$")
    public void verifySignInIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        account_login.verifySignInIsDisplayed();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is the text of Sign_in button$")
    public void verifySignInText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        account_login.verifySignInText(typeText);
    }

    @When("^for the Account_login page, user right clicks on \"(.*)\" Sign_in$")
    public void rightClickOnSignIn() {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        account_login.rightClickOnSignIn();
    }

    @Then("^for the Account_login page, User verifies \"(.*)\" is the value for Sign_in$")
    public void userVerifyValueForSignIn(String valueOfElement) {
        //This function is for web element @FindBy(Account_login.Sign_in);
        account_login.verifyValueFromSignIn(valueOfElement);
    }

    @Then("^for the Account_login page, user hovers over Sign_in$")
    public void hoverOverSignIn() {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        account_login.hoverOverSignIn();
    }

    @Then("^for the Account_login page, user scrolls to Sign_in element$")
    public void scrollToSignInElement() {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        account_login.scrollToSignInElement();
    }

    @Then("^for the Account_login page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Sign_in$")
    public void verifyAttributeContainsValueForSignIn(String attribute, String value) {
        //The below function is for web element @FindBy(Account_login.Sign_in);
        account_login.verifyAttributeContainsValueForSignIn(attribute, value);
    }

    @When("^for the Account_login page, user verifies Sign_in button is enabled$")
    public void userIsEnabledSignIn() {
        account_login.verifySignInIsEnabled();
    }

    @Then("^for the Account_login page, user changes focus to sign_in element$")
    public void changeFocusToSignIn() {
        account_login.changeFocusToSignIn();
        //The below function is for web element @FindBy(Account_login.Sign_in);
    }

    @Then("^for the Account_login page, user verifies sign_in element is disabled")
    public void verifySignInIsDisabled() {
        account_login.verifySignInIsDisabled();
        //The below function is for web element @FindBy(Account_login.sign_in);
    }

    @Then("^for the Account_login page, user is able to click Password element$")
    public void verifyUserIsClickablePassword() {
        account_login.elementIsClickablePassword();
    }

    @When("^for the Account_login page, user clears text for Password input element$")
    public void userClearPassword() {
        //The below function is for web element @FindBy(Account_login.Password);
        account_login.clearPassword();
    }

    @When("^for the Account_login page, user gets the text of Password element$")
    public void userGetTextPassword() {
        //The below function is for web element @FindBy(Account_login.Password);
        account_login.getTextFromPassword();
    }

    @When("^for the Account_login page, user enters \"(.*)\" as Password element and presses enter$")
    public void typeTextAndEnterForPassword(String typeText) {
        //The below function is for web element @FindBy(Account_login.Password);
        account_login.typeTextAndEnterForPassword(typeText);
    }

    @When("^for the Account_login page, user enters \"(.*)\" as Password element and presses tab$")
    public void typeTextAndTabForPassword(String typeText) {
        //The below function is for web element @FindBy(Account_login.Password);
        account_login.typeTextAndTabForPassword(typeText);
    }

    @When("^for the Account_login page, user enters \"(.*)\" as input for Password$")
    public void userEntersAsPassword(String typeText) {
        account_login.typeTextIntoPassword(typeText);
    }

    @Then("^for the Account_login page, user verifies Password input is visible$")
    public void verifyPasswordIsDisplayed() {
        //The below function is for web element @FindBy(Account_login.Password);
        account_login.verifyPasswordIsDisplayed();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is the text of Password input$")
    public void verifyPasswordText(String typeText) {
        //The below function is for web element @FindBy(Account_login.Password);
        account_login.verifyPasswordText(typeText);
    }

    @When("^for the Account_login page, user verifies Password input is enabled$")
    public void userIsEnabledPassword() {
        account_login.verifyPasswordIsEnabled();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is the value for Password$")
    public void userVerifyValueForPassword(String valueOfElement) {
        //This function is for web element @FindBy(Account_login.Password);
        account_login.verifyValueFromPassword(valueOfElement);
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" attribute for Password as \"(.*)\"$")
    public void userVerifyAttributeForPassword(String attributeName, String attributeValue) {
        //This function is for web element @FindBy(Account_login.Password);
        account_login.verifyAttributeValueForPassword(attributeName,attributeValue);
    }

    @Then("^for the Account_login page, user verifies value for Password input element is cleared$")
    public void verifyValueClearedForPassword() {
        account_login.verifyValueClearedForPassword();
    }

    @Then("^for the Account_login page, user hovers over Password$")
    public void hoverOverPassword() {
        //The below function is for web element @FindBy(Account_login.Password);
        account_login.hoverOverPassword();
    }

    @Then("^for the Account_login page, user scrolls to Password element$")
    public void scrollToPasswordElement() {
        //The below function is for web element @FindBy(Account_login.Password);
        account_login.scrollToPasswordElement();
    }

    @Then("^for the Account_login page, user verifies \"(.*)\" is present in Password element$")
    public void userVerifiesTextContainsInPassword(String typeText) {
        account_login.verifyPasswordContainsText(typeText);
    }

    @Then("^for the Account_login page, user verifies attribute \"(.*?)\" contains \"(.*?)\" value for Password$")
    public void verifyAttributeContainsValueForPassword(String attribute, String value) {
        //The below function is for web element @FindBy(Account_login.Password);
        account_login.verifyAttributeContainsValueForPassword(attribute, value);
    }

    @Then("^for the Account_login page, user changes focus to password element$")
    public void changeFocusToPassword() {
        account_login.changeFocusToPassword();
        //The below function is for web element @FindBy(Account_login.Password);
    }
}
