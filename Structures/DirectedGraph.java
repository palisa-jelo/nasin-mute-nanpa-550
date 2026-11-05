package Structures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class DirectedGraph implements Graph {
    private ArrayList<Set<Connection>> adjacencyLists;
    private ArrayList<Node> nodes;
    private boolean isWeighted;

    public DirectedGraph(int size) {
        adjacencyLists = new ArrayList<>();
        nodes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            adjacencyLists.add(new HashSet<>());
        }
        for(int i = 0; i < size; i++) {
            nodes.add(null);
        }
    }

    public DirectedGraph(String graph) {
        if (graph.indexOf("[[", 1) != -1) {
            // We get a string of this sort of format
            // [[[2, 3], [4, 2], [5, 1]], [], [[1, -1], [2, 4]], [[3, -2]], []]
            isWeighted = true;
            adjacencyLists = new ArrayList<>();
            nodes = new ArrayList<>();
            
            String lists = graph.substring(1, graph.length() - 1);
            
            // [2, 3], [4, 2], [5, 1]], [], [[1, -1], [2, 4]], [[3, -2]], [
            String[] unfiltered = lists.split("(?<=\\]\\]|\\[\\]), (?=\\[\\[|\\[\\])");
            for(int i = 0; i < unfiltered.length; i++) {
                nodes.add(new Node(i+1));
            }
            for(String adjacencyList : unfiltered) {
                // [2, 3], [4, 2], [5, 1]
                adjacencyList = adjacencyList.substring(1, adjacencyList.length() - 1);
                Set<Connection> connectedNodes = new LinkedHashSet<>();
                for(String connection : adjacencyList.split("\\], \\[")) {
                    connection = connection.replace("[", "").replace("]", "");
                    if(connection.length() > 0) {
                        String node = connection.split(", ")[0];
                        Double weight = Double.parseDouble(connection.split(", ")[1]);
                        connectedNodes.add(new Connection(getNode(Integer.parseInt(node)), weight));

                    }
                }
                adjacencyLists.add(connectedNodes);
            }

            return;
        }

        // We get a string of this sort of format:
        // [[2, 4, 5], [], [1, 2], [3], []]
        isWeighted = false;
        adjacencyLists = new ArrayList<>();
        nodes = new ArrayList<>();

        String lists = graph.substring(1, graph.length() - 1);
        // [2, 4, 5], [], [1, 2], [3], []
        String[] unfiltered = lists.split("\\], \\[");
        for(int i = 0; i < unfiltered.length; i++) {
            nodes.add(new Node(i+1));
        }
        for(String adjacencyList : unfiltered) {
            // [2, 4, 5]
            adjacencyList = adjacencyList.replace("[", "");
            adjacencyList = adjacencyList.replace("]", "");
            // 2, 4, 5
            Set<Connection> connectedNodes = new LinkedHashSet<>();
            for(String node : adjacencyList.split(", ")) {
                //2
                if(node.length() > 0) {
                    connectedNodes.add(new Connection(getNode(Integer.parseInt(node))));
                }
            }
            adjacencyLists.add(connectedNodes);
        }
    }

    @Override
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }

    public Set<Edge> getEdges() {
        Set<Edge> edges = new LinkedHashSet<>();
        for(int i = 0; i < nodes.size(); i++) {
            Node currentNode = nodes.get(i);
            Set<Connection> connectedNodes = adjacencyLists.get(i);
            for(Connection connection : connectedNodes) {
                edges.add(new Edge(currentNode, connection.node));
            }
        }
        return edges;
    }

    public Set<Edge> getWeightedEdges() {
        Set<Edge> edges = new LinkedHashSet<>();
        for(int i = 0; i < nodes.size(); i++) {
            Node currentNode = nodes.get(i);
            Set<Connection> connectedNodes = adjacencyLists.get(i);
            for(Connection connection : connectedNodes) {
                edges.add(new Edge(currentNode, connection.node, connection.weight));
            }
        }
        return edges;
    }

    private void clean(Node oldNode) {
        nodes.set(oldNode.getValue(), null);
        adjacencyLists.set(oldNode.getValue(), new HashSet<>());
        for(Set<Connection> connectedNodes : adjacencyLists) {
            for(Connection connection : connectedNodes) {
                if(connection.node == oldNode) {
                    connectedNodes.remove(connection);
                }
            }
        }
    }

    public void addNode(Node node) {
        if(nodes.get(node.getValue()) != null) {
            clean(nodes.get(node.getValue()));
        }
        nodes.set(node.getValue(), node);
    }

    public Node getNode(int i) {
        return nodes.get(i - 1);
    }

    public Node getNode(Node node) {
        return getNode(node.getValue());
    }

    public Set<Connection> getWeightedAdjacencyList(int i) {
        return adjacencyLists.get(i - 1);
    }

    public Set<Connection> getWeightedAdjacencyList(Node node) {
        return getWeightedAdjacencyList(node.getValue());
    }

    public Set<Node> getAdjacencyList(int i) {
        Set<Connection> list = getWeightedAdjacencyList(i);
        Set<Node> node_list = new LinkedHashSet<>();
        for(Connection connection : list) {
            node_list.add(connection.node);
        }
        return node_list;
    }

    public Set<Node> getAdjacencyList(Node node) {
        return getAdjacencyList(node.getValue());
    }

    @Override 
    public int getN() {
        return nodes.size();
    }

    public Node[] getNodesArray() {
        return nodes.toArray(new Node[getN()]);
    }

    @Override 
    public int getM() {
        return getEdges().size();
    }

    public double getWeight(int a, int b) {
        for(Connection connection : adjacencyLists.get(a - 1)) {
            if(connection.node.getValue() == b) {
                return connection.weight;
            }
        }
        return Double.MIN_VALUE;
    }

    public double getWeight(Node a, Node b) {
        return getWeight(a.getValue(), b.getValue());
    }

    public void connectNodes(Node a, Node b) {
        connectNodes(a.getValue(), b.getValue());
    }

    public void connectNodes(int a, int b, double weight) {
        a--;
        b--;
        if(nodes.get(a) == null || nodes.get(b) == null) {
            throw new IllegalArgumentException("Cannot connect nonexistent nodes!");
        } else {
            adjacencyLists.get(a).add(new Connection(nodes.get(b), weight));
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

    public void connectNodes(int a, int b) {
        a--;
        b--;
        if(nodes.get(a) == null || nodes.get(b) == null) {
            throw new IllegalArgumentException("Cannot connect nonexistent nodes!");
        } else {
            adjacencyLists.get(a).add(new Connection(nodes.get(b)));
        }
    }

    public void createConnection(int a, int b) {
        if(nodes.get(a - 1) == null) {
            nodes.set(a - 1, new Node(a));
        }
        if(nodes.get(b - 1) == null) {
            nodes.set(b - 1, new Node(b));
        }
        connectNodes(a, b);
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
                if (isWeighted) {
                    output += "," + connection.weight;
                }
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

    public static DirectedGraph reverse(DirectedGraph graph) {
        DirectedGraph reverse = new DirectedGraph(graph.getN());
        int i = 1;
        for(Set<Connection> adjacent : graph.adjacencyLists) {
            for(Connection connection : adjacent) {
                reverse.createConnection(connection.node.getValue(), i, connection.weight);
            }
            i++;
        }
        return reverse;
    }

    public record Connection (Node node, double weight) {

        public Connection (Node node) {
            this(node, Double.MIN_VALUE);
        }

        public Connection(Node node, double weight) {
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
