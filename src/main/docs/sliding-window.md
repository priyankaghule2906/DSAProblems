# Sliding Window — Problem Log

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

## 6. Maximum Number of Vowels in a Substring of Given Length
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