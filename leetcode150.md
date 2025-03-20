# Merge Sorted Array

## Problem Statement
You are given two integer arrays `nums1` and `nums2`, sorted in non-decreasing order, and two integers `m` and `n`, representing the number of elements in `nums1` and `nums2` respectively.

Merge `nums1` and `nums2` into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array `nums1`. To accommodate this, `nums1` has a length of `m + n`, where the first `m` elements denote the elements that should be merged, and the last `n` elements are set to `0` and should be ignored. `nums2` has a length of `n`.

### Example 1:
#### **Input:**
```java
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6], n = 3
```
#### **Output:**
```java
[1,2,2,3,5,6]
```

### Example 2:
#### **Input:**
```java
nums1 = [1], m = 1
nums2 = [], n = 0
```
#### **Output:**
```java
[1]
```

### Example 3:
#### **Input:**
```java
nums1 = [0], m = 0
nums2 = [1], n = 1
```
#### **Output:**
```java
[1]
```

## **Intuition**
Since both arrays are already sorted, we can use the **Two Pointers** technique to merge them efficiently.

### **Key Observations:**
1. `nums1` has extra space at the end, so merging should be done **from the back to the front** to avoid unnecessary shifting.
2. Use three pointers:
    - `i = m - 1` (last valid element in `nums1`)
    - `j = n - 1` (last element in `nums2`)
    - `k = m + n - 1` (last position in `nums1`)
3. Compare elements from `nums1[i]` and `nums2[j]`, placing the **larger** one at `nums1[k]` and moving the pointers accordingly.
4. If any elements remain in `nums2`, copy them directly to `nums1`.

## **Java Solution**
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Last valid element in nums1
        int j = n - 1; // Last element in nums2
        int k = m + n - 1; // Last position in nums1

        // Merge from the back
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // Copy remaining elements from nums2, if any
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
```

## **Complexity Analysis**
- **Time Complexity:** `O(m + n)`, since each element is processed once.
- **Space Complexity:** `O(1)`, as the merging is done in-place without extra storage.

## **Edge Cases Considered**
1. `m = 0, n > 0`: `nums1` is empty, so just copy `nums2` into `nums1`.
2. `n = 0`: `nums2` is empty, so `nums1` remains unchanged.
3. `nums1` and `nums2` have completely distinct elements.
4. `nums2` elements are all smaller or all larger than `nums1` elements.

By using **Two Pointers** from the end, we achieve an efficient and optimal merging process!

# Remove Element

## Problem Statement
Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in `nums` **in-place**. The order of elements may change. Then return the number of elements in `nums` which are **not equal** to `val`.

The function should:
- Modify the `nums` array such that the first `k` elements contain values **not equal** to `val`.
- The remaining elements can be left as they are.
- Return `k`, which is the count of elements not equal to `val`.

### **Example 1:**
**Input:**
```java
nums = [3,2,2,3], val = 3
```
**Output:**
```java
2, nums = [2,2,_,_]
```
Explanation: The first two elements are `2`. The order of these two elements may vary.

### **Example 2:**
**Input:**
```java
nums = [0,1,2,2,3,0,4,2], val = 2
```
**Output:**
```java
5, nums = [0,1,4,0,3,_,_,_]
```
Explanation: The first five elements are `[0,1,4,0,3]`, and order does not matter.

---

## **Intuition**
The problem requires modifying the array in-place, meaning we can't use extra space. The best approach is to use the **two-pointer technique**:
- **`j` (slow pointer):** Keeps track of the position to place valid elements.
- **`i` (fast pointer):** Iterates through the array.
- When `nums[i] != val`, copy it to `nums[j]` and increment `j`.
- Finally, return `j`, which represents the count of elements not equal to `val`.

---

## **Java Code**
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int j = 0; // Pointer for placing valid elements
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i]; // Move valid element forward
                j++; // Increase valid count
            }
        }
        return j; // Return new length
    }
}
```

---

## **Time & Space Complexity**
- **Time Complexity:** `O(n)` (We traverse the array once)
- **Space Complexity:** `O(1)` (We modify the array in-place, using no extra memory)

---

## **Dry Run**
### **Example 1: nums = [3,2,2,3], val = 3**
| `i` | `nums[i]` | `j` (Updated) | Modified `nums` |
|----|---------|---------|----------------|
| 0  | 3       | No change  | `[3,2,2,3]` |
| 1  | 2       | `nums[0] = 2` | `[2,2,2,3]` |
| 2  | 2       | `nums[1] = 2` | `[2,2,2,3]` |
| 3  | 3       | No change  | `[2,2,2,3]` |

**Output:**
```java
k = 2, nums = [2,2,_,_]
```

### **Example 2: nums = [0,1,2,2,3,0,4,2], val = 2**
| `i` | `nums[i]` | `j` (Updated) | Modified `nums` |
|----|---------|---------|----------------|
| 0  | 0       | `nums[0] = 0` | `[0,1,2,2,3,0,4,2]` |
| 1  | 1       | `nums[1] = 1` | `[0,1,2,2,3,0,4,2]` |
| 2  | 2       | No change  | `[0,1,2,2,3,0,4,2]` |
| 3  | 2       | No change  | `[0,1,2,2,3,0,4,2]` |
| 4  | 3       | `nums[2] = 3` | `[0,1,3,2,3,0,4,2]` |
| 5  | 0       | `nums[3] = 0` | `[0,1,3,0,3,0,4,2]` |
| 6  | 4       | `nums[4] = 4` | `[0,1,3,0,4,0,4,2]` |
| 7  | 2       | No change  | `[0,1,3,0,4,0,4,2]` |

**Output:**
```java
k = 5, nums = [0,1,3,0,4,_,_,_]
```

---

# Problem Statement

Given an integer array `nums` sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in `nums`.

Consider the number of unique elements of `nums` to be `k`. To get accepted, you need to do the following things:

- Change the array `nums` such that the first `k` elements of `nums` contain the unique elements in the order they were present in `nums` initially.
- The remaining elements of `nums` are not important.
- Return `k`.

### Example 1:
**Input:** nums = [1,1,2]  
**Output:** 2, nums = [1,2,_]  
**Explanation:** Your function should return `k = 2`, with the first two elements of `nums` being `1` and `2` respectively. It does not matter what you leave beyond the returned `k` (hence they are underscores).

### Example 2:
**Input:** nums = [0,0,1,1,1,2,2,3,3,4]  
**Output:** 5, nums = [0,1,2,3,4,_,_,_,_,_]  
**Explanation:** Your function should return `k = 5`, with the first five elements of `nums` being `0, 1, 2, 3, and 4` respectively.

### Constraints:
- `1 <= nums.length <= 3 * 10^4`
- `-100 <= nums[i] <= 100`
- `nums` is sorted in non-decreasing order.

---

# Approach

We use the **Two Pointers** technique to solve this problem in **O(n)** time complexity. Since the array is sorted, duplicates will always be adjacent.

1. Use a pointer `i` to track the position of unique elements.
2. Iterate over the array using a pointer `j`.
3. If `nums[j]` is different from `nums[i]`, increment `i` and set `nums[i] = nums[j]`.
4. Finally, return `i + 1` as the number of unique elements.

---

# Java Implementation
```java
public class RemoveDuplicatesSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        
        int i = 0; // Pointer for the unique elements
        
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) { // Found a new unique element
                i++;
                nums[i] = nums[j];
            }
        }
        
        return i + 1; // Number of unique elements
    }
}
```

---

# Dry Run

### Input: `nums = [0,0,1,1,1,2,2,3,3,4]`

| Step | `i` | `j` | `nums` (After Modification) |
|------|----|----|--------------------------------|
| 1    | 0  | 1  | `[0,0,1,1,1,2,2,3,3,4]`       |
| 2    | 1  | 2  | `[0,1,1,1,1,2,2,3,3,4]`       |
| 3    | 2  | 5  | `[0,1,2,1,1,2,2,3,3,4]`       |
| 4    | 3  | 7  | `[0,1,2,3,1,2,2,3,3,4]`       |
| 5    | 4  | 9  | `[0,1,2,3,4,2,2,3,3,4]`       |

### Output: `5, nums = [0,1,2,3,4,_,_,_,_,_]`


# Remove Duplicates from Sorted Array II

## Problem Statement
Given an integer array `nums` sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, the result must be placed in the first part of the array `nums`. More formally, if there are `k` elements after removing the duplicates, then the first `k` elements of `nums` should hold the final result. It does not matter what is left beyond the first `k` elements.

Return `k` after placing the final result in the first `k` slots of `nums`.

**Constraints:**
- `1 <= nums.length <= 3 * 10^4`
- `-10^4 <= nums[i] <= 10^4`
- `nums` is sorted in non-decreasing order.

### Example 1:
```
Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
```

### Example 2:
```
Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
```

---

## Approach
1. Use **two pointers** to solve this problem in-place with `O(1)` extra space.
2. Maintain a **slow pointer (`k`)** that marks the position for the next valid number.
3. Use a **fast pointer** to iterate through the array.
4. Allow each number to appear **at most twice**:
   - If `k < 2`, always place the number.
   - If `nums[k - 2]` is not equal to the current number, place it at `nums[k]`.
   - Otherwise, skip the current number.
5. Return `k`, which represents the new length of the modified array.

---

## Java Implementation
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 0;  // Position to place the next valid number
        
        for (int num : nums) {
            // Place the number if it's the first or second occurrence
            if (k < 2 || num != nums[k - 2]) {
                nums[k] = num;
                k++;
            }
        }
        return k; // The length of the modified array
    }
}
```

---

## Complexity Analysis
- **Time Complexity:** `O(n)`, since we traverse the array once.
- **Space Complexity:** `O(1)`, as we modify the array in place without extra storage.

---

## Dry Run
### Example 1: nums = `[1,1,1,2,2,3]`
| Step | k | Current Number | Condition Check | Modified Array      |
|------|---|---------------|----------------|---------------------|
| 1    | 0 | 1             | k < 2 → Place  | `[1,_,_,_,_,_]`     |
| 2    | 1 | 1             | k < 2 → Place  | `[1,1,_,_,_,_]`     |
| 3    | 2 | 1             | nums[k-2] == 1 (skip) | `[1,1,_,_,_,_]` |
| 4    | 2 | 2             | nums[k-2] ≠ 2 → Place | `[1,1,2,_,_,_]` |
| 5    | 3 | 2             | nums[k-2] ≠ 2 → Place | `[1,1,2,2,_,_]` |
| 6    | 4 | 3             | nums[k-2] ≠ 3 → Place | `[1,1,2,2,3,_]` |
| **Result** | **5** | | | **Output: `[1,1,2,2,3,_]`** |

### Example 2: nums = `[0,0,1,1,1,1,2,3,3]`
| Step | k | Current Number | Condition Check | Modified Array |
|------|---|---------------|----------------|----------------|
| 1    | 0 | 0             | k < 2 → Place  | `[0,_,_,_,_,_,_,_,_]` |
| 2    | 1 | 0             | k < 2 → Place  | `[0,0,_,_,_,_,_,_,_]` |
| 3    | 2 | 1             | nums[k-2] ≠ 1 → Place | `[0,0,1,_,_,_,_,_,_]` |
| 4    | 3 | 1             | nums[k-2] ≠ 1 → Place | `[0,0,1,1,_,_,_,_,_]` |
| 5    | 4 | 1             | nums[k-2] == 1 (skip) | `[0,0,1,1,_,_,_,_,_]` |
| 6    | 4 | 1             | nums[k-2] == 1 (skip) | `[0,0,1,1,_,_,_,_,_]` |
| 7    | 4 | 2             | nums[k-2] ≠ 2 → Place | `[0,0,1,1,2,_,_,_,_]` |
| 8    | 5 | 3             | nums[k-2] ≠ 3 → Place | `[0,0,1,1,2,3,_,_,_]` |
| 9    | 6 | 3             | nums[k-2] ≠ 3 → Place | `[0,0,1,1,2,3,3,_,_]` |
| **Result** | **7** | | | **Output: `[0,0,1,1,2,3,3,_,_]`** |

---

### Majority Element

```text
Naive Approach: 
The basic solution is to have two loops and keep track of the maximum count for all different elements. 
If the maximum count becomes greater than n/2 then break the loops and return the element having the maximum count. If the maximum count doesn’t become more than n/2 then the majority element doesn’t exist.

Moore's Voting Algorithm is an efficient algorithm used to find the majority element in an array, i.e., an element that appears more than N/2 times, where N is the size of the array. 
The algorithm was proposed by Robert S. Moore in 1981.

Here's how Moore's Voting Algorithm works:

* Finding a Potential Candidate:
1. The algorithm iterates through the array, maintaining a candidate variable initialized to the first element of the array.
2. It also maintains a count variable initialized to 1.
3. For each subsequent element in the array, if the element is equal to the current candidate, the count is incremented. Otherwise, the count is decremented.
   - If the count becomes zero, the current element becomes the new candidate, and the count is reset to 1.
   - At the end of this process, the candidate variable holds a potential majority element.
Verifying the Candidate:
- Once a potential candidate is found, the algorithm verifies if it is indeed the majority element by counting its occurrences in the array.
- If the count of the candidate in the array is greater than N/2, where N is the size of the array, then the candidate is considered the majority element.

The key insight behind Moore's Voting Algorithm is that if a majority element exists in the array, it will cancel out the occurrences of all other elements.
As a result, the remaining candidate (if any) after the algorithm completes is likely to be the majority element.

One of the main advantages of Moore's Voting Algorithm is its efficiency, as it requires only a single pass through the array with constant space complexity.
```
```java
static int majorityElement(int a[], int size)
    {
        int element = a[0];
        int count = 1;
        for(int i=1;i<size;i++){
            if(element == a[i]){
                count++;
            } else {
                count--;
                
                if(count == 0){
                    element = a[i];
                    count = 1;
                }
            }
        }   
        count =0;
        for(int i=0;i<size;i++){
            if(a[i]==element){
                count++;
            }
        }
        if(count > size/2){
            return element;
        }
        return -1;
    }
```

# Rotate Array (Leetcode 189)

## Problem Statement
Given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.

### Example 1:
**Input:**
```java
nums = [1,2,3,4,5,6,7], k = 3
```
**Output:**
```java
[5,6,7,1,2,3,4]
```
**Explanation:**
- Rotate 1 step to the right: `[7,1,2,3,4,5,6]`
- Rotate 2 steps to the right: `[6,7,1,2,3,4,5]`
- Rotate 3 steps to the right: `[5,6,7,1,2,3,4]`

### Example 2:
**Input:**
```java
nums = [-1,-100,3,99], k = 2
```
**Output:**
```java
[3,99,-1,-100]
```
**Explanation:**
- Rotate 1 step to the right: `[99,-1,-100,3]`
- Rotate 2 steps to the right: `[3,99,-1,-100]`

### Constraints:
- `1 <= nums.length <= 10^5`
- `-2^31 <= nums[i] <= 2^31 - 1`
- `0 <= k <= 10^5`

## Approaches
### **Approach 1: Using Extra Array (O(n) time, O(n) space)**
- Create a new array of the same length as `nums`.
- Place each element at its new position `(i + k) % n`.
- Copy the new array back to `nums`.

#### **Java Code:**
```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k > n
        int[] temp = new int[n];
        
        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }
        
        // Copy back to original array
        System.arraycopy(temp, 0, nums, 0, n);
    }
}
```
- **Time Complexity:** `O(n)`
- **Space Complexity:** `O(n)`

---

### **Approach 2: In-Place Reversal (O(n) time, O(1) space)**
- Reverse the whole array.
- Reverse the first `k` elements.
- Reverse the remaining elements.

#### **Java Code:**
```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k > n

        reverse(nums, 0, n - 1);     // Reverse entire array
        reverse(nums, 0, k - 1);     // Reverse first k elements
        reverse(nums, k, n - 1);     // Reverse remaining elements
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
```
- **Time Complexity:** `O(n)`
- **Space Complexity:** `O(1)` (in-place)

---

### **Approach 3: Cyclic Replacements (O(n) time, O(1) space)**
- Move elements in cycles based on their new position.
- Track visited indices to avoid repeating.

#### **Java Code:**
```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k > n
        int count = 0; // Number of elements placed correctly

        for (int start = 0; count < n; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
}
```
- **Time Complexity:** `O(n)` (Each element is moved exactly once)
- **Space Complexity:** `O(1)` (In-place)

---

## **Comparison of Approaches**
| Approach | Time Complexity | Space Complexity | Notes |
|----------|---------------|----------------|-------|
| Extra Array | `O(n)` | `O(n)` | Simple but requires extra space |
| Reverse Array | `O(n)` | `O(1)` | Best for in-place rotation |
| Cyclic Replacements | `O(n)` | `O(1)` | Efficient but harder to understand |

For interviews, **Approach 2 (Reversal Method)** is preferred because it’s simple and uses `O(1)` space.





# Best Time to Buy and Sell Stock II

## Problem Statement
You are given an integer array `prices` where `prices[i]` is the price of a given stock on the `ith` day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it and then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

### Examples

**Example 1:**
```
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: 
Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
```

**Example 2:**
```
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: 
Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
```

**Example 3:**
```
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: 
There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
```

### Constraints
- `1 <= prices.length <= 3 * 10^4`
- `0 <= prices[i] <= 10^4`

---

## Approach 1: Greedy Approach

### Intuition
- The best way to maximize the profit is to add up all the positive price differences between consecutive days.
- If the price goes up the next day, we can sell the stock and gain the profit.

### Algorithm
1. Initialize `maxProfit = 0`
2. Traverse through the array from day 1 to day `n-1`
3. If `prices[i] > prices[i-1]`, add `prices[i] - prices[i-1]` to `maxProfit`
4. Return `maxProfit`

### Java Solution
```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }
}
```

### Dry Run

**Input:** [7,1,5,3,6,4]

| Day | Price | Profit Calculation       | Total Profit |
|------|--------|--------------------------|--------------|
| 1    | 7      | -                        | 0            |
| 2    | 1      | -                        | 0            |
| 3    | 5      | 5 - 1 = 4                | 4            |
| 4    | 3      | -                        | 4            |
| 5    | 6      | 6 - 3 = 3                | 7            |
| 6    | 4      | -                        | 7            |

---

## Complexity Analysis
- **Time Complexity:** \( O(n) \) where `n` is the number of days. We traverse the prices array once.
- **Space Complexity:** \( O(1) \) since we only use constant space for variables.

---

## Approach 2: Peak Valley Approach

### Intuition
- Identify the valleys (local minima) and peaks (local maxima) and sum up the difference between peaks and valleys to maximize the profit.
- Every time the price goes down, we consider it a valley, and every time the price goes up, we treat it as a peak.

### Algorithm
1. Initialize `profit = 0`, `i = 0`, and `n = prices.length`
2. While `i < n - 1`:
   - Find the next valley: While `i < n-1` and `prices[i] >= prices[i+1]`, increment `i`
   - Set `valley = prices[i]`
   - Find the next peak: While `i < n-1` and `prices[i] <= prices[i+1]`, increment `i`
   - Set `peak = prices[i]`
   - Add the difference `(peak - valley)` to `profit`
3. Return `profit`

### Java Solution
```java
class Solution {
    public int maxProfit(int[] prices) {
        int i = 0;
        int n = prices.length;
        int profit = 0;
        while (i < n - 1) {
            // Find the valley
            while (i < n - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int valley = prices[i];
            
            // Find the peak
            while (i < n - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            int peak = prices[i];
            
            profit += peak - valley;
        }
        return profit;
    }
}
```

### Complexity Analysis
- **Time Complexity:** \( O(n) \) since we traverse the prices array once.
- **Space Complexity:** \( O(1) \) as we are not using any extra space.

---

## Conclusion
- The greedy approach is simple and works efficiently for this problem.
- It ensures maximum profit by taking advantage of all upward price trends.
- The Peak Valley approach is a more intuitive way to think about the problem, but the greedy solution is easier to implement in code.

