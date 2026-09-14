import Structures.*;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.BitSet;

public class UndirectedGraphAlg {
    public static void main(String[] args) {

        UndirectedGraph testGraph = new UndirectedGraph("[[[2, 1], [3, 3]], [[1, 1], [3, 4], [4, 2]], [[1, 3], [2, 4], [4, 5]], [[2, 2], [3, 5]], [[]]]");
        System.out.println(testGraph);
        printNodes(SearchAlgs.BFS(testGraph, 1));
        Prim(testGraph);
        testGraph.getTrimmedEdges();
    }

    public static void printNodes(int[] nodes) {
        String output = "[" + nodes[0];
        for(int i = 1; i < nodes.length; i++) {
            output += ", " + (nodes[i] < 0 ? "inf" : nodes[i]);
        }
        output += "]";
        System.out.println(output);
    }

    public static Edge[] Prim(UndirectedGraph graph) {
        BitSet visited = new BitSet(graph.getN());
        visited.clear();
        visited.set(0);
        Edge[] mst = new Edge[graph.getN() - 1];
        ArrayList<Node> nodes = graph.getNodes();
        //EdgePriorityQueue Q = new EdgePriorityQueue(graph.getM());

        //Q.add();
        for(int i = 1; i < graph.getN(); i++) {

        }

        return mst;
    }

        private class EdgePriorityQueue extends PriorityQueue<Edge> {
        Edge[] edges;
        int size;

        public EdgePriorityQueue(int m) {
            this.edges = new Edge[m];
            this.size = 0;
        }

        private int getLeft(int index) {
            return 2 * index + 1;
        }

        private int getRight(int index) {
            return 2 * index + 2;
        }

        private int getParent(int index) {
            return (index - 1) / 2;
        }

        private void bubbleUp(int i) {
            int parent = getParent(i);
            while(edges[i].getWeight() < edges[parent].getWeight()) {
                Edge temp = edges[parent];
                edges[parent] = edges[i];
                edges[i] = temp;
                i = parent;
            }
        }

        private void bubbleDown(int i) {
            
        }

        @Override 
        public boolean add(Edge edge) {
            edges[size] = edge;
            size++;
            return true;
        }



    }



    /*
    ArrayList<Edge> allEdges = graph.getTrimmedEdges();
        allEdges.sort((a, b) -> {
            return (Double.valueOf(a.getWeight()).compareTo(Double.valueOf(b.getWeight())));
        });
    */

}