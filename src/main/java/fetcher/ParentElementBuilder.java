package fetcher;


import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParentElementBuilder implements ElementBuilder {
    private final TagElementBuilder tagCreator = new TagElementBuilder();
    private final IdElementBuilder idCreator = new IdElementBuilder();
    private final ElementClassBuilder classCreator = new ElementClassBuilder();

    public ParentElementBuilder() {
    }

    public String create(NodeInfo nodeInfo) {
        NodeInfo parent = nodeInfo.getParent();
        return parent == null ? "" : ((String)Stream.of(this.tagCreator, this.idCreator, this.classCreator).map((creator) -> {
            return creator.create(parent);
        }).collect(Collectors.joining())).concat(" > ");
    }



//    new method xpath

//    public String create(NodeInfo nodeInfo) {
//        NodeInfo parent = nodeInfo.getParent();
//        return parent == null ? "" : ((String) Stream.of(this.tagCreator, this.idCreator, this.classCreator).map((creator) -> {
//            return creator.create(parent);
//        }).collect(Collectors.joining())).concat("/");
//    }

}
