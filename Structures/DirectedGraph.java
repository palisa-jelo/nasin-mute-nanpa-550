package Structures;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class DirectedGraph implements Iterable<Node> {
    private ArrayList<Set<Node>> adjacencyLists;
    private ArrayList<Node> nodes;

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
        // We get a string of this sort of format:
        // [[2, 4, 5], [], [1, 2], [3], []]
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
            //2, 4, 5
            Set<Node> connectedNodes = new LinkedHashSet<>();
            for(String node : adjacencyList.split(", ")) {
                //2
                if(node.length() > 0) {
                    connectedNodes.add(getNode(Integer.parseInt(node)));
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
            Set<Node> connectedNodes = adjacencyLists.get(i);
            for(Node node : connectedNodes) {
                edges.add(new Edge(currentNode, node));
            }
        }
        return edges;
    }

    private void clean(Node oldNode) {
        nodes.set(oldNode.getValue(), null);
        adjacencyLists.set(oldNode.getValue(), new HashSet<>());
        for(Set<Node> connectedNodes : adjacencyLists) {
            for(Node node : connectedNodes) {
                if(node == oldNode) {
                    connectedNodes.remove(node);
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

    public Set<Node> getAdjacencyList(int i) {
        return adjacencyLists.get(i - 1);
    }

    public Set<Node> getAdjacencyList(Node node) {
        return getAdjacencyList(node.getValue());
    }

    public int getN() {
        return nodes.size();
    }

    public int getM() {
        return getEdges().size();
    }

    public void connectNodes(Node a, Node b) {
        connectNodes(a.getValue(), b.getValue());
    }

    public void connectNodes(int a, int b) {
        a--;
        b--;
        if(nodes.get(a) == null || nodes.get(b) == null) {
            throw new IllegalArgumentException("Cannot connect nonexistent nodes!");
        } else {
            adjacencyLists.get(a).add(nodes.get(b));
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
            Set<Node> connectedNodes = adjacencyLists.get(i);
            for(Node node : connectedNodes) {
                output += " ".repeat((int)Math.log10(nodes.size()) - (int)Math.log10(node.getValue()) + 1) + node.getValue();
            }
        }
        output += "\n]";
        return output;
    }

    public void printEdges() {
        System.out.print("{ ");
        for(Edge edge : this.getEdges()) {
            System.out.print(edge + ", ");
        }
        System.out.println(" }");
    }
}
