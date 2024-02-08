package fetcher;


import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class IdElementBuilder implements ElementBuilder {
    public IdElementBuilder() {
    }

    public String create(NodeInfo nodeInfo) {
        return (String)Optional.ofNullable(nodeInfo.getId()).map(String::trim).filter(StringUtils::isNotBlank).map("#"::concat).orElse("");
    }



//    new method xpath

//    public String create(NodeInfo nodeInfo) {
//        return (String) Optional.ofNullable(nodeInfo.getId()).map(String::trim).filter(StringUtils::isNotBlank).map("[@id='"::concat).map(s -> s + "']").orElse("");
//    }


}