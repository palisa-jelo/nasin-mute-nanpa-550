import java.util.Random;
import java.util.ArrayList;

public class DynamicProgramming {
    public static void main(String[] args) {
        Random rand = new Random();

        int[] firstLIS = {6, 7, 8, 1, 2, 3};
        LongestIncreasingSubsequence(firstLIS);
        int[] secondLIS = {14, 84, 76, 26, 50, 45, 65, 79, 10, 3, 83, 43, 76, 1, 45, 72, 23, 94, 90, 4, 3, 54, 93, 38, 22, 42, 3, 22, 44, 50, 24, 23, 22, 46, 29, 3, 83, 56, 64, 19, 99, 86, 12, 33, 72, 71, 93, 42, 83, 67, 31, 59, 88, 84, 51, 59, 4, 25, 79, 42, 18, 55, 70, 67, 38, 44, 51, 78, 52, 39, 49, 3, 5, 70, 98, 59, 39, 17, 50, 98, 77, 54, 86, 23, 51, 95, 58, 46, 27, 55, 95, 1, 78, 82, 88, 74, 81, 52, 56, 43};
        LongestIncreasingSubsequence(secondLIS);
        System.out.println("\n--------------------\n");

        String testLPS = "racecar";
        largestPalindromicSubsequence(testLPS);
        System.out.println();
        String firstLPS = "abcdb";
        largestPalindromicSubsequence(firstLPS);
        String secondLPS = "accabbbcaacbcabcaccaabcbabaabbaaabaacbbaccaacccbcc";
        largestPalindromicSubsequence(secondLPS);


        
        /* JUMPING ALGORITHM CHECK*/
        /*
        int[] distances = new int[50];
        for(int repeat = 0; repeat < 10; repeat ++) {
            for(int i = 0; i < distances.length; i++) {
                distances[i] = rand.nextInt(100 + 1);
            }
            // Make path from end
            System.out.println("Alg1 says " + algOneJumping(distances));
            // Make path from start
            System.out.println("Alg2 says " + algTwoJumping(distances));
        }
        */
    }

    static int[] LongestIncreasingSubsequence(int[] A) {
        int[] OPT = new int[A.length];
        int[] paths = new int[A.length];
        // Initalise the list
        for(int i = 0; i < A.length; i++) {
            OPT[i] = 1;
            paths[i] = -1;
        }
        // Now check for each subproblem
        for(int i = 1; i < A.length; i++) {
            // We look backwards for a smaller number to see if we can attach A[i]
            for(int j = 0; j < i; j++) {
                if(A[i] > A[j]) {
                    if(OPT[i] < OPT[j] + 1) {
                        OPT[i] = OPT[j] + 1;
                        paths[i] = j;
                    }
                }
            }
        }
        int max = 0;
        for(int i = 0; i < OPT.length; i++) {
            if(OPT[i] > OPT[max]) {
                max = i;
            }
        }
        ArrayList<Integer> path = new ArrayList<>();
        int index = max;
        while(index != -1) {
            path.add(0, A[index]);
            index = paths[index];
        }
        System.out.println("Size = " + OPT[max] + " OPT:\n" + java.util.Arrays.toString(OPT));
        System.out.println("Our path was: " + path);
        return OPT;
    }

    static int[][] largestPalindromicSubsequence(String s) {
        int[][] OPT = new int[s.length()][s.length()];
        // Initalise the base cases
        for(int i = 0; i < s.length(); i++) {
            OPT[i][i] = 1;
            for(int j = i + 1; j < s.length(); j++) {
                OPT[i][j] = 0;
            }
        }
        // Let's be fun and iterate diagonally
        int row = 0;
        int column = 1;
        while(column < s.length()) {
            while(row < s.length() && column < s.length()) {
                if(s.charAt(row) == (s.charAt(column))) {
                    OPT[row][column] = OPT[row + 1][column - 1] + 2;
                } else {
                    OPT[row][column] = Math.max(OPT[row + 1][column], OPT[row][column - 1]);
                }
                row++;
                column++;
            }
            column = column - row + 1;
            row = 0;
        }
    
        System.out.println("Our longest palindrome has length = " + OPT[0][s.length() - 1]);

        return OPT;
    }

    static int algOneJumping(int[] distances) {
        int[] output = new int[distances.length];
        output[distances.length - 1] = 0;
        output[distances.length - 2] = 0;
        for(int i = distances.length - 3; i > -1; i--) {
            output[i] = Math.min(distances[i+1] + output[i+1], distances[i+2] + output[i+2]);
        }
        return Math.min(output[0] + distances[0], output[1] + distances[1]);
    }

    static int algTwoJumping(int[] distances) {
        int[] output = new int[distances.length];
        output[0] = distances[0];
        output[1] = distances[1];
        for(int i = 2; i < distances.length; i++) {
            output[i] = distances[i] + Math.min(output[i - 1], output[i - 2]);
        }
        return Math.min(output[distances.length - 1], output[distances.length - 2]);
    }
}