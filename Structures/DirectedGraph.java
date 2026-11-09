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

    public DirectedGraph(Graph graph) {
        // Copy method for some kind of graph. Can be made much more efficient when copying a guaranteed Directed Graph
        this(graph.getN());
        for (Edge edge : graph.getEdges()) {
            createConnection(edge.getSource().getValue(), edge.getDestination().getValue(), edge.getWeight());
        }
    }

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
                edges.add(new Edge(currentNode, connection.node, connection.weight));
            }
        }
        return edges;
    }

    public Edge getEdge(int u, int v) {
        Set<Connection> connectedNodes = adjacencyLists.get(u - 1);
        for(Connection connection : connectedNodes) {
            if(connection.node.getValue() == v) {
                return new Edge(getNode(u), connection.node, connection.weight);
            }
        }
        return null;
    }

    public Edge getEdge(Node u, Node v) {
        return getEdge(u.getValue(), v.getValue());
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

    private ArrayList<Node> getNodes() {
        return nodes;
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

    public void editEdge(Edge edge, double change, boolean switchDirection) {
        int a = edge.getSource().getValue();
        int b = edge.getDestination().getValue();
        a--;
        b--;
        // Check if already present
        for(Connection connection : adjacencyLists.get(a)) {
            if(connection.node.getValue() == b + 1) {
                if(switchDirection) {
                    adjacencyLists.get(a).remove(connection);
                    adjacencyLists.get(b).add(new Connection(nodes.get(a), connection.weight + change));
                    return;
                }
                adjacencyLists.get(a).remove(connection);
                adjacencyLists.get(a).add(new Connection(nodes.get(b), connection.weight + change));
                return;
            }
        }
        // Not present
        if(switchDirection) {
            adjacencyLists.get(b).add(new Connection(nodes.get(a), edge.getWeight() + change));
            return;
        }
        adjacencyLists.get(a).add(new Connection(nodes.get(b), edge.getWeight() + change));
    }

    @Override
    public boolean removeEdge(int u, int v) {
        u--;
        v--;
        for(Connection connection : adjacencyLists.get(u)) {
            if(connection.node.getValue() == v + 1) {
                adjacencyLists.get(u).remove(connection);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeEdge(Node u, Node v) {
        return removeEdge(u.getValue(), v.getValue());
    }

    @Override
    public boolean removeEdge(Edge edge) {
        return removeEdge(edge.getSource().getValue(), edge.getDestination().getValue());
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
