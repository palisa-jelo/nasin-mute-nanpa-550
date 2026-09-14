import Structures.*;
import java.util.LinkedList;
import java.util.Queue;

public class SearchAlgs {

    // Unweighted
    public static int[] BFS(Graph graph, int s) {
        Queue<Node> Q = new LinkedList<>();

        int[] distances = new int[graph.getN()];
        for(int i = 0; i < distances.length; i++) {
            distances[i] = -1;
        }
        distances[s - 1] = 0;
        Q.offer(graph.getNode(s));
        while(Q.size() > 0) {
            Node u = Q.poll();
            for(Node node : graph.getAdjacencyList(u)) {
                if(distances[node.getValue() - 1] < 0) {
                    distances[node.getValue() - 1] = distances[u.getValue() - 1] + 1;
                    Q.offer(node);
                }
            }
        }
        return distances;
    }
}
