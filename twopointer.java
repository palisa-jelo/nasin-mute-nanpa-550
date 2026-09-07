import javax.imageio.IIOException;

public class twopointer {
	public static void main(String[] args) {
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

		System.out.print("\n\n------\n\n");

		int[] unsorted1 = {18, 73, 98, 9, 33};
		int[] sorted1 = selectionSort(unsorted1,2);
		splicePrint(sorted1, 2, 4);

		int[] unsorted2 = {18, 73, 9, 33, 16, 64, 58, 61, 84, 49, 27, 13, 63, 4, 50, 56, 78, 1, 93, 35, 30, 76, 14, 41, 86, 3, 75, 70, 2, 90, 28, 55, 98, 29, 57, 94, 36, 15, 23, 62, 44, 60, 72, 79, 19, 71, 89, 87, 42, 7};
		int[] sorted2 = selectionSort(unsorted2, 1);
		splicePrint(sorted2, 10, 15);
		sorted2 = selectionSort(unsorted2, 10);
		splicePrint(sorted2, 10, 15);

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
		int start = Math.min(x - 1, arr.length);
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

	// public static int[] mergeSort() {

	// }
}
