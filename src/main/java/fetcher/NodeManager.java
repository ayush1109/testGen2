package fetcher;

/**
 * @author - Sajith,Hem,Raghav,Ayush
 * @version - 1.0
 * @since - 9/26/2023
 */


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class NodeManager {
    private static final Logger log = LoggerFactory.getLogger("GemHeal");
    private static final String SCRIPT ;
    static {
        SCRIPT = readResourceFile("elementProperties.js");
    }
    public NodeManager() {
    }
    private static String readResourceFile(String classpath) {
        try (InputStream stream = NodeManager.class.getClassLoader().getResourceAsStream(classpath)) {
            if (stream == null) {
                throw new IllegalStateException("Resource file not found: " + classpath);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                return reader.lines().collect(Collectors.joining());
            }
        } catch (IOException e) {
            throw new IllegalStateException("Could not read resource file: " + classpath, e);
        }
    }
    public List<NodeInfo> getNodePath(WebDriver driver, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        String data = (String)executor.executeScript(SCRIPT, new Object[]{webElement});
        List<NodeInfo> path = new LinkedList();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode treeNode = mapper.readTree(data);
            if (treeNode.isArray()) {
                Iterator var8 = treeNode.iterator();

                while(var8.hasNext()) {
                    JsonNode jsonNode = (JsonNode)var8.next();
                    NodeInfo nodeInfo = this.toNode(mapper.treeAsTokens(jsonNode));
                    path.add(nodeInfo);
                }
            }
        } catch (Exception var11) {
            log.error("Failed to get element node path!", var11);
        }

        return path;
    }

    private NodeInfo toNode(JsonParser parser) throws IOException {
        ObjectCodec codec = parser.getCodec();
        TreeNode tree = parser.readValueAsTree();
        String tag = (String)codec.treeToValue(tree.path("tag"), String.class);
        Integer index = (Integer)codec.treeToValue(tree.path("index"), Integer.class);
        String innerText = (String)codec.treeToValue(tree.path("innerText"), String.class);
        String id = (String)codec.treeToValue(tree.path("id"), String.class);
        Set<String> classes = (Set)codec.treeToValue(tree.path("classes"), Set.class);
        Map<String, String> attributes = (Map)codec.treeToValue(tree.path("other"), Map.class);
        return (new NodeBuilder()).setAttributes(attributes).setTag(tag).setIndex(index).setId(id).addContent(innerText).setClasses(classes).build();
    }
}
