package dsa2026.twopointer;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
//        System.out.println(new Solution().pancakeSort(new int[]{3,2,4,1}));
//        System.out.println(new Solution().bagOfTokensScore(new int[]{33,4,28,24,96},35));
//        System.out.println(new Solution().findClosestElements(new int[]{1,2,3,4,5},4,3));
//        System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(sol.maxFreq("aababcaab", 2, 3,4));
        System.out.println(sol.findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));
    }

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) {
            return result;
        }

        // Frequency of words we need
        Map<String, Integer> need = new HashMap<>();

        for (String word : words) {
            need.put(word, need.getOrDefault(word, 0) + 1);
        }

        // Try every possible word boundary
        for (int offset = 0; offset < wordLen; offset++) {

            int left = offset;
            int right = offset;

            Map<String, Integer> window = new HashMap<>();
            int count = 0;

            while (right + wordLen <= s.length()) {

                String word = s.substring(right, right + wordLen);
                right += wordLen;

                // Word is not required
                if (!need.containsKey(word)) {
                    window.clear();
                    count = 0;
                    left = right;
                    continue;
                }

                // Add word to window
                window.put(word, window.getOrDefault(word, 0) + 1);
                count++;

                // Too many occurrences of this word
                while (window.get(word) > need.get(word)) {

                    String leftWord = s.substring(left, left + wordLen);

                    window.put(
                            leftWord,
                            window.get(leftWord) - 1
                    );

                    left += wordLen;
                    count--;
                }

                // We have exactly all words
                if (count == wordCount) {
                    result.add(left);

                    // Move left forward to search for next window
                    String leftWord = s.substring(left, left + wordLen);

                    window.put(
                            leftWord,
                            window.get(leftWord) - 1
                    );

                    left += wordLen;
                    count--;
                }
            }
        }

        return result;
    }

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {

        Map<Character, Integer> freq = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        int start = 0;
        int maxFreq = 0;

        for (int end = 0; end < s.length(); end++) {

            char c = s.charAt(end);
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            // Keep window size = minSize
            if (end - start + 1 > minSize) {
                char left = s.charAt(start);

                freq.put(left, freq.get(left) - 1);

                if (freq.get(left) == 0) {
                    freq.remove(left);
                }

                start++;
            }

            // Window has exactly minSize characters
            if (end - start + 1 == minSize &&
                    freq.size() <= maxLetters) {

                String sub = s.substring(start, end + 1);

                count.put(sub, count.getOrDefault(sub, 0) + 1);

                maxFreq = Math.max(maxFreq, count.get(sub));
            }
        }

        return maxFreq;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int start = 0, besti = 0; int bestj = -1;

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

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left =0;
        int right = arr.length-1;
        List<Integer> result = new ArrayList<>();

        while(right - left +1 < k){
            if(Math.abs(arr[left] - x) <= Math.abs(arr[right]-x)) {
                right--;
            } else {
                left++;
            }
        }

        for(int i = left; i<= right;i++ ){
            result.add(arr[i]);
        }
        return result;
    }




    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int maxScore = 0;
        int left =0;
        int right = tokens.length-1;
        int score = 0;
        while(left <= right) {
            // face up
            if(power >= tokens[left]){
                power-=tokens[left];
                score+=1;
                maxScore = Math.max(score, maxScore);
                left++;
            } else if(score > 0){
                // face down
                power+=tokens[right];
                score-= score;
            } else {
                break;
            }
        }
        return maxScore;
    }




    public String reverseWords(String s) {
        // Code here
        String[] arr = s.split("\\.");

        int left =0;
        int right = arr.length-1;

        while(left < right) {
            System.out.println("arr[left]" + arr[left]);
            String temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            System.out.println("arr[left]" + arr[left]);
            left++;
            right--;
        }

        return String.join("/src/main/docs/dsa2026/twopointer/Solution.java", arr);
    }
    /**
     * sort like bubble-sort i.e. sink the largest number to the bottom at each round.
     */
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> ans = new ArrayList<>();

        for (int valueToSort = A.length; valueToSort > 0; valueToSort--) {
            // locate the position for the value to sort in this round
            int index = this.find(A, valueToSort);

            // sink the value_to_sort to the bottom,
            // with at most two steps of pancake flipping.
            if (index == valueToSort - 1)
                continue;
            // 1). flip the value to the head if necessary
            if (index != 0) {
                ans.add(index + 1);
                this.flip(A, index + 1);
            }
            // 2). now that the value is at the head, flip it to the bottom
            ans.add(valueToSort);
            this.flip(A, valueToSort);
        }

        return ans;
    }

    protected void flip(int[] sublist, int k) {
        int i = 0;
        while (i < k / 2) {
            int temp = sublist[i];
            sublist[i] = sublist[k - i - 1];
            sublist[k - i - 1] = temp;
            i += 1;
        }
    }

    protected int find(int[] a, int target) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == target)
                return i;
        return -1;
    }
}