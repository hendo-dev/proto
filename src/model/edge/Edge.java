package model.edge;

import model.node.Node;

import java.util.Objects;

/**
 * @author Hayder
 */

public class Edge {

    private Node from;
    private Node to;
    private double weight;
    private static final double UNWEIGHTED_VALUE = Double.NEGATIVE_INFINITY;
    private boolean directed;


    public Edge(Edge other) {
        this.from = new Node(other.from);
        this.to = new Node(other.to);
        this.weight = other.weight;
        this.directed = other.directed;
    }

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
        weight = UNWEIGHTED_VALUE;
        directed = false;
    }

    public Edge(Node from, Node to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        directed = false;
    }

    public Edge(Node from, Node to, boolean directed) {
        this.from = from;
        this.to = to;
        this.weight = UNWEIGHTED_VALUE;
        this.directed = directed;
    }

    public Edge(Node from, Node to, double weight, boolean directed) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.directed = directed;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isDirected() {
        return directed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return Double.compare(edge.weight, weight) == UNWEIGHTED_VALUE &&
                directed == edge.directed &&
                Objects.equals(from, edge.from) &&
                Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, weight, directed);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
}
