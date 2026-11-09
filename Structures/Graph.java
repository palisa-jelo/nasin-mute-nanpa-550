package Structures;

import java.util.Set;

public interface Graph extends Iterable<Node> {

    public Set<Edge> getEdges();
    public Edge getEdge(int u, int v);
    public Edge getEdge(Node u, Node v);
    public boolean removeEdge(int u, int v);
    public boolean removeEdge(Node u, Node v);
    public boolean removeEdge(Edge edge);
    public void addNode(Node node);
    public Node getNode(int i);
    public Node getNode(Node node);
    public Set<Node> getAdjacencyList(Node node);
    public int getN();
    public int getM();
    public void printEdges();

}
