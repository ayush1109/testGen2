package fetcher;

import java.util.stream.Collectors;

public class ElementClassBuilder implements ElementBuilder {
    public ElementClassBuilder() {
    }

    public String create(NodeInfo nodeInfo) {
        return (String) nodeInfo.getClasses().stream().map((clazz) -> {
            return "." + clazz;
        }).collect(Collectors.joining());
    }




//    new method xpath

//    public String create(NodeInfo nodeInfo) {
//        return (String) nodeInfo.getClasses().stream().map((clazz) -> {
//            return "[contains(@class, '" + clazz + "')]";
//        }).collect(Collectors.joining());
//    }

}
