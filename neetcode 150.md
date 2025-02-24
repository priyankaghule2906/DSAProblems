// I am creating file so that I can revise it whenever in hurry. I might add other problems than neetcode 150 in this
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
Arrays & Hashing
Contains Duplicate   	
Valid Anagram   	
Two Sum   	
Group Anagrams   	
Top K Frequent Elements   	
Encode and Decode Strings   	
Product of Array Except Self   	
Valid Sudoku   	
Longest Consecutive Sequence   

# 217. Contains Duplicate

## Problem Statement
Given an integer array `nums`, return `true` if any value appears at least twice in the array, and return `false` if every element is distinct.

### Example 1:
**Input:** `nums = [1,2,3,1]`  
**Output:** `true`  
**Explanation:** The element `1` occurs at indices `0` and `3`.

### Example 2:
**Input:** `nums = [1,2,3,4]`  
**Output:** `false`  
**Explanation:** All elements are distinct.

### Example 3:
**Input:** `nums = [1,1,1,3,3,4,3,2,4,2]`  
**Output:** `true`

## Approach 1: Using a HashSet (Optimal)

### Intuition
To efficiently check for duplicate values, we can utilize a `HashSet`. A `HashSet` stores unique elements and provides constant-time average complexity for insertions and lookups.

### Java Implementation
```java
import java.util.HashSet;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
```

### Time & Space Complexity
- **Time Complexity:** `O(n)`, where `n` is the length of `nums`, since we traverse the array once.
- **Space Complexity:** `O(n)`, in the worst case, where all elements are unique and stored in the HashSet.

---

## Approach 2: Sorting (Less Optimal)

### Intuition
If we sort the array first, any duplicate values will be adjacent to each other. This allows us to perform a single pass to check for duplicates.

### Java Implementation
```java
import java.util.Arrays;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
}
```

### Time & Space Complexity
- **Time Complexity:** `O(n log n)`, due to sorting.
- **Space Complexity:** `O(1)`, if sorting is done in place (or `O(n)` if using an extra array for sorting).

## Conclusion
- The **HashSet approach** is the best as it provides an `O(n)` time complexity solution with `O(n)` space.
- The **Sorting approach** is suboptimal due to its `O(n log n)` time complexity.
- Avoid brute force (`O(n^2)`) as it's too slow for large inputs.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------


# **Valid Anagram (Leetcode 242)**

## **Problem Statement**
Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

### **Example 1:**
**Input:**
```plaintext
s = "anagram", t = "nagaram"
```
**Output:**
```plaintext
true
```

### **Example 2:**
**Input:**
```plaintext
s = "rat", t = "car"
```
**Output:**
```plaintext
false
```

### **Constraints:**
- `1 <= s.length, t.length <= 5 * 10^4`
- `s` and `t` consist of lowercase English letters.

### **Follow-up:**
What if the inputs contain Unicode characters? How would you adapt your solution to such a case?

---

## **Intuition**

### **Brute Force Approach (Sorting)**
The most straightforward way to check if two strings are anagrams is:
1. Sort both strings.
2. If the sorted versions are equal, return `true`; otherwise, return `false`.

However, sorting has a time complexity of **O(n log n)**, which is inefficient for large inputs.

### **Optimal Approach (Using Frequency Count)**
Instead of sorting, we can:
1. Check if the lengths of `s` and `t` are different. If so, return `false`.
2. Use an array of size **26** (for lowercase English letters) to store the frequency of each character in `s`.
3. Iterate over `t` and decrement the frequency count.
4. If all counts are zero at the end, return `true`, else return `false`.

This runs in **O(n)** time with **O(1)** space.

---

## **Java Implementation**

### **Brute Force Approach (Sorting)**
```java
import java.util.Arrays;

public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return Arrays.equals(sArr, tArr);
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("rat", "car"));         // false
    }
}
```
**Time Complexity:** O(n log n)  
**Space Complexity:** O(n)

---

### **Optimal Approach (Frequency Count)**
```java
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] count = new int[26]; // Array for 26 lowercase letters
        
        for (char c : s.toCharArray()) count[c - 'a']++;
        for (char c : t.toCharArray()) count[c - 'a']--;
        
        for (int i : count) {
            if (i != 0) return false;
        }
        
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("rat", "car"));         // false
    }
}
```
**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

### **Handling Unicode Characters**
For Unicode characters, we use a `HashMap`:
```java
import java.util.HashMap;
import java.util.Map;

public class ValidAnagramUnicode {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        Map<Character, Integer> freq = new HashMap<>();
        
        for (char c : s.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);
        for (char c : t.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) - 1);
        
        for (int count : freq.values()) {
            if (count != 0) return false;
        }
        
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("你好", "好你")); // true
        System.out.println(isAnagram("hello", "world")); // false
    }
}
```
**Time Complexity:** O(n)  
**Space Complexity:** O(k) (where `k` is the number of unique characters)

---

## **Dry Run of Test Cases**

### **Test Case 1: "anagram" & "nagaram"**
#### **Step-by-step Execution for Frequency Count Method**

| Character | Initial Count (`s`) | After `t` Decrement | Final Count |
|-----------|--------------------|---------------------|-------------|
| 'a'       | +3                 | -3                 | 0           |
| 'n'       | +1                 | -1                 | 0           |
| 'g'       | +1                 | -1                 | 0           |
| 'r'       | +1                 | -1                 | 0           |
| 'm'       | +1                 | -1                 | 0           |

Since all counts are 0, the function returns `true`.

---

### **Test Case 2: "rat" & "car"**
#### **Step-by-step Execution for Frequency Count Method**

| Character | Initial Count (`s`) | After `t` Decrement | Final Count |
|-----------|--------------------|---------------------|-------------|
| 'r'       | +1                 | -1                 | 0           |
| 'a'       | +1                 | -1                 | 0           |
| 't'       | +1                 | 0                  | +1          |
| 'c'       | 0                  | -1                 | -1          |

Since not all counts are 0, the function returns `false`.

---

## **Summary**
| Approach | Time Complexity | Space Complexity | Notes |
|----------|----------------|------------------|-------|
| Sorting  | O(n log n) | O(n) | Simple but slow |
| Frequency Count | O(n) | O(1) | Best for lowercase English letters |
| HashMap (Unicode) | O(n) | O(k) | Handles Unicode |



--------------------------------------------------------------------------------------------------------------------------------------------------------------------
Two Sum
```text
Given an array of integers nums and an integer target. Return the indices(0 - indexed) of two elements in nums such that they add up to target.
Each input will have exactly one solution, and the same element cannot be used twice. Return the answer in non-decreasing order.
Example 1
Input: nums = [1, 6, 2, 10, 3], target = 7
Output: [0, 1]
Explanation: nums[0] + nums[1] = 1 + 6 = 7
```
```java
// brute force approach with TC of O(N*N)
/* Approach
Iterate in array from 0 to last index of the array (lets call this variable i).
Now, run another loop say(j) from i+1 to last index of the array.
If sum of arr[i] and arr[j] equals to target then return the i and j.
If no such indices are found then return -1 and -1.
 */

public int[] twoSum(int[] nums, int target) {
    int n = nums.length;
    //create ans array to store ans
    int[] ans = new int[2];
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
                /* if nums[i] + nums[j] is equal to 
                   target put i and j in ans */
            if (nums[i] + nums[j] == target) {
                ans[0] = i;
                ans[1] = j;
                return ans;
            }
        }
    }

    // Return {-1, -1} if no such pair is found
    return new int[]{-1, -1};
}
```

```java
// better idea with TC of O(n) and SC of O(n)
/*
 * The idea is to traverse the array and use a HashMap to check if for each element, 
 * an element in the HashMap exists, such that sum of both of the elements is equal to the target. 
 * This method trims down the search space and provides a better time complexity.
 * */

public int[] twoSum(int[] nums, int target) {
    // Map to store (element, index) pairs
    Map<Integer, Integer> mpp = new HashMap<>();
    // Size of the nums array
    int n = nums.length;
    for (int i = 0; i < n; i++) {
        // Current number in the array
        int num = nums[i];
        // Number needed to reach the target
        int moreNeeded = target - num;
        // Check if the complement exists in map
        if (mpp.containsKey(moreNeeded)) {
                /* Return the indices of the two
                numbers that sum up to target*/
            return new int[]{mpp.get(moreNeeded), i};
        }
        // Store current number and its index in map
        mpp.put(num, i);
    }
    // If no such pair found, return {-1, -1}
    return new int[]{-1, -1};
}
```
```java
// another solution using two pointers approach after sorting the array
// with this TC is O(n log n)
public int[] twoSum(int[] nums, int target) {
        // create a 2D array to store the elements and their original index
        int n = nums.length;
        int[] ans = new int[2];
        int[][] numberWithIndex = new int[n][2];
        for(int i=0; i<n; i++) {
            numberWithIndex[i][0] = nums[i];
            numberWithIndex[i][1] = i;
        }

        Arrays.sort(numberWithIndex, (a,b) -> Integer.compare(a[0], b[0]));

        int left = 0;
        int right = n-1;
        while(left < right) {
            int sum = numberWithIndex[left][0] + numberWithIndex[right][0];

            if(sum == target) {
                ans[0] = numberWithIndex[left][1];
                ans[1] = numberWithIndex[right][1];

                return ans;
            } else if(sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
Group Anagrams (leetcode 46 medium)
```text
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]

Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
Example 2:

Input: strs = [""]

Output: [[""]]

Example 3:

Input: strs = ["a"]

Output: [["a"]]
```

```text
Here’s the step-by-step approach:

Create a map to store lists of anagrams.
For each string in the input array, sort the string and use the sorted string as the key.
Append the original string to the list corresponding to that sorted key.
Return the values of the map as the result.

TC : O(N×KlogK).
if, 
N is the number of strings in the input array strs.
K is  the maximum length of a string in the array.

* sorting : For each string, we sort it, which takes O(KlogK) time where 𝐾 is the length of the string.
 Sorting all strings takes O(N×KlogK) time
* Inserting into map : n the worst case, inserting a string into the HashMap takes O(1) on average due to hash table operations. Over all 
N strings, this takes  O(N) time 
 
SC : O(N×K).
 
```

```java
 public List<List<String>> groupAnagrams(String[] strs) {
        // Create a map to group the anagrams, where the key is the sorted string, and the value is a list of original strings.
        Map<String, List<String>> anagramsMap = new HashMap<>();
        // Iterate over each string in the array.
        for (String str : strs) {
            // Convert the string to a character array and sort it.
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            // Create a new string from the sorted character array.
            String sortedStr = String.valueOf(charArray);
            // If the sorted string key is not present in the map, initialize the list.
            // Then add the original string to the list associated with the sorted string key.
            anagramsMap.computeIfAbsent(sortedStr, key -> new ArrayList<>()).add(str);
        }
        // Return a new list containing all values from the map's lists,
        // effectively grouping all anagrams together.
        return new ArrayList<>(anagramsMap.values());
    }
```

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Top K Frequent Elements

## Problem Statement
Given an integer array `nums` and an integer `k`, return the `k` most frequent elements. The answer can be in any order.

### Example 1:
**Input:**
```plaintext
nums = [1,1,1,2,2,3], k = 2
```
**Output:**
```plaintext
[1,2]
```

### Example 2:
**Input:**
```plaintext
nums = [1], k = 1
```
**Output:**
```plaintext
[1]
```

---

## Solution 1: Using Heap (Priority Queue)
### Approach
1. **Count Frequencies:** Use a `HashMap` to count occurrences of each element.
2. **Use Min-Heap:** Insert elements into a min-heap of size `k` using a priority queue.
3. **Extract Elements:** The heap will maintain the top `k` elements by frequency.
4. **Retrieve Output:** Extract elements from the heap.

### Java Implementation
```java
import java.util.*;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = 
            new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }
}
```

### Complexity Analysis
- **Counting Frequencies:** \(O(n)\)
- **Heap Operations:** \(O(n \log k)\) (inserting each element and maintaining heap size `k`)
- **Final Extraction:** \(O(k \log k)\)
- **Total Complexity:** \(O(n \log k)\)
- **Space Complexity:** \(O(n + k)\) (for frequency map and heap)

### Dry Run
#### Input
```plaintext
nums = [1,1,1,2,2,3], k = 2
```
#### Steps
1. Frequency count: `{1:3, 2:2, 3:1}`
2. Min-Heap operations:
    - Push `(3, 1)`
    - Push `(2, 2)`
    - Push `(1, 3)`, remove `(1, 3)` since heap exceeds size `k`
3. Heap now contains `{(2,2), (3,1)}`
4. Extract top elements: `[1,2]`

---

## Solution 2: Using Bucket Sort
### Approach
1. **Count Frequencies:** Use a `HashMap` to store occurrences.
2. **Bucket Array:** Create an array `bucket` where `bucket[i]` holds numbers that appear `i` times.
3. **Flatten Buckets:** Collect elements from highest frequency to lowest.

### Java Implementation
```java
import java.util.*;

public class TopKFrequentElementsBucketSort {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int key : freqMap.keySet()) {
            int frequency = freqMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && result.size() < k; i--) {
            if (bucket[i] != null) {
                result.addAll(bucket[i]);
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
}
```

### Complexity Analysis
- **Counting Frequencies:** \(O(n)\)
- **Bucket Fill:** \(O(n)\)
- **Extraction:** \(O(n)\)
- **Total Complexity:** \(O(n)\) (better than \(O(n \log k)\))
- **Space Complexity:** \(O(n)\) (for frequency map and bucket array)

### Dry Run
#### Input
```plaintext
nums = [1,1,1,2,2,3], k = 2
```
#### Steps
1. Frequency count: `{1:3, 2:2, 3:1}`
2. Create bucket: `[[], [3], [2], [1]]` (Index represents frequency)
3. Collect top `k`: `[1,2]`

---

## Conclusion
- **Heap Approach:** Works well when `k` is small but has \(O(n \log k)\) complexity.
- **Bucket Sort Approach:** More efficient with \(O(n)\) complexity but needs extra space.
- **Choosing Approach:** If `k` is small, heap works fine; otherwise, bucket sort is optimal.

Both approaches solve the problem efficiently, and the choice depends on constraints.


--------------------------------------------------------------------------------------------------------------------------------------------------------------------
# String Encoding and Decoding Algorithm Documentation

## Problem Description

This document explains how to implement an algorithm that encodes a list of strings into a single string and then decodes it back to the original list. This is a medium-difficulty problem that tests understanding of string manipulation and algorithm design.

### Problem Statement

Create two functions:
1. `encode`: Converts a list of strings into a single string
2. `decode`: Converts the encoded string back into the original list of strings

### Examples

```
Example 1:
Input: ["neet", "code", "love", "you"]
Output: ["neet", "code", "love", "you"]

Example 2:
Input: ["we", "say", ":", "yes"]
Output: ["we", "say", ":", "yes"]
```

### Constraints

- The input array length must be between 0 and 99 inclusive
- Each string in the array can have length between 0 and 199 inclusive
- Strings contain only UTF-8 characters

## Solution Approach

### Core Concept

The key to solving this problem lies in designing an encoding scheme that:
1. Preserves all information about the original strings
2. Handles special characters and empty strings
3. Allows for unambiguous decoding
4. Maintains efficiency in both time and space

### Implementation

Here's the complete Java implementation with detailed explanations:

```java
public class Codec {
    // We use '#' as a delimiter between the length prefix and the actual string
    private static final char DELIMITER = '#';
    
    // Encodes a list of strings to a single string
    public String encode(List<String> strs) {
        if (strs == null || strs.isEmpty()) {
            return "";
        }
        
        StringBuilder encoded = new StringBuilder();
        
        // For each string, we add its length, followed by the delimiter,
        // followed by the string itself
        for (String str : strs) {
            encoded.append(str.length())
                  .append(DELIMITER)
                  .append(str);
        }
        
        return encoded.toString();
    }
    
    // Decodes a single string back to the original list of strings
    public List<String> decode(String s) {
        if (s == null || s.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<String> decoded = new ArrayList<>();
        int i = 0;
        
        while (i < s.length()) {
            // Find where the length prefix ends
            int delimiterIndex = s.indexOf(DELIMITER, i);
            
            // Parse the length prefix
            int length = Integer.parseInt(s.substring(i, delimiterIndex));
            
            // Calculate where the actual string starts
            int stringStart = delimiterIndex + 1;
            
            // Extract the string using the length we found
            String str = s.substring(stringStart, stringStart + length);
            decoded.add(str);
            
            // Move to the start of the next length prefix
            i = stringStart + length;
        }
        
        return decoded;
    }
}
```

## Detailed Algorithm Explanation

### Encoding Process

The encoding function works by:
1. First checking for null or empty input
2. For each string in the input list:
    - Converting its length to a string
    - Adding the delimiter character ('#')
    - Appending the actual string
3. Combining all these elements into a single string

For example, encoding ["neet", "code"] would work like this:
1. "neet" → length = 4 → "4#neet"
2. "code" → length = 4 → "4#code"
3. Final result: "4#neet4#code"

### Decoding Process

The decoding function:
1. Checks for null or empty input
2. Iteratively:
    - Finds the next delimiter
    - Extracts and parses the length prefix
    - Uses the length to extract the exact string
    - Moves the pointer to the next length prefix
3. Returns the reconstructed list

For example, decoding "4#neet4#code":
1. First iteration:
    - Finds '#' at position 1
    - Extracts length 4
    - Extracts "neet"
    - Moves pointer to position 6
2. Second iteration:
    - Finds '#' at position 7
    - Extracts length 4
    - Extracts "code"
    - Completes decoding

## Complexity Analysis

### Time Complexity

- **Encode**: O(n)
    - Where n is the total length of all strings
    - Each character is processed exactly once

- **Decode**: O(n)
    - Where n is the length of the encoded string
    - Each character is processed at most once

### Space Complexity

- **Encode**: O(n)
    - Where n is the total length of all strings
    - The encoded string needs to store all original characters plus length prefixes and delimiters

- **Decode**: O(n)
    - Where n is the length of the encoded string
    - The decoded list needs to store all the original strings

## Design Considerations

### Why This Approach Works Well

1. **Robustness**: The solution handles all edge cases:
    - Empty strings
    - Strings with special characters
    - Strings containing numbers
    - Null input

2. **Efficiency**: The algorithm achieves:
    - Linear time complexity
    - Minimal space overhead
    - No complex data structures needed

3. **Simplicity**: The implementation is:
    - Easy to understand
    - Maintainable
    - Well-documented

### Alternative Approaches

While the current solution is optimal for most cases, alternative approaches could include:

1. **JSON Serialization**
    - Pros: Built-in support, handles all types of strings
    - Cons: More overhead, slower processing

2. **Base64 Encoding with Separators**
    - Pros: Standard encoding, handles binary data
    - Cons: Larger output size, more complex implementation

3. **Multiple Delimiter Scheme**
    - Pros: Could be more readable
    - Cons: More complex, potential issues with special characters

## Testing Considerations

When testing this implementation, consider these scenarios:

1. Edge Cases:
    - Empty list
    - Null input
    - Empty strings within the list
    - Single-character strings

2. Special Characters:
    - Strings containing numbers
    - Strings containing the delimiter character
    - Unicode characters
    - Whitespace characters

3. Performance Tests:
    - Large number of strings
    - Very long strings
    - Maximum constraint cases

## Conclusion

This solution provides an efficient and robust way to encode and decode lists of strings. It maintains O(n) time complexity while being simple to implement and maintain. The use of length prefixes eliminates ambiguity and allows for reliable decoding, making it suitable for production use.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Product of Array Except Self

## Problem Statement

Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all elements of `nums` except `nums[i]`. The algorithm must run in O(n) time without using division operations.

### Constraints
- Array length is between 2 and 10^5 (2 ≤ nums.length ≤ 10^5)
- Array elements are between -30 and 30 (-30 ≤ nums[i] ≤ 30)
- The result is guaranteed to fit in a 32-bit integer

## Approach

The solution uses a clever two-pass approach to build the result array:

1. First Pass (Left to Right):
   - Calculate the product of all elements to the left of each position
   - Store these products in the answer array
   - The first element has no left products, so it starts with 1

2. Second Pass (Right to Left):
   - Maintain a running product of elements from the right
   - Multiply each position in the answer array by the right running product
   - Update the right running product by multiplying it with the current number

input [1,2,3,4]

final ans should be [24,12,8,6]
first element in prefix array is 1 and last element in suffix array is 1 and keep multiplying in each order
prefix [1,1,2,6]
suffix [24,12,4,1]
prefix[i] * suffix[i]
final [24,12,8,6]

This approach allows us to get the product of all elements except the current one without using division and with minimal space complexity.

## Java Implementation

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        
        // First pass: Calculate products of all elements to the left
        answer[0] = 1;  // nothing to the left of first element
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i-1] * nums[i-1];
        }
        
        // Second pass: Multiply by products of all elements to the right
        int rightProduct = 1;
        for (int i = n-1; i >= 0; i--) {
            answer[i] = answer[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
        }
        
        return answer;
    }
}
```

## Detailed Dry Run

Let's walk through how the algorithm works with the example input: `nums = [1,2,3,4]`

### First Pass (Left to Right)
1. Initialize answer[0] = 1 (no left elements)
2. For i = 1: answer[1] = answer[0] * nums[0] = 1 * 1 = 1
3. For i = 2: answer[2] = answer[1] * nums[1] = 1 * 2 = 2
4. For i = 3: answer[3] = answer[2] * nums[2] = 2 * 3 = 6

After first pass: answer = [1, 1, 2, 6]

### Second Pass (Right to Left)
1. Initialize rightProduct = 1
2. For i = 3:
   - answer[3] = 6 * 1 = 6
   - rightProduct = 1 * 4 = 4
3. For i = 2:
   - answer[2] = 2 * 4 = 8
   - rightProduct = 4 * 3 = 12
4. For i = 1:
   - answer[1] = 1 * 12 = 12
   - rightProduct = 12 * 2 = 24
5. For i = 0:
   - answer[0] = 1 * 24 = 24
   - rightProduct = 24 * 1 = 24

Final result: [24, 12, 8, 6]

## Complexity Analysis

### Time Complexity: O(n)
- The algorithm makes exactly two passes through the array
- Each pass performs constant time operations
- Total operations = 2n = O(n)

### Space Complexity: O(1)
- Besides the output array (which doesn't count towards space complexity per problem requirements)
- We only use one additional variable (rightProduct)
- Therefore, extra space used is constant = O(1)

## Key Insights

1. The solution avoids division operations completely, meeting a key requirement
2. It efficiently handles cases with zeros in the input array
3. The two-pass approach allows us to get all necessary products without storing the entire prefix/suffix arrays
4. By using the output array to store intermediate results, we achieve O(1) extra space complexity

This problem demonstrates how creative array manipulation can help us achieve efficient solutions without using extra space or operations that might cause numerical issues.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------


Binary Search

# 981. Time Based Key-Value Store

## Problem Statement
Design a time-based key-value data structure that can store multiple values for the same key at different timestamps and retrieve the key's value at a certain timestamp.

### Implement the `TimeMap` class:
- `TimeMap()` Initializes the object of the data structure.
- `void set(String key, String value, int timestamp)` Stores the key `key` with the value `value` at the given `timestamp`.
- `String get(String key, int timestamp)` Returns a value such that `set` was called previously, with `timestamp_prev <= timestamp`. If there are multiple such values, it returns the value associated with the largest `timestamp_prev`. If there are no values, it returns `""`.

### Example:
```java
Input:
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]

Output:
[null, null, "bar", "bar", null, "bar2", "bar2"]
```

## Intuition
1. We need a **key-value store** where multiple values can exist for the same key at different timestamps.
2. **Binary Search** is ideal since timestamps are strictly increasing.
3. Using a **HashMap** (`Map<String, List<Pair<Integer, String>>>`):
    - **Key:** String.
    - **Value:** A list of timestamp-value pairs, keeping values sorted by timestamps.
4. **set():** Append values to the list.
5. **get():** Use **binary search** to find the largest timestamp `≤` the given timestamp.

## Java Logic
```java
import java.util.*;

class TimeMap {
    Map<String, List<Pair<Integer, String>>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Pair<>(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        List<Pair<Integer, String>> list = map.get(key);

        int left = 0, right = list.size() - 1;
        String result = "";

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).getKey() <= timestamp) {
                result = list.get(mid).getValue();
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    static class Pair<K, V> {
        private final K key;
        private final V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return this.key; }
        public V getValue() { return this.value; }
    }
}
```

## Dry Run
### Example Execution:
#### **Step 1:** `set("foo", "bar", 1)`
- Store: `{ "foo": [(1, "bar")] }`

#### **Step 2:** `get("foo", 1)`
- Binary search on `[(1, "bar")]`
- Returns **"bar"**.

#### **Step 3:** `get("foo", 3)`
- No entry at `3`, returns **"bar"** (last known value before `3`).

#### **Step 4:** `set("foo", "bar2", 4)`
- Store: `{ "foo": [(1, "bar"), (4, "bar2")] }`

#### **Step 5:** `get("foo", 4)`
- Binary search returns **"bar2"**.

#### **Step 6:** `get("foo", 5)`
- Returns **"bar2"**.

## Time and Space Complexity
### **Time Complexity:**
- `set()`: **O(1)** (Amortized, as `ArrayList.append()` is fast).
- `get()`: **O(log N)** (Binary search on timestamps).

### **Space Complexity:**
- **O(N)** (For storing `N` key-value pairs in HashMap).

## Summary
✅ **Used HashMap with timestamp-value pairs stored in an ArrayList.**
✅ **Binary search ensures efficient retrieval.**
✅ **Handles edge cases where no valid timestamp exists.**

## another Intuition

Think of this data structure like a historical record keeper. For each key (like a topic), we maintain a chronological history of values. When retrieving a value, we need to find the most recent "historical record" that exists at or before the requested time.

A helpful real-world analogy would be looking up the price of a product at a specific date in the past. If a product's price changed at timestamps 1, 3, and 5, and we want to know the price at timestamp 4, we should return the price that was set at timestamp 3, as that was the valid price at timestamp 4.

We need a data structure that can:
1. Efficiently store multiple values for the same key
2. Quickly find the most recent value before a given timestamp
3. Maintain the chronological order of values

A HashMap combined with a TreeMap provides an elegant solution to these requirements. The HashMap gives us fast access to each key's history, while the TreeMap efficiently maintains the chronological order of values and provides quick access to the most recent value before a given timestamp.

## Implementation

```java
class TimeMap {
    // Main storage: Key -> (Timestamp -> Value)
    private Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        // Initialize our main storage HashMap
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        // Create a new TreeMap for this key if it doesn't exist
        map.putIfAbsent(key, new TreeMap<>());
        
        // Store the value with its timestamp
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        // Return empty string if key doesn't exist
        if (!map.containsKey(key)) {
            return "";
        }

        // Get the TreeMap containing all timestamps for this key
        TreeMap<Integer, String> timeMap = map.get(key);
        
        // Find the largest timestamp less than or equal to the query timestamp
        Integer floorKey = timeMap.floorKey(timestamp);
        
        // Return empty string if no valid timestamp found
        if (floorKey == null) {
            return "";
        }
        
        // Return the value at the found timestamp
        return timeMap.get(floorKey);
    }
}
```

## Dry Run Example

Let's walk through the example from the problem statement:

```
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
```

1. `TimeMap timeMap = new TimeMap()`
    - Creates an empty HashMap

2. `timeMap.set("foo", "bar", 1)`
    - Creates new TreeMap for key "foo"
    - Adds mapping: 1 → "bar"
    - TreeMap state: {1: "bar"}

3. `timeMap.get("foo", 1)`
    - Finds floorKey(1) = 1
    - Returns "bar"

4. `timeMap.get("foo", 3)`
    - Finds floorKey(3) = 1
    - Returns "bar"

5. `timeMap.set("foo", "bar2", 4)`
    - Adds to existing TreeMap: 4 → "bar2"
    - TreeMap state: {1: "bar", 4: "bar2"}

6. `timeMap.get("foo", 4)`
    - Finds floorKey(4) = 4
    - Returns "bar2"

7. `timeMap.get("foo", 5)`
    - Finds floorKey(5) = 4
    - Returns "bar2"

## Complexity Analysis

### Time Complexity

- `set()`: O(log n)
    - Where n is the number of timestamps for the given key
    - This comes from TreeMap's put operation which maintains a balanced binary search tree

- `get()`: O(log n)
    - Where n is the number of timestamps for the given key
    - TreeMap's floorKey operation requires traversing the balanced binary search tree

### Space Complexity

- O(k * n)
    - Where k is the number of unique keys
    - And n is the average number of timestamps per key
    - Each key in the HashMap stores a TreeMap containing timestamp-value pairs

## Design Considerations

1. **Why TreeMap?**
    - TreeMap maintains sorted order of keys (timestamps)
    - Provides efficient floorKey() operation
    - Balanced binary search tree implementation ensures logarithmic time complexity

