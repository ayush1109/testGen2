package fetcher;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;

public class Healer {
    static List<NodeInfo> searchNodes = new ArrayList<>();
    WebDriver driver;
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

    public By findNewLocations(String element, List<NodeInfo> paths, NodeInfo destination) {

        int num = 0;
        PathLocator pathLocator = new PathLocator(new LCSNodePathDistance(), new LongestNodeDistance());
        AbstractMap.SimpleImmutableEntry<Integer, Map<Double, List<AbstractMap.SimpleImmutableEntry<NodeInfo, Integer>>>> scoresToNodes = pathLocator.findScoresToNodes(new NodePath((NodeInfo[]) paths.toArray(new NodeInfo[0])), destination);
        List<NodePath> nodes = pathLocator.destinationLeaves;
        this.selectorDetailLevels = Collections.unmodifiableList(TEMP);
        List<Set<ComponentScanner>> detailLevels = this.selectorDetailLevels;
        List<NodeInfo> listNodes = findNodeWithText(nodes, element);
        System.out.println(listNodes);
        if (listNodes.size() > 0) {
            for (int i = 0; i < listNodes.size(); i++) {

                if (listNodes.get(i).getTag().equals("img") || listNodes.get(i).getTag().equals("button") || listNodes.get(i).getTag().equals("input") || listNodes.get(i).getTag().equals("span")) {
                    num = i;
                    break;
                }
            }
        }
        Set<ComponentScanner> detailLevel = detailLevels.get(5);
//        By locator = this.construct((NodeInfo) nodes.get(f).getLastNode(), detailLevel);
        By locator = this.construct((NodeInfo) listNodes.get(num), detailLevel);
        return locator;
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

    public static List<NodeInfo> findNodeWithText(List<NodePath> nodes, String searchText) {
        searchNodes.clear();
        for (NodePath node : nodes) {

            if (node.getLastNode().getInnerText().equals(searchText) || node.getLastNode().toString().contains(searchText)) {
                searchNodes.add(node.getLastNode());
            }
        }
        return searchNodes;
    }


}
