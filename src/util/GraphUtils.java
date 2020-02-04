package util;

import model.edge.Edge;
import model.graph.AdjacencyListGraph;
import model.graph.Graph;
import model.node.Node;

import java.util.HashSet;
import java.util.Set;

public class GraphUtils {

    public Set<Graph> communityfy(Graph graph) {

        var graphsByCommunities = new HashSet<Graph>();
        var communitiesId = new HashSet<String>();
        for (var node : graph.getNodes()) {
            communitiesId.add(node.getCommunityId());
        }
        for (var id : communitiesId) {
            var nodes = new HashSet<Node>();
            var edges = new HashSet<Edge>();
            for (var node : graph.getNodes()) {
                if (node.getCommunityId().equals(id)) {
                    nodes.add(new Node(node));
                }
            }
            for (var edge : edges) {
                if (nodes.contains(edge.getTo()) && nodes.contains(edge.getFrom()))
                    edges.add(new Edge(edge));

            }
            graphsByCommunities.add(new AdjacencyListGraph(nodes, edges));
        }
        return graphsByCommunities;
    }
}
