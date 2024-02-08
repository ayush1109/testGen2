package fetcher;



import fetcher.ElementBuilder;
import fetcher.NodeInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ElementAttributesBuilder implements ElementBuilder {
    private static final Set<String> SKIPPED_ATTRIBUTES = new HashSet<>();

    static {
        SKIPPED_ATTRIBUTES.add("style");
        SKIPPED_ATTRIBUTES.add("onChange");
        SKIPPED_ATTRIBUTES.add("onKeyPress");
        SKIPPED_ATTRIBUTES.add("onSubmit");
        SKIPPED_ATTRIBUTES.add("onReset");
        SKIPPED_ATTRIBUTES.add("onClick");
        SKIPPED_ATTRIBUTES.add("onclick");
        SKIPPED_ATTRIBUTES.add("onFocus");
        SKIPPED_ATTRIBUTES.add("onLoad");
        SKIPPED_ATTRIBUTES.add("media");
        SKIPPED_ATTRIBUTES.add("height");
        SKIPPED_ATTRIBUTES.add("width");
        SKIPPED_ATTRIBUTES.add("formenctype");
        SKIPPED_ATTRIBUTES.add("formnovalidate");
    }
    public ElementAttributesBuilder() {
    }

    public String create(NodeInfo nodeInfo) {
        return (String) nodeInfo.getOtherAttributes().entrySet().stream().filter((entry) -> {
            return StringUtils.isNoneBlank(new CharSequence[]{(CharSequence)entry.getKey(), (CharSequence)entry.getValue()});
        }).filter((entry) -> {
            return !SKIPPED_ATTRIBUTES.contains(entry.getKey());
        }).map((entry) -> {
            return String.format("[%s='%s']", ((String)entry.getKey()).trim(), ((String)entry.getValue()).trim());
        }).collect(Collectors.joining());
    }

//    new method xpath

//    public String create(NodeInfo nodeInfo) {
//        return (String) nodeInfo.getOtherAttributes().entrySet().stream().filter((entry) -> {
//            return StringUtils.isNoneBlank(new CharSequence[]{(CharSequence) entry.getKey(), (CharSequence) entry.getValue()});
//        }).filter((entry) -> {
//            return !SKIPPED_ATTRIBUTES.contains(entry.getKey());
//        }).map((entry) -> {
//            return String.format("[@%s='%s']", ((String) entry.getKey()).trim(), ((String) entry.getValue()).trim());
//        }).collect(Collectors.joining());
//    }

}
