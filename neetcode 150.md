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



Linked List
# Merge Two Sorted Lists

## Problem Statement
You are given the heads of two sorted linked lists `list1` and `list2`.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

### **Example 1**
**Input:**  
`list1 = [1,2,4]`  
`list2 = [1,3,4]`  
**Output:** `[1,1,2,3,4,4]`

### **Example 2**
**Input:**  
`list1 = []`  
`list2 = []`  
**Output:** `[]`

### **Example 3**
**Input:**  
`list1 = []`  
`list2 = [0]`  
**Output:** `[0]`

### **Constraints**
- The number of nodes in both lists is in the range `[0, 50]`.
- `-100 <= Node.val <= 100`
- Both `list1` and `list2` are sorted in non-decreasing order.

---

## **Logic**
We use a **Two Pointer approach** to merge the two sorted lists efficiently.
1. Create a dummy node (`dummy`) and a pointer (`current`) to track the merged list.
2. Traverse both lists using two pointers, `list1` and `list2`.
3. Compare values:
   - If `list1.val < list2.val`, attach `list1` to `current` and move `list1` forward.
   - Otherwise, attach `list2` to `current` and move `list2` forward.
4. After the loop, attach the remaining nodes from `list1` or `list2`.
5. Return `dummy.next` (skipping the dummy node).

---

## **Java Code**
### **Iterative Approach (Two Pointers)**
```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1); // Dummy node to simplify logic
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Attach remaining nodes
        if (list1 != null) current.next = list1;
        if (list2 != null) current.next = list2;

        return dummy.next;
    }
}
```

### **Complexity Analysis**
- **Time Complexity:** `O(N)`, where `N` is the total number of nodes in both lists.
- **Space Complexity:** `O(1)`, as we modify the lists in place.

---

## **Dry Run**
### **Example Input**
`list1 = [1,2,4]`, `list2 = [1,3,4]`

### **Step-by-Step Execution**
| `list1` | `list2` | `current` (Merged List) |
|---------|---------|------------------|
| `1 → 2 → 4` | `1 → 3 → 4` | `dummy →` |
| `2 → 4` | `1 → 3 → 4` | `dummy → 1 →` |
| `2 → 4` | `3 → 4` | `dummy → 1 → 1 →` |
| `4` | `3 → 4` | `dummy → 1 → 1 → 2 →` |
| `4` | `4` | `dummy → 1 → 1 → 2 → 3 →` |
| `null` | `4` | `dummy → 1 → 1 → 2 → 3 → 4 →` |
| `null` | `null` | `dummy → 1 → 1 → 2 → 3 → 4 → 4` |

### **Final Output:** `[1,1,2,3,4,4]` ✅

---

### **Alternative Approach: Recursion**
```java
public class MergeTwoSortedListsRecursive {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
```

### **Complexity Analysis (Recursive Approach)**
- **Time Complexity:** `O(N)`, where `N` is the total number of nodes.
- **Space Complexity:** `O(N)`, due to recursive function calls.

---


1. Linked List Cycle (leetcode 141)
```text
Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.
```

```text
Brute Force approach is to use use an extra ds like list or set to check if the visited node is already present in the list, if yes its a cycle
TC O(N)
SC O(N)  
```
![brute-detect-cycle.png](..%2F..%2F..%2F..%2F.gitbook%2Fassets%2Fbrute-detect-cycle.png)

```java
public boolean hasCycle1(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        if(head==null)
            return false;

        ListNode temp = head;
        while(temp!=null){
            if(list.contains(temp)){
                return true;
            }
            list.add(temp);
            temp = temp.next;
        }
        return false;
    }
```

```text
Optimal approach is to use Floyd Warshals Algo of fast and slow pointer
if fast and slow pointer meets at some point then there is a cycle
TC: O(n), where n is the number of nodes in the Linked List.
SC: O(1). 
```

![tortoise-hare.png](..%2F..%2F..%2F..%2F.gitbook%2Fassets%2Ftortoise-hare.png)


```java
 public boolean hasCycle(ListNode head) {
        if(head == null)
            return false;
        ListNode tortoise = head;
        ListNode hare = head;
        while(tortoise!=null && hare!=null && hare.next!=null ){
            tortoise = tortoise.next;
            hare = hare.next.next;
            if(tortoise == hare ) return true;
        }
        return false;
    }
```

# Valid Sudoku

## Problem Statement
Determine if a **9 x 9** Sudoku board is **valid**. Only the **filled cells** need to be validated according to the following rules:

1. Each **row** must contain the digits **1-9** without repetition.
2. Each **column** must contain the digits **1-9** without repetition.
3. Each of the nine **3×3 sub-boxes** of the grid must contain the digits **1-9** without repetition.

**Notes:**
- A Sudoku board (partially filled) **could be valid** but is not necessarily solvable.
- Only the **filled cells** need to be validated according to the rules.

### Example 1
#### **Input:**
```plaintext
board =
[['5','3','.','.','7','.','.','.','.']
,['6','.','.','1','9','5','.','.','.']
,['.','9','8','.','.','.','.','6','.']
,['8','.','.','.','6','.','.','.','3']
,['4','.','.','8','.','3','.','.','1']
,['7','.','.','.','2','.','.','.','6']
,['.','6','.','.','.','.','2','8','.']
,['.','.','.','4','1','9','.','.','5']
,['.','.','.','.','8','.','.','7','9']]
```
#### **Output:**
```plaintext
true
```

### Example 2
#### **Input:**
```plaintext
board =
[['8','3','.','.','7','.','.','.','.']
,['6','.','.','1','9','5','.','.','.']
,['.','9','8','.','.','.','.','6','.']
,['8','.','.','.','6','.','.','.','3']
,['4','.','.','8','.','3','.','.','1']
,['7','.','.','.','2','.','.','.','6']
,['.','6','.','.','.','.','2','8','.']
,['.','.','.','4','1','9','.','.','5']
,['.','.','.','.','8','.','.','7','9']]
```
#### **Output:**
```plaintext
false
```
#### **Explanation:**
- The number **'8'** appears **twice** in the **top-left 3×3 sub-box**, making the board invalid.

---

## Intuition
To efficiently check the Sudoku board, we need to validate three conditions **simultaneously**:
1. Each row should contain unique numbers.
2. Each column should contain unique numbers.
3. Each **3×3 box** should contain unique numbers.

### **Tracking Mechanism**
We use a **HashSet** to track numbers seen in:
- Each **row**
- Each **column**
- Each **3×3 sub-box** (indexed using `(i / 3) + "-" + (j / 3)`).

If any number is **repeated**, the board is **invalid**.

---

## Java Implementation
```java
import java.util.HashSet;

public class ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        HashSet<String> seen = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    if (!seen.add(num + " in row " + i) ||
                        !seen.add(num + " in col " + j) ||
                        !seen.add(num + " in box " + (i / 3) + "-" + (j / 3))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board1 = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        
        char[][] board2 = {
            {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        
        System.out.println(isValidSudoku(board1)); // Output: true
        System.out.println(isValidSudoku(board2)); // Output: false
    }
}
```
Understanding (i / 3) + "-" + (j / 3)
Each 3×3 sub-box in a Sudoku board can be identified using integer division:
Row Index (i / 3): Determines which group of 3 rows the cell belongs to.
Column Index (j / 3): Determines which group of 3 columns the cell belongs to.
Together, (i / 3) + "-" + (j / 3) creates a unique identifier for each of the nine 3×3 sub-boxes.
---

j →  0       1     2   |   3      4    5   |   6      7     8  
i  -----------------------------------------------------------
0  | (0,0) (0,0) (0,0) | (0,1) (0,1) (0,1) | (0,2) (0,2) (0,2)
1  | (0,0) (0,0) (0,0) | (0,1) (0,1) (0,1) | (0,2) (0,2) (0,2)
2  | (0,0) (0,0) (0,0) | (0,1) (0,1) (0,1) | (0,2) (0,2) (0,2)
   -----------------------------------------------------------
3  | (1,0) (1,0) (1,0) | (1,1) (1,1) (1,1) | (1,2) (1,2) (1,2)
4  | (1,0) (1,0) (1,0) | (1,1) (1,1) (1,1) | (1,2) (1,2) (1,2)
5  | (1,0) (1,0) (1,0) | (1,1) (1,1) (1,1) | (1,2) (1,2) (1,2)
   -----------------------------------------------------------
6  | (2,0) (2,0) (2,0) | (2,1) (2,1) (2,1) | (2,2) (2,2) (2,2)
7  | (2,0) (2,0) (2,0) | (2,1) (2,1) (2,1) | (2,2) (2,2) (2,2)
8  | (2,0) (2,0) (2,0) | (2,1) (2,1) (2,1) | (2,2) (2,2) (2,2)


## Dry Run Example

| i | j | num  | Row Check | Column Check | Box Check | Result |
|---|---|------|-----------|--------------|-----------|--------|
| 0 | 0 | '5'  | ✅ Unique  | ✅ Unique     | ✅ Unique  | ✅      |
| 0 | 1 | '3'  | ✅ Unique  | ✅ Unique     | ✅ Unique  | ✅      |
| 1 | 0 | '6'  | ✅ Unique  | ✅ Unique     | ✅ Unique  | ✅      |
| 4 | 4 | '8'  | ✅ Unique  | ✅ Unique     | ✅ Unique  | ✅      |
| 8 | 8 | '9'  | ✅ Unique  | ✅ Unique     | ✅ Unique  | ✅      |
| 0 | 0 | '8' (duplicate) | ❌ Not Unique | ✅ Unique | ❌ Not Unique | ❌ Invalid |

---

## Complexity Analysis
| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Checking Rows | **O(1)** | **O(1)** |
| Checking Columns | **O(1)** | **O(1)** |
| Checking Boxes | **O(1)** | **O(1)** |
| **Overall Complexity** | **O(1)** | **O(1)** |

### **Why O(1)?**
- The board size is always **9×9** (**fixed size**), meaning the algorithm runs in **constant time and space**.

---
### 🚀 This solution efficiently checks **rows, columns, and 3×3 sub-boxes** using **one pass** with a **HashSet**.

# Longest Consecutive Sequence
**Medium**

**Given an array of integers **`nums`, return *the length* of the longest consecutive sequence of elements that can be formed.

**A *consecutive sequence* is a sequence of elements in which each element is exactly **`1` greater than the previous element. The elements do *not* have to be consecutive in the original array.

**You must write an algorithm that runs in **`O(n)` time.

**Example 1:**

```java
Input: nums = [2,20,4,10,3,4,5]

Output: 4
```

**Explanation: The longest consecutive sequence is **`[2, 3, 4, 5]`.

**Example 2:**

```java
Input: nums = [0,3,2,5,4,6,1,1]

Output: 7
```

**Constraints:**
* `0 <= nums.length <= 1000`
* `-10^9 <= nums[i] <= 10^9`

## Intuition

The key insight is that we need to find the longest chain of consecutive numbers, but these numbers don't need to be consecutive in the original array. Since we need an O(n) solution, sorting (which would be O(n log n)) isn't optimal.

Instead, we can use a HashSet to quickly check if a number exists. Then for each number, we can check if it's the start of a sequence by verifying if its predecessor exists in the set. If not, we can count how many consecutive numbers follow it.

## Java Approach

```java
public class Solution {
    public int longestConsecutive(int[] nums) {
        // Handle empty array case
        if (nums.length == 0) {
            return 0;
        }
        
        // Add all numbers to a HashSet for O(1) lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int longestStreak = 0;
        
        // Iterate through each number in the array
        for (int num : numSet) {
            // Only check for sequences starting with numbers that have no predecessor
            // This ensures O(n) time complexity
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                
                // Count consecutive numbers
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                
                // Update longest streak if current is longer
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        
        return longestStreak;
    }
}
```

## Dry Run

Let's walk through Example 1: `nums = [2,20,4,10,3,4,5]`

1. We add all numbers to a HashSet: {2, 20, 4, 10, 3, 5}
2. We iterate through each number in the set:
   - 2: No predecessor (1 not in set), so we count: [2, 3, 4, 5] = 4
   - 20: No predecessor (19 not in set), so we count: [20] = 1
   - 4: Has predecessor (3 in set), skip
   - 10: No predecessor (9 not in set), so we count: [10] = 1
   - 3: Has predecessor (2 in set), skip
   - 5: Has predecessor (4 in set), skip
3. The longest sequence is 4 (from checking the sequence starting with 2)

For Example 2: `nums = [0,3,2,5,4,6,1,1]`
We would find a consecutive sequence of [0, 1, 2, 3, 4, 5, 6] with length 7.

## Time & Space Complexity

- **Time Complexity**: O(n)
   - Adding all numbers to the HashSet is O(n)
   - For each number, we only perform the sequence counting if it's the start of a sequence
   - Each number is only visited once when counting consecutive numbers
   - In the worst case, we might visit each number twice (once during HashSet iteration and once during consecutive counting)

- **Space Complexity**: O(n)
   - We store all numbers in a HashSet, which in the worst case takes O(n) space

The key optimization is checking if a number is the start of a sequence by confirming its predecessor doesn't exist, which ensures we don't redundantly count sequences and keeps the time complexity at O(n).

# Valid Palindrome

## Intuition
A palindrome is a sequence of characters that reads the same forward and backward. However, in this problem, we need to consider only alphanumeric characters and ignore case sensitivity. The best way to approach this problem efficiently is by using the **two-pointer technique**, which allows us to check characters from both ends and move inward until we determine whether the string is a palindrome.

## Java Logic
To solve this problem in Java, we:
1. Use two pointers: `left` starting from the beginning and `right` starting from the end.
2. Ignore non-alphanumeric characters using `Character.isLetterOrDigit()`.
3. Convert characters to lowercase using `Character.toLowerCase()` for case insensitivity.
4. Compare the characters at the `left` and `right` pointers.
5. If a mismatch is found, return `false`.
6. If all valid characters match, return `true`.

### Java Code Implementation
```java
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);

            if (!Character.isLetterOrDigit(l)) {
                left++;
            } else if (!Character.isLetterOrDigit(r)) {
                right--;
            } else if (Character.toLowerCase(l) != Character.toLowerCase(r)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}
```

## Dry Run
### Example 1:
#### Input: `s = "Was it a car or a cat I saw?"`
#### Processing:
1. Convert to alphanumeric only: "wasitacaroracatisaw"
2. `left = 0, right = 18` → 'w' == 'w' ✅ (move inwards)
3. `left = 1, right = 17` → 'a' == 'a' ✅
4. `left = 2, right = 16` → 's' == 's' ✅
5. Continue until `left >= right`.
#### Output: `true`

### Example 2:
#### Input: `s = "tab a cat"`
#### Processing:
1. Convert to alphanumeric only: "tabacat"
2. `left = 0, right = 6` → 't' == 't' ✅
3. `left = 1, right = 5` → 'a' == 'a' ✅
4. `left = 2, right = 4` → 'b' != 'c' ❌
#### Output: `false`

## Complexity Analysis
- **Time Complexity:** O(N) → We iterate through the string once, processing each character at most twice (once for checking and once for comparison).
- **Space Complexity:** O(1) → No extra space is used apart from a few variables.

This approach ensures an efficient solution for checking valid palindromes even for large input sizes.

# Three Sum Problem

## Problem Statement
Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` where:
- `nums[i] + nums[j] + nums[k] == 0`
- `i, j, and k` are distinct indices.
- The output should not contain any duplicate triplets.

The triplets can be returned in any order.

### Examples
#### Example 1:
**Input:** `nums = [-1,0,1,2,-1,-4]`

**Output:** `[[-1,-1,2],[-1,0,1]]`

**Explanation:**
- `nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0`
- `nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0`
- `nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0`
- The distinct triplets are `[-1,-1,2]` and `[-1,0,1]`

#### Example 2:
**Input:** `nums = [0,1,1]`

**Output:** `[]`

**Explanation:** The only possible triplet does not sum up to 0.

#### Example 3:
**Input:** `nums = [0,0,0]`

**Output:** `[[0,0,0]]`

**Explanation:** The only possible triplet sums up to 0.

### Constraints
- `3 <= nums.length <= 1000`
- `-10^5 <= nums[i] <= 10^5`

---

## Approach
### Brute Force (O(n^3))
- Use three nested loops to find all triplets `(i, j, k)`.
- Check if `nums[i] + nums[j] + nums[k] == 0`.
- Store unique triplets in a set to avoid duplicates.

### Optimized Approach (Two Pointers, O(n^2))
1. **Sort the array** to simplify duplicate handling.
2. **Fix one element** and use two pointers to find pairs that sum up to the negative of the fixed element.
3. **Use two-pointer technique** to move left and right pointers based on the sum.
4. **Avoid duplicates** by skipping duplicate elements.

---

## Java Solution
```java
import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // Step 1: Sort the array
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates
            
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++; // Skip duplicates
                    while (left < right && nums[right] == nums[right + 1]) right--; // Skip duplicates
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
```

---

## Dry Run
### Input: `nums = [-1, 0, 1, 2, -1, -4]`
#### Sorted: `[-4, -1, -1, 0, 1, 2]`

| i  | left | right | nums[i] | nums[left] | nums[right] | sum  | Action       |
|----|------|-------|---------|------------|-------------|------|-------------|
| 0  | 1    | 5     | -4      | -1         | 2           | -3   | left++       |
| 0  | 2    | 5     | -4      | -1         | 2           | -3   | left++       |
| 0  | 3    | 5     | -4      | 0          | 2           | -2   | left++       |
| ...| ...  | ...   | ...     | ...        | ...         | ...  | ...         |
| 1  | 2    | 5     | -1      | -1         | 2           | 0    | Add triplet  |
| 1  | 3    | 4     | -1      | 0          | 1           | 0    | Add triplet  |

Final result: `[[-1,-1,2],[-1,0,1]]`

---

## Time & Space Complexity
- **Sorting the array**: `O(n log n)`
- **Two-pointer traversal for each element**: `O(n^2)`
- **Overall Complexity**: `O(n^2)`
- **Space Complexity**: `O(1)`, ignoring the output list.

---

## Summary
- **Brute Force:** `O(n^3)`, generates all triplets, slow.
- **Optimized (Two Pointers):** `O(n^2)`, uses sorting and two-pointer technique.
- **Edge Cases:** Handles duplicates efficiently, considers arrays with all zeroes.

This approach provides an efficient way to solve the problem while maintaining uniqueness in results.

# Container With Most Water

## Problem Statement
You are given an integer array `heights` of length `n` where each element represents the height of a vertical line drawn at that index. The width between two indices is `1`. Find two lines that together with the x-axis form a container, such that the container holds the most water.

Return the **maximum amount of water** a container can store.

**Example:**

```
Input: heights = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The container formed by heights[1] (8) and heights[8] (7) gives max area (7 * 7 = 49).
```

## Intuition
- The key idea is that **the area is determined by the smaller of the two heights and the distance between them**.
- If we fix two pointers at the leftmost and rightmost ends, the **width is maximized initially**.
- Since **reducing width decreases area**, we must always move the pointer pointing to the **shorter line** to maximize the height, potentially increasing the area.
- We use a **two-pointer approach** to check all possible containers efficiently.

## Java Logic
```java
class Solution {
    public int maxArea(int[] heights) {
        int maxArea = 0; // Area cannot be negative
        int left = 0;
        int right = heights.length - 1;

        while (left < right) {
            int area = Math.min(heights[left], heights[right]) * (right - left);
            maxArea = Math.max(maxArea, area);

            if (heights[left] <= heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
```

## Dry Run
### Input: `heights = [1,8,6,2,5,4,8,3,7]`

| Left | Right | heights[left] | heights[right] | Area | MaxArea |
|------|-------|--------------|---------------|------|---------|
| 0    | 8     | 1            | 7             | 8    | 8       |
| 1    | 8     | 8            | 7             | 49   | 49      |
| 1    | 7     | 8            | 3             | 18   | 49      |
| 1    | 6     | 8            | 8             | 40   | 49      |
| 2    | 6     | 6            | 8             | 24   | 49      |
| 3    | 6     | 2            | 8             | 6    | 49      |
| 4    | 6     | 5            | 8             | 10   | 49      |
| 5    | 6     | 4            | 8             | 4    | 49      |
| 6    | 6     | 8            | 8             | -    | 49      |

## Time and Space Complexity
- **Time Complexity:** `O(n)`, since we traverse the array only once using two pointers.
- **Space Complexity:** `O(1)`, since we use only a few extra variables.

### Summary
This approach efficiently finds the maximum water that can be stored using the **two-pointer technique** and has an optimal `O(n)` time complexity. 🚀

# Trapping Rain Water

## Problem Statement

Given an array of non-negative integers `height` where each value represents the height of a bar with width 1, return the maximum amount of water that can be trapped between the bars after raining.

### Example
```
Input: height = [0,2,0,3,1,0,1,3,2,1]
Output: 9
```

### Constraints
* `1 <= height.length <= 1000`
* `0 <= height[i] <= 1000`

## Approach 1: Using Pre-computed Arrays

The key insight for this problem is to recognize that the amount of water that can be trapped at any position depends on the minimum of the maximum heights to the left and right of that position, minus the height at that position.

For each position i:
1. Find the maximum height to the left of i (leftMax[i])
2. Find the maximum height to the right of i (rightMax[i])
3. The water that can be trapped at position i = min(leftMax[i], rightMax[i]) - height[i]
4. Sum up the water trapped at each position to get the total

### Java Implementation

```java
public class TrappingRainWater {
    public static int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        
        int n = height.length;
        int totalWater = 0;
        
        // For each position, we need to find the maximum height to its left and right
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        
        // Fill leftMax array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        
        // Fill rightMax array
        rightMax[n-1] = height[n-1];
        for (int i = n-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }
        
        // Calculate water trapped at each position
        for (int i = 0; i < n; i++) {
            // The water level is determined by the minimum of the highest bars on both sides
            // Water trapped = min(leftMax, rightMax) - current height
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            totalWater += waterLevel - height[i];
        }
        
        return totalWater;
    }
}
```

### Dry Run

For the input `[0,2,0,3,1,0,1,3,2,1]`:

| Index | Height | leftMax | rightMax | min(leftMax, rightMax) | Water Trapped |
|-------|--------|---------|----------|------------------------|---------------|
| 0     | 0      | 0       | 3        | 0                      | 0             |
| 1     | 2      | 2       | 3        | 2                      | 0             |
| 2     | 0      | 2       | 3        | 2                      | 2             |
| 3     | 3      | 3       | 3        | 3                      | 0             |
| 4     | 1      | 3       | 3        | 3                      | 2             |
| 5     | 0      | 3       | 3        | 3                      | 3             |
| 6     | 1      | 3       | 3        | 3                      | 2             |
| 7     | 3      | 3       | 3        | 3                      | 0             |
| 8     | 2      | 3       | 2        | 2                      | 0             |
| 9     | 1      | 3       | 1        | 1                      | 0             |

Total water trapped = 0 + 0 + 2 + 0 + 2 + 3 + 2 + 0 + 0 + 0 = 9

### Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the height array. We traverse the array three times.
- **Space Complexity**: O(n) for the two additional arrays (leftMax and rightMax).

## Approach 2: Two Pointer Technique

A more efficient approach uses a two-pointer technique to eliminate the need for pre-computing left and right maximum arrays, reducing the space complexity to O(1).

### Key Insight
- Use two pointers, left and right, starting from the beginning and end of the array.
- Maintain two variables, leftMax and rightMax, to track the maximum heights seen from left and right sides.
- At each step, determine which side has a smaller boundary (leftMax or rightMax).
- Calculate water trapped at that position and move that pointer inward.

### Java Implementation

```java
public class TrappingRainWaterTwoPointer {
    public static int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int totalWater = 0;
        
        while (left < right) {
            // Update the maximum heights seen so far from left and right
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            
            // If the left bar is lower than the right bar
            if (leftMax < rightMax) {
                // We know that water level at left is determined by leftMax
                // Water trapped at this position = leftMax - height[left]
                totalWater += leftMax - height[left];
                left++; // Move left pointer inward
            } 
            // If the right bar is lower than or equal to the left bar
            else {
                // We know that water level at right is determined by rightMax
                // Water trapped at this position = rightMax - height[right]
                totalWater += rightMax - height[right];
                right--; // Move right pointer inward
            }
        }
        
        return totalWater;
    }
}
```

### Explanation of Two Pointer Approach

The two-pointer approach relies on an important property: if there is a higher bar on the right side (rightMax > leftMax), then the water level at the left position is determined solely by leftMax. Similarly, if there is a higher bar on the left side (leftMax ≥ rightMax), then the water level at the right position is determined solely by rightMax.

1. Initialize two pointers `left = 0` and `right = height.length - 1`
2. Initialize `leftMax = height[left]` and `rightMax = height[right]`
3. While `left < right`:
   - Update `leftMax` and `rightMax` if necessary
   - If `leftMax < rightMax`, we know the water level at position `left` is determined by `leftMax`:
      - Add `leftMax - height[left]` to the total water
      - Move `left` pointer inward
   - Otherwise, we know the water level at position `right` is determined by `rightMax`:
      - Add `rightMax - height[right]` to the total water
      - Move `right` pointer inward

### Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the array. We only traverse the array once.
- **Space Complexity**: O(1) as we only use a constant amount of extra space regardless of input size.


## Problem Statement: Best Time to Buy and Sell Stock

You are given an array `prices` where `prices[i]` represents the price of a given stock on the **i**-th day.

You want to maximize your profit by choosing a **single day** to **buy one stock** and choosing a **different day** in the future to **sell that stock**.

Return *the maximum profit you can achieve from this transaction*. If you cannot achieve any profit, return `0`.

### Example 1
```
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: 
Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5.
```

### Example 2
```
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: 
No transactions are done since prices are always decreasing.
```

---

## Approach 1: Brute Force
- **Idea:** Check all possible pairs `(i, j)` where `i < j` to find the maximum profit.
- **Algorithm:**
   - Iterate over all days using two nested loops.
   - For every pair of days, calculate `profit = prices[j] - prices[i]`.
   - Keep track of the maximum profit.
- **Time Complexity:** \( O(n^2) \)
- **Space Complexity:** \( O(1) \)

**Java Code:**
```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
}
```

---

## Approach 2: One Pass (Optimal Solution)
- **Idea:** Track the **minimum price** so far and calculate the potential profit on each day.
- **Algorithm:**
   1. Initialize `minPrice = Integer.MAX_VALUE` and `maxProfit = 0`.
   2. Traverse the array:
      - Update `minPrice` to the current price if it's lower.
      - Calculate the profit: `profit = prices[i] - minPrice`.
      - Update `maxProfit` if `profit > maxProfit`.
- **Time Complexity:** \( O(n) \)
- **Space Complexity:** \( O(1) \)

**Java Code:**
```java
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price; // Update min price
            } else {
                int profit = price - minPrice;
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        
        return maxProfit;
    }
}
```

---

## Approach 3: Using Kadane’s Algorithm
- **Idea:** Similar to finding the maximum subarray sum, consider the profit on each day as a continuous subarray.
- Track the difference between days using:
   - `CurrentProfit = prices[i] - prices[i-1]`
   - Track `maxProfit` using Kadane’s algorithm.
- **Time Complexity:** \( O(n) \)
- **Space Complexity:** \( O(1) \)

**Java Code:**
```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int currentProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            currentProfit += prices[i] - prices[i - 1];
            currentProfit = Math.max(0, currentProfit);
            maxProfit = Math.max(maxProfit, currentProfit);
        }
        
        return maxProfit;
    }
}
```

---

## Dry Run

**Input:** prices = [7, 1, 5, 3, 6, 4]

| Day | Price | Min Price | Profit | Max Profit |
|------|--------|------------|--------|------------|
| 1    | 7      | 7          | 0      | 0          |
| 2    | 1      | 1          | 0      | 0          |
| 3    | 5      | 1          | 4      | 4          |
| 4    | 3      | 1          | 2      | 4          |
| 5    | 6      | 1          | 5      | 5          |
| 6    | 4      | 1          | 3      | 5          |

**Output:** 5

