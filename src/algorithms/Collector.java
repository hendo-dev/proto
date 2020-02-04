package algorithms;

import model.graph.Graph;
import model.node.Node;

import java.util.Set;

/**
 * @author Hayder
 */

public interface Collector {

    Node collect(Graph graph, Set<Node> seen, Set<Node> candidates);

}
