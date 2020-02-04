package model.node;

import java.util.Objects;

/**
 * @author Hayder
 */

public final class Node {

    private final String nodeId;
    private final String communityId;

    public Node(Node other) {
        this.nodeId = other.nodeId;
        this.communityId = other.communityId;
    }

    public Node(final String nodeId, String communityId) {
        this.nodeId = nodeId;
        this.communityId = communityId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getCommunityId() {
        return communityId;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeId='" + nodeId + '\'' +
                ", communityId='" + communityId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(nodeId, node.nodeId) &&
                Objects.equals(communityId, node.communityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, communityId);
    }
}
