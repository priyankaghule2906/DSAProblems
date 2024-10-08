* Sliding Window Maximum

### Sliding Window Maximum
```text
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
Return the max sliding window.
Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

```text
Consider following examples for solving the problem

[1,2,3,4] k = 3
[1,1,1,1,1,1,4,6] k = 6
[8,7,6,9] k =2

Brute for solution is to run two loops and find the max element for each k window O(n*n)

Optimal solution is to use deque.
Rule 1: we always maintain elements in deque in decreasing/ descending order
Rule 2 : We always maintain the bound for window k
```
