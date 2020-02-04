package algorithms;

import model.edge.Edge;
import model.graph.Graph;
import model.node.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Hayder
 */

public class ShortestPathLib {

    public static Map<String, Map<String, Double>> floydWarshall(Graph graph, Set<Node> seen) {

        Set<Node> unseen = graph.getNodes().stream().filter(node -> !seen.contains(node)).collect(Collectors.toSet());
        var shortestPaths = new HashMap<String, Map<String, Double>>();

        //Initialization
        for (Node from : unseen) {
            shortestPaths.put(from.getNodeId(), new HashMap<String, Double>());
            for (Node to : unseen) {
                shortestPaths
                        .get(from)
                        .put(to.getNodeId(), from.equals(to) ? 0.0 : Double.POSITIVE_INFINITY);
            }
        }

        for (Edge edge : graph.getEdges()) {
            if (!unseen.contains(edge.getFrom())
                    || !unseen.contains(edge.getTo())
            ) continue;
            shortestPaths
                    .get(edge.getFrom().getNodeId())
                    .put(edge.getTo().getNodeId(), edge.getWeight());

        }
        for (Node k : unseen) {
            for (Node i : unseen) {
                for (Node j : unseen) {
                    double ij = shortestPaths
                            .get(i.getNodeId())
                            .get(j.getNodeId());
                    double ik = shortestPaths
                            .get(i.getNodeId())
                            .get(k.getNodeId());
                    double kj = shortestPaths
                            .get(k.getNodeId())
                            .get(j.getNodeId());
                    shortestPaths
                            .get(i.getNodeId())
                            .put(j.getNodeId(), Math.min(ij, ik + kj));
                }
            }
        }

        return shortestPaths;
    }
}
