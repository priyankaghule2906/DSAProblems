# **Letter Combinations of a Phone Number**

## **Problem Statement**
Given a string containing digits from **2-9** inclusive, return all possible letter combinations that the number could represent. The mapping of digits to letters follows a standard telephone keypad.

Return the answer in **any order**.

### **Mapping of digits to letters:**
```
2 -> "abc"
3 -> "def"
4 -> "ghi"
5 -> "jkl"
6 -> "mno"
7 -> "pqrs"
8 -> "tuv"
9 -> "wxyz"
```

### **Example 1:**
#### **Input:**
```
digits = "23"
```
#### **Output:**
```
["ad","ae","af","bd","be","bf","cd","ce","cf"]
```

### **Example 2:**
#### **Input:**
```
digits = ""
```
#### **Output:**
```
[]
```

### **Example 3:**
#### **Input:**
```
digits = "2"
```
#### **Output:**
```
["a","b","c"]
```

---

## **Approach**
We use **backtracking** to generate all possible letter combinations.

### **Steps:**
1. **Define a mapping** from digits to letters.
2. **Use recursion (backtracking)** to generate all possible combinations.
3. If the current combination has the same length as the input string, **store it in the result list**.
4. Iterate over the letters mapped to the current digit and recursively build the next combination.

---

## **Java Solution**

```java
import java.util.*;

public class LetterCombinations {
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;

        // Mapping digits to letters
        String[] phoneMap = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        // Backtracking function
        backtrack(digits, 0, new StringBuilder(), result, phoneMap);
        return result;
    }

    private static void backtrack(String digits, int index, StringBuilder current, List<String> result, String[] phoneMap) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = phoneMap[digits.charAt(index) - '0'];
        for (char letter : letters.toCharArray()) {
            current.append(letter);
            backtrack(digits, index + 1, current, result, phoneMap);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23")); // Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
        System.out.println(letterCombinations("2")); // Output: ["a", "b", "c"]
        System.out.println(letterCombinations("")); // Output: []
    }
}
```

---

## **Complexity Analysis**

- **Time Complexity:** O(3ⁿ × 4ᵐ), where `n` is the number of digits that map to 3 letters (2,3,4,5,6,8) and `m` is the number of digits that map to 4 letters (7,9). In the worst case, every digit contributes to multiple combinations.
- **Space Complexity:** O(3ⁿ × 4ᵐ) for storing the results.

---

## **Dry Run with Recursion Tree**
### **Input:** `digits = "293"`

```
                           ""
                          / | \  (2 -> "abc")
                         a  b  c
                      / / \ \ \ \ \  (9 -> "wxyz")
                     aw ax ay az bw bx by bz cw cx cy cz
                   /  |  |  |  |  |  |  |  |  |  |  |  |  (3 -> "def")
              awd awe awf axd axe axf ... czd cze czf
```

### **Final Output:**
```
["awd", "awe", "awf", "axd", "axe", "axf", "ayd", "aye", "ayf", "azd", "aze", "azf",
 "bwd", "bwe", "bwf", "bxd", "bxe", "bxf", "byd", "bye", "byf", "bzd", "bze", "bzf",
 "cwd", "cwe", "cwf", "cxd", "cxe", "cxf", "cyd", "cye", "cyf", "czd", "cze", "czf"]
```

---

### **Key Takeaways:**
- **Backtracking** is an efficient way to generate all possible combinations.
- The recursion tree helps visualize how different combinations are generated.
- **Iterative solutions using BFS (Queue) are possible but require more memory.**
- The problem has an **exponential complexity** but is manageable since the input is small (max length = 4).



# **Letter Combinations of a Phone Number**Combination Sum III

Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.



Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.
Example 3:

Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.

Constraints:
2 <= k <= 9
1 <= n <= 60


# Combination Sum III - Recursion Approach

## Problem Statement
Find all valid combinations of `k` numbers that sum up to `n` such that:
- Only numbers `1` through `9` are used.
- Each number is used **at most once**.
- The list must not contain duplicate combinations, and order does not matter.

### Constraints:
- `2 <= k <= 9`
- `1 <= n <= 60`

---

## Approach
We use **Backtracking** to explore all possible combinations of numbers from `1` to `9` while ensuring:
- We use exactly `k` numbers.
- The sum equals `n`.
- Each number is used at most once.

### Steps:
1. **Recursive Backtracking Function:**
    - Maintain a list `temp` to store the current combination.
    - Track the **current sum** and the **remaining count (`k`)**.
    - If `temp.size() == k` and the sum matches `n`, add it to the result.
    - Stop if the sum exceeds `n`.
    - Explore numbers from `1` to `9`, ensuring each number is used only once.

2. **Optimization:**
    - If the sum exceeds `n`, terminate that branch early.
    - Use a loop to explore numbers, avoiding duplicates.

---

## Java Implementation
```java
import java.util.*;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> temp, int k, int target, int start) {
        if (temp.size() == k && target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        
        for (int i = start; i <= 9; i++) {
            if (target < i) break; // Stop if the number is greater than the remaining sum
            
            temp.add(i);
            backtrack(result, temp, k, target - i, i + 1);
            temp.remove(temp.size() - 1); // Undo choice for backtracking
        }
    }

    public static void main(String[] args) {
        CombinationSumIII solution = new CombinationSumIII();
        System.out.println(solution.combinationSum3(3, 7)); // [[1,2,4]]
        System.out.println(solution.combinationSum3(3, 9)); // [[1,2,6], [1,3,5], [2,3,4]]
        System.out.println(solution.combinationSum3(4, 1)); // []
    }
}
```

### Complexity Analysis
- **Time Complexity:** `O(2^9)`, as we explore subsets recursively.
- **Space Complexity:** `O(k)`, due to recursion depth and temporary storage.

---

## Recursion Trees

### Example 1: k = 3, n = 7
We need to find 3 numbers that sum to 7 using numbers 1 to 9.

#### Recursion Tree:
```
                            []
                            |
         -------------------------------------
         |           |          |           |
        [1]        [2]        [3]         [4] (and so on...)
         |
  ---------------      
  |      |      |      
 [1,2] [1,3]  [1,4]   
  |                   
 [1,2,3] (sum=6, continue)
 [1,2,4] ✅ (sum=7, valid)
 [1,2,5] (sum=8, stop)
```
The only valid combination is `[[1,2,4]]`.

---

### Example 2: k = 3, n = 9
We need to find 3 numbers that sum to 9.

#### Recursion Tree:
```
                            []
                            |
         -------------------------------------
         |           |          |           |
        [1]        [2]        [3]         [4] (and so on...)
         |
  ---------------      
  |      |      |      
 [1,2] [1,3]  [1,4]   
  |        |       |
 [1,2,3]  [1,3,4]  [1,4,5]
  |        |       |
 [1,2,4]  [1,3,5] ✅  [1,4,6] (sum > 9, stop)
 [1,2,5]  [1,3,6] (sum > 9, stop)
 [1,2,6] ✅ (sum=9, valid)

 Other valid branches:
 [2,3,4] ✅ (sum=9, valid)
```
Valid combinations: `[[1,2,6], [1,3,5], [2,3,4]]`.

---

### Example 3: k = 4, n = 1
We need to find 4 numbers that sum to 1. The smallest sum using 4 different numbers is `1+2+3+4=10`, which is already too large.

#### Recursion Tree:
```
                            []
                            |
         -------------------------------------
         |           |          |          
        [1]        [2]        [3]   (each number is already too large)
         |
    [1,2] (sum=3, already too large)
```
Since it's impossible to find 4 numbers that sum to 1, the result is `[]`.

---

## Conclusion
Each recursion tree follows a depth-first search (DFS) pattern where:
- Each level corresponds to a choice of numbers.
- Pruning occurs when the sum exceeds `n`.
- Valid results are collected when exactly `k` numbers sum to `n`.



199. 
200. House Robber

```text
I'll explain the House Robber problem with a detailed solution using both recursive and dynamic programming approaches.

First, let's solve using Dynamic Programming with detailed explanation:

class Solution {
    public int rob(int[] nums) {
        // Handle base cases
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // dp[i] represents the maximum money that can be robbed from houses 0 to i
        int[] dp = new int[nums.length];
        
        // Initialize first two values
        dp[0] = nums[0];  // If only one house, rob it
        dp[1] = Math.max(nums[0], nums[1]);  // If two houses, rob the one with more money
        
        // For each house starting from index 2
        for (int i = 2; i < nums.length; i++) {
            // Maximum money at current house = max of
            // 1. Rob current house + money from house before last (dp[i-2])
            // 2. Skip current house + money from previous house (dp[i-1])
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }
        
        return dp[nums.length - 1];
    }
}
Let's add detailed logging to understand the process:
javaCopyclass Solution {
    public int rob(int[] nums) {
        System.out.println("Input array: " + Arrays.toString(nums));
        
        // Handle base cases
        if (nums == null || nums.length == 0) {
            System.out.println("Empty array, returning 0");
            return 0;
        }
        if (nums.length == 1) {
            System.out.println("Only one house, returning " + nums[0]);
            return nums[0];
        }
        if (nums.length == 2) {
            int result = Math.max(nums[0], nums[1]);
            System.out.println("Two houses, returning max of " + nums[0] + " and " + nums[1] + " = " + result);
            return result;
        }
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        System.out.println("\nInitializing DP array:");
        System.out.println("dp[0] = " + dp[0] + " (first house)");
        System.out.println("dp[1] = " + dp[1] + " (max of first two houses)");
        
        for (int i = 2; i < nums.length; i++) {
            System.out.println("\nProcessing house " + i + ":");
            System.out.println("Current house value: " + nums[i]);
            System.out.println("Option 1: Rob current house (" + nums[i] + ") + money from two houses back (" + dp[i-2] + ")");
            System.out.println("Option 2: Skip current house and keep previous max (" + dp[i-1] + ")");
            
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
            
            System.out.println("Chose maximum: " + dp[i]);
            System.out.println("Current DP array: " + Arrays.toString(dp));
        }
        
        System.out.println("\nFinal result: " + dp[nums.length - 1]);
        return dp[nums.length - 1];
    }
}
Let's test with example:
javaCopyint[] nums = {2,7,9,3,1};
Solution solution = new Solution();
int result = solution.rob(nums);
Output explanation for [2,7,9,3,1]:
CopyInput array: [2, 7, 9, 3, 1]

Initializing DP array:
dp[0] = 2 (first house)
dp[1] = 7 (max of first two houses)

Processing house 2:
Current house value: 9
Option 1: Rob current house (9) + money from two houses back (2) = 11
Option 2: Skip current house and keep previous max (7)
Chose maximum: 11
Current DP array: [2, 7, 11, 0, 0]

Processing house 3:
Current house value: 3
Option 1: Rob current house (3) + money from two houses back (7) = 10
Option 2: Skip current house and keep previous max (11)
Chose maximum: 11
Current DP array: [2, 7, 11, 11, 0]

Processing house 4:
Current house value: 1
Option 1: Rob current house (1) + money from two houses back (11) = 12
Option 2: Skip current house and keep previous max (11)
Chose maximum: 12
Current DP array: [2, 7, 11, 11, 12]

Final result: 12

Space-Optimized Solution:
Since we only need the last two values, we can optimize space:

javaCopyclass Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int twoBack = nums[0];  // max money if we rob only first house
        int oneBack = Math.max(nums[0], nums[1]);  // max money if we consider up to second house
        int current = oneBack;  // initialize current with oneBack for houses of length 2
        
        for (int i = 2; i < nums.length; i++) {
            current = Math.max(nums[i] + twoBack, oneBack);
            twoBack = oneBack;
            oneBack = current;
        }
        
        return current;
    }
}
Key Points to Remember:

At each house, we have two options:

Rob current house + money from house before last
Skip current house and keep money from previous house


We use dynamic programming to avoid recalculating same values:

dp[i] represents maximum money we can rob up to house i


Base cases are important:

Empty array: return 0
One house: return its value
Two houses: return max of the two


Space complexity can be optimized from O(n) to O(1)

We only need last two values
Can use three variables instead of array

Time complexity is O(n) where n is number of houses
```

# Unique Paths Problem

## Problem Statement

There is a robot on an **m x n** grid. The robot is initially located at the top-left corner (**grid[0][0]**). The robot tries to move to the bottom-right corner (**grid[m - 1][n - 1]**). The robot can only move either **down** or **right** at any point in time.

Given two integers **m** and **n**, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be **≤ 2 × 10^9**.

### **Examples**

#### Example 1:

```
Input: m = 3, n = 7
Output: 28
```

#### Example 2:

```
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
```

---

# **Approaches**

## **Approach 1: Dynamic Programming (DP)**

We use a **2D DP table** where `dp[i][j]` represents the number of ways to reach cell `(i, j)`. The transition formula is:

$$
dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
$$

Since the robot can only move **right** or **down**, the number of ways to reach `(i, j)` is the sum of the ways to reach from the top `(i-1, j)` and from the left `(i, j-1)`.

### **Java Implementation (DP)**

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Fill the first row and first column with 1 (only one way to reach those)
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        // Fill the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
```

### **Dry Run for DP Approach with Visualization**

#### **Grid Representation**

```

1  _  _       1  1  _       1  1  _      1  1  1
_  _  _    -> 1  _  _  ->   1  2  _   -> 1  2  3
_  _  _       _  _  _       1  _  _      1  3  _

1  1  1  
1  2  3  
1  3  6  
```

Each cell contains the number of ways to reach that position.

---

## **Approach 2: Combinatorics (Optimized)**

The robot must take **(m-1) downward moves** and **(n-1) rightward moves** in any order. The total number of moves is:

$$
(m-1) + (n-1) = (m+n-2)
$$

From these `m+n-2` moves, we need to **choose** `(m-1)` downward moves. This is a **combinations** problem:

$$
C(m+n-2, m-1) = \frac{(m+n-2)!}{(m-1)! (n-1)!}
$$

This approach uses the formula for binomial coefficients.

### **Java Implementation (Combinatorics)**

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int N = m + n - 2;
        int r = Math.min(m - 1, n - 1); // Choosing the smaller one for efficiency
        long res = 1;
        
        for (int i = 1; i <= r; i++) {
            res = res * (N - i + 1) / i;
        }
        
        return (int) res;
    }
}
```

### **Dry Run for Combinatorics Approach with Visualization**

#### **Calculation Steps**

```
Total moves = 4
Choosing 2 moves for down: C(4,2) = 4! / (2! * 2!)
= (4 × 3) / (2 × 1)
= 6
```

---

## **Which Approach to Choose?**

- **DP is easier to understand** but uses extra memory.
- **Combinatorics is optimal in both time and space**.

For large values of `m` and `n`, the **combinatorial** approach is preferred.

---

### **Conclusion**

This problem can be efficiently solved using either **DP** (tabulation) or **Combinatorics**. The combinatorial approach is more optimized in terms of space and time.



# Min Cost Climbing Stairs - Dynamic Programming Solution

## Problem Statement
You are given an integer array `cost` where `cost[i]` is the cost of the `i`-th step on a staircase. Once you pay the cost, you can either climb **one** or **two** steps.

You can start from the step with index `0` or `1`. Return the **minimum cost** to reach the top of the floor.

### Example 1:
#### **Input:**
```java
cost = [10,15,20]
```
#### **Output:**
```java
15
```
#### **Explanation:**
- Start at index 1.
- Pay `15` and climb two steps to reach the top.
- The total cost is `15`.

### Example 2:
#### **Input:**
```java
cost = [1,100,1,1,1,100,1,1,100,1]
```
#### **Output:**
```java
6
```
#### **Explanation:**
- Start at index `0`.
- Follow the optimal path with minimal cost.
- The total cost is `6`.

## Constraints:
- `2 <= cost.length <= 1000`
- `0 <= cost[i] <= 999`

---

## Approach: Dynamic Programming
### **1. Bottom-Up DP Approach (Optimized Space O(1))**
We use **two variables** instead of an array to track the last two computed values.

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int prev2 = cost[0];
        int prev1 = cost[1];

        for (int i = 2; i < n; i++) {
            int curr = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        return Math.min(prev1, prev2);
    }
}
```
### **Time and Space Complexity:**
- **Time Complexity:** `O(n)`
- **Space Complexity:** `O(1)`

---

## **Recursive Approach**
### **Recursive Formula:**
```
dp[i] = cost[i] + min(dp[i-1], dp[i-2])
```

#### **Recursive Java Solution:**
```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        return Math.min(minCost(cost, 0), minCost(cost, 1));
    }

    private int minCost(int[] cost, int i) {
        if (i >= cost.length) return 0;
        return cost[i] + Math.min(minCost(cost, i + 1), minCost(cost, i + 2));
    }
}
```
#### **Time Complexity:** `O(2^n)` (Exponential)
#### **Space Complexity:** `O(n)` (Recursion Stack)

---

## **Memoized Recursive Approach**
```java
import java.util.*;

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] memo = new int[cost.length];
        Arrays.fill(memo, -1);
        return Math.min(minCost(cost, 0, memo), minCost(cost, 1, memo));
    }

    private int minCost(int[] cost, int i, int[] memo) {
        if (i >= cost.length) return 0;
        if (memo[i] != -1) return memo[i];
        memo[i] = cost[i] + Math.min(minCost(cost, i + 1, memo), minCost(cost, i + 2, memo));
        return memo[i];
    }
}
```
#### **Time Complexity:** `O(n)`
#### **Space Complexity:** `O(n)` (Memoization Array)

---

## **Dry Run of Optimized DP Approach**


# Step-by-Step Execution

## Example 1
### Input:
```java
cost = [10, 15, 20]
```

### Step-by-step Execution:

We initialize:
- `prev2 = cost[0] = 10`
- `prev1 = cost[1] = 15`

Now, we iterate from index 2 to n-1:

| Step 𝑖 | i   | cost[i] | Computation (curr = cost[i] + min(prev1, prev2)) | Updated prev2 | Updated prev1 |
|--------|-----|---------|---------------------------------------------------|---------------|----------------|
| 2      | 20  | 20      | 20 + min(15, 10) = 20 + 10 = 30                  | prev2 = 15    | prev1 = 30     |

### Final Answer:
```java
return min(prev1, prev2) = min(30, 15) = 15
```

✅ **Output**: 15

---

## Example 2
### Input:
```java
cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
```

### Step-by-step Execution:

We initialize:
- `prev2 = cost[0] = 1`
- `prev1 = cost[1] = 100`

Now, we iterate from index 2 to n-1:

| Step 𝑖 | i   | cost[i] | Computation (curr = cost[i] + min(prev1, prev2)) | Updated prev2 | Updated prev1 |
|--------|-----|---------|---------------------------------------------------|---------------|----------------|
| 2      | 1   | 1       | 1 + min(100, 1) = 1 + 1 = 2                      | prev2 = 100   | prev1 = 2      |
| 3      | 1   | 1       | 1 + min(2, 100) = 1 + 2 = 3                      | prev2 = 2     | prev1 = 3      |
| 4      | 1   | 1       | 1 + min(3, 2) = 1 + 2 = 3                      | prev2 = 3     | prev1 = 3      |
| 5      | 100 | 100     | 100 + min(3, 3) = 100 + 3 = 103                  | prev2 = 3     | prev1 = 103    |
| 6      | 1   | 1       | 1 + min(103, 3) = 1 + 3 = 4                      | prev2 = 103   | prev1 = 4      |
| 7      | 1   | 1       | 1 + min(4, 103) = 1 + 4 = 5                      | prev2 = 4     | prev1 = 5      |
| 8      | 100 | 100     | 100 + min(5, 4) = 100 + 4 = 104                  | prev2 = 5     | prev1 = 104    |
| 9      | 1   | 1       | 1 + min(104, 5) = 1 + 5 = 6                      | prev2 = 104   | prev1 = 6      |

### Final Answer:
```java
return min(prev1, prev2) = min(6, 104) = 6
```

✅ **Output**: 6

## Example 3
### Input:
```java
cost = [10, 25, 30, 5, 15, 20]
```

### Step-by-step Execution:

We initialize:
- `prev2 = cost[0] = 10`
- `prev1 = cost[1] = 25`

Now, we iterate from index 2 to n-1:

| Step 𝑖 | i   | cost[i] | Computation (curr = cost[i] + min(prev1, prev2)) | Updated prev2 | Updated prev1 |
|--------|-----|---------|---------------------------------------------------|---------------|----------------|
| 2      | 30  | 30      | 30 + min(25, 10) = 30 + 10 = 40                  | prev2 = 25    | prev1 = 40     |
| 3      | 5   | 5       | 5 + min(40, 25) = 5 + 25 = 30                    | prev2 = 40    | prev1 = 30     |
| 4      | 15  | 15      | 15 + min(30, 40) = 15 + 30 = 45                  | prev2 = 30    | prev1 = 45     |
| 5      | 20  | 20      | 20 + min(45, 30) = 20 + 30 = 50                  | prev2 = 45    | prev1 = 50     |

### Final Answer:
```java
return min(prev1, prev2) = min(50, 45) = 45
```

✅ **Output**: 45

---

## Example 4
### Input:
```java
cost = [100, 200, 300, 50, 10]
```

### Step-by-step Execution:

We initialize:
- `prev2 = cost[0] = 100`
- `prev1 = cost[1] = 200`

Now, we iterate from index 2 to n-1:

| Step 𝑖 | i   | cost[i] | Computation (curr = cost[i] + min(prev1, prev2)) | Updated prev2 | Updated prev1 |
|--------|-----|---------|---------------------------------------------------|---------------|----------------|
| 2      | 300 | 300     | 300 + min(200, 100) = 300 + 100 = 400            | prev2 = 200   | prev1 = 400    |
| 3      | 50  | 50      | 50 + min(400, 200) = 50 + 200 = 250              | prev2 = 400   | prev1 = 250    |
| 4      | 10  | 10      | 10 + min(250, 400) = 10 + 250 = 260              | prev2 = 250   | prev1 = 260    |

### Final Answer:
```java
return min(prev1, prev2) = min(260, 250) = 250
```

✅ **Output**: 250

#### **Final Answer:**
```java
return min(prev1, prev2) = min(30, 15) = 15
```
✅ **Output:** `15`

---

## **How is this Similar to Fibonacci?**
This problem follows a **Fibonacci-like structure**, but instead of summing previous terms, we take the **minimum**.

### **Comparison of Recurrence Relations**
| Problem                     | Recurrence Relation |
|-----------------------------|---------------------|
| Fibonacci Sequence          | `F(n) = F(n-1) + F(n-2)` |
| Min Cost Climbing Stairs    | `dp[i] = cost[i] + min(dp[i-1], dp[i-2])` |

**Space-optimized DP** (like Fibonacci) allows solving this problem in `O(1)` space.

---

## **Summary**
| Approach       | Time Complexity | Space Complexity | Notes |
|---------------|---------------|----------------|------|
| **Recursion** | `O(2^n)`    | `O(n)` (stack) | Inefficient due to repeated calls |
| **Memoization** | `O(n)`    | `O(n)` | Optimized recursion |
| **DP Array**  | `O(n)`    | `O(n)` | Uses an array to store results |
| **Optimized DP** | `O(n)`    | `O(1)` | Best approach using two variables |

### **Best Choice:** 🚀 **Optimized DP Approach**
This provides the most efficient solution with `O(n)` time and `O(1)` space.

Would you like another **Fibonacci-like DP problem** to practice? 😊

## Longest Common Subsequence (LCS)

### **Problem Statement**
Given two strings `text1` and `text2`, return the length of their longest common subsequence. A subsequence is a sequence derived by deleting some characters without changing the order of the remaining characters.

### **Intuition**
The **Longest Common Subsequence (LCS)** problem requires us to find the longest sequence that appears in both given strings in the same relative order, but not necessarily consecutively.

#### **Key Observations:**
1. If the last characters of both strings match, then the LCS must include this character, and we reduce the problem to the previous characters in both strings.
2. If the last characters do not match, then we have two options:
   - Ignore the last character of `text1` and compute LCS for `text1[0...n-1]` and `text2[0...m]`
   - Ignore the last character of `text2` and compute LCS for `text1[0...n]` and `text2[0...m-1]`
   - The answer is the maximum of these two cases.

This forms a classic **Dynamic Programming (DP)** problem where we break it into subproblems and build our solution using a DP table.

### **Dynamic Programming Approach**
We define `dp[i][j]` as the length of the LCS for `text1[0...i]` and `text2[0...j]`.

#### **Recurrence Relation:**
- If `text1[i-1] == text2[j-1]`, then  
  \[
  dp[i][j] = 1 + dp[i-1][j-1]
  \]
- Otherwise,  
  \[
  dp[i][j] = \max(dp[i-1][j], dp[i][j-1])
  \]

#### **Base Case:**
- If either string is empty (`i=0` or `j=0`), then `dp[i][j] = 0` because an empty string has no subsequence.

#### **Time and Space Complexity**
- **Time Complexity:** \(O(n \times m)\) where \(n = \) length of `text1` and \(m = \) length of `text2` because we fill a `dp` table of size `n × m`.
- **Space Complexity:**
   - \(O(n \times m)\) for the full DP table.
   - Can be optimized to \(O(\min(n, m))\) using a **rolling array** technique (only keeping two rows).

---

### **Java Code Implementation**
```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];  // Include this character
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);  // Exclude one character
                }
            }
        }
        return dp[n][m];
    }
}
```

---

### **Dry Run**
#### **Example:**
```plaintext
text1 = "programming"
text2 = "gaming"
```
#### **DP Table Computation**
|   | "" | g | a | m | i | n | g |
|---|----|---|---|---|---|---|---|
| "" | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| p  | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| r  | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| o  | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| g  | 0 | 1 | 1 | 1 | 1 | 1 | 1 |
| r  | 0 | 1 | 1 | 1 | 1 | 1 | 1 |
| a  | 0 | 1 | 2 | 2 | 2 | 2 | 2 |
| m  | 0 | 1 | 2 | 3 | 3 | 3 | 3 |
| m  | 0 | 1 | 2 | 4 | 4 | 4 | 4 |
| i  | 0 | 1 | 2 | 4 | 5 | 5 | 5 |
| n  | 0 | 1 | 2 | 4 | 5 | 6 | 6 |
| g  | 0 | 1 | 2 | 4 | 5 | 6 | 7 |

#### **Final Answer:**
```plaintext
LCS Length: 7
LCS: "gaming"
```

This means the **longest common subsequence** of `"programming"` and `"gaming"` is `"gaming"`, which has a length of **7**.
# Edit Distance
## **Problem Statement**
Given two strings `word1` and `word2`, return *the minimum number of operations required to convert* `word1` to `word2`.

You have the following three operations permitted on a word:
- Insert a character
- Delete a character
- Replace a character

### **Example 1:**
**Input:**
```plaintext
word1 = "horse", word2 = "ros"
```
**Output:**
```plaintext
3
```
**Explanation:**
```
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
```

### **Example 2:**
**Input:**
```plaintext
word1 = "intention", word2 = "execution"
```
**Output:**
```plaintext
5
```
**Explanation:**
```
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
```

### **Constraints:**
- `0 <= word1.length, word2.length <= 500`
- `word1` and `word2` consist of lowercase English letters.

---


## Solution Approach

### Intuition
The problem can be solved using Dynamic Programming by breaking it down into smaller subproblems. At each position in both strings, we have four possible choices:
To determine `dp[i][j]`, we compare the characters at `word1[i-1]` and `word2[j-1]`:
1. **If the characters are the same**, then no operation is needed:

   ```
   dp[i][j] = dp[i-1][j-1]
   ```

2. **If they are different**, we consider three operations and take the minimum:
   - **Insert a character** (make `word1` longer) → `dp[i][j-1] + 1`
   - **Delete a character** (shorten `word1`) → `dp[i-1][j] + 1`
   - **Replace a character** (change one letter) → `dp[i-1][j-1] + 1`

   ```
   dp[i][j] = min(dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1] + 1)
   ```

---

### Implementation

```java
public class Solution {
    public int minDistance(String word1, String word2) {
        // Create a DP table with dimensions (m+1) x (n+1)
        // where m and n are lengths of word1 and word2 respectively
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize first row and column
        // These represent transforming to/from an empty string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;  // Cost of deleting all characters
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;  // Cost of inserting all characters
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    // If characters match, no operation needed
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // Take minimum of three operations
                    dp[i][j] = 1 + Math.min(
                        dp[i-1][j-1],  // Replace
                        Math.min(
                            dp[i-1][j],    // Delete
                            dp[i][j-1]     // Insert
                        )
                    );
                }
            }
        }
        
        return dp[m][n];
    }
}
```

## Detailed Example: Converting "intention" to "execution"

### Initial DP Table Setup
The table is initialized with the cost of converting to/from empty strings:

```
    ""  e   x   e   c   u   t   i   o   n
""  0   1   2   3   4   5   6   7   8   9
i   1
n   2
t   3
e   4
n   5
t   6
i   7
o   8
n   9
```

### Final DP Table
After filling the table using the dynamic programming approach:

```
    ""  e   x   e   c   u   t   i   o   n
""  0   1   2   3   4   5   6   7   8   9
i   1   1   2   3   4   5   6   6   7   8
n   2   2   2   3   4   5   6   7   7   7
t   3   3   3   3   4   5   5   6   7   8
e   4   3   4   3   4   5   6   7   8   9
n   5   4   4   4   4   5   6   7   8   8
t   6   5   5   5   5   5   5   6   7   8
i   7   6   6   6   6   6   6   5   6   7
o   8   7   7   7   7   7   7   6   5   6
n   9   8   8   8   8   8   8   7   6   5
```

### Step-by-Step Transformation
1. intention → inention (remove 't')
2. inention → enention (replace 'i' with 'e')
3. enention → exention (replace 'n' with 'x')
4. exention → exection (replace 'n' with 'c')
5. exection → execution (insert 'u')

## Complexity Analysis
- Time Complexity: O(m×n) where m and n are the lengths of the strings
- Space Complexity: O(m×n) for the DP table

## Key Points
1. Base cases:
   - Converting an empty string to another string requires insertions equal to the length of the target string
   - Converting a string to an empty string requires deletions equal to the length of the source string

2. Recurrence relation:
   - When characters match: dp[i][j] = dp[i-1][j-1]
   - When they don't match: dp[i][j] = 1 + min(replace, delete, insert)
      - replace = dp[i-1][j-1]
      - delete = dp[i-1][j]
      - insert = dp[i][j-1]

3. The value at dp[m][n] gives us the minimum number of operations needed for the entire transformation

This dynamic programming approach ensures we find the optimal solution by building up from smaller subproblems to solve the larger problem. Each cell in our DP table represents the minimum operations needed to transform the substring of word1 up to index i into the substring of word2 up to index j.



### **Time and Space Complexity**
- **Time Complexity:** \(O(m \times n)\) → We compute values for each pair `(i, j)`.
- **Space Complexity:** \(O(m \times n)\) → We store results in a 2D table.
   - This can be optimized to \(O(n)\) using a rolling array.

---

## **Dry Run**
### **Example 1**: `word1 = "horse", word2 = "ros"`
|    | ∅ | r  | o  | s  |
|----|----|----|----|----|
| ∅  |  0 |  1 |  2 |  3 |
| h  |  1 |  1 |  2 |  3 |
| o  |  2 |  2 |  1 |  2 |
| r  |  3 |  2 |  2 |  2 |
| s  |  4 |  3 |  3 |  2 |
| e  |  5 |  4 |  4 |  3 |

Final answer: `dp[5][3] = 3` (Replace 'h' with 'r', delete 'r', delete 'e').

