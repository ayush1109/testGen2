package fetcher;

public enum ComponentScanner {
    PATH(new PathElementBuilder()),
    PARENT(new ParentElementBuilder()),
    TAG(new TagElementBuilder()),
    ID(new IdElementBuilder()),
    CLASS(new ElementClassBuilder()),
    POSITION(new PositionElementBuilder()),
    ATTRIBUTES(new ElementAttributesBuilder());

    private final ElementBuilder elementBuilder;

    public String createComponent(NodeInfo nodeInfo) {
        return this.elementBuilder.create(nodeInfo);
    }

    private ComponentScanner(ElementBuilder elementBuilder) {
        this.elementBuilder = elementBuilder;
    }
}
