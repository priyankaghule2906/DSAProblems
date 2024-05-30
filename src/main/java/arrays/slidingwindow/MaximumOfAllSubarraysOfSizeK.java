package arrays.slidingwindow;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class MaximumOfAllSubarraysOfSizeK {

    @Test
    public void test(){
        System.out.println(Arrays.toString(getMaxSubArrayUsingBruteForce(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
    }

    @Test
    public void gfgTest(){
        int[] arr = {1,2,3,1,4,5,2,3,6};
        System.out.println(max_of_subarrays(arr,9,3));
    }

    // brute force

    /*
    * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    * Output: [3,3,5,5,6,7]
    * */

    public static int[] getMaxSubArrayUsingBruteForce(int nums[], int k){
        int n = nums.length;;
        int []result = new int[n-k+1];

        for(int i=0;i<n-2;i++){
            int max = Integer.MIN_VALUE;
            for(int j=i;j<i+k;j++){
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        return null;
    }


    static ArrayList<Integer> max_of_subarrays(int arr[], int n, int k)
    {
        // Your code here

        ArrayList<Integer> list = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i=0;i<n;i++){

            while(deque.size() >0 && deque.peekFirst() <= i-k){
                deque.pollFirst();
            }

            while(deque.size() > 0 && arr[deque.peekLast()] < arr[i]){
                deque.pollLast();
            }

            deque.offerLast(i);


            if (i >= k - 1) {
                list.add(arr[deque.peekFirst()]);
            }

        }
        return list;
    }
}
