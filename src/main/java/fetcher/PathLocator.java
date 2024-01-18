package fetcher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class PathLocator {
    private static final Logger log = LoggerFactory.getLogger(PathLocator.class);
    private final NodePathDistance nodePathDistance;
    private final NodeDistance nodeDistance;
    public List<NodePath> destinationLeaves;
    public PathLocator(NodePathDistance nodePathDistance, NodeDistance nodeDistance) {
        this.nodePathDistance = nodePathDistance;
        this.nodeDistance = nodeDistance;
    }

    public NodeInfo findNearest(NodePath nodePath, NodeInfo newSource) {
        List<ScoreGenerator<NodeInfo>> found = this.find(nodePath, newSource, 1);
        return found.isEmpty() ? null : (NodeInfo) ((ScoreGenerator) found.get(0)).getValue();
    }

    public List<ScoreGenerator<NodeInfo>> find(NodePath nodePath, NodeInfo newSource, int bestGuessesCount) {
        return this.getSortedNodes((Map) this.findScoresToNodes(nodePath, newSource).getValue(), bestGuessesCount, -1.0);
    }


    public AbstractMap.SimpleImmutableEntry<Integer, Map<Double, List<AbstractMap.SimpleImmutableEntry<NodeInfo, Integer>>>> findScoresToNodes(NodePath nodePath, NodeInfo newSource) {
        List<NodePath> destinationLeaves = this.findAllLeafPaths(newSource);

        this.destinationLeaves = this.findAllLeafPaths(newSource);

        return null;
    }




    public List<ScoreGenerator<NodeInfo>> getSortedNodes(Map<Double, List<AbstractMap.SimpleImmutableEntry<NodeInfo, Integer>>> scoresToNodes, int bestGuessesCount, double guessCap) {
        int nodeLimit = this.normalizeLimit(bestGuessesCount);
        double scoreLimit = this.normalizeScoreCap(guessCap);

        return scoresToNodes.keySet().stream().sorted(Comparator.reverseOrder()).filter(ScoreFilter.logFiltered((score) -> {

            return score >= scoreLimit;
        }, (score) -> {
            // log.debug("Skipping nodes, because their score={} is less than {}", score, scoreLimit);
        })).flatMap((score) -> {
            return scoresToNodes.get(score).stream().map((entry) -> {
                return new ScoreGenerator<NodeInfo>(score, entry.getKey());
            });
        }).limit((long) nodeLimit).collect(Collectors.toList());
    }


    private List<NodePath> findAllLeafPaths(NodeInfo nodeInfo) {
        List<NodePath> nodePaths = new ArrayList();
        this.addLeafPath(nodePaths, new NodePath(nodeInfo));
        return nodePaths;
    }

    private void addLeafPath(List<NodePath> leaves, NodePath current) {
        Deque<NodePath> nodePaths = new ArrayDeque();
        nodePaths.addFirst(current);

        while(true) {
            while(!nodePaths.isEmpty()) {
                NodePath nodePath = (NodePath) nodePaths.removeLast();
                NodeInfo nodeInfo = nodePath.getLastNode();
                if (nodeInfo.getChildren() != null && !nodeInfo.getChildren().isEmpty()) {
                    Iterator var6 = nodeInfo.getChildren().iterator();

                    while(var6.hasNext()) {
                        NodeInfo child = (NodeInfo)var6.next();
                        nodePaths.addFirst(GemUtils.addNode(nodePath, child));
                    }
                } else {
                    leaves.add(nodePath);
                }
            }

            return;
        }
    }

    private double normalizeScoreCap(double value) {
        if (value > 1.0) {
            log.warn("Required min score value={} will be ignored, because exceed allowed value. It must be in range [0..1]", value);
            return -1.0;
        } else {
            return value;
        }
    }

    private int normalizeLimit(int value) {
        if (value < 0) {
            log.warn("Desired number of result nodes={} will be reset to 1, because it must be positive", value);
            return 1;
        } else {
            return value;
        }
    }
}
