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

