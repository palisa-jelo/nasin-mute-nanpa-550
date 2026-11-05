package Structures;

public class Node {
    private int value;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Node[] reverse(Node[] nodes) {
        Node[] output = new Node[nodes.length];
        for(int i = 0; i < nodes.length; i++) {
            output[nodes.length - i] = nodes[i];
        }
        return output;
    }
}
