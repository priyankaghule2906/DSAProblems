package arrays;

import org.junit.Assert;
import org.junit.Test;

public class KadanesAlgorithm {


    @Test
    public void test(){
        Assert.assertEquals(7, maxSubarraySum(new int[]{-2,-3,4,-1,-2,1,5,-3}, 8));
    }


    long maxSubarraySum(int arr[], int n){

        // Your code here
        long max_so_far = Integer.MIN_VALUE;
        long max_ending_here = 0;

        for (int i = 0; i < n; i++) {
            max_ending_here = max_ending_here + arr[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;

    }
}
