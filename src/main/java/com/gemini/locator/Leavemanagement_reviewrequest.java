package com.gemini.locator;

import org.openqa.selenium.By;
import com.gemini.gpog.annotation.LocatorType;

public class Leavemanagement_reviewrequest {

    @LocatorType(value = "div")
    public static By Service_Unavailable = By.xpath("//h2");

    @LocatorType(value = "button")
    public static By Delegate = By.xpath("//button[@id='btnUserDelegation']");
}
