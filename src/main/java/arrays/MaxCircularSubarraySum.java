package arrays;

import org.junit.Test;

public class MaxCircularSubarraySum {
    @Test
    public void test(){
//        System.out.println(maxCircularSum(new int[]{8,-8,9,-9,10,-11,12}, 7));
        System.out.println(circularSubarraySumgfg(new int[]{-2,-15,-25,-16, -2, 10, -27, 24}, 8));
    }

    public static int maxCircularSum(int a[], int n)
    {

        if (n == 1)
            return a[0];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        int curr_max = a[0], max_so_far = a[0],
                curr_min = a[0], min_so_far = a[0];

        for (int i = 1; i < n; i++)
        {
            curr_max = Math.max(curr_max + a[i], a[i]);
            max_so_far = Math.max(max_so_far, curr_max);

            curr_min = Math.min(curr_min + a[i], a[i]);
            min_so_far = Math.min(min_so_far, curr_min);
        }
        if (min_so_far == sum) {
            return max_so_far;
        }

        return Math.max(max_so_far, sum - min_so_far);
    }


    static int circularSubarraySumgfg(int a[], int n) {

        if(n==1){
            return a[0];
        }

        int maxSum = a[0];
        int minSum = a[0];
        int max_so_far = a[0];
        int min_so_far = a[0];
        int totalSum =0;

        for(int i=0;i<n;i++) {
            totalSum += a[i];
        }

        for(int i=1;i<n;i++){

            // kadanes algo to find max sum

            max_so_far = Math.max(a[i], max_so_far+ a[i]);
            maxSum = Math.max(maxSum, max_so_far);

            // kadanes algo to find min sum

            min_so_far = Math.min(a[i],min_so_far+a[i]);
            minSum = Math.min(minSum, min_so_far);

        }

        if(totalSum == minSum){
            return maxSum;
        }

        return Math.max(maxSum, totalSum-minSum);
    }

    static int circularSubarraySum(int a[], int n) {

        //brute
        int res =a[0];
        for(int i=0;i<n;i++){
            int sum =a[i];
            int currentMaxSum = a[i];
            for(int j = 1;j<n;j++){
                int index = (i+j) %n;
                sum += a[index];
                currentMaxSum = Math.max(currentMaxSum, sum);
            }
            res = Math.max(res, currentMaxSum);

        }

        return res;
    }
}
