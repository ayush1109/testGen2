package fetcher;


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

    public By findNewLocations(String identity, List<NodeInfo> paths, NodeInfo destination, WebDriver driver) {
        this.driver = driver;
        PathLocator pathLocator = new PathLocator(new LCSNodePathDistance(), new LongestNodeDistance());
        AbstractMap.SimpleImmutableEntry<Integer, Map<Double, List<AbstractMap.SimpleImmutableEntry<NodeInfo, Integer>>>> scoresToNodes = pathLocator.findScoresToNodes(new NodePath((NodeInfo[]) paths.toArray(new NodeInfo[0])), destination);
        List<NodePath> nodes = pathLocator.destinationLeaves;
        this.selectorDetailLevels = Collections.unmodifiableList(TEMP);
        List<Set<ComponentScanner>> detailLevels = this.selectorDetailLevels;
        List<NodeInfo> listNodes = findNodeWithText(nodes, identity);
        System.out.println(listNodes);
//        Comparator<NodeInfo> customComparator = Comparator.comparing(node -> !node.toString().contains("Search"))
//                .thenComparingInt(node -> node.);
//        construct(listNodes.get(0));
        // Sort the listNodes based on the customComparator
//        Collections.sort(listNodes, customComparator);
        StringBuilder xpath = construct(listNodes.get(0));
        if (xpath == null) {
            Set<ComponentScanner> detailLevel = detailLevels.get(5);
            return this.construct((NodeInfo) listNodes.get(0), detailLevel);
        }
//        for (int index = 5; index < detailLevels.size(); index++) {

        return By.xpath(xpath.toString());
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

    public static StringBuilder construct(NodeInfo nodeInfo) {

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


    public static List<NodeInfo> findNodeWithText(List<NodePath> nodes, String searchText) {
        searchNodes.clear();
        for (NodePath node : nodes) {

            if (node.getLastNode().getInnerText().equals(searchText) || node.getLastNode().toString().contains(searchText)) {
                searchNodes.add(node.getLastNode());
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
        if (driver.findElements(By.xpath(String.valueOf(xpath))).size() > 1) {
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
