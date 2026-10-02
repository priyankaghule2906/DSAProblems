import java.util.*;

/**
 * Prefix sum pattern templates.
 * Convention: prefix[i] = sum of nums[0..i-1], so sum(l..r) = prefix[r+1] - prefix[l].
 */
public class PrefixSumTemplates {

    // ------------------------------------------------------------------
    // 1. BASIC PREFIX ARRAY
    // Use for: 303, 1480, 2559, 2055, 1310 (XOR version: swap + for ^)
    // ------------------------------------------------------------------
    static class RangeSum {
        private final long[] prefix;

        RangeSum(int[] nums) {
            prefix = new long[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefix[i + 1] = prefix[i] + nums[i];
            }
        }

        // inclusive range [l, r]
        long sum(int l, int r) {
            return prefix[r + 1] - prefix[l];
        }
    }

    // Left-sum vs right-sum without an array. Use for: 724
    static int pivotIndex(int[] nums) {
        int total = 0;
        for (int x : nums) total += x;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (left == total - left - nums[i]) return i;
            left += nums[i];
        }
        return -1;
    }

    // ------------------------------------------------------------------
    // 2. PREFIX SUM + HASHMAP
    // Core idea: subarray (j, i] has sum k  <=>  prefix[i] - prefix[j] == k
    // ------------------------------------------------------------------

    // Count subarrays with sum == k. Use for: 560, 930, 1248 (map odd/even to 1/0)
    static int countSubarraysWithSum(int[] nums, int k) {
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, 1); // empty prefix
        int prefix = 0, count = 0;
        for (int x : nums) {
            prefix += x;
            count += seen.getOrDefault(prefix - k, 0);
            seen.merge(prefix, 1, Integer::sum);
        }
        return count;
    }

    // Longest subarray with sum == k. Store FIRST index of each prefix.
    // Use for: 325, 525 (map 0 -> -1, then k = 0), 1590 (with modulo)
    static int longestSubarrayWithSum(int[] nums, int k) {
        Map<Integer, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0, -1);
        int prefix = 0, best = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            Integer j = firstIndex.get(prefix - k);
            if (j != null) best = Math.max(best, i - j);
            firstIndex.putIfAbsent(prefix, i); // keep earliest
        }
        return best;
    }

    // Count subarrays with sum divisible by k. Use for: 974, 523
    // Same remainder twice => the subarray between them is divisible by k.
    static int countSubarraysDivByK(int[] nums, int k) {
        int[] freq = new int[k];
        freq[0] = 1;
        int prefix = 0, count = 0;
        for (int x : nums) {
            prefix = ((prefix + x) % k + k) % k; // handle negatives
            count += freq[prefix];
            freq[prefix]++;
        }
        return count;
    }

    // ------------------------------------------------------------------
    // 3. DIFFERENCE ARRAY (inverse of prefix sum)
    // Range add in O(1), rebuild with one prefix-sum pass.
    // Use for: 370, 1094, 1109
    // updates[i] = {left, right, value}, inclusive, 0-indexed
    // ------------------------------------------------------------------
    static int[] applyRangeUpdates(int n, int[][] updates) {
        int[] diff = new int[n + 1];
        for (int[] u : updates) {
            diff[u[0]] += u[2];
            diff[u[1] + 1] -= u[2];
        }
        int[] result = new int[n];
        int running = 0;
        for (int i = 0; i < n; i++) {
            running += diff[i];
            result[i] = running;
        }
        return result;
    }

    // ------------------------------------------------------------------
    // 4. 2D PREFIX SUM
    // p[i][j] = sum of matrix[0..i-1][0..j-1]
    // Use for: 304, 1314, 1074 (fix two rows, then run 1D map pattern)
    // ------------------------------------------------------------------
    static class RangeSum2D {
        private final long[][] p;

        RangeSum2D(int[][] m) {
            int rows = m.length, cols = m[0].length;
            p = new long[rows + 1][cols + 1];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    p[i + 1][j + 1] = m[i][j] + p[i][j + 1] + p[i + 1][j] - p[i][j];
                }
            }
        }

        // inclusive corners (r1,c1) top-left, (r2,c2) bottom-right
        long sum(int r1, int c1, int r2, int c2) {
            return p[r2 + 1][c2 + 1] - p[r1][c2 + 1] - p[r2 + 1][c1] + p[r1][c1];
        }
    }

    // ------------------------------------------------------------------
    // Quick sanity checks
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        RangeSum rs = new RangeSum(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(rs.sum(0, 2));                                      // 1
        System.out.println(pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));           // 3
        System.out.println(countSubarraysWithSum(new int[]{1, 1, 1}, 2));      // 2
        System.out.println(longestSubarrayWithSum(new int[]{1, -1, 5, -2, 3}, 3)); // 4
        System.out.println(countSubarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5)); // 7
        System.out.println(Arrays.toString(applyRangeUpdates(5, new int[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}})));                // [-2, 0, 3, 5, 3]
        RangeSum2D r2 = new RangeSum2D(new int[][]{{1, 2}, {3, 4}});
        System.out.println(r2.sum(0, 0, 1, 1));                                // 10
    }
    /*
    * Count vs. longest: for counting, store how many times each prefix has occurred.
    * For longest, store only the first index and use putIfAbsent.
Modulo with negatives: always normalize with ((x % k) + k) % k,
* as in the divisible-by-K version, or negative numbers will give wrong remainders.
    * */
}