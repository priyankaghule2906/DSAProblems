1. Longest subarray with sum K


1. Longest subarray with sum K

```text
Given an array nums of size n and an integer k, find the length of the longest sub-array that sums up to k. If no such sub-array exists, return 0.

Example 1
Input: nums = [10, 5, 2, 7, 1, 9],  k=15

Output: 4

Explanation: The longest sub-array with a sum equal to 15 is [5, 2, 7, 1], which has a length of 4. This sub-array starts at index 1 and ends at index 4, and the sum of its elements (5 + 2 + 7 + 1) equals 15. Therefore, the length of this sub-array is 4.
```

```java
// simple bruteforce with O(n*n*n)
// run three loops
// i to n, j = i=n  , third for i to j sum sum and check if equals k if yes check for maxLength
public int longestSubarray(int[] nums, int k) {
    int n = nums.length;
    int maxLength = 0;

    // starting index
    for (int startIndex = 0; startIndex < n; startIndex++) {
        // ending index
        for (int endIndex = startIndex; endIndex < n; endIndex++) { 
                /* add all the elements of 
                   subarray = nums[startIndex...endIndex]  */
            int currentSum = 0;
            for (int i = startIndex; i <= endIndex; i++) {
                currentSum += nums[i];
            }

            if (currentSum == k) {
                maxLength = Math.max(maxLength, endIndex - startIndex + 1);
            }
        }
    }
    return maxLength;
}

```

```java
// another approach is to use avoid using inner loop with o(n*n)

public static int getLongestSubarray(int []a, int k) {
    int n = a.length; // size of the array.
    int len = 0;
    for (int i = 0; i < n; i++) { // starting index
        int s = 0;
        for (int j = i; j < n; j++) { // ending index
            // add the current element to
            // the subarray a[i...j-1]:
            s += a[j];
            if (s == k)
                len = Math.max(len, j - i + 1);
        }
    }
    return len;
}
```
![hasing_prefixsum.png](..%2F..%2F..%2F..%2F.gitbook%2Fassets%2Fhasing_prefixsum.png)

```java
// optimal with TC = SC = O(n)

public static int lenOfLongSubarr(int nums[], int n, int k) {
    HashMap<Integer,Integer> prefixSum = new HashMap<>();
    int maxLength = 0;
    int sum =0;
    for(int i=0;i<n;i++){
        sum += nums[i];
        if(sum ==k){
            maxLength =i+1;
        }
        int rem = sum -k;
        if(prefixSum.containsKey(rem)){
            maxLength = Math.max(maxLength, i-prefixSum.get(rem));
        }
        if(!prefixSum.containsKey(sum)){
            prefixSum.put(sum, i);
        }
    }

    return maxLength;
}

```