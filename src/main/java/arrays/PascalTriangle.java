package arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    // leet code 118


    @Test
    public void testGeneratePT(){

        //System.out.println(generatePascalTriangle(4));
        generatePascalTriangle(5);
    }

    // leetcode 118
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<numRows;i++){
            List<Integer> row = new ArrayList<>();
            for(int j=0;j<=i;j++){
                if(j==0){
                    row.add(1);
                } else if(j==i){
                    row.add(1);
                } else {
                    // access previous row
                    List<Integer> prev = list.get(i-1);
                    row.add(prev.get(j-1) + prev.get(j));
                }

            }
            list.add(row);
        }
        return list;
    }

    public static void generatePascalTriangle(int n){

        for (int line = 0; line < n; line++)
        {
            // Every line has number of
            // integers equal to line number
            for (int i = 0; i <= line; i++) {
                System.out.print(binomialCoeff
                        (line, i) + " ");
            }

            System.out.println();
        }

    }

    public static int binomialCoeff(int n, int r){
        // n C r = n! / r! (n-r)!  could be simplied as follows
        // 5 C 3 = 5*4*3/3*2*1; instead of 5!/3!*(2!)

        int res = 1;
        for(int i=0;i<r;i++){
            res = res*(n-i);
            res = res/(i+1);
        }

        return res;
    }

}
