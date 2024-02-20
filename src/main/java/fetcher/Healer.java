package fetcher;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;

public class Healer {
    static List<NodeInfo> searchNodes = new ArrayList<>();
    static WebDriver driver;
    private List<Set<ComponentScanner>> selectorDetailLevels;
    private static final List<Set<ComponentScanner>> TEMP = new ArrayList<Set<ComponentScanner>>() {
        {
            this.add(EnumSet.of(ComponentScanner.TAG, ComponentScanner.ID));
            this.add(EnumSet.of(ComponentScanner.TAG, ComponentScanner.CLASS));
            this.add(EnumSet.of(ComponentScanner.PARENT, ComponentScanner.TAG, ComponentScanner.ID, ComponentScanner.CLASS));
            this.add(EnumSet.of(ComponentScanner.PARENT, ComponentScanner.TAG, ComponentScanner.CLASS, ComponentScanner.POSITION));
            this.add(EnumSet.of(ComponentScanner.PARENT, ComponentScanner.TAG, ComponentScanner.ID, ComponentScanner.CLASS, ComponentScanner.ATTRIBUTES));
            this.add(EnumSet.of(ComponentScanner.PATH));
        }
    };

    public By findNewLocations(String identity, List<NodeInfo> paths, NodeInfo destination, WebDriver driver, String action) {
        this.driver = driver;
        int n = 0;
        PathLocator pathLocator = new PathLocator(new LCSNodePathDistance(), new LongestNodeDistance());
        AbstractMap.SimpleImmutableEntry<Integer, Map<Double, List<AbstractMap.SimpleImmutableEntry<NodeInfo, Integer>>>> scoresToNodes = pathLocator.findScoresToNodes(new NodePath((NodeInfo[]) paths.toArray(new NodeInfo[0])), destination);
        List<NodePath> nodes = pathLocator.destinationLeaves;
        this.selectorDetailLevels = Collections.unmodifiableList(TEMP);
        List<Set<ComponentScanner>> detailLevels = this.selectorDetailLevels;
        List<NodeInfo> listNodes = findNodeWithText(nodes, identity);
        for (NodeInfo nodeInfo : listNodes
        ) {
            if (action.equals("input") || (action.equals("write"))) {
                n = nodeInfo.getIndex();
                break;
            }
        }
        System.out.println(listNodes);
        if (listNodes.size() == 0) {
            By xpath = By.xpath("//*[contains(text(), '" + identity + "')]");
            if (identity.equalsIgnoreCase("delete") || identity.equalsIgnoreCase("Bulk Approve") || identity.equalsIgnoreCase("close"))
                return xpath;
            if (driver.findElements(xpath).size() > 1) {
                if (identity.equalsIgnoreCase("lunch")) {
                    return By.xpath("//*[text()='" + identity + "']");
                }
                return By.xpath("//*[text()=' " + identity + "']");
            } else
                return By.xpath("//*[contains(text(), '" + identity + "')]");
        }
//        Comparator<NodeInfo> customComparator = Comparator.comparing(node -> !node.toString().contains("Search"))
//                .thenComparingInt(node -> node.);
//        construct(listNodes.get(0));
        // Sort the listNodes based on the customComparator
//        Collections.sort(listNodes, customComparator);


        StringBuilder xpath = userFriendlyXpath(listNodes.get(n));
        By absolute = null;
        if (StringUtils.equalsIgnoreCase("select", action)) {

        }
        int flag = 1;
        if ((xpath == null) || (driver.findElements(By.xpath(xpath.toString())).size() == 0)) {
            xpath = null;
//            Set<ComponentScanner> detailLevel = detailLevels.get(5);
//            absolute = this.absoluteXpath(listNodes.get(n), detailLevel);
            Set<ComponentScanner> detailLevel = detailLevels.get(5);
            xpath = new StringBuilder(construct(listNodes.get(n), detailLevel).toString());
            xpath = new StringBuilder(xpath.substring(15));
            flag = 0;
        }

//        for (int index = 5; index < detailLevels.size(); index++) {
        if (flag == 1) return By.xpath(xpath.toString());
        else return By.cssSelector(xpath.toString());
    }

    protected HealedElement toLocator(ScoreGenerator<NodeInfo> node, WebDriver driver) {

        this.selectorDetailLevels = Collections.unmodifiableList(TEMP);
        List<Set<ComponentScanner>> detailLevels = this.selectorDetailLevels;

        List<WebElement> elements = null;
        for (int index = 4; index < detailLevels.size(); index++) {
            Set<ComponentScanner> detailLevel = detailLevels.get(index);
            By locator = this.construct((NodeInfo) node.getValue(), detailLevel);
            elements = driver.findElements(locator);
            if (elements.size() <= 1) {
                break; // Exit the loop if only one or no elements found
            }
        }

        HealedElement healedElement = new HealedElement();
        if (!elements.isEmpty()) {
            healedElement.setElement(elements.get(0));
        }

        return healedElement;
    }


    protected By construct(NodeInfo nodeInfo, Set<ComponentScanner> detailLevel) {
        return By.cssSelector((String) detailLevel.stream().map((component) -> {
            return component.createComponent(nodeInfo);
        }).collect(Collectors.joining()));
    }

    public static StringBuilder userFriendlyXpath(NodeInfo nodeInfo) {

        StringBuilder xpath = new StringBuilder("//");
        for (int i = 1; i <= 4; i++) {
            switch (i) {
                case 1:
                    buildXPathWithOneField(xpath, nodeInfo);
                    Boolean status = checkstatus(xpath);
                    if (status) {
                        return xpath;
                    }
                    xpath = new StringBuilder("//");
                    break;
                case 2:
                    xpath = buildXPathWithTwoFields(xpath, nodeInfo);
                    if (checkstatus(xpath)) {
                        return xpath;
                    }
                    xpath = new StringBuilder("//");
                    // Add more cases for additional field combinations as needed

                case 3:
                    xpath = buildXPathWithThreeFields(xpath, nodeInfo);
                    if (checkstatus(xpath)) {
                        return xpath;
                    }
                default:
                    return null;
            }
        }
        return null;
    }

    protected By absoluteXpath(NodeInfo nodeInfo, Set<ComponentScanner> detailLevel) {
        return By.xpath((String) detailLevel.stream().map((component) -> {
            return component.createComponent(nodeInfo);
        }).collect(Collectors.joining()));
    }

    public static List<NodeInfo> findNodeWithText(List<NodePath> nodes, String searchText) {
        searchNodes.clear();
        if (searchText.equalsIgnoreCase("sign in")) {
            for (NodePath node : nodes) {
                try {
                    if (node.getLastNode().getInnerText().trim().equals(searchText) || (node.getLastNode().getId().trim().equals(searchText)) || (node.getLastNode().getOtherAttributes().get("value").equals(searchText))) {
                        searchNodes.add(node.getLastNode());
                    }

                } catch (NullPointerException e) {
                    try {
                        if (node.getLastNode().getInnerText().trim().equals(searchText) || (node.getLastNode().getId().trim().equals(searchText)) || (node.getLastNode().getOtherAttributes().get("placeholder").equals(searchText))) {
                            searchNodes.add(node.getLastNode());
                        }
                    } catch (NullPointerException e1) {
                        if (node.getLastNode().getInnerText().trim().equals(searchText) || (node.getLastNode().getId().trim().equals(searchText))) {
                            searchNodes.add(node.getLastNode());
                        }
                    }
                }
//            if (node.getLastNode().getOtherAttributes().get("value") == null) {

//            }
//            else if (node.getLastNode().getInnerText().trim().equalsIgnoreCase(searchText) || (node.getLastNode().getId().trim().equalsIgnoreCase(searchText)) || (node.getLastNode().getOtherAttributes().get("value").equalsIgnoreCase(searchText))) {
//                searchNodes.add(node.getLastNode());
//            }

            }

        } else {
            for (NodePath node : nodes) {
                try {
                    if (node.getLastNode().getInnerText().trim().equalsIgnoreCase(searchText) || (node.getLastNode().getId().trim().equalsIgnoreCase(searchText)) || (node.getLastNode().getOtherAttributes().get("value").equalsIgnoreCase(searchText))) {
                        searchNodes.add(node.getLastNode());
                    }

                } catch (NullPointerException e) {
                    try {
                        if (node.getLastNode().getInnerText().trim().equalsIgnoreCase(searchText) || (node.getLastNode().getId().trim().equalsIgnoreCase(searchText)) || (node.getLastNode().getOtherAttributes().get("placeholder").equalsIgnoreCase(searchText))) {
                            searchNodes.add(node.getLastNode());
                        }
                    } catch (NullPointerException e1) {
                        if (node.getLastNode().getInnerText().trim().equalsIgnoreCase(searchText) || (node.getLastNode().getId().trim().equalsIgnoreCase(searchText))) {
                            searchNodes.add(node.getLastNode());
                        }
                    }
                }
//            if (node.getLastNode().getOtherAttributes().get("value") == null) {

//            }
//            else if (node.getLastNode().getInnerText().trim().equalsIgnoreCase(searchText) || (node.getLastNode().getId().trim().equalsIgnoreCase(searchText)) || (node.getLastNode().getOtherAttributes().get("value").equalsIgnoreCase(searchText))) {
//                searchNodes.add(node.getLastNode());
//            }

            }
        }
        return searchNodes;
    }

    private static void buildXPathWithOneField(StringBuilder xpath, NodeInfo nodeInfo) {

        if (!nodeInfo.getTag().isBlank()) {
            xpath.append(nodeInfo.getTag());
        }
    }


    private static StringBuilder buildXPathWithTwoFields(StringBuilder xpath, NodeInfo nodeInfo) {

        if (!(nodeInfo.getTag().isEmpty()) && !(nodeInfo.getId().equals(""))) {
            xpath.append(nodeInfo.getTag())
                    .append("[@id='" + nodeInfo.getId() + "']");
            return xpath;
        } else if (!(nodeInfo.getTag().isEmpty()) && !(nodeInfo.getInnerText().equals(""))) {
            xpath.append(nodeInfo.getTag())
                    .append("[text()='" + nodeInfo.getInnerText() + "']");
            return xpath;
//
        }
//        } else if (!(nodeInfo.getTag().isEmpty()) && !(nodeInfo.getOtherAttributes().get("href").equals(""))) {
//            xpath.append(nodeInfo.getTag())
//                    .append("@href='" + nodeInfo.getOtherAttributes().get("href") + "']");
//            return xpath;
//        }
//        else if (!(nodeInfo.getTag().isEmpty()) && !(nodeInfo.getOtherAttributes().get("target").equals(""))
//        {
//            xpath.append(nodeInfo.getTag())
//                    .append("@href='" + nodeInfo.getOtherAttributes().get("href") + "']");
//            return xpath;
//        }
//

//        MY SAMPLE CODE


        for (Map.Entry<String, String> entry : nodeInfo.getOtherAttributes().entrySet()) {
            String attributeName = entry.getKey();
            String attributeValue = entry.getValue();

            if (!attributeValue.isEmpty()) {
                // Handle special cases or apply a general pattern for other attributes
                xpath.append(nodeInfo.getTag()).append("[@" + attributeName + "='" + attributeValue + "']");

                if (checkstatus(xpath)) {
                    return xpath;
                } else {
                    xpath = new StringBuilder("//");
                    xpath.append(nodeInfo.getTag());
                }
            }
        }
        return null;

    }

    public static boolean checkstatus(StringBuilder xpath) {
        if (driver.findElements(By.xpath(String.valueOf(xpath))).size() > 1 || driver.findElements(By.xpath(String.valueOf(xpath))).size() == 0) {
            return false;
        } else return true;
    }

    private static StringBuilder buildXPathWithThreeFields(StringBuilder xpath, NodeInfo nodeInfo) {

        if (!nodeInfo.getTag().isEmpty() && !nodeInfo.getId().isEmpty() && !nodeInfo.getInnerText().isEmpty()) {
            xpath.append(nodeInfo.getTag())
                    .append("[@id='" + nodeInfo.getId() + "'][text()='" + nodeInfo.getInnerText() + "']");
            return xpath;
        } else if (!nodeInfo.getTag().isEmpty() && !nodeInfo.getOtherAttributes().isEmpty()) {

            for (Map.Entry<String, String> entry : nodeInfo.getOtherAttributes().entrySet()) {
                String attributeName = entry.getKey();
                String attributeValue = entry.getValue();
                if (!attributeValue.isEmpty()) {
                    xpath.append("[@" + attributeName + "='" + attributeValue + "']");
                }
            }
            return xpath;
        }
        // Add more conditions as needed

        return null;
    }


}
