package bitsmagic;

import org.junit.Test;

public class MaxAndValue {

    @Test
    public void testMaxAndValue(){
        System.out.println(findMaxAnd(new int[]{4,8,12,16}));
    }

    static int findMaxAnd(int[] arr){

        int res = 0;
        int count;

        for (int bits = 31; bits>=0; bits--){
            count  = checkBits(arr, res | (1 << bits));
            if(count >= 2){
                res = res | (1 << bits);
            }
        }


        return res;
    }

    static int checkBits(int[] arr, int pattern){
        int count =0;
        for (int i=0;i<arr.length;i++){
            if((pattern & arr[i]) == pattern){
                count++;
            }
        }

        return count;
    }
}
