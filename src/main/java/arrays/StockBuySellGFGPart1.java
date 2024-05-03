package arrays;

import org.junit.Test;

import java.util.ArrayList;

public class StockBuySellGFGPart1 {

    @Test
    public void test(){
        // get the pairs

        System.out.println(stockBuySell(new int[]{100,180,260,310,40,535,695}, 7));
    }

    ArrayList<ArrayList<Integer> > stockBuySell(int A[], int n) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int purchasePrice = A[0];
        int day = 0;
        for(int i=1;i<n;i++){
            ArrayList<Integer> pair = new ArrayList<>();
            if(A[i]>purchasePrice){
                pair.add(day);
                pair.add(i);
                list.add(pair);
            }
            purchasePrice = A[i];
            day = i;


        }

        return list;
    }
}
