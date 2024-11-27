package strings;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    @Test
    public void test(){
        String s = "timetopractice", t = "toc";
        System.out.println(minWindow(s,t));


    }

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Array to store the character frequency in `t`
        int[] targetFreq = new int[128];
        for (char c : t.toCharArray()) {
            targetFreq[c]++;
        }

        // Sliding window variables
        int left = 0, right = 0;
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;
        int required = 0;

        // Calculate how many unique characters with required frequencies in `t`
        for (int freq : targetFreq) {
            if (freq > 0) {
                required++;
            }
        }

        // Array to store the character frequency in the current window
        int[] windowFreq = new int[128];
        int formed = 0;

        while (right < s.length()) {
            // Add the character on the right to the window
            char c = s.charAt(right);
            windowFreq[c]++;

            // Check if this character completes the requirement
            if (targetFreq[c] > 0 && windowFreq[c] == targetFreq[c]) {
                formed++;
            }

            // Try to contract the window until it ceases to be "desirable"
            while (left <= right && formed == required) {
                // Update the minimum window
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minStart = left;
                }

                // Remove the character on the left from the window
                char leftChar = s.charAt(left);
                windowFreq[leftChar]--;
                if (targetFreq[leftChar] > 0 && windowFreq[leftChar] < targetFreq[leftChar]) {
                    formed--;
                }

                left++; // Shrink the window
            }

            right++; // Expand the window
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
    }

    public String minWindow1(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Map to store the character count in `t`
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        // Sliding window variables
        int left = 0, right = 0;
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;
        int required = targetMap.size();
        int formed = 0;

        // Map to store the character count in the current window
        Map<Character, Integer> windowMap = new HashMap<>();

        while (right < s.length()) {
            // Add the character on the right to the window
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            // Check if this character completes the requirement
            if (targetMap.containsKey(c) && windowMap.get(c).intValue() == targetMap.get(c).intValue()) {
                formed++;
            }

            // Try to contract the window until it ceases to be "desirable"
            while (left <= right && formed == required) {
                // Update the minimum window
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minStart = left;
                }

                // Remove the character on the left from the window
                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                if (targetMap.containsKey(leftChar) && windowMap.get(leftChar) < targetMap.get(leftChar)) {
                    formed--;
                }

                left++; // Shrink the window
            }

            right++; // Expand the window
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
    }
}
