### Find largest and Second largest element in an array

```text
The naive solution is to sort the array in descending order and then return the 
second element which is not equal to the largest element from the sorted array.
but the time time complexity of the solution would be O(n log n) log n to sort 
the array in descending order and n for iterating the loop.

The better approach is to keep track of the largest and second largest element
 while traversing the array. If an element is greater than the largest element, 
 we update the largest as well as the second largest. Else if an element 
 is smaller than largest but greater than second largest, then we update the
 second largest only.  
 Time Complexity: O(n), where n is the size of input array.
 Auxiliary space: O(1), as no extra space is required.
 
```
```java
 public static ArrayList<Integer> largestAndSecondLargest(int sizeOfArray, int arr[])
    {
        //code here.
        ArrayList<Integer> list = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        
        for(int i=0;i<sizeOfArray;i++){
            if(arr[i]>max){
                secondMax = max;
                max = arr[i];
            } else if(arr[i]>secondMax && arr[i]!=max) {
                secondMax = arr[i];
            }
        }
        
        if(max == secondMax || secondMax==Integer.MIN_VALUE){
            secondMax = -1;
        }
        list.add(max);
        list.add(secondMax);
        return list;
        
    }
```

### Maximum Index
```text

Given an array a of n positive integers. The task is to find the 
maximum of j - i subjected to the constraint of a[i] < a[j] and i < j.

Concept : find the min from left, maximum from right and store them in a separate array,
we need to fint the maximum difference of j-i so keep traversing the element of and find the max
tc : O(n)
sc : O(n)
```

```java
  static int maxIndexDiff(int a[], int n) { 
        
        // create two array to store max from right and min from left
        
        int[] maxRight = new int[n];
        int[] minLeft = new int[n];
        
        // start storing elements
        minLeft[0] = a[0];
        for(int i=1;i<n;i++){
            minLeft[i] = Math.min(minLeft[i-1], a[i]);
        }
        maxRight[n-1] = a[n-1];
        for(int i=n-2;i>=0;i--){
            maxRight[i] = Math.max(maxRight[i+1], a[i]);
        }
        // check for maxDiff
        int ans = 0;
        int i=0,j=0;
        while(i<n && j<n){
            if(minLeft[i]<=maxRight[j]){
                ans = Math.max(ans, j-i);
                j++;
            } else  {
                i++;
            }
        }
        
        return ans;
    }
```
### Check if array is sorted and rotated
```text

When the array is sorted and rotated, so in this case when the previous 
element is greater than the current element will occur only once. If 
this occurs zero times or more than one times then the array is not properly sorted and rotated
TC : O(n)
SC : O(1)
```

```java
public boolean check(int[] nums) {
        int count=0;
        for(int i =0;i<nums.length-1;i++){
            if(nums[i] > nums[(i+1)]){
                count++;
            }
        }
        if(count==0 || (count==1 && nums[0]>=nums[nums.length-1])){
            return true;
        }
        return false;      
    }
```
### Maximum occured integer in the given range
````text

Problem : Given two arrays L[] and R[] of size N where L[i] and R[i] (0 ? L[i], R[i] < 106)denotes a range 
of numbers, the task is to find the maximum occurred integer in all the ranges. If more than one such integer 
exists, print the smallest one
A naive approach to solve this problem is create a hashmap with frequencies and find the key corresponding to that value

the efficient solution is use the prefix sum  
````

![max-appearing-element-in-range.png](..%2F..%2F..%2F..%2F.gitbook%2Fassets%2Fmax-appearing-element-in-range.png)

```java
 public static int maxOccured(int L[], int R[], int n, int maxx) {
    // using prefix sum method
    int[] freq = new int[1000000];

    for (int i = 0; i < n; i++) {
        freq[L[i]] += 1;
        freq[R[i] + 1] -= 1;
    }

    // do the sum of prefix and simultaneously find the max

    int max = 0;
    int index = 0;
    for (int i = 1; i < maxx; i++) {
        freq[i] = freq[i] + freq[i - 1];
        if (freq[i] > max) {
            max = freq[i];
            index = i;
        }

    }
    return index;
}
```
### Rearrange Array Alternately
```text

Given a sorted array of positive integers. Your task is to rearrange the array elements alternatively i.e first element should be max value,
second should be min value, third should be second max, fourth should be second min and so on.
Note: Modify the original array itself. Do it without using any extra space. You do not have to return anything.

Example 1:

Input:
n = 6
arr[] = {1,2,3,4,5,6}
Output: 6 1 5 2 4 3
Explanation: Max element = 6, min = 1, 
second max = 5, second min = 2, and 
so on... Modified array is : 6 1 5 2 4 3.

The naive approach is to create a temporary array, maintain two pointers one for max element and one min elements and keep populating the temp array until all are visited
https://www.interviewbit.com/blog/rearrange-array-alternately/ 
but here TC is O(n) and SC is O(n)
we can optimized the space complexity by following

The pseudo-code for the above-discussed algorithm can be:

1. Initialize two pointers i and j, i will be pointing at the minimum index, and j will be pointing at the maximum index (i.e. n-1).
2. Initialize another variable called greaterMax that will be equal to the 1 + maximum element i.e. 1 + a[j].
3. Traverse the entire input array by performing the following operations:
    - check if the current index is even (divisible by 2), then:
        update a[currentIndex] = a[currentIndex] + (A[j] % greaterMax) * greaterMax
        decrement j by 1 i.e. j--
    - if the current index is odd (not divisible by 2), then:
        update a[currentIndex] = a[currentIndex] + (A[i] % greaterMax) * greaterMax
        increment i by 1 i.e. i++
        
```
![img.png](..%2F..%2F..%2F..%2F.gitbook%2Fassets%2Fimg.png)
````java
  public static void rearrange(long arr[], int n){
        
        int max = n-1;
        int min = 0;
        long gt = arr[max]+1;
        
        for(int i=0;i<n;i++){
            if(i%2==0){
                arr[i] = arr[i] + (arr[max]%gt) * gt;
                max--;
            } else {
                arr[i] = arr[i] + (arr[min]%gt) * gt;
                min++;
            }
        }
        
        for(int i=0;i<n;i++){
            arr[i] /=gt;
        }
        
    }
````
### First Missing Positive
```text
Smallest Positive missing number OR 41. First Missing Positive
Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.

You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.


Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.
Example 3:

Brute force idea is to sort the array and then check for the smallest missing number (start from 1) if it is present then increment it.

https://leetcode.com/problems/first-missing-positive/editorial/

Algorithm
1. Initialize a variable n to the length of nums.
2. Use cycle sort to place positive elements smaller than n at the correct index.

    - Initialize a variable i to 0.
    - Iterate through the elements in nums:
    - Set a variable correctIdx to nums[i] - 1.
    - If the nums[i] is greater than zero, less than or equal to n, and does not equal nums[correctIdx], swap the element at nums[i] with the element at nums[correctIdx].
      Otherwise, increment i.
 3. Iterate through sorted nums and return the smallest missing positive number.

For each element in nums, if nums[i] does not equal i + 1, return i + 1, the smallest missing positive number.
Return n + 1, the smallest missing positive number when each number in nums is in the correct position.

```

````java
 public static int firstMissingPositive(int[] nums,
                                        int n)
{
    Arrays.sort(nums);
    int ans = 1;
    for (int i = 0; i < n; i++) {
        if (nums[i] == ans)
            ans++;
    }
    return ans;
}
//bruce force TC (n log n) and SC O(1)
````

```java
 public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        
        // Use cycle sort to place positive elements smaller than n
        // at the correct index
        int i = 0;
        while (i < n) {
            int correctIdx = nums[i] - 1;
            if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[correctIdx]) {
                swap(nums, i, correctIdx);
            } else {
                i++;
            }
        }

        // Iterate through nums
        // return smallest missing positive integer
        for (i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // If all elements are at the correct index
        // the smallest missing positive number is n + 1
        return n + 1;
    }

    // Swaps two elements in nums
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
```
### Buy and sell Stock
```text
The cost of stock on each day is given in an array A[] of size N. Find all the segments of days on which you buy and sell the stock such that the sum of difference between sell and buy prices is maximized. Each segment consists of indexes of two elements, first is index of day on which you buy stock and second is index of day on which you sell stock.
Note: Since there can be multiple solutions, the driver code will print 1 if your answer is correct, otherwise, it will return 0. In case there's no profit the driver code will print the string "No Profit" for a correct solution.
N = 7
A[] = {100,180,260,310,40,535,695}
Output:
1
Explanation:
One possible solution is (0 3) (4 6)
We can buy stock on day 0,
and sell it on 3rd day, which will 
give us maximum profit. Now, we buy 
stock on day 4 and sell it on day 6.
Your Task:
The task is to complete the function stockBuySell() which takes an array of A[] and N as input parameters and finds the days of buying and selling stock. The function must return a 2D list of integers containing all the buy-sell pairs i.e. the first value of the pair will represent the day on which you buy the stock and the second value represent the day on which you sell that stock. If there is No Profit, return an empty list.


Expected Time Complexity: O(N)
Expected Auxiliary Space: O(N)

idea : 
minus sign denotes stock bought and + denotes stock sold on that day
bought at | Sold at | pair (index of bought at, index of sold at)
100         180       [0, 1]
180         260       [1, 2]
260         310       [2, 3]
40          535       [4, 5]
535         695       [5, 6]

-100+180-180+260-260+310-40+535-535+695
```

```java
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
```
### Buy and Sell a Share at most twice
```text
Buy and Sell a Share at most twice
In daily share trading, a buyer buys shares in the morning and sells them on the same day. If the trader is allowed to make at most 2 transactions in a day, the second transaction can only start after the first one is complete (buy->sell->buy->sell). The stock prices throughout the day are represented in the form of an array of prices. 

Given an array price of size n, find out the maximum profit that a share trader could have made.

Example 1:

Input:
n = 6
prices[] = {10,22,5,75,65,80}
Output:
87
Explanation:
Trader earns 87 as sum of 12, 75 Buy at 10, sell at 22, Buy at 5 and sell at 80.

Create an array say profit of size n and initialize all its value to 0.
Iterate price[] from right(n-1) to left(0) and update profit[i] such that profit[i] stores maximum profit achievable from one transaction in sub-array price[i..n-1].
Iterate price[] from left to right and update profit[i] such that profit[i] stores maximum profit such that profit[i] contains maximum achievable profit from two transactions in sub-array price[0..i].
After completing the iteration print value of profit[n-1].

TC : O(n)
SC : O(n)
```

```java
 public static int maxProfit(int n, int[] price) {
        // code here

       int[] profits = new int[n];
       int maxPrice = price[n-1];
       for(int i=n-2;i>=0;i--){
           if(maxPrice<price[i]){
               maxPrice = price[i];
           }
           profits[i] = Math.max(profits[i+1], maxPrice - price[i]);
       }

       int minPrice = price[0];
       for(int i=1;i<n;i++){
           if(minPrice>price[i]){
               minPrice = price[i];
           }
           profits[i] = Math.max(profits[i-1], profits[i]+ (price[i]- minPrice));
       }
        return profits[n-1];
    }
```

### Trapping Rain Water
```text
42. Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 
Idea to use in both brute force and optimal solution is 
1. find left highest bar and right highest bar
2. find the minimum of two as the bar can contain the water of minimum height only
3. while traversing deduct the arr[i] from minimum of left highest bar and right highest bar store in in trappedWater
4. add the trappedwater to result.
 
```
```java
// TC : O(n2)
// SC 0(1)
    static long trappingWater(int arr[], int n) {
        // naive solution
        // find the lb, rb
        // find minimum water level min(lb,rb)
        // find trapped water tw = wl height[i]
        // sum up the trapped water
        int res =0;
        for(int i =1;i<n-1;i++){
            int lb = arr[i];
            // find the greatest left bar on left side
            for(int j =0;j<i;j++){
                if(arr[j]>lb){
                    lb = arr[j];
                }
            }
            // find the greatest right bar on right side
            int rb = arr[i];
            for(int j = i+1;j<n;j++){
                if(arr[j]>rb){
                    rb = arr[j];
                }
            }
            int wl = Math.min(lb, rb);
            int tw = wl-arr[i];
            res += tw;
        }
        return res;
    }
    
// TC : O(n)
// SC 0(n)
static long trap(int arr[], int n){
    int[] left = new int[n];
    int[] right = new int[n];

    left[0] = arr[0];
    for(int i=1; i<n; i++){
        left[i] = Math.max(left[i-1],arr[i]);
    }

    right[n-1] =arr[n-1];
    for(int i=n-2; i>=0; i--){
        right[i] = Math.max(right[i+1],arr[i]);
    }

    long ans = 0;
    for(int i=0; i<n; i++){
        ans += Math.min(left[i],right[i])-arr[i];
    }
    return ans;
}
```
### Largest Sum Contiguous Subarray
```text
Kadane’s Algorithm : Largest Sum Contiguous Subarray
Approach:

The idea of Kadane’s algorithm is to maintain a variable max_ending_here that stores the maximum sum contiguous subarray ending at current index and a variable max_so_far stores the maximum sum of contiguous subarray found so far, Everytime there is a positive-sum value in max_ending_here compare it with max_so_far and update max_so_far if it is greater than max_so_far.

So the main Intuition behind Kadane’s Algorithm is, 

The subarray with negative sum is discarded (by assigning max_ending_here = 0 in code).
We carry subarray till it gives positive sum.

{-2,-3,4,-1,-2,1,5,-3}
```
![img_1.png](..%2F..%2F..%2F..%2F.gitbook%2Fassets%2Fimg_1.png)

```java
long maxSubarraySum(int arr[], int n){
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
```
### Maximum circular subarray sum
```text
Maximum circular subarray sum
Given a circular array of size n, find the maximum subarray sum of the non-empty subarray.

Examples: 

Input: arr[] = {8, -8, 9, -9, 10, -11, 12}
Output: 22 
Explanation: Subarray 12, 8, -8, 9, -9, 10 gives the maximum sum, that is 22.

Input: arr[] = {10, -3, -4, 7, 6, 5, -4, -1} 
Output:  23 
Explanation: Subarray 7, 6, 5, -4, -1, 10 gives the maximum sum, that is 23.

The naive solution is to use two loops to find the maximum sum but TC is O(n*n)
Maximum Circular Subarray Sum using Kadane’s Algorithm:
The idea is to modify Kadane’s algorithm to find a minimum contiguous subarray sum and the maximum contiguous
subarray sum, then check for the maximum value between the max_value and the value left after subtracting min_value
from the total sum.

Follow the steps below to solve the given problem:

We will calculate the total sum of the given array.
We will declare the variable curr_max, max_so_far, curr_min, min_so_far as the first value of the array.
Now we will use Kadane’s Algorithm to find the maximum subarray sum and minimum subarray sum.
Check for all the values in the array:- 
If min_so_far is equaled to sum, i.e. all values are negative, then we return max_so_far.
Else, we will calculate the maximum value of max_so_far and (sum – min_so_far) and return it.
```
```java
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

    
    // TC O(n)
   // SC O(1)
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
```
### Longest Even Odd Subarray
```text
Longest Even Odd Subarray
Problem Statement: Given an array of N integers, find the length of the longest alternating even-odd subarray present in the array.
Example 1:
Input: arr[]={1, 2, 3, 4, 5, 7, 9}
Output: 5
Explanation: The longest subarray with alternate even odd subarray is {1,2,3,4,5}

Example 2:
Input: arr[]={2,3,4,6,10}
Output: 3
Explanation: The longest subarray with alternate even odd subarray is {2,3,4}

Naive Approach:
In this approach, we find the longest even-odd or odd-even subarray for every element and later find the 
maximum length of them.

We iterate through the array and for every element we again iterate through the array to find the subarray size.
Later we find the maximum length 

The following code explains this approach in a better way
Time complexity: O(n^2)

Space complexity: O(1)

Optimal Approach: Simple Mathematics and Kadane’s Algorithm
Everyone knows that the sum of two even/odd numbers is even but the sum of an even and an odd number is odd.

We iterate through the array starting from the 2nd element and check if the sum of the previous element and current element is odd, we increment the size of the subarray, or else we choose the new subarray.

This approach is very similar to our second approach.

The following diagram explains the above approach

https://takeuforward.org/data-structure/longest-even-odd-subarray/
```

```java
// brute
static void evenodd_naive(int arr[]) {
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
        int count = 1;
        for (int j = i + 1; j < arr.length; j++) {
            if ((arr[j - 1] % 2 == 0 && arr[j] % 2 != 0) || (arr[j - 1] % 2 != 0 && arr[j]
                    % 2 == 0)) {
                count++;
            } else break;
        }
        ans = Math.max(ans, count);
    }

    System.out.println("The length of the longest even-odd subarray is "+ans);
}
// optimal
 static void evenodd_mathKadane(int arr[]) {
     int ans = 0;
     int count = 1;
     for (int i = 1; i < arr.length; i++) {
       if ((arr[i - 1] + arr[i]) % 2 != 0) { // extending the same subarray
         count++;
         ans = Math.max(ans, count);
       } else count = 1; // choosing the new subarray
     }
     System.out.println("The length of the longest even-odd subarray is "+ans);
   }
```