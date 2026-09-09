package slidingwindow;


import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SlidingWindowPatterns {

    private static final long MOD = 1_000_000_007L;

    @Test
    public void testMaximumFrequencyScoreOfSubarray(){
        System.out.println(power(2, 5));
    }

    private long modPow(long base, long exp){
        base %= MOD;
        long result = 1L;

        while(exp > 0){
            if((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) * MOD;
            exp >>=1;
        }
        return result;
    }

    public static double power(long base, int exp) {
        long result = 1;
        long currentBase = base;
        long n = exp;

        // Handle negative exponents
        if (n < 0) {
            n = -n;
        }

        while (n > 0) {
            // Check if the lowest bit is 1 using bitwise AND
            if ((n & 1) == 1) {
                result *= currentBase;
            }

            // Square the base for the next bit position
            currentBase *= currentBase;

            // Shift exponent right by 1 bit to process the next bit
            n >>= 1;
        }

        return exp < 0 ? (1.0 / result) : result;
    }


    @Test
    public void testEqualCountSubstrings(){
        Assert.assertEquals(3, equalCountSubstrings("aaabcbbcc", 3));
    }

    public int equalCountSubstrings(String s, int count){
        int result =0;


        for(int i=1;i<=26;i++){
            int k = i*count;
            if( k > s.length()) {
                break;
            }
            int[] freq = new int[26];
            // build first window
            for(int right =0;right <k;right++){
                freq[s.charAt(right) - 'a']++;

            }
           // System.out.println(s.substring(0, k));
            if(isValid(freq, count)) result++;
            // slide the window
            for(int right=k; right<s.length(); right++){
                // remove the left
                char left = s.charAt(right-k);
                freq[left -'a']--;

                // add current char
                freq[s.charAt(right)-'a']++;

                if(isValid(freq, count)) result++;
              //  System.out.println(s.substring(right-k +1, right));
            }

        }
        return result;
    }

    private boolean isValid(int[] freq, int count){
        for(int num: freq){
            if(num!=0 && num!=count) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void testMinMoves(){
        Assert.assertEquals(5, minMoves(new int[]{1,0,0,0,0,0,1,1}, 3));
    }

    public int minMoves(int[] nums, int k) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) positions.add(i);
        }

        int n = positions.size();
        long[] normalized = new long[n];
        for (int i = 0; i < n; i++) {
            normalized[i] = positions.get(i) - i;
        }

        // prefix[i] = sum of normalized[0..i-1]
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + normalized[i];
        }

        long answer = Long.MAX_VALUE;

        for (int left = 0; left + k <= n; left++) {
            int right = left + k - 1;
            int mid = left + k / 2;
            long median = normalized[mid];

            // left side: [left, mid)  -- all <= median
            long leftCount = mid - left;
            long leftSum = prefix[mid] - prefix[left];
            long leftCost = median * leftCount - leftSum;

            // right side: [mid, right]  -- all >= median
            long rightCount = right - mid + 1;
            long rightSum = prefix[right + 1] - prefix[mid];
            long rightCost = rightSum - median * rightCount;

            answer = Math.min(answer, leftCost + rightCost);
        }

        return (int) answer;
    }


    @Test
    public void testIsAnagram(){
        Assert.assertEquals(false, isAnagram("jar", "jam"));
    }

    public boolean isAnagram(String s, String t) {
        if(s == null && t == null && s == t) return true;
        if(s.length() !=t.length()) return false;
        int[] schar = new int[26];
        int[] tchar = new int[26];

        for(int i=0;i<s.length();i++) {
            schar[s.charAt(i) -'a']++;
            tchar[t.charAt(i) -'a']++;
        }

        // compare two array

        for(int i=0;i<26;i++){
            System.out.println("schar[i] "+ schar[i] + " tchar[i] "+ tchar[i]);
            if(schar[i]!=tchar[i]) return false;
        }
        return true;

    }

    @Test
    public void test(){
        int[] arr = {12, -1, -7, 8, -15, 30, 16, 28}; int k=3;
        firstNegativeInWindow(arr, k);
    }

    public static int[] firstNegativeInWindow(int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> negIndices = new ArrayDeque<>(); // stores indices of negatives, front = oldest

        for (int i = 0; i < n; i++) {
            // add current element's index if negative
            if (arr[i] < 0) {
                negIndices.addLast(i);
            }

            // remove indices that are out of this window's range
            while (!negIndices.isEmpty() && negIndices.peekFirst() <= i - k) {
                negIndices.pollFirst();
            }

            // once we've formed a full window of size k, record the answer
            if (i >= k - 1) {
                result[i - k + 1] = negIndices.isEmpty() ? 0 : arr[negIndices.peekFirst()];
            }
        }

        return result;
    }

    @Test
    public void testFindMAxAvg(){
        Assert.assertEquals(1,findMaxAverage(Arrays.asList(1, 12, -5, -6, 50, 3), 4));

    }

    public int findMaxAverage(List<Integer> arr, int k) {
        // code here
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        int start =0;
        for(int right =0;right<arr.size();right++){
            sum = sum + arr.get(right);

            if(right >= k-1){
                //System.out.println(sum);

                if(maxSum < sum) {
                    maxSum = sum;
                    start = right-k+1;
                }
                sum = sum - arr.get(right-k+1);


            }

        }
        return start;

    }

    @Test
    public void testPq(){
        PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> large = new PriorityQueue<>();
        small.add(5);  large.add(5);
        small.add(4);  large.add(4);
        small.add(6);  large.add(6);
        small.add(3);  large.add(3);
        small.add(7);  large.add(7);

        System.out.println("small PQ");

        small.forEach(System.out::print);
        System.out.println();
        System.out.println("large PQ");
        large.forEach(System.out::print);

    }




}
