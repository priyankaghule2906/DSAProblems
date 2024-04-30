package arrays;

import org.junit.Test;

public class BettingGame {

    @Test
    public void test(){
        System.out.println(betBalance("WWLWLLL"));
    }

    static int betBalance(String result)
    {
        // code here
        int previousBetAmt = 1;
        int balance = 4;

        for(char c: result.toCharArray()){
            if(balance<previousBetAmt){
                return -1;
            }
            if(c =='W'){
                balance +=previousBetAmt;
                previousBetAmt  = 1;
            } else if (c =='L'){
                balance -= previousBetAmt;
                previousBetAmt *= 2;
            }
            System.out.println("balance "+balance + " previousBetAmt "+previousBetAmt);

        }
        return balance;
    }
}
