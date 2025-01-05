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

## 4. Check if there exists a subsequence with sum K
```text
Given an array nums and an integer k. R﻿eturn true if there exist subsequences such that the sum of all elements in subsequences is equal to k else false.
Input : nums = [1, 2, 3, 4, 5] , k = 8

Output : Yes

Explanation : The subsequences like [1, 2, 5] , [1, 3, 4] , [3, 5] sum up to 8.
```