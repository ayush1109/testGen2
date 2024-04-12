package com.gemini;

import fetcher.DOMFetcher;
import fetcher.Healer;
import fetcher.NodeInfo;
import fetcher.NodeManager;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.webdriver.WebdriverContext;
import net.thucydides.core.webdriver.WebdriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static fetcher.Utilities.parseTree;
import static fetcher.Utilities.readProperties;

public class SeleniumActions extends PageObject {

    public static WebDriver driver;

    private static ArrayList<HashMap<String, String>> scenarioLocators = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> scenarioLocatorsDuplicate = new ArrayList<>();
    private static final HashMap<String, ArrayList<HashMap<String, String>>> featureMap = new HashMap<>();

    public static Set<String> failedScenarios = new HashSet<>();

    private void setup() {

        driver = getDriver();
//        System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/edge/msedgedriver.exe");
//        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    public static void openBrowser() throws IOException, InterruptedException {
        SeleniumActions actions = new SeleniumActions();
        actions.setup();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(Utils.readProperties("url"));
    }

    public static void close() {
        driver.quit();
    }

    private static By findXpath(WebDriver driver, String element, String action) {
        NodeManager nodeManager = new NodeManager();
        List<NodeInfo> objectList = new ArrayList<>();
        DOMFetcher DOMFetcher = new DOMFetcher(driver);
        String pageSource = DOMFetcher.getPageSource();
        NodeInfo destination = parseTree(pageSource);
        Healer healer = new Healer();
        return healer.findNewLocations(element, objectList, destination, driver, action);
    }

    public static void performAction(String elementName, String action, String data, String scenarioName) {
        try {
            ArrayList<HashMap<String, String>> scenarioUpdatedLocators = new ArrayList<>();
            HashMap<String, String> locatorMap = new HashMap<>();
            String previousUrl = getFeatureNameFromUrl();
            By xpath = findXpath(driver, elementName, action);

            if(Utils.readProperties("userInterventionNeeded").equalsIgnoreCase("true"))
             if(xpath == null) {
                 Scanner sc = new Scanner(System.in);
                 System.out.println("Could not find xpath. Enter xpath for " + elementName);
                 xpath = By.xpath(sc.nextLine());
                 System.out.println("Updated xpath for " + elementName + " is " + xpath);
             }
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));

            switch (action) {
                case "select" -> {
                    new Select(element).selectByVisibleText(data);
                    String newUrl = getFeatureNameFromUrl();
                    locatorMap.put(elementName.replace(" ", "_") + "-DROPDOWN", xpath.toString());
                    int flag = 0;
                    for (HashMap<String, String> copiedValue : scenarioLocatorsDuplicate) {
                        if (copiedValue.keySet().toString().replace("[", "").replace("]", "").equalsIgnoreCase(elementName.replace(" ", "_") + "-BUTTON")) {
                            flag = 1;
                            break;
                        }
                    }
                    LocatorPOJO.setFeatures(SeleniumActions.featureMap);
                    scenarioLocatorsDuplicate.add(locatorMap);
                    if (!StringUtils.equalsIgnoreCase(previousUrl, newUrl)) {
                        if (SeleniumActions.featureMap.containsKey(previousUrl)) {
                            scenarioLocators = (ArrayList<HashMap<String, String>>) SeleniumActions.featureMap.get(previousUrl).stream().collect(Collectors.toList());
                        }
                        if (flag == 0) {
                            scenarioLocators.add(locatorMap);
                        }
                        SeleniumActions.featureMap.put(previousUrl, scenarioLocators);
                        scenarioLocators = new ArrayList<>();
                    } else {
                        if (SeleniumActions.featureMap.containsKey(newUrl)) {
                            scenarioLocators = (ArrayList<HashMap<String, String>>) SeleniumActions.featureMap.get(newUrl).stream().collect(Collectors.toList());
                        }
                        if (flag == 0) {
                            scenarioLocators.add(locatorMap);
                        }
                        SeleniumActions.featureMap.put(newUrl, scenarioLocators);
                    }
                }
                case "click" -> {
                    element.click();
                    Thread.sleep(6000);
                    String newUrl = getFeatureNameFromUrl();
                    locatorMap.put(elementName.replace(" ", "_") + "-BUTTON", xpath.toString());
                    int flag = 0;
                    for (HashMap<String, String> copiedValue : scenarioLocatorsDuplicate) {
                        if (copiedValue.keySet().toString().replace("[", "").replace("]", "").equalsIgnoreCase(elementName.replace(" ", "_") + "-BUTTON")) {
                            flag = 1;
                            break;
                        }
                    }
                    LocatorPOJO.setFeatures(SeleniumActions.featureMap);
                    scenarioLocatorsDuplicate.add(locatorMap);
                    if (!StringUtils.equalsIgnoreCase(previousUrl, newUrl)) {
                        if (SeleniumActions.featureMap.containsKey(previousUrl)) {
                            scenarioLocators = (ArrayList<HashMap<String, String>>) SeleniumActions.featureMap.get(previousUrl).stream().collect(Collectors.toList());
                        }
                        if (flag == 0) {
                            scenarioLocators.add(locatorMap);
                        }
                        SeleniumActions.featureMap.put(previousUrl, scenarioLocators);
                        scenarioLocators = new ArrayList<>();
                    } else {
                        if (SeleniumActions.featureMap.containsKey(newUrl)) {
                            scenarioLocators = (ArrayList<HashMap<String, String>>) SeleniumActions.featureMap.get(newUrl).stream().collect(Collectors.toList());
                        }
                        if (flag == 0) {
                            scenarioLocators.add(locatorMap);
                        }
                        SeleniumActions.featureMap.put(newUrl, scenarioLocators);
                    }
                }
                case "input", "write" -> {
                    element.sendKeys(data);
                    String newUrl = getFeatureNameFromUrl();
                    if (!StringUtils.equalsIgnoreCase(previousUrl, newUrl)) {
                        scenarioLocators = new ArrayList<>();
                    }
                    locatorMap.put(elementName.replace(" ", "_") + "-INPUT", xpath.toString());
                    int flag = 0;

                    for (HashMap<String, String> copiedValue : scenarioLocatorsDuplicate) {
                        if (copiedValue.keySet().toString().replace("[", "").replace("]", "").equalsIgnoreCase(elementName.replace(" ", "_") + "-INPUT")) {
                            flag = 1;
                            break;
                        }
                    }

                    if (SeleniumActions.featureMap.containsKey(newUrl)) {
                        scenarioLocators = (ArrayList<HashMap<String, String>>) SeleniumActions.featureMap.get(newUrl).stream().collect(Collectors.toList());
                    }
                    if (flag == 0) {
                        scenarioLocators.add(locatorMap);
                    }
                    SeleniumActions.featureMap.put(newUrl, scenarioLocators);
                    LocatorPOJO.setFeatures(SeleniumActions.featureMap);
                    scenarioLocatorsDuplicate.add(locatorMap);
                }
                case "verify" -> {
                    boolean elementDisplayed = element.isDisplayed();
                    if(!elementDisplayed) throw new Exception();
                    String newUrl = getFeatureNameFromUrl();
                    if (!StringUtils.equalsIgnoreCase(previousUrl, newUrl)) {
                        scenarioLocators = new ArrayList<>();
                    }
                    locatorMap.put(elementName.replace(" ", "_") + "-DIV", xpath.toString());
                    int flag = 0;
                    for (HashMap<String, String> copiedValue : scenarioLocatorsDuplicate) {
                        if (copiedValue.keySet().toString().replace("[", "").replace("]", "").equalsIgnoreCase(elementName.replace(" ", "_") + "-DIV")) {
                            flag = 1;
                            break;
                        }
                    }
                    if (SeleniumActions.featureMap.containsKey(newUrl)) {
                        scenarioLocators = (ArrayList<HashMap<String, String>>) SeleniumActions.featureMap.get(previousUrl).stream().collect(Collectors.toList());
                    }
                    if (flag == 0) {
                        scenarioLocators.add(locatorMap);
                    }

                    SeleniumActions.featureMap.put(newUrl, scenarioLocators);
                    LocatorPOJO.setFeatures(SeleniumActions.featureMap);
                    scenarioLocatorsDuplicate.add(locatorMap);
                }

            }
        } catch (Exception e) {
                failedScenarios.add(scenarioName);
        }
    }

    protected static String getFeatureNameFromUrl() {
        String currentURL = driver.getCurrentUrl().split("\\?")[0].split("://")[1];
        String[] uris = StringUtils.split(currentURL, "/");
        StringBuilder featureName = new StringBuilder();
        for (int i = 1; i < uris.length; i++) {
            if (i != uris.length - 1)
                featureName.append(uris[i]).append("_");
            else
                featureName.append(uris[i]);
        }
        return StringUtils.capitalize(featureName.toString().toLowerCase());
    }

    private static String getCSSSelector(By xpath) {
        return xpath.toString().substring(15);
    }

}
