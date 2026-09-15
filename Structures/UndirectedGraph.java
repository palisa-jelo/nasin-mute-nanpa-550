package Structures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class UndirectedGraph implements Graph {
    private ArrayList<Set<Connection>> adjacencyLists;
    private ArrayList<Node> nodes;

    public UndirectedGraph(int size) {
        adjacencyLists = new ArrayList<>();
        nodes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            adjacencyLists.add(new HashSet<>());
        }
        for(int i = 0; i < size; i++) {
            nodes.add(null);
        }
    }

    public UndirectedGraph(String graph) {
        // We get a string of this sort of format:
        // [[[2, 1], [3, 3]], [[1, 1], [3, 4], [4, 2]], [[1, 3], [2, 4], [4, 5]], [[2, 2], [3, 5]]]

        // Broken up, we have pairs of the connected node and the weight of that connection for each node. See-
        // Node 1: [2, 1], [3, 3]
        // Node 2: [1, 2], [3, 4], [4, 2]
        // Node 3: [1, 3], [2, 4], [4, 5]
        // Node 4: [2, 2], [3, 5]

        adjacencyLists = new ArrayList<>();
        nodes = new ArrayList<>();

        // Chop off the ends
        String lists = graph.substring(1, graph.length() - 1);
        // Then separate into individual arrays [[1, 2], [3, 4], [4, 2]]
        String[] unfiltered = lists.split("\\]\\], \\[\\[");
        for(int i = 0; i < unfiltered.length; i++) {
            nodes.add(new Node(i+1));
            adjacencyLists.add(new LinkedHashSet<>());
        }

        ArrayList<Edge> allEdges = new ArrayList<>();
        int currentNode = 1;
        for(String adjacencyList : unfiltered) {
            // Our arrays are messy and formatted weirdly, like "[[2, 1], [3,3" or "1,3], [2,4" so we need to clean them up a bit
            adjacencyList = adjacencyList.replace("[[", "[");
            adjacencyList = adjacencyList.replace("]]", "]");
            // And then chop off the ends again
            if(adjacencyList.charAt(0) == '[') {
                adjacencyList = adjacencyList.substring(1, adjacencyList.length());
            }
            if(adjacencyList.charAt(adjacencyList.length() - 1) == ']') {
                adjacencyList = adjacencyList.substring(0, adjacencyList.length() - 1);
            }
            // Isolate the pairs in 1, 2], [3, 4], [4, 2
            String[] weightedPairs = adjacencyList.split("\\], \\["); 
            
            for(String pair : weightedPairs) {
                // [1, 2]
                pair = pair.replace("[", "");
                pair = pair.replace("]", "");
                if(pair.length() < 1) {
                    break;
                }
                int nodeValue = Integer.parseInt(pair.substring(0, pair.indexOf(',')));
                int nodeWeight = Integer.parseInt(pair.substring(pair.indexOf(',') + 2, pair.length()));
                allEdges.add(new Edge(getNode(currentNode), getNode(nodeValue), nodeWeight));

                // We will assume that the string represents both directions, so no need for this
                // allEdges.add(new Edge(getNode(nodeValue), getNode(currentNode), nodeWeight));
            }

            currentNode++;
        }

        for(Edge edge : allEdges) {
            Node fromNode = getNode(edge.getSource());
            Connection connection = new Connection(edge.getDestination(), edge.getWeight());
            adjacencyLists.get(fromNode.getValue() - 1).add(connection);
        }
    }

    public void connectNodes(Node a, Node b, double weight) {
        connectNodes(a.getValue(), b.getValue(), weight);
    }

    public void connectNodes(int a, int b, double weight) {
        a--;
        b--;
        if(nodes.get(a) == null || nodes.get(b) == null) {
            throw new IllegalArgumentException("Cannot connect nonexistent nodes!");
        } else {
            // We need to check if there's already a connection that needs to be removed for re-sizing
            removeEdge(a, b);
            adjacencyLists.get(a).add(new Connection(nodes.get(b), weight));
            adjacencyLists.get(b).add(new Connection(nodes.get(a), weight));
        }
    }

    public void createConnection(int a, int b, double weight) {
        if(nodes.get(a - 1) == null) {
            nodes.set(a - 1, new Node(a));
        }
        if(nodes.get(b - 1) == null) {
            nodes.set(b - 1, new Node(b));
        }
        connectNodes(a, b, weight);
    }

    private void removeEdge(int a, int b) {
        for(Connection connection : adjacencyLists.get(a)) {
            if(connection.node.equals(nodes.get(b))) {
                adjacencyLists.get(a).remove(connection);
                break;
            }
        }
        for(Connection connection : adjacencyLists.get(b)) {
            if(connection.node.equals(nodes.get(a))) {
                adjacencyLists.get(b).remove(connection);
                break;
            }
        }
    }

    @Override 
    public Set<Edge> getEdges() {
        Set<Edge> edges = new LinkedHashSet<>();
        for(int i = 0; i < adjacencyLists.size(); i++) {
            Node currentNode = nodes.get(i);
            Set<Connection> connections = adjacencyLists.get(i);
            for(Connection connection : connections) {
                edges.add(new Edge(currentNode, connection.node, connection.weight));
            }
        }
        return edges;
    }

    public ArrayList<Edge> getTrimmedEdges() {
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Set<Connection>> lists = new ArrayList<>(adjacencyLists);
        for(int i = 0; i < lists.size(); i++) {
            Node currentNode = nodes.get(i);
            Set<Connection> connections = lists.get(i);
            for(Connection connection : connections) {
                edges.add(new Edge(currentNode, connection.node, connection.weight));
                //Remove the copy in other list
                Set<Connection> reflectedList = lists.get(connection.node.getValue() - 1);
                for(Connection conn : reflectedList) {
                    if(conn.node.equals(currentNode)) {
                        reflectedList.remove(conn);
                        break;
                    }
                }
            }
        }
        return edges;
    }

    @Override
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }

    private void clean(Node oldNode) {
        //NOT IMPLEMENTED YET
    }

    @Override
    public void addNode(Node node) {
        throw new RuntimeException("Not implemented yet");
        // if(nodes.get(node.getValue()) != null) {
        //     clean(nodes.get(node.getValue()));
        // }
        // nodes.set(node.getValue(), node);
    }

    @Override
    public Node getNode(int i) {
        return this.nodes.get(i - 1);
    }

    @Override
    public Node getNode(Node node) {
        return getNode(node.getValue());
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public Set<Node> getAdjacencyList(int i) {
        LinkedHashSet<Node> output = new LinkedHashSet<>();
        for(Connection connection : adjacencyLists.get(i - 1)) {
            output.add(connection.node);
        }
        return output;
    }

    @Override 
    public Set<Node> getAdjacencyList(Node node) {
        return getAdjacencyList(node.getValue());
    }

    public double[] getAdjacentWeights(Node node) {
        int index = node.getValue() - 1;
        Set<Connection> list = adjacencyLists.get(index);
        Iterator<Connection> it = list.iterator();
        double[] output = new double[list.size()];
        for(int i = 0; i < list.size(); i++) {
            output[i] = it.next().weight;
        }
        return output;
    }

    public Set<Connection> getAdjacentConnections(Node node) {
        return adjacencyLists.get(node.getValue() - 1);
    }

    @Override 
    public int getN() {
        return nodes.size();
    }

    @Override 
    public int getM() {
        return getEdges().size() / 2;
    }

    @Override 
    public String toString() {
        String output = "[";
        for(int i = 0; i < nodes.size(); i++) {
            output += "\n " + (i + 1) + " ".repeat((int)Math.log10(nodes.size()) - (int)Math.log10(i+1) + 1) + ":";
            Set<Connection> connectedNodes = adjacencyLists.get(i);
            for(Connection connection : connectedNodes) {
                Node node = connection.node;
                output += " ".repeat((int)Math.log10(nodes.size()) - (int)Math.log10(node.getValue()) + 1) + node.getValue();
            }
        }
        output += "\n]";
        return output;
    }

    @Override
    public void printEdges() {
        System.out.print("{ ");
        for(Edge edge : this.getEdges()) {
            System.out.print(edge + ", ");
        }
        System.out.println(" }");
    }

    public record Connection (Node node, double weight) {
        public Connection(Node node, double weight) {
            if(weight <= 0) {
                throw new IllegalArgumentException("The weight of a connection must be greater than 0");
            }
            this.node = node;
            this.weight = weight;
        }

        public Node getDestination() {
            return this.node;
        }

        public double getWeight() {
            return this.weight;
        }
    }
    
}
