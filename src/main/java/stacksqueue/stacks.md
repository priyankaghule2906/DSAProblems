* Next Greater Element
* LRU

### Next Greater Element
```text
Given an array arr[ ] of size N having elements, the task is to find the next greater element for each element of the array in order of their appearance in the array.
Next greater element of an element in the array is the nearest element on the right which is greater than the current element.
If there does not exist next greater of current element, then next greater element for current element is -1. For example, next greater of the last element is always -1.

Example 1:

Input: 
N = 4, arr[] = [1 3 2 4]
Output:
3 4 4 -1
Explanation:
In the array, the next larger element 
to 1 is 3 , 3 is 4 , 2 is 4 and for 4 ? 
since it doesn't exist, it is -1.

Brute force approach is to run 2 loops and find the next greater element update result array and break but TC 0(n*n)

next approach is using stack

Algorithm
Iterate over the array from right to left.
For each element, pop elements from the stack until you find an element greater than the current element.
The next greater element for the current element is the element at the top of the stack (if the stack is not empty).
Push the current element onto the stack.
Repeat until you process all elements in the array.
TC O(n)
SC O(n)
https://leetcode.com/problems/next-greater-element-i/submissions/1262147430/
```

```java
      public static long[] nextLargerElement(long[] arr, int n)
{
    Stack<Long> stack = new Stack<>();
    long[] result = new long[n];
    for(int i=n-1;i>=0;i--){
        //System.out.println(arr[i]);
        while (!stack.isEmpty() && stack.peek()<=arr[i]){
            stack.pop();
        }

        if(stack.isEmpty()){
            result[i] = -1;
        } else {
            result[i] = stack.peek();
        }

        stack.push(arr[i]);
    }
    return result;
}

```

### LRU
```text
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

https://takeuforward.org/plus/data-structures-and-algorithm/stack-and-queues/faqs/lru-cache/editorial 
```
![lru.png](images%2Flru.png)