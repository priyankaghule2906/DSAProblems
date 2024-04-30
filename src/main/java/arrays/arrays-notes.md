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


```text
Maximum Index
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
```text
Check if array is sorted and rotated
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

````text
Maximum occured integer in the given range
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

````text
Rearrange Array Alternately
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
        
````
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