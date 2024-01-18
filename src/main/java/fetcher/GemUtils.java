package fetcher;


import java.util.*;

public class GemUtils {
    private GemUtils() {
    }

    public static Set<String> union(Set<String> set1, Set<String> set2) {
        HashSet<String> strings = new HashSet(set1);
        strings.addAll(set2);
        return strings;
    }

    public static Set<String> difference(Set<String> set1, Set<String> set2) {
        HashSet<String> strings = new HashSet(set1);
        strings.removeAll(set2);
        return strings;
    }

    public static Set<String> intersect(Set<String> set1, Set<String> set2) {
        HashSet<String> strings = new HashSet(set1);
        strings.retainAll(set2);
        return strings;
    }

    public static Map<String, String> fromArray(String... elems) {
        if (elems.length % 2 != 0) {
            throw new IllegalArgumentException("Number of elements should be even");
        } else {
            Map<String, String> map = new HashMap(elems.length / 2);

            for(int i = 0; i < elems.length; i += 2) {
                map.put(elems[i], elems[i + 1]);
            }

            return map;
        }
    }

    public static NodePath addNode(NodePath nodePath, NodeInfo nodeInfo) {
        List<NodeInfo> list = new LinkedList(Arrays.asList(nodePath.getNodes()));
        list.add(nodeInfo);
        return new NodePath((NodeInfo[])list.toArray(new NodeInfo[0]));
    }
}
