package bitsmagic;

import org.junit.Assert;
import org.junit.Test;

public class CheckWhetherKthBitIsSetOrNot {

    @Test
    public void test() {
        Assert.assertFalse(checkKthBitNaiveApproach(4, 0));
        Assert.assertTrue(checkKthBitNaiveApproach(4, 2));
        Assert.assertFalse(checkKthBitNaiveApproach(500, 3));

        Assert.assertFalse(checkKthBitEfficientApproach(4, 0));
        Assert.assertTrue(checkKthBitEfficientApproach(4, 2));
        Assert.assertFalse(checkKthBitEfficientApproach(500, 3));
    }


    private static boolean checkKthBitNaiveApproach(int n, int k) {
//        Convert the integer N into its binary representation.
//        Check if the Kth bit (from the right) in the binary representation is set or not.
//        Return true if the Kth bit is set, false otherwise.

        String binaryString = Integer.toBinaryString(n);
        int index = binaryString.length() - 1 - k;
        if (index < 0 || index >= binaryString.length())
            return false;

        char c = binaryString.charAt(index);
        return binaryString.charAt(index) == '1';

    }


    private static boolean checkKthBitEfficientApproach(int n, int k) {
//        If we right shift n by k-1, we get the last bit as 1 if the Kth bit is set else 0

        return ((n >> k) & 1) == 1;

    }
}
