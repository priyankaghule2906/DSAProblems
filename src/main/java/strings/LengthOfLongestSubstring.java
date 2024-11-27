package strings;

import org.junit.Test;

import java.util.HashSet;

public class LengthOfLongestSubstring {

    @Test
    public void test() {
        String s = "abpriyankaghule";
        System.out.println(lengthOfLongestSubstring(s));


    }

    public int lengthOfLongestSubstring1(String s) {
        int max =0;
        int start = 0, end =0;
        int n = s.length();
        HashSet<Character> set = new HashSet<>();
        while(end < n){
            if(!set.contains(s.charAt(end))){
                max = Math.max(max, end-start+1);
                set.add(s.charAt(end));
                end++;
            } else {
                set.remove(s.charAt(start));
                start++;
            }
        }
        return max;
    }

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
}
