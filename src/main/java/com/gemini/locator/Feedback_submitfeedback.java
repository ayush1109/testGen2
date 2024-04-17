package com.gemini.locator;

import org.openqa.selenium.By;
import com.gemini.gpog.annotation.LocatorType;

public class Feedback_submitfeedback {

    @LocatorType(value = "button")
    public static By Provide_Feedback = By.xpath("//button[text()='Provide Feedback']");

    @LocatorType(value = "input")
    public static By feedback = By.xpath("//textarea[@id='feedback']");
}
