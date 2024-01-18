package fetcher;



import java.util.Optional;

public class PositionElementBuilder implements ElementBuilder {
    public PositionElementBuilder() {
    }

    public String create(NodeInfo nodeInfo) {
        NodeInfo parent = nodeInfo.getParent();
        return parent == null ? "" : (String)Optional.ofNullable(nodeInfo.getIndex()).map((i) -> {
            return String.format(":nth-child(%d)", i + 1);
        }).orElse("");
    }
}
