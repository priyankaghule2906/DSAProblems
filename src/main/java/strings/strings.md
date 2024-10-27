# String related Problems

1. Group Anagrams (leetcode 46 medium)

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