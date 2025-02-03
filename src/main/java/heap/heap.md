# Smallest Number in Infinite Set

## Problem Statement

You have a set which contains all positive integers `[1, 2, 3, 4, 5, ...]`.
Implement the `SmallestInfiniteSet` class with the following operations:

* `SmallestInfiniteSet()` - Initializes the SmallestInfiniteSet object to contain all positive integers.
* `int popSmallest()` - Removes and returns the smallest integer contained in the infinite set.
* `void addBack(int num)` - Adds a positive integer num back into the infinite set, if it is not already in the infinite set.

### Example:
```
Input
["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
[[], [2], [], [], [], [1], [], [], []]
Output
[null, null, 1, 2, 3, null, 1, 4, 5]

Explanation:
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and is the smallest number
smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.
```

### Constraints:
* `1 <= num <= 1000`
* At most `1000` calls will be made in total to `popSmallest` and `addBack`.

## Intuition

The key insights for solving this problem are:
1. We need to track which numbers have been removed from the infinite set
2. The smallest available number will either be:
    - A previously removed number that was added back, or
    - The smallest number that was never removed
3. We can optimize by only tracking "added back" numbers that are smaller than our current smallest untouched number

## Brute Force Solution

A simple approach would be to use a boolean array to track the state of each number:

```java
class SmallestInfiniteSet {
    private boolean[] numbers;
    
    public SmallestInfiniteSet() {
        // Initialize array with all numbers present (true)
        numbers = new boolean[1001];  // 1-based indexing
        Arrays.fill(numbers, true);
    }
    
    public int popSmallest() {
        // Find first true value
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i]) {
                numbers[i] = false;  // Remove number
                return i;
            }
        }
        return -1;  // Should never happen given constraints
    }
    
    public void addBack(int num) {
        numbers[num] = true;
    }
}
```

### Time Complexity:
- Constructor: O(N) where N is the size of array
- popSmallest(): O(N) - need to scan array
- addBack(): O(1)

### Space Complexity:
- O(N) where N is the maximum number we need to track (1000 in this case)

### Problems with Brute Force:
1. Not memory efficient - need to maintain array for all possible numbers
2. Linear time for popSmallest() operation
3. Doesn't scale well if number range increases

## Optimal Solution

We can optimize using a PriorityQueue for added back numbers and a counter for the current smallest untouched number:

```java
class SmallestInfiniteSet {
    private PriorityQueue<Integer> addedBack;
    private HashSet<Integer> presentNumbers;
    private int currentSmallest;
    
    public SmallestInfiniteSet() {
        addedBack = new PriorityQueue<>();
        presentNumbers = new HashSet<>();
        currentSmallest = 1;
    }
    
    public int popSmallest() {
        int result;
        
        if (!addedBack.isEmpty() && addedBack.peek() < currentSmallest) {
            result = addedBack.poll();
            presentNumbers.remove(result);
        } else {
            result = currentSmallest++;
        }
        
        return result;
    }
    
    public void addBack(int num) {
        if (num < currentSmallest && !presentNumbers.contains(num)) {
            addedBack.offer(num);
            presentNumbers.add(num);
        }
    }
}
```

### Time Complexity:
- Constructor: O(1)
- popSmallest(): O(log k) where k is number of elements in addedBack
- addBack(): O(log k)

### Space Complexity:
- O(k) where k is the number of added back numbers

### Dry Run:

Initial state:
```
currentSmallest = 1
addedBack = []
presentNumbers = {}
```

Operations:
1. `addBack(2)`:
   ```
   currentSmallest = 1
   addedBack = []      // No change as 2 > currentSmallest
   presentNumbers = {}
   ```

2. `popSmallest()`:
   ```
   Returns: 1
   currentSmallest = 2
   addedBack = []
   presentNumbers = {}
   ```

3. `popSmallest()`:
   ```
   Returns: 2
   currentSmallest = 3
   addedBack = []
   presentNumbers = {}
   ```

4. `popSmallest()`:
   ```
   Returns: 3
   currentSmallest = 4
   addedBack = []
   presentNumbers = {}
   ```

5. `addBack(1)`:
   ```
   currentSmallest = 4
   addedBack = [1]        // Added as 1 < currentSmallest
   presentNumbers = {1}
   ```

6. `popSmallest()`:
   ```
   Returns: 1            // From addedBack
   currentSmallest = 4
   addedBack = []
   presentNumbers = {}
   ```

7. `popSmallest()`:
   ```
   Returns: 4
   currentSmallest = 5
   addedBack = []
   presentNumbers = {}
   ```

8. `popSmallest()`:
   ```
   Returns: 5
   currentSmallest = 6
   addedBack = []
   presentNumbers = {}
   ```

## Key Points

1. The optimal solution uses a combination of data structures:
    - PriorityQueue for maintaining sorted order of added back numbers
    - HashSet for efficient duplicate checking
    - Simple counter for tracking untouched numbers

2. We only need to track numbers that are:
    - Less than currentSmallest
    - Have been previously removed and added back

3. The solution efficiently handles both operations while maintaining the concept of an infinite set without actually storing infinite numbers.

4. The space complexity is optimal as we only store the minimum necessary information about the set's state.

# Total Cost to Hire K Workers - Problem Intuition, Java Logic, and Complexity

## Problem Intuition
The problem requires us to hire `k` workers from a given list of worker costs while minimizing the total hiring cost. We can choose workers from either end of the list but only consider `candidates` number of workers from both sides at a time. This means we must efficiently manage and update our selection pool to always pick the cheapest available worker.

### **Key Observations**
1. **Always hire the cheapest worker available** - Use a **Min-Heap (Priority Queue)** to efficiently extract the smallest cost.
2. **Maintain two heaps** - One for the leftmost `candidates` workers and one for the rightmost `candidates` workers.
3. **Gradually expand selection pool** - Once a worker is hired, add the next available worker from either end to maintain a valid selection pool.

---

## Java Logic & Approach
### **1. Initialize Two Min-Heaps**
- `leftHeap` stores the smallest `candidates` costs from the left.
- `rightHeap` stores the smallest `candidates` costs from the right.
- Maintain `left` and `right` pointers to track the range of available workers.

### **2. Pre-fill the Heaps**
- Add the first `candidates` workers to `leftHeap`.
- Add the last `candidates` workers to `rightHeap` (ensuring no overlap).

### **3. Hire `k` Workers**
- Always pick the **cheapest** worker from either `leftHeap` or `rightHeap`.
- If a worker is picked from `leftHeap`, replace them with the next available worker from `left`.
- If a worker is picked from `rightHeap`, replace them with the next available worker from `right`.
- Repeat the process `k` times to hire `k` workers.

### **Java Implementation**
```java
class Solution {
   public long totalCost(int[] costs, int k, int candidates) {
      int i = 0;
      int j = costs.length - 1;
      PriorityQueue<Integer> pq1 = new PriorityQueue<>();
      PriorityQueue<Integer> pq2 = new PriorityQueue<>();

      long ans = 0;
      while (k-- > 0) {
         while (pq1.size() < candidates && i <= j) {
            pq1.offer(costs[i++]);
         }
         while (pq2.size() < candidates && i <= j) {
            pq2.offer(costs[j--]);
         }

         int t1 = pq1.size() > 0 ? pq1.peek() : Integer.MAX_VALUE;
         int t2 = pq2.size() > 0 ? pq2.peek() : Integer.MAX_VALUE;

         if (t1 <= t2) {
            ans += t1;
            pq1.poll();
         } else {
            ans += t2;
            pq2.poll();
         }
      }
      return ans;
   }
}
```

---

## Complexity Analysis
### **Time Complexity: O(k log candidates)**
- **Heap insertions and deletions:** `O(log candidates)` (since each heap stores at most `candidates` elements).
- **Pre-filling the heaps:** `O(candidates log candidates)`.
- **Hiring process:** Runs for `k` iterations, each taking `O(log candidates)`.
- **Overall Complexity:** `O((candidates + k) log candidates)`.

### **Space Complexity: O(candidates)**
- **Two Min-Heaps:** Store `O(candidates)` elements.
- **Overall Space Complexity:** `O(candidates)`, as no additional data structures are used.

---

## Summary
- **Efficiently select `k` cheapest workers** using two **Min-Heaps**.
- **Maintain a valid candidate pool** by dynamically adding new workers.
- **Minimize the total cost** while ensuring each step runs in `O(log candidates)` time.
- **Final complexity:** `O((candidates + k) log candidates)`, making this approach optimal for large inputs.

This method ensures a balance between efficiency and correctness, making it an ideal solution to the problem.

# Maximum Subsequence Score - Detailed Explanation

## Problem Statement
You are given two 0-indexed integer arrays `nums1` and `nums2` of equal length `n` and a positive integer `k`. You must choose a subsequence of indices from `nums1` of length `k`.

For chosen indices \(i_0, i_1, ..., i_{k-1}\), your score is defined as:

\[ (nums1[i_0] + nums1[i_1] + ... + nums1[i_{k-1}]) * \min(nums2[i_0] , nums2[i_1], ... ,nums2[i_{k-1}]) \]

Return the maximum possible score.

---

## **Intuition**
- Since the score depends on both the **sum of selected elements from `nums1`** and the **minimum element from `nums2`**, we need to balance these factors.
- Sorting `nums2` in descending order allows us to process elements that provide **higher minimum values** first.
- Using a **min-heap (priority queue)** ensures that we maintain the largest possible `nums1` sum while iterating.

---

## **Approach**
1. **Pair and Sort**: Create pairs `(nums2[i], nums1[i])` and sort them in descending order of `nums2`.
2. **Use a Min-Heap**: Maintain a heap of the `k` largest `nums1` values seen so far.
3. **Iterate and Compute Score**: As we iterate, we track the sum of `k` elements and compute the score for each step.

---

## **Example Dry Run**

### **Input:**
```java
nums1 = [5, 2, 7, 4, 1], nums2 = [3, 9, 8, 2, 6], k = 2
```

### **Step 1: Pair and Sort**
We create pairs of `(nums2[i], nums1[i])` and sort them **in descending order of `nums2`**:

| nums2 | nums1 |
|--------|--------|
| 9      | 2      |
| 8      | 7      |
| 6      | 1      |
| 3      | 5      |
| 2      | 4      |

Sorted pairs:
```
[(9,2), (8,7), (6,1), (3,5), (2,4)]
```

---

### **Step 2: Iteration with Min-Heap**
We use a **min-heap** to maintain the `k=2` largest values from `nums1` and track their sum.

| Iteration | num2 | num1 (added) | Heap (nums1 values) | Sum of nums1 | Score Calculation | Max Score |
|-----------|------|-------------|----------------------|--------------|--------------------|-----------|
| 1         | 9    | 2           | [2]                  | 2            | (not enough elements) | -         |
| 2         | 8    | 7           | [2, 7]               | 9            | **9 × 8 = 72**      | 72        |
| 3         | 6    | 1           | [1, 7] (remove 2)    | 8            | **8 × 6 = 48**      | 72        |
| 4         | 3    | 5           | [5, 7] (remove 1)    | 12           | **12 × 3 = 36**     | 72        |
| 5         | 2    | 4           | [4, 7] (remove 5)    | 11           | **11 × 2 = 22**     | 72        |

---

### **Final Answer**
The **maximum score** we obtained is **72**.

---

## **Java Code Implementation**
```java
import java.util.*;

class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int size = nums1.length;
        int[][] pair = new int[size][2];

        // Create pairs (nums2[i], nums1[i]) and sort by nums2 in descending order
        for (int i = 0; i < size; i++) {
            pair[i][0] = nums1[i];  // nums1
            pair[i][1] = nums2[i];  // nums2
        }
        Arrays.sort(pair, (a, b) -> Integer.compare(b[1], a[1]));

        // Min-Heap to track k largest nums1 values
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        long sum = 0, maxScore = 0;

        for (int i = 0; i < size; i++) {
            int num1 = pair[i][0];  // nums1 value
            int num2 = pair[i][1];  // nums2 value (multiplier)

            sum += num1;
            queue.offer(num1);

            // If heap exceeds k elements, remove smallest nums1 to keep top k
            if (queue.size() > k) {
                sum -= queue.poll();
            }

            // Compute score when we have exactly k elements
            if (queue.size() == k) {
                maxScore = Math.max(maxScore, sum * num2);
            }
        }

        return maxScore;
    }
}
```

---

## **Time and Space Complexity**

| Step | Operation | Complexity |
|------|-----------|------------|
| Sorting | Sorting `pair` array | **O(n log n)** |
| Heap Operations | Pushing and popping from heap | **O(n log k)** |
| Iteration | Iterating over `nums1` | **O(n)** |
| **Total Complexity** | **O(n log n)** (Dominated by sorting) |
| Space Complexity | Extra space for pairs and heap | **O(n)** |

This method ensures that we maximize the overall score efficiently! 🚀

