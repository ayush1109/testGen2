package fetcher;


import java.util.ArrayDeque;

public class PathElementBuilder implements ElementBuilder {
    private PositionElementBuilder positionCreator = new PositionElementBuilder();

    public PathElementBuilder() {
    }

    public String create(NodeInfo nodeInfo) {
        NodeInfo current = nodeInfo;

        ArrayDeque path;
        for(path = new ArrayDeque(); current != null; current = current.getParent()) {
            String item = current.getTag();
            if (this.hasSimilarNeighbours(current)) {
                item = item + this.positionCreator.create(current);
            }

            path.addFirst(item);
        }

        return String.join(" > ", path);
    }


//    new method xpath

//    public String create(NodeInfo nodeInfo) {
//        NodeInfo current = nodeInfo;
//
//        ArrayDeque path;
//        for (path = new ArrayDeque(); current != null; current = current.getParent()) {
//            String item = current.getTag();
//            if (this.hasSimilarNeighbours(current)) {
//                item = item + this.positionCreator.create(current);
//            }
//
//            path.addFirst(item);
//        }
//
//        return "/" + String.join("/", path);
//    }


    private boolean hasSimilarNeighbours(NodeInfo current) {
        NodeInfo parent = current.getParent();
        if (parent == null) {
            return false;
        } else {
            return parent.getChildren().stream().map(NodeInfo::getTag).filter((tag) -> {
                return tag.equals(current.getTag());
            }).count() > 1L;
        }
    }
}
