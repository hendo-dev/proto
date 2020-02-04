package algorithms;

import model.graph.Graph;
import model.node.Node;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Hayder
 */

public final class ProtoLib {

    public static final UnweightedProtoSearch randomSelection = graph -> {

        var seen = new HashSet<Node>();
        var dominatingSet = new HashSet<Node>();

        for (Node current : graph.getNodes()) {
            if (seen.contains(current))
                continue;
            dominatingSet.add(current);
            seen.addAll(graph.getNeighbours(current));
        }
        return dominatingSet;

    };

    public static final WeightedProtoSearch findUsing = (Graph graph, Metric metric) -> {

        var seen = new HashSet<Node>();
        var dominatingSet = new HashSet<Node>();
        Set<Node> candidates;

        while (!(candidates = graph.getNodes()
                .stream()
                .filter(node -> !seen.contains(node))
                .collect(Collectors.toCollection(HashSet::new)))
                .isEmpty()) {

            Node max = null;
            switch (metric) {
                case CENTRALITY:
                    max = ProtoLib.maxCentrality.collect(graph, seen, candidates);
                    break;
                case BETWEENNESS:
                    max = ProtoLib.maxBetweenness.collect(graph, seen, candidates);
                    break;
            }
            seen.add(max);
            graph.getNeighbours(max).stream().forEach(any -> seen.add(any));
        }

        return dominatingSet;
    };

    public static final Collector maxCentrality = (graph, seen, candidates) -> candidates.stream().max((a, b) -> {

        var aCount = graph.getNeighbours(a)
                .stream()
                .filter(any -> !seen.contains(any))
                .count();
        var bCount = graph.getNeighbours(b)
                .stream()
                .filter(any -> !seen.contains(any))
                .count();
        return Long.compare(aCount, bCount);

    }).get();

    public static final Collector maxBetweenness = (graph, seen, candidates) -> {

        var shortestPath = ShortestPathLib.floydWarshall(graph, seen);
        var betweenness = new HashMap<Node, Integer>();

        for (Node node : candidates) {
            betweenness.put(node, 0);
        }

        for (Node k : candidates) {
            for (Node i : candidates) {
                for (Node j : candidates) {
                    double ij = shortestPath
                            .get(i.getNodeId())
                            .get(j.getNodeId());
                    double ik = shortestPath
                            .get(i.getNodeId())
                            .get(k.getNodeId());
                    double kj = shortestPath
                            .get(k.getNodeId())
                            .get(j.getNodeId());
                    if (ij == ik + kj && ij != Double.POSITIVE_INFINITY) {
                        int old = betweenness.get(k);
                        betweenness.put(k, old + 1);
                    }

                }
            }
        }

        return betweenness.keySet().stream()
                .max(Comparator.comparingInt(betweenness::get)).get();

    };

}
