https://leetcode.com/discuss/interview-question/5886397/DSA-Patterns-you-need-to-know-!!!/

# Two Pointers Technique

The **Two Pointers** technique is an efficient approach used for solving various algorithmic problems. It involves using two indices (or pointers) that traverse the data structure in a strategic manner.

## **Standard Algorithm for Two Pointers**
1. **Determine the type of traversal**
    - **Opposite ends (Left & Right pointers)**: Used for problems like palindrome checking, pair sum, and sorting-related problems.
    - **Same direction (Slow & Fast pointers)**: Used for problems in linked lists like cycle detection and finding the middle element.

2. **Initialize the pointers**
    - If scanning from both ends, set `left = 0` and `right = n - 1`.
    - If using slow/fast pointers, initialize both at the starting position.

3. **Iterate while the pointers are within valid bounds**
    - Adjust pointers based on the problem logic.
    - One pointer often moves conditionally based on the other's position.

4. **Process the elements and update pointers**
    - Perform necessary operations such as swaps, deletions, or sum checks.
    - Move `left` or `right` accordingly (`left++`, `right--`).
    - Handle edge cases like duplicates or empty input.

---

## **Two Pointers Problems & Solutions**

### **1. Valid Palindrome**
Check if a string is a palindrome, ignoring non-alphanumeric characters.
```java
public boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    
    while (left < right) {
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
        
        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) 
            return false;
        
        left++;
        right--;
    }
    return true;
}
```

### **2. 3Sum**
Find unique triplets in an array that sum to zero.
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
                left++;
                right--;
                while (left < right && nums[left] == nums[left - 1]) left++;
                while (left < right && nums[right] == nums[right + 1]) right--;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
    }
    return result;
}
```

### **3. Remove Nth Node from End of List**
Use slow and fast pointers to remove the Nth node from the end.
```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode slow = dummy, fast = dummy;
    
    for (int i = 0; i <= n; i++) fast = fast.next;
    while (fast != null) {
        slow = slow.next;
        fast = fast.next;
    }
    slow.next = slow.next.next;
    return dummy.next;
}
```

### **4. Two Sum II - Input Array is Sorted**
Find two numbers in a sorted array that sum up to a target value.
```java
public int[] twoSum(int[] numbers, int target) {
    int left = 0, right = numbers.length - 1;
    while (left < right) {
        int sum = numbers[left] + numbers[right];
        if (sum == target) {
            return new int[]{left + 1, right + 1};
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }
    return new int[]{};
}
```

### **5. Container With Most Water**
Find the maximum area of water that can be held between two lines.
```java
public int maxArea(int[] height) {
    int left = 0, right = height.length - 1;
    int maxArea = 0;
    
    while (left < right) {
        int area = Math.min(height[left], height[right]) * (right - left);
        maxArea = Math.max(maxArea, area);
        
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    return maxArea;
}
```
# Two Pointers Technique

The **Two Pointers** technique is an efficient approach used for solving various algorithmic problems. It involves using two indices (or pointers) that traverse the data structure in a strategic manner.

## **Standard Algorithm for Two Pointers**
1. **Determine the type of traversal**
    - **Opposite ends (Left & Right pointers)**: Used for problems like palindrome checking, pair sum, and sorting-related problems.
    - **Same direction (Slow & Fast pointers)**: Used for problems in linked lists like cycle detection and finding the middle element.

2. **Initialize the pointers**
    - If scanning from both ends, set `left = 0` and `right = n - 1`.
    - If using slow/fast pointers, initialize both at the starting position.

3. **Iterate while the pointers are within valid bounds**
    - Adjust pointers based on the problem logic.
    - One pointer often moves conditionally based on the other's position.

4. **Process the elements and update pointers**
    - Perform necessary operations such as swaps, deletions, or sum checks.
    - Move `left` or `right` accordingly (`left++`, `right--`).
    - Handle edge cases like duplicates or empty input.

---

## **Two Pointers Problems & Solutions**

### **1. Valid Palindrome**
Check if a string is a palindrome, ignoring non-alphanumeric characters.
```java
public boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    
    while (left < right) {
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
        
        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) 
            return false;
        
        left++;
        right--;
    }
    return true;
}
```

### **2. Valid Word Abbreviation**
```java
public boolean validWordAbbreviation(String word, String abbr) {
    int i = 0, j = 0;
    while (i < word.length() && j < abbr.length()) {
        if (Character.isDigit(abbr.charAt(j))) {
            if (abbr.charAt(j) == '0') return false;
            int num = 0;
            while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                num = num * 10 + (abbr.charAt(j++) - '0');
            }
            i += num;
        } else {
            if (word.charAt(i++) != abbr.charAt(j++)) return false;
        }
    }
    return i == word.length() && j == abbr.length();
}
```

### **3. Strobogrammatic Number**
```java
public boolean isStrobogrammatic(String num) {
    Map<Character, Character> map = Map.of('0', '0', '1', '1', '6', '9', '8', '8', '9', '6');
    int left = 0, right = num.length() - 1;
    while (left <= right) {
        if (!map.containsKey(num.charAt(left)) || map.get(num.charAt(left)) != num.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}
```

### **4. Minimum Number of Moves to Make Palindrome**
```java
public int minMovesToMakePalindrome(String s) {
    int moves = 0;
    List<Character> list = new ArrayList<>();
    for (char c : s.toCharArray()) list.add(c);
    while (!list.isEmpty()) {
        int i = list.lastIndexOf(list.get(0));
        if (i == 0) {
            moves += list.size() / 2;
        } else {
            moves += i;
            list.remove(i);
        }
        list.remove(0);
    }
    return moves;
}
```

### **5. Next Palindrome Using Same Digits**
```java
public String nextPalindrome(String num) {
    char[] arr = num.toCharArray();
    int n = arr.length / 2;
    if (!nextPermutation(arr, n)) return "";
    for (int i = 0; i < n; i++) arr[arr.length - 1 - i] = arr[i];
    return new String(arr);
}

private boolean nextPermutation(char[] arr, int n) {
    int i = n - 2;
    while (i >= 0 && arr[i] >= arr[i + 1]) i--;
    if (i < 0) return false;
    int j = n - 1;
    while (arr[j] <= arr[i]) j--;
    swap(arr, i, j);
    reverse(arr, i + 1, n - 1);
    return true;
}
```

### **6. Lowest Common Ancestor of a Binary Tree III**
```java
public Node lowestCommonAncestor(Node p, Node q) {
    Node a = p, b = q;
    while (a != b) {
        a = (a == null) ? q : a.parent;
        b = (b == null) ? p : b.parent;
    }
    return a;
}
```

### **7. Valid Palindrome II**
```java
public boolean validPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
        }
        left++;
        right--;
    }
    return true;
}

private boolean isPalindrome(String s, int left, int right) {
    while (left < right) {
        if (s.charAt(left++) != s.charAt(right--)) return false;
    }
    return true;
}
```

---
## **Conclusion**
- **Two-pointer techniques** work well on sorted arrays or linked lists.
- **Opposite end pointers** are useful for problems like two-sum, reversing arrays, and sorting.
- **Same-direction pointers** (slow/fast) help in cycle detection, middle element search, and linked list operations.



// Two Pointer Technique Examples

// 1. Two Sum - Find two numbers in a sorted array that sum to target
class TwoSum {
public int[] findTwoSum(int[] nums, int target) {
int left = 0;
int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }
}

// 2. Remove Duplicates from Sorted Array
class RemoveDuplicates {
public int removeDuplicates(int[] nums) {
if (nums.length == 0) return 0;

        int writePointer = 1;
        for (int readPointer = 1; readPointer < nums.length; readPointer++) {
            if (nums[readPointer] != nums[readPointer - 1]) {
                nums[writePointer] = nums[readPointer];
                writePointer++;
            }
        }
        return writePointer;
    }
}

// 3. Container With Most Water
class ContainerWithMostWater {
public int maxArea(int[] height) {
int maxArea = 0;
int left = 0;
int right = height.length - 1;

        while (left < right) {
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, width * h);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}

// 4. 3Sum - Find three numbers that sum to zero
class ThreeSum {
public List<List<Integer>> threeSum(int[] nums) {
Arrays.sort(nums);
List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
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

// 5. Move Zeros
class MoveZeros {
public void moveZeroes(int[] nums) {
int writePointer = 0;

        // Move all non-zero elements to the front
        for (int readPointer = 0; readPointer < nums.length; readPointer++) {
            if (nums[readPointer] != 0) {
                nums[writePointer] = nums[readPointer];
                writePointer++;
            }
        }
        
        // Fill remaining positions with zeros
        while (writePointer < nums.length) {
            nums[writePointer] = 0;
            writePointer++;
        }
    }
}

// More Two Pointer Problems

// 1. Palindrome Check - Check if string is palindrome ignoring non-alphanumeric
class ValidPalindrome {
public boolean isPalindrome(String s) {
int left = 0;
int right = s.length() - 1;

        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

// 2. Reverse Words in String
class ReverseWords {
public String reverseWords(String s) {
char[] chars = s.toCharArray();

        // First reverse the entire string
        reverse(chars, 0, chars.length - 1);
        
        // Then reverse each word
        int start = 0;
        for (int end = 0; end < chars.length; end++) {
            if (chars[end] == ' ') {
                reverse(chars, start, end - 1);
                start = end + 1;
            }
        }
        // Reverse last word
        reverse(chars, start, chars.length - 1);
        
        return new String(chars);
    }
    
    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}

// 3. Trapping Rain Water
class TrappingRainWater {
public int trap(int[] height) {
if (height == null || height.length < 3) return 0;

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        
        while (left < right) {
            // Water trapped depends on the smaller height
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }
}

// 4. Sort Colors (Dutch National Flag Problem)
class SortColors {
public void sortColors(int[] nums) {
int low = 0;        // for 0s
int mid = 0;        // for 1s
int high = nums.length - 1;   // for 2s

        while (mid <= high) {
            switch (nums[mid]) {
                case 0:
                    swap(nums, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(nums, mid, high);
                    high--;
                    break;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// 5. Minimum Window Substring
class MinWindow {
public String minWindow(String s, String t) {
if (s == null || t == null || s.length() == 0 || t.length() == 0) {
return "";
}

        // Character frequency map for target string
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        
        int start = 0, minStart = 0;
        int end = 0;
        int minLen = Integer.MAX_VALUE;
        int counter = t.length(); // characters still needed
        
        while (end < s.length()) {
            // If char in s exists in t, decrease counter
            if (map[s.charAt(end)] > 0) {
                counter--;
            }
            // Decrease map value for the character
            map[s.charAt(end)]--;
            end++;
            
            // When we found a valid window
            while (counter == 0) {
                if (end - start < minLen) {
                    minStart = start;
                    minLen = end - start;
                }
                
                // Increase map value for the character
                map[s.charAt(start)]++;
                // If char in s exists in t, increase counter
                if (map[s.charAt(start)] > 0) {
                    counter++;
                }
                start++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}