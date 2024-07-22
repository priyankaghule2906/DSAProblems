package arrays;

import org.junit.Test;

//gfg
public class MissingNumber {
    @Test
    public void test(){
        System.out.println(missingNumber(5, new int[]{1,2,3,5}));
    }

    int missingNumber(int n, int arr[]) {

        // Your Code Here
        int x1 = 0;
        int x2 = 1;



        // For xor of all the elements from 1 to n+1
        for (int i = 2; i <= n + 1; i++)
            x2 = x2 ^ i;

        return (x1 ^ x2);
    }
}
