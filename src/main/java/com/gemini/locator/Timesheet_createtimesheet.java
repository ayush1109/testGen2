package com.gemini.locator;

import org.openqa.selenium.By;
import com.gemini.gpog.annotation.LocatorType;

public class Timesheet_createtimesheet {

    @LocatorType(value = "button")
    public static By Ok = By.xpath("//button[text()='OK']");
}
