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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static fetcher.Utilities.parseTree;

public class SeleniumActions {

    private static WebDriver driver;

    private static ArrayList<HashMap<String, String>> scenarioLocators = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> scenarioLocatorsDuplicate = new ArrayList<>();
    private static final HashMap<String, ArrayList<HashMap<String, String>>> featureMap = new HashMap<>();

    private static void setup() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\raghav.suneja\\OneDrive - Gemini Solutions\\Desktop\\GemGen\\nlp_codeless\\src\\main\\resources\\drivers\\edge\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
    public static void open() throws IOException, InterruptedException {
        setup();
        driver.get(Utils.readProperties("url"));
        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[text()='Login via SSO']")).click();
        Thread.sleep(10000);
    }

    public static void close() {
        driver.quit();
    }

    private static By findXpath(WebDriver driver, String element) {

        NodeManager nodeManager = new NodeManager();
        List<NodeInfo> objectList = new ArrayList<>();
        DOMFetcher DOMFetcher = new DOMFetcher(driver);
        String pageSource = DOMFetcher.getPageSource();
        NodeInfo destination = parseTree(pageSource);
        Healer healer = new Healer();
        By xpath = healer.findNewLocations(element, objectList, destination, driver);
        return xpath;
    }

    public static void performAction(String elementName, String action, String data) {
        try {
            ArrayList<HashMap<String, String>> scenarioUpdatedLocators = new ArrayList<>();
            HashMap<String, String> locatorMap = new HashMap<>();
            String previousUrl = getFeatureNameFromUrl();
            By element = findXpath(driver, elementName);
            switch (action) {
                case "click" -> {
                    driver.findElement(element).click();
                    Thread.sleep(4000);
                    String newUrl = getFeatureNameFromUrl();
                    if (!StringUtils.equalsIgnoreCase(previousUrl, newUrl)) {
//                        scenarioUpdatedLocators.addAll(scenarioLocators);
//                        SeleniumActions.featureMap.put(previousUrl, scenarioUpdatedLocators);
                        scenarioLocators = new ArrayList<>();
                        ;
                    }
                    locatorMap.put(elementName + "_BUTTON", element.toString());
//                    scenarioUpdatedLocators.add(locatorMap);
                    int flag = 0;
                    for (HashMap<String, String> copiedValue : scenarioLocatorsDuplicate) {
                        if(copiedValue.keySet().stream().filter(key -> key.contains(elementName)).collect(Collectors.toList()).size() > 0)
                        {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag==0)
                    {
                        scenarioLocators.add(locatorMap);
                    }

                    SeleniumActions.featureMap.put(newUrl, scenarioLocators);
                    LocatorPOJO.setFeatures(SeleniumActions.featureMap);
                    scenarioLocatorsDuplicate.add(locatorMap);
                }
                case "input" -> driver.findElement(element).sendKeys(data);
                case "verify" -> {
                    String newUrl = getFeatureNameFromUrl();
                    if (!StringUtils.equalsIgnoreCase(previousUrl, newUrl)) {
                        scenarioUpdatedLocators.addAll(scenarioLocators);
                        SeleniumActions.featureMap.put(previousUrl, scenarioUpdatedLocators);
                        scenarioLocators.clear();
                    }
                    locatorMap.put(elementName +"_DIV", element.toString());
                    scenarioUpdatedLocators.add(locatorMap);
                    scenarioLocators.add(locatorMap);
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
