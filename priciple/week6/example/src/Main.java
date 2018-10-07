import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
        int[] prices = new int[]{1,2,3,4,1};
        int start = 0;
        int minPrice = Integer.MAX_VALUE;
        int benefit = 0;
        for (int i = 1; i < prices.length; i++ ) {

            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            else {

                benefit = Integer.max(benefit, prices[i] - minPrice);
            }
        }
        return benefit;
    }

}
