package bitsmagic;

import org.junit.Test;

public class BitFlips {

    @Test
    public void testfilpsCount(){
        System.out.println(countBitflips(10, 20));
    }

    static int countBitflips(int a, int b){
        int xor = a ^ b;
        int count = 0;

        while (xor!=0){
            System.out.println("res   "+ Integer.toBinaryString(xor) );
            System.out.println("res-1 " + Integer.toBinaryString(xor-1));
            System.out.println("& res " + Integer.toBinaryString(xor & (xor-1)));
            xor = xor & (xor-1);
            count ++;
        }
        return count;
    }
}
