package dp;

import org.junit.Test;

public class DominoTromino {

    @Test
    public void test(){
        System.out.println(numTilings(4));
    }

    public int numTilings(int n) {
        int mod = 1_000_000_007;
        int[] dp = new int[n+1];
        dp[1] =1;
        dp[2] =2;
        dp[3] =5;

        for(int i=4;i<=n;i++){
            dp[i] = 2  * dp[i-1] + dp[i-3]  % mod;
        }
        return dp[n];
    }
}
