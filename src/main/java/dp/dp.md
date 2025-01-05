198. House Robber

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