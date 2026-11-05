import java.util.ArrayList;
import java.util.Arrays;

public class MiscelaneousHomework3Algs {

    public static void main (String[] args) {
        System.out.println("Homework 3 Part 2:\n");

        // COMPATIBLE INTERVALS

        int[][] testInterval = process("[[3, 5], [1, 2], [2, 4]]");
        for(int[] interval : testInterval) {
            System.out.println("From " + interval[0] + " to " + interval[1]);
        }

        ArrayList<int[]> compatableTest = compatableIntervals(testInterval);
        for(int[] interval : compatableTest) {
            System.out.println("From " + interval[0] + " to " + interval[1]);
        }
        System.out.println("\n");
        
        ArrayList<int[]> compatableIntervalsOne = compatableIntervals(process("[[4, 36], [17, 147], [5, 18], [62, 67], [16, 32], [96, 128], [21, 117], [19, 122], [64, 168], [45, 99], [12, 55], [20, 26], [79, 126], [3, 9], [89, 101], [55, 112], [26, 157], [1, 2], [16, 116], [49, 70], [26, 60], [52, 153], [16, 28], [20, 83], [158, 166], [3, 7], [7, 8], [61, 140], [1, 4], [12, 170], [19, 57], [35, 110], [54, 155], [93, 137], [47, 58], [49, 114], [115, 174], [60, 61], [64, 90], [3, 142], [4, 145], [89, 119], [24, 76], [22, 154], [63, 108], [8, 27], [26, 49], [18, 77], [106, 175], [2, 87], [7, 148], [65, 66], [36, 135], [17, 118], [96, 130], [38, 171], [4, 40], [24, 38], [22, 132], [129, 144]]"));
        for(int[] interval : compatableIntervalsOne) {
            System.out.println("From " + interval[0] + " to " + interval[1]);
        }
        System.out.println();

        // FRACTIONAL KNAPSACK

        int[] testValue = {1, 4, 4};
        int[] testCost = {2, 3, 4};
        double money = 8;
        buy(testValue, testCost, money);

        int[] valueOne = {201, 95, 131, 190, 220, 105, 221, 144, 183, 240, 229, 225, 146, 217, 242, 193, 130, 124};
        int[] costOne = {23, 10, 16, 51, 29, 18, 22, 14, 27, 13, 22, 94, 51, 12, 3, 10, 14, 5};
        money = 33;
        buy(valueOne, costOne, money);

        int[] valueTwo = {64, 111, 58, 168, 98, 192, 142, 129, 214, 205, 240, 243, 127, 190, 150, 216, 221, 242, 242, 123, 215, 237, 113, 93, 202, 187, 71};
        int[] costTwo = {1, 2, 2, 23, 10, 38, 16, 24, 8, 18, 31, 59, 14, 27, 46, 21, 64, 49, 35, 40, 37, 11, 3, 10, 14, 44, 5};
        money = 31;
        buy(valueTwo, costTwo, money);
    }

    public static int[][] process(String input) {
        input = input.substring(2, input.length() - 2);
        String[] unprocessed = input.split("\\], \\[");
        int[][] output = new int[unprocessed.length][2];
        
        for(int i = 0; i < unprocessed.length; i++) {
            output[i][0] = Integer.parseInt(unprocessed[i].substring(0, unprocessed[i].indexOf(',')));
            output[i][1] = Integer.parseInt(unprocessed[i].substring(unprocessed[i].indexOf(',') + 2, unprocessed[i].length()));
        }

        // Sort by non-incresing end time
        Arrays.sort(output, (a, b) -> (Integer.compare(a[1], b[1])));

        return output;
    }

    public static ArrayList<int[]> compatableIntervals(int[][] intervals) {
        ArrayList<int[]> outputs = new ArrayList<>();

        int index = 0;
        int takenTime = 0;
        while(index < intervals.length && takenTime <= intervals[intervals.length - 1][1]) {
            if(intervals[index][0] > takenTime) {
                outputs.add(intervals[index]);
                takenTime = intervals[index][1];
            }
            index++;
        }

        return outputs;
    }

    public static double[] fractionalKnapsack(int[] value, int[] cost, double money) {
        if(cost.length != value.length) {
            throw new RuntimeException("There must be as many prices as values for the items");
        }
        double[] output = new double[cost.length];
        double[][] efficiency = new double[cost.length][2];
        for(int i = 0; i < cost.length; i++) {
            efficiency[i][0] = Double.valueOf(value[i]) / Double.valueOf(cost[i]);
            efficiency[i][1] = i; //Original index of item
        }

        Arrays.sort(efficiency, (double[] a, double[] b) -> {
            return Double.compare(b[0],  a[0]);
        });

        int index = 0;
        while(money > 0 && index < cost.length) {
            // Buy the whole item if we can
            if(money > cost[(int)efficiency[index][1]]) {
                output[(int)efficiency[index][1]] = 1;
                money -= cost[(int)efficiency[index][1]];
            } else {
                // Otherwise, buy as much of it as we can
                double fraction = money / cost[(int)efficiency[index][1]];
                output[(int)efficiency[index][1]] = fraction;
                money = 0;
            }
            index++;
        }
        

        return output;
    }

    public static void buy(int[] value, int[] cost, double money) {
        double[] item = fractionalKnapsack(value, cost, money);
        double totalValue = 0;

        System.out.print("We are buying");
        for(int i = 0; i < cost.length; i++) {
            System.out.print(" :  " + item[i]);
            totalValue += item[i]*value[i];
        }
        System.out.println("\nOur total bought value is " + totalValue);
    }
}
