import Structures.*;
import Structures.UndirectedGraph.Connection;

import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class UndirectedGraphAlg {
    public static void main(String[] args) {

        UndirectedGraphAlg alg = new UndirectedGraphAlg();

        UndirectedGraph testGraph = new UndirectedGraph("[[[2, 1], [3, 3]], [[1, 1], [3, 4], [4, 2]], [[1, 3], [2, 4], [4, 5]], [[2, 2], [3, 5]]]");
        printNodes(SearchAlgs.BFS(testGraph, 1));
        System.out.println(testGraph + "\n\n");

        
        int i = 1;
        /*
        for(Edge edge : alg.Prim(testGraph)) {
            System.out.println(i + ":  " + edge);
            i++;
        }
        */
        
        //testGraph.getTrimmedEdges();

        UndirectedGraph graphOne = new UndirectedGraph("[[[2, 459], [4, 46], [7, 144], [8, 420], [11, 589], [14, 185], [16, 127], [23, 758], [25, 158], [26, 569]], [[1, 459], [3, 48], [4, 496], [5, 543], [6, 753], [10, 183], [11, 339], [16, 573], [18, 595], [26, 445], [29, 278], [30, 887]], [[2, 48], [4, 643], [5, 399], [11, 317], [19, 402], [20, 632], [21, 759], [26, 748], [27, 234], [30, 507]], [[3, 643], [5, 885], [1, 46], [2, 496], [7, 308], [9, 779], [10, 531], [14, 468], [20, 511], [29, 33], [30, 409]], [[4, 885], [6, 791], [2, 543], [3, 399], [7, 321], [12, 401], [14, 889], [19, 328], [21, 764], [22, 11], [24, 28], [27, 540], [29, 865], [30, 213]], [[5, 791], [7, 654], [2, 753], [8, 834], [10, 323], [17, 489], [22, 801], [26, 843]], [[6, 654], [8, 279], [1, 144], [4, 308], [5, 321], [11, 559], [12, 446], [14, 106], [15, 642], [21, 248], [23, 702], [24, 661], [30, 119]], [[7, 279], [9, 52], [1, 420], [6, 834], [13, 166], [16, 684], [19, 246], [23, 683], [26, 847], [29, 551]], [[8, 52], [10, 533], [4, 779], [11, 741], [12, 782], [13, 712], [15, 772], [18, 770], [19, 47], [20, 793], [21, 284], [28, 469]], [[9, 533], [11, 126], [2, 183], [4, 531], [6, 323], [12, 560], [14, 594], [16, 303], [18, 746], [21, 274], [23, 84], [28, 230], [30, 832]], [[10, 126], [12, 156], [1, 589], [2, 339], [3, 317], [7, 559], [9, 741], [13, 627], [28, 118]], [[11, 156], [13, 458], [5, 401], [7, 446], [9, 782], [10, 560], [22, 350], [28, 650], [29, 151]], [[12, 458], [14, 839], [8, 166], [9, 712], [11, 627], [16, 390], [22, 75], [27, 417], [28, 41], [30, 655]], [[13, 839], [15, 434], [1, 185], [4, 468], [5, 889], [7, 106], [10, 594], [16, 617], [20, 802], [21, 603], [24, 501], [26, 591], [29, 299], [30, 689]], [[14, 434], [16, 406], [7, 642], [9, 772], [26, 349], [27, 347], [29, 377]], [[15, 406], [17, 848], [1, 127], [2, 573], [8, 684], [10, 303], [13, 390], [14, 617], [18, 700], [22, 667], [23, 827]], [[16, 848], [18, 96], [6, 489], [20, 275], [21, 600]], [[17, 96], [19, 792], [2, 595], [9, 770], [10, 746], [16, 700], [24, 735], [25, 171], [29, 174]], [[18, 792], [20, 675], [3, 402], [5, 328], [8, 246], [9, 47], [21, 137], [24, 87]], [[19, 675], [21, 717], [3, 632], [4, 511], [9, 793], [14, 802], [17, 275], [26, 42], [27, 67]], [[20, 717], [22, 200], [3, 759], [5, 764], [7, 248], [9, 284], [10, 274], [14, 603], [17, 600], [19, 137], [25, 241]], [[21, 200], [23, 636], [5, 11], [6, 801], [12, 350], [13, 75], [16, 667], [27, 546]], [[22, 636], [24, 586], [1, 758], [7, 702], [8, 683], [10, 84], [16, 827], [26, 740], [27, 548], [30, 705]], [[23, 586], [25, 692], [5, 28], [7, 661], [14, 501], [18, 735], [19, 87], [27, 561]], [[24, 692], [26, 295], [1, 158], [18, 171], [21, 241], [27, 886]], [[25, 295], [27, 611], [1, 569], [2, 445], [3, 748], [6, 843], [8, 847], [14, 591], [15, 349], [20, 42], [23, 740]], [[26, 611], [28, 547], [3, 234], [5, 540], [13, 417], [15, 347], [20, 67], [22, 546], [23, 548], [24, 561], [25, 886]], [[27, 547], [29, 557], [9, 469], [10, 230], [11, 118], [12, 650], [13, 41], [30, 99]], [[28, 557], [30, 386], [2, 278], [4, 33], [5, 865], [8, 551], [12, 151], [14, 299], [15, 377], [18, 174]], [[29, 386], [2, 887], [3, 507], [4, 409], [5, 213], [7, 119], [10, 832], [13, 655], [14, 689], [23, 705], [28, 99]]]");
        i = 1;
        for(Edge edge : alg.Prim(graphOne)) {
            System.out.println(i + ":  " + edge);
            i++;
        }
    }

    public static void printNodes(int[] nodes) {
        String output = "[" + nodes[0];
        for(int i = 1; i < nodes.length; i++) {
            output += ", " + (nodes[i] < 0 ? "inf" : nodes[i]);
        }
        output += "]";
        System.out.println(output);
    }

    public Edge[] Prim(UndirectedGraph graph) {
        BitSet visited = new BitSet(graph.getN());
        visited.clear();
        visited.set(0);
        Edge[] mst = new Edge[graph.getN() - 1];
        PriorityQueue<Edge> Q = new EdgePriorityQueue(graph.getN());

        Node recentNode = graph.getNode(1);
        for(int i = 0; i < graph.getN() - 1; i++) {

            //Making connection public here may not be the best design choice
            for(Connection connection : graph.getAdjacentConnections(recentNode)) {
                if(!visited.get(connection.getDestination().getValue() - 1)) {
                    Q.add(new Edge(recentNode, connection.getDestination(), connection.getWeight()));
                    //System.out.println(Q);
                }
            }
            while(Q.peek() != null && visited.get(Q.peek().getDestination().getValue() - 1)) {
                Q.poll();
            } 

            mst[i] = Q.poll();
            recentNode = mst[i].getDestination();
            visited.set(recentNode.getValue() - 1);
        }

        return mst;
    }

    class EdgePriorityQueue extends PriorityQueue<Edge> {
        // For each Node, keep track of the edge in the edges[] queue 
        HashMap<Node, PQEntry> minDistances;
        PQEntry[] edges;
        int size;

        public EdgePriorityQueue(int n) {
            minDistances = new HashMap<>();
            this.edges = new PQEntry[n+1];
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
            if(i == 0 || parent < 0) {
                return;
            }
            while(edges[i].getWeight() < edges[parent].getWeight()) {
                swap(edges[i], edges[parent]);
                i = parent;
                parent = getParent(i);
            }
        }

        private void bubbleDown(int i) {
            int left = getLeft(i);
            int right = getRight(i);
            int min;
            if(left < edges.length && edges[left] != null) {
                if(edges[right] == null || edges[left].getWeight() < edges[right].getWeight()) {
                    min = left;
                } else {
                    min = right;
                }
                if(edges[i].getWeight() > edges[min].getWeight()) {
                    swap(edges[i], edges[min]);
                    bubbleDown(min);
                }
            }
        }

        @Override 
        public boolean add(Edge edge) {
            // See if we should just override an existing entry
            if(minDistances.containsKey(edge.getDestination())) {
                // Only replace if it's a shorter path
                PQEntry current = minDistances.get(edge.getDestination());
                if(current.getWeight() > edge.getWeight()) {
                    PQEntry newest = new PQEntry(edge, current.queueLocation);
                    minDistances.put(edge.getDestination(), newest);
                    edges[current.queueLocation] = newest;
                    bubbleUp(current.queueLocation);
                    bubbleDown(current.queueLocation);
                }
            } else {
                // Insert new entry
                // System.out.println("\n\n" + this);
                edges[size] = new PQEntry(edge, size);
                minDistances.put(edge.getDestination(), edges[size]);
                try {
                    bubbleUp(size);
                    size++;
                } catch (RuntimeException e) {
                    System.out.println(e);
                    return false;
                }
            }
            return true;
        }

        @Override 
        public boolean offer(Edge edge) {
            return this.add(edge);
        }

        @Override
        public Edge peek() {
            return edges[0] == null ? null : edges[0].edge;
        }

        public Edge poll() {
            Edge output = edges[0].edge;

            minDistances.remove(edges[0].getDestination());
            size--;
            swap(edges[0], edges[size]);
            edges[size] = null;
            bubbleDown(0);

            return output;
        }

        @Override
        public String toString() {
            String output = "";
            for(PQEntry entry : edges) {
                if(entry != null) {
                    output += (entry.edge + " | ");
                }
            }
            return output;
        }

        void swap(PQEntry a, PQEntry b) {
            PQEntry temp = b;
            edges[b.queueLocation] = a;
            edges[a.queueLocation] = temp;
            int tempInt = b.queueLocation;
            b.queueLocation = a.queueLocation;
            a.queueLocation = tempInt;
        }

        public class PQEntry {
            Edge edge;
            int queueLocation;
            
            PQEntry(Edge edge, int loc) {
                this.edge = edge;
                this.queueLocation = loc;
            }

            double getWeight() {
                return edge.getWeight();
            }

            Node getSource() {
                return edge.getSource();
            }

            Node getDestination() {
                return edge.getDestination();
            }
        }
    }



    /*
    ArrayList<Edge> allEdges = graph.getTrimmedEdges();
        allEdges.sort((a, b) -> {
            return (Double.valueOf(a.getWeight()).compareTo(Double.valueOf(b.getWeight())));
        });
    */

}