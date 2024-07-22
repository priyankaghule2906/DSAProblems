package arrays.slidingwindow;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

// leetcode 2461
public class MaximumSumofDistinctSubarraysWithLengthK {


    @Test
    public void test(){
        int[] arr = new int[]{1, 5, 4, 2, 9, 9, 9, 8, 7, 6};
        System.out.println(maxSumWithDistinct(arr, 3));

    }

    public int maxSum(int[] nums, int k){
        // in this method first just try to find out the max sum irrespective of distinct elements
        int n = nums.length;
        int currentSum =0;
        int maxSum =0;
        int left =0;


        for(int right=0;right<n;right++){
            currentSum += nums[right];
             if(right-left+1 == k){
                 maxSum = Math.max(maxSum, currentSum);
                 currentSum -= nums[left];
                 left++;
             }
        }

        return maxSum;
    }

    public int maxSumWithDistinct(int[] nums, int k){
        // now in this method I want to find for distinct elements only
        int n = nums.length;
        int currentSum =0;
        int maxSum =0;
        int right =0;
        Set<Integer> set = new HashSet<>();

        for (int left =0; left<n; left++){

            while (right<n && !set.contains(nums[right])&& set.size()<k){
                set.add(nums[right]);
                currentSum += nums[right];
                right++;
            }

            if(set.size() == k){
                maxSum = Math.max(maxSum, currentSum);
            }

            currentSum -= nums[left];
            set.remove(nums[left]);
        }

        return maxSum;
    }
}
