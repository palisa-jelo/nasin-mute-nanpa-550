import Structures.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Iterator;

public class BasicSearchAlgs {

    public static void main(String[] args) {
		System.out.println("Homework 1 Part 1:\n");
		
        int[] arr1 = {1, 3, 4, 5, 8, 9};
		int[] result1 = twoSum(arr1, 8);
        System.out.println(result1[0] + ", " + result1[1]);

		int[] arr2 = {1, 4, 10, 13, 16, 19, 22, 25, 28, 34, 37, 40, 43, 46, 49, 55, 58, 61, 64, 67, 70, 73, 76, 79, 82, 85, 88, 91, 94, 97};
		int[] result2 = twoSum(arr2, 149);
		System.out.println(result2[0] + ", " + result2[1]);

		int[] arr3 = {4, 13, 34, 40, 43, 46, 97, 145, 154, 157, 181, 184, 205, 286, 292, 322, 331, 337, 340, 352, 355, 358, 391, 409, 436, 445, 454, 466, 487, 511, 529, 583, 586, 598, 604, 640, 649, 664, 673, 685, 691, 706, 724, 748, 760, 766, 769, 775, 778, 811, 832, 850, 853, 874, 901, 907, 934, 967, 985, 997};
		int[] result3 = twoSum(arr3, 1111);
		System.out.println(result3 == null ? "It's null!" : result3[0] + ", " + result3[1]);

		int[] binaryTest = {1, 3, 4, 5, 8, 9};
		System.out.print("Searching for 8: ");
		printBinarySearch(binarySearch(binaryTest, 8));

		int[] arr4 = {1, 3, 4, 5, 8, 9};
		System.out.print("Searching for 2: ");
		printBinarySearch(binarySearch(arr4, 2));

		int[] arr5 = {1, 2, 3, 4, 7, 9, 13, 14, 15, 16, 18, 19, 23, 27, 28, 29, 30, 33, 35, 36, 41, 42, 44, 49, 50, 55, 56, 57, 58, 60, 61, 62, 63, 64, 70, 71, 72, 73, 75, 76, 78, 79, 84, 86, 87, 89, 90, 93, 94, 98};
		System.out.print("Searching for 33: ");
		printBinarySearch(binarySearch(arr5, 33));

		int[] arr6 = {1, 2, 3, 4, 7, 8, 9, 12, 13, 14, 15, 16, 18, 19, 22, 23, 27, 28, 29, 30, 33, 35, 36, 41, 42, 43, 44, 47, 49, 50, 55, 56, 57, 58, 60, 61, 62, 63, 64, 68, 69, 70, 71, 72, 73, 75, 76, 78, 79, 83, 84, 86, 87, 88, 89, 90, 93, 94, 96, 98};
		System.out.print("Searching for 33: ");
		printBinarySearch(binarySearch(arr6, 33));

		System.out.print("\n\n------\n\nHomework Two:\n");

		/*
		Random random = new Random();

        
        DirectedGraph bigGraph = new DirectedGraph(100);
        for (int i = 0; i < 500; i++) {
            int node1 = random.nextInt(100) + 1;
            int node2 = random.nextInt(100) + 1;

            bigGraph.createConnection(node1, node2);
        }
        bigGraph.printEdges();
        System.out.println(bigGraph);
        */

		DirectedGraph testBFSGraph = new DirectedGraph("[[2, 4], [], [1, 2], [3], []]");
        printNodes(BFS(testBFSGraph, 1));
        System.out.println(testBFSGraph + "\n");
        
        DirectedGraph graphOne = new DirectedGraph("[[3], [1], [4], [1, 2]]");
        printNodes(BFS(graphOne, 1));
        printNodes(BFS(graphOne, 1), 1, 3);
        
        DirectedGraph graphTwo = new DirectedGraph("[[42], [27, 32, 38, 43], [], [14, 25, 38, 42], [12, 23, 38], [21, 30, 39, 46, 47], [1, 12, 47], [23], [30, 45, 50], [8, 17, 41], [4, 6, 32, 37], [], [12, 26], [4, 25], [12, 35, 45], [24, 29], [8, 12, 21, 30], [21, 43, 50], [8, 11, 40, 50], [4, 9, 15, 21, 49], [9, 12, 27], [24], [], [11, 36], [3, 22, 32], [7, 19, 27, 47, 50], [5], [9, 31], [11, 32, 40], [2, 5, 27], [6, 14, 33], [2, 11, 13, 18, 47], [5], [2, 6, 9, 31], [9, 18], [18], [31, 35, 49], [49], [12, 30], [3, 35, 37, 47, 48], [1, 13, 19, 39], [21], [2, 37], [5, 12, 16, 18], [12], [13], [4, 6, 27, 36, 37, 44], [3], [5, 15, 25], [32, 37]]");
        printNodes(BFS(graphTwo, 1), 1, 10);

        DirectedGraph graphThree = new DirectedGraph("[[42], [17, 22, 28, 33], [43, 54], [8, 12, 31, 42, 57], [30, 39, 48, 55, 56, 60], [11, 46], [12], [9, 24, 29, 37, 45], [10, 23, 25, 50, 55], [], [10, 24, 52], [13, 50], [12, 23, 51, 56], [26, 30, 38, 47], [28, 50, 57], [5, 8, 37, 47, 51, 56], [2, 7, 36, 46, 49], [3, 50], [], [17, 42, 59], [18, 28, 53], [5, 12, 33, 36, 41], [35, 56], [27, 47, 55], [7, 10, 33], [1, 9, 28, 47, 56, 58], [3, 32, 40], [26, 31, 34, 56], [23, 33], [22], [25, 29, 43], [33, 46], [4, 26, 59], [1, 10, 11, 14, 26, 32, 53], [24, 55], [30, 48, 55, 59], [1, 45], [35], [16, 18, 40, 49, 50, 57], [5, 57], [7, 17], [14, 19, 47, 51], [29, 44, 58], [45], [9, 23, 56], [27, 34, 47], [7, 25], [1, 2, 5, 7, 51], [25, 43, 54], [1, 32], [4, 41], [20, 41], [33], [3, 9, 22], [3, 21], [48], [17, 19, 20, 42, 45], [29, 33, 53, 54, 57], [11, 27, 48, 53, 58], [4, 25, 26, 34, 57]]");
        printNodes(BFS(graphThree, 1), 1, 10);

        DirectedGraph testDFSGraph = new DirectedGraph("[[2, 4], [], [1, 2], [3], []]");
        printPrePost(DFS(testDFSGraph));

        DirectedGraph graphFour = new DirectedGraph("[[3], [1], [4], [1, 2]]");
        printPrePost(DFS(graphFour));

        DirectedGraph graphFive = new DirectedGraph("[[19, 20, 24], [3, 9, 37], [2, 9, 14], [30], [4, 16, 21, 29, 33], [9, 13, 19, 24, 29, 39], [18, 20, 32, 39], [1, 2, 11, 17, 18, 19, 23, 33, 37], [3, 26, 29, 34, 36, 38], [1, 3, 15], [12, 13, 31, 32], [7, 11, 13, 20, 21, 28, 29], [14, 27, 29], [15, 20, 33], [3, 27], [15, 20, 29, 31], [18, 37, 38], [5, 10, 20, 36, 40], [18, 21, 29], [17, 23, 28], [8, 12, 14, 15, 16, 18, 19, 25, 30, 34, 40], [24, 27, 31, 35], [17, 25, 33, 36], [1, 2, 4, 5, 25, 35, 39], [4, 10, 14, 15, 34, 39], [4, 7, 14, 16, 17, 32], [14, 39], [19, 26], [1, 27], [7, 8, 17, 32, 33], [4, 9, 22, 28, 30, 38], [10, 23, 25, 30, 36, 37], [2, 3, 9, 23, 26, 31], [], [6, 27, 37], [1, 15, 18, 31, 32, 39], [7, 10, 15, 19, 22, 27, 29, 31], [5, 11, 27, 33], [2, 8, 20, 24, 40], [9, 11, 14, 16, 36]]");
        printPrePost(DFS(graphFive), 1, 10);
        System.out.println("The number of back-edges is " + getBackEdges(graphFive).size());
    }

    public static int[] twoSum(int[] arr, int target) {
		int i = 0;
		int j = arr.length - 1;
		int[] result = null;
		while(i < j) {
			if(arr[i] + arr[j] == target) {
				result = new int[2];
				result[0] = i + 1;
				result[1] = j + 1;
				return result;
			} else if(arr[i] + arr[j] > target) {
				j--;
			} else if(arr[i] + arr[j] < target) {
				i++;
			} else {
				throw new RuntimeException("Something has gone very wrong!!");
			}
		}
		return result;
	}

    public static int[] binarySearch(int[] arr, int target) {
		int[] result = new int[(int)(Math.ceil(Math.log(arr.length) / Math.log(2)))];
		int i = 0;
		int j = arr.length - 1;
		int index = 0;
		int m;
		while(i <= j) {
			m = (i+j)/2;
			result[index] = m + 1;
			index++;
			if(arr[m] == target) {
				i+=j;
				//Exit the loop
			} else if(arr[m] < target) {
				i = m + 1;
			} else if(arr[m] > target) {
				j = m - 1;
			}
		}
		return result;
	}

    // Unweighted
    public static int[] BFS(Graph graph, int s) {
        Queue<Node> Q = new LinkedList<>();

        int[] distances = new int[graph.getN()];
        for(int i = 0; i < distances.length; i++) {
            distances[i] = -1;
        }
        distances[s - 1] = 0;
        Q.offer(graph.getNode(s));
        while(Q.size() > 0) {
            Node u = Q.poll();
            for(Node node : graph.getAdjacencyList(u)) {
                if(distances[node.getValue() - 1] < 0) {
                    distances[node.getValue() - 1] = distances[u.getValue() - 1] + 1;
                    Q.offer(node);
                }
            }
        }
        return distances;
    }

	public static int[] BFS(DirectedGraph graph, int s) {
        Queue<Node> Q = new LinkedList<>();

        int[] distances = new int[graph.getN()];
        for(int i = 0; i < distances.length; i++) {
            distances[i] = -1;
        }
        distances[s - 1] = 0;
        Q.offer(graph.getNode(s));
        while(Q.size() > 0) {
            Node u = Q.poll();
            for(Node node : graph.getAdjacencyList(u)) {
                if(distances[node.getValue() - 1] < 0) {
                    distances[node.getValue() - 1] = distances[u.getValue() - 1] + 1;
                    Q.offer(node);
                }
            }
        }
        return distances;
    }

    public static int[][] DFS(DirectedGraph graph) {
        int[] pre = new int[graph.getN()];
        int[] post = new int[graph.getN()];
        int[][] output = new int[2][graph.getN()];
        output[0] = pre;
        output[1] = post;

        for(int i = 0; i < pre.length; i++) {
            pre[i] = -1;
            post[i] = -1;
        }
        int[] t = new int[]{1};
        Iterator<Node> allNodes = graph.iterator();
        while(allNodes.hasNext()) {
            Node next = allNodes.next();
            if(pre[next.getValue() - 1] < 0) {
                Explore(graph, next, pre, post, t);
            }
        }

        return output;
    }

    private static void Explore(DirectedGraph graph, Node node, int[] pre, int[] post, int[] t) {
        pre[node.getValue() - 1] = t[0];
        t[0]++;
        for(Node connectedNodes : graph.getAdjacencyList(node)) {
            if(pre[connectedNodes.getValue() - 1] < 0) {
                Explore(graph, connectedNodes, pre, post, t);
            }
        }
        post[node.getValue() - 1] = t[0];
        t[0]++;
    }

    public static ArrayList<Edge> getBackEdges(DirectedGraph graph) {
        ArrayList<Edge> backEdges = new ArrayList<>();
        int[][] prePost = BasicSearchAlgs.DFS(graph);
        int[] pre = prePost[0];
        int[] post = prePost[1];
        Set<Edge> edges = graph.getEdges();

        for(Edge edge : edges) {
            int source = edge.getSource().getValue() - 1;
            int destination = edge.getDestination().getValue() - 1;
            if(pre[destination] < pre[source] && pre[source] < post[source]
                    && post[source] < post[destination]) {
                backEdges.add(edge);
            }
        }

        return backEdges;
    }

	public static void printBinarySearch(int[] arr) {
		int i = 0;
		while(i < arr.length && arr[i] != 0) {
			if(i != 0) {
				System.out.print(", ");
			}
			System.out.print(arr[i]);
			i++;
		}
		System.out.println();
	}

    public static void printNodes(int[] nodes) {
        String output = "[" + nodes[0];
        for(int i = 1; i < nodes.length; i++) {
            output += ", " + (nodes[i] < 0 ? "inf" : nodes[i]);
        }
        output += "]";
        System.out.println(output);
    }

    //Splicing
    public static void printNodes(int[] nodes, int a, int b) {
        if(a < 0 || b < a || b > nodes.length) {
            throw new IllegalArgumentException("The arguments to spice these nodes are invalid. ");
        }
        int[] newNodes = new int[b - a + 1];
        for(int i = a - 1; i < b; i++) {
            newNodes[i] = nodes[i];
        }
        printNodes(newNodes);
    }

    public static void printPrePost(int[][] nodes, int a, int b) {
        System.out.print("\nPre: ");
        printNodes(nodes[0], a, b);
        System.out.print("Post: ");
        printNodes(nodes[1], a, b);
    }

    public static void printPrePost(int[][] nodes) {
        System.out.print("Pre: ");
        printNodes(nodes[0]);
        System.out.print("Post: ");
        printNodes(nodes[1]);
    }
}
