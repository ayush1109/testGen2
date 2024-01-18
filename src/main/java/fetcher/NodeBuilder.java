package fetcher;

/**
 * @author - Sajith,Hem,Raghav,Ayush
 * @version - 1.0
 * @since - 9/26/2023
 */


import java.util.*;
import java.util.stream.Collectors;

public class NodeBuilder {
    private static final Set<String> MAIN_ATTRIBUTED = new HashSet(Arrays.asList("id", "class"));
    private String tag;
    private String id = "";
    private Set<String> classes = Collections.emptySet();
    private Integer index = 0;
    private Map<String, String> otherAttributes = Collections.emptyMap();
    private List<String> content = new ArrayList();
    private List<NodeInfo> children = new ArrayList();

    public NodeBuilder() {
    }

    public NodeBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public NodeBuilder setIndex(int index) {
        this.index = index;
        return this;
    }

//    public NodeBuilder setAttributes(Map<String, String> attributes) {
//        this.id = (String)attributes.getOrDefault("id", "");
//        this.classes = (Set)((Stream)Optional.ofNullable((String)attributes.get("class")).map((s) -> {
//            return s.split(" ");
//        }).map(Arrays::stream).orElse(Stream.empty())).map(String::trim).filter((next) -> {
//            return !next.isEmpty();
//        }).collect(Collectors.toSet());
//        this.otherAttributes = (Map)attributes.entrySet().stream().filter((s) -> {
//            return !MAIN_ATTRIBUTED.contains(s.getKey()) && !((String)s.getKey()).trim().isEmpty();
//        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        return this;
//    }

    public NodeBuilder setAttributes(Map<String, String> attributes) {
        this.id = attributes.getOrDefault("id", "");

        this.classes = Optional.ofNullable(attributes.get("class"))
                .map(s -> Arrays.stream(s.split(" "))
                        .map(String::trim)
                        .filter(next -> !next.isEmpty())
                        .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());

        this.otherAttributes = attributes.entrySet().stream()
                .filter(entry -> !MAIN_ATTRIBUTED.contains(entry.getKey().trim()) && !entry.getKey().trim().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return this;
    }



    public NodeBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public NodeBuilder setClasses(Set<String> classes) {
        this.classes = classes;
        return this;
    }

    public NodeBuilder setOtherAttributes(Map<String, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
        return this;
    }

    public NodeBuilder addChild(NodeInfo child) {
        this.children.add(child);
        return this;
    }

    public NodeBuilder addChildren(List<NodeInfo> children) {
        this.children.addAll(children);
        return this;
    }

    public NodeBuilder setChildren(List<NodeInfo> children) {
        this.children = children;
        return this;
    }

    public NodeBuilder addContent(String content) {
        this.content.add(content);
        return this;
    }

    public NodeBuilder setContent(List<String> content) {
        this.content = content;
        return this;
    }

    public NodeBuilder copy() {
        NodeBuilder copy = new NodeBuilder();
        copy.tag = this.tag;
        copy.id = this.id;
        copy.classes = new HashSet(this.classes);
        copy.index = this.index;
        copy.otherAttributes = new HashMap(this.otherAttributes);
        copy.content = new ArrayList(this.content);
        copy.children = new ArrayList(this.children);
        return copy;
    }

    public NodeInfo build() {
        String fullContent = String.join(System.lineSeparator(), this.content);
        NodeInfo nodeInfo = new NodeInfo(this.tag, this.id, this.classes, this.index, this.otherAttributes, this.children, fullContent);
        Iterator var3 = nodeInfo.getChildren().iterator();

        while(var3.hasNext()) {
            NodeInfo child = (NodeInfo)var3.next();
            child.setParent(nodeInfo);
        }

        return nodeInfo;
    }
}
