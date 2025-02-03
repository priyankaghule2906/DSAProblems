# String related Problems

1. Group Anagrams (leetcode 46 medium)

2. Longest Substring Without Repeating Characters:
   Find the length of the longest substring without repeating characters in a given string.
   https://leetcode.com/problems/longest-substring-without-repeating-characters/

3. Minimum Window Substring:
Given a string S and a string T, find the minimum window in S that contains all the characters of T.
https://leetcode.com/problems/minimum-window-substring/
4. Regular Expression Matching:
Implement regular expression matching with support for '.' and '*'.
https://leetcode.com/problems/regular-expression-matching/
5. Edit Distance:
Determine the minimum number of operations required to convert one string into another, where operations include insert, delete, and replace.
https://leetcode.com/problems/edit-distance/
6. String to Integer (atoi):
Implement the atoi function which converts a string to an integer.
https://leetcode.com/problems/string-to-integer-atoi/
7. Group Anagrams:
Given an array of strings, group anagrams together.
https://leetcode.com/problems/group-anagrams/
8. Longest Palindromic Substring:
Find the longest palindromic substring in a given string.
https://leetcode.com/problems/longest-palindromic-substring/
9. ZigZag Conversion:
Convert a given string into a zigzag pattern with a specified number of rows.
https://leetcode.com/problems/zigzag-conversion/
10. Valid Parentheses:
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
https://leetcode.com/problems/valid-parentheses/

11. String Compression:
Implement a method to perform basic string compression using the counts of repeated characters. For example, the string "aabcccccaaa" would become "a2b1c5a3".
https://leetcode.com/problems/string-compression/




1. Group Anagrams (leetcode 46 medium)
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

2. Longest Substring Without Repeating Characters:
```text
Given a string s, find the length of the longest substring without repeating characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
```
```text
Approach with TC O(n)  and SC (1)
1. use two pointers (start and end) to represent the current window of non-repeating characters.
2. Use a HashSet or some boolean array to keep track of the characters in the current window.
3. Move the end pointer to expand the window, and add each character to the HashSet.
4. If a duplicate character is found, remove characters from the start until the duplicate is removed.
5. Keep track of the maximum length of the substring seen so far.
```

```java
    public int lengthOfLongestSubstring(String str) {
       int max = 0;
       boolean[] hash = new boolean[256];
       int n = str.length();
       int start =0;
       int end =0;
       while (end < n){

           if(!hash[str.charAt(end)]){
               hash[str.charAt(end)] = true;
               max = Math.max(max, end-start+1);
               end++;
           } else {
               hash[str.charAt(start)] = false;
               start++;
           }
       }
       return max;
    }
```

# Valid Anagram Solution

## Problem Description

Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

### Example 1:

**Input:**
```java
s = "anagram", t = "nagaram"
```

**Output:**
```java
true
```

### Example 2:

**Input:**
```java
s = "rat", t = "car"
```

**Output:**
```java
false
```

### Constraints:
- `1 <= s.length, t.length <= 5 * 10^4`
- `s` and `t` consist of lowercase English letters.

### Follow-up:
What if the inputs contain Unicode characters? How would you adapt your solution to such a case?

---

## Java Solution

```java
import java.util.HashMap;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false; // If lengths are not equal, they cannot be anagrams
        }
        
        // Use a HashMap to count character frequencies in `s`
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Decrement counts using characters in `t`
        for (char c : t.toCharArray()) {
            if (!charCount.containsKey(c) || charCount.get(c) == 0) {
                return false; // Character is either not in `s` or used up
            }
            charCount.put(c, charCount.get(c) - 1);
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();
        System.out.println(solution.isAnagram("anagram", "nagaram")); // Output: true
        System.out.println(solution.isAnagram("rat", "car"));         // Output: false
    }
}
```

---

## Explanation
1. **Length Check:** If the lengths of `s` and `t` are different, return `false` immediately.
2. **Character Count:** Use a `HashMap` to store the frequency of each character in `s`.
3. **Validation Against `t`:** For each character in `t`, decrement its frequency in the map. If a character is missing or its count is zero, return `false`.
4. **Result:** If all characters are matched correctly, return `true`.

---

## Unicode Adaptation

If the inputs include Unicode characters:
1. You can use the same `HashMap` approach since Java's `char` and `String` support Unicode natively.
2. Alternatively, you can use `int[]` arrays of size `65536` (the range of Unicode characters) if you want to optimize space and avoid the overhead of `HashMap`.

---

## Unicode Adaptation

If the inputs include Unicode characters:

### Alternative Solution Using `int[]` Arrays
You can use an `int[]` array of size `65536` (the range of Unicode characters) to store character frequencies. This avoids the overhead of a `HashMap`.

```java
public class ValidAnagramUnicode {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false; // If lengths are not equal, they cannot be anagrams
        }

        // Use an int array to count character frequencies
        int[] charCount = new int[65536];

        // Increment counts for characters in `s`
        for (char c : s.toCharArray()) {
            charCount[c]++;
        }

        // Decrement counts for characters in `t`
        for (char c : t.toCharArray()) {
            charCount[c]--;
            if (charCount[c] < 0) {
                return false; // If count goes negative, `t` has extra characters
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidAnagramUnicode solution = new ValidAnagramUnicode();
        System.out.println(solution.isAnagram("anagram", "nagaram")); // Output: true
        System.out.println(solution.isAnagram("rat", "car"));         // Output: false
    }
}
```

---

# Longest Repeating Character Replacement

## Problem

Given an integer `k` and a string `s`, any character in the string can be selected and changed to any other uppercase English character. This operation can be performed up to `k` times. After completing these steps, return the length of the longest substring that contains the same letter.

### Examples

#### Example 1:

Input:
```text
s = "BAABAABBBAAA", k = 2
```

Output:
```text
6
```

Explanation:
- We can change the `B` present at index `0` and `3` (0-based indexing) to `A`.
- The new string becomes: `"AAAAAABBBAAA"`.
- The substring `"AAAAAA"` is the longest substring having the same letter with length `6`.

#### Example 2:

Input:
```text
s = "AABABBA", k = 1
```

Output:
```text
4
```

Explanation:
- The underlined characters are changed in the new string obtained.
- The new string becomes: `"AABBBBA"`.
- The substring `"BBBB"` is the answer. There are other ways to achieve this answer.

---

## Intuition

1. **Goal**: Find the longest substring where, after replacing at most `k` characters, all characters in the substring are the same.

2. **Key Idea**:
   - Use the sliding window approach to expand and shrink a window over the string to find the longest valid substring.
   - A valid substring means that the number of characters that need to be replaced (`window length - frequency of most frequent character`) is less than or equal to `k`.

3. **Reasoning**:
   - The substring with the same character after replacing at most `k` characters will be constrained by:
      - The number of allowed replacements (`k`).
      - The frequency of the most frequent character in the current window.

4. **Steps**:
   - Use a sliding window (two pointers) to iterate through the string.
   - Maintain a frequency map of characters in the current window.
   - Check if the window is valid by ensuring that the number of characters that need to be replaced (`window length - max frequency`) is ≤ `k`.
   - If valid, update the maximum length of the substring.
   - If invalid, shrink the window from the left.

---

## Algorithm

1. Initialize:
   - `max_length` to store the maximum length of a valid substring.
   - `left` as the left pointer of the sliding window.
   - `freq` (dictionary) to store the frequency of characters in the current window.
   - `max_freq` to store the maximum frequency of any character in the current window.

2. Iterate through the string using the `right` pointer:
   - Add the character at `right` to the frequency map.
   - Update `max_freq` with the maximum frequency in the current window.

3. Check if the window is valid:
   - If `window_length - max_freq > k` (too many replacements needed), shrink the window from the left by incrementing `left` and updating the frequency map.

4. Update `max_length` as the maximum of its current value and the window size (`right - left + 1`).

5. Return `max_length`.

---

## Code Implementation in Java

```java
import java.util.HashMap;

class Solution {
   public int characterReplacement(String s, int k) {
      int n = s.length();
      int maxCount=0;
      int maxLength =0;
      int left =0;
      int[] freq = new int[26];

      for(int right=0;right<n;right++){
         freq[s.charAt(right)-'A']++;
         maxCount = Math.max(maxCount, freq[s.charAt(right)-'A']);
         int window = right-left+1;
         if(window - maxCount > k){
            freq[s.charAt(left)-'A']--;
            left++;
         }
         maxLength = Math.max(maxLength, right-left+1);

      }

      return maxLength;

   }
}
    public static void main(String[] args) {
        System.out.println(characterReplacement("BAABAABBBAAA", 2)); // Output: 6
        System.out.println(characterReplacement("AABABBA", 1));       // Output: 4
    }
}
```

---

## Explanation of Examples

### Example 1

**Input**: `s = "BAABAABBBAAA"`, `k = 2`

- Sliding window expands and adjusts by replacing `B`s at indices `0` and `3` with `A`s.
- Final string: `"AAAAAABBBAAA"`.
- Longest substring with the same character: `"AAAAAA"`.
- **Answer**: `6`.

### Example 2

**Input**: `s = "AABABBA"`, `k = 1`

- Replace the `A` at index `4` with `B`.
- Final string: `"AABBBBA"`.
- Longest substring with the same character: `"BBBB"`.
- **Answer**: `4`.

---

## Complexity

1. **Time Complexity**: \(O(n)\)
   - Each character is processed at most twice (once when added to the window, once when removed).

2. **Space Complexity**: \(O(1)\)
   - Frequency map contains at most 26 entries (constant space for uppercase English letters).

# Reverse String
 
Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.



Example 1:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]


Constraints:

1 <= s.length <= 105
s[i] is a printable ascii character.

```java
class Solution {
    public void reverseString(char[] s) {
        //Do not return anything, modify s in-place instead.
        int l = 0;
        int r = s.length - 1;
        while(l <= r) {
            char tmp = s[l];
            s[l++] = s[r];
            s[r--] = tmp;
        }
    }
}
```


# Reverse Words in a String

### Problem
Given an input string containing upper-case and lower-case letters, digits, and spaces (`' '`). A word is defined as a sequence of non-space characters. The words in the string are separated by at least one space.

Return a string with the words in reverse order, concatenated by a single space.

### Examples

**Input:**
```plaintext
s = "welcome to the jungle"
```

**Output:**
```plaintext
"jungle the to welcome"
```

**Explanation:**
The words in the input string are "welcome", "to", "the", and "jungle". Reversing the order of these words gives "jungle", "the", "to", and "welcome". The output string should have exactly one space between each word.

**Input:**
```plaintext
s = " amazing coding skills "
```

**Output:**
```plaintext
"skills coding amazing"
```

---

### Solution in Java

```java
public class ReverseWords {
    public static String reverseWords(String s) {
        // Trim leading and trailing spaces and split the string by one or more spaces
        String[] words = s.trim().split("\\s+");
        
        // Use StringBuilder to construct the reversed string
        StringBuilder reversed = new StringBuilder();
        
        // Traverse the words in reverse order
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i > 0) {
                reversed.append(" ");
            }
        }
        
        return reversed.toString();
    }

    public static void main(String[] args) {
        String input1 = "welcome to the jungle";
        String input2 = " amazing coding skills ";
        
        System.out.println(reverseWords(input1)); // Output: "jungle the to welcome"
        System.out.println(reverseWords(input2)); // Output: "skills coding amazing"
    }
}
```

One more Solution
```java
class Solution {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        int left =0;
        int right = words.length -1;

        while(left<right){
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }

        StringBuilder sb = new StringBuilder();
        for(String word: words){
            if(!word.isEmpty()){
                if(sb.length()>0){
                    sb.append(" ");
                }
                sb.append(word);
            }
        }

        return sb.toString();
    }
}

```

### Explanation
1. **Trimming and Splitting:**
   - The `trim()` method removes leading and trailing spaces.
   - `split("\\s+")` splits the string into words using one or more spaces as a delimiter.

2. **Reversing the Words:**
   - Start from the last word in the array and append each word to the `StringBuilder`.
   - Add a single space between words except after the last word.

3. **Output:**
   - The `StringBuilder` is converted to a string and returned as the result.

This solution handles extra spaces and ensures only a single space separates the reversed words.


# Valid Palindrome

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.



Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.

```java
class Solution {
    public boolean isPalindrome(String s) {
        // convert all to lowercase
        s = s.toLowerCase();

        int left =0;
        int right = s.length() -1;

        while(left<=right){
            char ch1 = s.charAt(left);
            char ch2 = s.charAt(right);

            if(!Character.isLetterOrDigit(ch1)){
                left++;
            } else if (!Character.isLetterOrDigit(ch2)){
                right--;
            } else  {
                if(ch1!=ch2){
                return false;
             } else {
                left++;
                right--;
             } 
              
            }
        }
        return true;

    }
}
```

# Valid Parentheses Problem

### Problem Description
Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

### Examples

#### Example 1:
```text
Input: s = "()"
Output: true
```

#### Example 2:
```text
Input: s = "()[]{}"
Output: true
```

#### Example 3:
```text
Input: s = "(]"
Output: false
```

#### Example 4:
```text
Input: s = "([])"
Output: true
```

### Constraints
- `1 <= s.length <= 10^4`
- `s` consists of parentheses only `'()[]{}'`.

### Solution in Java

```java
import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // Push opening brackets to the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // If stack is empty, return false
                if (stack.isEmpty()) {
                    return false;
                }
                
                // Check if the closing bracket matches the top of the stack
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == '}' && top != '{') || 
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // If stack is empty, all brackets are matched
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();

        // Test cases
        System.out.println(vp.isValid("()"));       // true
        System.out.println(vp.isValid("()[]{}"));   // true
        System.out.println(vp.isValid("(]"));       // false
        System.out.println(vp.isValid("([])"));     // true
    }
}
```

### Explanation
1. **Use a stack:**
   - Push opening brackets onto the stack.
   - For closing brackets, check if the top of the stack has a matching opening bracket. If it doesn't, the string is invalid.
2. **Check the stack:**
   - At the end of the loop, the stack must be empty. If it's not, it means there are unmatched opening brackets.

### Complexity Analysis
- **Time Complexity:**
   - `O(n)` where `n` is the length of the string, as we traverse the string once.

- **Space Complexity:**
   - `O(n)` in the worst case, where all characters in the string are opening brackets.

### Count All the Palindromic substring

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.



Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.

### Intuition
To solve the problem of counting all palindromic substrings in a string, we use the **expand-around-center** technique. The core idea is:

1. A **palindrome** reads the same backward as forward.
2. For any character in the string, treat it as the potential center of a palindrome and expand outward to check if the substring is a palindrome.
3. Palindromes can have:
   - **Odd length**: Center is a single character.
   - **Even length**: Center is between two characters.
4. By expanding from every possible center, we can systematically find all palindromic substrings.

### Why Two Center Expansions?
- **Odd-length palindromes** have a single character center. Example: "aba"
- **Even-length palindromes** have two consecutive characters as the center. Example: "abba"

To ensure we count both types, we need two expansions for each character: one for odd-length and one for even-length palindromes.

### Follow-Up Questions
1. **Can this approach handle larger strings efficiently?**
   - Yes, the time complexity is \(O(n^2)\), which is efficient for strings up to the given constraint (\(1 \leq s.length \leq 1000\)).
2. **Can we improve the time complexity?**
   - An alternative approach is using **Manacher's Algorithm**, which can find all palindromic substrings in \(O(n)\). However, this is more complex to implement.

### Java Code
Here is the Java implementation using the expand-around-center approach:

```java
class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        int result = 0;

        for (int i = 0; i < len; i++) {
            // Check for odd-length palindromes
            result += expandAround(s, i, i);
            // Check for even-length palindromes
            result += expandAround(s, i, i + 1);
        }

        return result;
    }

    int expandAround(String s, int start, int end) {
        int result = 0;

        // Expand outward while the substring remains a palindrome
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            result++;
            start--; // Move left
            end++;   // Move right
        }

        return result;
    }
}
```

### Example Walkthrough
#### Input: `"aaa"`
1. **Step 1**: Expand from index 0:
   - Odd: `"a"` (1 palindrome).
   - Even: `"aa"` (1 palindrome).
2. **Step 2**: Expand from index 1:
   - Odd: `"a"`, `"aaa"` (2 palindromes).
   - Even: `"aa"` (1 palindrome).
3. **Step 3**: Expand from index 2:
   - Odd: `"a"` (1 palindrome).
   - Even: None.

**Output**: Total = `6` palindromic substrings.

# 5. Longest Palindromic Substring

## Problem Description

Given a string `s`, return *the longest palindromic substring* in `s`.

### Examples

**Example 1:**
```
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
```

**Example 2:**
```
Input: s = "cbbd"
Output: "bb"
```

### Constraints
* `1 <= s.length <= 1000`
* `s` consist of only digits and English letters.

## Intuition and Approach

### Key Insights
1. Every palindrome has a center point
2. This center can be a single character or between two characters
3. We can systematically expand from each potential center

### Solution Strategy
- Iterate through each character in the string
- For each character, consider two types of centers:
   1. The character itself (odd-length palindromes)
   2. The space between the current and next character (even-length palindromes)
- Expand outwards from each center, tracking the longest palindrome found
- Keep track of the start index and maximum length of the palindrome

## Java Implementation

```java
class Solution {
    public String longestPalindrome(String s) {
        // Handle edge cases
        if (s == null || s.length() < 2) {
            return s;
        }
        
        // Variables to track the longest palindrome
        int start = 0;     // Starting index of longest palindrome
        int maxLength = 1; // Length of longest palindrome
        
        // Try every possible center of palindrome
        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes (single character center)
            int len1 = expandAroundCenter(s, i, i);
            
            // Check for even-length palindromes (between characters center)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Update longest palindrome if a longer one is found
            int currentMaxLength = Math.max(len1, len2);
            if (currentMaxLength > maxLength) {
                // Calculate start index based on the current center
                start = i - (currentMaxLength - 1) / 2;
                maxLength = currentMaxLength;
            }
        }
        
        // Extract and return the longest palindromic substring
        return s.substring(start, start + maxLength);
    }
    
    // Helper method to expand around a center and find palindrome length
    private int expandAroundCenter(String s, int left, int right) {
        // Expand outwards while characters match and within string bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        // Return length of palindrome (right - left - 1)
        return right - left - 1;
    }
}
```

## Detailed Walkthrough

### Iteration Process
Let's break down the logic with an example: `s = "babad"`

1. **Start at index 0 ('b')**:
   * Odd center palindrome: "b" (length 1)
   * Even center palindrome: No valid palindrome

2. **At index 1 ('a')**:
   * Odd center palindrome: "aba" (length 3)
   * Even center palindrome: "ba" (length 0)

3. **At index 2 ('b')**:
   * Odd center palindrome: "b" (length 1)
   * Even center palindrome: "bab" (length 3)

### Key Method: `expandAroundCenter()`
- Takes a left and right index
- Expands outwards while characters match
- Returns the length of the palindrome found

## Complexity Analysis

### Time and Space Complexity
- **Time Complexity**: O(n²), where n is the string length
- **Space Complexity**: O(1), as we're just tracking indices

## Optimization Potential
While this solution is intuitive and works well for most cases, for extremely large strings, more advanced algorithms like Manacher's algorithm can provide O(n) time complexity.

## Practical Tips
- Always consider edge cases (empty or single-character strings)
- Break complex problems into smaller, manageable steps
- Use helper methods to improve code readability


# Minimum Window Substring

## Problem Description

Given two strings `s` and `t`, find the **minimum window** in `s` which will contain all the characters in `t` (including duplicates). If there is no such window, return an empty string.

### Example:

#### Input:
```java
s = "ADOBECODEBANC", t = "ABC"
```

#### Output:
```java
"BANC"
```

#### Explanation:
The minimum window substring of `s` which contains all characters of `t` is `"BANC"`, which is the answer.

---

## Intuition

The problem is a typical sliding window problem where we need to find a substring in `s` that contains all characters of `t`. The window should be as small as possible, and we need to take care of the fact that the window should contain all characters of `t` including duplicates. The general approach involves two main steps:

1. **Expanding the window**: This involves moving the `right` pointer of the window to include characters from `s` until all characters in `t` are covered.
2. **Shrinking the window**: Once we have a valid window (i.e., all characters in `t` are covered), we move the `left` pointer to shrink the window while ensuring that it remains valid (i.e., it still contains all characters from `t`).

---

## Java Code

```java
class Solution {
    public String minWindow(String s, String t) {
        int s_length = s.length();
        int t_length = t.length();
        
        if (t_length > s_length) return "";

        Map<Character, Integer> target = new HashMap<>();
        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int startIndex = -1, min = Integer.MAX_VALUE;
        int matchCount = 0;

        while (right < s_length) {
            // Take right char and update its frequency in the window
            char rightChar = s.charAt(right);
            window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);

            // If the current character's count matches the required count, increase matchCount
            if (target.containsKey(rightChar) && window.get(rightChar).intValue() == target.get(rightChar).intValue()) {
                matchCount++;
            }

            // Shrink the window when all characters are matched
            while (matchCount == target.size()) {
                // Update the minimum window size
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    startIndex = left;
                }

                // Shrink the window from the left
                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);

                // If the frequency of leftChar falls below the required frequency, decrease matchCount
                if (target.containsKey(leftChar) && window.get(leftChar).intValue() < target.get(leftChar).intValue()) {
                    matchCount--;
                }

                left++; // Move the left pointer
            }

            right++; // Expand the window
        }

        // Return the result
        return min == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + min);
    }
}
```

---

## Dry Run

Let's walk through a dry run with the input `s = "ADOBECODEBANC", t = "ABC"`.

### Initial Setup:

- `target` map: `{A: 1, B: 1, C: 1}`
- `window` map: `{}` (empty initially)
- `matchCount = 0`, `startIndex = -1`, `min = Integer.MAX_VALUE`

---

### Expanding the Window:

1. **Right = 0, s[right] = 'A'**:
   - `window = {'A': 1}`
   - `window.get('A') = 1` matches `target.get('A') = 1`, so `matchCount = 1`.
   - Since `matchCount` is not equal to `target.size()`, continue expanding.

2. **Right = 1, s[right] = 'D'**:
   - `window = {'A': 1, 'D': 1}`
   - No update to `matchCount` as 'D' is not in `target`.
   - Continue expanding.

3. **Right = 2, s[right] = 'O'**:
   - `window = {'A': 1, 'D': 1, 'O': 1}`
   - No update to `matchCount` as 'O' is not in `target`.
   - Continue expanding.

4. **Right = 3, s[right] = 'B'**:
   - `window = {'A': 1, 'D': 1, 'O': 1, 'B': 1}`
   - `window.get('B') = 1` matches `target.get('B') = 1`, so `matchCount = 2`.
   - Continue expanding.

5. **Right = 4, s[right] = 'E'**:
   - `window = {'A': 1, 'D': 1, 'O': 1, 'B': 1, 'E': 1}`
   - No update to `matchCount` as 'E' is not in `target`.
   - Continue expanding.

6. **Right = 5, s[right] = 'C'**:
   - `window = {'A': 1, 'D': 1, 'O': 1, 'B': 1, 'E': 1, 'C': 1}`
   - `window.get('C') = 1` matches `target.get('C') = 1`, so `matchCount = 3`.
   - Now `matchCount == target.size()`, so we start shrinking the window.

---

### Shrinking the Window:

- **Left = 0, s[left] = 'A'**:
   - Update `window = {'A': 0, 'D': 1, 'O': 1, 'B': 1, 'E': 1, 'C': 1}`
   - `window.get('A') < target.get('A')`, so `matchCount = 2`.
   - `startIndex = 0`, `min = 4` (current window size is 4).

- **Left = 1, s[left] = 'D'**:
   - Update `window = {'A': 0, 'D': 0, 'O': 1, 'B': 1, 'E': 1, 'C': 1}`
   - No further reduction in `matchCount`.
   - Continue shrinking.

---

### Final Result:

The minimum window is `"BANC"`.

---

## Time Complexity:

- **Time Complexity:** \( O(n) \), where \( n \) is the length of the string `s`. Both the `right` and `left` pointers traverse the string only once, and operations inside the loop (like `get` and `put` in the `HashMap`) are constant time on average.
- **Space Complexity:** \( O(m) \), where \( m \) is the number of distinct characters in `t` (space for the `target` and `window` maps).

---

## Conclusion

This algorithm efficiently solves the problem by utilizing the sliding window technique, ensuring that both the time and space complexities remain optimal.



# Find All Anagrams in a String

## Problem Description
Given two strings `s` and `p`, return an array of all the start indices of `p`'s anagrams in `s`. You may return the answer in any order.

### Example 1:
**Input**:
```text
s = "cbaebabacd", p = "abc"
```
**Output**:
```text
[0, 6]
```

**Explanation**:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

### Example 2:
**Input**:
```text
s = "abab", p = "ab"
```
**Output**:
```text
[0, 1, 2]
```

**Explanation**:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

### Constraints:
- 1 <= s.length, p.length <= 3 * 10^4
- `s` and `p` consist of lowercase English letters.

## Intuition
The problem boils down to checking if any substring of `s` is an anagram of `p`. An anagram of a string is a rearrangement of its characters. To efficiently find all the anagrams in `s`, we can use the sliding window technique.

A sliding window allows us to maintain a window of size `p.length()` on `s` and check whether the characters in this window form an anagram of `p`. If they do, we record the starting index of the window. We then slide the window one character at a time to check the next potential anagram.

## Java Logic

```java
import java.util.*;

public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }
        
        // Frequency array for p
        int[] pCount = new int[26];
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        // Sliding window frequency array for s
        int[] sCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
        }
        
        // Check the first window
        if (Arrays.equals(pCount, sCount)) {
            result.add(0);
        }
        
        // Slide the window over the rest of s
        for (int i = p.length(); i < s.length(); i++) {
            // Add the new character to the window
            sCount[s.charAt(i) - 'a']++;
            // Remove the old character from the window
            sCount[s.charAt(i - p.length()) - 'a']--;
            
            // Check if the current window is an anagram of p
            if (Arrays.equals(pCount, sCount)) {
                result.add(i - p.length() + 1);
            }
        }
        
        return result;
    }
}
```

## Dry Run
### Example 1:
Input:
```text
s = "cbaebabacd", p = "abc"
```
Initial `pCount = [1, 1, 1, 0, ..., 0]` (for "abc").
Initial `sCount = [1, 1, 1, 0, ..., 0]` (for "cba").

We compare both counts and find them equal. So, we add index 0.

Then, we slide the window and check the substring "bac", which also matches the count of "abc". We add index 6.

Final result: `[0, 6]`.

### Example 2:
Input:
```text
s = "abab", p = "ab"
```
Initial `pCount = [1, 1, 0, 0, ..., 0]` (for "ab").
Initial `sCount = [1, 1, 0, 0, ..., 0]` (for "ab").

We compare both counts and find them equal. So, we add index 0.

Then, we slide the window and check the substring "ba", which also matches the count of "ab". We add index 1.

Finally, we check the substring "ab" again at index 2 and add it to the result.

Final result: `[0, 1, 2]`.

## Time Complexity
- **Time complexity**: O(n), where `n` is the length of string `s`. We traverse through string `s` once and compare frequency counts in constant time.
- **Space complexity**: O(1), as the space used for the frequency arrays is constant (26 characters at most for lowercase English letters).

