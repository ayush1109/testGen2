package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Actions {
    static WebDriver driver;

    public static void initialiseBrowser(String ain) {
        String initialiseBrosersloc = "webdriver." + ain + ".driver";

        switch (ain) {
            case "edge":
                System.setProperty(initialiseBrosersloc, "C:\\Users\\raghav.suneja\\Downloads\\GEMHEALUPDATED\\gemHeal\\src\\test\\resources\\drivers\\windows\\edge\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;

            case "chrome":
                System.setProperty(initialiseBrosersloc, "src/test/resources/drivers/msedgedriver.exe");
                driver = new ChromeDriver();
                break;

        }


    }

    public static void openWebsite(String ad) {
        driver.get(ad);

    }
}
