package arrays;

import org.junit.Test;

public class LargestEvenOddSubarray {

    @Test
    public void test(){
        System.out.println(maxEvenOdd(new int[]{10,12,14,7,8}, 5));
    }

    public static int maxEvenOdd(int arr[], int n)
    {
        // your code here

        int count=0;
        int result =0;

        for(int i=1;i<n;i++) {

            if((arr[i] + arr[i-1])%2!=0){
                count++;
            }

            result = Math.max(result, count);
        }

        return result;
    }
}
