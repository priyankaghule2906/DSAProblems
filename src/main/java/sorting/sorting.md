* insertion sort
* merge two sorted array





* insertion sort
```text
Insertion sort is a simple sorting algorithm that works by iteratively inserting each element of an unsorted list
 into its correct position in a sorted portion of the list. It is a stable sorting algorithm,
 meaning that elements with equal values maintain their relative order in the sorted output.
 
To achieve insertion sort, follow thes steps:

* We have to start with second element of the array as first element in the array is assumed to be sorted.
* Compare second element with the first element and check if the second element is smaller then swap them.
* Move to the third element and compare it with the second element, then the first element and swap as necessary to put it in the correct position among the first three elements.
* Continue this process, comparing each element with the ones before it and swapping as needed to place it in the correct position among the sorted elements.
* Repeat until the entire array is sorted.
```
![insertion_sort.png](..%2F..%2F..%2F..%2F.gitbook%2Fassets%2Finsertion_sort.png)

```java
 public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

```
```text
Time Complexity of Insertion Sort
Best case: O(n) , If the list is already sorted, where n is the number of elements in the list.
Average case: O(n 2 ) , If the list is randomly ordered
Worst case: O(n 2 ) , If the list is in reverse order
Space Complexity of Insertion Sort
Auxiliary Space: O(1), Insertion sort requires O(1) additional space, making it a space-efficient sorting algorithm.
```


* merge two sorted array
```text
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

 

Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
```
```text
Approach : Two Pointer
We can start with two pointers i and j, initialized to m-1 and n-1, respectively. We will also have another pointer k initialized to m+n-1, which will be used to keep track of the position in nums1 where we will be placing the larger element. Then we can start iterating from the end of the arrays i and j, and compare the elements at these positions. We will place the larger element in nums1 at position k, and decrement the corresponding pointer i or j accordingly. We will continue doing this until we have iterated through all the elements in nums2. If there are still elements left in nums1, we don't need to do anything because they are already in their correct place.

Complexity
Time complexity: O(m+n)
We are iterating through both arrays once, so the time complexity is O(m+n).

Space complexity: O(1)
We are not using any extra space, so the space complexity is O(1)
```
```java
// https://leetcode.com/problems/merge-sorted-array/submissions/
public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m+n-1;
        int i=m-1;
        int j=n-1;
        while(j>=0) {
            if(i>=0 && nums1[i]> nums2[j]){
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }      
    }
```