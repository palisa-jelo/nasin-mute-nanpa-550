package Structures;

public class Edge {
    private Node source;
    private Node destination;
    private double weight;

    public Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
        this.weight = -1.0;
    }

    public Edge(Node source, Node destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public boolean isWeighted() {
        return weight != -1.0;
    }

    public double getWeight() {
        return weight;
    }

    @Override 
    public String toString() {
        if(weight != -1.0) {
            return "((" + source.getValue() + ", " + destination.getValue() + "), " + weight + ")";
        } else {
            return "(" + source.getValue() + ", " + destination.getValue() + ")";
        }
    }
}
