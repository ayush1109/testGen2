package com.gemini.locator;

import org.openqa.selenium.By;
import com.gemini.gpog.annotation.LocatorType;

public class Timesheet_managetasktemplates {

    @LocatorType(value = "button")
    public static By Add_New_Template = By.xpath("//input[@id='btnAddNewTemplate']");

    @LocatorType(value = "input")
    public static By Template_Name = By.xpath("//input[@id='txtTemplateName']");

    @LocatorType(value = "input")
    public static By Description = By.xpath("//input[@id='txtTemplateDescription']");

    @LocatorType(value = "dropdown")
    public static By Team_Name = By.xpath("//th[text()='Team Name']/following::select");

    @LocatorType(value = "dropdown")
    public static By Team_Name = By.xpath("//th[text()='Team Name']/following::select");
}
