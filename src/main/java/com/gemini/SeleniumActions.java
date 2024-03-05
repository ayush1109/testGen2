package com.gemini;

import fetcher.DOMFetcher;
import fetcher.Healer;
import fetcher.NodeInfo;
import fetcher.NodeManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static fetcher.Utilities.parseTree;
import static fetcher.Utilities.readProperties;

public class SeleniumActions {

    private static WebDriver driver;

    private static ArrayList<HashMap<String, String>> scenarioLocators = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> scenarioLocatorsDuplicate = new ArrayList<>();
    private static final HashMap<String, ArrayList<HashMap<String, String>>> featureMap = new HashMap<>();

    private static void setup() {
        System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/edge/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    public static void open() throws IOException, InterruptedException {
        setup();
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
        By xpath = healer.findNewLocations(element, objectList, destination, driver, action);
        return xpath;
    }

    public static void performAction(String elementName, String action, String data) {
        try {
            ArrayList<HashMap<String, String>> scenarioUpdatedLocators = new ArrayList<>();
            HashMap<String, String> locatorMap = new HashMap<>();
            String previousUrl = getFeatureNameFromUrl();
            By element = findXpath(driver, elementName, action);

            if(Utils.readProperties("trueOrFalse").equalsIgnoreCase("true"))
             if(element == null) {
                 Scanner sc = new Scanner(System.in);
                 System.out.println("Could not find xpath. Enter xpath for " + elementName);
                 element = By.xpath(sc.nextLine());
                 System.out.println("Updated xpath for " + elementName + " is " + element);
             }
            Thread.sleep(2000);
            switch (action) {
                case "click" -> {
                    driver.findElement(element).click();
                    Thread.sleep(6000);
                    String newUrl = getFeatureNameFromUrl();
                    locatorMap.put(elementName.replace(" ", "_") + "-BUTTON", element.toString());
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
                    driver.findElement(element).sendKeys(data);
                    String newUrl = getFeatureNameFromUrl();
                    if (!StringUtils.equalsIgnoreCase(previousUrl, newUrl)) {
                        scenarioLocators = new ArrayList<>();
                    }
                    locatorMap.put(elementName.replace(" ", "_") + "-INPUT", element.toString());
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
                    String newUrl = getFeatureNameFromUrl();
                    if (!StringUtils.equalsIgnoreCase(previousUrl, newUrl)) {
                        scenarioLocators = new ArrayList<>();
                    }
                    locatorMap.put(elementName.replace(" ", "_") + "-DIV", element.toString());
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
