package algorithms;

import model.edge.Edge;
import model.graph.Graph;
import model.node.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Hayder
 */

public class Grouping {

    public static final Set<Node> similarityClass(Graph graph, Node node) {
        return similarityClass(graph, node, Double.NEGATIVE_INFINITY);
    }

    public static final Set<Node> similarityClass(Graph graph, Node node, double threshold) {

        var similarityClass = graph
                .getEdges()
                .stream()
                .filter(edge -> Double.compare(edge.getWeight(), threshold) >= 0)
                .filter(edge -> edge.getFrom().equals(node))
                .map(Edge::getTo)
                .collect(Collectors.toSet());
        similarityClass.add(node);
        return similarityClass;

    }

    public static final Set<Node> bottomApproach(Graph graph, String communityId) {
        return bottomApproach(graph, communityId, Double.NEGATIVE_INFINITY);
    }

    public static final Set<Node> bottomApproach(Graph graph, String communityId, double threshold) {
        var communityMembers = graph
                .getNodes()
                .stream()
                .filter(node -> node.getCommunityId().equals(communityId)).collect(Collectors.toSet());

        var bottomApproach = new HashSet<Node>();
        for (var node : communityMembers) {
            var similarityClass = similarityClass(graph, node, threshold);
            if (communityMembers.containsAll(similarityClass))
                bottomApproach.add(node);
        }

        return bottomApproach;

    }

}
