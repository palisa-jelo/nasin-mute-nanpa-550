import java.util.Arrays;

import Structures.*;

public class SortingAlgs {
	public static void main(String[] args) {
		System.out.println("Homework 1 Part 1:\n");

		int[] unsorted1 = {18, 73, 98, 9, 33};
		int[] sorted1 = selectionSort(unsorted1,2);
		splicePrint(sorted1, 2, 4);

		int[] unsorted2 = {18, 73, 9, 33, 16, 64, 58, 61, 84, 49, 27, 13, 63, 4, 50, 56, 78, 1, 93, 35, 30, 76, 14, 41, 86, 3, 75, 70, 2, 90, 28, 55, 98, 29, 57, 94, 36, 15, 23, 62, 44, 60, 72, 79, 19, 71, 89, 87, 42, 7};
		int[] sorted2 = selectionSort(unsorted2, 1);
		splicePrint(sorted2, 10, 15);
		sorted2 = selectionSort(unsorted2, 10);
		splicePrint(sorted2, 10, 15);

		int[] unsorted3 = {18, 73, 9, 33, 16, 64, 58, 61, 84, 49, 27, 13, 63, 4, 50, 56, 78, 1, 93, 35, 30, 76, 14, 41, 86, 3, 75, 70, 2, 90, 28, 55, 98, 29, 57, 94, 36, 15, 23, 62, 44, 60, 72, 79, 19, 71, 89, 87, 42, 7};
		int[] sorted3 = mergeSort(unsorted3);
		System.out.println("Merge sort results:");
		splicePrint(sorted3, 0, unsorted3.length);
	}



	public static int[] selectionSort(int[] arr_, int iterations) {
		int[] arr = arr_.clone();
		for(int i = 0; i < arr.length && i < iterations; i++) {
			int min = i;
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[j] < arr[min]) {
					min = j;
				}
			}
			if(i != min) {
				int temp = arr[i];
				arr[i] = arr[min];
				arr[min] = temp;
			}
		}
		return arr;
	}

	public static int[] selectionSort(int[] arr) {
		return selectionSort(arr, arr.length - 1);
	}

	public static int[] splicePrint(int[] arr, int x, int y) {
		int start = Math.max(Math.min(x - 1, arr.length), 0);
		int end = Math.min(y - 1, arr.length);
		if(end - start <= 0) {
			throw new IllegalArgumentException("Wrong bounds! bad boy");
		}
		int[] result = new int[end - start + 1];
		for(int i = start; i < end + 1; i ++) {
			result[i - start] = arr[i];
		}

		for(int i = 0; i < result.length; i++) {
			if(i != 0) {
				System.out.print(", ");
			}
			System.out.print(result[i]);
		}
		System.out.println();

		return result;
	}

	public static int[] mergeSort(int[] arr) {
		int n = arr.length;
		if(n <= 1) {
			return arr;
		}
		int k = n / 2;
		int[] leftArray = new int[k];
		int[] rightArray = new int[n - k];
		System.arraycopy(arr, 0, leftArray, 0, k);
		System.arraycopy(arr, k, rightArray, 0, n - k);
		leftArray = mergeSort(leftArray);
		rightArray = mergeSort(rightArray);
		return merge(leftArray, rightArray);
	}

	private static int[] merge(int[] leftArray, int[] rightArray) {
		int n = leftArray.length + rightArray.length;
		int[] result = new int[n];
		int i = 0;
		int j = 0;
		for(int k = 0; k < n; k++) {
			if(i >= leftArray.length) {
				result[k] = rightArray[j];
				j++;
			} else if(j >= rightArray.length) {
				result[k] = leftArray[i];
				i++;
			} else if(leftArray[i] < rightArray[j]) {
				result[k] = leftArray[i];
				i++;
			} else {
				result[k] = rightArray[j];
				j++;
			}
		}
		return result;
	}

	public static Node[] TopologicalSort(DirectedGraph graph) {
        Node[] T = graph.getNodesArray();
        int[] post = BasicSearchAlgs.DFS(graph)[1];
        Arrays.sort(T, (Node a, Node b) -> {
            return Integer.compare(post[b.getValue() - 1], post[a.getValue() - 1]);
        });
        return T;
    }
}