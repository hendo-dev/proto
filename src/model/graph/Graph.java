package model.graph;

import model.edge.Edge;
import model.node.Node;

import java.util.Set;

/**
 * @author Hayder
 */

public interface Graph {

    Set<Node> getNodes();

    Set<Edge> getEdges();

    void insertNode(Node node);

    void insertEdge(Edge edge);

    int nodeCount();

    int edgeCount();

    Set<Node> getNeighbours(Node node);

}
