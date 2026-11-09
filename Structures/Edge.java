package Structures;

public class Edge {
    private final Node source;
    private final Node destination;
    private final double weight;

    public Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
        this.weight = Double.MIN_VALUE;
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
        return weight != Double.MIN_VALUE;
    }

    public double getWeight() {
        return weight;
    }

    public Edge reverse() {
        return new Edge(destination, source, weight);
    }

    @Override 
    public String toString() {
        if(weight != Double.MIN_VALUE) {
            return "((" + source.getValue() + ", " + destination.getValue() + "), " + weight + ")";
        } else {
            return "(" + source.getValue() + ", " + destination.getValue() + ")";
        }
    }
}
