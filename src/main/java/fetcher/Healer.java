package fetcher;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.*;
import java.util.stream.Collectors;

public class Healer {
    static List<NodeInfo> searchNodes = new ArrayList<>();
    static List<NodePath> nodePathList = new ArrayList<>();
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

//    public WebElement findNewLocations(String identity, List<NodeInfo> paths, NodeInfo destination, WebDriver driver) {
//        // Create a PathLocator instance with LCSNodePathDistance and LongestNodeDistance
//        PathLocator pathLocator = new PathLocator(new LCSNodePathDistance(), new LongestNodeDistance());
//
//        // Find scores to nodes using the PathLocator
//        AbstractMap.SimpleImmutableEntry<Integer, Map<Double, List<AbstractMap.SimpleImmutableEntry<NodeInfo, Integer>>>> scoresToNodes = pathLocator.findScoresToNodes(new NodePath((NodeInfo[]) paths.toArray(new NodeInfo[0])), destination);
//
//        // Get sorted nodes based on scores and criteria
//        List<ScoreGenerator<NodeInfo>> scoreGenerators = pathLocator.getSortedNodes((Map) scoresToNodes.getValue(), 2, 0.6);
//        List<HealedElement> healedElements;
//        int flag = 0;
//        for (int i = 0; i < scoreGenerators.size(); i++) {
//            if(scoreGenerators.get(i).toString().contains(identity)){
//                flag = 1;
//            }
//        }
//        if(flag == 1) {
//            healedElements = scoreGenerators.stream()
//                    .filter(node -> node.toString().contains(identity))
//                    .map(node -> this.toLocator(node, driver))
//                    .filter(Objects::nonNull)
//                    .collect(Collectors.toList());
//        } else {
//            healedElements = scoreGenerators.stream()
//                    .map(node -> this.toLocator(node, driver))
//                    .filter(Objects::nonNull)
//                    .collect(Collectors.toList());
//        }
//
////        GemHealGlobalVariables.score= scoreGenerators.get(0).getScore();
////
////        List<HealedElement> healedElements = scoreGenerators.stream()
////                .map(node -> this.toLocator(node, driver))
////                .filter(Objects::nonNull)
////                .collect(Collectors.toList());
//        return healedElements.get(0).getElement();
//
//    }

    public By findNewLocations(String identity, List<NodeInfo> paths, NodeInfo destination, WebDriver driver, String action) {
        this.driver = driver;
        int n = 0;
        PathLocator pathLocator = new PathLocator(new LCSNodePathDistance(), new LongestNodeDistance());
        AbstractMap.SimpleImmutableEntry<Integer, Map<Double, List<AbstractMap.SimpleImmutableEntry<NodeInfo, Integer>>>> scoresToNodesSimple = pathLocator.findSimpleScoresToNodes(new NodePath((NodeInfo[]) paths.toArray(new NodeInfo[0])), destination);
        List<NodePath> nodes = pathLocator.destinationLeaves;
        this.selectorDetailLevels = Collections.unmodifiableList(TEMP);
        List<Set<ComponentScanner>> detailLevels = this.selectorDetailLevels;
        List<NodeInfo> listNodes = findNodeWithText(nodes, identity);

        if(listNodes.size() == 0) return null;

        if (listNodes.size() > 1) for (NodeInfo nodeInfo : listNodes
        ) {
            if (StringUtils.equalsIgnoreCase("click", action)) {
                if (StringUtils.equalsIgnoreCase("input", nodeInfo.getTag()) || StringUtils.equalsIgnoreCase("button", nodeInfo.getTag())) {
//                    int index = listNodes.indexOf(nodeInfo);
                    listNodes.remove(nodeInfo);
                    listNodes.add(0, nodeInfo);
                    break;
                }
            } else if (StringUtils.equalsIgnoreCase("input", action)) {
                if (StringUtils.equalsIgnoreCase("input", nodeInfo.getTag()) || StringUtils.equalsIgnoreCase("textarea", nodeInfo.getTag())) {
                    listNodes.remove(nodeInfo);
                    listNodes.add(0, nodeInfo);
                    break;
                }
            }
        }

        // need to if we can rank into most usable nodes first.
//        for (NodeInfo nodeInfo : listNodes
//        ) {
//            if (action.equals("input") || (action.equals("write"))) {
//                if (!(identity.equalsIgnoreCase("Mobile number") || identity.equalsIgnoreCase("Emergency Contact")))
//                    n = nodeInfo.getIndex();
//                break;
//            }
//        }
        System.out.println(listNodes);
//        if (listNodes.size() == 0) {
//            if (action.equalsIgnoreCase("select")) {
//                By xpath = By.xpath("//*[contains(text(), '" + identity + "')]/following::select");
//                if (identity.equalsIgnoreCase("Year"))
//                    return xpath;
//                if (driver.findElements(xpath).size() > 1) {
//                    if (identity.equalsIgnoreCase("lunch") || identity.equalsIgnoreCase("LNSA") || identity.equalsIgnoreCase("Country") || identity.equalsIgnoreCase("State") || identity.equalsIgnoreCase("City") || identity.equalsIgnoreCase("Location") || identity.equalsIgnoreCase("Marital Status")) {
//                        return By.xpath("//*[text()='" + identity + "']/following::select");
//                    }
//                    return By.xpath("//*[text()=' " + identity + "']/following::select");
//                } else if (identity.equalsIgnoreCase("edit")) {
//                    return By.xpath("//button[@id='btnUpdateProfile']/following::select");
//                } else
//                    return By.xpath("//*[contains(text(), '" + identity + "')]/following::select");
//            } else {
//                By xpath = By.xpath("//*[contains(text(), '" + identity + "')]");
//                if (identity.equalsIgnoreCase("delete") || identity.equalsIgnoreCase("Bulk Approve") || identity.equalsIgnoreCase("close"))
//                    return xpath;
//                if (driver.findElements(xpath).size() > 1) {
//                    if (identity.equalsIgnoreCase("lunch") || identity.equalsIgnoreCase("LNSA")) {
//                        return By.xpath("//*[text()='" + identity + "']");
//                    }
//                    if(identity.equalsIgnoreCase("Edit")) {
//                        return By.xpath("//*[@title='" + identity + "']");
//                    }
//                    if(identity.equalsIgnoreCase("Update")) {
//                        return By.xpath("//*[contains(text(), '" + identity + "')]");
//                    }
//                    return By.xpath("//*[text()=' " + identity + "']");
//                } else if (identity.equalsIgnoreCase("edit")) {
//                    return By.xpath("//button[@id='btnUpdateProfile']");
//                } else
//                    return By.xpath("//*[contains(text(), '" + identity + "')]");
//            }
//        }
//        Comparator<NodeInfo> customComparator = Comparator.comparing(node -> !node.toString().contains("Search"))
//                .thenComparingInt(node -> node.);
//        construct(listNodes.get(0));
        // Sort the listNodes based on the customComparator
//        Collections.sort(listNodes, customComparator);

        StringBuilder xpath = new StringBuilder();
        if (listNodes.size() > 1) {
//            Set<NodeInfo> nodeInfoSet = new LinkedHashSet<>(listNodes);
//            listNodes.clear();
//            listNodes.addAll(nodeInfoSet);
            for (int i = 0; i < listNodes.size(); i++) {
                xpath = userFriendlyXpath(listNodes.get(i));
                if (xpath != null && driver.findElement(By.xpath(xpath.toString())).isDisplayed()) {
                    break;
                }
            }
        } else {
            xpath = userFriendlyXpath(listNodes.get(n));
        }
        By absolute = null;
        if (StringUtils.equalsIgnoreCase("select", action) && xpath != null) {
                Objects.requireNonNull(xpath).append("/following::select");
        }
        int flag = 1;
        if ((xpath == null) || (driver.findElements(By.xpath(xpath.toString())).size() == 0)) {
            xpath = null;
//            Set<ComponentScanner> detailLevel = detailLevels.get(5);
//            absolute = this.absoluteXpath(listNodes.get(n), detailLevel);
            Set<ComponentScanner> detailLevel = detailLevels.get(5);
            xpath = new StringBuilder(construct(listNodes.get(n), detailLevel).toString());
            xpath = new StringBuilder(xpath.substring(15));
            if (StringUtils.equalsIgnoreCase("select", action)) {
                Objects.requireNonNull(xpath).append("/following::select");
            }
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

    public static List<NodeInfo> findNodeWithTextTemp(List<NodePath> nodes, String searchText) {
        for (int i = 0; i < nodes.size() - 1; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                PathLocator pathLocator = new PathLocator(new LCSNodePathDistance(), new LongestNodeDistance());

                pathLocator.calculateDistance(nodes.get(i), nodes.get(j));
            }


        }
        return null;
    }

    public static List<NodeInfo> findNodeWithText(List<NodePath> nodes, String searchText) {
        searchNodes.clear();
        PathLocator pathLocator = new PathLocator(new LCSNodePathDistance(), new LongestNodeDistance());
//        if (searchText.equalsIgnoreCase("sign in")) {
//            for (NodePath node : nodes) {
//                    try {
//                        if (node.getLastNode().getInnerText().trim().equals(searchText) || (node.getLastNode().getId().trim().equals(searchText)) || (node.getLastNode().getOtherAttributes().get("value").equals(searchText))) {
//                            searchNodes.add(node.getLastNode());
//                            nodePathList.add(node);
//                        }
//
//                    } catch (NullPointerException e) {
//                        try {
//                            if (node.getLastNode().getInnerText().trim().equals(searchText) || (node.getLastNode().getId().trim().equals(searchText)) || (node.getLastNode().getOtherAttributes().get("placeholder").equals(searchText))) {
//                                searchNodes.add(node.getLastNode());
//                                nodePathList.add(node);
//                            }
//                        } catch (NullPointerException e1) {
//                            if (node.getLastNode().getInnerText().trim().equals(searchText) || (node.getLastNode().getId().trim().equals(searchText))) {
//                                searchNodes.add(node.getLastNode());
//                                nodePathList.add(node);
//                            }
//                        }
//                    }
//
//            }
//
//        } else {
        for (NodePath node : nodes) {
            try {

                if (node.getLastNode().getInnerText().trim().equalsIgnoreCase(searchText) || (node.getLastNode().getId().trim().equalsIgnoreCase(searchText)) || (node.getLastNode().getOtherAttributes().get("value").equalsIgnoreCase(searchText))) {
                    searchNodes.add(node.getLastNode());
                    nodePathList.add(node);
                }

            } catch (NullPointerException e) {
                try {
                    if (node.getLastNode().getInnerText().trim().equalsIgnoreCase(searchText) || (node.getLastNode().getId().trim().equalsIgnoreCase(searchText)) || (node.getLastNode().getOtherAttributes().get("placeholder").equalsIgnoreCase(searchText))) {
                        searchNodes.add(node.getLastNode());
                        nodePathList.add(node);
                    }
                } catch (NullPointerException e1) {
                    if (node.getLastNode().getInnerText().trim().equalsIgnoreCase(searchText) || (node.getLastNode().getId().trim().equalsIgnoreCase(searchText))) {
                        searchNodes.add(node.getLastNode());
                        nodePathList.add(node);
                    }
                }
            }
        }

//        }

        if (searchNodes.size() == 0) {
            for (NodePath node : nodes) {
                Arrays.stream(node.getNodes()).forEach(nodeInfo -> {
                    try {

                            if (nodeInfo.getInnerText().trim().equalsIgnoreCase(searchText) || (nodeInfo.getId().trim().equalsIgnoreCase(searchText)) || (nodeInfo.getOtherAttributes().get("value").equalsIgnoreCase(searchText))) {
                            searchNodes.add(nodeInfo);
                            nodePathList.add(node);
                        }

                    } catch (NullPointerException e) {
                        try {
                            if (nodeInfo.getInnerText().trim().equalsIgnoreCase(searchText) || (nodeInfo.getId().trim().equalsIgnoreCase(searchText)) || (nodeInfo.getOtherAttributes().get("placeholder").equalsIgnoreCase(searchText))) {
                                searchNodes.add(nodeInfo);
                                nodePathList.add(node);
                            }
                        } catch (NullPointerException e1) {
                            if (nodeInfo.getInnerText().trim().equalsIgnoreCase(searchText) || (nodeInfo.getId().trim().equalsIgnoreCase(searchText))) {
                                searchNodes.add(nodeInfo);
                                nodePathList.add(node);
                            }
                        }
                    }
                });
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
