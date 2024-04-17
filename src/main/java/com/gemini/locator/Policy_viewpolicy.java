package com.gemini.locator;

import org.openqa.selenium.By;
import com.gemini.gpog.annotation.LocatorType;

public class Policy_viewpolicy {

    @LocatorType(value = "div")
    public static By Access_Policy = By.xpath("//td[text()='Access Policy']");
}
