package com.gemini.locator;

import org.openqa.selenium.By;
import com.gemini.gpog.annotation.LocatorType;

public class Account_login {

    @LocatorType(value = "input")
    public static By Username = By.xpath("//input[@id='username']");

    @LocatorType(value = "button")
    public static By LNSA = By.xpath("//span[text()='LNSA']");

    @LocatorType(value = "button")
    public static By forgot_password = By.xpath("//a[@id='lnkForgotPassword']");

    @LocatorType(value = "button")
    public static By Reset_Password = By.xpath("//input[@id='btnLogin']");

    @LocatorType(value = "div")
    public static By Enter_your_username_and_password = By.xpath("//span[text()='Enter your username and password.']");

    @LocatorType(value = "button")
    public static By Sign_in = By.xpath("//input[@id='btnLogin']");

    @LocatorType(value = "user")
    public static By dd = By.xpath("//button[@id='dd-user-menu']");

    @LocatorType(value = "input")
    public static By Password = By.xpath("//input[@id='password']");

    @LocatorType(value = "button")
    public static By TimeSheet = By.xpath("//span[text()='TimeSheet']");

    @LocatorType(value = "button")
    public static By btnUpdateProfile = By.xpath("//button[@id='btnUpdateProfile']");
}
