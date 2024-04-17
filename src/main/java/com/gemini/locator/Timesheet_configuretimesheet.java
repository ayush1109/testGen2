package com.gemini.locator;

import org.openqa.selenium.By;
import com.gemini.gpog.annotation.LocatorType;

public class Timesheet_configuretimesheet {

    @LocatorType(value = "button")
    public static By Save = By.xpath("//input[@id='btnSavePrefrences']");

    @LocatorType(value = "button")
    public static By Assigned_Projects = By.xpath("//span[text()='Assigned Projects']");
}
