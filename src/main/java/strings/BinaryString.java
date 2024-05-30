package strings;

import org.junit.Assert;
import org.junit.Test;

public class BinaryString {

    @Test
    public void testBinaryString() {

        String str = "1111";
        Assert.assertEquals(6, binarySubstring(4, str));
    }

    public static int binarySubstring(int a, String str) {
        // Your code here

        int count = 0;
        for (int i = 0; i < a; i++) {
            if (str.charAt(i) == '1') {
                for (int j = i + 1; j < a; j++) {
                    System.out.println(str.substring(j));
                    if (str.charAt(j) == '1') {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    int countSubStr(char str[], int n) {
        int m = 0; // Count of 1's in input string

        // Traverse input string and count of 1's in it
        for (int i = 0; i < n; i++) {
            if (str[i] == '1')
                m++;
        }
        // Return count of possible pairs among m 1's
        return m * (m - 1) / 2;
    }

}
