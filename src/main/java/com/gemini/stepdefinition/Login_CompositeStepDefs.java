package com.gemini.stepdefinition;

import io.cucumber.java.en.When;

public class Login_CompositeStepDefs {

    Account_loginStepDefinition definition = new Account_loginStepDefinition();

    @When("^I login with \"(.*?)\" username and \"(.*?)\" password$")
    public void login(String username, String password) {
        definition.userEntersAsUsername(username);
        definition.userEntersAsPassword(password);
        definition.userClicksOnSignIn();
    }
}
