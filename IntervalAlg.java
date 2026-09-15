import java.util.ArrayList;
import java.util.Arrays;

public class IntervalAlg {

    public static void main (String[] args) {
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
}
