package algorithms;

import model.graph.Graph;
import model.node.Node;

import java.util.Set;

/**
 * @author Hayder
 */

public interface UnweightedProtoSearch {

    Set<Node> find(Graph graph);

}
