* Arrays DSA Questions


1. Linear Search
2. Largest Element
3. Second Largest Element
4. Maximum Consecutive Ones
5. Left Rotate Array by One
6. Left Rotate Array by K Places
7. Move Zeros to End
8. Remove duplicates from sorted array
9. Find missing number
10. Union of two sorted arrays
11. Intersection of two sorted arrays
12. Leaders in an Array
13. Print the matrix in spiral manner
14. Rearrange array elements by sign
15. Pascal's Triangle
16. Rotate matrix by 90 degrees
17. Two Sum
18. 3 Sum


1. Linear Search
```text
Given an array of integers nums and an integer target, find the smallest index (0 based indexing) where the target appears in the array. If the target is not found in the array, return -1.

Example 1
Input: nums = [2, 3, 4, 5, 3], target = 3

Output: 1

Explanation: The first occurence of 3 in nums is at index 1
```
```text
Approach 
Traverse through the array, similar to the idea of scanning each book serially.
Check if the current element of array is equal to the target element. If so, return the index and stop scanning further.
In case target value is not found, return -1 marking that the target element missing.
```

```java
public int linearSearch(int[] nums, int target) {
        // Traverse the entire array
        for (int i = 0; i < nums.length; i++) {
            // Check if current element is target
            if (nums[i] == target) {
                // Return if target found
                return i;
            }
        }
        // If target not found
        return -1;
    }
```
```text
TC = O(N)
SC = O(1)
```

2. Largest Element

```text
Given an array of integers nums, return the value of the largest element in the array.


Example 1
Input: nums = [3, 3, 6, 1]

Output: 6

Explanation: The largest element in array is 6
```
```java
// Brute Force approach with O(n Log n)

public static int largestElement(int[]  nums) {
    // Sort array
    Arrays.sort(nums);
        /*Largest element will be at 
        the last index of the array.*/
    int largest = nums[nums.length - 1];
    //Return the largest element in array.
    return largest;
}
```
```java
// Optimal approach with o(n)
  public int largestElement(int[] nums) {
        // Initialize max as the first element
        int max = nums[0];
        // Traverse the entire array
        for (int i = 1; i < nums.length; i++) {
            /* If current element is greater
            than max, update max*/
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        // Return the largest element found
        return max;
    }
```

3. Second Largest Element
```text
Given an array of integers nums, return the second-largest element in the array. If the second-largest element does not exist, return -1.

Example 1
Input: nums = [8, 8, 7, 6, 5]

Output: 7
Explanation: The largest value in nums is 8, the second largest is 7
```

```java
// Brute force approach with O(n log n)
public int secondLargestElement(int[] nums) {
    int n = nums.length;
    // Check if the array has less than 2 elements
    if (n < 2) {
        // Indicating no second largest element is possible
        return -1;
    }
    // Sort the array in ascending order
    Arrays.sort(nums);
    // Largest element will be at last index
    int largest = nums[n - 1];
    int secondLargest = -1;
    // Traverse the sorted array from right to left
    for (int i = n - 2; i >= 0; i--) {
            /* If the current element is not
            equal to the largest element*/
        if (nums[i] != largest) {
                /* Assign the current element 
                as the second largest and break*/
            secondLargest = nums[i];
            break;
        }
    }
    // Return the second largest element
    return secondLargest;
}

```
```text
Approach 
* Initialize two variables: largest, and secondLargest. Initialize largest and secondLargest to INT_MIN as initially none of them should be holding any values.
*  If the current element is larger than largest, update secondLargest and largest.
*  Else if the current element is larger than secondLargest and not equal to largest, update secondLargest.
* Traverse the entire array to update the second largest element in declared variable i.e, secondLargest.
```
```java
// Optimal approach with O(n)

public int secondLargestElement(int[] nums) {
    // Check if the array has less than 2 elements
    if (nums.length < 2) {
        // If true, return -1 there is no second largest element
        return -1;
    }
        /* Initialize variables to store the
    largest and second largest elements*/
    int largest = Integer.MIN_VALUE;
    int secondLargest = Integer.MIN_VALUE;
        /*Single traversal to find thelargest 
       and second largest elements*/
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] > largest) {
            secondLargest = largest;
            largest = nums[i];
        }
        else if (nums[i] > secondLargest && nums[i] != largest) {
            secondLargest = nums[i];
        }
    }
    // Return the second largest element
    return secondLargest == Integer.MIN_VALUE ?  -1 : secondLargest;
}
```

4. Maximum Consecutive Ones


17. Two Sum
```text
Given an array of integers nums and an integer target. Return the indices(0 - indexed) of two elements in nums such that they add up to target.
Each input will have exactly one solution, and the same element cannot be used twice. Return the answer in non-decreasing order.
Example 1
Input: nums = [1, 6, 2, 10, 3], target = 7
Output: [0, 1]
Explanation: nums[0] + nums[1] = 1 + 6 = 7
```
```java
// brute force approach with TC of O(N*N)
/* Approach
Iterate in array from 0 to last index of the array (lets call this variable i).
Now, run another loop say(j) from i+1 to last index of the array.
If sum of arr[i] and arr[j] equals to target then return the i and j.
If no such indices are found then return -1 and -1.
 */

public int[] twoSum(int[] nums, int target) {
    int n = nums.length;
    //create ans array to store ans
    int[] ans = new int[2];
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
                /* if nums[i] + nums[j] is equal to 
                   target put i and j in ans */
            if (nums[i] + nums[j] == target) {
                ans[0] = i;
                ans[1] = j;
                return ans;
            }
        }
    }

    // Return {-1, -1} if no such pair is found
    return new int[]{-1, -1};
}
```

```java
// better idea with TC of O(n) and SC of O(n)
/*
 * The idea is to traverse the array and use a HashMap to check if for each element, 
 * an element in the HashMap exists, such that sum of both of the elements is equal to the target. 
 * This method trims down the search space and provides a better time complexity.
 * */

public int[] twoSum(int[] nums, int target) {
    // Map to store (element, index) pairs
    Map<Integer, Integer> mpp = new HashMap<>();
    // Size of the nums array
    int n = nums.length;
    for (int i = 0; i < n; i++) {
        // Current number in the array
        int num = nums[i];
        // Number needed to reach the target
        int moreNeeded = target - num;
        // Check if the complement exists in map
        if (mpp.containsKey(moreNeeded)) {
                /* Return the indices of the two
                numbers that sum up to target*/
            return new int[]{mpp.get(moreNeeded), i};
        }
        // Store current number and its index in map
        mpp.put(num, i);
    }
    // If no such pair found, return {-1, -1}
    return new int[]{-1, -1};
}
```
```java
// another solution using two pointers approach after sorting the array
// with this TC is O(n log n)
public int[] twoSum(int[] nums, int target) {
        // create a 2D array to store the elements and their original index
        int n = nums.length;
        int[] ans = new int[2];
        int[][] numberWithIndex = new int[n][2];
        for(int i=0; i<n; i++) {
            numberWithIndex[i][0] = nums[i];
            numberWithIndex[i][1] = i;
        }

        Arrays.sort(numberWithIndex, (a,b) -> Integer.compare(a[0], b[0]));

        int left = 0;
        int right = n-1;
        while(left < right) {
            int sum = numberWithIndex[left][0] + numberWithIndex[right][0];

            if(sum == target) {
                ans[0] = numberWithIndex[left][1];
                ans[1] = numberWithIndex[right][1];

                return ans;
            } else if(sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }
```