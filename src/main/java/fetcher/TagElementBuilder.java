package fetcher;


public class TagElementBuilder implements ElementBuilder {
    public TagElementBuilder() {
    }

    public String create(NodeInfo nodeInfo) {
        return nodeInfo.getTag();
    }
}

