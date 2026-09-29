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

    @Test
    public void testIntegerCompare(){
        int x = 10, y= 15, z = 20, w = 15;

        System.out.println("x compare y : "+ Integer.compare(x, y));
        System.out.println("z compare x : "+ Integer.compare(z, x));
        System.out.println("z compare x : "+ Integer.compare(y, w));

//        if x < y  -1
//        if x > y  1
//        else 0
    }


    @Test
    public void testMinWindowSubsequence(){
        System.out.println(minWindow("abcdebdde", "bde"));

        /*
         a b c d e b d d e  b d e
         0 1 2 3 4 5 6 7 8  0 1 2
        */
    }
    // we have to find the minimum window subsequence, the order of the characters should match in the
    // above example first match is bcde  second match is at bdde, both the strings have same length we have return the first one
    public String minWindow(String s, String t){
     int sLen = s.length();
     int tLen = t.length();

     if(tLen > sLen) return "";
     int minStart = -1;
     int minLength = Integer.MAX_VALUE;

     int sPtr = 0;
     while(sPtr < sLen){
         int tPtr = 0;
         // forwards pass
         while (sPtr < sLen){
             if(s.charAt(sPtr) == t.charAt(tPtr)) {
                 tPtr++;
             }
             if(tPtr == tLen) break; // all the characters from t has matched
             sPtr++;
         }

         if(tPtr < tLen) break; // all the characters t could not be found in s

         // backward pass
         int end = sPtr;
         tPtr = tLen -1;
         int start = end;

         while(tPtr >= 0){
             if(t.charAt(tPtr) == s.charAt(start)){
                 tPtr--;
             }
             start--;
         }
         start++;  // adjust the overshoot

         if(end - start + 1 < minLength){
             minLength = end- start +1;
             minStart = start;
         }

         sPtr = start+1;

     }

     return minStart == -1 ? "" : s.substring(minStart, minStart + minLength);
    }

    @Test
    public void testShortestSubarray(){
        System.out.println(shortestSubarray(new int[]{2, -1, 2, 3, -2, 4}, 5));
    }

    public int shortestSubarray(int[] nums , int k){
        int n = nums.length;
        long[] prefixSum = new long[n+1];
        for(int i =0;i<n;i++){
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int minLen = Integer.MAX_VALUE;

        for(int i=0;i<=n;i++){
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >=k){
                minLen = Math.min(minLen, i - deque.pollFirst());
            }
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    @Test
    public void testMinBitFlips(){
        System.out.println(minKBitFlips(new int[]{0,0,0,1,0,1,1,0}, 3));
    }

    public int minKBitFlips(int[] nums, int k){
        int n = nums.length;
        int flipCounts = 0;
        int ans = 0;
        int[] diff = new int[n+1];

        for(int i=0;i<n;i++){
            flipCounts+=diff[i];
            int currentBit = (nums[i] + flipCounts) % 2;
            if(currentBit == 0){
                if((i+k) > n) return -1;
                ans++;
                flipCounts++;
                diff[i+k]--;
            }
        }

        return ans;
    }

    @Test
    public void testFindLengthOfShortestSubarray(){
        System.out.println(findLengthOfTheShortestSubarray(new int[]{11,10,18,14,12,11,16,20,13,11}));
    }

    public int findLengthOfTheShortestSubarray(int[] arr){
        int n  = arr.length;
        // find the sorted prefix
        int left = 0;
        while (left < n-1 && arr[left] <= arr[left+1]){
            left++;
        }
        // find the sorted suffix
        int right = n-1;
        while(right > 0 && arr[right-1] <= arr[right]){
            right--;
        }
        // find the min among prefix (everything after prefix ) and suffix (everything before suffix)
        int min = Math.min(n-left-1, right);

        // merge prefix and suffix until sorted

        int i =0;
        int j=right;

        while (i<=left && j <n){
            if(arr[i] <= arr[j]){
                min = Math.min(min, j-i-1);
                i++;
            } else {
                j++;
            }
        }
        return min;
    }

    @Test
    public void testNumberOfStrings(){
        System.out.println(numberOfSubstrings("abcabc"));
    }

    public int numberOfSubstrings(String s) {
        int[] freq = new int[3];
        int left = 0, count = 0;

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'a']++;

            while (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                count += s.length() - right; // all substrings starting at `left`, ending at right or later
                freq[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return count;
    }

    @Test
    public void testFindSubstring(){
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }
    public List<Integer> findSubstring(String s, String[] words){
        List<Integer> result = new ArrayList<>();
        if(s == null || s.isEmpty() || words == null || words.length == 0) return result;

        // all the words from words array are of same length that gives us fixed window size
        int wordLength = words[0].length();
        int numOfWords = words.length;
        int totalLength = wordLength * numOfWords;
        Map<String, Integer> need = new HashMap<>();
        for(String word: words){
            need.merge(word, 1, Integer::sum);
        }

        // run the loop from position 0, 1, 2 until it covers all the starting points word length
        for(int offset=0;offset < wordLength; offset++){
            int left = offset;
            int count =0;
            Map<String, Integer> window = new HashMap<>();
                for(int right =  offset; right+wordLength <= s.length(); right+=wordLength){
                    String word = s.substring(right, right+wordLength);

                    if(need.containsKey(word)) {
                        window.merge(word, 1, Integer::sum);
                        count++;

                        while (window.get(word) > need.get(word)){
                            // slide window
                            String leftWord = s.substring(left, left + wordLength);
                            window.merge(leftWord, -1, Integer::sum);
                            count--;
                            left += wordLength;
                        }

                        if(count == numOfWords){
                            result.add(left);
                            // slide the window
                            String leftWord = s.substring(left, left+wordLength);
                            window.merge(leftWord, -1, Integer::sum);
                            count--;
                            left = left+ wordLength;
                        }

                    } else {
                        window.clear();
                        count = 0;
                        left = right + wordLength;
                    }

                }
        }

        return result;
    }

    @Test
    public void testMaxFrequency(){
        System.out.println(maxFrequency(new int[]{1,4,8,13}, 5));
    }


    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;
        int left = 0, maxFreq = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            // cost to make window [left, right] all equal to nums[right]
            while ((long) nums[right] * (right - left + 1) - sum > k) {
                sum -= nums[left];
                left++;
            }

            maxFreq = Math.max(maxFreq, right - left + 1);
        }

        return maxFreq;
    }

    @Test
    public void testMaxSubarrayLength(){
        maxSubarrayLength(new int[]{1,2,3,1,2,3,1,2}, 2);
    }

    public int maxSubarrayLength(int[] nums, int k) {
        int longestSubarray = 0;
        int left = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int right = 0; right < nums.length; right++){
            freq.merge(nums[right], 1, Integer::sum);
            while(nums[right] > k){
                freq.merge(nums[left], -1, Integer::sum);
                left++;
            }
            longestSubarray = Math.max(longestSubarray, right-left+1);
        }
        return longestSubarray;
    }

    @Test
    public void testPrintSubArrays(){
        int[] nums = {2,1,4,3};
        int n = nums.length;
        for(int i =0; i < n; i++) {
            System.out.println(" ");
            System.out.print("[");
            for(int j = i; j<n; j++){
                System.out.print(nums[j]);
            }
            System.out.print("]");
        }
    }



}
