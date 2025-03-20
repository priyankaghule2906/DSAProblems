# Recursion

1. Implement the power function pow(x, n) , which calculates the x raised t
2. Generate parenthesis
3. PowerSet
4. Check if there exists a subsequence with sum K 

## 1. Implement the power function pow(x, n) , which calculates the x raised to n i.e. xn.

```text
Brute force solution with TC SC = (n)
Use a loop to iterate from 0 to n (converted to an integer). In each iteration, multiply ans by x. This effectively computes x raised to the power of n.
```
```java
 public double myPow(double x, int n) {
        // Base case: any number to the power of 0 is 1
        if (n == 0) return 1; 
        // Handle negative exponents
        if (n < 0) { 
            x = 1 / x;
            n = -n;
        }
        double ans = 1;
        for (int i = 0; i < n; i++) {
            // Multiply ans by x n times
            ans *= x; 
        }
        return ans;
    }
```

```text
Optimal solution 
Check if the exponent n is even:
If true, recursively calculate the power by squaring the base and halving the exponent.
Example: power(x, n) = power(x * x, n / 2)
Check if the exponent n is odd:
If true, recursively calculate the power by multiplying the base with the result of the power function for n - 1.
Example: power(x, n) = x * power(x, n - 1)
```
![powerOfANumber.jpg](..%2F..%2F..%2F..%2F.gitbook%2Fassets%2FpowerOfANumber.jpg)

```java
// recursive solution
 private double power(double x, long n) {
        // Base case: anything raised to 0 is 1
        if (n == 0) return 1.0;
        // Base case: anything raised to 1 is itself
        if (n == 1) return x;
        // If 'n' is even
        if (n % 2 == 0) {
            // Recursive call: x * x, n / 2
            return power(x * x, n / 2);
        }
        // If 'n' is odd
        // Recursive call: x * power(x, n - 1)
        return x * power(x, n - 1);
    }
    
// iterative 
public double myPow(double x, int n) {
    long num = n;
    if(num<0) {
        x = 1/x;
        num =-num;
    }
    double ans =1.0;
    while(num>0){
        if(num%2==1){
            ans = ans*x;
        }
        x = x*x;
        num=num/2;
    }
    return ans;
}
```


## 2. Generate paranthesis
```text
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.


Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
```

![generate-paranthesis.png](..%2F..%2F..%2F.gitbook%2Fassets%2Fgenerate-paranthesis.png)

```java
 public List<String> generateParenthesis(int n) {
        /**
         * Generates all combinations of n pairs of balanced parentheses.
         *
         * @param n The number of pairs of parentheses.
         * @return A list containing all valid combinations of parentheses.
         */
        List<String> result = new ArrayList<>();
        // Start the recursive generation with initial values
        generate(0, 0, n, "", result);
        return result;
    }

    private void generate(int open, int close, int n, String current, List<String> result) {
        /**
         * A recursive helper function to generate all combinations
         * of balanced parentheses.
         *
         * @param open The number of open parentheses used so far.
         * @param close The number of close parentheses used so far.
         * @param n The total number of pairs of parentheses.
         * @param current The current string being built.
         * @param result The list storing all valid combinations.
         */
        // Base case: if the number of open and close parentheses used
        // is equal to the total number of pairs, add the string to the result.
        if (open == close && open + close == 2 * n) {
            result.add(current);
            return;
        }
        // If the number of open parentheses used is less than the total
        // number of pairs, add an open parenthesis and call the function recursively.
        if (open < n) {
            generate(open + 1, close, n, current + '(', result);
        }
        // If the number of close parentheses used is less than the number
        // of open parentheses, add a close parenthesis and call the function recursively.
        if (close < open) {
            generate(open, close + 1, n, current + ')', result);
        }
    }
```

## 3. PowerSet
```text
Given an array of integers nums of unique elements. Return all possible subsets (power set) of the array.
Do not include the duplicates in the answer.
Example 1
Input : nums = [1, 2, 3]

Output : [ [ ] , [1] , [2] , [1, 2] , [3] , [1, 3] , [2, 3] , [1, 2 ,3] ]
```

```text
Approach
Base Case: When the current index equals the length of the array, it means all items have been considered. At this point, add the current subset (combination of included items) to the result list.
Make a recursive call to the function without adding the current item to the subset. This explores the possibility of subsets that do not include the current item.
Add the current item to the subset and make a recursive call to the function to explore subsets that include this item.
Backtrack: After exploring the subsets that include the current item, remove it from the subset (backtrack) and continue exploring subsets with the next items. This step ensures that all combinations are considered.
Continue the process recursively for each item, using both inclusion and exclusion to cover all possible subsets until all combinations are generated.
```

```java
private void backtrack(int index, int n, int[] nums, List<Integer> current, List<List<Integer>> ans) {
        // Base case: if the index reaches the length of the array,
        // add the current subset to the answer list
        if (index == n) {
            ans.add(new ArrayList<>(current));
            return;
        }

        // Recursive case: Exclude the current element and move to the next element
        backtrack(index + 1, n, nums, current, ans);

        // Include the current element in the subset and move to the next element
        current.add(nums[index]);
        backtrack(index + 1, n, nums, current, ans);

        // Backtrack: remove the last added element to explore other subsets
        current.remove(current.size() - 1);
    }

    // Main function to generate the power set of the given array
    public List<List<Integer>> powerSet(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();  // List to store all subsets
        List<Integer> current = new ArrayList<>();  // Temporary list to store the current subset
        backtrack(0, nums.length, nums, current, ans);  // Start the recursive process
        return ans;  // Return the list of all subsets
    }
```
```text
                    []
                 /    \
               /        \
          []             [1]
         /   \          /    \
       []     [2]     [1]     [1, 2]
      /  \    /  \    /   \    /     \
     []  [3] [2] [2,3] [1] [1,3] [1,2] [1,2,3]
     
at every level a decision whether to include or exclude
at level one , decision whether to include 1 or exclude 1
at level two , decision whether to include 2 or exclude 2
at level three , decision whether to include 3 or exclude 3
 

Time Complexity O(2^N): Each element in the array has two choices: either to be included in a subset or not, leading to 2^n possible subsets.

Space Complexity O(N * 2^N): We generate 2^n subsets, and each subset can have up to n elements. Additionally, the recursion stack can go up to a depth of n.
```



# **Generate Parentheses**

## **Problem Statement**
Given an integer `n`, generate all possible combinations of well-formed parentheses of length `2 × n`.

### **Examples:**
#### **Input:**
```java
n = 3
```
#### **Output:**
```java
[ "((()))" , "(()())" , "(())()" , "()(())" , "()()()" ]
```
#### **Input:**
```java
n = 2
```
#### **Output:**
```java
[ "(())" , "()()" ]
```
#### **Input:**
```java
n = 1
```
#### **Output:**
```java
["()"]
```

---

## **Intuition**
- We use **Backtracking** to generate all possible combinations.
- At each step, we make two choices:
    - Add `(` if the count of `(` is less than `n`.
    - Add `)` if the count of `)` is less than the count of `(`.
- We stop when we reach a valid sequence of length `2 × n`.
- We ensure **pruning** by never allowing `)` count to exceed `(` count.

---

## **Java Code Implementation**
```java
import java.util.*;

public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
```

---

## **Recursion Tree for n = 3**
```
                     "" (0,0)
                      |
             ┌───────┴───────┐
          "(" (1,0)        X (Invalid)
          /                 \
       "((" (2,0)           "()" (1,1)
      /       \              /      \
  "(((" (3,0)  "(()" (2,1)  "()(" (2,1)  X (Invalid)
    |         /       \       /       \
 "((()" (3,1) "(())" (2,2) "()()" (2,2)  X (Invalid)
    |        /        |        |
 "((())" (3,2) "(())" (Valid) "()()" (Valid)
    |          
 "((()))" (3,3)  (Valid)
```

---

## **Dry Run for n = 2**

### **Call Stack Evolution**
```
backtrack("", 0, 0, 2)
  ├── backtrack("(", 1, 0, 2)
  │   ├── backtrack("((", 2, 0, 2)
  │   │   ├── backtrack("(()", 2, 1, 2)
  │   │   │   ├── backtrack("(())", 2, 2, 2) ✅ [Add to result]
  │   │   │   └── Backtrack ⬅ Pop "(())"
  │   │   └── Backtrack ⬅ Pop "(()"
  │   └── Backtrack ⬅ Pop "(("
  ├── backtrack("()", 1, 1, 2)
  │   ├── backtrack("()(", 2, 1, 2)
  │   │   ├── backtrack("()()", 2, 2, 2) ✅ [Add to result]
  │   │   └── Backtrack ⬅ Pop "()()"
  │   └── Backtrack ⬅ Pop "()("  
  └── Backtrack ⬅ Pop "()"
```

### **Final Result for n = 2**
```java
["(())", "()()"]
```

---

## **Complexity Analysis**
- The number of valid sequences follows the **Catalan number**:

  \[ C_n = \frac{(2n)!}{(n+1)!n!} \]

- The **approximate time complexity** is **O(4ⁿ / √n)** due to the branching factor.
- **Space Complexity**: O(4ⁿ / √n) due to recursion depth and result storage.

---

# Power Set Problem

## Problem Statement

Given an array of integers `nums` of unique elements, return all possible **subsets (power set)** of the array.

Do not include duplicates in the answer.

### Examples:
**Input:** nums = [1, 2, 3]  
**Output:** [ [ ], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3] ]

**Input:** nums = [1, 2]  
**Output:** [ [ ], [1], [2], [1, 2] ]

## Intuition

The power set of a set with n elements contains 2^n subsets. This is because for each element, we have two choices: include it or exclude it from a subset.

For example, with the set [1, 2, 3]:
- For the element 1: we can include it or exclude it
- For the element 2: we can include it or exclude it
- For the element 3: we can include it or exclude it

This leads to 2^3 = 8 possible combinations, which forms the power set.

We can solve this problem using recursion with a decision tree approach:
1. For each element in the array, we make a binary decision (include/exclude)
2. When we've processed all elements, we add the current subset to our result
3. Use backtracking to explore all possible combinations

## Java Implementation

```java
import java.util.ArrayList;
import java.util.List;

public class PowerSet {
    public static List<List<Integer>> findPowerSet(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateSubsets(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // Base case: when we've processed all elements
        if (index == nums.length) {
            result.add(new ArrayList<>(current)); // Add a copy of the current subset
            return;
        }
        
        // Option 1: Don't include the current element
        generateSubsets(nums, index + 1, current, result);
        
        // Option 2: Include the current element
        current.add(nums[index]);
        generateSubsets(nums, index + 1, current, result);
        
        // Backtrack by removing the last added element
        current.remove(current.size() - 1);
    }
    
    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> powerSet1 = findPowerSet(nums1);
        System.out.println("Power set of [1, 2, 3]:");
        System.out.println(powerSet1);
        
        // Test case 2
        int[] nums2 = {1, 2};
        List<List<Integer>> powerSet2 = findPowerSet(nums2);
        System.out.println("\nPower set of [1, 2]:");
        System.out.println(powerSet2);
    }
}
```

## Dry Run

Let's trace through the execution for the input array `[1, 2]`:

1. Initial call: `generateSubsets([1, 2], 0, [], result)`
  - index = 0, current = [], result = []

2. We have two choices for the element at index 0 (which is 1):

   A. Exclude element 1:
  - Call `generateSubsets([1, 2], 1, [], result)`

   B. We have two choices for the element at index 1 (which is 2):

         i. Exclude element 2:
            - Call `generateSubsets([1, 2], 2, [], result)`
            - index = 2 = nums.length, so we add current = [] to result
            - result = [ [] ]
         
         ii. Include element 2:
            - current = [2]
            - Call `generateSubsets([1, 2], 2, [2], result)`
            - index = 2 = nums.length, so we add current = [2] to result
            - result = [ [], [2] ]
            - Backtrack: remove 2 from current, current = []

   C. Include element 1:
  - current = [1]
  - Call `generateSubsets([1, 2], 1, [1], result)`

   D. We have two choices for the element at index 1 (which is 2):

         i. Exclude element 2:
            - Call `generateSubsets([1, 2], 2, [1], result)`
            - index = 2 = nums.length, so we add current = [1] to result
            - result = [ [], [2], [1] ]
         
         ii. Include element 2:
            - current = [1, 2]
            - Call `generateSubsets([1, 2], 2, [1, 2], result)`
            - index = 2 = nums.length, so we add current = [1, 2] to result
            - result = [ [], [2], [1], [1, 2] ]
            - Backtrack: remove 2 from current, current = [1]

  - Backtrack: remove 1 from current, current = []

3. Final result = [ [], [2], [1], [1, 2] ]

## Recursion Tree

```
                            generateSubsets(index=0, current=[])
                           /                                     \
                          /                                       \
                         / (exclude 1)                             \ (include 1)
                        /                                           \
    generateSubsets(index=1, current=[])               generateSubsets(index=1, current=[1])
                  /           \                                   /           \
                 /             \                                 /             \
                / (exclude 2)   \ (include 2)                   / (exclude 2)   \ (include 2)
               /                 \                             /                 \
generateSubsets(2,[])   generateSubsets(2,[2])   generateSubsets(2,[1])   generateSubsets(2,[1,2])
          |                     |                       |                       |
          |                     |                       |                       |
          v                     v                       v                       v
  Add [] to result      Add [2] to result      Add [1] to result      Add [1,2] to result
```

## Complexity Analysis

### Time Complexity
- **O(2^n)**: For each element in the array, we have two choices (include/exclude), resulting in 2^n leaf nodes in our recursion tree.
- We also need O(n) time to create a copy of each subset when adding it to our result, making the total time complexity O(n * 2^n).

### Space Complexity
- **O(2^n)** for storing all subsets in the result list.
- **O(n)** for the recursion call stack.
- **O(n)** for storing the current subset during recursion.

Overall space complexity is O(n * 2^n) due to storing all subsets.

# Check if There Exists a Subsequence with Sum K

## Problem Statement
Given an array `arr` and a target sum `k`, check whether there exists a subsequence such that the sum of all elements in the subsequence equals the given target sum `k`.

### Example 1:
**Input:**
```plaintext
arr = [10,1,2,7,6,1,5], k = 8
```
**Output:**
```plaintext
Yes
```
**Explanation:**
Subsequences like `[2, 6]`, `[1, 7]`, `[1, 2, 5]` sum up to `8`.

### Example 2:
**Input:**
```plaintext
arr = [2,3,5,7,9], k = 100
```
**Output:**
```plaintext
No
```
**Explanation:**
No subsequence can sum up to `100`.

## Recursive Backtracking Solution
```java
class Solution {
    public static boolean checkSubsequenceSum(int[] arr, int k) {
        return backtrack(arr, 0, k);
    }

    private static boolean backtrack(int[] arr, int index, int target) {
        // Base cases
        if (target == 0) return true;  // Found a valid subsequence
        if (index == arr.length || target < 0) return false; // Reached end without finding sum

        // Include current element
        boolean include = backtrack(arr, index + 1, target - arr[index]);
        
        // Exclude current element
        boolean exclude = backtrack(arr, index + 1, target);

        return include || exclude;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 1, 2, 7, 6, 1, 5};
        int k1 = 8;
        System.out.println(checkSubsequenceSum(arr1, k1)); // Output: true

        int[] arr2 = {2, 3, 5, 7, 9};
        int k2 = 100;
        System.out.println(checkSubsequenceSum(arr2, k2)); // Output: false
    }
}
```

## Dry Run (Recursive Backtracking for arr = [10,1,2,7,6,1,5], k = 8)
### Initial Call:
```plaintext
backtrack(arr, 0, 8)
```
- **Include 10:** `backtrack(arr, 1, -2) → false`
- **Exclude 10:** `backtrack(arr, 1, 8)`
    - **Include 1:** `backtrack(arr, 2, 7)`
        - **Include 2:** `backtrack(arr, 3, 5)`
            - **Include 7:** `backtrack(arr, 4, -2) → false`
            - **Exclude 7:** `backtrack(arr, 4, 5)`
                - **Include 6:** `backtrack(arr, 5, -1) → false`
                - **Exclude 6:** `backtrack(arr, 5, 5)`
                    - **Include 1:** `backtrack(arr, 6, 4)`
                        - **Include 5:** `backtrack(arr, 7, -1) → false`
                        - **Exclude 5:** `backtrack(arr, 7, 4) → false`
                    - **Exclude 1:** `backtrack(arr, 6, 5)`
                        - **Include 5:** `backtrack(arr, 7, 0) → true`

Since one path leads to `true`, the function returns `true`.

## Time and Space Complexity
- **Time Complexity:** `O(2^N)` (Each element can either be included or excluded, leading to exponential growth).
- **Space Complexity:** `O(N)` (Recursive call stack depth in the worst case is `N`).


# Count Subsequences with Sum K

## Problem Statement
Given an array `nums` and an integer `k`, return the number of non-empty subsequences of `nums` such that the sum of all elements in the subsequence is equal to `k`.

### Examples
#### Example 1:
**Input:**
```plaintext
nums = [4, 9, 2, 5, 1], k = 10
```
**Output:**
```plaintext
2
```
**Explanation:** The possible subsets with sum `k` are `[9, 1]` and `[4, 5, 1]`.

#### Example 2:
**Input:**
```plaintext
nums = [4, 2, 10, 5, 1, 3], k = 5
```
**Output:**
```plaintext
3
```
**Explanation:** The possible subsets with sum `k` are `[4, 1]`, `[2, 3]`, and `[5]`.

#### Example 3:
**Input:**
```plaintext
nums = [1, 10, 4, 5], k = 16
```
**Output:**
```plaintext
1
```
**Explanation:** The only valid subset is `[1, 10, 5]`.

---

## Approach
We solve this problem using **backtracking**, which explores all possible subsequences. The approach involves:

1. **Recursive Backtracking:**
    - At each index, we have two choices:
        - Include the current element in the subset and subtract it from `k`.
        - Exclude the current element and move forward.
    - If `k` becomes `0`, we found a valid subsequence.
    - If we reach the end of the array, we stop searching.

2. **Base Case:**
    - If the sum of the selected elements equals `k`, we count it as one valid subsequence.
    - If we exhaust all elements, return 0.

3. **Recursive Case:**
    - Solve for both including and excluding the current element.
    - Sum the results from both cases to get the final count.

---

## Backtracking Solution (Java)
```java
public class SubsequenceSum {
    public static int countSubsequences(int[] nums, int k) {
        return countHelper(nums, k, 0, 0);
    }

    private static int countHelper(int[] nums, int k, int index, int currentSum) {
        if (index == nums.length) {
            return currentSum == k ? 1 : 0;
        }

        // Include current element
        int include = countHelper(nums, k, index + 1, currentSum + nums[index]);

        // Exclude current element
        int exclude = countHelper(nums, k, index + 1, currentSum);

        return include + exclude;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 2, 5, 1};
        int k1 = 10;
        System.out.println(countSubsequences(nums1, k1)); // Output: 2

        int[] nums2 = {4, 2, 10, 5, 1, 3};
        int k2 = 5;
        System.out.println(countSubsequences(nums2, k2)); // Output: 3

        int[] nums3 = {1, 10, 4, 5};
        int k3 = 16;
        System.out.println(countSubsequences(nums3, k3)); // Output: 1
    }
}
```

---

## Dry Run
### Input:
```plaintext
nums = [4, 2, 10, 5, 1, 3], k = 5
```

### Execution Steps:
1. Start with index `0`, sum = `0`.
2. Include `4`: move to index `1`, sum = `4`.
3. Include `1`: move to index `5`, sum = `5` → **Valid subsequence**.
4. Backtrack: Exclude `1`, move forward.
5. Include `2`: move to index `2`, sum = `2`.
6. Include `3`: move to index `5`, sum = `5` → **Valid subsequence**.
7. Include `5`: move to index `6`, sum = `5` → **Valid subsequence**.
8. Exclude other elements, return total count `3`.

---

## Complexity Analysis
- **Time Complexity:** `O(2^n)` (Exponential), as each element has two choices (include/exclude).
- **Space Complexity:** `O(n)`, for recursive stack space.

This approach is feasible for small values of `n`. For large inputs, dynamic programming (memoization) can optimize the solution.

---


