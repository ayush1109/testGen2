package com.gemini.locator;

import org.openqa.selenium.By;
import com.gemini.gpog.annotation.LocatorType;

public class Lnsa_apply {

    @LocatorType(value = "button")
    public static By Monday = By.cssSelector(" html > body > div:nth-child(4) > div:nth-child(1) > section > div > div > div > section > div > div > div:nth-child(2) > div > div > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)");

    @LocatorType(value = "button")
    public static By Submit = By.xpath("//button[@id='btnReasonPop']");
}
