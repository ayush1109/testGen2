package fetcher;


import java.util.Arrays;

public class NodePath {
    private NodeInfo[] nodeInfos;

    public NodePath(NodeInfo[] nodeInfos) {
        this.nodeInfos = nodeInfos;
    }

    public NodePath(NodeInfo nodeInfo) {
        this.nodeInfos = new NodeInfo[]{nodeInfo};
    }

    public NodeInfo[] getNodes() {
        return this.nodeInfos;
    }

    public void setNodes(NodeInfo[] nodeInfos) {
        this.nodeInfos = nodeInfos;
    }

    public NodeInfo getLastNode() throws ArrayIndexOutOfBoundsException {
        return this.nodeInfos[this.nodeInfos.length - 1];
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            NodePath nodePath = (NodePath)o;
            return Arrays.equals(this.nodeInfos, nodePath.nodeInfos);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(this.nodeInfos);
    }

    public String toString() {
        return "NodePath{nodeInfos=" + Arrays.toString(this.nodeInfos) + '}';
    }
}
