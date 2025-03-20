package strings;

import org.junit.Test;

public class PalindromCheck {

    @Test
    public void test(){
        System.out.println(isPalindrome("tab a cat"));
    }
    public boolean isPalindrome(String s) {
        int left =0;
        int right = s.length()-1;

        while(left<right){
            if(!Character.isLetterOrDigit(left)){
                left++;
            } else if (!Character.isLetterOrDigit(right)){
                right--;
            } else if (s.charAt(left) != s.charAt(right)){
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}
