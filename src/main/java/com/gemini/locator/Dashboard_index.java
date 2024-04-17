package com.gemini.locator;

import org.openqa.selenium.By;
import com.gemini.gpog.annotation.LocatorType;

public class Dashboard_index {

    @LocatorType(value = "button")
    public static By LNSA = By.xpath("//span[text()='LNSA']");

    @LocatorType(value = "button")
    public static By Reimbursement = By.xpath("//span[text()='Reimbursement']");

    @LocatorType(value = "div")
    public static By Canaan_Tower = By.xpath("//div[@id='userLocation']");

    @LocatorType(value = "div")
    public static By Location = By.xpath("//b[text()='Location']");

    @LocatorType(value = "button")
    public static By Skills = By.xpath("//a[@id='skills']");

    @LocatorType(value = "button")
    public static By btnUpdateProfile = By.xpath("//button[@id='btnUpdateProfile']");

    @LocatorType(value = "button")
    public static By Apply = By.cssSelector(" html > body > header > div > div > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(2) > span:nth-child(2)");

    @LocatorType(value = "input")
    public static By Search = By.xpath("//input[@aria-controls='tblTimeSLots']");

    @LocatorType(value = "button")
    public static By System_Upgrade_Request = By.xpath("//a[@id='systemUpgrade']");

    @LocatorType(value = "button")
    public static By Next = By.xpath("//a[text()='Next']");

    @LocatorType(value = "input")
    public static By contactNo = By.xpath("//input[@id='contactNo']");

    @LocatorType(value = "button")
    public static By Feedback = By.xpath("//span[text()='Feedback']");

    @LocatorType(value = "button")
    public static By employee_directory = By.xpath("//span[text()='Employee Directory']");

    @LocatorType(value = "button")
    public static By Lunch = By.xpath("//a[@id='applyForlunch']");

    @LocatorType(value = "button")
    public static By view_policies = By.xpath("//span[text()='View Policies']");

    @LocatorType(value = "button")
    public static By policy = By.xpath("//span[text()='Policy']");

    @LocatorType(value = "button")
    public static By Save = By.xpath("//button[@id='btnSaveSkills']");

    @LocatorType(value = "button")
    public static By Leave_Management = By.xpath("//span[text()='Leave Management']");

    @LocatorType(value = "button")
    public static By timesheet = By.xpath("//span[text()='TimeSheet']");

    @LocatorType(value = "button")
    public static By Customize_your_holidays = By.xpath("//a[text()='Customize your holidays']");

    @LocatorType(value = "button")
    public static By Apply_LNSA = By.xpath("//span[text()='Apply LNSA']");

    @LocatorType(value = "button")
    public static By Organization_Structure = By.xpath("//span[text()='Organization Structure']");

    @LocatorType(value = "button")
    public static By Review_Request = By.xpath("//span[text()='Review Request']");

    @LocatorType(value = "button")
    public static By Submit_Feedback = By.xpath("//span[text()='Submit Feedback']");

    @LocatorType(value = "button")
    public static By configure_timesheet = By.xpath("//span[text()='Configure Timesheet']");

    @LocatorType(value = "button")
    public static By Update = By.cssSelector(" html > body > div:nth-child(14) > div > div > div:nth-child(3) > button:nth-child(1) > i");

    @LocatorType(value = "button")
    public static By Configure_Timesheet = By.xpath("//span[text()='Configure Timesheet']");

    @LocatorType(value = "input")
    public static By Mobile_number = By.xpath("//input[@id='contactNo']");

    @LocatorType(value = "button")
    public static By MongoDB = By.xpath("//a[text()='MongoDB']");

    @LocatorType(value = "button")
    public static By Create_Timesheet = By.xpath("//span[text()='Create Timesheet']");

    @LocatorType(value = "button")
    public static By Manage_Task_Template = By.xpath("//span[text()='Manage Task Template']");
}
