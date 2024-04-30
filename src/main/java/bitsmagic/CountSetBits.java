package bitsmagic;

import org.junit.Test;

import java.util.Arrays;

public class CountSetBits {

    @Test
    public void test(){
        int[] countedBits = getTotalCountOfSetBits(14);
        Arrays.stream(countedBits).forEach(System.out::println);
    }

    @Test
    public void test1(){
        System.out.println(countSetBits(14));
    }

    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++)
        {
            f[i] = f[i >> 1] + (i & 1);
        }
        return f;
    }

    int[] getTotalCountOfSetBits(int n){

        int[] counts = new int[n+1];
        for(int i=1;i<=n;i++){
            counts[i] = counts[i >> 1] + (i&1);
        }
        return counts;
    }


    public static int countSetBits(int n){
        n++;
        int i = 1;
        int j = 2;
        int sum = 0;

        while (i <= n) {
            System.out.println("i"+i);
            System.out.println("j"+j);
            sum += (n / j) * i;
            int k = (n % j) - i;
            if (k > 0) {
                sum += k;
            }
            i <<= 1;
            j <<= 1;
            System.out.println("----------------------------");
            System.out.println("i"+i);
            System.out.println("j"+j);
        }

        return sum;
    }
}
