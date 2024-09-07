package arrays.slidingwindow;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class LargestSubArrayLengthWith0Sum {

    @Test
    public void test() {
        int a[] = {9, -3, 3, -1, 6, -5};
        System.out.println(maxLenO(a, a.length));
    }


    int maxLen(int arr[], int n) {
        // brute force solution
        int max = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum = sum + arr[j];
                if(sum ==0){
                    max = Math.max(max, j-i+1);
                }
            }
        }
        return max;
    }

    int maxLenO(int arr[], int n) {
        // optimal solution
        int max = 0;
        int currSum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
           currSum +=arr[i];

           if(currSum ==0){
               max = i+1;
           }

           if(map.containsKey(currSum)){
               max = Math.max(max, i - map.get(currSum));
           } else {
               map.put(currSum,i);
           }
        }


        return max;
    }

}
