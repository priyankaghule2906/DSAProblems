package leetcodecontents;


import org.junit.Test;

import java.util.Arrays;

/* 3397. Maximum Number of Distinct Elements After Operations
* maximum number of distinct elements after operation
*
* You are given an integer array nums and an integer k.

You are allowed to perform the following operation on each element of the array at most once:

Add an integer in the range [-k, k] to the element.
Return the maximum possible number of distinct elements in nums after performing the operations.



Example 1:

Input: nums = [1,2,2,3,3,4], k = 2

Output: 6

Explanation:

nums changes to [-1, 0, 1, 2, 3, 4] after performing operations on the first four elements.

Example 2:

Input: nums = [4,4,4,4], k = 1

Output: 3

Explanation:

By adding -1 to nums[0] and 1 to nums[1], nums changes to [3, 5, 4, 4].



Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
0 <= k <= 109
* */


/*

Sort the Array: Helps in orderly processing of elements.
Track Last Assigned Value: Ensures distinct values for every element.
Use Minimum First: Assign the smallest possible value in range [min, max] to minimize conflict with future elements.
Handle Conflict by Incrementing: When conflict arises, increment previousAssignedMax to maintain distinctness.


Conclusion
The intuition is simple:

Sort the array to handle elements in a systematic order.
Assign the smallest possible distinct value to each element using its range.
Increment the last assigned value when conflicts arise to maintain distinctness.
This ensures the maximum number of distinct elements while adhering to the constraints of the probl

*
 */

public class MaxDistinctElemAfterKOperation {


    @Test
    public void test(){
        System.out.println(maxDistinctElements(new int[] {1,2,2,3,3,4},2));

        // ans : 6
    }

    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int distinctCount = 0;
        int previousAssignedMax = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            int min = nums[i] - k;
            int max = nums[i] + k;
            // each element can be assigned values in the range of -k to k so each element we consider min possbile value
            if(previousAssignedMax < min ){
                previousAssignedMax = min;
                distinctCount++;
            } else if(previousAssignedMax < max) {
                previousAssignedMax = previousAssignedMax+1;
                distinctCount++;
            }


        }
        return distinctCount;

    }
}
