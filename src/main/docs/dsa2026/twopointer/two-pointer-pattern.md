# Two Pointers Problem Notes

## Problem List

**A: Opposite-End Converging**

1. Two Sum II - Input Array Is Sorted (Easy)
2. 3Sum (Medium)
3. 3Sum Closest (Medium)
4. 3Sum Smaller (Medium)
5. 4Sum (Medium)
6. Container With Most Water (Medium)
7. Trapping Rain Water (Hard)
8. Valid Palindrome (Easy)
9. Valid Palindrome II (Easy)
10. Boats to Save People (Medium)
11. Squares of a Sorted Array (Easy)
12. Max Number of K-Sum Pairs (Medium)

**B: Same-Direction / In-Place**

13. Remove Duplicates from Sorted Array (Easy)
14. Remove Duplicates from Sorted Array II (Medium)
15. Remove Duplicates from Sorted List II (Linked List LC 82) (Medium)
16. Remove Element (Easy)
17. Move Zeroes (Easy)
18. Merge Sorted Array (Easy)
19. Sort Array By Parity (Easy)
20. Sort Array By Parity II (Easy)
21. Sort Colors (Dutch National Flag) (Medium)
22. String Compression (Medium)
23. Remove All Adjacent Duplicates In String (Easy)

**C: Two Arrays/Strings**

24. Intersection of Two Arrays (Easy)
25. Intersection of Two Arrays II (Easy)
26. Is Subsequence (Easy)
27. Backspace String Compare (Easy)
28. Long Pressed Name (Easy)
29. One Edit Distance (Medium)
30. Interval List Intersections (Medium)
31. Merge Strings Alternately (Easy)

**D: Reversal / Rearrangement**

32. Reverse String (Easy)
33. Reverse Vowels of a String (Easy)
34. Reverse Words in a String (Medium)
35. Reverse Words in a String III (Easy)
36. Rotate Array (Medium)
37. Reverse Only Letters (Easy)
38. Next Permutation (Medium)
39. Pancake Sorting (Medium)

**E: Palindrome Expand-Center**

40. Longest Palindromic Substring (Medium)
41. Palindromic Substrings (Medium)
42. Shortest Palindrome (Hard)
43. Valid Palindrome III (Hard)

**F: Partition / Condition-Based**

44. Partition Array Into Three Parts With Equal Sum (Easy)
45. Wiggle Sort II (Medium)
46. Sort Transformed Array (Medium)
47. Minimum Length of String After Deleting Similar Ends (Medium)
48. Count Pairs Whose Sum is Less than Target (Easy)
49. Bag of Tokens (Medium)

**G: Additional**

50. Find K Closest Elements (Medium)
51. Two Sum Less Than K (Easy)
52. DI String Match (Easy)
53. Height Checker (Easy)
54. Sentence Similarity III (Medium)
55. Reverse Prefix of Word (Easy)
---


## A: Opposite-End Converging

### 1. Two Sum II - Input Array Is Sorted (EASY)

**Description:**

You are given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.


Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
**Code:**

```java
   public int[] twoSum(int[] numbers, int target) {
      int left = 0, right = numbers.length - 1;
      while (left < right) {
         int sum = numbers[left] + numbers[right];
         if (sum == target) return new int[]{left + 1, right + 1};
         else if (sum < target) left++;
         else right--;
      }
      return new int[]{-1, -1};
   }


```
**Logic / Approach:**

Opposite-end converging pointers. Because the array is sorted,
increasing sum means moving left forward and decreasing sum means moving
right backward; no need to check every pair.



### 2. 3Sum (MEDIUM)

**Description:**

Given an array, find all unique triplets that sum to zero.

**Code:**

```java
 public List<List<Integer>> threeSum(int[] nums) {
   Arrays.sort(nums);
   List<List<Integer>> result = new ArrayList<>();
   for (int i = 0; i < nums.length - 2; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) continue;
      int left = i + 1, right = nums.length - 1;
      while (left < right) {
         int sum = nums[i] + nums[left] + nums[right];
         if (sum == 0) {
            result.add(Arrays.asList(nums[i], nums[left], nums[right]));
            while (left < right && nums[left] == nums[left + 1]) left++;
            while (left < right && nums[right] == nums[right - 1]) right--;
            left++; right--;
         } else if (sum < 0) left++;
         else right--;
      }
   }
   return result;
}
```
**Logic / Approach:**

Duplicate skipping at all three positions avoids repeated triplets.

Priyanka --

We need following conditions to skip duplicate triplet

For example [-1,-1,0,0,1,1]  -1,0,1 if we omit following condition  we would end up getting -1,0,1 twice

if (i > 0 && nums[i] == nums[i - 1]) continue;

We also need following loop to skip duplicate second and third element

Skip duplicate second elements while (left < right && nums[left] == nums[left - 1]) left++; 
Skip duplicate third elements while  (left < right && nums[right] == nums[right + 1]) right--;

[-2, 0, 0, 0, 2, 2] if we take following example without above
duplicate check we would end up with [-2, 0, 2] and [-2, 0, 2] which
we do no want hence the above loops

**Mistakes to Avoid:**

1. initializing left and right outside of the loop they need to be set
   for every iteration

2. when sum is 0 then only we do duplicate skip check not every time
   if(sum ==0 ). ->

while(left < right && nums[left] == nums[left-1]) left++

while(left < right && nums[right] == nums[right+1])right--;



### 3. 3Sum Closest (MEDIUM)

**Description:**

You are given an integer array nums of length n and an integer target.
Find three integers at distinct indices in nums such that the sum is closest to target.
Return the sum of the three integers.
You may assume that each input would have exactly one solution.

Example 1:
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

**Code:**

```java
public int threeSumClosest(int[] nums, int target) {
   Arrays.sort(nums);
   int closest = nums[0] + nums[1] + nums[2];
   for (int i = 0; i < nums.length - 2; i++) {
      int left = i + 1, right = nums.length - 1;
      while (left < right) {
         int sum = nums[i] + nums[left] + nums[right];
         if (Math.abs(sum - target) < Math.abs(closest - target))
            closest = sum;
         if (sum == target) return sum;
         else if (sum < target) left++;
         else right--;
      }
   }
   return closest;
}
```
**Logic / Approach:**

Sort the array to enable the two-pointer technique.
Track the closest sum by comparing:
Math.abs(target - sum) with Math.abs(target - closest)
Move pointers based on the target:
sum < target → left++
sum > target → right--
sum == target → return immediately, since it\'s the best possible
answer.
O(n log n) + O(n × n)
= O(n log n + n²)
= O(n²)
Since O(n²) dominates O(n log n), the final time complexity is:

✅ Time Complexity = O(n²)

**Mistakes to Avoid:**
1. arrays needs to be sorted
2. logic wont execute for last two indices so i< nums.length-2

### 4. 3Sum Smaller (MEDIUM)

**Description:**

Given an array and a target, count the number of triplets whose sum is strictly less than target.

**Code:**

```java
public int threeSumSmaller(int[] nums, int target) {

Arrays.sort(nums);

int count = 0;

for (int i = 0; i < nums.length - 2; i++) {

int left = i + 1, right = nums.length - 1;

while (left < right) {

if (nums[i] + nums[left] + nums[right] < target) {

count += right - left;

left++;

} else {

right--;

}

}

}

return count;

}
```
**Logic / Approach:**

Sort, fix one element, and use converging pointers. When a sum is small
enough, every index between left and right also satisfies the condition
with the current left, so right-left pairs are counted in one step.

Step 1: Fix i = 0 (nums[i] = -1)

left = 1 (1)

right = 4 (4)

sum = -1 + 1 + 4 = 4 Since: 4 < 6 every element between left and right
with the current left also works.

Valid triplets are: (-1,1,2) (-1,1,3) (-1,1,4)

Instead of checking each one, we simply do:

count += right - left

+= 4 - 1

+= 3

Current count = 3 Move left++.

Why count += right - left?

When: nums[i] + nums[left] + nums[right] < target

the current right is the largest possible third element. Since the array
is sorted, every index between left + 1 and right gives an equal or
smaller sum, so all these triplets are valid. That\'s why we can add:
count += right - left;

**Mistakes to Avoid:**

not doing count +=right-left

### 5. 4Sum (MEDIUM)

*Difficulty: Medium*

**Description:**

Given an array and a target, find all unique quadruplets that sum to
target.

**Code:**

```java
public List<List<Integer>> fourSum(int[] nums, int target) {

Arrays.sort(nums);

List<List<Integer>> result = new ArrayList<>();

int n = nums.length;

for (int i = 0; i < n - 3; i++) {

if (i > 0 && nums[i] == nums[i - 1]) continue;

for (int j = i + 1; j < n - 2; j++) {

if (j > i + 1 && nums[j] == nums[j - 1]) continue;

int left = j + 1, right = n - 1;

while (left < right) {

long sum = (long) nums[i] + nums[j] + nums[left] +
nums[right];

if (sum == target) {

result.add(Arrays.asList(nums[i], nums[j], nums[left],
nums[right]));

while (left < right && nums[left] == nums[left + 1]) left++;

while (left < right && nums[right] == nums[right - 1]) right--;

left++; right--;

} else if (sum < target) left++;

else right--;

}

}

}

return result;

}
```
**Logic / Approach:**

Extension of 3Sum with two fixed loop indices (i, j) instead of one,
then opposite-end two pointers on the remaining subarray. Duplicate
skipping applied at every level.

**Mistakes to Avoid:**

not casting sum to long

### 6. Container With Most Water (MEDIUM)

*Difficulty: Medium*

**Description:**

Given heights of vertical lines, find two lines that together with the
x-axis form a container holding the most water.

**Code:**

```java
public int maxArea(int[] height) {

int left = 0, right = height.length - 1, maxArea = 0;

while (left < right) {

int width = right - left;

int minHeight = Math.min(height[left], height[right]);

maxArea = Math.max(maxArea, width * minHeight);

if (height[left] < height[right]) left++;

else right--;

}

return maxArea;

}
```
**Logic / Approach:**

Key Insight:

The height of the shorter line decides how much water the container can
hold because water cannot rise above the shorter boundary.

Start with two pointers at the leftmost and rightmost lines to get the
maximum possible width.

Calculate the area:

Area = min(height[left], height[right]) × (right - left)

To possibly get a larger area, move only the pointer at the shorter
line:

If you move the taller line, the width decreases but the limiting height
(shorter line) stays the same or becomes even smaller, so the area
cannot improve.

Moving the shorter line gives a chance to find a taller line that
increases the limiting height, which may compensate for the reduced
width.

**Mistakes to Avoid:**

_None noted._

### 7. Trapping Rain Water (HARD)

*Difficulty: Hard*

**Description:**

Given elevation heights, compute how much water can be trapped after
raining.

**Code:**

```java
public int trap(int[] height) {

int left = 0, right = height.length - 1;

int leftMax = 0, rightMax = 0, water = 0;

while (left < right) {

if (height[left] < height[right]) {

leftMax = Math.max(leftMax, height[left]);

water += leftMax - height[left];

left++;

} else {

rightMax = Math.max(rightMax, height[right]);

water += rightMax - height[right];

right--;

}

}

return water;

}
```
**Logic / Approach:**

The water trapped at any index is bounded by the shorter of the tallest
walls to its left and right. Processing from whichever side has the
smaller current height guarantees the running max on that side is the
true limiting wall.

The question here asks for the water trapped between the bars and we
have been given the height of the each bar in the array

To find out the water trapped between the bars we have to consider
height of the shorter wall since the water spills over the shorter wall

So Water trapped at any index depends on : minimum of (tallest wall on
the left, tallest wall on the right) - current height.

Brute force approach

For every index:

* Find tallest wall on left.

* Find tallest wall on right.

* Add trapped water.

We can solve this problem using two pointer approach

The amount of water trapped at any position depends on the shorter of
the tallest walls on its left and right.\"

For example,

Left Wall = 4

Right Wall = 7

Water can only rise to height 4.

The taller wall doesn\'t matter because water will spill over the
shorter wall.

Now comes the important part

\"Instead of finding the tallest wall on both sides for every index, I
keep track of them while moving two pointers.\"

I maintain:

* leftMax → tallest wall seen from the left

* rightMax → tallest wall seen from the right

The trick

Suppose

leftMax = 4

rightMax = 7

Since leftMax is smaller, I already know the maximum water level on the
left side is 4.

Even if later I find a taller wall on the right, say

rightMax = 10

the water level is still

min(4,10) = 4

It doesn\'t change.

So I can safely calculate the water at the left pointer and move it.

Similarly,

If

leftMax = 8

rightMax = 5

then the right side is the limiting wall.

So I process the right pointer.

\"Because the water level is always limited by the shorter boundary.
Once I know one side is shorter, I don\'t need any more information from
the other side. Even if the taller side becomes taller later, the
shorter side is still the limiting factor.\"

For every index, water depends on the smaller of the tallest walls on
the left and right. Instead of precomputing those arrays, I maintain
leftMax and rightMax using two pointers. If leftMax is smaller, I know
the left side is the limiting wall, so I can calculate the water at the
left pointer and move it. If rightMax is smaller or equal, I process the
right pointer for the same reason. This lets me solve the problem in one
pass with O(1) extra space.

[4,2,0,3,2,5]. Water min of leftpmax, rightmax - height

Height. [4,2,0,3,2,5].

leftMax. [4,4,4,4,4,5]

rightMax. [5,5,5,5,5,5]

water. [0,2,4,1,2,0]. =: 9

height[left]=5 is always ≥ height[right], so the algorithm treats
rightMax as the trustworthy ceiling and processes the right pointer,
moving inward. leftMax never gets updated past its initial 0 because
index 0 (height 5) is never \"processed\" --- same reasoning as before:
it\'s a boundary/tallest bar, so it correctly never contributes water.

Cross-check with DP arrays:

height: 5 2 1 2 5

leftMax: 5 5 5 5 5

rightMax: 5 5 5 5 5

water: 0 3 4 3 0 → sum = 10 ✓

**Mistakes to Avoid:**

_None noted._

### 8. Valid Palindrome (EASY)

*Difficulty: Easy*

**Description:**

Given a string, determine if it is a palindrome considering only
alphanumeric characters and ignoring case.

**Code:**

```java
public boolean isPalindrome(String s) {

int left = 0, right = s.length() - 1;

while (left < right) {

while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
left++;

while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
right--;

if (Character.toLowerCase(s.charAt(left)) !=
Character.toLowerCase(s.charAt(right))) return false;

left++; right--;

}

return true;

}
```
**Logic / Approach:**

Opposite-end pointers skip non-alphanumeric characters and compare
lowercase versions of the remaining characters, converging toward the
middle.

**Mistakes to Avoid:**

_None noted._

### 9. Valid Palindrome II (EASY)

*Difficulty: Easy*

**Description:**

Given a string, determine if it can become a palindrome by removing at
most one character.

**Code:**

```java
public boolean validPalindrome(String s) {

int left = 0, right = s.length() - 1;

while (left < right) {

if (s.charAt(left) != s.charAt(right)) {

return isPalindromeRange(s, left + 1, right) || isPalindromeRange(s,
left, right - 1);

}

left++; right--;

}

return true;

}

private boolean isPalindromeRange(String s, int left, int right) {

while (left < right) {

if (s.charAt(left) != s.charAt(right)) return false;

left++; right--;

}

return true;

}
```
**Logic / Approach:**

How it works

Start with two pointers:

left = 0

right = s.length() - 1

If characters match, move both pointers inward.

On the first mismatch, you have only two choices:

Skip the left character: isPalindrome(left + 1, right)

Skip the right character: isPalindrome(left, right - 1)

If either remaining substring is a palindrome, return true.

Example

For s = \"abca\":

a b c a

\^ \^

a == a → move inward.

b != c → check:

Skip b → \"aca\" ✅

Skip c → \"aba\" ✅

Return true.

**Mistakes to Avoid:**

_None noted._

### 10. Boats to Save People (MEDIUM)

*Difficulty: Medium*

**Description:**

Given people\'s weights and a boat weight limit (each boat carries at
most two people), find the minimum number of boats needed.

**Code:**

```java
public int numRescueBoats(int[] people, int limit) {

Arrays.sort(people);

int left = 0, right = people.length - 1;

int boats = 0;

while (left <= right) {

if (people[left] + people[right] <= limit) left++;

right--;

boats++;

}

return boats;

}
```
**Logic / Approach:**

Greedy + two pointers: pair the lightest person with the heaviest
person. If they fit together, send both. If not, the heaviest person
must go alone (since they\'re too heavy to pair with anyone, including
the lightest).

Why this works: if the heaviest person can\'t be paired with the
lightest, they can\'t be paired with anyone (everyone else is ≥ the
lightest\'s weight), so they must go alone. If they can pair with the
lightest, pairing them is never worse than any other pairing --- it
\"uses up\" the extra capacity as efficiently as possible.

Steps

Sort weights ascending.

Two pointers: left = 0, right = n - 1.

While left <= right:

If weights[left] + weights[right] <= limit, both go together →
left++, right--.

Else the heaviest goes alone → right--.

Either way, one boat is used.

Count boats.

**Mistakes to Avoid:**

_None noted._

### 11. Squares of a Sorted Array (EASY)

*Difficulty: Easy*

**Description:**

Given a sorted array that may contain negatives, return the squares of
each number sorted in non-decreasing order.

**Code:**

```java
public int[] sortedSquares(int[] nums) {

int n = nums.length;

int[] result = new int[n];

int left = 0, right = n - 1, writeIndex = n - 1;

while (left <= right) {

int leftSquare = nums[left] * nums[left];

int rightSquare = nums[right] * nums[right];

if (leftSquare > rightSquare) {

result[writeIndex] = leftSquare;

left++;

} else {

result[writeIndex] = rightSquare;

right--;

}

writeIndex--;

}

return result;

}
```
**Logic / Approach:**

The largest square always comes from one of the two extreme ends (most
negative or most positive). Comparing both ends and filling a new result
array from the back builds the sorted output in O(n).

**Mistakes to Avoid:**

_None noted._

### 12. Max Number of K-Sum Pairs (MEDIUM)

*Difficulty: Medium*

**Description:**

Given an array and an integer k, find the max number of disjoint pairs
whose sum equals k.

**Code:**

```java
public int maxOperations(int[] nums, int k) {

Arrays.sort(nums);

int left = 0, right = nums.length - 1, count = 0;

while (left < right) {

int sum = nums[left] + nums[right];

if (sum == k) {

count++;

left++; right--;

} else if (sum < k) left++;

else right--;

}

return count;

}
```
**Logic / Approach:**

Sort then use opposite-end pointers exactly like Two Sum II; every
matching pair is removed from consideration by moving both pointers
inward, and the count of such pairs is accumulated.

**Mistakes to Avoid:**

_None noted._

## B: Same-Direction / In-Place

### 13. Remove Duplicates from Sorted Array (EASY)

*Difficulty: Easy*

**Description:**

Given a sorted array nums, remove the duplicates in-place such that each
unique element appears only once. Return the number of unique elements
k.

**Code:**

```java
public int removeDuplicates(int[] nums) {

if (nums.length == 0) return 0;

int slow = 0;

for (int fast = 1; fast < nums.length; fast++) {

if (nums[fast] != nums[slow]) {

slow++;

nums[slow] = nums[fast];

}

}

return slow + 1;

}
```
**Logic / Approach:**

Same-direction read-write pointers. slow marks the last position of a
confirmed unique element; fast scans ahead. Works because duplicates are
always adjacent in a sorted array.

**Mistakes to Avoid:**

_None noted._

### 14. Remove Duplicates from Sorted Array II (MEDIUM)

*Difficulty: Medium*

**Description:**

Given a sorted array nums, remove duplicates in-place so each unique
element appears at most twice. Return the new length k.

**Code:**

```java
public int removeDuplicates(int[] nums) {

int slow = 0;

for (int fast = 0; fast < nums.length; fast++) {

if (slow < 2 || nums[fast] != nums[slow - 2]) {

nums[slow] = nums[fast];

slow++;

}

}

return slow;

}
```
**Logic / Approach:**

Since the array is sorted, duplicates are consecutive.

Keep a write pointer k indicating where to place the next valid element.

For each number:

If we\'ve written fewer than 2 elements, always keep it.

Otherwise, compare the current number with the element 2 positions
before the write pointer.

If they are different, keep the current number.

Otherwise, skip it.

Input:

nums = [1,1,1,2,2,3]

Current k Action Array (valid part)

1 0 Keep [1]

1 1 Keep [1,1]

1 2 Skip (same as nums[0]) [1,1]

2 2 Keep [1,1,2]

2 3 Keep [1,1,2,2]

3 4 Keep [1,1,2,2,3]

Return:

5

Why nums[k - 2]?

The last two kept elements determine whether we\'ve already kept two
copies of the current value:

If num == nums[k - 2], we\'ve already stored two occurrences → skip.

Otherwise, it\'s safe to keep the current number.

**Mistakes to Avoid:**

_None noted._

### -. Remove Duplicates from Sorted List II (Linked List LC 82) (MEDIUM)

*Difficulty: Medium*

**Description:**

Given the head of a sorted linked list, delete all nodes that have
duplicate numbers, leaving only distinct numbers from the original list.
Return the linked list sorted as well.

**Code:**

```java
class Solution {

public ListNode deleteDuplicates(ListNode head) {

ListNode dummy = new ListNode(0);

dummy.next = head;

ListNode prev = dummy;

ListNode curr = head;

while (curr != null) {

// Duplicate found

if (curr.next != null && curr.val == curr.next.val) {

int duplicateValue = curr.val;

// Skip all nodes having duplicateValue

while (curr != null && curr.val == duplicateValue) {

curr = curr.next;

}

// Remove entire duplicate block

prev.next = curr;

} else {

// Current node is unique

prev = curr;

curr = curr.next;

}

}

return dummy.next;

}

}
```
**Logic / Approach:**

Why do we need a dummy node?

A dummy node simplifies deletion, especially when duplicate nodes
include the head of the list. 1 -> 1 -> 2 solution is dummy -> 1 ->
1 -> 2

Why use two pointers (prev and curr)?

dummy -> 1 -> 2 -> 3 -> 3 -> 4

\^

prev

\^

curr

prev = last node confirmed to be unique

curr = node currently checking

Algorithm (2 cases):

Duplicate found: Skip all nodes with the duplicate value and connect
prev.next to the next distinct node. prev.next = curr

No duplicate: Move both prev and curr one step forward since the current
node is unique. prev = curr; curr = curr.next;

**Mistakes to Avoid:**

_None noted._

### 15. Remove Element (EASY)

*Difficulty: Easy*

**Description:**

Given an array nums and a value val, remove all occurrences of val
in-place and return the new length.

**Code:**

```java
public int removeElement(int[] nums, int val) {

int slow = 0;

for (int fast = 0; fast < nums.length; fast++) {

if (nums[fast] != val) {

nums[slow] = nums[fast];

slow++;

}

}

return slow;

}
```
**Logic / Approach:**

Same-direction read-write pointers. fast scans every element; slow only
advances and writes when the current element should be kept.

**Mistakes to Avoid:**

_None noted._

### 16. Move Zeroes (EASY)

*Difficulty: Easy*

**Description:**

Given an array nums, move all zeroes to the end while maintaining the
relative order of the non-zero elements, in-place.

**Code:**

```java
public void moveZeroes(int[] nums) {

int slow = 0;

for (int fast = 0; fast < nums.length; fast++) {

if (nums[fast] != 0) {

int temp = nums[slow];

nums[slow] = nums[fast];

nums[fast] = temp;

slow++;

}

}

}
```
**Logic / Approach:**

Same-direction pointers with a swap instead of an overwrite; slow tracks
the boundary of the non-zero region, naturally pushing zeroes to the
back while preserving order.

**Mistakes to Avoid:**

_None noted._

### 17. Merge Sorted Array (EASY)

*Difficulty: Easy*

**Description:**

Merge sorted array nums2 into nums1 (which has extra trailing space),
producing one sorted array in-place.

**Code:**

```java
public void merge(int[] nums1, int m, int[] nums2, int n) {

int i = m - 1, j = n - 1, write = m + n - 1;

while (j >= 0) {

if (i >= 0 && nums1[i] > nums2[j]) {

nums1[write] = nums1[i]; i--;

} else {

nums1[write] = nums2[j]; j--;

}

write--;

}

}
```
**Logic / Approach:**

Merge from the back so the larger remaining element is placed at the
current write position without overwriting unread values in nums1.

**Mistakes to Avoid:**

_None noted._

### 18. Sort Array By Parity (EASY)

*Difficulty: Easy*

**Description:**

Given an array nums, move all even integers to the front and all odd
integers to the back (any order within each group).

**Code:**

```java
public int[] sortArrayByParity(int[] nums) {

int left = 0, right = nums.length - 1;

while (left < right) {

if (nums[left] % 2 == 0) {

left++;

} else {

int temp = nums[left];

nums[left] = nums[right];

nums[right] = temp;

right--;

}

}

return nums;

}
```
**Logic / Approach:**

Opposite-end converging pointers used as a partition; odd values found
at left are swapped toward the back, shrinking right each time.

**Mistakes to Avoid:**

_None noted._

### 19. Sort Array By Parity II (EASY)

*Difficulty: Easy*

**Description:**

Given an array with equal counts of even and odd numbers, rearrange so
every even index holds an even number and every odd index holds an odd
number.

**Code:**

```java
public int[] sortArrayByParityII(int[] nums) {

int even = 0, odd = 1, n = nums.length;

while (even < n && odd < n) {

if (nums[even] % 2 == 0) {

even += 2;

} else if (nums[odd] % 2 == 1) {

odd += 2;

} else {

int temp = nums[even];

nums[even] = nums[odd];

nums[odd] = temp;

}

}

return nums;

}
```
**Logic / Approach:**

Two independent pointers walk even and odd indices separately (step 2);
a swap happens only when both are simultaneously misplaced, fixing both
positions at once.

**Mistakes to Avoid:**

_None noted._

### 20. Sort Colors (Dutch National Flag) (MEDIUM)

*Difficulty: Medium*

**Description:**

Given an array of values 0, 1, 2, sort it in-place in one pass so all 0s
come first, then 1s, then 2s.

**Code:**

```java
public void sortColors(int[] nums) {

int low = 0, mid = 0, high = nums.length - 1;

while (mid <= high) {

if (nums[mid] == 0) {

int t = nums[low]; nums[low] = nums[mid]; nums[mid] = t;

low++; mid++;

} else if (nums[mid] == 1) {

mid++;

} else {

int t = nums[mid]; nums[mid] = nums[high]; nums[high] = t;

high--;

}

}

}
```
**Logic / Approach:**

Three pointers partition the array into four regions (0s, 1s, unknown,
2s). mid scans the unknown region, swapping 0s to the front and 2s to
the back, advancing only when safe.

**Mistakes to Avoid:**

_None noted._

### 21. String Compression (MEDIUM)

*Difficulty: Medium*

**Description:**

Compress a character array in-place, replacing runs of repeated
characters with the character followed by run length. Return the new
length.

**Code:**

```java
public int compress(char[] chars) {

int write = 0, read = 0, n = chars.length;

while (read < n) {

char c = chars[read];

int count = 0;

while (read < n && chars[read] == c) { read++; count++; }

chars[write++] = c;

if (count > 1) {

for (char d : Integer.toString(count).toCharArray()) chars[write++]
= d;

}

}

return write;

}
```
**Logic / Approach:**

Read-write pointers where read counts a full run before write commits
the character and, if needed, each digit of the count. write never
outruns read, so in-place compression is safe.

// maintain two pointers

// one for reading

// one for writing

// start reading from start until end of the array and char is matching

// keep increasing count and read pointer until codition is true

// once done write the character at write pointer and advance it

// and write the count at the end using write pointer

**Mistakes to Avoid:**

_None noted._

### 22. Remove All Adjacent Duplicates In String (EASY)

*Difficulty: Easy*

**Description:**

Repeatedly remove adjacent pairs of identical characters from a string
until no such pairs remain.

**Code:**

```java
public String removeDuplicates(String s) {

char[] stack = new char[s.length()];

int top = -1;

for (char c : s.toCharArray()) {

if (top >= 0 && stack[top] == c) top--;

else stack[++top] = c;

}

return new String(stack, 0, top + 1);

}
```
**Logic / Approach:**

An array-backed stack acts as the write pointer. A match with the top
cancels the pair (pop); otherwise the character is pushed. Same
read-write pattern with a cancellation rule.

**Mistakes to Avoid:**

_None noted._

## C: Two Arrays/Strings

### 23. Intersection of Two Arrays (EASY)

*Difficulty: Easy*

**Description:**

Given two arrays, return their unique intersection (each element appears
once).

**Code:**

```java
public int[] intersection(int[] nums1, int[] nums2) {

Arrays.sort(nums1);

Arrays.sort(nums2);

int i = 0, j = 0;

Set<Integer> result = new LinkedHashSet<>();

while (i < nums1.length && j < nums2.length) {

if (nums1[i] == nums2[j]) {

result.add(nums1[i]); i++; j++;

} else if (nums1[i] < nums2[j]) i++;

else j++;

}

int[] arr = new int[result.size()];

int idx = 0;

for (int v : result) arr[idx++] = v;

return arr;

}
```
**Logic / Approach:**

Sort both arrays, then walk two pointers through them simultaneously.
Equal values are recorded (deduplicated with a set) and both pointers
advance; the smaller value\'s pointer advances otherwise.

**Mistakes to Avoid:**

_None noted._

### 24. Intersection of Two Arrays II (EASY)

*Difficulty: Easy*

**Description:**

Given two arrays, return their intersection where each element appears
as many times as it shows in both.

**Code:**

```java
public int[] intersect(int[] nums1, int[] nums2) {

Arrays.sort(nums1);

Arrays.sort(nums2);

int i = 0, j = 0;

List<Integer> result = new ArrayList<>();

while (i < nums1.length && j < nums2.length) {

if (nums1[i] == nums2[j]) {

result.add(nums1[i]); i++; j++;

} else if (nums1[i] < nums2[j]) i++;

else j++;

}

return result.stream().mapToInt(Integer::intValue).toArray();

}
```
**Logic / Approach:**

* What if the given array is already sorted? How would you optimize
  your algorithm? -> problem can be solved using two pointer approach

* What if nums1\'s size is small compared to nums2\'s size? Which
  algorithm is better?

Suppose:

nums1 = [2,2,4] // very small

nums2 = [1,2,3,4,5,6,7,8,9,...] // huge

We should build the frequency map from the smaller array. Why? Because
we want to keep our HashMap as small as possible.

Map<Integer, Integer> map = new HashMap<>();

for (int num : nums1) {

map.put(num, map.getOrDefault(num, 0) + 1);

}

Then scan the larger array. So the general idea is: Put the smaller
array into memory, then stream through the larger array.

* What if elements of nums2 are stored on disk, and the memory is
  limited such that you cannot load all elements into the memory at once?

This is where the HashMap approach becomes especially useful.

Imagine:

nums1 = [1,2,2,4]

fits comfortably in memory.

But nums2 is a huge file that cannot fit into memory.

We don\'t need to load all of nums2.

We can:

1. Load nums1 into HashMap

2. Read nums2 from disk in chunks

3. Process each number

4. Add matches to result

5. Continue until the file ends

For 349 --- Intersection of Two Arrays: Duplicates don\'t matter →
HashSet

For 350 --- Intersection of Two Arrays II: Duplicates matter → HashMap
frequency

**Mistakes to Avoid:**

_None noted._

### 25. Is Subsequence (EASY)

*Difficulty: Easy*

**Description:**

Given strings s and t, determine if s is a subsequence of t.

**Code:**

```java
public boolean isSubsequence(String s, String t) {

if(s.length() > t.length()) return false;

int n1 = s.length();

int n2 = t.length();

int i=0,j=0;

while(i<n1 && j<n2) {

if(s.charAt(i) == t.charAt(j)) {

i++;

j++;

} else {

j++;

}

}

return n1 == i;

}
```
**Logic / Approach:**

Two pointers walk s and t simultaneously; i only advances on a character
match, j always advances. If i reaches the end of s, every character was
matched in order within t.

**Mistakes to Avoid:**

_None noted._

### 26. Backspace String Compare (EASY)

*Difficulty: Easy*

**Description:**

Given two strings containing \'#\' as a backspace character, determine
if they become equal after applying the backspaces.

**Code:**

```java
public class Solution {

public boolean backspaceCompare(String s, String t) {

int indexS = s.length() - 1, indexT = t.length() - 1;

while (indexS >= 0 || indexT >= 0) {

indexS = nextValidChar(s, indexS);

indexT = nextValidChar(t, indexT);

char charS = indexS >= 0 ? s.charAt(indexS) : \'\0\';

char charT = indexT >= 0 ? t.charAt(indexT) : \'\0\';

if (charS != charT) return false;

indexS--;

indexT--;

}

return true;

}

private int nextValidChar(String str, int index) {

int backspace = 0;

while (index >= 0) {

if (str.charAt(index) == \'#\') {

backspace++;

} else if (backspace > 0) {

backspace--;

} else {

break;

}

index--;

}

return index;

}

}
```
**Logic / Approach:**

Intuition

We can compare the strings character by character without building the
full result. Starting from the end of both strings, we find the next
valid character in each (skipping over characters deleted by
backspaces). If at any point these characters differ, the strings are
not equal. This approach uses O(1) extra space since we only track
pointers and counts.

Algorithm

Initialize two pointers at the end of each string.

Create a helper function that finds the next valid character index by:

Counting backspaces encountered.

Skipping characters that would be deleted.

Returning the index of the next valid character (or -1 if none).

While either pointer is valid:

Find the next valid character in each string.

Compare them (treat out-of-bounds as empty).

If they differ, return false.

Move both pointers left.

Return true if we finish without finding a mismatch.

**Mistakes to Avoid:**

\"Starting from this index, find me the next character that actually
survives the backspaces

### 27. Long Pressed Name (EASY)

*Difficulty: Easy*

**Description:**

Given a typed name and the intended name, determine if the typed name
could result from long-pressing some characters of the intended name.

**Code:**

```java
public boolean isLongPressedName(String name, String typed) {

int i = 0, j = 0;

while (j < typed.length()) {

if (i < name.length() && name.charAt(i) == typed.charAt(j)) {

i++; j++;

} else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {

j++;

} else {

return false;

}

}

return i == name.length();

}
```
**Logic / Approach:**

Characters equal

↓

move BOTH pointers

Characters different

↓

Is typed[j] same as typed[j-1]?

↓

YES → move typed only

↓

NO → false

**Mistakes to Avoid:**

not checking conditions like i< length

### 28. One Edit Distance (MEDIUM)

*Difficulty: Medium*

**Description:**

Given two strings, determine if they are exactly one edit (insert,
delete, or replace) apart.

**Code:**

```java
class Solution {

public boolean isOneEditDistance(String s, String t) {

int n = s.length(), m = t.length();

// Ensure s is the shorter (or equal-length) string, to simplify cases

if (n > m) return isOneEditDistance(t, s);

// Lengths differ by 2+ -> can\'t be one edit apart

if (m - n > 1) return false;

for (int i = 0; i < n; i++) {

if (s.charAt(i) != t.charAt(i)) {

if (n == m) {

// Same length -> must be a REPLACE: rest of strings must match
exactly

return s.substring(i + 1).equals(t.substring(i + 1));

} else {

// s is shorter -> must be an INSERT into s (or delete from t)

// Skip the extra char in t, then rest must match exactly

return s.substring(i).equals(t.substring(i + 1));

}

}

}

// No mismatch found in the shared prefix:

// - if lengths equal, strings are identical -> 0 edits -> false

// - if lengths differ by 1, the extra char in t is the one edit ->
true

return n != m;

}

}
```
**Logic / Approach:**

Two pointers advance through both strings; on a mismatch, one edit is
consumed, and i only advances if the strings are equal length (a
replace), otherwise only j advances (an insert into the shorter string).

1. Length difference > 1

↓

false

2. First mismatch + same length

↓

REPLACE

↓

compare rest after i

3. First mismatch + different length by 1

↓

INSERT/DELETE

↓

skip one character from longer string

**Mistakes to Avoid:**

_None noted._

### 29. Interval List Intersections (MEDIUM)

*Difficulty: Medium*

**Description:**

Given two lists of disjoint, sorted intervals, return their intersection
intervals.

**Code:**

```java
public int[][] intervalIntersection(int[][] a, int[][] b)
{

List<int[]> result = new ArrayList<>();

int i = 0, j = 0;

while (i < a.length && j < b.length) {

int start = Math.max(a[i][0], b[j][0]);

int end = Math.min(a[i][1], b[j][1]);

if (start <= end) result.add(new int[]{start, end});

if (a[i][1] < b[j][1]) i++;

else j++;

}

return result.toArray(new int[result.size()][]);

}
```
**Logic / Approach:**

Two pointer approach. You have two sorted lists of intervals, A and B.
Since both are already sorted by start time, you can walk through them
with one pointer each (i for A, j for B) instead of comparing every
pair.

Key insight --- finding the overlap:

For any two intervals A[i] and B[j], their intersection (if it
exists) is:

start = max(A[i].start, B[j].start)

end = min(A[i].end, B[j].end)

If start <= end, that\'s a valid overlapping interval --- add it to the
result.

Key insight --- which pointer to move:

After checking A[i] and B[j], you need to decide which one to
advance. The trick: whichever interval ends first can no longer overlap
with anything else in the other list (since the other list is sorted,
everything after this point starts even later). So:

if A[i].end < B[j].end, move i forward

else move j forward

This works even if there\'s no overlap between the current pair ---
you\'re essentially always discarding the interval that \"expires\"
first.

Why it terminates correctly: each step retires at least one interval
permanently, so you make progress through both lists in O(m + n) total,
no backtracking needed.

That\'s the whole idea --- compute the overlap of the current pair, keep
it if valid, then drop whichever interval finishes earliest and
continue.

**Mistakes to Avoid:**

_None noted._

### 30. Merge Strings Alternately (EASY)

*Difficulty: Easy*

**Description:**

Given two strings, merge them by adding letters in alternating order,
starting with the first string; append any leftover letters.

**Code:**

```java
public String mergeAlternately(String word1, String word2) {

StringBuilder sb = new StringBuilder();

int i = 0, j = 0;

while (i < word1.length() || j < word2.length()) {

if (i < word1.length()) sb.append(word1.charAt(i++));

if (j < word2.length()) sb.append(word2.charAt(j++));

}

return sb.toString();

}
```
**Logic / Approach:**

Two pointers advance through both strings in lockstep, appending one
character from each in turn; once one string is exhausted, only the
other pointer keeps contributing.

**Mistakes to Avoid:**

_None noted._

## D: Reversal / Rearrangement

### 31. Reverse String (EASY)

*Difficulty: Easy*

**Description:**

Reverse an array of characters in-place.

**Code:**

```java
public void reverseString(char[] s) {

int left = 0, right = s.length - 1;

while (left < right) {

char t = s[left]; s[left] = s[right]; s[right] = t;

left++; right--;

}

}
```
**Logic / Approach:**

Classic opposite-end converging pointers, swapping characters and moving
inward until they meet.

**Mistakes to Avoid:**

_None noted._

### 32. Reverse Vowels of a String (EASY)

*Difficulty: Easy*

**Description:**

Reverse only the vowels of a string, leaving other characters in place.

**Code:**

```java
public String reverseVowels(String s) {

char[] arr = s.toCharArray();

String vowels = \"aeiouAEIOU\";

int left = 0, right = arr.length - 1;

while (left < right) {

while (left < right && vowels.indexOf(arr[left]) == -1) left++;

while (left < right && vowels.indexOf(arr[right]) == -1) right--;

char t = arr[left]; arr[left] = arr[right]; arr[right] = t;

left++; right--;

}

return new String(arr);

}
```
**Logic / Approach:**

convert string to array

while iterating from both left and right check if the current char is
vowel if yes swap it else keep moving the pointer

**Mistakes to Avoid:**

_None noted._

### 33. Reverse Words in a String (MEDIUM)

*Difficulty: Medium*

**Description:**

Given a string with words separated by spaces, reverse the order of the
words, removing extra whitespace.

**Code:**

```java
public String reverseWords(String s) {

String[] words = s.trim().split(\"\\s+\");

int left = 0, right = words.length - 1;

while (left < right) {

String t = words[left]; words[left] = words[right];
words[right] = t;

left++; right--;

}

return String.join(\" \", words);

}
```
**Logic / Approach:**

Split into words, then apply opposite-end converging pointers on the
word array to reverse their order in place before rejoining.

**Mistakes to Avoid:**

removed leading and trailling dots s =
s.replaceAll(\"\^\\.+|\\.+$\", \"\");

split(\"\\.+\") treats one-or-more consecutive dots as a single
delimiter.

### 34. Reverse Words in a String III (EASY)

*Difficulty: Easy*

**Description:**

Given a string, reverse the characters within each word while preserving
word order and whitespace.

**Code:**

```java
public String reverseWords(String s) {

// String[] arr = s.split(\" \");

// StringBuilder sb = new StringBuilder();

// for(int i=0;i<arr.length;i++) {

// arr[i] = reverse(arr[i]);

// }

// return String.join(\" \", arr);

String[] words = s.split(\" \");

StringBuilder sb = new StringBuilder();

for(String word: words) {

sb.append(new StringBuilder(word).reverse()).append(\" \");

}

return sb.toString().trim();

}

private String reverse(String word){

char[] arr = word.toCharArray();

int left =0;

int right = word.length()-1;

while(left < right) {

char temp = arr[left];

arr[left] = arr[right];

arr[right] = temp;

left++;

right--;

}

return new String(arr);

}
```
**Logic / Approach:**

A scanning pointer finds word boundaries (spaces); for each word found,
an inner opposite-end pointer pair reverses just that word\'s characters
in place.

**Mistakes to Avoid:**

_None noted._

### 35. Rotate Array (MEDIUM)

*Difficulty: Medium*

**Description:**

Rotate an array to the right by k steps, in-place.

**Code:**

```java
public void rotate(int[] nums, int k) {

int n = nums.length;

k %= n;

reverse(nums, 0, n - 1);

reverse(nums, 0, k - 1);

reverse(nums, k, n - 1);

}

private void reverse(int[] nums, int left, int right) {

while (left < right) {

int t = nums[left]; nums[left] = nums[right]; nums[right] = t;

left++; right--;

}

}
```
**Logic / Approach:**

Reverse the whole array, then reverse the first k and remaining n-k
elements separately. Three applications of the opposite-end reversal
pointer pattern achieve an in-place rotation in O(n).

**Mistakes to Avoid:**

_None noted._

### 36. Reverse Only Letters (EASY)

*Difficulty: Easy*

**Description:**

Given a string, reverse only the letter characters, leaving non-letters
(digits, symbols) in their original positions.

**Code:**

```java
public String reverseOnlyLetters(String s) {

char[] arr = s.toCharArray();

int left = 0, right = arr.length - 1;

while (left < right) {

while (left < right && !Character.isLetter(arr[left])) left++;

while (left < right && !Character.isLetter(arr[right])) right--;

char t = arr[left]; arr[left] = arr[right]; arr[right] = t;

left++; right--;

}

return new String(arr);

}
```
**Logic / Approach:**

Opposite-end pointers skip non-letter characters and swap only letters,
so non-letters stay fixed while letters are mirrored around the center.

**Mistakes to Avoid:**

_None noted._

### 37. Next Permutation (MEDIUM)

*Difficulty: Medium*

**Description:**

Rearrange an array into the lexicographically next greater permutation,
in-place; if none exists, rearrange to the lowest order.

**Code:**

```java
class Solution {

public void nextPermutation(int[] nums) {

// Step 1: Find the breakpoint

int i = nums.length - 2;

while (i >= 0 && nums[i] >= nums[i + 1]) {

i--;

}

// Step 2: Find the next greater element

if (i >= 0) {

int j = nums.length - 1;

while (nums[j] <= nums[i]) {

j--;

}

swap(nums, i, j);

}

// Step 3: Make suffix smallest

reverse(nums, i + 1, nums.length - 1);

}

private void swap(int[] nums, int i, int j) {

int temp = nums[i];

nums[i] = nums[j];

nums[j] = temp;

}

private void reverse(int[] nums, int left, int right) {

while (left < right) {

swap(nums, left, right);

left++;

right--;

}

}

}
```
**Logic / Approach:**

Scan from the right to find the first descending point, swap it with the
smallest greater value to its right, then reverse the suffix using
opposite-end pointers to get the smallest possible ordering after the
swap.

1\) find the element from the right that breaks increasing order

1 2 3 5 4

0 1 2 3 4

nums[i] < nums[i+1]. -> 3 < 5 so i = 2

2\) swap with smaller element from which is greater than 3

1 2 3 5 4. 1 2 4 5 3

0 1 2 3 4

\^ \^

3\) reverse everything after pivot

1 2 4 5 3. -> 1 2 4 3 5

0 1 2 3 4

\^

**Mistakes to Avoid:**

wrong tracing for 3 2 1

ip 1,2,3,5,4

op 1,2,4,3,5

ip 1,2,3,5,4

op 1,2,4,3,5

### 38. Pancake Sorting (MEDIUM)

*Difficulty: Medium*

**Description:**

Given an array, sort it using only \'pancake flips\' (reverse a prefix
of the array); return the sequence of flip sizes.

**Code:**

```java
class Solution {

public List<Integer> pancakeSort(int[] arr) {

List<Integer> result = new ArrayList<>();

int n = arr.length;

for (int size = n; size > 1; size--) {

// Find index of max element in arr[0...size-1]

int maxIdx = findMaxIndex(arr, size);

// Already in correct position, skip this round

if (maxIdx == size - 1) {

continue;

}

// Step 1: bring max to the front (skip if already there)

if (maxIdx != 0) {

flip(arr, maxIdx + 1);

result.add(maxIdx + 1);

}

// Step 2: flip it from front to its correct position at \'size - 1\'

flip(arr, size);

result.add(size);

}

return result;

}

private int findMaxIndex(int[] arr, int size) {

int maxIdx = 0;

for (int i = 1; i < size; i++) {

if (arr[i] > arr[maxIdx]) {

maxIdx = i;

}

}

return maxIdx;

}

private void flip(int[] arr, int k) {

int left = 0, right = k - 1;

while (left < right) {

int temp = arr[left];

arr[left] = arr[right];

arr[right] = temp;

left++;

right--;

}

}

}
```
**Logic / Approach:**

Look at the unsorted part of the array (initially the whole array).

Find the largest element in that unsorted part.

Flip the array up to that largest element\'s position --- this brings it
to the front.

Flip the array up to the end of the unsorted part --- this pushes the
largest element from the front to the end of the unsorted part, its
correct final position.

Shrink the unsorted part by one (exclude the last element, now correctly
placed).

Repeat steps 2--5 until only one element remains unsorted (it\'s
automatically in place).

That\'s the whole idea: repeatedly find the max, flip it to the front,
then flip it to where it belongs --- one element gets locked into its
correct position at the end of the array each round.

**Mistakes to Avoid:**

so many around loop , keeping one of the many possibilites

## E: Palindrome Expand-Center

### 39. Longest Palindromic Substring (MEDIUM)

*Difficulty: Medium*

**Description:**

Given a string, find the longest palindromic substring.

**Code:**

```java
public String longestPalindrome(String s) {

if (s.length() < 1) return \"\";

int start = 0, end = 0;

for (int i = 0; i < s.length(); i++) {

int len1 = expandFromCenter(s, i, i);

int len2 = expandFromCenter(s, i, i + 1);

int len = Math.max(len1, len2);

if (len > end - start + 1) {

start = i - (len - 1) / 2;

end = i + len / 2;

}

}

return s.substring(start, end + 1);

}

private int expandFromCenter(String s, int left, int right) {

while (left >= 0 && right < s.length() && s.charAt(left) ==
s.charAt(right)) {

left--; right++;

}

return right - left - 1;

}
```
**Logic / Approach:**

For every index (and every gap between indices, for even-length
palindromes), expand two pointers outward while characters match. The
widest successful expansion across all centers gives the longest
palindrome.

**Mistakes to Avoid:**

_None noted._

### 40. Palindromic Substrings (MEDIUM)

*Difficulty: Medium*

**Description:**

Given a string, count how many substrings are palindromes.

**Code:**

```java
public int countSubstrings(String s) {

int count = 0;

for (int i = 0; i < s.length(); i++) {

count += expandFromCenter(s, i, i);

count += expandFromCenter(s, i, i + 1);

}

return count;

}

private int expandFromCenter(String s, int left, int right) {

int count = 0;

while (left >= 0 && right < s.length() && s.charAt(left) ==
s.charAt(right)) {

count++; left--; right++;

}

return count;

}
```
**Logic / Approach:**

Same expand-around-center technique as Longest Palindromic Substring,
but every successful expansion step is counted (each is itself a valid
palindromic substring) instead of just tracking the longest one.

To solve the problem of counting all palindromic substrings in a string,
we use the expand-around-center technique. The core idea is:

A palindrome reads the same backward as forward.

For any character in the string, treat it as the potential center of a
palindrome and expand outward to check if the substring is a palindrome.

Palindromes can have:

Odd length: Center is a single character.

Even length: Center is between two characters.

By expanding from every possible center, we can systematically find all
palindromic substrings.

Why Two Center Expansions?

Odd-length palindromes have a single character center. Example: \"aba\"

Even-length palindromes have two consecutive characters as the center.
Example: \"abba\"

To ensure we count both types, we need two expansions for each
character: one for odd-length and one for even-length palindromes.

**Mistakes to Avoid:**

_None noted._

### 41. Shortest Palindrome (HARD)

*Difficulty: Hard*

**Description:**

Given a string s, find the shortest palindrome that can be formed by
adding characters only in front of s.

**Code:**

```java
using two pointer public String shortestPalindrome(String s) {

for (int i = s.length() - 1; i >= 0; i--) {

if (isPalindrome(s, 0, i)) {

String remaining = s.substring(i + 1);

return new StringBuilder(remaining)

.reverse()

.append(s)

.toString();

}

}

return s;

}

private boolean isPalindrome(String str, int start, int end) {

while (start < end) {

if (str.charAt(start) != str.charAt(end)) {

return false;

}

start++;

end--;

}

return true;

}

public String shortestPalindrome(String s) {

String combined = s + \"#\" + new StringBuilder(s).reverse();

int n = combined.length();

int[] lps = new int[n];

for (int i = 1; i < n; i++) {

int len = lps[i - 1];

while (len > 0 && combined.charAt(i) != combined.charAt(len)) len =
lps[len - 1];

if (combined.charAt(i) == combined.charAt(len)) len++;

lps[i] = len;

}

int palinLen = lps[n - 1];

return new StringBuilder(s.substring(palinLen)).reverse().toString() +
s;

}
```
**Logic / Approach:**

Find prefix

↓

Check palindrome with two pointers

↓

If no → shorten prefix

↓

Check again

↓

Found → reverse remaining + original

Start with the biggest possible prefix and keep shrinking it until we
find the first palindrome.

Because we start from the end, the first palindrome we find is
automatically the longest palindromic prefix.

Time complexity is O(n²), which is fine for understanding the problem
before moving to the optimal KMP solution.

**Mistakes to Avoid:**

_None noted._

### 42. Valid Palindrome III (HARD)

*Difficulty: Hard*

**Description:**

Given a string s and an integer k, determine if s can become a
palindrome after removing at most k characters.

**Code:**

```java
public boolean isValidPalindrome(String s, int k) {

int n = s.length();

int[][] dp = new int[n][n];

for (int len = 2; len <= n; len++) {

for (int left = 0; left <= n - len; left++) {

int right = left + len - 1;

if (s.charAt(left) == s.charAt(right)) {

dp[left][right] = dp[left + 1][right - 1];

} else {

dp[left][right] = 1 + Math.min(dp[left + 1][right],
dp[left][right - 1]);

}

}

}

return dp[0][n - 1] <= k;

}
```
**Logic / Approach:**

A left/right pointer pair defines each substring\'s boundary in a DP
table storing the minimum deletions needed to make that range a
palindrome; matching ends inherit the inner result, mismatches take the
cheaper of shrinking from either side.

**Mistakes to Avoid:**

_None noted._

## F: Partition / Condition-Based

### 43. Partition Array Into Three Parts With Equal Sum (EASY)

*Difficulty: Easy*

**Description:**

Given an array, determine if it can be split into three contiguous parts
each with the same sum.

**Code:**

```java
public boolean canThreePartsEqualSum(int[] arr) {

int sum = Arrays.stream(arr).sum();

if (sum % 3 != 0) {

return false;

}

int part = sum / 3;

int currentSum = 0;

int count = 0;

for (int i = 0; i < arr.length; i++) {

currentSum += arr[i];

if (currentSum == part) {

count++;

currentSum = 0;

}

}

return count >= 3;

}
```
**Logic / Approach:**

First calculate the total sum. If the total isn\'t divisible by 3, it\'s
impossible. Otherwise, each part must have sum total / 3. I scan from
left to right and keep accumulating the current part. Every time its sum
reaches the target, I\'ve found one valid partition, so I reset the sum
and look for the next one. If I can find at least three such parts, the
answer is true.

Complexity:

Time: O(n)

Space: O(1)

The important pattern to remember here isn\'t the code---it\'s:

Total → target per part → scan → whenever target reached, close a part.

**Mistakes to Avoid:**

checking currentSum<part is wrong

### 44. Wiggle Sort II (MEDIUM)

*Difficulty: Medium*

**Description:**

Given an array, reorder it so that nums[0] < nums[1] > nums[2]
< nums[3]... (strict wiggle pattern).

**Code:**

```java
public void wiggleSort(int[] nums) {

int[] sorted = nums.clone();

Arrays.sort(sorted);

int n = nums.length;

int right = n - 1;

for (int i = 1; i < n; i += 2) nums[i] = sorted[right--];

for (int i = 0; i < n; i += 2) nums[i] = sorted[right--];

}
```
**Logic / Approach:**

After sorting, a single pointer walking backward through the sorted
array fills all odd positions with the largest remaining values first,
then all even positions with the rest, guaranteeing no two adjacent
values are equal or out of wiggle order.

**Mistakes to Avoid:**

checking condition i<=n is wrong check i <n

### 45. Sort Transformed Array (MEDIUM)

*Difficulty: Medium*

**Description:**

Given a sorted array nums and quadratic coefficients a, b, c, return
f(x) = a*x\^2 + b*x + c applied to each element, sorted in ascending
order.

**Code:**

```java
public int[] sortTransformedArray(int[] nums, int a, int b, int c)
{

int n = nums.length;

int[] result = new int[n];

int left = 0, right = n - 1;

int index = a >= 0 ? n - 1 : 0;

while (left <= right) {

int leftVal = quad(nums[left], a, b, c);

int rightVal = quad(nums[right], a, b, c);

if (a >= 0) {

if (leftVal > rightVal) { result[index--] = leftVal; left++; }

else { result[index--] = rightVal; right--; }

} else {

if (leftVal < rightVal) { result[index++] = leftVal; left++; }

else { result[index++] = rightVal; right--; }

}

}

return result;

}

private int quad(int x, int a, int b, int c) {

return a * x * x + b * x + c;

}
```
**Logic / Approach:**

Because a sorted input passed through a quadratic produces its extreme
values at the two ends of the array (parabola shape), opposite-end
pointers compare transformed values and fill the result array from
whichever side (front or back) matches the parabola\'s direction.

why does a matter?

This is the subtle part.

a doesn\'t represent an array element.

a tells us the shape/direction of the parabola.

If:

a > 0

we have:

\ /

\\_\_\_\_\_/

It opens upward.

If:

a < 0

we have:

/\

/ \

/ \

It opens downward.

That\'s why the algorithm checks:

if (a >= 0)

Because the input is already sorted, the transformed values at the two
ends are the candidates for the next extreme value. So instead of
sorting the transformed array, we use two pointers and place values from
the appropriate end of the result

**Mistakes to Avoid:**

[-4,-2,2,4]

a =1, b= 3, c=5

here a > 0 that means parabola (symmetrical / U shaped curve) opens
upwards (i.e larger values are present at the extreme ends of the array
note that array is sorted)

if a < 0 that means parabola opens downwards

lets solve quadratic equation now for all the values to trace the
output.

-4 = 16-12+5 = 9

-2 = 4-6+5 = 3

2 = 4+6+5 = 15

4 = 16+12+5 =33

left 0 0 0 1

right 3 2 1 1

index 3 2 1 1

index = 3 (since a>0)

compare 9 and 33

compare 9 and 15

compare 9 and 3

[3,9,15,33]

### 46. Minimum Length of String After Deleting Similar Ends (MEDIUM)

*Difficulty: Medium*

**Description:**

Given a string, repeatedly delete a non-empty prefix and suffix made of
the same character (they must match) until no longer possible; return
the minimum resulting length.

**Code:**

```java
public int minimumLength(String s) {

int left = 0, right = s.length() - 1;

while (left < right && s.charAt(left) == s.charAt(right)) {

char c = s.charAt(left);

while (left <= right && s.charAt(left) == c) left++;

while (right >= left && s.charAt(right) == c) right--;

}

return right - left + 1;

}
```
**Logic / Approach:**

Opposite-end pointers repeatedly strip matching runs of the same
character from both ends simultaneously; the loop stops once the ends
differ or the pointers cross, leaving the minimal remaining length.

**Mistakes to Avoid:**

made mistake by adding extra condition. move pointer to left until all
chars are same and matching with right and vice verca

### 47. Count Pairs Whose Sum is Less than Target (EASY)

*Difficulty: Easy*

**Description:**

Given an array and a target, count the number of pairs (i, j) with i <
j such that nums[i] + nums[j] < target.

**Code:**

```java
public int countPairs(List<Integer> nums, int target) {

List<Integer> sorted = new ArrayList<>(nums);

Collections.sort(sorted);

int left = 0, right = sorted.size() - 1, count = 0;

while (left < right) {

if (sorted.get(left) + sorted.get(right) < target) {

count += right - left;

left++;

} else {

right--;

}

}

return count;

}
```
**Logic / Approach:**

Sort, then use converging pointers; whenever the pair at the current
ends sums below target, every index between left and right also pairs
validly with left, so those pairs are counted in bulk before advancing
left.

**Mistakes to Avoid:**

sum < target

↓

ALL elements between left and right

also work with left

↓

count += right - left

↓

left++

But:

sum >= target

↓

sum is too big

↓

need smaller number

↓

right--

### 48. Bag of Tokens (MEDIUM)

*Difficulty: Medium*

**Description:**

Given tokens and starting power, you may spend power to gain a score
(face-up) or spend score to gain power (face-down) using any token; find
the maximum score achievable.

**Code:**

```java
public int bagOfTokensScore(int[] tokens, int power) {

Arrays.sort(tokens);

int left = 0, right = tokens.length - 1;

int score = 0, maxScore = 0;

while (left <= right) {

if (power >= tokens[left]) {

power -= tokens[left++];

score++;

maxScore = Math.max(maxScore, score);

} else if (score > 0 && left < right) {

power += tokens[right--];

score--;

} else {

break;

}

}

return maxScore;

}
```
**Logic / Approach:**

Sort tokens, then greedily play the cheapest token face-up (left
pointer) whenever affordable to gain score, or the most expensive token
face-down (right pointer) to regain power when stuck, tracking the best
score seen.

Use two pointers:

left → smallest token

right → largest token

Why?

1. If we have enough power

Use the smallest token face-up.

power -= tokens[left]

score++

left++

Why smallest?

Because we want to gain 1 score while spending as little power as
possible.

2. If we don\'t have enough power

We can\'t play the smallest token face-up.

If we already have a score, we can:

use our largest token face-down

lose 1 score

gain lots of power

power += tokens[right]

score--

right--

SMALLEST ---------------------- LARGEST

↑ ↑

gain recover

score power

So the rule is:

Enough power?

↓

YES → use smallest → score++

NO

↓

Have score?

↓

YES → use largest → score--, power++

NO

↓

STOP

**Mistakes to Avoid:**

not checking left <= right doing score-=score instead of score-=1

33,4,28,24,96. 35

after sorting

4 24 28 33 96. power : 35

0 1. 2. 3. 4

left 0. 1. 2 2 3

right 4 4. 3 3 3

tokens[i] 4 24 96 28 33

power. 31 (35-1). 7 (31-24). 103(96+7). 75 (103-28) 42

score 1 2 1 2 3

## G: Additional

### 49. Find K Closest Elements (MEDIUM)

*Difficulty: Medium*

**Description:**

Given a sorted array, a target x, and an integer k, find the k closest
elements to x, sorted in ascending order.

**Code:**

```java
public List<Integer> findClosestElements(int[] arr, int k, int x)
{

int left = 0, right = arr.length - 1;

while (right - left + 1 > k) {

if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x))
right--;

else left++;

}

List<Integer> result = new ArrayList<>();

for (int i = left; i <= right; i++) result.add(arr[i]);

return result;

}
```
**Logic / Approach:**

1. Since arr is sorted, the k closest elements will always form a
   continuous window.

2. Start with the entire array using left = 0 and right = n-1.

While the window has more than k elements:

Compare |arr[left] - x| and |arr[right] - x|.

Remove the element that is farther from x.

If distances are equal, remove the right element because the smaller
value on the left wins the tie.

Once exactly k elements remain, return that window.

Key idea to remember:

Sorted array + K closest → shrink the window by removing the farther
end.

Complexity: O(n) time, O(k) space.

**Mistakes to Avoid:**

re do

### 50. Two Sum Less Than K (EASY)

*Difficulty: Easy*

**Description:**

Given an array and an integer k, find the maximum sum of two elements
that is strictly less than k.

**Code:**

```java
public int twoSumLessThanK(int[] nums, int k) {

Arrays.sort(nums);

int left = 0, right = nums.length - 1, best = -1;

while (left < right) {

int sum = nums[left] + nums[right];

if (sum < k) {

best = Math.max(best, sum);

left++;

} else {

right--;

}

}

return best;

}
```
**Logic / Approach:**

Sort then use converging pointers; each time the sum is under k it\'s a
candidate for the best answer and left advances to try a larger sum,
otherwise right shrinks to reduce an over-large sum.

**Mistakes to Avoid:**

_None noted._

**51. Minimum Difference Between Largest and Smallest Value in Three
Moves**

*Difficulty: Medium*

**Description:**

Given an array, you may change up to three elements to any value;
minimize the difference between the largest and smallest value in the
array.

**Code:**

```java
class Solution {

public int minDifference(int[] nums) {

int n = nums.length;

if(n <= 4) return 0;

// if there are less than 5 elements we make 3 of them the smallest
and min diff will be 0 e.g 4,5,6,7 -> 4,4,4,4. -> 4-4 =0

Arrays.sort(nums);

// since we have 3 possible moves we have to consider following
scenarios

// 1, 5, 6, 14, 15, 20

// 0 1. 2. 3. 4. 5

// Left 0 and right 3 (that means remove last 3 largest elements ).
-> 1,5,6. -> 6-1 = 5

// Left 1 and right 2 (that means remove 1 smallest and last 2 largest
elements ). -> 5,6,14. -> 14-5 = 9

// Left 2 and right 1 (that means remove 2 smallest and 1 largest
element). -> 6,14,15. -> 15-6 =9

// Left 3 and right 0 (that means remove 3 smallest element s). ->
14,15,20 -> 20-14 =6

int min = Integer.MAX_VALUE;

for(int left =0; left<=3;left++) {

int right = 3-left;

System.out.println(nums[n-1-right] - nums[left]);

min = Math.min(min, nums[n-1-right] - nums[left]);

}

return min;

}

}
```
**Logic / Approach:**

Instead of sorting we can find max 4 elements and min4 elements and
perform the minus operation

class Solution {

public int minDifference(int[] nums) {

int size = nums.length;

if (size < 5)

return 0;

int h1, h2, h3, h4;

int l1, l2, l3, l4;

h1 = h2 = h3 = h4 = Integer.MIN_VALUE;

l1 = l2 = l3 = l4 = Integer.MAX_VALUE;

for (int num : nums) {

if (num >= h1) {

h4 = h3;

h3 = h2;

h2 = h1;

h1 = num;

} else if (num >= h2) {

h4 = h3;

h3 = h2;

h2 = num;

} else if (num >= h3) {

h4 = h3;

h3 = num;

} else if (num > h4)

h4 = num;

if (num <= l1) {

l4 = l3;

l3 = l2;

l2 = l1;

l1 = num;

} else if (num <= l2) {

l4 = l3;

l3 = l2;

l2 = num;

} else if (num <= l3) {

l4 = l3;

l3 = num;

} else if (num < l4)

l4 = num;

}

return Math.min(Math.min(h1 - l4, h2 - l3), Math.min(h3 - l2, h4 - l1));

}

}

**Mistakes to Avoid:**

Key Idea

You are allowed to change at most 3 elements to any value.

After those changes, you want:

largest value - smallest value to be as small as possible.

The important observation is:

1. Sort the array

Once sorted:

[1, 5, 6, 14, 15, 20]

The only values that matter are the extremes.

2. Think about where to spend the 3 moves

You have 4 possible ways to remove the effect of the extreme values:

Change the 3 smallest elements

Change the 2 smallest + 1 largest

Change the 1 smallest + 2 largest

Change the 3 largest elements

### 52. DI String Match (EASY)

*Difficulty: Easy*

**Description:**

Given a string of \'I\' (increase) and \'D\' (decrease) characters,
construct a permutation of 0..n that matches the pattern.

**Code:**

```java
public int[] diStringMatch(String s) {

int n = s.length();

int low = 0, high = n;

int[] result = new int[n + 1];

for (int i = 0; i < n; i++) {

result[i] = s.charAt(i) == \'I\' ? low++ : high--;

}

result[n] = low;

return result;

}
```
**Logic / Approach:**

You need to build a permutation of numbers 0 to n.

Keep two pointers:

low = 0 (smallest unused number)

high = n (largest unused number)

Now process each character in the string:

If it is \'I\'

place the smallest available number (low)

increment low

If it is \'D\'

place the largest available number (high)

decrement high

At the end, only one number is left (low == high), so place it in the
last position. This greedy approach always works because each decision
satisfies the current constraint while preserving enough flexibility for
the remaining positions.

Quick trace on \"IDID\" (n=4, low=0, high=4):

i=0, \'I\' → result[0]=0, low=1

i=1, \'D\' → result[1]=4, high=3

i=2, \'I\' → result[2]=1, low=2

i=3, \'D\' → result[3]=3, high=2

result[4] = low = 2 (== high)

Final: [0, 4, 1, 3, 2] --- check: 0<4 (I), 4>1 (D), 1<3 (I), 3>2
(D) ✓

**Mistakes to Avoid:**

When you see I, you only need the current number to be small so the next
number can be larger.

The smallest unused number is the safest choice.

When you see D, you only need the current number to be large so the next
number can be smaller.

The largest unused number is the safest choice.

By always consuming one extreme (smallest or largest), all the middle
numbers remain available for future decisions.

### 53. Height Checker (EASY)

*Difficulty: Easy*

**Description:**

Given an array of heights, count how many indices differ from the array
sorted in non-decreasing order.

**Code:**

```java
public int heightChecker(int[] heights) {

int[] expected = heights.clone();

Arrays.sort(expected);

int count = 0;

for (int i = 0; i < heights.length; i++) {

if (heights[i] != expected[i]) count++;

}

return count;

}
```
**Logic / Approach:**

A single pointer walks the original array alongside the sorted copy at
the same index; any position where the two differ is counted as out of
place.

**Mistakes to Avoid:**

not using clone method on array object. heights.clone();

> **Google asked**

### 54. Sentence Similarity III (MEDIUM)

*Difficulty: Medium*

**Description:**

You are given two strings sentence1 and sentence2, each representing a
sentence composed of words. A sentence is a list of words that are
separated by a single space with no leading or trailing spaces. Each
word consists of only uppercase and lowercase English characters.

Two sentences s1 and s2 are considered similar if it is possible to
insert an arbitrary sentence (possibly empty) inside one of these
sentences such that the two sentences become equal. Note that the
inserted sentence must be separated from existing words by spaces.

For example,

s1 = \"Hello Jane\" and s2 = \"Hello my name is Jane\" can be made equal
by inserting \"my name is\" between \"Hello\" and \"Jane\" in s1.

s1 = \"Frog cool\" and s2 = \"Frogs are cool\" are not similar, since
although there is a sentence \"s are\" inserted into s1, it is not
separated from \"Frog\" by a space.

Given two sentences sentence1 and sentence2, return true if sentence1
and sentence2 are similar. Otherwise, return false.

**Code:**

```java
public boolean areSentencesSimilar(String sentence1, String sentence2)
{

String[] s1 = sentence1.split(\" \");

String[] s2 = sentence2.split(\" \");

// make sure s1 is shorter one

if(s1.length > s2.length) {

String[] temp = s1;

s1 = s2;

s2 = temp;

}

int n = s1.length;

int m = s2.length;

// prefix match

int i=0, j=0;

while(i < n && s1[i].equals(s2[i])) {

i++;

}

while(j < n-i && s1[n - 1 - j].equals(s2[m-1-j])) {

j++;

}

return i+j >= n;

}
```
**Logic / Approach:**

Split into word arrays; make w1 the shorter one (swap if needed).

Count matching words from the front (i).

Count matching words from the back (j), but don\'t let it overlap with
what i already counted.

If i + j >= len(w1), the entire shorter sentence is covered by the
prefix+suffix match, so the sentences are similar (the \"inserted\" part
is whatever remains in the middle of the longer one).

**Mistakes to Avoid:**

1: Didn\'t update the lengths after swapping

After swapping s1 and s2, n and m still referred to the original
sentence lengths.

This caused the loops to use incorrect bounds and could lead to an
ArrayIndexOutOfBoundsException.

2: dependent on entire logic

> **Linked in**

### 55. Reverse Prefix of Word (EASY)

*Difficulty: Easy*

**Description:**

Given a 0-indexed string word and a character ch, reverse the segment of
word that starts at index 0 and ends at the index of the first
occurrence of ch (inclusive). If the character ch does not exist in
word, do nothing.

For example, if word = \"abcdefd\" and ch = \"d\", then you should
reverse the segment that starts at 0 and ends at 3 (inclusive). The
resulting string will be \"dcbaefd\".

Return the resulting string.

**Code:**

```java
public String reversePrefix(String word, char ch) {

if(word == null || word.length() < 1) return word;

int end =0;

for(char c: word.toCharArray()) {

if(c == ch) {

return reverse(word.toCharArray(), 0, end++);

}

end++;

}

return word;

}

public String reverse(char[] word, int start, int end){

while(start <= end) {

char temp = word[start];

word[start] = word[end];

word[end] = temp;

start++;

end--;

}

return new String(word);

}
```
**Logic / Approach:**

_None noted._

**Mistakes to Avoid:**

1. if(word == null && word.length() < 1) → use || because
   word.length() causes NullPointerException when word is null.

2. while(start <= end) → pointers never move, causing an infinite
   loop; use while(start < end).

3. After swapping in reverse(), you forgot start++ and end-- to move
   pointers.

4. return word.toString() → char[] does not convert to String using
   toString(); use new String(word).

5. reverse(word.toCharArray(), 0, end++) → end++ is unnecessary because
   you return immediately; use end.
