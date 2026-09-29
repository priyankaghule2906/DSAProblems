Prefix Sum Problems

1. Longest Subarray with Sum ≤ K — not on LeetCode directly (custom/GfG variant)

Given an array arr[] containing integers and an integer k, your task is to find the length of the longest subarray where the sum of its elements is equal to the given value k. If there is no subarray with sum equal to k, return 0.

Examples:

Input: arr[] = [10, 5, 2, 7, 1, -10], k = 15
Output: 6
Explanation: Subarrays with sum = 15 are [5, 2, 7, 1], [10, 5] and [10, 5, 2, 7, 1, -10]. The length of the longest subarray with a sum of 15 is 6.

```java
class Solution {
    public int longestSubarraySumK(int[] nums, int k) {
        Map<Integer, Integer> firstIndexOfPrefixSum = new HashMap<>();
        int prefixSum = 0;
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            // case 1: subarray from index 0 to i sums exactly to k
            if (prefixSum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // case 2: some earlier prefix sum, when removed, leaves exactly k
            if (firstIndexOfPrefixSum.containsKey(prefixSum - k)) {
                int startIndex = firstIndexOfPrefixSum.get(prefixSum - k);
                maxLen = Math.max(maxLen, i - startIndex);
            }

            // only store the FIRST occurrence of each prefix sum (to maximize length)
            firstIndexOfPrefixSum.putIfAbsent(prefixSum, i);
        }

        return maxLen;
    }
}
```

Why it works:

Let prefixSum[i] = sum of nums[0..i]. A subarray nums[j+1..i] has sum k exactly when prefixSum[i] - prefixSum[j] == k, i.e. prefixSum[j] == prefixSum[i] - k.
So at each index i, we check: "has some earlier prefix sum equal to prefixSum[i] - k occurred before?" If yes, the subarray between that earlier index and i sums to k.
We store only the first occurrence of each prefix sum in the map — since we want the longest subarray, an earlier starting point is always at least as good, so overwriting would only hurt us. putIfAbsent enforces this.
The prefixSum == k check handles the edge case where the valid subarray starts right at index 0 (no earlier prefix to subtract).

Complexity:

Time: O(n) — single pass, O(1) average HashMap operations.
Space: O(n) — for the prefix sum map.

```text
arr[] = [10, 5, 2, 7, 1, -10], k = 15

i             0       1.      2.     3.      4     5. 
arr[i].       10.     5.      2.     7.      1.  -10
prefix sum    10.    15.      17.   24.     25.   15
prefix sum-k   5      0.      -2.    9.     10
maxLen.               2                      4.    6
map.          {10, 0} {15,1} {17,2} {24,3}
    
    
    -5, 8, -14, 2, 4, 12.  k = -5
    
    -5, 8, -14,  2,  4, 12
    -5. 3. -11. -9. -5.  7
    
    10, [4,  2,  7,  1,  1,]  7 k=15
    
    10  14  16  23  24  25  32
    
    -5. -1.  1.  8.  9  10. 17
```