1. Maximum Sum Subarray of Size K

Problem : Given an array of integers arr[]  and a number k. Return the maximum sum of a subarray of size k.

Note: A subarray is a contiguous part of any given array.

Examples:

Input: arr[] = [100, 200, 300, 400], k = 2
Output: 700
Explanation: arr2 + arr3 = 700, which is maximum.
Input: arr[] = [1, 4, 2, 10, 23, 3, 1, 0, 20], k = 4
Output: 39
Explanation: arr1 + arr2 + arr3 + arr4 = 39, which is maximum.
Input: arr[] = [100, 200, 300, 400], k = 1
Output: 400
Explanation: arr3 = 400, which is maximum.

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

TC O(N)
Sc O(1)

2. First Negative in Windows of Size K

Given an array arr[]  and a positive integer k, find the first negative integer for each and every window(contiguous subarray) of size k.

Note: If a window does not contain a negative integer, then return 0 for that window.

Examples:

Input: arr[] = [-8, 2, 3, -6, 10] , k = 2
Output: [-8, 0, -6, -6]
Explanation:
Window [-8, 2] First negative integer is -8.
Window [2, 3] No negative integers, output is 0.
Window [3, -6] First negative integer is -6.
Window [-6, 10] First negative integer is -6.
Input: arr[] = [12, -1, -7, 8, -15, 30, 16, 28] , k = 3
Output: [-1, -1, -7, -15, -15, 0]
Explanation:
Window [12, -1, -7] First negative integer is -1.
Window [-1, -7, 8] First negative integer is -1.
Window [-7, 8, -15] First negative integer is -7.
Window [8, -15, 30] First negative integer is -15.
Window [-15, 30, 16] First negative integer is -15.
Window [30, 16, 28] No negative integers, output is 0.
Input: arr[] = [12, 1, 3, 5] , k = 3
Output: [0, 0]
Explanation:
Window [12, 1, 3] No negative integers, output is 0.
Window [1, 3, 5] No negative integers, output is 0.


For every window of size k, we need the first negative number.

Instead of scanning all k elements again for every window, keep track of the indices of negative numbers using a queue.
code

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
-8, 2, 3, -6, 10
0  1. 2.  3.  4

k=2

right       0   1   2  3  4
right-k    -2  -1.  0. 1. 2
window.     F.  T.  T. T  T
q           0.  0   {} 3.
result.        -8.  0.-6. -6


Input: arr[] = [12, -1, -7, 8, -15, 30, 16, 28] , k = 3
Output: [-1, -1, -7, -15, -15, 0]

12, -1, -7, 8, -15, 30, 16, 28
0.   1.  2. 3.  4.   5.  6.  7


right.   0.       1.        2            3           4           5             6           7
q        {}.     {1}       {1,2}        {1,2}       {2,4}       {4}.         {4}.         {}
right-k. -3       -2        -1            0           1          2             3           4
window.?  F.      F.        T            T            T          T            T.           T
result                      -1          -1           -7.        -15          -15          0
3. Maximum Average Subarray
   Given an array arr[] and a positive integer k, find the subarray of length k having the maximum average value.

Return the starting index of that subarray.

If multiple subarrays have the same maximum average, return the smallest starting index.

Examples:

Input: k = 4, arr[] = [1, 12, -5, -6, 50, 3]
Output: 1
Explanation: Maximum average is (12 - 5 - 6 + 50)/4 = 51/4. Therefore answer for this test case is 1.
Input: k = 3, arr[] = [3, -435, 335, 10, -50, 100, 20]
Output: 2
Explanation: Maximum average is (335 + 10 - 50)/3 = 295/3. Therefore answer for this test case is 2.

** WHOEVER has max sum is also max avg candidate so we find max sum**

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

/*

1, 12, -5, -6, 50, 3
0.  1.  2.  3.  4. 5
k = 4

right.  0.    1.    2.    3.    4.   5
k-1     3.    3.    3.    3.    3.    3
cond.   F.    F.    F.    T.    T.    T
sum     1.   13.    8.    2     51.   42
max.    ~.    ~.    ~.    2.    51    51    
*/
```

4. Count Occurences of Anagrams
   Given a word pat and a text txt. Return the count of the occurrences of anagrams of the word in the text.

Example 1:

Input: txt = "forxxorfxdofr", pat = "for"
Output: 3
Explanation: for, orf and ofr appears in the txt, hence answer is 3.
Example 2:

Input: txt = "aabaabaa", pat = "aaba"
Output: 4
Explanation: aaba is present 4 times in txt.
Constraints:
1 <= |pat| <= |txt| <= 105
Both strings contain lowercase English letters.

```java
class Solution {

    int search(String pat, String txt) {
        // code here
        if(pat.length() > txt.length()) return -1;
        int k = pat.length();
        int[] freq = new int[26];
        int[] window = new int[26];
        
        for(int i=0;i<k;i++) {
            freq[pat.charAt(i)-'a']++;
            window[txt.charAt(i)-'a']++;
        }
        int count = Arrays.equals(freq, window) ? 1 : 0;
        
        for(int right=k;right<txt.length();right++){
            // add coming char
            window[txt.charAt(right)-'a']++;
            // remove window char
            window[txt.charAt(right-k)-'a']--;
            
            if(Arrays.equals(freq, window)) count++;
        }
        
        return count;
        
        
    }
}

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
```
Condition	Purpose	First true at	Why
right >= k	Remove outgoing char	right = k	Window just exceeded size k
right >= k - 1	Check for match	right = k - 1	Window just reached size k for the first time

Both are correct as written in the single-loop solution — they're intentionally offset by one because "window first becomes valid" and "window first needs shrinking" are two different moments in the iteration.
```java
class Solution {

    int search(String pat, String txt) {
        // code here
        if(pat.length() > txt.length()) return -1;
        int k = pat.length();
        int[] freq = new int[26];
        int[] window = new int[26];
        int count =0;
        
        for(int i=0;i<k;i++) {
            freq[pat.charAt(i)-'a']++;
        }
        
        for(int right=0;right<txt.length();right++){
            // add coming char
            window[txt.charAt(right)-'a']++;
            // remove window char
            if(right >= k) {
               window[txt.charAt(right-k)-'a']--;
            }
            if(right >= k-1 && Arrays.equals(freq, window)) {
             count++;   
            }
            
            
        }
        return count;
    }
}

/*

f. o. r. x. x. o. r. f. x. d.  o.  f.  r
0. 1. 2. 3. 4. 5. 6. 7  8. 9  10. 11. 12

freq {f:1, o:1, r:1}
window {f:1, 0:1, r:1 }

                     3 

*/
```
5.  Sliding Window Maximum
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
Example 2:

Input: nums = [1], k = 1
Output: [1]

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

For Sliding Window Maximum, memorize this:

1. Remove expired indices → FRONT
2. Remove smaller values   → BACK
3. Front = maximum

// expired
deque.peekFirst() <= right - k

// smaller
nums[deque.peekLast()] <= nums[right]

// maximum
nums[deque.peekFirst()]

6. Permutation in String

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.



Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false

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
TC O(n) Sc O(1)



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



7. Sliding Window Median
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.

For examples, if arr = [2,3,4], the median is 3.
For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted


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

[1,3,-1,-3,5,3,6,7], k=3,

① Two heaps
```text
small = Max Heap
large = Min Heap
```
② Why?
```text
small.peek() = largest of smaller half
large.peek() = smallest of larger half
```
③ Balance
```text
smallSize == largeSize
OR
smallSize == largeSize + 1
```
④ Median
```text
k odd:
small.peek()


k even:
((double) small.peek() + large.peek()) / 2.0
```
⑤ Removal
```text
Don't directly remove from heap.
        ↓
Mark in delayed map.
        ↓
Update logical size.
        ↓
Prune when it reaches the top.
```

8. Repeated DNA Sequences
The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.

For example, "ACGAATTCCG" is a DNA sequence.
When studying DNA, it is useful to identify repeated sequences within the DNA.

Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.



Example 1:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
Example 2:

Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]

```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> dna = new HashMap<>();
        List<String> result = new ArrayList<>();

        for(int i =0; i+10 <=s.length();i++) {
            String sub = s.substring(i, i+10);
            int cnt = dna.merge(sub, 1, Integer::sum);
            if(cnt == 2){
                result.add(sub);
            }
        }
        return result;
        
    }
}
```
TC SC O(n)

Following Problems are variable window problem

for (right = 0 → n-1)

    add nums[right]

    if window becomes invalid:
        move left
        remove nums[left]

    update answer

9. Longest Substring Without Repeating Characters
   Given a string s, find the length of the longest substring without duplicate characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

```java
class Solution {
    public int longestNonRepeatingSubstring(String s) {
        int max = 1;
        Set<Character> set = new HashSet<>();
        int left =0;
        for(int right =0; right<s.length();right++){
            while(set.contains(s.charAt(right))){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            max = Math.max(max, right-left+1);
        }
        return max;
    }
}

/* a a a b b b c c c
0 1 2 3 4 5 6 7 8 */
```

10. Longest Substring With At Most K Distinct Characters
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

```java
class Solution {
    public int kDistinctChar(String s, int k) {
        if(s == null || s.isEmpty() || k == 0) return 0;

        int maxLen =0, left =0;
        Map<Character, Integer> freq = new HashMap<>();
        for(int right=0; right< s.length(); right++){
            char c = s.charAt(right);
            freq.merge(c, 1, Integer::sum);
            while(freq.size() > k) {
                char ch = s.charAt(left);
                int count = freq.get(ch);
                if(count == 1){
                    freq.remove(ch);
                } else {
                    freq.put(ch, count-1);
                }
                left++;
            }
            maxLen = Math.max(maxLen, right-left+1);
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

Mistakes : hardcoded k value in the code
using set instead of map, when I remove the character from left set does not count for frequencies
Example: window "aba", k=2. If you remove s.charAt(left) = 'a' from the set as you shrink past the first 'a', but there's another 'a' still inside the window (at index 2), you've now wrongly dropped 'a' from your distinct-count tracking even though it's still present.

11. Longest Substring with At Most Two Distinct Characters — LeetCode 159 (premium)
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