import java.util.Arrays;

public class KnapsackAlg {
    public static void main(String[] args) {
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
