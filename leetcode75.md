# Merge Strings Alternately
```text
Example 1:

Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
Example 2:

Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s
```
```java
class Solution {
    public String mergeAlternately(String word1, String word2) {
        if(word1 == null || word2 == null)  return null;
        if(word1 == null || word1.isEmpty()) return word2;
        if(word2 == null || word2.isEmpty()) return word1;

        StringBuilder sb = new StringBuilder();
        int len1 = word1.length();
        int len2 = word2.length();
        int len = Math.min(len1, len2);
        for(int i=0;i<len;i++){
            sb.append(word1.charAt(i)).append(word2.charAt(i));
        }

        if(len1 == len2) {
            return sb.toString();
        }
        if(len1 < len2){
            sb.append(word2.substring(len1));
        } else if(len2< len1) {
            sb.append(word1.substring(len2));
        }

        return sb.toString();

        
    }
}
```

# Greatest Common Divisor of Strings
```text
For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).

Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

 

Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""
 

Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of English uppercase letters.
```

```text

To solve the problem, we can leverage the properties of the greatest common divisor (GCD) for strings. Here's the plan:

Determine GCD of Strings: If a string t divides both str1 and str2, it must also divide their concatenation in both orders (str1 + str2 and str2 + str1).
Check GCD Properties: Using the GCD idea for lengths of the strings, the solution involves repeatedly reducing the problem to smaller prefixes of the strings until the greatest common divisor string is found.
Implementation Steps:
If str1 + str2 != str2 + str1, return "" because they don't have a common divisor.
Use the GCD of the lengths of str1 and str2 to determine the size of the largest possible divisor.
Return the prefix of str1 of that size if it divides both strings.
Here's the Java implementation for this problem:
```
```java
public class Solution {
    public String gcdOfStrings(String str1, String str2) {
        // If concatenated strings in both orders are not equal, no common divisor
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // Find the greatest common divisor of the lengths of the strings
        int gcdLength = gcd(str1.length(), str2.length());
        // The largest common divisor string is the prefix of str1 with length gcdLength
        return str1.substring(0, gcdLength);
    }

    // Helper method to compute GCD of two integers
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

```

## Explanation of Examples:
Example 1:

Input: str1 = "ABCABC", str2 = "ABC".
str1 + str2 == str2 + str1, so they share a common divisor.
GCD of lengths: gcd(6, 3) = 3.
Common divisor: "ABC".
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB".
str1 + str2 == str2 + str1, so they share a common divisor.
GCD of lengths: gcd(6, 4) = 2.
Common divisor: "AB".
Example 3:

Input: str1 = "LEET", str2 = "CODE".
str1 + str2 != str2 + str1, so no common divisor exists.
Output: "".
This approach ensures efficiency with a time complexity of
𝑂(min(𝑛,𝑚)) O(min(n,m)), where n and m are the lengths of str1 and str2, due to the GCD computation and string comparison.

The **Euclidean Algorithm** is a highly efficient method for calculating the **Greatest Common Divisor (GCD)** of two integers. It works by repeatedly replacing the larger number with its remainder when divided by the smaller number until one of the numbers becomes 0. At that point, the other number is the GCD.

---

## Algorithm:
1. Start with two numbers, \( a \) and \( b \) (\( a \geq b \)).
2. Divide \( a \) by \( b \), and get the remainder \( r \): \( r = a \mod b \).
3. Replace \( a \) with \( b \), and \( b \) with \( r \).
4. Repeat steps 2–3 until \( b = 0 \). When \( b = 0 \), \( a \) is the GCD.

---

## Example: GCD of 56 and 98

1. Start: \( a = 98 \), \( b = 56 \).
    - Compute \( r = a \mod b = 98 \mod 56 = 42 \).
    - Replace: \( a = 56 \), \( b = 42 \).

2. Next iteration: \( a = 56 \), \( b = 42 \).
    - Compute \( r = a \mod b = 56 \mod 42 = 14 \).
    - Replace: \( a = 42 \), \( b = 14 \).

3. Next iteration: \( a = 42 \), \( b = 14 \).
    - Compute \( r = a \mod b = 42 \mod 14 = 0 \).
    - Replace: \( a = 14 \), \( b = 0 \).

4. Stop: Since \( b = 0 \), the GCD is \( a = 14 \).

---

## Code Implementation in Java
```java
public class GCDExample {
    public static void main(String[] args) {
        int a = 56, b = 98;
        System.out.println("GCD of " + a + " and " + b + " is: " + gcd(a, b));
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b; // Remainder
            a = temp;  // Update a to b
        }
        return a;
    }
}
```

# Kids With the Greatest Number of Candies

```text
There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.

Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.

Note that multiple kids can have the greatest number of candies.

 

Example 1:

Input: candies = [2,3,5,1,3], extraCandies = 3
Output: [true,true,true,false,true] 
Explanation: If you give all extraCandies to:
- Kid 1, they will have 2 + 3 = 5 candies, which is the greatest among the kids.
- Kid 2, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
- Kid 3, they will have 5 + 3 = 8 candies, which is the greatest among the kids.
- Kid 4, they will have 1 + 3 = 4 candies, which is not the greatest among the kids.
- Kid 5, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
Example 2:

Input: candies = [4,2,1,1,2], extraCandies = 1
Output: [true,false,false,false,false] 
Explanation: There is only 1 extra candy.
Kid 1 will always have the greatest number of candies, even if a different kid is given the extra candy.
Example 3:

Input: candies = [12,1,12], extraCandies = 10
Output: [true,false,true]
```

```java
class Solution {
    // TC O(n)
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();

        int max =candies[0];
        int n = candies.length;

        for(int i=1;i<n;i++){
            if(max < candies[i]){
                max = candies[i];
            }
        }
        for(int i=0;i<n;i++){
            int current = candies[i] + extraCandies;
            if(current >= max){
                result.add(true);
            } else {
                result.add(false);
            }
        }

        return result;
        
    }
}
```
# Can Place Flowers Without Modifying Original Array

Here's the step-by-step approach in Java:

1. Loop through the flowerbed array.
2. For each position, check if it is empty (flowerbed[i] == 0) and whether the adjacent positions (if they exist) are also empty or beyond the bounds of the array.
3. If a flower can be planted, plant it (set flowerbed[i] = 1) and decrement n (the number of flowers left to plant).
4. If n becomes 0, return true (all flowers are successfully planted).
5. If the loop completes and n > 0, return false (not enough space to plant all flowers).

Explanation:
Check Conditions: We check the prevEmpty and nextEmpty conditions to ensure no adjacent flowers.
Boundary Handling: For the first and last positions, there are no "previous" or "next" positions, so we handle these cases explicitly.
Update n: Every time a flower is planted, decrement n. If n reaches 0, we can return early.
## Complexity:
Time Complexity: O(flowerbed.length), as we iterate through the array once.
Space Complexity:
O(1), since we are modifying the input array in place and using no extra space.

## Updated Solution in Java

```java
public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int length = flowerbed.length;

        for (int i = 0; i < length; i++) {
            if (flowerbed[i] == 0) {
                // Check if the previous and next spots are empty (or out of bounds)
                boolean prevEmpty = (i == 0) || (flowerbed[i - 1] == 0);
                boolean nextEmpty = (i == length - 1) || (flowerbed[i + 1] == 0);

                if (prevEmpty && nextEmpty) {
                    n--; // Simulate planting a flower by reducing n
                    i++; // Skip the next spot since no adjacent flowers are allowed

                    if (n == 0) {
                        return true; // All flowers are successfully planted
                    }
                }
            }
        }

        return n <= 0; // Return true if all flowers are planted, otherwise false
    }
}


```

# Reverse Vowels in a String

## Problem Statement
Given a string `s`, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

### Example 1:
```
Input: s = "IceCreAm"
Output: "AceCreIm"

Explanation:
The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".
```

### Example 2:
```
Input: s = "leetcode"
Output: "leotcede"
```

### Constraints:
- `1 <= s.length <= 3 * 10^5`
- `s` consists of printable ASCII characters.

---

## Java Solution
Below is the Java implementation to solve the problem:

```java
import java.util.*;

public class ReverseVowels {
    public static String reverseVowels(String s) {
        // Convert the string to a character array
        char[] chars = s.toCharArray();
        
        // Define a set of vowels (both upper and lower case)
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        
        // Two pointers approach
        int left = 0;
        int right = chars.length - 1;
        
        while (left < right) {
            // Move left pointer until it points to a vowel
            while (left < right && !vowels.contains(chars[left])) {
                left++;
            }
            // Move right pointer until it points to a vowel
            while (left < right && !vowels.contains(chars[right])) {
                right--;
            }
            // Swap the vowels
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            
            // Move both pointers
            left++;
            right--;
        }
        
        // Convert the character array back to a string and return
        return new String(chars);
    }

    public static void main(String[] args) {
        // Example 1
        String input1 = "IceCreAm";
        System.out.println("Input: " + input1);
        System.out.println("Output: " + reverseVowels(input1)); // Output: "AceCreIm"

        // Example 2
        String input2 = "leetcode";
        System.out.println("Input: " + input2);
        System.out.println("Output: " + reverseVowels(input2)); // Output: "leotcede"
    }
}
```

---

## Explanation

### Approach:
1. **Two Pointers Approach**:
   - Use two pointers, `left` and `right`, starting from the beginning and end of the string respectively.
   - Move the `left` pointer forward and the `right` pointer backward until they point to vowels.

2. **Set of Vowels**:
   - Use a `Set` to store vowels for quick lookup (both uppercase and lowercase).

3. **Swapping**:
   - Once both pointers point to vowels, swap the characters at those positions.

4. **Iterate Until Pointers Meet**:
   - Continue the process until `left` is no longer less than `right`.

5. **Convert and Return**:
   - Convert the modified character array back to a string and return the result.

### Complexity:
- **Time Complexity**: `O(n)` where `n` is the length of the string.
   - Each character is processed at most once.
- **Space Complexity**: `O(1)` (excluding the space used by the set of vowels).

---

## Examples

### Example 1:
#### Input:
```
IceCreAm
```
#### Output:
```
AceCreIm
```

### Example 2:
#### Input:
```
leetcode
```
#### Output:
```
leotcede
```

---

# Increasing Triplet Subsequence
```text
Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

 

Example 1:

Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.
Example 2:

Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.
```

```java
class Solution {

    /**
     * TC O(n) SC O(1)
    Explanation:
    first keeps track of the smallest number encountered so far.
    second keeps track of the smallest number that is greater than first.
    If a number larger than second is found, it means we have a triplet 

    nums[i]<nums[j]<nums[k].
    
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int Ith = Integer.MAX_VALUE;
        int Jth = Integer.MAX_VALUE;

        for(int k=0;k<n;k++){
            if(nums[k] <= Ith){
                Ith = nums[k];
            } else if(nums[k]<=Jth){
                Jth = nums[k];
            } else {
                return true;
            }
        }
        return false;
        
    }
}
```

