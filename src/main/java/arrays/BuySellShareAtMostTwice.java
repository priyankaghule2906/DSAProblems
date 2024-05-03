package arrays;

import org.junit.Test;

public class BuySellShareAtMostTwice {

    @Test
    public void testMaxProfit() {
        maxProfit(6, new int[]{10,22,5,75,65,80});
    }

    public static int maxProfit(int n, int[] price) {
        // code here

       int[] profits = new int[n];
       int maxPrice = price[n-1];
       for(int i=n-2;i>=0;i--){
           if(maxPrice<price[i]){
               maxPrice = price[i];
           }
           profits[i] = Math.max(profits[i+1], maxPrice - price[i]);
       }

       int minPrice = price[0];
       for(int i=1;i<n;i++){
           if(minPrice>price[i]){
               minPrice = price[i];
           }
           profits[i] = Math.max(profits[i-1], profits[i]+ (price[i]- minPrice));
       }
        return profits[n-1];
    }

}


