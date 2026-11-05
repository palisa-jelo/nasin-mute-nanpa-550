import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.Set;

import Structures.*;

public class GraphSearchAlg {
    public static void main(String[] args) {
        System.out.println("Homework 5:\n");

        // For BFS and DFS, see BasicSearchAlgs

        // Basic DAG DP
        DirectedGraph SSSPGraph = new DirectedGraph("[[[2, 3], [3, 5], [4, 6]], [[3, 4]], [[4, -1]], []]");
        double[] d1 = SingleSourceShortestPath(SSSPGraph, 1);
        System.out.println("d[2] is " + d1[1]);
        System.out.println("d[3] is " + d1[2]);
        System.out.println("d[4] is " + d1[3]);

        DirectedGraph BFGraph = new DirectedGraph("[[[2, 4]], [[3, -2], [4, 3]], [[1, 1], [4, 3]], []]");
        double[] d2 = BellmanFord(BFGraph, 1);
        System.out.println("\nd[2] is " + d2[1]);
        System.out.println("d[3] is " + d2[2]);
        System.out.println("d[4] is " + d2[3]);

        DirectedGraph BFGraph2 = new DirectedGraph("[[[2, 53], [6, -66], [14, -84], [15, -52], [16, 70], [17, 83], [27, 76], [29, -87]], [[3, 78], [10, -72], [13, 96], [14, -81], [16, -60], [17, -99], [18, 95], [19, -82], [25, -54], [29, -56]], [[4, 91], [8, -58], [9, 63], [10, 95], [19, 58], [20, 61], [26, 89]], [[5, 69], [9, -89], [17, 87], [21, 66]], [[6, 90], [8, -52], [9, 78]], [[7, 84], [10, -96], [11, 51], [18, 83], [19, 73], [21, 51], [22, 56], [24, -51], [25, 90]], [[8, 73], [18, 57], [20, -63]], [[9, 83], [10, -96], [11, -93], [18, -86], [21, 76], [22, -93], [23, -58], [24, 95]], [[10, 98], [17, 72], [24, 82]], [[11, 73], [12, 97], [13, 96], [18, -50], [21, 61], [22, 63], [25, -56], [28, 87]], [[12, 89], [20, 50], [21, -92]], [[13, 98], [16, -66], [19, 60], [21, -59], [24, 84], [25, -63], [26, 91]], [[14, 66], [15, 91], [16, 91], [18, 90], [21, 82], [28, 74], [29, 72]], [[15, 79], [18, 58], [19, 90], [23, 98], [29, -75], [30, -67]], [[16, 96], [17, -94], [24, -84], [30, 80]], [[17, 55], [18, 74], [23, -98], [27, -84]], [[18, 70], [21, -71]], [[19, 99], [21, 54], [26, 99], [28, 53]], [[20, 89], [21, -89], [25, -94]], [[21, 87], [23, -61]], [[22, 59], [24, 91], [26, 54]], [[23, 53], [27, 83], [28, 81]], [[24, 65]], [[25, 75], [26, -82], [30, 63]], [[26, 61]], [[27, 75]], [[28, 56]], [[29, 98]], [[30, 67]], []]");
        double[] d3 = BellmanFord(BFGraph2, 1);
        System.out.println("\nd[2] is " + d3[1]);
        System.out.println("d[5] is " + d3[4]);
        System.out.println("d[10] is " + d3[9]);
        System.out.println("d[15] is " + d3[14]);

        DirectedGraph DijkstraGraph = new DirectedGraph("[[[2, 3], [3, 5]], [[3, 1]], []]");
        double[] d4 = Dijkstra(DijkstraGraph, 1);
        System.out.println("\nd[2] is " + d4[1]);
        System.out.println("d[3] is " + d4[2]);

        DirectedGraph DijkstraGraph2 = new DirectedGraph("[[[2, 58], [27, 96]], [[3, 64], [7, 51], [9, 91], [16, 82], [17, 77], [19, 62]], [[4, 93], [23, 85], [26, 86], [28, 92], [30, 50]], [[3, 97], [5, 82]], [[6, 50], [15, 92]], [[7, 95], [11, 90], [22, 53], [26, 97]], [[4, 79], [8, 99], [27, 58]], [[2, 72], [3, 93], [9, 52], [12, 63], [20, 99], [21, 81]], [[10, 86]], [[1, 84], [2, 68], [11, 68]], [[8, 52], [12, 87]], [[5, 61], [13, 88], [17, 52], [23, 94]], [[12, 86], [14, 51]], [[8, 91], [15, 73], [28, 86]], [[2, 96], [12, 66], [16, 55], [17, 58], [18, 89], [23, 74], [24, 77], [26, 78], [28, 63]], [[17, 67]], [[2, 51], [6, 98], [18, 95]], [[4, 79], [7, 95], [8, 69], [16, 71], [19, 87], [20, 94]], [[1, 67], [20, 72]], [[5, 92], [21, 71], [30, 98]], [[8, 59], [22, 56], [25, 61]], [[14, 65], [23, 63], [24, 52]], [[19, 99], [24, 90]], [[11, 72], [16, 54], [22, 59], [25, 63]], [[3, 52], [26, 65], [29, 82]], [[1, 87], [11, 68], [27, 83]], [[1, 70], [21, 91], [28, 51]], [[2, 61], [4, 89], [29, 77]], [[2, 62], [6, 66], [18, 50], [27, 84], [30, 85]], [[3, 83], [11, 60]]]");
        double[] d5 = Dijkstra(DijkstraGraph2, 1);
        System.out.println("\nd[2] is " + d5[1]);
        System.out.println("d[5] is " + d5[4]);
        System.out.println("d[10] is " + d5[9]);
    }

    public static double[] SingleSourceShortestPath(DirectedGraph graph, int source) {
        double[] OPT = new double[graph.getN()];
        for(int i = 0; i < OPT.length; i++) {
            OPT[i] = Double.POSITIVE_INFINITY;
        }
        OPT[source - 1] = 0;
        DirectedGraph reverse = DirectedGraph.reverse(graph);
        Node[] T = SortingAlgs.TopologicalSort(graph);

        for(Node node : T) {
            for(Node inNeighbour : reverse.getAdjacencyList(node)) {
                double distance = OPT[inNeighbour.getValue() - 1] + graph.getWeight(inNeighbour, node);
                OPT[node.getValue() - 1] = Math.min(OPT[node.getValue() - 1], distance);
            }
        }
        return OPT;
    }

    public static double[] BellmanFord(DirectedGraph graph, int source) {
        int n = graph.getN();
        double[][] OPT = new double[n][n];
        for(int i = 0; i < n; i++) {
            OPT[i][0] = Double.POSITIVE_INFINITY;
        }
        OPT[source - 1][0] = 0;

        DirectedGraph reverse = DirectedGraph.reverse(graph);
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < n; j++) {
                OPT[j][i] = OPT[j][i - 1];
                for(Node inNeighbour : reverse.getAdjacencyList(j + 1)) {
                    OPT[j][i] = Math.min(OPT[j][i], OPT[inNeighbour.getValue() - 1][i - 1] + graph.getWeight(inNeighbour, graph.getNode(j + 1)));
                }
            }
        }
        double[] output = new double[n];
        for(int i = 0; i < n; i++) {
            output[i] = OPT[i][n - 1];
        }
        return output;
    }

    public static double[] Dijkstra(DirectedGraph graph, int source) {
        int n = graph.getN();
        double[] OPT = new double[n];
        for(int i = 0; i < n; i++) {
            OPT[i] = Double.POSITIVE_INFINITY;
        }
        OPT[source - 1] = 0;
        BitSet visited = new BitSet(n);
        MinHashHeapSet<Entry, Node> S = new MinHashHeapSet<Entry, Node>(
        n,
        (a, b) -> Double.compare(a.dist(), b.dist()),
        (a) -> a.node());
        S.add(new Entry(graph.getNode(source), 0));

        while(S.peek() != null) {
            Node u = S.poll().node();
            if(!visited.get(u.getValue() - 1)) {
                visited.set(u.getValue() - 1);
                for(Node v : graph.getAdjacencyList(u)) {
                    double candidate = OPT[u.getValue() - 1] + graph.getWeight(u, v);
                    if (candidate < OPT[v.getValue() - 1]) {
                        OPT[v.getValue() - 1] = candidate;
                        S.add(new Entry(v, candidate));
                    }
                }
            }
        }
        return OPT;
    }
    private record Entry(Node node, double dist) {}
}
