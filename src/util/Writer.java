package util;

import model.graph.Graph;
import model.node.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class Writer {

    public static void write(Graph graph, Map<Node, Double> metricValue, String file) throws FileNotFoundException {
        var printWriter = new PrintWriter(new File(file));
        graph.getNodes()
                .stream()
                .map(node -> String.format("%s %s %d", node.getNodeId(), node.getCommunityId(), metricValue.get(node)))
                .forEach(printWriter::println);
        printWriter.close();
    }
}
