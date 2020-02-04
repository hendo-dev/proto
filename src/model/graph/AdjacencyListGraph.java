package model.graph;

import model.edge.Edge;
import model.node.Node;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Hayder
 */

public class AdjacencyListGraph implements Graph {

    private int nodeCount;
    private int edgeCount;
    private Map<Node, List<Edge>> adj;

    public AdjacencyListGraph(Set<Node> nodes, Set<Edge> edges) {
        this();
        for (Node node : nodes) {
            insertNode(node);
        }
        nodeCount = nodes.size();
        for (Edge edge : edges) {
            insertEdge(edge);
        }
    }

    public AdjacencyListGraph() {
        nodeCount = 0;
        edgeCount = 0;
        adj = new HashMap<>();
    }


    @Override
    public Set<Node> getNodes() {
        return adj.keySet();
    }

    @Override
    public Set<Edge> getEdges() {
        Set<Edge> edges = new HashSet<>();
        adj.keySet().stream().map(node -> adj.get(node)).forEach(edges::addAll);
        return edges;
    }

    @Override
    public void insertNode(Node node) {
        adj.put(node, new LinkedList<>());
        nodeCount++;
    }

    @Override
    public void insertEdge(Edge edge) {
        if (!adj.containsKey(edge.getFrom()))
            insertNode(edge.getFrom());
        adj.get(edge.getFrom()).add(edge);
        if (!edge.isDirected()) {
            if (!adj.containsKey(edge.getTo()))
                insertNode(edge.getTo());
            adj.get(edge.getTo()).add(new Edge(edge.getTo(), edge.getFrom(), edge.getWeight()));
        }
        edgeCount++;
    }

    @Override
    public int nodeCount() {
        return adj.keySet().size();
    }

    @Override
    public int edgeCount() {
        return adj.keySet().stream().mapToInt(node -> adj.get(node).size()).sum();
    }

    @Override
    public Set<Node> getNeighbours(Node node) {
        return adj.get(node).stream().map(Edge::getTo).collect(Collectors.toSet());
    }
}
