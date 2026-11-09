import java.util.ArrayList;
import Structures.*;

public class FlowsAndCutsAlgs {
    public static void main(String[] args) {
        DirectedGraph FFGraphOne = new DirectedGraph(5);
        FFGraphOne.createConnection(1, 2, 3);
        FFGraphOne.createConnection(1, 3, 2);
        FFGraphOne.createConnection(2, 3, 5);
        FFGraphOne.createConnection(2, 4, 8);
        FFGraphOne.createConnection(3, 4, 4);
        FFGraphOne.createConnection(3, 5, 4);
        FFGraphOne.createConnection(4, 5, 3);
        System.out.println(FFGraphOne);
        DirectedGraph flowGraph = FordFulkerson(FFGraphOne, 1, 5);
        System.out.println(flowGraph);
        System.out.println("Max flow: " + getMaxFlow(flowGraph, 1));
    }

    public static DirectedGraph FordFulkerson(DirectedGraph graph, int source, int end) {
        int[][] F = new int[graph.getN()][graph.getN()];
        DirectedGraph GminusF = new DirectedGraph(graph);

        int[][] dfsResult = BasicSearchAlgs.ParentPointerDFS(GminusF);
        int[] pre = dfsResult[0];
        int[] post = dfsResult[1];
        while(pre[source - 1] < pre[end - 1] && post[source - 1] > post[end - 1]) {
            // Get the path
            int[] dfsPath = dfsResult[2];
            ArrayList<Edge> path = new ArrayList<>();
            int parent = end - 1;
            while(dfsPath[parent] != -1) {
                int u = dfsPath[parent];
                int v = parent;
                parent = u;
                path.add(GminusF.getEdge(u + 1, v + 1));
            }

            double min = path.get(0).getWeight(); // The flow rate change
            for(Edge edge : path) {
                if(edge.getWeight() < min) {
                    min = edge.getWeight();
                }
            }

            // Update F
            for(Edge edge : path) {
                int u = edge.getSource().getValue() - 1;
                int v = edge.getDestination().getValue() - 1;
                if(pre[v] < pre[u] && post[u] < post[v]) {
                    // It's a backwards edge
                    F[u][v] -= min;
                } else {
                    // It's a forwards edge
                    F[u][v] += min;
                }
            }

            // Set GminusF to the residual graph
            for(Edge e : path) {
                Node u = e.getSource();
                Node v = e.getDestination();
                Edge currentEdge = GminusF.getEdge(e.getSource(), e.getDestination());

                double difference = currentEdge.getWeight() - min;
                if(difference == 0) {
                    // If the difference is zero, the edge reverses 
                    if(GminusF.getEdge(v, u) == null) {
                        GminusF.editEdge(currentEdge, 0, true);
                    } else {
                        GminusF.editEdge(currentEdge.reverse(), min, false);
                        GminusF.removeEdge(currentEdge);
                    }
                } else {
                    // The difference is positive, so we should have two edges going in opposite directions
                    GminusF.editEdge(currentEdge.reverse(), min - currentEdge.getWeight(), false);
                    GminusF.editEdge(currentEdge, -min, false);
                }
            }

            // Prepare for next loop
            dfsResult = BasicSearchAlgs.ParentPointerDFS(GminusF);
            pre = dfsResult[0];
            post = dfsResult[1];
        }

        // Create the flow graph
        DirectedGraph fGraph = new DirectedGraph(graph.getN());
        for(int u = 0; u < graph.getN(); u++) {
            for(int v = 0; v < graph.getN(); v++) {
                if(F[u][v] > 0) {
                    fGraph.createConnection(u + 1, v + 1, F[u][v]);
                }
                else if(F[u][v] < 0) {
                    fGraph.createConnection(v + 1, u + 1, -F[u][v]);
                }
            }
        }

        return fGraph;
    }

    public static double getMaxFlow(DirectedGraph graph, int source) {
        double sum = 0;
        Node u = graph.getNode(source);
        for(Node v : graph.getAdjacencyList(u)) {
            sum += graph.getEdge(u, v).getWeight();
        }
        return sum;
    }
}
