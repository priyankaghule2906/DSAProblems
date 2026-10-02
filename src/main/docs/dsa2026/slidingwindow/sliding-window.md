# Sliding Window — DSA Notes (2026)

- **Fixed-size window** (#1–8): window size `k` is constant. Slide by adding `nums[right]` and 
  removing `nums[right - k]` each step — no `while` loop needed.
```java
public int fixedLengthSlidingWindow(int[] nums, int k) {
    // choose appropriate data structure
    // Map<Integer, Integer> state = new HashMap<>();
    int start = 0;
    int max = 0;

    for (int end = 0; end < nums.length; end++) {
        // extend window
        // add nums[end] to state in O(1) time

        if (end - start + 1 == k) {
            // INVARIANT: size of the window is k here.
            max = Math.max(max, contents of state);

            // contract window
            // remove nums[start] from state in O(1) time
            start++;
        }
    }

    return max;
}
```
- **Variable-size window** (#9–14): window grows with `right` and shrinks with `left` only when it becomes invalid.
```
Variable Sliding Window Template
─────────────────────────────────
for (right = 0 → n-1):
    add nums[right] to window state
    while (window is invalid):
        remove nums[left] from window state
        left++
    update answer using [left, right]
```


## Pattern 1: Fixed-Size Window (6)
1. Sliding Window Maximum — [LC 239](https://leetcode.com/problems/sliding-window-maximum/)
2. Sliding Window Median — [LC 480](https://leetcode.com/problems/sliding-window-median/)
3. Find All Anagrams in a String — [LC 438](https://leetcode.com/problems/find-all-anagrams-in-a-string/)
4. Permutation in String — [LC 567](https://leetcode.com/problems/permutation-in-string/)
5. Repeated DNA Sequences — [LC 187](https://leetcode.com/problems/repeated-dna-sequences/)
6. Maximum Sum Subarray of Size K — *GfG classic, not on LeetCode; still worth doing as the intro problem*

## Pattern 2: Variable-Size — Longest (11)
7. Longest Substring Without Repeating Characters — [LC 3](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
8. Longest Substring with At Most Two Distinct Characters — [LC 159](https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/) *(premium)*
9. Longest Substring with At Most K Distinct Characters — [LC 340](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/) *(premium)*
10. Longest Repeating Character Replacement — [LC 424](https://leetcode.com/problems/longest-repeating-character-replacement/)
11. Fruit Into Baskets — [LC 904](https://leetcode.com/problems/fruit-into-baskets/)
12. Max Consecutive Ones — [LC 485](https://leetcode.com/problems/max-consecutive-ones/)
13. Max Consecutive Ones III — [LC 1004](https://leetcode.com/problems/max-consecutive-ones-iii/)
14. Longest Continuous Subarray with Absolute Diff ≤ Limit — [LC 1438](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/)
15. Subarray Product Less Than K — [LC 713](https://leetcode.com/problems/subarray-product-less-than-k/)
16. Longest Turbulent Subarray — [LC 978](https://leetcode.com/problems/longest-turbulent-subarray/)
17. Longest Substring with At Least K Repeating Characters — [LC 395](https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/)

## Pattern 3: Variable-Size — Shortest (7)
18. Minimum Window Substring — [LC 76](https://leetcode.com/problems/minimum-window-substring/)
19. Minimum Size Subarray Sum — [LC 209](https://leetcode.com/problems/minimum-size-subarray-sum/)
20. Minimum Window Subsequence — [LC 727](https://leetcode.com/problems/minimum-window-subsequence/) *(premium)*
21. Minimum Number of K Consecutive Bit Flips — [LC 995](https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/)
22. Minimum Operations to Reduce X to Zero — [LC 1658](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/)
23. Shortest Subarray to Remove to Make Array Sorted — [LC 1574](https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/)
24. Minimum Swaps to Group All 1's Together — [LC 1151](https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/) *(premium)*

## Pattern 4: Counting Subarrays (6)
25. Subarrays with K Different Integers — [LC 992](https://leetcode.com/problems/subarrays-with-k-different-integers/)
26. Count Number of Nice Subarrays — [LC 1248](https://leetcode.com/problems/count-number-of-nice-subarrays/)
27. Binary Subarrays With Sum — [LC 930](https://leetcode.com/problems/binary-subarrays-with-sum/)
28. Number of Substrings Containing All Three Characters — [LC 1358](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/)
29. Count Vowel Substrings of a String — [LC 2062](https://leetcode.com/problems/count-vowel-substrings-of-a-string/)
30. Maximum Number of Vowels in a Substring of Given Length — [LC 1456](https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/)

## Pattern 5: Hybrid / String Matching / Two-Ended (6)
31. Substring with Concatenation of All Words — [LC 30](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)
32. Maximum Erasure Value — [LC 1695](https://leetcode.com/problems/maximum-erasure-value/)
33. Max Points You Can Obtain from Cards — [LC 1423](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/)
34. Frequency of the Most Frequent Element — [LC 1838](https://leetcode.com/problems/frequency-of-the-most-frequent-element/)
35. Grumpy Bookstore Owner — [LC 1052](https://leetcode.com/problems/grumpy-bookstore-owner/)
36. K-Radius Subarray Averages — [LC 2090](https://leetcode.com/problems/k-radius-subarray-averages/)

This set has zero duplicates and every entry is a genuine sliding-window solution (I pulled out 560, 862, and 1763 since those lean on prefix-sum, monotonic deque + prefix-sum, or divide-and-conquer instead). Five problems require LeetCode Premium (159, 340, 727, 1151) — let me know if you want free alternatives swapped in for those.Want me to walk through a few of these with you next — maybe starting with the fixed-size window ones since those build the core mechanics for everything else?

## Table of Contents

**Fixed-size window**
1. [Maximum Sum Subarray of Size K](#1-maximum-sum-subarray-of-size-k)
2. [First Negative in Every Window of Size K](#2-first-negative-in-every-window-of-size-k)
3. [Maximum Average Subarray](#3-maximum-average-subarray)
4. [Count Occurrences of Anagrams](#4-count-occurrences-of-anagrams)
5. [Sliding Window Maximum](#5-sliding-window-maximum)
6. [Permutation in String](#6-permutation-in-string)
7. [Sliding Window Median](#7-sliding-window-median)
8. [Repeated DNA Sequences](#8-repeated-dna-sequences)
   **Variable-size window**
9. [Longest Substring Without Repeating Characters](#9-longest-substring-without-repeating-characters)
10. [Longest Substring With At Most K Distinct Characters](#10-longest-substring-with-at-most-k-distinct-characters)
11. [Longest Substring With At Most Two Distinct Characters](#11-longest-substring-with-at-most-two-distinct-characters-leetcode-159-premium)
12. [Longest Repeating Character Replacement](#12-longest-repeating-character-replacement)
13. [Fruit Into Baskets](#13-fruit-into-baskets)
14. [Max Consecutive Ones III](#14-max-consecutive-ones-iii)
---

## 1. Maximum Sum Subarray of Size K

**Problem:** Given `arr[]` and integer `k`, return the maximum sum of any contiguous subarray of size `k`.

Examples:

**Examples**
```
arr = [100, 200, 300, 400], k = 2  →  700   (arr[1]+arr[2])
arr = [1,4,2,10,23,3,1,0,20], k=4  →  39    (arr[1..4])
arr = [100, 200, 300, 400], k = 1  →  400
```

Code
```java
public int maxSubarraySum(int[] arr, int k) {
        int sum = 0;
        for(int i=0;i<k;i++) {
            sum+=arr[i];
        }
        int maxSum = sum;
        for(int right=k; right<arr.length;right++){
            // remove left + add right
            sum = sum - arr[right-k] + arr[right];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
```

**Complexity:** Time O(N) · Space O(1)

## 2. First Negative in Every Window of Size K

**Problem:** For every contiguous window of size `k`, return the first negative integer in it (or `0` if none exists).

**Examples**
```
arr = [-8, 2, 3, -6, 10], k = 2
→ [-8, 0, -6, -6]

arr = [12, -1, -7, 8, -15, 30, 16, 28], k = 3
→ [-1, -1, -7, -15, -15, 0]

arr = [12, 1, 3, 5], k = 3
→ [0, 0]
```
For every window of size k, we need the first negative number.

**Idea:** Track indices of negative numbers in a deque. The front of the deque is always the first negative number in the current window, as long as it hasn't fallen out of range.

```java
static List<Integer> firstNegInt(int arr[], int k) {
        // code here
        List<Integer> result = new ArrayList<>();
        Deque<Integer> q = new ArrayDeque<>();
        
        for(int right=0;right<arr.length;right++){
            if(arr[right] < 0) {
                q.addLast(right);
            }
            // once the window size becomes k
            if(right >= k-1){
                while(!q.isEmpty() && q.peekFirst() <= right-k){
                    q.pollFirst();
                }
                
                result.add(q.isEmpty()?0:arr[q.peekFirst()]);
            }
        }
       
        return result;
    }
```

**Trace:** `arr = [-8, 2, 3, -6, 10]`, `k = 2`

| right | right−k | window valid? | q      | result |
|-------|---------|----------------|--------|--------|
| 0     | −2      | F              | {0}    | —      |
| 1     | −1      | T              | {0}    | −8     |
| 2     | 0       | T              | {}     | 0      |
| 3     | 1       | T              | {3}    | −6     |
| 4     | 2       | T              | {3}    | −6     |

Input: arr[] = [12, -1, -7, 8, -15, 30, 16, 28] , k = 3
Output: [-1, -1, -7, -15, -15, 0]

12, -1, -7, 8, -15, 30, 16, 28
0.   1.  2. 3.  4.   5.  6.  7


right.   0.       1.        2            3           4           5             6           7
q        {}.     {1}       {1,2}        {1,2}       {2,4}       {4}.         {4}.         {}
right-k. -3       -2        -1            0           1          2             3           4
window.?  F.      F.        T            T            T          T            T.           T
result                      -1          -1           -7.        -15          -15          0

## 3. Maximum Average Subarray
**Problem:**    Given an array arr[] and a positive integer k, find the subarray of length k having the maximum average value.

Return the starting index of that subarray.

If multiple subarrays have the same maximum average, return the smallest starting index.

Examples:

Input: k = 4, arr[] = [1, 12, -5, -6, 50, 3]
Output: 1
Explanation: Maximum average is (12 - 5 - 6 + 50)/4 = 51/4. Therefore answer for this test case is 1.
Input: k = 3, arr[] = [3, -435, 335, 10, -50, 100, 20]
Output: 2
Explanation: Maximum average is (335 + 10 - 50)/3 = 295/3. Therefore answer for this test case is 2.


**Key insight:** Since `k` is fixed, maximizing the average is the same as maximizing the sum. ** WHOEVER has max sum is also max avg candidate so we find max sum**

```java
class Solution {
    public int findMaxAverage(List<Integer> arr, int k) {
        // code here
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        int start =0;
        for(int right =0;right<arr.size();right++){
            sum = sum + arr.get(right);
            if(right >= k-1){
                if(maxSum < sum) {
                    maxSum = sum;    
                    start = right-k+1;
                }
                sum = sum - arr.get(right-k+1);
            }
        }
        return start;
    }
}


```
**Trace:** `arr = [1, 12, -5, -6, 50, 3]`, `k = 4`

| right | sum | k-1 reached? | maxSum | start |
|-------|-----|--------------|--------|-------|
| 0     | 1   | F            | —      | —     |
| 1     | 13  | F            | —      | —     |
| 2     | 8   | F            | —      | —     |
| 3     | 2   | T            | 2      | 0     |
| 4     | 51  | T            | 51     | 1     |
| 5     | 42  | T            | 51     | 1     |

**Complexity:** Time O(N) · Space O(1)

---


## 4. Count Occurrences of Anagrams

**Problem:** Given `pat` and `txt`, count how many substrings of `txt` are anagrams of `pat`.

**Examples**
```
txt = "forxxorfxdofr", pat = "for"  → 3   ("for", "orf", "ofr")
txt = "aabaabaa", pat = "aaba"      → 4
```

**Approach:** Maintain a fixed-size (length `k = |pat|`) frequency window over `txt` and compare it to `pat`'s frequency array each time the window slides.
Both strings contain lowercase English letters.

**Code (two-pass setup + slide)**
```java
class Solution {
    int search(String pat, String txt) {
        if (pat.length() > txt.length()) return -1;
        int k = pat.length();
        int[] freq = new int[26];
        int[] window = new int[26];

        for (int i = 0; i < k; i++) {
            freq[pat.charAt(i) - 'a']++;
            window[txt.charAt(i) - 'a']++;
        }
        int count = Arrays.equals(freq, window) ? 1 : 0;

        for (int right = k; right < txt.length(); right++) {
            window[txt.charAt(right) - 'a']++;
            window[txt.charAt(right - k) - 'a']--;
            if (Arrays.equals(freq, window)) count++;
        }
        return count;
    }
}
```

**Code (single-loop version)**
```java
class Solution {
    int search(String pat, String txt) {
        if (pat.length() > txt.length()) return -1;
        int k = pat.length();
        int[] freq = new int[26];
        int[] window = new int[26];
        int count = 0;

        for (int i = 0; i < k; i++) freq[pat.charAt(i) - 'a']++;

        for (int right = 0; right < txt.length(); right++) {
            window[txt.charAt(right) - 'a']++;
            if (right >= k) {
                window[txt.charAt(right - k) - 'a']--;
            }
            if (right >= k - 1 && Arrays.equals(freq, window)) {
                count++;
            }
        }
        return count;
    }
}
```

> **Why the two conditions differ by one:** `right >= k` (remove outgoing char) and `right >= k - 1` (check for match) are two different milestones in the same loop — the window first *reaches* size `k` at `right = k-1`, but only first *exceeds* it (needing a removal) at `right = k`. Both are correct as written; they're intentionally offset.

**Complexity:** Time O(N) · Space O(1) (26-size arrays)

/*

f. o. r. x. x. o. r. f. x. d.  o.  f.  r
0. 1. 2. 3. 4. 5. 6. 7  8. 9  10. 11. 12

freq {f:1, o:1, r:1}
window {f:1, 0:1, r:1 }

right   right-k.     window.             freq.        count
0.       -3
1.       -2
2        -1     {f:1, o:1, r:1 }      {f:1, o:1, r:1 }. 1
3         0.    {o:1, r:1, x:1 }      {f:1, o:1, r:1 }. 1
4         1.    {r:1, x:2 }                             1
5         2     {x:2, o:1 }
6         3     {r:1:, x:1,o:1 }
7         4.    {r:1:,o:1, f:1 }                        2
8.        5.    {r:1:, f:1 , x:1}
9         6.    {d:1:, f:1 , x:1}
10        7.    {d:1:, o:1 , x:1}
11        8.    {d:1:, o:1 , f:1}
12.       9.    {o:1:, r:1 , f:1}                       3 

*/

Condition	Purpose	First true at	Why
right >= k	   Remove outgoing char	right = k	Window just exceeded size k
right >= k - 1	Check for match	right = k - 1	Window just reached size k for the first time

Both are correct as written in the single-loop solution — they're intentionally offset by one because "window first becomes valid" and
"window first needs shrinking" are two different moments in the iteration.


## 5. Sliding Window Maximum

**Problem:** For a sliding window of size `k` moving across `nums`, return the max of each window.

**Example**
```



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
Example 2:

Input: nums = [1], k = 1
Output: [1]
```
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        Deque<Integer> dq = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        for(int right=0;right<nums.length;right++){
            // remove the elements that are outside of the window 
            // if the index is less than equals right-k remove it
            if(!dq.isEmpty() && dq.peekFirst() <= right-k){
                dq.pollFirst();
            }
            // remove the smaller element from the end of queue so that max elements are at the front
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[right]) {
                dq.pollLast();
            }
            // add the current
            dq.offerLast(right);
            if(right >= k-1){
                result[index++] = nums[dq.peekFirst()];
            }
        }

        return result;
    }
}

/**
1,3,-1,-3,5,3,6,7
0 1. 2  3.4 5 6 7

right      0 1  2. 3. 4. 5. 6. 7
num.       1.3 -1 -3  5. 3. 6. 7
win out?
small out?
dq.                           7
   
result [3, 3, 5, 5, 6 ,7]
 */
```

**Memorize this pattern:**
```
1. Remove expired indices  → FRONT   :  dq.peekFirst() <= right - k
2. Remove smaller values   → BACK    :  nums[dq.peekLast()] <= nums[right]
3. Front = maximum                   :  nums[dq.peekFirst()]
```

**Complexity:** Time O(N) — each index pushed/popped at most once · Space O(k)



## 6. Permutation in String

**Problem:** Return `true` if `s2` contains a permutation (anagram) of `s1` as a substring.

**Examples**
```
s1 = "ab", s2 = "eidbaooo"  →  true   ("ba")
s1 = "ab", s2 = "eidboaoo"  →  false
```

**Code**

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s2.length() < s1.length()) return false;
        int[] freq = new int[26];
        int[] window = new int[26];
        int k = s1.length();
        for(int i=0;i<k;i++){
            freq[s1.charAt(i)-'a']++;
            window[s2.charAt(i)-'a']++;
        }
        if(Arrays.equals(freq, window)) return true;

        for(int i=k;i<s2.length();i++){
            // remove char from window
            window[s2.charAt(i-k)-'a']--;
            // add char in window
            window[s2.charAt(i)-'a']++;
            // compare
            if(Arrays.equals(freq, window)) return true;
        }
        return false;
    }
}
```
**Complexity:** Time O(N) · Space O(1)

---



Note : for the next question until I learn about heap/heapify methods just remember that


- smallest element is always on the top of the PQ if you do peek or poll , you get 3
   PriorityQueue<Integer> pq = new PriorityQueue<>();

   pq.add(5);
   pq.add(4);
   pq.add(6);
   pq.add(3);
   pq.add(7);

   System.out.println(pq.poll());
- max element is always  on the top of the PQ, in the above exmaple 
- if pq was new PriorityQueue<>(Collections.reverseOder());, peek or poll would give me 7 which is max in this case



## 7. Sliding Window Median

**Problem:** Return the median of every window of size `k` as it slides across `nums` (within `1e-5` tolerance).

**Examples**
```
Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
Explanation:
Window position                Median
---------------                -----
[1  3  -1] -3  5  3  6  7        1
1 [3  -1  -3] 5  3  6  7       -1
1  3 [-1  -3  5] 3  6  7       -1
1  3  -1 [-3  5  3] 6  7        3
1  3  -1  -3 [5  3  6] 7        5
1  3  -1  -3  5 [3  6  7]       6
Example 2:

Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]


Constraints:

1 <= k <= nums.length <= 105
-231 <= nums[i] <= 231 - 1

```


**Approach:** Two-heap technique.
- **`small`** — a max-heap holding the *smaller* half of the window (top = largest of the small half).
- **`large`** — a min-heap holding the *larger* half of the window (top = smallest of the large half).

Median comes from the tops of these heaps. Since we can't cheaply delete an arbitrary element from a heap, use **lazy deletion**: mark removed values in a `delayed` map and only actually pop them once they surface at the top of a heap. Track *logical* sizes (`smallSize`, `largeSize`) separately from the heaps' physical sizes.

```
① Two heaps          small = max-heap, large = min-heap
② Why                small.peek() = largest of smaller half
                      large.peek() = smallest of larger half
③ Balance invariant   smallSize == largeSize   OR   smallSize == largeSize + 1
④ Median              k odd:  small.peek()
                      k even: (small.peek() + large.peek()) / 2.0
⑤ Removal             mark in `delayed` → update logical size → prune when at top
```
my note :-
A max-heap for the smaller half (to quickly get the largest element in this half)
A min-heap for the larger half (to quickly get the smallest element in this half)

*
A brute force solution to solve this problem is slide the window of k elements at a time, sort those k elements, if the k is odd take the mid element as a median
if k is even take 2 mid elements, sum and divide by 2.
sorting k elements would require k log k and sliding a window of n elements is o(n) so → O(n·k log k) overall)
We actually dont need elements to be sorted we just want access to the middle element which can achieved using two heaps
a max heap and a min heap a max heap will contain the smaller elements of half of each window thus max element can be accessed using the top
a min heap will contain other half of the window with larger element and top of the heap will return the smallest element from it

e.g 1,2,3,4,5

a max heap -> 3,2,1
a min heal -> 4,5 

so top of max heap is 3 which is the mid element

a next things to consider is window when k is odd we simply take the top of the max heap
however if k is even, we take the top of the each heap , sum and divide by 2

Whenever we slide the window older elements needs to be removed, however deleting the elements from priority queue is log(k)
so instead of deleting the elements right away we would use lazy deletion whenever the element is at the top of the heap here TC would he O(1)

we will also use two counters to count the elements of max and min heap to track logical and valid elements of the heap instead of physical elements of the heap

> **PriorityQueue refresher:** `new PriorityQueue<>()` is a min-heap (peek/poll gives the smallest). `new PriorityQueue<>(Collections.reverseOrder())` is a max-heap (peek/poll gives the largest).

> ⚠️ **Worth double-checking:** in `remove()`, the check `num <= small.peek()` runs *before* pruning — trace a case where `small.peek()` is itself a stale/delayed value at that moment to make sure the routing is still correct.

**Complexity:** Time O(N log k) · Space O(k)



```java
    import java.util.*;

    class Solution {

        PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> large = new PriorityQueue<>();
        Map<Integer, Integer> delayed = new HashMap<>();
        int smallSize = 0, largeSize=0;

        public double[] medianSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            double[] result = new double[n-k+1];
            int index= 0;

            for(int i=0;i<n;i++) {
                add(nums[i]);
                // when window reaches the size

                if(i >= k-1){
                    // remove invalid elements from the top of the heaps
                    prune(small);
                    prune(large);
                    // get the median
                    double median = getMedian(k);
                    result[index++] = median;
                    // remove outgoing element
                    remove(nums[i-k+1]);
                }
            }

            return result;
            
        }

        private void prune(PriorityQueue<Integer> heap){
            // whenever the elements are outside of the window we want to lazy delete them
            while(!heap.isEmpty() && delayed.containsKey(heap.peek())){
                int num = heap.poll();
                int count = delayed.get(num);
                if(count==1){
                    delayed.remove(num);
                } else {
                    delayed.put(num, count-1);
                }
            }
        }

        private void remove(int num){

            /**
                remove(num)
                        |
                        ↓
                mark as deleted
                        |
                        ↓
            Which half did it belong to?
                /             \
                /               \
        small half          large half
            ↓                   ↓
        smallSize--         largeSize--
                \               /
                \             /
                    ↓
                    prune
                    ↓
                    rebalance
            */
            delayed.merge(num, 1, Integer::sum);

            if(num <= small.peek()) {
                smallSize--;
            } else {
                largeSize--;
            }

            prune(small);
            prune(large);
            rebalance();

        }

        private void add(int num) {
            // if the num is smaller than top of smaller heap it belongs to smaller part of the window
            if(small.isEmpty() || num <= small.peek()){
                small.offer(num);
                smallSize++;
            } else {
                large.offer(num);
                largeSize++;
            }
            rebalance();
        }

        private void rebalance(){
            // whenever the size of the both heaps changes they need to be rebalanced
            // our design allows one more element in the max heap to handle odd window size

            if(smallSize > largeSize+1) {
                large.offer(small.poll());
                largeSize++;
                smallSize--;
                prune(small);
            } else if (smallSize < largeSize) {
                small.offer(large.poll());
                smallSize++;
                largeSize--;
                prune(large);
            }
        }

        private double getMedian(int k){
            // window is odd take top element from the max heap (smaller elements of the window)
            if(k%2==1) {
                return (double) small.peek();
            } 
            return  ((double) small.peek() + (double)large.peek()) /2.0;
        }
    }
```


## 8. Repeated DNA Sequences

**Problem:** Return all 10-letter substrings that occur more than once in a DNA sequence (`A`, `C`, `G`, `T`).

**Examples**
```
s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
→ ["AAAAACCCCC", "CCCCCAAAAA"]

s = "AAAAAAAAAAAAA"
→ ["AAAAAAAAAA"]
```

**Code**
```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> dna = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i + 10 <= s.length(); i++) {
            String sub = s.substring(i, i + 10);
            int cnt = dna.merge(sub, 1, Integer::sum);
            if (cnt == 2) result.add(sub);
        }
        return result;
    }
}
```

**Complexity:** Time O(N) · Space O(N)

# Variable-Size Window Problems

From here on, the window's size is *not* fixed — it grows with `right` and only shrinks with `left` when some validity condition is violated:

```
for (right = 0 → n-1):
    add nums[right]
    if window becomes invalid:
        move left, remove nums[left]
    update answer
```

---

## 9. Longest Substring Without Repeating Characters

**Problem:** Find the length of the longest substring of `s` with no duplicate characters.

**Examples**
```
s = "abcabcbb"  →  3   ("abc")
s = "bbbbb"     →  1   ("b")
s = "pwwkew"    →  3   ("wke")
```

**Code**
```java
class Solution {
    public int longestNonRepeatingSubstring(String s) {
        int max = 1;
        Set<Character> set = new HashSet<>();
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
```

**Complexity:** Time O(N) · Space O(min(N, charset size))

## 10. Longest Substring With At Most K Distinct Characters
Given a string s and an integer k.Find the length of the longest substring with at most k distinct characters.


Example 1
Input : s = "aababbcaacc" , k = 2
Output : 6
Explanation : The longest substring with at most two distinct characters is "aababb".
The length of the string 6.
Example 2
Input : s = "abcddefg" , k = 3
Output : 4
Explanation : The longest substring with at most three distinct characters is "bcdd".
The length of the string 4.


Approach: Sliding window with a frequency map. Expand the right pointer, and whenever the window has more than k distinct characters, shrink from the left until it's valid again. Track the max window length seen


**Code**
```java
class Solution {
    public int kDistinctChar(String s, int k) {
        if (s == null || s.isEmpty() || k == 0) return 0;

        int maxLen = 0, left = 0;
        Map<Character, Integer> freq = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq.merge(c, 1, Integer::sum);

            while (freq.size() > k) {
                char ch = s.charAt(left);
                int count = freq.get(ch);
                if (count == 1) freq.remove(ch);
                else freq.put(ch, count - 1);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
/*
a a b a b b c a a c c
0 1 2 3 4 5 6 7 8 9 10

right.   0    1.  2.    3.    4.    5     6     7.    8.   9    10
left.    0    0   0     0     0     0     4.    6.    6.   6.    6
set.     a1   a2  a2b   a3b   a3b2  a3b3  b2c   ca    ca2  c2a2. c3a2
max.     1    2.  3.    4.    5.    6.    6.    6      6.   6.    6


when right is 6
left  0.       1      2.     3.    4
freq  a3b3c.  a2b3c.  ab3c. ab2c. b2c

when right is 7
left  4       5.   6
freq  b2ca.  bca.  ca

*/
```

Time: O(n) — each character is added and removed from the window at most once.
Space: O(k) — the map holds at most k+1 distinct characters at any point.

**Mistakes to remember:**
- Don't hardcode a `k` value in the shrink condition — use the parameter.
- Don't use a `Set` for tracking distinct characters — when you remove `s.charAt(left)`, a `Set` has no notion of "how many are still in the window," so it will wrongly drop a character that still appears elsewhere in the window.
    - Example: window `"aba"`, k = 2. Removing `s.charAt(left) = 'a'` from a `Set` incorrectly forgets that another `'a'` is still present at index 2. A frequency **map** avoids this by decrementing counts and only removing the key when the count hits zero.

## 11. Longest Substring With At Most Two Distinct Characters (LeetCode 159, premium)

**Code**
```java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int max = 0, left = 0;
        Map<Character, Integer> freq = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            freq.merge(s.charAt(right), 1, Integer::sum);
            while (freq.size() > 2) {
                char c = s.charAt(left);
                freq.put(c, freq.get(c) - 1);
                if (freq.get(c) == 0) freq.remove(c);
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}

```
**Related problems using the same template:**
- **LC 3** — Longest Substring Without Repeating Characters (≈ "at most 1 of each")
- **LC 904** — Fruit Into Baskets (literally "at most 2 types", see #13 below)
- **LC 340** — Longest Substring with At Most K Distinct Characters (the general form, premium)

---
12. Longest Repeating Character Replacement

You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.


```java
public int characterReplacement(String s, int k) {

    int[] count = new int[26];

    int left = 0;
    int maxFreq = 0;
    int result = 0;

    for (int right = 0; right < s.length(); right++) {

        // Add current character
        count[s.charAt(right) - 'A']++;

        // Maximum frequency inside the window
        maxFreq = Math.max(
            maxFreq,
            count[s.charAt(right) - 'A']
        );

        // Characters that need to be replaced
        int replacements =
            (right - left + 1) - maxFreq;

        // Window is invalid
        if (replacements > k) {
            count[s.charAt(left) - 'A']--;
            left++;
        }

        // Current window length
        result = Math.max(result, right - left + 1);
    }

    return result;
}
```

```text
Key insight: This is still the sliding window template, but the "validity" condition is different. A window of length len is achievable with at most k replacements if:

len - maxFreq <= k

where maxFreq is the count of the most frequent character currently in the window. (You'd replace all the other characters in the window with that majority character.)

Clever trick: You don't need to shrink the window every time it becomes invalid — you only need to track the maximum window size ever achieved. If the window becomes invalid, shrink it by exactly one from the left (not in a while loop) and move on. This works because you're only interested in the max length, and a window can never need to shrink by more than 1 to become valid again relative to the best window seen so far — maxFreq doesn't need to be perfectly accurate on shrink, since it can only ever help you find an equal-or-larger valid window later.

Variable Sliding Window
        ↓
frequency array/map
        ↓
max frequency
        ↓
window length - max frequency
        ↓
if > k → shrink
        ↓
otherwise → update answer


A A B A B B A
0 1 2 3 4 5 6. k=1

right.      0.   1.   2.    3.      4.      5.      6
            A.   A.   B.    A.      B       B.      A
maxFreq.    1    2.   2     3       3.      3.      3
left        0.   0.   0.    0.      1.      2       3 
maxLength.  1.   2    3.    4.      4.      4.      4
replacement 0.   0.   1.    1.      2.      2.      1

freq        [A] [A2] [A2,B] [A3,B] [A3,B2]  [A2,B3] [A2,B3]
                                   [A2,B2]. [A,B3]. 

```

13. Fruit Into Baskets
    You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.



Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].


Constraints:

1 <= fruits.length <= 105
0 <= fruits[i] < fruits.length

```java
class Solution {
    public int totalFruit(int[] fruits) {
        int maxFruits = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        int left=0;
        for(int right=0; right<fruits.length; right++){
            freq.merge(fruits[right], 1, Integer::sum);
            while(freq.size() > 2) {
                // shrink the window
                int count = freq.get(fruits[left]);
                if(count ==1) {
                    freq.remove(fruits[left]);
                } else {
                    freq.put(fruits[left], count-1);
                }
                left++;

            }
            maxFruits = Math.max(maxFruits, right-left+1);
        }
        return maxFruits;
    }
}


```

it's exactly the "at most 2 distinct" template (same as LeetCode 159) applied to Fruit Into Baskets

14. Max Consecutive Ones III
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.


Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length
 
```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        // variable sliding window problem
        int zeroCount = 0;
        int left = 0;
        int maxOnes = 0;
        for(int right=0; right< nums.length; right++) {
            // add current to the window
            if(nums[right]==0){
                zeroCount++;
            }
            while(zeroCount > k){
                // shrink the window
                if(nums[left]==0){
                    zeroCount--;
                }
                left++;
            }
            maxOnes = Math.max(maxOnes, right-left+1);
        }
        return maxOnes;
    }
}


```

15. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
    Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.

Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2
Explanation: All subarrays are:
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4.
Therefore, the size of the longest subarray is 2.

```java
// Solution using TreeMap

public int longestSubarray(int[] nums, int limit) {

    TreeMap<Integer, Integer> map = new TreeMap<>();
    int left = 0;
    int maxLength = 0;
    for (int right = 0; right < nums.length; right++) {
        map.put(nums[right],
                map.getOrDefault(nums[right], 0) + 1);
        while (map.lastKey() - map.firstKey() > limit) {
            int value = nums[left];
            map.put(value, map.get(value) - 1);
            if (map.get(value) == 0) {
                map.remove(value);
            }
            left++;
        }
        maxLength = Math.max(
                maxLength,
                right - left + 1
        );
    }
    return maxLength;
}
```

```java
// solution using queue

public int longestSubarray(int[] nums, int limit) {

    Deque<Integer> maxDeque = new ArrayDeque<>();
    Deque<Integer> minDeque = new ArrayDeque<>();

    int left = 0;
    int result = 0;

    for (int right = 0; right < nums.length; right++) {

        // Maintain decreasing deque for maximum
        while (!maxDeque.isEmpty()
                && nums[maxDeque.peekLast()] < nums[right]) {
            maxDeque.pollLast();
        }

        // Maintain increasing deque for minimum
        while (!minDeque.isEmpty()
                && nums[minDeque.peekLast()] > nums[right]) {
            minDeque.pollLast();
        }

        maxDeque.offerLast(right);
        minDeque.offerLast(right);

        // Shrink while window is invalid
        while (nums[maxDeque.peekFirst()]
                - nums[minDeque.peekFirst()] > limit) {

            if (maxDeque.peekFirst() == left) {
                maxDeque.pollFirst();
            }

            if (minDeque.peekFirst() == left) {
                minDeque.pollFirst();
            }

            left++;
        }

        result = Math.max(result, right - left + 1);
    }

    return result;
}
```

right	Added	left	maxDeque 	minDeque 	max-min	 result
0	    8	     0	        [8]	    [8] 	       0	 [8]                 	1
1	    2	     1	        [2]	    [2]	           0	 [2]	1
2	    4	     1	        [4]	    [2,4]	       2	 [2,4]	2
3	    7	     2	        [7]	    [4,7]	       3	 [4,7]

Logic 

```text
for every right:

    add nums[right]

    maintain maxDeque
    maintain minDeque

    while max - min > limit:
        remove left
        left++

    update answer
    
    Each element is:

added to each deque once
removed from each deque at most once

Therefore:

Time = O(n)
Space = O(n)
```

16. Subarray Product Less Than K
         You are given an array of integers nums and an integer k.

Return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

Example 1:

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count=0;
        if(k <= 1) return count;
        int product = 1;
        int left = 0;
        for(int right=0; right<nums.length;right++){
            // expand the window
            product = product * nums[right];
            // shrink the window
            while(product >= k ){
                product = product / nums[left];
                left++;
            }
            // all the sub arrays starting from left ending at right are valid subarrays where product is less than k so we add them
            count += right-left+1;
        }

        return count;
    }
}
```
Important thing to note here is that there are just positive elements in the array so product will never be negative hence this is 
is a good candidate for sliding window since window can be shrinked when product is greater than k

The window [left, right] always represents the largest valid window ending at right (smallest left such that the product is still < k).
For a fixed right, every subarray [i, right] where left <= i <= right has a product < k too (since removing elements from the front only shrinks the product further, as all values are positive).
So the number of valid subarrays ending at right is simply right - left + 1.

Edge case handled: k <= 1 returns 0 immediately, since nums[i] >= 1 per constraints, meaning no subarray can have a product strictly less than 1.

17. Longest Turbulent Subarray
    Given an integer array arr, return the length of a maximum size turbulent subarray of arr.

A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:

For i <= k < j:
arr[k] > arr[k + 1] when k is odd, and
arr[k] < arr[k + 1] when k is even.
Or, for i <= k < j:
arr[k] > arr[k + 1] when k is even, and
arr[k] < arr[k + 1] when k is odd.


Example 1:

Input: arr = [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]

following solution is using sliding window technique

```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        // we use integer compare
        // 0 means equality no previous comparison
        // 1 means last comparison was >
        // -1 means last comparison was <
        // we want a > b < c > d < and so on

        int maxLength = 1;
        int left = 0;
        int prevSign = 0;
        if(arr.length == 1) return maxLength;
        for(int right = 1; right < arr.length; right++){

            int cmp = Integer.compare(arr[right-1], arr[right]);

            if(cmp == 0){
                // turbulence is broken entirely start from current position again
                left = right;
                prevSign = 0;
            } else if(cmp == prevSign) {
                // turbulence broken at this position restart from prev 
                left = right - 1;
                prevSign = cmp;
            } else {
                // direction flipped (or first comparison) -> extend window
                prevSign = cmp;
            }
            maxLength = Math.max(maxLength, right-left+1);
        }
        return maxLength;
    }
}
/*
9 > 4 > 2 < 10 > 7 < 8 = 8 > 1 > 9

9 4 2 10 7 8 8 1 9

right-1.   9. 4. 2. 10. 7.  8  8  1. 
right.     4  2  10  7  8   8  1  9
index.     1. 2.  3. 4. 5.  6. 7. 8
cmp.       1. 1. -1. 1. -1  0. 1. -1
prevSign   1  1  -1. 1. -1. 0. 1. -1
left.      0. 1.  1. 1.  1. 6. 6.  6
maxLen.    2. 2.  3. 4.  5. 5. 5.  5

if x < y then -1
if x > y then  1
else           0 

4,8,12,16
index.   1.  2.  3.  
right-1. 4.  8.  12
right.   8.  12. 16
cmp.     -1.  -1. -1
prevSign -1.  -1. -1
left      0.   1.  2
max.      2.   2.  2

*/
```

Why it works:

cmp tells us whether arr[right-1] > arr[right] (1), < (-1), or == (0).
A turbulent subarray requires signs to strictly alternate. So:
If cmp == 0: the pair breaks any turbulence — window restarts fresh at right.
If cmp == prevSign: two consecutive comparisons went the same direction (e.g., > > or < <), which isn't turbulent — window restarts at right - 1 (the pair [right-1, right] is still valid on its own).
Otherwise (cmp != prevSign, including the first step where prevSign = 0): the sign flipped, so we can extend the window.

Complexity:

Time: O(n) — single pass, left only moves forward.
Space: O(1)

Edge case handled: arrays with length 1 return maxLen = 1 by default since the loop starting at right = 1 never executes when n == 1.

18. Get Equal Substrings Within Budget
    "
    You are given two strings s and t of the same length and an integer maxCost.

You want to change s to t. Changing the ith character of s to ith character of t costs |s[i] - t[i]| (i.e., the absolute difference between the ASCII values of the characters).

Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of t with a cost less than or equal to maxCost. If there is no substring from s that can be changed to its corresponding substring from t, return 0.



Example 1:

Input: s = ""abcd"", t = ""bcdf"", maxCost = 3
Output: 3
Explanation: ""abc"" of s can change to ""bcd"".
That costs 3, so the maximum length is 3.
Example 2:

Input: s = ""abcd"", t = ""cdef"", maxCost = 3
Output: 1
Explanation: Each character in s costs 2 to change to character in t,  so the maximum length is 1.
Example 3:

Input: s = ""abcd"", t = ""acde"", maxCost = 0
Output: 1
Explanation: You cannot make any change, so the maximum length is 1.


Constraints:

1 <= s.length <= 105
t.length == s.length
0 <= maxCost <= 106
s and t consist of only lowercase English letters."

```java
"class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int cost = 0;
        int maxLength = 0;
        int left = 0;
        for(int right = 0; right < s.length();right++){
            cost = cost + Math.abs(s.charAt(right) - t.charAt(right));
            while(cost > maxCost){
                cost = cost - Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            maxLength = Math.max(maxLength, right-left +1);
        }
        return maxLength;
    }
}
/*
abcd.  97 98 99  100
bcdf.  98 99 100 102
----------------------
        1.  1   1. 2

abcd. 97   98  99  100
cdef. 99. 100  101 102
-----------------------
      2.    2.  2.   2

      */
```

"s = ""abcdefghi""
t = ""bcdfghijkl""
maxCost = 5


s:       a b c d e f g h i
t:       b c d f g h i j k
-----------------
cost:    1 1 1 2 2 2 2 2 2
index:   0 1 2 3 4 5 6 7 8

right 0  1  2. 3  4. 4. 4. 5  5
cost  1. 2. 3  5. 7. 6  5. 7. 5
left. 0. 0. 0. 0. 1  2. 3  4.
max   1. 2. 3. 4. 4  4. 4. 4

1. For each index, calculate the change cost:
   abs(s[i] - t[i]).
2. Use a variable-size sliding window:
    - right expands the window and adds the current cost.
    - If totalCost > maxCost, move left forward and subtract the leftmost cost.
    - Whenever the window is valid (totalCost <= maxCost), update:
    - maxLength = max(maxLength, right - left + 1).
3. Return maxLength.


Longest contiguous substring + total cost ≤ limit → Variable Sliding Window

Time: O(n)
Space: O(1)"

19. 76. Minimum Window Substring

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.

```java
class Solution {
    public String minWindow(String s, String t) {

        if (s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> need = new HashMap<>();

        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();

        int left = 0;
        int formed = 0;
        int required = need.size();

        int minLength = Integer.MAX_VALUE;
        int minLeft = 0;

        for (int right = 0; right < s.length(); right++) {

            char c = s.charAt(right);

            // Add character to window
            window.put(c, window.getOrDefault(c, 0) + 1);

            // Did we just satisfy this character?
            if (need.containsKey(c)
                    && window.get(c).intValue() == need.get(c).intValue()) {
                formed++;
            }

            // Window is valid
            while (formed == required) {

                // Update answer
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }

                // Remove left character
                char leftChar = s.charAt(left);

                window.put(leftChar, window.get(leftChar) - 1);

                // Did removing it make window invalid?
                if (need.containsKey(leftChar)
                        && window.get(leftChar) < need.get(leftChar)) {
                    formed--;
                }

                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(minLeft, minLeft + minLength);
    }
}
```
```text
right expands
      ↓
add character
      ↓
did we satisfy a required frequency?
      ↓
yes → formed++
      ↓
is window valid?
      ↓
YES
      ↓
record answer
      ↓
remove left
      ↓
did we lose a required character?
      ↓
yes → formed--
      ↓
stop shrinking
      ↓
right expands again

Why it works:

need records how many of each character t requires.
window tracks counts of characters currently in [left, right].
formed counts how many distinct characters currently meet their required count exactly — not total characters, just how many keys in need are "satisfied." When formed == required, the window contains everything t needs (with at least the right multiplicity).
Once the window is fully valid, we try to shrink from the left as much as possible while it stays valid — every shrink is a chance at a smaller answer, so we record the length before each removal.
Removing s[left] may cause a needed character to drop below its required count, which decrements formed and breaks the while loop, resuming expansion from right.

Complexity:

Time: O(|s| + |t|) — right and left each traverse s at most once; building need is O(|t|).
Space: O(|s| + |t|) for the two hashmaps (bounded by charset size in practice, so effectively O(1) if the alphabet is fixed/small).
```

20. Minimum Window Subsequence

You are given two strings, s1 and s2. Your task is to find the smallest substring in s1 such that s2 appears as a subsequence within that substring.

The characters of s2 must appear in the same sequence within the substring of s1.
If there are multiple valid substrings of the same minimum length, return the one that appears first in s1.
If no such substring exists, return an empty string.
Note: Both the strings contain only lowercase english letters.

Examples:

Input: s1 = "geeksforgeeks", s2 = "eksrg"
Output: "eksforg"
Explanation: "eksforg" satisfies all required conditions. s2 is its subsequence and it is smallest and leftmost among all possible valid substrings of s1.
Input: s1 = "abcdebdde", s2 = "bde"
Output: "bcde"
Explanation:  "bcde" and "bdde" are two substring of s1 where s2 occurs as subsequence but "bcde" occur first so we return that.

```java
class Solution {
    public String minWindow(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        int minLen = Integer.MAX_VALUE;
        int minStart = -1;

        int sPtr = 0;

        while (sPtr < sLen) {
            // Phase 1: advance sPtr until t is fully matched as a subsequence
            int tPtr = 0;
            while (sPtr < sLen) {
                if (s.charAt(sPtr) == t.charAt(tPtr)) {
                    tPtr++;
                    if (tPtr == tLen) break; // fully matched
                }
                sPtr++;
            }

            if (tPtr < tLen) break; // ran out of s before matching all of t — done

            // sPtr now points to the position where the LAST char of t matched
            int end = sPtr;

            // Phase 2: walk backward from 'end' to find the tightest start
            tPtr = tLen - 1;
            int start = end;
            while (tPtr >= 0) {
                if (s.charAt(start) == t.charAt(tPtr)) {
                    tPtr--;
                }
                start--;
            }
            start++; // adjust after overshoot

            // record if this window is smaller
            if (end - start + 1 < minLen) {
                minLen = end - start + 1;
                minStart = start;
            }

            // resume forward search just past this window's start,
            // to look for a possibly better match
            sPtr = start + 1;
        }

        return minStart == -1 ? "" : s.substring(minStart, minStart + minLen);
    }
}
```

Complexity:

Time: O(|s| * |t|) worst case — for each of up to |s| restarts, the backward contraction can take up to O(|t|), and in pathological inputs (e.g. s = "aaaaaaa...a", t = "aa") this repeats often. This is the standard accepted complexity for this problem — no known better general approach.
Space: O(1) extra (excluding the output string).

Order matters — characters of T must appear in the same order inside S.
Unlike Minimum Window Substring, we cannot use just frequency counts.
Use two-pointer scanning, not the normal expand/shrink sliding window.
Forward pass: move right through S to find a complete occurrence of T.
When T is completely matched, backward pass from right to find the smallest possible start.
Backward pass matches T from right to left.
windowStart = i + 1 after the backward scan because i moves one position before the start.
Calculate the window length as end - windowStart + 1.
Keep the shortest window found so far.
After finding a window, restart the forward search from windowStart + 1.
If the forward pass reaches the end without matching all of T, stop.
Core pattern: Forward → find valid subsequence → Backward → minimize window.
Easy memory trick: Forward finds it; backward shrinks it.

/*
a b c d e b d d e  b d e
0 1 2 3 4 5 6 7 8  0 1 2

in the first pass we  match the chars from t 
a  - No match
b  - BINGO
c  - no match
d  - BINGO
e  - BINGO
tPtr = 3
sPtr = 4

now come back to find minimal matching string

e -> e, d->d, c->b b->b
start = 0 -> make it +1 to adjust overshoot
find min window 4 and string bcde (1-4)
now start searching from sptr = start +1 i.e 2

in forward pass we find c d e b d d e
in backward b d d e
start = 6 and it will exhaust sLen
    
*/

21. Minimum Size Subarray Sum
    Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.



Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int sum =0;
        for(int right=0; right<nums.length;right++){
            sum+=nums[right];
            while(sum >= target){
                minLength = Math.min(minLength, right-left+1);
                sum-=nums[left];
                left++;
            }
           
        
        }
        return minLength ==Integer.MAX_VALUE ? 0 : minLength;
    }
}


```

O(n) O(1)

22. Shortest Subarray with Sum at Least K
    Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.

A subarray is a contiguous part of an array.

Example 1:

Input: nums = [1], k = 1
Output: 1
Example 2:

Input: nums = [1,2], k = 4
Output: -1
Example 3:

Input: nums = [2,-1,2], k = 3
Output: 3

```java
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>(); // stores indices into prefixSum, increasing order
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            // 1. Try to close out valid subarrays ending at i:
            //    while the front of deque gives a sum difference >= k, it's a candidate
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                minLen = Math.min(minLen, i - deque.pollFirst());
            }

            // 2. Maintain monotonic increasing prefixSum in the deque:
            //    if prefixSum[i] <= prefixSum[back], the back is useless (worse sum AND longer length)
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
}
```


```text
2, -1, 2, 3, -2, 4.  k = 5


index.        0.  1.  2.  3.   4   5.  
nums[index].  2. -1.  2.  3.  -2.  4

prefix index  0.  1.  2.  3.   4   5.  6
prefix sum.   0.  2.  1.  3.   6.  4.  8

for understanding dequeue written as [sum, index]
i                0        1       2.            3.        4.        5               6
p sum            0        2       1.            3         6.        4               8
>=k              -        F       F             F         T         F               T
minLength                                           (4,0) 4         4.         (6-3)3
mntnc incr?      -        T       F peeklast    T         T         F peeklast.    T
deque.        ([0,0])  ([0,0].    ([0,0]).      ([0,0].   ([3,3]).  ([3,3].     [(4,5), 
                        ->[2,1])                ->[3,3])  ->[6,4]).  ->[4,5]).   ->[8,6]
        
        
i. prefix.  sum>=k  minLength. monotonic.         deque
    sum                         increase break?
    
0.   0.     F.       ~.          F.              [0,0]
1.   2.     F        ~           F               [0,0], [2,1]     
2.   1.     F        ~           T peekLast      [0,0], [1,2]
3.   3.     F        ~           F               [0,0], [1,2], [3,3]
4.   6.     T(6-0). 4 peekfirst                  [1,2], [3,3], 
            T(6-1). 2 peekFirst                  [3,3]
            F(6-3)               F               [3,3], [6,4]         
5.   4.     F                    T peekLast      [3,3], [4,5]
6.   8.     T(8-3)  3 peekFirst                  [4,5]
            

|  i | Prefix | Front check       | Action                           | Deque after processing |
| -: | -----: | ----------------- | -------------------------------- | ---------------------- |
|  0 |      0 | —                 | Add `[0,0]`                      | `[[0,0]]`              |
|  1 |      2 | `2-0 < 5`         | Add `[2,1]`                      | `[[0,0],[2,1]]`        |
|  2 |      1 | `1-0 < 5`         | Remove `[2,1]`, add `[1,2]`      | `[[0,0],[1,2]]`        |
|  3 |      3 | `3-0 < 5`         | Add `[3,3]`                      | `[[0,0],[1,2],[3,3]]`  |
|  4 |      6 | `6-0 ≥ 5` → len 4 | Remove `[0,0]`                   | `[[1,2],[3,3]]`        |
|  4 |      6 | `6-1 ≥ 5` → len 2 | Remove `[1,2]`                   | `[[3,3]]`              |
|  4 |      6 | `6-3 < 5`         | Add `[6,4]`                      | `[[3,3],[6,4]]`        |
|  5 |      4 | `4-3 < 5`         | Remove back `[6,4]`, add `[4,5]` | `[[3,3],[4,5]]`        |
|  6 |      8 | `8-3 ≥ 5` → len 3 | Remove `[3,3]`                   | `[[4,5]]`              |
|  6 |      8 | `8-4 < 5`         | Add `[8,6]`                      | `[[4,5],[8,6]]`        |
```

Complexity:

Time: O(n) — each index is pushed and popped from the deque at most once.
Space: O(n) — for prefixSum array and the deque.

### Pattern - **Prefix Sum + Monotonic Increasing Deque**

### Why not normal Sliding Window?

Because `nums` can contain **negative numbers**.
With negatives:
* expanding the window may decrease the sum
* shrinking the window may increase the sum
So normal sliding-window logic doesn't work reliably.
---

## 1. Prefix Sum

```text
prefix[i] = sum of nums before index i
```

Example:

```text
nums   = [2, -1, 2, 3]
prefix = [0,  2, 1, 3, 6]
          ↑
       boundary
```

### Range sum

```text
sum(nums[L ... R])
= prefix[R + 1] - prefix[L]
```

So we need:

```text
prefix[j] - prefix[i] >= k
```

and the length is:

```text
j - i
```

**Important:** Prefix has `n + 1` elements, so loop through:

```java
i <= n
```

not `i < n`.

---

## 2. What does the Deque store?

The deque stores **prefix indices**.
For understanding, imagine:
```text
[ prefixSum, index ]
```
The corresponding prefix sums are kept in **increasing order**.
Example:
```text
Deque:

[0,0] → [1,2] → [3,3]
```

means:

```text
prefix[0] = 0
prefix[2] = 1
prefix[3] = 3
```

So prefix values are:

```text
0 < 1 < 3
```

---

# 3. TWO while loops — remember their jobs

## LOOP 1: Check from FRONT

```java
while (!deque.isEmpty()
       && prefixSum[i] - prefixSum[deque.peekFirst()] >= k)
```

Meaning:

> "Can I make a valid subarray using the oldest prefix index?"

If yes:

```java
minLength = Math.min(minLength, i - deque.pollFirst());
```

Then **remove it** because we found a valid subarray.

### Why `while`, not `if`?

There may be multiple valid starting points.

Example:

```text
prefix[i] = 6

6 - 0 = 6 >= 5   → valid
6 - 1 = 5 >= 5   → valid
6 - 3 = 3 < 5    → stop
```

So keep checking the new front.

**Memory:**

> 🟢 FRONT = find valid windows

---

# 4. LOOP 2: Remove from BACK

```java
while (!deque.isEmpty()
       && prefixSum[i] <= prefixSum[deque.peekLast()])
```

Meaning:

> "Is the new prefix sum smaller than or equal to the last prefix sum?"

If yes, remove the old one.

Why?

Suppose:

```text
old: prefix[1] = 5
new: prefix[2] = 3
```

The new index is:

```text
later → index 2
```

and has:

```text
smaller prefix → 3
```

So index 1 is useless.

The new candidate is:

* later → potentially shorter
* smaller prefix → easier to reach `k`

Therefore remove the old candidate.

**Memory:**

> 🔵 BACK = remove dominated prefix sums

---

# 5. Overall Mental Model

Think:

```text
PREFIX SUM
    ↓
Convert subarray sum into
prefix[j] - prefix[i]
    ↓
Need >= K
    ↓
FRONT of deque
find valid subarray
    ↓
BACK of deque
remove useless candidates
```

### One-line memory trick

> **Front finds valid windows. Back maintains increasing prefix sums.**

---


## ⚠️ Mistake to remember

### Most important mistake from this problem:

```java
for (int i = 0; i < n; i++)
```

❌ Wrong for processing prefix sums.

Use:

```java
for (int i = 0; i <= n; i++)
```

because:

```text
nums has n elements
prefix has n + 1 elements The final prefix boundary `prefix[n]` is important.
```
23. 995. Minimum Number of K Consecutive Bit Flips
         You are given a binary array nums and an integer k.

A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.

Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not possible, return -1.

A subarray is a contiguous part of an array.

Example 1:

Input: nums = [0,1,0], k = 1
Output: 2
Explanation: Flip nums[0], then flip nums[2].
Example 2:

Input: nums = [1,1,0], k = 2
Output: -1
Explanation: No matter how we flip subarrays of size 2, we cannot make the array become [1,1,1].
Example 3:

Input: nums = [0,0,0,1,0,1,1,0], k = 3
Output: 3
Explanation:
Flip nums[0],nums[1],nums[2]: nums becomes [1,1,1,1,0,1,1,0]
Flip nums[4],nums[5],nums[6]: nums becomes [1,1,1,1,1,0,0,0]
Flip nums[5],nums[6],nums[7]: nums becomes [1,1,1,1,1,1,1,1]

Approach

- We are given an array nums consisting only of 0s and 1s. We need to make sure that the nums array has all elements as 1s. We can perform k-bit flips, meaning selecting a contiguous subarray of length k and flipping every 0 to 1 and every 1 to 0 within that subarray.

In the end, we need to return the minimum number of k-bit flips needed to ensure there are no 0s in the array. If not possible, return -1.

- A naive approach to solving this problem is to iterate the array from left to right and flip subarrays whenever a 0 is encountered. This ensures that each 0 is flipped as soon as it is detected, ensuring no 0s remain in the array, assuming the k-grouping is possible. However, due to the problem constraints, this approach is not feasible.

- Two properties make the greedy solution possible. First, the order of flips doesn't matter, so I can process the array from left to right and decide where each flip starts. Second, only the parity of the number of flips affecting a position matters: an odd number of flips reverses the bit, while an even number cancels out. Therefore, I only need to track the number of currently active flips rather than actually flipping the elements.
If the effective bit is 0, I must flip here; I only need to track how many previous flips are still active.

The core idea

flipCount = how many active flip-windows currently cover index i. Each individual flip toggles a bit (0→1 or 1→0). If a bit gets toggled an even number of times, it ends up back at its original value. If toggled an odd number of times, it ends up flipped.

So you don't care about the exact value of flipCount — you only care whether it's even or odd. That's why flipCount % 2 (or flipCount & 1) is enough, instead of tracking the full count.

Why "even cancels out"

Think of a light switch. Flip it once → ON. Flip it again → OFF (back to original). Flip it a third time → ON again.

0 flips → original
1 flip → toggled
2 flips → original (the two flips cancel)
3 flips → toggled
4 flips → original

Pattern: even count = original bit, odd count = toggled bit.

Why this matters for the algorithm

This is exactly why the diff-array / expiry-tracking approach works with a simple counter instead of simulating every actual flip:

java
int currentBit = (nums[i] & 1) ^ (flipCount & 1);
nums[i] & 1 → the original bit
flipCount & 1 → whether an odd or even number of flips currently affect this position
XOR them → the actual bit value right now, after all pending flips are accounted for

This is what makes the whole algorithm O(n) instead of O(n·k) — instead of physically flipping up to k positions every time (which is what the brute-force / naive simulation would do), you just maintain a running parity counter and derive the effective bit in O(1) per index.

```java

class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        boolean[] flips = new boolean[n];
        int flipCount =0 ;
        int ans = 0;
        for(int i=0;i<n;i++){
            if(i>=k && flips[i-k]){
                flipCount--;
            }
            int currentBit = (nums[i] + flipCount)%2;
            if(currentBit==0){
                if(i+k > n){
                    return -1;
                }

                flips[i] = true;
                flipCount++;
                ans++;

            }
        }
        return ans;
    }
}

class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
       // boolean[] flips = new boolean[n];
        int flipCount =0 ;
        int ans = 0;
        for(int i=0;i<n;i++){
            if(i>=k && nums[i-k]==2){
                flipCount--;
            }
            int currentBit = (nums[i] + flipCount)%2;
            if(currentBit==0){
                if(i+k > n){
                    return -1;
                }

                nums[i] = 2;
                flipCount++;
                ans++;

            }
        }
        return ans;
    }
}
/*
0,0,0,1,0,1,1,0. k=3


        0  0. 0  1 0 1 1 0
        0. 1. 2. 3 4 5 6 7

        [F,F,F,F,F,F,F]

condition is if i>=k && flipped[i-k]

i nums[i] cond current bit flipCount   ans    flipped
0.  0.    F.    (0+0)%2=0.  0->1        1.  [T,F,F,F,F,F,F]
        1.  0.    F.    (0+1)%2=1   1
        2.  0.    F     (0+1)%2=1   1.
        3.  1.    T     (1+0)%2=1   1->0
        4.  0.    F.    (0+0)%2=0.  0->1.        2.  [T,F,F,F,T,F,F]
        5.  1.    F.    (1+1)%2=0   1->2         3   [T,F,F,F,T,T,F]
        6.  1     F.    (1+2)%2=1.  2
        7.  0.    T     (0+1)%2=1   1

so final answer is 3*/
```
24.  1658. Minimum Operations to Reduce X to Zero

You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.

Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1
Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.


1. Brute Force
   Intuition
   We can only remove elements from the left or right ends of the array. The total sum we remove must equal x. We try every possible combination: take some elements from the left (prefix) and some from the right (suffix), checking if their combined sum equals x. For each valid combination, we track the min number of elements removed.

Algorithm
First, check all suffix-only cases by iterating from the right and accumulating a suffixSum. If suffixSum == x, update res.
Then, for each prefix length (iterating from left), calculate prefixSum. If prefixSum == x, update res.
For each prefix, try combining it with suffixes by iterating from the right. If prefixSum + suffixSum == x, update res with the combined count.
Return -1 if no valid combination is found, otherwise return res.
O(n^2) O(1)

2. Sliding Window
Intuition
Building on the same insight as the hash map approach, we want the longest subarray summing to target = total - x. Since all elements are positive, the subarray sum increases as we expand and decreases as we shrink. This monotonic property allows us to use a sliding window: expand the right boundary to include more elements, and shrink from the left when the sum exceeds the target.

Algorithm
Calculate target = total - x. This is the sum we want the middle subarray to have.
Use two pointers l and r to define the current window, with curSum tracking the window sum.
Expand r to include elements. If curSum exceeds target, shrink from l until curSum <= target.
When curSum == target, record the window length if it is the max seen.
The answer is n - maxWindow. Return -1 if no valid window was found.
```java
class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) total += num;

        int target = total - x;
        if (target < 0) return -1; // x too big, can't remove enough even by taking everything

        int left = 0, sum = 0, maxLen = -1;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;
            }

            if (sum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen == -1 ? -1 : n - maxLen;
    }
}
```
O(n) and O(1)

```text
nums = [1, 1, 4, 2, 3], x = 5
total = 11, target = total - x = 6

find longest contiguous subarray length whose sum is target

x= 5 total= 11-5 = 6


1. 1. 4. 2. 3
0. 1. 2. 3  4


1. 1. 4. record max, expand right
1+1+4+2. remove 1 from left as sum > target (8>6)

1+4+2 remove 1 from left as sum > target (7>6)

4+2 record max expand right

4+2+3 9 , remove 4
2+3 5 stop

max length so far 3

n-max = 5-3 = 2 is the final answer

```

Common Pitfalls
Not Recognizing the Problem Transformation
The key insight is that removing elements from both ends that sum to x is equivalent to finding the longest contiguous subarray that sums to total - x. Many solutions fail because they try to directly simulate removing from both ends, leading to inefficient or incorrect approaches.

Forgetting to Handle Edge Cases
When total == x, the answer is n (remove all elements). When total < x, the answer is -1 (impossible). When target = total - x is negative, return -1. Missing any of these edge cases causes wrong answers on specific test cases.

Overlapping Prefix and Suffix in Brute Force
In approaches that combine prefix and suffix sums, you must ensure the prefix and suffix do not overlap (i.e., the suffix must start after the prefix ends). A common bug is allowing j <= i instead of j > i, which double-counts elements and produces incorrect results.

25. 1574. Shortest Subarray to be Removed to Make Array Sorted

Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.

Return the length of the shortest subarray to remove.

A subarray is a contiguous subsequence of the array.



Example 1:

Input: arr = [1,2,3,10,4,2,3,5]
Output: 3
Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
Another correct solution is to remove the subarray [3,10,4].

```java
class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int left = 0;
        // find the sorted first half of the array
        while(left < n-1 && arr[left] <= arr[left+1]){
            left++;
        }
        // array is already sorted
        if(left == n-1) return 0;

        // find the sorted right half of the array
        int right=n-1;
        while(right > 0 && arr[right-1] <= arr[right]){
            right--;
        }
         
        // remove everything after prefix or everything before suffix
        int min = Math.min(n-left-1, right);

        // merge both prefix and suffix to keep array sorted

        int i=0;
        int j= right;
        while(i<=left && j<n){
            if(arr[i]<=arr[j]){
                min = Math.min(min, j-i-1);
                i++;
            } else {
                j++;
            }
        }
        return min;
    }
}
/*
 1,2,3,10,4,2,3,5


1 2 3 10 4 2 3 5
0 1 2  3 4 5 6 7

element               1  2  3   10  4   2  3  5
left.                 0. 1. 2.  3.  4.  5. 6. 7.
left <= left+1        T. T. T.   F

left = 3

right.                0. 1. 2.  3.  4.  5. 6. 7.
right < right-1.                        F  T. T
right = 5

8-3-1 = 4, 5

left 3 right 5

i            = 0       1.       2.      2       3
j            = 5       5        5       6       6
n[i] <= n[j] = 1<=2=T  2<=2=T   3<=2=F. 3<=3=T  10<=3=F
result       min(4,5). min(4,3).        min(3,3)

final answer is 3
*/
```

Main intuition

Keep a sorted piece on the left and a sorted piece on the right, and remove the smallest middle portion that makes them connect.

Time: O(n)
Space: O(1)

25. Minimum Swaps to Group All 1's Together — LeetCode 1151 (premium)
    You are given a binary array arr[] consisting only of 0s and 1s. Determine the minimum swaps required to group all the 1s together in a contiguous subarray.

If the array contains no 1s, return -1.

Examples:

Input: arr[] = [1, 0, 1, 0, 1]
Output: 1
Explanation: Only 1 swap is required to group all 1's together. Swapping index 1 and 4 will give arr[] = [1, 1, 1, 0, 0].
Input: arr[] = [1, 0, 1, 0, 1, 1]
Output: 1
Explanation: Only 1 swap is required to group all 1's together. Swapping index 0 and 3 will give arr[] = [0, 0, 1, 1, 1, 1].

```java
class Solution {
    public int minSwaps(int[] arr) {
        // code here
        // count the number of ones
        
        int k = Arrays.stream(arr).sum();
        
        if(k == 0) return -1;
        
        // find the window which has max number of ones because 
        int windowOnes = 0;
        
        
        for(int i=0;i<k;i++){
            windowOnes+=arr[i];    
        }
        int maxOnes = windowOnes;
        for(int i=k;i<arr.length;i++){
            windowOnes+=arr[i];
            windowOnes-=arr[i-k];
            maxOnes = Math.max(maxOnes, windowOnes);
        }
        
        return k-maxOnes;
        
        
    }
}

```

equivalently the most 1s.

Use a fixed-size sliding window. Keep total as the number of 1s in the current window. Initialize it for the first count1 elements, then slide right by adding the new element and removing the element that left the window. For every window, required swaps are count1 - total. Track the minimum of this value.

This gives an O(n) solution with O(1) extra space.

26. Longest Substring with At Least K Repeating Characters
    Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

if no such substring exists, return 0.



Example 1:

Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

```java
class Solution {
    public int longestSubstring(String s, int k) {
        if(s == null || s.isEmpty() || k > s.length()) return 0;
        // solution using sliding window

        // find the number of unique character in a string
        int maxUniq  = findUniqCharCount(s);
        int[] countMap = new int[26];
        int result = 0;
        for(int target = 1; target <=maxUniq; target++) {
            int left = 0;
            Arrays.fill(countMap, 0);
            int uniqCount = 0, atleastK = 0;
            for(int right = 0; right < s.length();right++){
                char ch  = s.charAt(right);
                if(countMap[ch-'a'] == 0) uniqCount++;
                countMap[ch - 'a']++;
                if(countMap[ch -'a'] == k) atleastK++;

                while(uniqCount > target ){
                    int lch = s.charAt(left);
                    if(countMap[lch -'a'] == k) atleastK--;
                    countMap[lch- 'a']--; 
                    if(countMap[lch - 'a'] == 0) uniqCount--;
                    left++;
                }
                if(uniqCount == target && atleastK == target) {
                    result = Math.max(result, right-left+1);
                }

            }
            
        }
        return result;

        
    }

    private int findUniqCharCount(String s){
        boolean[] map = new boolean[26];
        int maxUniqChars = 0;
        for(char ch: s.toCharArray()){
            if(!map[ch-'a']){
                maxUniqChars++;
                map[ch-'a'] = true;
            }
        }
        return maxUniqChars;
    }

    private int bruteforce(String s, int k){
        if(s==null || s.isEmpty() || k > s.length()) return 0;
        int[] countMap = new int[26];
        int n = s.length();
        int result = 0;
        for(int start = 0; start < n; start++){
            Arrays.fill(countMap, 0);
            for(int end = start; end < n; end++){
                countMap[s.charAt(end)-'a']++;
                if(isValid(k, countMap)){
                    result = Math.max(result, end-start+1);
                }
            }
        }
        return result;
       
    }

    private boolean isValid(int k, int[] countMap){
        int countLetters = 0, countAtLeastK = 0;
        for(int freq : countMap){
            if(freq > 0) countLetters++;
            if(freq >= k) countAtLeastK++;
        }
        return countLetters == countAtLeastK;
    }
}
```

We want to find the longest substring in a given string s where each character is repeated at least k times. This is an interesting problem that can be solved using different algorithm paradigms like Divide and Conquer and the Sliding Window Approach. We will start by discussing the brute force approach, moving towards more efficient implementations.

The naive approach would be to generate all possible substrings for a given string s. For each substring, we must check if all the characters are repeated at least k times. Among all the substrings that satisfy the given condition, return the length of the longest substring.

Algorithm

Generate substrings from string s starting at index start and ending at index end.
Use the countMap array to store the frequency of each character in the substring.
The isValid method uses countMap to check whether every character in substring has at least k frequency.
Track the maximum substring length and return the result.

Complexity Analysis

Time Complexity : O(n*n), where n is equal to length of string s. The nested for loop that generates all substrings from string s takes O(n*n)
time, and for each substring, we iterate over countMap array of size 26.
This gives us time complexity as O(26⋅n *n )


This approach is exhaustive and results in Time Limit Exceeded (TLE).

Space Complexity: O(1) We use constant extra space of size 26 for countMap array.

using sliding window

Intuition

There is another intuitive method to solve the problem by using the Sliding Window Approach. The sliding window slides over the string s and validates each character. Based on certain conditions, the sliding window either expands or shrinks.

A substring is valid if each character has at least k frequency. The main idea is to find all the valid substrings with a different number of unique characters and track the maximum length. Let's look at the algorithm in detail.

Algorithm

Find the number of unique characters in the string s and store the count in variable maxUnique. For s = aabcbacad, the unique characters are a,b,c,d and maxUnique = 4.

Iterate over the string s with the value of currUnique ranging from 1 to maxUnique. In each iteration, currUnique is the maximum number of unique characters that must be present in the sliding window.

The sliding window starts at index windowStart and ends at index windowEnd and slides over string s until windowEnd reaches the end of string s. At any given point, we shrink or expand the window to ensure that the number of unique characters is not greater than currUnique.

If the number of unique character in the sliding window is less than or equal to currUnique, expand the window from the right by adding a character to the end of the window given by windowEnd

Otherwise, shrink the window from the left by removing a character from the start of the window given by windowStart.

Keep track of the number of unique characters in the current sliding window having at least k frequency given by countAtLeastK. Update the result if all the characters in the window have at least k frequency.


s = "abababbdabcbabc"
k = 2

target = 1
----------------
We allow only 1 unique character.

Longest valid substring = "bb"

MAX LENGTH = 2


target = 2
----------------
We allow 2 unique characters.

Longest valid substring = "abababb"

Counts:
a → 3
b → 4

Both occur at least k = 2 times.

MAX LENGTH = 7


target = 3
----------------
We allow 3 unique characters.

Longest valid substring = "abcbabc"

Counts:
a → 2
b → 3
c → 2

All occur at least k = 2 times.

MAX LENGTH = 6


RESULT
----------------
currUnique = 1 → 2
currUnique = 2 → 7
currUnique = 3 → 6

MAX(2, 7, 6) = 7


KEY IDEA
----------------
Try every possible number of unique characters:

1 → longest valid substring with 1 unique character
2 → longest valid substring with 2 unique characters
3 → longest valid substring with 3 unique characters
...
26 → longest valid substring with 26 unique characters

Take the maximum.

For this string, there are only 4 unique characters:
a, b, c, d

So currUnique > 4 cannot produce a valid window.


PATTERN: 1 → 26 DISTINCT CHARACTERS + SLIDING WINDOW

395  — Longest Substring with At Least K Repeating Characters
2067 — Number of Equal Count Substrings
2953 — Count Complete Substrings
3713 — Longest Balanced Substring I

related distinct-character sliding-window problems,

3    — Longest Substring Without Repeating Characters
159  — Longest Substring with At Most Two Distinct Characters
340  — Longest Substring with At Most K Distinct Characters
992  — Subarrays with K Different Integers


27. Subarrays with K Different Integers
    Given an integer array nums and an integer k, return the number of good subarrays of nums.

A good array is an array where the number of different integers in that array is exactly k.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.

Example 1:

Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]

Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].

```java
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k-1);
    }

    private int atMost(int[] nums, int k){
        int[] freq = new int[nums.length + 1]; // numbers are from 1 to n
        int left = 0, distinct = 0;
        int count = 0;
        for(int right = 0; right < nums.length; right++){
            if(freq[nums[right]]==0) distinct++;
            freq[nums[right]]++;

            while(distinct > k){
                if(freq[nums[left]]==1) distinct--;
                freq[nums[left]]--;
                left++;
            }
            count += right-left+1;
        }
        return count;
    }
}

// brute 

class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[n + 1]; // values are in [1, n]
            int distinct = 0;

            for (int j = i; j < n; j++) {
                if (freq[nums[j]]++ == 0) distinct++;

                if (distinct == k) count++;
                else if (distinct > k) break; // adding more can only increase distinct
            }
        }

        return count;
    }
}
```

notes

xactly(K) = atMost(K) - atMost(K - 1)

atMost(K) counts all subarrays with d ≤ K, which means d = 0, 1, 2, ..., K
atMost(K-1) counts all subarrays with d ≤ K-1, which means d = 0, 1, 2, ..., K-1

atMost(K)    = [d=1] + [d=2] + ... + [d=K-1] + [d=K]
atMost(K-1)  = [d=1] + [d=2] + ... + [d=K-1]
--------
difference                                    [d=K]  = exactly(K)


count += right - left + 1 adds up all the valid subarrays that end at right.

The key fact: after the while loop, the window [left, right] has at most K distinct integers. Any subarray inside it that also ends at right must then have at most K distinct integers too, because a smaller window can only have fewer or equal distinct values.

Those subarrays start at left, left+1, ..., right. The number of start positions is:

right - left + 1

Example: nums = [1, 2, 1, 2, 3], k = 2, and say right = 3 with left = 0. The window is [1, 2, 1, 2], which has 2 distinct values. Subarrays ending at index 3:

start = 0 -> [1, 2, 1, 2]   valid
start = 1 -> [2, 1, 2]      valid
start = 2 -> [1, 2]         valid
start = 3 -> [2]            valid

That is 4 subarrays, and right - left + 1 = 3 - 0 + 1 = 4.

----

how many contiguous subarrays can be formed with n element
formula is n * (n-1) / 2

27. 1248. Count Number of Nice Subarrays

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

a brute force approach to solve this problem would be  generate every possible subarray and count how many odd numbers it contains.
“But I notice that I don't care about the actual values — I only care whether a number is odd or even. So conceptually I can convert the array into 1 for odd and 0 for even.”

Now the problem becomes:
Count subarrays containing exactly k ones.
exactly k
↓
atMost(k) - atMost(k - 1)

```java
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        //return brute(nums, k);
        return countNumberOfOnes(nums, k) - countNumberOfOnes(nums, k-1);
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
}

/*
 nums = [1,1,2,1,1], k = 3

1 1 0 1 1  k = 3

atmost(3) - atmost(2).  == 14 - 12 = 2

right.       0.   1.   2.  3.     4
left         0.   0.   0.  0      1
oddcount.    1.   2.   2.  3.     4->3 

right-left+1 1.   2.   3.  4      4
count.       1.   3.   6.  10.   14


right.       0.   1.   2.  3.     4
left         0.   0.   0.  1      2
oddcount.    1.   2.   2.  3-> 2. 3->2

right-left+1 1.   2.   3.  3.     3
count.       1.   3.   6.  9.    12

*/
```

28. Binary Subarrays With Sum — LeetCode 930
    Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.

A subarray is a contiguous part of the array.



Example 1:

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

The brute-force approach is to consider every possible subarray. Since the array contains only 0s and 1s, I can maintain the sum while extending the right pointer. Whenever the sum equals goal, I increment the answer.

```text
int ans = 0;

for (int i = 0; i < nums.length; i++) {
    int sum = 0;

    for (int j = i; j < nums.length; j++) {
        sum += nums[j];

        if (sum == goal) {
            ans++;
        }
    }
}
// Time  = O(n²)
//Space = O(1)
```

We want: sum == goal but standard sliding window is naturally good at sum <= goal  So use the one of the pattern of sliding window 
exactly goal = atMost(goal) - atMost(goal - 1)

e.g goal = 2 -> exactly 2  = atMost(2) - atMost(1)
atMost(2) = sum 0 + sum 1 + sum 2
atMost(1) = sum 0 + sum 1
subtract = sum exactly 2

```java
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private int atMost(int[] nums, int goal) {
        if (goal < 0) {
            return 0;
        }

        int left = 0;
        int sum = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum > goal) {
                sum -= nums[left];
                left++;
            }

            ans += right - left + 1;
        }

        return ans;
    }
}
```
27. 1358. Number of Substrings Containing All Three Characters
          Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.



Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 

Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
Example 3:

Input: s = "abc"
Output: 1

Constraints:

3 <= s.length <= 5 x 104
s only consists of 'a', 'b' or 'c' characters.

```java
class Solution {
    public int numberOfSubstrings(String s) {
        if(s == null || s.isEmpty()) return 0;
        int left = 0;
        int countA = 0, countB = 0, countC = 0;
        int count = 0;
        for(int right = 0; right < s.length(); right++){
            if(s.charAt(right) == 'a') countA++;
            if(s.charAt(right) == 'b') countB++;
            if(s.charAt(right) == 'c') countC++;

            while(countA > 0 && countB > 0 && countC > 0 ){
                count += s.length() -  right;
                if(s.charAt(left) == 'a') countA--;
                if(s.charAt(left) == 'b') countB--;
                if(s.charAt(left) == 'c') countC--;
                left++;
            }
        }
        return count;
    }

    private int brute(String s){
         int count = 0;
        if(s == null || s.isEmpty()) return count;
        
        for(int i = 0;i<s.length();i++){
            int countA = 0, countB =0 , countC = 0;
            for(int j=i;j<s.length();j++) {
                if(s.charAt(j) == 'a') countA++;
                if(s.charAt(j) == 'b') countB++;
                if(s.charAt(j) == 'c') countC++;

                if(countA > 0 && countB > 0 && countC > 0) {
                    count++;
                }
            }
        }
        return count;

    }


}
```

The brute-force approach is to generate every substring and keep track of whether I've seen a, b, and c.

sliding window

Rule:
Expand right.
When window is VALID (has a,b,c):
count += n - right
shrink from left while still valid.

Why n - right?
Once [left...right] is valid, extending right keeps it valid.
Possible endings = right, right+1, ..., n-1
Count = n - right

TRACE:

right=0 → 'a'
window = [a]
missing b,c
count = 0

right=1 → 'b'
window = [ab]
missing c
count = 0

right=2 → 'c'
window = [abc] → VALID
count += 6-2 = 4

Valid substrings:
abc
abca
abcab
abcabc

Shrink:
remove a → [bc]
left=1 → invalid


right=3 → 'a'
window = [bca] → VALID
count += 6-3 = 3

Valid substrings:
bca
bcab
bcabc

Shrink:
remove b → [ca]
left=2 → invalid


right=4 → 'b'
window = [cab] → VALID
count += 6-4 = 2

Valid substrings:
cab
cabc

Shrink:
remove c → [ab]
left=3 → invalid


right=5 → 'c'
window = [abc] → VALID
count += 6-5 = 1

Valid substring:
abc

Shrink:
remove a → [bc]
left=4 → invalid


TOTAL:
4 + 3 + 2 + 1 = 10

a b c a b c
0 1 2 3 4 5

right countA countB countC left. len-right.  count
0       1       0.   0.      0
1.      1.      1.   0.      0
2       1.      1.   1.      0.    4           4
0       1.   1.      1
3       1.      1.   1             3
1.      0    1       2                 7
4       1       1.   1             2           9
1       1    0       3     
5       1.      1.   1             1           10
0       1.   1       4        


28. Count Vowel Substrings of a String
    A substring is a contiguous (non-empty) sequence of characters within a string.

A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u') and has all five vowels present in it.

Given a string word, return the number of vowel substrings in word.



Example 1:

Input: word = "aeiouu"
Output: 2
Explanation: The vowel substrings of word are as follows (underlined):
- "aeiouu"
- "aeiouu"
  Example 2:

Input: word = "unicornarihan"
Output: 0
Explanation: Not all 5 vowels are present, so there are no vowel substrings.
Example 3:

Input: word = "cuaieuouac"
Output: 7
Explanation: The vowel substrings of word are as follows (underlined):
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
 
```java
class Solution {
    public int countVowelSubstrings(String word) {
        //return brute(word);
        int n = word.length();
        int count =0;
        int i=0;
        while(i<n){
            if(!isVowel(word.charAt(i))){
                i++;
                continue;
            }
            int[] lastSeen = new int[5];
            Arrays.fill(lastSeen, -1);
            int runStart = i;

            while(i<n && isVowel(word.charAt(i))){
                lastSeen[vowelIndex(word.charAt(i))] = i;
                int minLast = Math.min(Math.min(lastSeen[0], lastSeen[1]), Math.min(lastSeen[2], lastSeen[3]));
                minLast = Math.min(minLast, lastSeen[4]);

                if(minLast >= runStart) {
                    count+= minLast-runStart+1;
                }
                i++;
            }
            
        }
        return count;
    }

    private int brute(String word){
        int ans = 0;
        for(int i=0;i<word.length();i++){
            Set<Integer> set = new HashSet<>();
            for(int j=i;j<word.length();j++){
                if(!isVowel(word.charAt(j))){
                    break;
                }
                set.add(vowelIndex(word.charAt(j)));
                if(set.size() == 5) ans++;
            }
        }
        return ans;
    }

    private boolean isVowel(char c){
        return "aeiou".indexOf(c) >=0;
    }

    private int vowelIndex(char c){
        return "aeiou".indexOf(c);
    }

}
```

c u a i e u o u a c
0 1 2 3 4 5 6 7 8 9

a e i o u
0 1 2 3 4

i. isVowel min  lastSeen                             runstart count
0. F        -1  a->-1, e->-1, i->-1, o->-1, u->-1.    1       2-1+1. = 2
   1  T        -1  a->-1, e->-1, i->-1, o->-1, u->1
2. T.       -1. a->2,  e->-1, i->-1, o->-1, u->1
3. T        -1. a->2,  e->-1, i->3,  o->-1, u->1
4. T        -1  a->2,  e->4,  i->3,  o->-1, u->1
5. T        -1  a->2,  e->4,  i->3,  o->-1, u->5
6. T         2  a->2,  e->4,  i->3,  o->6,  u->5
7. T         2. a->2,  e->4,  i->3,  o->6,  u->7.             2-1+1 = 2.  -> 4
8. T         3  a->8,  e->4,  i->3,  o->6,  u->7.             3-1+1 = 3   -> 7

Valid substring:
1. Only vowels
2. Contains all 5 vowels

Sliding window:
- Expand right.
- If consonant → reset window.
- Track latest position of a,e,i,o,u.
- Once all 5 vowels exist:
  min(lastA,lastE,lastI,lastO,lastU)
  tells how many starting positions are valid.
minLast - left + 1

Time: O(n)
Space: O(1)

Important:
A consonant completely breaks the vowel-only window.

29. Maximum Number of Vowels in a Substring of Given Length
    **Difficulty:** Medium

**Description:**
Given a string `s` and an integer `k`, return the maximum number of vowel letters (`a, e, i, o, u`) in any substring of `s` with length `k`.

Example 1: `s = "abciiidef", k = 3` → Output `3` (`"iii"`).
Example 2: `s = "aeiou", k = 2` → Output `2`.
Example 3: `s = "leetcode", k = 3` → Output `2` (`"lee"`, `"eet"`, `"ode"`).

**Code:**
```java
public int maxVowels(String s, int k) {
    int count = 0;
    int maxCount = 0;
    // First window
    for (int i = 0; i < k; i++) {
        if (isVowel(s.charAt(i))) {
            count++;
        }
    }
    maxCount = count;
    // Slide window
    for (int right = k; right < s.length(); right++) {
        // Remove left character
        if (isVowel(s.charAt(right - k))) {
            count--;
        }
        // Add right character
        if (isVowel(s.charAt(right))) {
            count++;
        }
        maxCount = Math.max(maxCount, count);
    }

    return maxCount;
}

private boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
}
```

**Logic / Approach:**
1. Calculate the first window.
2. Store as answer.
3. Remove the leaving left element.
4. Add the incoming right element.
5. Update the answer.

---

ignal	Approach
Non-negative values, "exactly k" of something	atMost(k) - atMost(k-1) sliding window
Values can be negative, "sum equals k"	prefix sum + hashmap

30.  Maximum Erasure Value

You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).



Example 1:

Input: nums = [4,2,4,5,6]
Output: 17
Explanation: The optimal subarray here is [2,4,5,6].
Example 2:

Input: nums = [5,2,1,2,5,2,1,2,5]
Output: 8
Explanation: The optimal subarray here is [5,2,1] or [1,2,5].

```java
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        // since nums contain all the positive values we can use plain sliding window technique
        int left=0;
        Set<Integer> window = new HashSet<>();
        int sum = 0;
        int max = 0;
        for(int right=0;right<nums.length;right++){
            
            while(window.contains(nums[right])){
                sum-=nums[left];
                window.remove(nums[left]);
                left++;
            }
            window.add(nums[right]);
            sum+=nums[right];
            max = Math.max(max, sum);
        }
        return max;
    }
}

/**
4,2,4,5,6

right = 0       1.       2.   3
left.   0.      0        1.   1
window. {4}     {4,2}   {2}.  {2,4}
sum     4.      6       2      6
max     4.      6.      6      6


 */
```

Why the original order is correct: checking window.contains(nums[right]) before adding it means the set only contains previous elements. If nums[right] is genuinely a repeat, the check is true for the right reason (an earlier occurrence exists) and the shrink correctly stops as soon as that specific earlier occurrence is evicted — not when the current element itself gets evicted.

Rule of thumb for this whole family of problems (also true for LC 3, "Longest Substring Without Repeating Characters"): always check membership before mutating the window with the new element. The check's entire meaning depends on the set reflecting the window's state prior to the current addition.

31.  Substring with Concatenation of All Words
     You are given a string s and an array of strings words. All the strings of words are of the same length.

A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.

For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.

Example 1:

Input: s = "barfoothefoobarman", words = ["foo","bar"]

Output: [0,9]

Explanation:

The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.

Example 2:

Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]

Output: []

Notes : 
Why do we need offset?
This is the most important part to understand.
If:wordLen = 3
we have three possible alignments:

offset 0:  0  3  6  9  12 ...
offset 1:  1  4  7  10 13 ...
offset 2:  2  5  8  11 14 ...
for (int offset = 0; offset < wordLen; offset++) ensures we don't miss a valid concatenation.

Same sliding window, but we slide in chunks of wordLen, and we repeat it for every possible alignment.

### Logic

1. All words have the same length, so we can process the string in chunks of `wordLength`.

2. First, store how many times each word is required in the `need` map.

3. A valid substring must contain exactly `numOfWords` words, so its total length is:
   `wordLength × numOfWords`.

4. Since a word can start at any position within the first `wordLength` characters, run the sliding window for every `offset` from `0` to `wordLength - 1`.

5. For each offset, move `right` by `wordLength` at a time and extract one complete word.

6. If the word is not present in `need`, the current window can never be valid, so clear the window and start again from the next word.

7. If the word is valid, add it to the `window` frequency map and increase `count`.

8. If a word appears more times than required, move `left` forward one word at a time until its frequency becomes valid again.

9. When `count == numOfWords`, the window contains exactly all required words, so add `left` to the result.

10. After recording the answer, move `left` forward by one word so we can continue searching for overlapping valid windows.

### Key idea

We are not sliding one character at a time.

We slide **one complete word at a time**, and we repeat the process for every possible starting offset.

```java
import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        int wordLength = words[0].length();
        int wordCount = words.length;
        int totalLength = wordLength * wordCount;

        if (totalLength > s.length()) {
            return result;
        }

        // How many times each word is allowed to appear
        Map<String, Integer> required = new HashMap<>();

        for (String word : words) {
            required.put(word, required.getOrDefault(word, 0) + 1);
        }

        // Try each possible alignment
        for (int offset = 0; offset < wordLength; offset++) {

            int left = offset;
            int count = 0;

            Map<String, Integer> window = new HashMap<>();

            for (int right = offset;
                 right + wordLength <= s.length();
                 right += wordLength) {

                String word = s.substring(right, right + wordLength);

                // Word is not present in words
                if (!required.containsKey(word)) {
                    window.clear();
                    count = 0;
                    left = right + wordLength;
                    continue;
                }

                window.put(word, window.getOrDefault(word, 0) + 1);
                count++;

                // Too many copies of this word
                while (window.get(word) > required.get(word)) {

                    String leftWord = s.substring(
                        left,
                        left + wordLength
                    );

                    window.put(
                        leftWord,
                        window.get(leftWord) - 1
                    );

                    left += wordLength;
                    count--;
                }

                // Found exactly wordCount words
                if (count == wordCount) {
                    result.add(left);

                    // Move forward to search for another answer
                    String leftWord = s.substring(
                        left,
                        left + wordLength
                    );

                    window.put(
                        leftWord,
                        window.get(leftWord) - 1
                    );

                    left += wordLength;
                    count--;
                }
            }
        }

        return result;
    }
}
```

Complexity: O(n) total, since each of the wordLen offset-passes visits each word-position once: O(wordLen · (n / wordLen)) = O(n). Building substrings costs O(wordLen) each, so with substring extraction it's closer to O(n · wordLen) in practice, but that's still much better than brute force.

32. Maximum Points You Can Obtain from Cards
    **Difficulty:** Medium

**Description:**
There are several cards arranged in a row, and each card has an associated number of points, given in the integer array `cardPoints`. In one step, you can take one card from the beginning or from the end of the row. You have to take exactly `k` cards. Your score is the sum of the points of the cards you have taken. Return the maximum score you can obtain.

Example 1: `cardPoints = [1,2,3,4,5,6,1], k = 3` → Output `12` (take the three rightmost cards: `1 + 6 + 5 = 12`).
Example 2: `cardPoints = [2,2,2], k = 2` → Output `4`.

**Code:**
```java
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int currentSum = 0;
        int n = cardPoints.length;

        // Step 1: Start by taking all k cards from the left
        for (int i = 0; i < k; i++) {
            currentSum += cardPoints[i];
        }

        int maxSum = currentSum;

        // Step 2: Slide the window
        // Remove one card from the left boundary and add one from the right
        for (int i = 0; i < k; i++) {
            currentSum = currentSum - cardPoints[k - 1 - i] + cardPoints[n - 1 - i];
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
```

**Logic / Approach:**
Start by taking all k cards from the left, then repeatedly remove one from the left boundary and add one from the right, k times total, tracking the max sum along the way. This directly evaluates every left/right split combination instead of finding the minimum middle subarray.

Trace with `cardPoints = [1,2,3,4,5,6,1], k=4`:
- Initial: take `[1,2,3,4]` → sum = 10, maxSum = 10
- Slide 1: drop 4, add rightmost 1 → sum = 7
- Slide 2: drop 3, add 6 → sum = 10
- Slide 3: drop 2, add 5 → sum = 13
- Slide 4: drop 1, add 4 → sum = 16 (new max)

---
33. Frequency of the Most Frequent Element
    The frequency of an element is the number of times it occurs in an array.

You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.

Return the maximum possible frequency of an element after performing at most k operations.

Example 1:

Input: nums = [1,2,4], k = 5
Output: 3
Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.
Example 2:

Input: nums = [1,4,8,13], k = 5
Output: 2
Explanation: There are multiple optimal solutions:
- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
  Example 3:

Input: nums = [3,9,6], k = 2
Output: 1

Brute force 
Sort the array.
Consider every possible right as the target.
Starting from right, keep adding elements to the left.
Calculate the cost to make all of them equal to nums[right].
Stop when the cost exceeds k.

Your goal:

Make as many elements as possible equal to the same number.


1. Sort nums.

2. Use a sliding window [left, right].

3. Add nums[right] to sum.

4. Assume nums[right] is the target.

5. Calculate:
   cost = nums[right] * windowSize - sum

6. If cost > k:
   move left forward
   subtract nums[left] from sum

7. Keep track of the largest valid window.
```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        int ans =1;
        Arrays.sort(nums);
        int n = nums.length;
        for(int right = 0; right < n; right++) {
            long cost =0;
            for(int left = right; left >=0;left--){
                cost += nums[right] - nums[left];
                if(cost > k){
                    break;
                }
                ans = Math.max(ans, right-left+1);
            }
        }
        return ans;
    }
}


```
```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int maxFreq = 0;
        int left = 0;
        int n = nums.length;
        long sum = 0;
        for(int right = 0; right < n;right++) {
            // [1,2,4]
            // (4-1) + (4-2) + (4-4) = (4+4+4) - (1+2+4) = 4*3 - 7
            // target * number of element - sum
            sum+=nums[right];
           
            while((long)nums[right]* (right-left+1) - sum > k){
                sum-=nums[left];
                left++;
            }
            maxFreq = Math.max(maxFreq, right-left+1);
        }
        return maxFreq;
    }
}
```

Setup: say the window is [2, 4, 7] (already sorted, since the whole array is sorted before this loop runs). You want to turn every element into the same value. Since you can only increase elements (never decrease), the only value you can realistically target is the largest one already in the window — here, 7. Targeting anything smaller would require decreasing 7, which isn't allowed. Targeting anything larger just wastes extra operations for no benefit (the problem only cares about making them equal, not reaching some specific number).

So the target is fixed: nums[right], the max of the window.

Now, how many operations does it take?

To turn 2 into 7, you need 7 - 2 = 5 operations.
To turn 4 into 7, you need 7 - 4 = 3 operations.
To turn 7 into 7, you need 7 - 7 = 0 operations.

Total operations = (7-2) + (7-4) + (7-7).

Group the terms differently — separate the 7s from the actual values:

(7-2) + (7-4) + (7-7)
= (7+7+7) - (2+4+7)
= 7*3 - (2+4+7)

That's exactly:

target * (number of elements) - (sum of elements)

Sorted nums: [1, 1, 2, 4, 6, 8, 13]

k: 5

1, 1, 2, 4, 6, 8, 13. = 1, 1, 2, 4, 6, 13, 13. -> 13. (2)
1, 1, 2, 4, 6, 8, 13  = 1, 1, 2, 7, 8, 8, 13    -> 8. (2)
1, 1, 2, 4, 6, 8, 13 =  1, 1, 5, 6, 6, 8, 13    -> 6. (2)
1, 1, 2, 4, 6, 8, 13 =  1, 4, 4, 4, 6, 8, 13    -> 4. (3)

Complexity: O(n log n) for the sort, O(n) for the window pass (both pointers move forward only). Space O(1) extra (O(log n) to O(n) depending on sort implementation).

34. 523. Continuous Subarray Sum
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

Key insight: if prefix[i] and prefix[j] (with i < j) leave the same remainder when divided by k, then the subarray between them, nums[i+1..j], sums to a multiple of k. Why: prefix[j] - prefix[i] is divisible by k exactly when prefix[i] % k == prefix[j] % k.

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

35. 2958. Length of Longest Subarray With at Most K Frequency
          You are given an integer array nums and an integer k.

The frequency of an element x is the number of times it occurs in an array.

An array is called good if the frequency of each element in this array is less than or equal to k.

Return the length of the longest good subarray of nums.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [1,2,3,1,2,3,1,2], k = 2
Output: 6
Explanation: The longest possible good subarray is [1,2,3,1,2,3] since the values 1, 2, and 3 occur at most twice in this subarray. Note that the subarrays [2,3,1,2,3,1] and [3,1,2,3,1,2] are also good.
It can be shown that there are no good subarrays with length more than 6.
Example 2:

Input: nums = [1,2,1,2,1,2,1,2], k = 1
Output: 2
Explanation: The longest possible good subarray is [1,2] since the values 1 and 2 occur at most once in this subarray. Note that the subarray [2,1] is also good.
It can be shown that there are no good subarrays with length more than 2.
Example 3:

Input: nums = [5,5,5,5,5,5,5], k = 4
Output: 4
Explanation: The longest possible good subarray is [5,5,5,5] since the value 5 occurs 4 times in this subarray.
It can be shown that there are no good subarrays with length more than 4.

1,2,3,1,2,3,1,2 k=2
0 1 2 3 4 5 6 7

{1:2, 2:2 3:2 }. 0-5.  right-left +1 = 6
when right = 6 {1:3, 2:2 3:2}. count of 1 > k shrink the window

left = 1. {1:2, 2:2 3:2}. 1-6.
left = 2. {1:2, 2:2 3:2}. 2-7 

brute
```java
class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int longest = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int j = i; j < n; j++) {
                int count = freq.merge(nums[j], 1, Integer::sum);
                if (count > k) break; // adding nums[j] broke validity; no point extending further from this i
                longest = Math.max(longest, j - i + 1);
            }
        }
        return longest;
    }
}
```

optimal

```java
class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int longestSubarray = 0;
        int left = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int right = 0; right < nums.length; right++){
            int count = freq.merge(nums[right], 1, Integer::sum);
            while(count > k){
                freq.merge(nums[left], -1, Integer::sum);
                left++;
                count = freq.getOrDefault(nums[right],0);
            }
            longestSubarray = Math.max(longestSubarray, right-left+1);
        }
        return longestSubarray;
    }
}
```

36. 1052. Grumpy Bookstore Owner

There is a bookstore owner that has a store open for n minutes. You are given an integer array customers of length n where customers[i] is the number of the customers that enter the store at the start of the ith minute and all those customers leave after the end of that minute.

During certain minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.

When the bookstore owner is grumpy, the customers entering during that minute are not satisfied. Otherwise, they are satisfied.

The bookstore owner knows a secret technique to remain not grumpy for minutes consecutive minutes, but this technique can only be used once.

Return the maximum number of customers that can be satisfied throughout the day.

Example 1:

Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3

Output: 16

Explanation:

The bookstore owner keeps themselves not grumpy for the last 3 minutes.

The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

Example 2:

Input: customers = [1], grumpy = [0], minutes = 1

Output: 1


easy sliding window problem
count existing happy customers
and find max number of 1's window where there most customers and return the result;

answer = alreadyHappy + maximumExtraHappy

alreadyHappy:
grumpy[i] == 0

extraHappy:
grumpy[i] == 1 → customers[i]

Window size = minutes

```java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int currentHappyCustomers = 0;
        int n = customers.length;
        for(int i=0;i<n;i++){
            if(grumpy[i] == 0){
                currentHappyCustomers+=customers[i];
            }
        }
        int extraHappyCustomers = 0;
        // first window 
        for(int right=0; right < minutes; right++){
            if(grumpy[right] == 1){
                extraHappyCustomers+=customers[right];
            }
        }
        int maxHappy = extraHappyCustomers;

        for(int right = minutes; right<n; right++){
            if(grumpy[right] == 1){
                extraHappyCustomers+=customers[right];
            }
            if(grumpy[right-minutes]==1){
                extraHappyCustomers-=customers[right-minutes];
            }
            maxHappy = Math.max(maxHappy, extraHappyCustomers);

        }
        return currentHappyCustomers +  maxHappy;
    }
}
```

37. 795. Number of Subarrays with Bounded Maximum
         Given an integer array nums and two integers left and right, return the number of contiguous non-empty subarrays such that the value of the maximum array element in that subarray is in the range [left, right].

The test cases are generated so that the answer will fit in a 32-bit integer.



Example 1:

Input: nums = [2,1,4,3], left = 2, right = 3
Output: 3
Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].

Example 2:

Input: nums = [2,9,2,5,6], left = 2, right = 8
Output: 7


Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= left <= right <= 109

```java
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return countAtMost(nums, right) - countAtMost(nums, left-1);
    }

    private int countAtMost(int[] nums, int limit){
        int length = 0;
        int count = 0;
        for(int num : nums){
            if(num <= limit) {
                length++;
                count+=length;
            } else {
                length = 0;
            }
        }
        return count;
    }

    private int brute(int[] nums, int left, int right){
        int count = 0;
        int n = nums.length;
        for(int i =0; i < n; i++) {
            int max = Integer.MIN_VALUE;
            for(int j = i; j<n; j++){
                max = Math.max(max, nums[j]);
                if(max>=left && max<=right){
                    count++;
                }
            }
         
        }   
        return count;
    }
}


```

nums  = [2, 5, 1, 8, 3, 2, 6, 1, 9, 4]
left  = 3
right = 6

(max <= right)  minus (max <= left - 1) = left <= max <= right

answer  = countAtMost(6) - countAtMost(3-1)



nums             2,  5,     1,    8,     3,    2,   6,    1,    9,     4
index            0.  1.     2.    3.     4.    5.   6.    7     8.     9
<= 6             T.  T      T     F      T.    T.   T.    T.    F.     T
length           1.  2.     3.    0      1     2.   3.    4.    0      1
length + count   1.  (1+2)  (3+3) (0+6)  (1+6) (2+7)(3+9) (4+12)(0+16) (1+16)
total count      1.  3.     6.    6       7.   9    12.    16.   16.   17
subarrays.       [2] [2]    [2]
[2,5]  [5]
[5].   [1]
[2,5]
[5,1]
[2,5,1]


nums             2,  5,     1,    8,     3,    2,   6,    1,    9,     4
index            0.  1.     2.    3.     4.    5.   6.    7     8.     9
<= 2             T.  F      T     F      F     T.   F     T.    F      f
length           1.  0      1     0      0     1    0     1
length + count   1.  (0+1). (1+1)              (1+2)      (1+3)
total count      1.   1.    2                   3.   3.   4

countAtMost(6) = 17
countAtMost(2) =  4
-------------------
answer         = 13


contiguous subarray where element is  <=6

[2, 5, 1]     [3, 2, 6, 1]     [4]
[2]           [3]              [4]
[2,5].        [3,2]
[2,5,1].      [3,2,6]
[5]           [3,2,6,1]
[5,1]         [2]
[1].          [2,6]
[2,6,1]
[6]
[6,1]
[1]

All subarrays where every element < 2

[2, 5, 1, 8, 3, 2, 6, 1, 9, 4]
↑                    ↑
1                    1

[2]
[1]
[2]
[1]

so you cut all these <2 subarray from the above and get 13 as answer

              [3]              [4]
[2,5].        [3,2]
[2,5,1].      [3,2,6]
[5]           [3,2,6,1]
[5,1]         
[2,6]
[2,6,1]
[6]
[6,1]

num <= limit
→ extend window
→ length++
→ add length

num > limit
→ window breaks
→ length = 0

LC 795 = count subarrays with max ≤ right − count subarrays with max ≤ left−1; length tells how many valid subarrays end at current index.


38. 2090. K Radius Subarray Averages

You are given a 0-indexed array nums of n integers, and an integer k.

The k-radius average for a subarray of nums centered at some index i with the radius k is the average of all elements in nums between the indices i - k and i + k (inclusive). If there are less than k elements before or after the index i, then the k-radius average is -1.

Build and return an array avgs of length n where avgs[i] is the k-radius average for the subarray centered at index i.

The average of x elements is the sum of the x elements divided by x, using integer division. The integer division truncates toward zero, which means losing its fractional part.

For example, the average of four elements 2, 3, 1, and 5 is (2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75, which truncates to 2.


Example 1:


Input: nums = [7,4,3,9,1,8,5,2,6], k = 3
Output: [-1,-1,-1,5,4,4,-1,-1,-1]
Explanation:
- avg[0], avg[1], and avg[2] are -1 because there are less than k elements before each index.
- The sum of the subarray centered at index 3 with radius 3 is: 7 + 4 + 3 + 9 + 1 + 8 + 5 = 37.
  Using integer division, avg[3] = 37 / 7 = 5.
- For the subarray centered at index 4, avg[4] = (4 + 3 + 9 + 1 + 8 + 5 + 2) / 7 = 4.
- For the subarray centered at index 5, avg[5] = (3 + 9 + 1 + 8 + 5 + 2 + 6) / 7 = 4.
- avg[6], avg[7], and avg[8] are -1 because there are less than k elements after each index.
  Example 2:

Input: nums = [100000], k = 0
Output: [100000]
Explanation:
- The sum of the subarray centered at index 0 with radius 0 is: 100000.
  avg[0] = 100000 / 1 = 100000.

```java
class Solution {
    public int[] getAverages(int[] nums, int k) {
        int windowSize =  2 * k + 1; // center element + left k + right k
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        if(windowSize > n) return ans;
        long sum = 0;
        for(int i=0;i<windowSize;i++){
            sum+=nums[i];
        }
        ans[k] = (int) (sum / windowSize);

        for(int right=windowSize; right<n;right++){
            sum = sum + nums[right] - nums[right - windowSize];
            ans[right - k] = (int)(sum / windowSize);
        }
        return ans;
    }
}


```
mistake in casting
A quick way to remember it: sum / windowSize is a long divided by an int, so Java computes it in long and the result is small enough to fit in an int. You only want to narrow to int after the division, and the parentheses are what enforce that order.

Input: nums = [7,4,3,9,1,8,5,2,6], k = 3
Output: [-1,-1,-1,5,4,4,-1,-1,-1]


windowSize = 3*2 +1 = 7
k  = 3
[7, 4, 3, 9, 1, 8, 5, 2, 6]
initial ans [-1,-1,-1,-1,-1,-1,-1,-1,-1]
0. 1. 2. 3. 4. 5. 6. 7. 8
right nums[right].  sum right-k ans
0       7             7  -3    [-1,-1,-1,-1,-1,-1,-1,-1,-1]
1       4            11. -2     [-1,-1,-1,-1,-1,-1,-1,-1,-1]
2.      3.           14  -1     [-1,-1,-1,-1,-1,-1,-1,-1,-1]
3.      9            23.  0.   [-1,-1,-1,-1,-1,-1,-1,-1,-1]
4.      1            24.  1    [-1,-1,-1,-1,-1,-1,-1,-1,-1]
5.      8            32.  2    [-1,-1,-1,-1,-1,-1,-1,-1,-1]
6       5            37.  3.   [-1,-1,-1, 5,-1,-1,-1,-1,-1]
7       2            32.  4.   [-1,-1,-1, 5, 4,-1,-1,-1,-1]
8       6            34.  5.   [-1,-1,-1, 5, 4, 4,-1,-1,-1]

window size = 2k + 1

initial window → calculate sum

then repeatedly:
add right element
remove left element
calculate average

one liner LC 2090 = fixed window of size 2k+1 + maintain sum by add-right/remove-left + center = right-k.


## 1. Maximum Points You Can Obtain from Cards
**Difficulty:** Medium

**Description:**
There are several cards arranged in a row, and each card has an associated number of points, given in the integer array `cardPoints`. In one step, you can take one card from the beginning or from the end of the row. You have to take exactly `k` cards. Your score is the sum of the points of the cards you have taken. Return the maximum score you can obtain.

Example 1: `cardPoints = [1,2,3,4,5,6,1], k = 3` → Output `12` (take the three rightmost cards: `1 + 6 + 5 = 12`).
Example 2: `cardPoints = [2,2,2], k = 2` → Output `4`.

**Code:**
```java
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int currentSum = 0;
        int n = cardPoints.length;

        // Step 1: Start by taking all k cards from the left
        for (int i = 0; i < k; i++) {
            currentSum += cardPoints[i];
        }

        int maxSum = currentSum;

        // Step 2: Slide the window
        // Remove one card from the left boundary and add one from the right
        for (int i = 0; i < k; i++) {
            currentSum = currentSum - cardPoints[k - 1 - i] + cardPoints[n - 1 - i];
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
```

**Logic / Approach:**
Start by taking all k cards from the left, then repeatedly remove one from the left boundary and add one from the right, k times total, tracking the max sum along the way. This directly evaluates every left/right split combination instead of finding the minimum middle subarray.

Trace with `cardPoints = [1,2,3,4,5,6,1], k=4`:
- Initial: take `[1,2,3,4]` → sum = 10, maxSum = 10
- Slide 1: drop 4, add rightmost 1 → sum = 7
- Slide 2: drop 3, add 6 → sum = 10
- Slide 3: drop 2, add 5 → sum = 13
- Slide 4: drop 1, add 4 → sum = 16 (new max)

---

## 2. Contains Duplicate II
**Difficulty:** Easy

**Description:**
Given an integer array `nums` and an integer `k`, return `true` if there are two distinct indices `i` and `j` such that `nums[i] == nums[j]` and `abs(i - j) <= k`.

Example 1: `nums = [1,2,3,1], k = 3` → `true`
Example 2: `nums = [1,0,1,1], k = 1` → `true`
Example 3: `nums = [1,2,3,1,2,3], k = 2` → `false`

**Code:**
```java
public boolean containsNearbyDuplicate(int[] nums, int k) {
    // use hashset to check for duplicity
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
        if (!set.add(nums[i])) {
            return true;
        }
        if (set.size() > k) {
            set.remove(nums[i - k]);
        }
    }
    return false;
}
```

**Logic / Approach:**
For every `nums[i]`: if it already exists in the current window's set, a duplicate within distance `k` was found → return `true`. Otherwise add it, and once the window grows past size `k`, remove the oldest element.

Trace with `nums = [1,2,3,1]`:
| i | nums[i] | Set before | Action |
|---|---|---|---|
| 0 | 1 | `{}` | add 1 |
| 1 | 2 | `{1}` | add 2 |
| 2 | 3 | `{1,2}` | add 3 |
| 3 | 1 | `{1,2,3}` | 1 already exists → `true` |

---

## 3. Longest Substring Without Repeating Characters
**Difficulty:** Medium

**Description:**
Given a string `s`, find the length of the longest substring without duplicate characters.

Example 1: `s = "abcabcbb"` → Output `3` (`"abc"`).
Example 2: `s = "bbbbb"` → Output `1`.
Example 3: `s = "pwwkew"` → Output `3` (`"wke"`; note the answer must be a substring, not a subsequence).

**Code:**
```java
public int lengthOfLongestSubstring(String s) {
    Set<Character> set = new HashSet<>();

    int start = 0;
    int besti = 0;
    int bestj = -1;

    for (int i = 0; i < s.length(); i++) {

        // Remove from left until duplicate is gone
        while (set.contains(s.charAt(i))) {
            set.remove(s.charAt(start));
            start++;
        }

        // Add current character
        set.add(s.charAt(i));

        // Update best window
        if (i - start > bestj - besti) {
            besti = start;
            bestj = i;
        }
    }

    return bestj - besti + 1;
}
```

**Logic / Approach:**
Sliding window + HashSet. `start` is the left boundary, `i` is the right boundary. Add characters while unique; on a duplicate, shrink from the left via a `while` loop until the duplicate is gone. Current window length is `i - start + 1`; update the best window every iteration. Both pointers only move forward, so time is O(n) and space is O(min(n, charset)).

Key pattern:
```java
while (set.contains(s.charAt(i))) {
    set.remove(s.charAt(start));
    start++;
}
set.add(s.charAt(i));
maxLen = Math.max(maxLen, i - start + 1);
```

Trace with `"abcabcbb"` (indices 0–7):
| i | start | set | condition | besti | bestj |
|---|---|---|---|---|---|
| initial | 0 | — | — | 0 | -1 |
| 0 | 0 | {a} | 0-0 > -1-0 | 0 | 0 |
| 1 | 0 | {a,b} | 1-0 > 0-0 | 0 | 1 |
| 2 | 0 | {a,b,c} | 2-0 > 1-0 | 0 | 2 |
| 3 | 1 | {b,c,a} | 3-1 > 2-0 | 0 | 2 |
| 4 | 2 | {c,a,b} | 4-2 > 2-0 | 0 | 2 |
| 5 | 3 | {a,b,c} | 5-3 > 2-0 | 0 | 2 |
| 6 | 4 | {a,c,b} | 6-4 > 2-0 | 0 | 2 |
| 7 | 5 | {a,c,b} | 7-5 > 2-0 | 0 | 2 |

Answer: `bestj - besti + 1 = 3`.

---

## 4. Maximum Number of Occurrences of a Substring
**Difficulty:** *(not specified in sheet)*

**Description:**
Given a string `s`, return the maximum number of occurrences of any substring such that: the number of unique characters in the substring is `<= maxLetters`, and the substring size is between `minSize` and `maxSize` inclusive.

Example 1: `s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4` → Output `2` (`"aab"` occurs twice).
Example 2: `s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3` → Output `2` (`"aaa"` occurs twice, overlapping allowed).

Constraints: `1 <= s.length <= 1e5`, `1 <= maxLetters <= 26`, `1 <= minSize <= maxSize <= min(26, s.length)`, lowercase English letters only.

**Code:**
```java
public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
    Map<String, Integer> substringCount = new HashMap<>();
    int[] count = new int[26];
    int uniqChars = 0;
    int start = 0;
    int maxOccurrences = 0;

    for (int end = 0; end < s.length(); end++) {

        char c = s.charAt(end);
        if (count[c - 'a'] == 0) uniqChars++;
        count[c - 'a']++;

        // this is the time to slide the window
        if (end - start + 1 > minSize) {
            char left = s.charAt(start);
            count[left - 'a']--;
            start++;
            if (count[left - 'a'] == 0) {
                uniqChars--;
            }
        }

        // this is the time to record the substring count
        if (end - start + 1 >= minSize && uniqChars <= maxLetters) {
            String sub = s.substring(start, end + 1);
            int cnt = substringCount.getOrDefault(sub, 0) + 1;
            substringCount.put(sub, cnt);
            maxOccurrences = Math.max(maxOccurrences, cnt);
        }
    }

    return maxOccurrences;
}
```

**Logic / Approach:**
Since a substring that satisfies the constraints is always at least as frequent at its minimum allowed size, only `minSize` needs to be considered (a smaller valid substring can only occur equal or more often than a longer one). Maintain a fixed-size window of `minSize`, a 26-count array for unique character tracking, and a HashMap of substring → frequency, updating the max as we go.

Complexity: there are O(n) windows; each valid window's `substring()` call costs O(minSize) in Java (creates a new String). Since `minSize <= 26` is bounded by a constant, this is effectively O(n) time, O(n) space.

Short pattern to remember:
1. Only consider `minSize`.
2. Sliding window of exactly `minSize`.
3. Count array → unique character count.
4. HashMap<String,Integer> → substring frequency.
5. Track maximum frequency.

**Mistakes:**
`count[char-'a']` indexes the count array by letter — e.g. `count[0]` for `'a'`, `count[1]` for `'b'`, and so on.

Trace through Example 1 (`s = "aababcaab", maxLetters = 2, minSize = 3`), windows of size 3: `"aab", "aba", "bab", "abc", "bca", "caa", "aab"`
- `"aab"` → distinct = 2 ✓ → count 1
- `"aba"` → distinct = 2 ✓ → count 1
- `"bab"` → distinct = 2 ✓ → count 1
- `"abc"` → distinct = 3 ✗
- `"bca"` → distinct = 3 ✗
- `"caa"` → distinct = 2 ✓ → count 1
- `"aab"` → distinct = 2 ✓ → count 2 ← max

---

## 5. Diet Plan Performance
**Difficulty:** Easy

**Description:**
A dieter tracks calorie intake over several days in `calories[i]`. For every consecutive sequence of `k` days, compute total `T`:
- If `T < lower`, lose 1 point.
- If `T > upper`, gain 1 point.
- Otherwise, no change.

Return total points after all days (starting from 0; can be negative).

Example 1: `calories = [2,4,6,8,10], k=2, lower=7, upper=9` → Output `2`.
Example 2: `calories = [1,1,1,1,1], k=3, lower=4, upper=5` → Output `-3`.

**Code:**
```java
public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
    int totalPoints = 0;
    int sum = 0;

    for (int i = 0; i < calories.length; i++) {
        sum += calories[i];
        if (i >= k) {
            sum -= calories[i - k];
        }
        if (i >= k - 1) {
            if (sum > upper) {
                totalPoints++;
            } else if (sum < lower) {
                totalPoints--;
            }
        }
    }

    return totalPoints;
}
```

**Logic / Approach:**
A window of size `k` starting at `left` must fit inside the array, so the last valid start is `calories.length - k`. Consecutive windows overlap in `k-1` elements, so maintain a running sum: add the incoming element, subtract the outgoing one (`calories[i-k]`) once the window is full — O(n) instead of O(n·k). Since `lower <= upper`, each window sum falls into exactly one of three cases, checked independently.

**Mistakes:**
- Loop bound wrong — used `left < calories.length - 1` instead of `left <= calories.length - k`. This either runs out of bounds or skips the last valid window.
- Comparison logic wrong — used `sum > lower || sum > upper` and `sum < lower || sum < upper`, which conflates the two thresholds. Since `lower <= upper`, this makes almost every window count as "too high" and makes the "too low" branch nearly unreachable.

---

## 6.

## 7. Maximum Average Subarray I
**Difficulty:** Easy

**Description:**
Given an integer array `nums` of `n` elements and an integer `k`, find a contiguous subarray of length `k` with the maximum average value. Answers within `1e-5` are accepted.

Example 1: `nums = [1,12,-5,-6,50,3], k = 4` → Output `12.75000` (`(12-5-6+50)/4 = 51/4`).
Example 2: `nums = [5], k = 1` → Output `5.00000`.

**Code:**
```java
public double findMaxAverage(int[] nums, int k) {

    int sum = 0;

    for (int i = 0; i < k; i++) {
        sum += nums[i];
    }

    int maxSum = sum;

    for (int i = k; i < nums.length; i++) {
        sum = sum - nums[i - k] + nums[i];
        maxSum = Math.max(maxSum, sum);
    }

    return (double) maxSum / k;
}
```

**Logic / Approach:**
Compute the first window's sum, set it as `maxSum`, then slide: remove the left element, add the right element, update `maxSum`. Finally divide `maxSum / k` once at the end.

**Mistakes:**
1. Both `sum` and `k` are `int`: `sum / k` performs integer division first, then assigns to `double` — e.g. `int sum = 5; int k = 2; double avg = sum / k;` gives `2.0`, not `2.5`. Must cast before dividing.
2. The window with the largest sum will also have the largest average, so there's no need to compute the average on every iteration — just track the max sum and divide once at the end.

---

## 8. Minimum Recolors to Get K Consecutive Black Blocks
**Difficulty:** Easy

**Description:**
Given a 0-indexed string `blocks` of length `n` where each character is `'W'` or `'B'`, and an integer `k` (desired number of consecutive black blocks), you may recolor a white block to black in one operation. Return the minimum number of operations needed so there is at least one occurrence of `k` consecutive black blocks.

Example 1: `blocks = "WBBWWBBWBW", k = 7` → Output `3`.

**Code:**
```java
public int minimumRecolors(String blocks, int k) {
    int countW = 0;
    for (int i = 0; i < k; i++) {
        if (blocks.charAt(i) == 'W') {
            countW++;
        }
    }
    if (countW == 0) return 0;
    int min = countW;

    for (int i = k; i < blocks.length(); i++) {
        if (blocks.charAt(i - k) == 'W') {
            countW--;
        }
        if (blocks.charAt(i) == 'W') {
            countW++;
        }
        min = Math.min(countW, min);
    }
    return min;
}
```

**Logic / Approach:**
Count `'W'` characters in the first window (positions 0 to k-1); set as the initial result. Slide the window from position `k` to `n-1`: if the element leaving (`i-k`) is `'W'`, decrement the count; if the element entering (`i`) is `'W'`, increment the count. Update the result with the minimum count seen.

**Mistakes:**
Added extra logic for checking "consecutive" black blocks explicitly — unnecessary, since it's already handled implicitly by taking the min count of W's over all fixed windows.

---

## 9. Find All Anagrams in a String
**Difficulty:** Medium

**Description:**
Given two strings `s` and `p`, return an array of all the start indices of `p`'s anagrams in `s`, in any order.

Example 1: `s = "cbaebabacd", p = "abc"` → Output `[0,6]` (`"cba"` at index 0, `"bac"` at index 6 are anagrams of `"abc"`).

**Code:**
```java
public List<Integer> findAnagrams(String s, String p) {
    List<Integer> result = new ArrayList<>();
    int n = s.length(), m = p.length();
    if (m > n) return result;

    int[] pCount = new int[26];
    int[] sCount = new int[26];

    for (int i = 0; i < m; i++) {
        pCount[p.charAt(i) - 'a']++;
        sCount[s.charAt(i) - 'a']++;
    }

    if (Arrays.equals(pCount, sCount)) {
        result.add(0);
    }

    for (int i = m; i < n; i++) {
        sCount[s.charAt(i) - 'a']++;
        sCount[s.charAt(i - m) - 'a']--;

        if (Arrays.equals(pCount, sCount)) {
            result.add(i - m + 1);
        }
    }

    return result;
}
```

**Logic / Approach:**
For a fixed-size window of size `k`: when `right` moves, add `s[right]`; if the window exceeds `k`, remove `s[right - k]`; then check the window. The starting index is `right - k + 1`.

Key indices to remember:
- `i` = character entering the window
- `i - k` = character leaving the window
- `i - k + 1` = starting index of the current window

---

## 10. Maximum Frequency Score of a Subarray
**Difficulty:** Hard

**Description:**
Given a positive integer `k` and an integer array `nums`, an array's frequency score is the sum, modulo `1e9+7`, of each unique value raised to the power of its frequency. For instance, `[5,4,5,7,4,4]` has a frequency score of `(5^3 + 4^2 + 7^1) mod (1e9+7) = 96`. Return the maximum frequency score among all subarrays of size `k` (maximizing under the modulo, not the true value).

Example 1: `nums = [2,5,2,5,3,2], k = 3` → Output `27` (subarray `[5,2,5]`).
Example 2: `nums = [1,2,3,3,2,10], k = 4` → Output `21` (subarray `[3,3,2,10]`).

Constraints: `1 <= k <= nums.length <= 1e5`, `1 <= nums[i] <= 1e6`.

**Code:**
```java
class Solution {
  private static final long MOD = 1_000_000_007;

  public int maxFrequencyScore(List<Integer> nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();
    long score = 0;

    // build first window
    for (int i = 0; i < k; i++) {
      score = updateFrequency(score, nums.get(i), 1, freq);
    }
    long maxScore = score;

    for (int right = k; right < nums.size(); right++) {
      score = updateFrequency(score, nums.get(right - k), -1, freq);
      score = updateFrequency(score, nums.get(right), 1, freq);
      maxScore = Math.max(maxScore, score);
    }
    return (int) maxScore;
  }

  private long updateFrequency(long score, int num, int delta, Map<Integer, Integer> freq) {
    int oldFreq = freq.getOrDefault(num, 0);
    int newFreq = oldFreq + delta;

    // remove old contribution
    if (oldFreq > 0) {
      score = (score - power(num, oldFreq) + MOD) % MOD;
    }

    // add new contribution
    if (newFreq > 0) {
      score = (score + power(num, newFreq)) % MOD;
      freq.put(num, newFreq);
    } else {
      freq.remove(num);
    }
    return score;
  }

  private long power(long base, int exp) {
    base = base % MOD;
    long result = 1;
    while (exp > 0) {
      if ((exp & 1) == 1) {
        result = (result * base) % MOD;
      }
      base = (base * base) % MOD;
      exp >>= 1;
    }
    return result;
  }
}
```

**Logic / Approach:**
The core of `updateFrequency()`: given the old frequency, remove its old contribution from the score, change the frequency, then add the new contribution.

1. Create a frequency map.
2. Build the first k-sized window.
3. Maintain its frequency score.
4. Store score as max.
5. Slide the window one position at a time.
6. Remove the outgoing element and update its frequency/contribution.
7. Add the incoming element and update its frequency/contribution.
8. Update max.
9. Return max.

**Mistakes:**
Notes captured during review (not literal mistakes made, but points worth remembering):

Trace with `nums = [2,5,2,5,3,2], k=3`:

| Window | Frequency | Score | Max |
|---|---|---|---|
| `[2,5,2]` | `{2:2,5:1}` | 9 | 9 |
| `[5,2,5]` | `{2:1,5:2}` | **27** | **27** |
| `[2,5,3]` | `{2:1,5:1,3:1}` | 10 | 27 |
| `[5,3,2]` | `{5:1,3:1,2:1}` | 10 | 27 |

1. **Why `power()`?** Called once per (value, frequency) pair rather than on the whole window; uses binary exponentiation (exponentiation by squaring) to break the exponent into binary bits and square the base repeatedly, reducing O(exp) multiplications to O(log exp). E.g. `2^5`: binary of 5 is `101` → `5 = 4 + 1` → `2^5 = 2^4 × 2^1 = 16 × 2 = 32`. Generate `2^1, 2^2, 2^4, 2^8, ...` and select the powers corresponding to 1-bits.

2. **Why `power(num, oldFreq) + MOD`?** Java's `%` can return negative results when the left operand is negative. Adding `MOD` before the final `% MOD` guarantees a non-negative, correctly wrapped result. E.g. `score=10`, removing contribution `25` gives `10-25=-15`; `(-15 + 1_000_000_007) % MOD = 999,999,992`. Pattern: adding uses `(score + value) % MOD`; removing uses `(score - value + MOD) % MOD`.

3. **What does `(exponent & 1)` mean?** Checks whether the exponent is odd or even (checks the lowest bit). E.g. `5 = 101₂`, `5 & 1 = 1` → odd; `6 = 110₂`, `6 & 1 = 0` → even. If the current bit is 1, multiply the current base into the result.

4. **What does `exponent >>= 1` mean?** Right-shifts the bits by one, equivalent to `exponent = exponent / 2` for positive integers. E.g. `13 → 6 → 3 → 1 → 0` (binary `1101 → 110 → 11 → 1 → 0`).

5. **Why does `currentBase` become 256 when calculating `2^5`?** At the end, `result = 32` (the actual answer) while `currentBase = 256` (`2^8`, generated after `2^4` was already used but simply not needed further) — the answer lives in `result`, not `currentBase`.

6. **What does `updateFrequency()` do?** Receives `(score, num, delta, freq)` where `delta = +1` for adding a number and `delta = -1` for removing one. It finds the old frequency, computes the new frequency, removes the old contribution, adds the new contribution, and updates the map.

---

## 11. Minimum Difference Between Highest and Lowest of K Scores
**Difficulty:** Easy

**Description:**
Given a 0-indexed integer array `nums` where `nums[i]` is the score of the i-th student, and an integer `k`, pick the scores of any `k` students so the difference between the highest and lowest of the `k` scores is minimized. Return the minimum possible difference.

Example 1: `nums = [90], k = 1` → Output `0`.
Example 2: `nums = [9,4,1,7], k = 2` → Output `2` (picking `9` and `7`).

Constraints: `1 <= k <= nums.length <= 1000`, `0 <= nums[i] <= 1e5`.

**Code:**
```java
public int minimumDifference(int[] nums, int k) {
    Arrays.sort(nums);  // e.g. 1,4,7,9
    int result = Integer.MAX_VALUE;
    int left = 0, right = k - 1;
    while (right < nums.length) {
        result = Math.min(result, nums[right] - nums[left]);
        left++;
        right++;
    }
    return result;
}
```

**Logic / Approach:**
To minimize the difference between the highest and lowest scores among the selected `k` students, they must be chosen continuously from the sorted array — skipping an index and swapping in a farther one can never decrease the difference. So there's always an optimal selection of `k` consecutive elements from the sorted array.

Sort `nums` ascending, then slide a fixed-size window of `k` across it. With left boundary `i`, the right boundary is `i+k-1`, and the difference for that window is `nums[i+k-1] - nums[i]`. The answer is the minimum of this value across all windows.