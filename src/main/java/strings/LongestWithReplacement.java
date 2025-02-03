package strings;

import org.junit.Test;

public class LongestWithReplacement {

    @Test
    public void test(){
        System.out.println(characterReplacement("AABABBC", 2));
    }

    public int characterReplacement(String s, int k) {
        int n = s.length();
        int maxCountOfRplChar =0;
        int max=0;
        int left =0;
        int[] freq = new int[26];
        for(int right=0;right<n;right++){
            int i = s.charAt(right) - 'A';
            freq[i]++;
            maxCountOfRplChar = Math.max(maxCountOfRplChar, freq[i]);
            int window = right-left+1;
            if(window - maxCountOfRplChar >k){
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            max = Math.max(max, right-left+1);


        }
        return max;
    }
}
