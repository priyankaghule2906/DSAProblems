Prefix Sum Problems

Basics (build the intuition)
1. 1480 - Running Sum of 1d Array
2. 1732 - Find the Highest Altitude
3. 724 - Find Pivot Index
4. 303 - Range Sum Query - Immutable
5. 2559 - Count Vowel Strings in Ranges

Prefix sum + HashMap (the most important pattern) 6. 560 - Subarray Sum Equals K 7. 525 - Contiguous Array 8. 523 - Continuous Subarray Sum 9. 974 - Subarray Sums Divisible by K 10. 930 - Binary Subarrays With Sum 11. 1248 - Count Number of Nice Subarrays 12. 437 - Path Sum III (prefix sums on a tree) 13. 325 - Maximum Size Subarray Sum Equals k (premium) 14. 1590 - Make Sum Divisible by P

Difference array (the inverse of prefix sum) 15. 370 - Range Addition (premium) 16. 1094 - Car Pooling 17. 1109 - Corporate Flight Bookings
Prefix product / XOR / bitmask variants 18. 238 - Product of Array Except Self 19. 1310 - XOR Queries of a Subarray 20. 1442 - Count Triplets That Equal XOR 21. 1177 - Can Make Palindrome from Substring 22. 1915 - Number of Wonderful Substrings
2D prefix sums 23. 304 - Range Sum Query 2D - Immutable 24. 1314 - Matrix Block Sum 25. 1074 - Number of Submatrices That Sum to Target (hard)
Advanced / combined techniques 26. 528 - Random Pick with Weight (prefix sums + binary search) 27. 2055 - Plates Between Candles 28. 1423 - Maximum Points You Can Obtain from Cards 29. 918 - Maximum Sum Circular Subarray 30. 862 - Shortest Subarray with Sum at Least K (prefix sums + monotonic deque)


1. 1480. Running Sum of 1d Array
Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
Return the running sum of nums.
Example 1:
Input: nums = [1,2,3,4]
Output: [1,3,6,10]
Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].

```java
class Solution {
    public int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            result[i] = nums[i] + result[i-1];
        }
        return result;
    }
}
```

2. 1732. Find the Highest Altitude

There is a biker going on a road trip. The road trip consists of n + 1 points at various altitudes. The biker starts his trip on point 0 with altitude equal 0.

You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i and i + 1 for all (0 <= i < n). Return the highest altitude of a point.

Example 1:

Input: gain = [-5,1,5,0,-7]
Output: 1
Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
Example 2:

Input: gain = [-4,-3,-2,-1,4,3,2]
Output: 0
Explanation: The altitudes are [0,-4,-7,-9,-10,-6,-3,-1]. The highest is 0.

```java
class Solution {
    public int largestAltitude(int[] gain) {
        int n = gain.length;
       // int[] result = new int[n+1];
        int sum = 0;
        int highest = 0;
        for(int i=0;i<n;i++){
            sum = sum + gain[i];
            highest = Math.max(highest, sum);
        }
        return highest;
    }
}
```

3. 724. Find Pivot Index
Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.


Example 1:

Input: nums = [1,7,3,6,5,6]
Output: 3
Explanation:
The pivot index is 3.
Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
Right sum = nums[4] + nums[5] = 5 + 6 = 11
Example 2:

Input: nums = [1,2,3]
Output: -1
Explanation:
There is no index that satisfies the conditions in the problem statement.
Example 3:

Input: nums = [2,1,-1]
Output: 0
Explanation:
The pivot index is 0.
Left sum = 0 (no elements to the left of index 0)
Right sum = nums[1] + nums[2] = 1 + -1 = 0

```java
class Solution {
    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int left = 0;
        int right = 0;
        for(int i=0;i<nums.length;i++){
            right = total - left - nums[i];
            if(left == right) return i;
            left+=nums[i];
        }
        return -1;
    }
}
```
nums = [1,7,3,6,5,6]

1,7,3,6,5,6
0 1 2 3 4 5


total : total sum of element
left  : current sum
right : total - left - nums[i]

4. 303. Range Sum Query - Immutable
        Given an integer array nums, handle multiple queries of the following type:

Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).

Example 1:

Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]

Explanation
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3

class NumArray {

    public int[] prefixSum;
    public NumArray(int[] nums) {
        prefixSum = new int[nums.length+1];
        for(int i=0;i<nums.length;i++){
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }
}

/**
* Your NumArray object will be instantiated and called as such:
* NumArray obj = new NumArray(nums);
* int param_1 = obj.sumRange(left,right);
  */

5. 2559. Count Vowel Strings in Ranges
You are given a 0-indexed array of strings words and a 2D array of integers queries.

Each query queries[i] = [li, ri] asks us to find the number of strings present at the indices ranging from li to ri (both inclusive) of words that start and end with a vowel.

Return an array ans of size queries.length, where ans[i] is the answer to the ith query.

Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.


Example 1:

Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
Output: [2,3,0]
Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
The answer to the query [0,2] is 2 (strings "aba" and "ece").
to query [1,4] is 3 (strings "ece", "aa", "e").
to query [1,1] is 0.
We return [2,3,0].
Example 2:

Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
Output: [3,2,1]
Explanation: Every string satisfies the conditions, so we return [3,2,1].

```java
class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] prefixSum = new int[n+1];

        for(int i = 0; i<n;i++){
            String str = words[i];
            int flag = isVowel(str.charAt(0)) && isVowel(str.charAt(str.length()-1)) ? 1: 0;
            prefixSum[i+1] = flag + prefixSum[i];
        }
        int ans[] = new int[queries.length];
        for(int i=0; i<queries.length;i++){
            int right = queries[i][1];
            int left = queries[i][0];
            ans[i] = prefixSum[right +1] - prefixSum[left];
        }
        return ans;
        
    }
    private boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```

Idea: turn each word into a 0/1 (starts and ends with a vowel), then build a prefix array over those flags. Each query is then prefix[r+1] - prefix[l], so O(1) per query.

Complexity: O(n + q) time, O(n) space for the prefix array (the output array is extra).

Example: words = ["aba","bcb","ece","aa","e"] gives flags [1,0,1,1,1] and prefix [0,1,1,2,3,4]. Query [0,2] is prefix[3] - prefix[0] = 2, and query [1,4] is prefix[5] - prefix[1] = 3.

words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]


["aba","bcb","ece","aa","e"] prefixSum is n+1 size

index      0.    1.   2.    3.   4.    5
flag.      1.    0.   1.    1.   1
prefixSum  0.    1.   1     2.   3.    4

query [0,2]
right+1 = 3 left = 0.   2-0 = 2

query [1,4]
4 - 1 = 3

query [1,1]
1-1 = 0

hence ans is 2,3,0


6. Subarray Sum Equals K
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2


Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107


idea

lets say sum of first i (eg. 5 ) element is prefix[i] (prefix[5]) and sum of any subarray starting from j (2) to i-1 is then

sum(j.. i-1) = prefix[i] - prefix[j]

sum(2-5) = prefix[5] - prefix[2]
we want that equal to k so
prefix[j] - prefix[i] = k  -->  prefix[j] = prefix[i] - k

so we deduct the given k from current sum

So for each position i, the question becomes: how many earlier prefix sums equal prefix[i] - k? Each one marks a start point j that gives a subarray ending at i with sum k.

A HashMap answers that in O(1). It maps each prefix sum to how many times it has occurred so far.

```java
class Solution {
    // sliding window wont work here since array contains negative elements
    public int subarraySum(int[] nums, int k) {
      Map<Integer, Integer> seen = new HashMap<>();
      int prefixSum =0, count = 0;
      seen.put(0,1);
      for(int i=0;i<nums.length;i++){
        prefixSum+=nums[i];
        int diff  = prefixSum - k;
        count += seen.getOrDefault(diff,0);
        seen.merge(prefixSum, 1, Integer::sum);
      }
      return count;
        
    }
}
```

Two details that trip people up

1. Why map.put(0, 1) at the start? It represents the empty prefix (nothing taken yet). Without it you'd miss subarrays that start at index 0. In the example above, [1,2] was found precisely because 0 was in the map.

2. Why check the map before adding the current prefix? If k = 0, then prefix - k equals the current prefix itself. Adding first would count an empty subarray (the prefix matched against itself). Looking up first guarantees you only match strictly earlier prefixes.

Why not sliding window?

Sliding window needs the sum to move predictably as you expand or shrink, which only holds when all numbers are non-negative. Here values can be negative, so shrinking the window might increase the sum. Prefix sums don't care about signs, which is why this pattern is the go-to for "subarray sum equals target" problems.
nums = [1, 2, 4, 1, 3, 2, 5, 1]
k = 7

1, 2, 4, 1, 3, 2, 5, 1
0. 1. 2. 3. 4. 5. 6. 7
initial hashmap {0:1}
count = 0
i. nums[i].  currentSum  difference count  hashmap
0.  1.         1.         -6         0     {0:1, 1:1}
1.  2          3          -4         0     {0:1, 1:1, 3:1}
2.  4          7           0         1     {0:1, 1:1, 3:1, 7:1}
3.  1          8           1         2     {0:1, 1:1, 3:1, 7:1, 8:1}
4.  3         11           4               {0:1, 1:1, 3:1, 7:1, 8:1, 11:1}
5.  2         13           6               {0:1, 1:1, 3:1, 7:1, 8:1, 11:1, 13:1}
6   5         18          11         3.    {0:1, 1:1, 3:1, 7:1, 8:1, 11:1, 13:1, 18:1}           
7   1         19          12         3     {0:1, 1:1, 3:1, 7:1, 8:1, 11:1, 13:1, 18:1, 19:1}
    
nums = [2, 1, 2, -1, 2, 3, -2, 2]
k = 4

i nums[i] currentSum diff count.   hashmap
0.   2.     2         -2.   0        {0:1, 2:1}
1.   1      3         -1    0        {0:1, 2:1, 3:1}
2.   2      5          1.   0        {0:1, 2:1, 3:1,5:1}
3.  -1      4          0.   1        {0:1, 2:1, 3:1,5:1,4:1}
4.   2      6.         2.   2        {0:1, 2:1, 3:1,5:1,4:1, 6:1}
5.   3      9.         5    3.       {0:1, 2:1, 3:1,5:1,4:1, 6:1, 9:1}
6.  -2.     7.         3.   4        {0:1, 2:1, 3:1,5:1,4:1, 6:1, 9:1, 7:1}
7.   2      9.         5.   5        {0:1, 2:1, 3:1,5:1,4:1, 6:1, 9:2, 7:1}


7. 525. Contiguous Array
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
example Input: nums = [0,1,1,1,1,1,0,0,0]
        Output: 6
        Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1.
        Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.

```java
class Solution {
    public int findMaxLength(int[] nums) {
       Map<Integer, Integer> firstOcc = new HashMap<>();
       firstOcc.put(0,-1);
       int maxLength = 0;
       int balance  = 0;
       for(int i=0;i<nums.length;i++){
         balance += nums[i] == 1 ? 1: -1;
         if(firstOcc.containsKey(balance)){
            maxLength = Math.max(maxLength, i - firstOcc.get(balance));
         } else {
         firstOcc.put(balance, i);
         }

       }
       return maxLength;
    }

    private int brute(int[] nums) {
         int max = 0;
        int n = nums.length;
        for(int start =0; start < n; start++) {
            int zero = 0, one =0;
            for(int end = start; end < n; end++) {
                if(nums[end] == 0) zero++;
                if(nums[end] == 1) one++;
                if (zero == one) max = Math.max(max, end-start+1);
            }
        }
        return max;
    }
}
```
Idea
Step 1: Turn it into a sum problem

"Equal number of 0s and 1s" is awkward to track with two counters. So treat every 0 as -1 and every 1 as +1. Now:

equal 0s and 1s   <=>   the subarray sums to 0

Each 1 cancels out a 0. The problem becomes longest subarray with sum 0.
Step 2: Track a running balance

Keep a running total as you scan:

balance = (#1s so far) - (#0s so far)

Step 3: The key observation

Suppose the balance is the same value at two different positions, j and i. Then everything between them added up to zero:

balance[i] - balance[j] = 0   →   subarray (j, i] has equal 0s and 1s

So the question becomes: for each position, what's the earliest time I saw this same balance? The distance to that position is the longest valid subarray ending here.

Step 4: Store only the first occurrence

Use a HashMap from balance to the first index where it appeared. If you see the balance again later, the subarray length is i - firstIndex. You don't update the map, because keeping the earliest index gives the longest span.

0,1,1,1,1,1,0,0,0


i nums[i] flag. balance.                                            cond best
0.  0.     -1.    -1.  {0:-1, -1:0}.                                 F   0
1.  1.      1      0.  {0:-1, -1:0}.                                 T.  2
2.  1       1.     1.  {0:-1, -1:0, 1:2}.                            F.  2
3   1.      1      2.  {0:-1, -1:0, 1:2, 2:3}.                       F.  2
4.  1       1      3.  {0:-1, -1:0, 1:2, 2:3, 3:4}.                  F.  2
5.  1       1      4.  {0:-1, -1:0, 1:2, 2:3, 3:4, 4:5}.             F.  2
6.  0      -1      3.  {0:-1, -1:0, 1:2, 2:3, 3:4, 4:5}.             T.  (6-4) 2
7.  0.     -1      2.  {0:-1, -1:0, 1:2, 2:3, 3:4, 4:5}.             T.  (7-3) 4
8.  0      -1      1.  {0:-1, -1:0, 1:2, 2:3, 3:4, 4:5}.             T.  (8-2) 6

Why first.put(0, -1)?

It represents "balance was 0 before the array started" (at virtual index -1). Without it, you'd miss valid subarrays that start at index 0. In the walkthrough, the length-4 answer came from matching against that entry: 3 - (-1) = 4.

O(n) time, O(n) space.

The pattern to remember

This is the same idea as 560, with two changes:

560 asks for a count, so the map stores how many times each prefix occurred.
525 asks for the longest length, so the map stores the first index of each prefix.

Whenever a problem says "longest/shortest subarray with some property", ask whether you can rewrite the property as "prefix values at two points are equal (or differ by k)". If yes, prefix sum + HashMap applies.

8.  523. Continuous Subarray Sum
Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.

A good subarray is a subarray where:

its length is at least two, and
the sum of the elements of the subarray is a multiple of k.
Note that:

A subarray is a contiguous part of the array.
An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.

Example 1:

Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
Example 2:

Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
Example 3:

Input: nums = [23,2,6,4,7], k = 13
Output: false

Key insight: if prefix[i] and prefix[j] (with i < j) leave the same remainder when divided by k, then the subarray between them, nums[i+1..j], sums to a multiple of k. 
Why: prefix[j] - prefix[i] is divisible by k exactly when prefix[i] % k == prefix[j] % k.

Why remainderIndex.put(0, -1) at the start:

Example: nums = [6, 3, 5], k = 6.

Prefix sums: prefix[0] = 6, prefix[1] = 9, prefix[2] = 14.

Remainders mod 6: prefix[0] % 6 = 0, prefix[1] % 6 = 3, prefix[2] % 6 = 2.

Better example for length ≥ 2: nums = [3, 3], k = 6.

Prefix sums: prefix[0] = 3, prefix[1] = 6.

Remainders: prefix[0] % 6 = 3, prefix[1] % 6 = 0.

At i = 1, remainder is 0, which matches the seeded entry at index -1:

i - remainderIndex.get(0) = 1 - (-1) = 2   → 2 > 1 → return true

This correctly identifies that nums[0..1] = [3, 3], summing to 6, is a multiple of k, and it's a subarray that starts right at the beginning of the array — no earlier prefix sum needed to "cancel out" anything, because the seed at -1 conceptually represents "the empty prefix, sum 0," and 0 % k is always 0.

Why this matters: without the seed, your map would only ever detect multiples of k that occur strictly between two later indices (i and j, both ≥ 0). Any valid subarray that happens to start at index 0 would be invisible to the algorithm, because there'd be no prior remainder-0 entry to match against. The -1 seed patches that gap by pretending there's a "prefix sum of 0" sitting just before the array starts, so subarrays starting at index 0 are treated the same as any other subarray — just another case where two matching remainders bound the subarray, one of them being this virtual empty prefix.
```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderIndex = new HashMap<>();
        remainderIndex.put(0, -1); // remainder 0 at "before the array starts"

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int remainder = sum % k;

            if (remainderIndex.containsKey(remainder)) {
                if (i - remainderIndex.get(remainder) > 1) {
                    return true; // subarray length >= 2
                }
                // don't overwrite — keep the earliest index for this remainder
            } else {
                remainderIndex.put(remainder, i);
            }
        }

        return false;
    }
}
```

9. 974. Subarray Sums Divisible by K
Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
A subarray is a contiguous part of an array.

Example 1:

Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
Example 2:

Input: nums = [5], k = 9
Output: 0


Count subarrays whose sum is divisible by k. This is the counting sibling of #523: same "matching remainders" idea, but you're counting how many pairs match, not just checking whether any pair exists. So the map stores frequency of each remainder, seeded with put(0, 1).

Why ((sum % k) + k) % k: in Java, % can return a negative result when the dividend is negative (e.g. -4 % 5 = -4, not 1). Since nums can contain negative values here (unlike #523, where they can't), sum can go negative, and you need the remainder to always land in [0, k-1] so that two prefix sums with the "same" mathematical remainder actually produce the same map key. Adding k before the second % guarantees a non-negative result without changing its mathematical meaning.



```java
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1); // empty prefix has remainder 0, occurred once

        int sum = 0, count = 0;

        for (int num : nums) {
            sum += num;
            int remainder = ((sum % k) + k) % k; // normalize to always be non-negative

            count += remainderCount.getOrDefault(remainder, 0);
            remainderCount.merge(remainder, 1, Integer::sum);
        }

        return count;
    }
}
```
4  5  0  -2  -3  1
0  1  2.  3.  4  5

k= 5

rem 0 1 2 3 4 5
4 9 9 7 4 5
4 4 4 2 4 1

1-0 = 1
2-0 = 2
4-0 = 4

{0:1}
{4:0}
{2:3}
{1:5}

Side-by-side comparison:

	put(0, 1)	put(0, -1)
Map value means	count of occurrences	index of (earliest) occurrence
Used for	"how many subarrays..." (#560, #974)	"does one exist" / "longest subarray..." (#523, #325, #525)
Why seed with that value	the empty prefix has occurred once already	the empty prefix sits at position -1, before index 0
What breaks without it	subarrays starting at index 0 are never counted	subarrays starting at index 0 are never detected / length is wrong

10. 930. Binary Subarrays With Sum
Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.

A subarray is a contiguous part of the array.

Exmaple 1
Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
Example 2:

Input: nums = [0,0,0,0,0], goal = 0
Output: 15

Why put(0, 1) and not put(0, -1): this is a counting problem ("how many subarrays"), same category as #560 and #974 — not an existence/longest-length problem like #523. So the map stores frequency, seeded with 1 for the empty prefix, following the rule from your last question.

No remainder normalization needed here: unlike #974, there's no % k involved — you're matching exact sums, not remainders. And since all values are 0 or 1, sum never goes negative, so there's no need for the ((x % k) + k) % k trick either.


Trace on nums = [1, 0, 1, 0, 1], goal = 2:  diff is (prefixSum- goal)

i           0.     1.   2.    3.    4   
nums[i]     1.     0.   1.    0.    1
prefixSum.  1.     1.   2.    2.    3
diff       -1.    -1.   0.    0.    1
map.       {0:1}. {0:1} {0:1} {0:1} {0:1}
{1:1}. {1:2} {1:2} {1:2} {1:2}
{2:1} {2:2} {2:2}
{3:1}
cond.      F.      F.   T     T        T
count                   1     2.       4

```java
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        //return atMost(nums, goal) - atMost(nums, goal-1);
        return prefixSumApproach(nums, goal);
    }

    private int atMost(int[] nums, int goal) {
        if(goal < 0) return 0;

        int left = 0;
        int sum = 0;
        int count = 0;
        for(int right = 0; right<nums.length;right++) {
            sum+=nums[right];
            while(sum > goal) {
                sum-=nums[left];
                left++;
            }
            count += right-left+1;
        }   
        return count;
    }

    private int prefixSumApproach(int[] nums, int goal){
        Map<Integer, Integer> seen = new HashMap<>();
        int count = 0;
        int prefixSum = 0;
        seen.put(0,1);
        for(int num: nums){
            prefixSum+= num;
            count+=seen.getOrDefault(prefixSum - goal, 0);
            seen.merge(prefixSum, 1, Integer::sum);
        }
        return count;

    }
}
```

11. 1248. Count Number of Nice Subarrays

Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16

Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length

```java
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        //return brute(nums, k);
       // return countNumberOfOnes(nums, k) - countNumberOfOnes(nums, k-1);
       return prefixSum(nums, k);
    }

    private int countNumberOfOnes(int[] nums, int k){
        int left = 0;
        int count = 0;
        int oddCount = 0;
        for(int right = 0;right<nums.length; right++){
            if(nums[right]%2 == 1){
                oddCount++;
            }
            while(oddCount > k){
                if(nums[left]%2==1) oddCount--;
                left++;
            }
            count +=  right - left+1;
        }
        return count;
    }

    private int brute(int[] nums, int k){
        int n = nums.length;
        int count = 0;
        for(int i=0;i<n;i++){
            int oddCount = 0;
            for(int j=i;j<n; j++){
                if(nums[j]%2 == 1){
                    oddCount++;
                }
                if(oddCount == k) {
                    count++;
                }

            }
        }
        return count;
    }

    private int prefixSum(int[] nums, int k){
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0,1);
        int count = 0;
        int sum = 0;
        for(int num: nums){
            sum+= num%2;
            count+=seen.getOrDefault(sum - k, 0);
            seen.merge(sum, 1, Integer::sum);
        }
        return count;
    }
}
```
12. 348. Maximum Size Subarray Sum Equals k


Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. If there is not one, return 0 instead.

Example 1:
Input: nums = [1,-1,5,-2,3], k = 3

Output: 4

Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.

Example 2:
Input: nums = [-2,-1,2,1], k = 1

Output: 2

Explanation: The subarray [-1, 2] sums to 1 and is the longest.

Find the longest subarray summing to exactly k. This is the earliest-index sibling of #560 — same equation (prefix[j] - prefix[i] = k), but now you want to maximize j - i, not count matches.

```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        // Your code goes here
        int maxLength = 0;
        Map<Integer, Integer> firstIndiceMap = new HashMap<>();
        firstIndiceMap.put(0, -1);
        int sum = 0;
        for(int i=0;i<nums.length; i++){
            sum+=nums[i];
            if(firstIndiceMap.containsKey(sum-k)){
                int start = firstIndiceMap.get(sum-k);
                maxLength = Math.max(maxLength, i-start);
            }
            firstIndiceMap.putIfAbsent(sum, i);
        }
        return maxLength;
    }
}

```
Why -1 seeds here must not be overwritten (and can't be, since it's set before the loop runs): it represents the only valid "sum is exactly k from the very start of the array" case. If sum == k at some index i, then sum - k = 0, which is found at the seed, giving length i - (-1) = i + 1 — exactly the subarray nums[0..i].

Complexity: O(n) time, O(n) space.
Why put(0, -1) here, not put(0, 1): same reasoning as #523 — you're tracking the earliest index (to maximize the subarray length), not counting occurrences, so the seed represents "the empty prefix occurred at position -1," consistent with your earlier question about when to use which seed.

Why putIfAbsent instead of overwriting: you want the earliest occurrence of each prefix sum, because that maximizes i - firstIndex.get(...) for any future match. If you overwrote with a later index every time, you'd shrink the possible subarray length instead of maximizing it. This is the same principle as #523's "don't overwrite — keep the earliest index."

Input: nums = [-2,-1,2,1], k = 1

-2,-1,2,1

      -2 -3 1 2

diff  -3 -4 0 1

[0:-1, -2:0, -3:1, 1:3, 2:3]

1-(-1). = 2

1. Longest Subarray with Sum ≤ K — not on LeetCode directly (custom/GfG variant)

Given an array arr[] containing integers and an integer k, your task is to find the length of the longest subarray where the sum of its elements is equal to the given value k. 
If there is no subarray with sum equal to k, return 0.

Examples:

Input: arr[] = [10, 5, 2, 7, 1, -10], k = 15
Output: 6
Explanation: Subarrays with sum = 15 are [5, 2, 7, 1], [10, 5] and [10, 5, 2, 7, 1, -10]. The length of the longest subarray with a sum of 15 is 6.

```java
class Solution {
    public int longestSubarraySumK(int[] nums, int k) {
        Map<Integer, Integer> firstIndexOfPrefixSum = new HashMap<>();
        int prefixSum = 0;
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            // case 1: subarray from index 0 to i sums exactly to k
            if (prefixSum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // case 2: some earlier prefix sum, when removed, leaves exactly k
            if (firstIndexOfPrefixSum.containsKey(prefixSum - k)) {
                int startIndex = firstIndexOfPrefixSum.get(prefixSum - k);
                maxLen = Math.max(maxLen, i - startIndex);
            }

            // only store the FIRST occurrence of each prefix sum (to maximize length)
            firstIndexOfPrefixSum.putIfAbsent(prefixSum, i);
        }

        return maxLen;
    }
}
```

Why it works:

Let prefixSum[i] = sum of nums[0..i]. A subarray nums[j+1..i] has sum k exactly when prefixSum[i] - prefixSum[j] == k, i.e. prefixSum[j] == prefixSum[i] - k.
So at each index i, we check: "has some earlier prefix sum equal to prefixSum[i] - k occurred before?" If yes, the subarray between that earlier index and i sums to k.
We store only the first occurrence of each prefix sum in the map — since we want the longest subarray, an earlier starting point is always at least as good, so overwriting would only hurt us. putIfAbsent enforces this.
The prefixSum == k check handles the edge case where the valid subarray starts right at index 0 (no earlier prefix to subtract).

Complexity:

Time: O(n) — single pass, O(1) average HashMap operations.
Space: O(n) — for the prefix sum map.

```text
arr[] = [10, 5, 2, 7, 1, -10], k = 15

i             0       1.      2.     3.      4     5. 
arr[i].       10.     5.      2.     7.      1.  -10
prefix sum    10.    15.      17.   24.     25.   15
prefix sum-k   5      0.      -2.    9.     10
maxLen.               2                      4.    6
map.          {10, 0} {15,1} {17,2} {24,3}
    
    
    -5, 8, -14, 2, 4, 12.  k = -5
    
    -5, 8, -14,  2,  4, 12
    -5. 3. -11. -9. -5.  7
    
    10, [4,  2,  7,  1,  1,]  7 k=15
    
    10  14  16  23  24  25  32
    
    -5. -1.  1.  8.  9  10. 17
```