package arrays;

import org.junit.Test;

import java.util.Arrays;

public class MissingAndRepeating {

    @Test
    public void test(){
        int[] arr1 = {2,2 };
        int[] twoElement = findTwoElement(arr1, arr1.length);
        System.out.println(Arrays.toString(twoElement));

        int[] arr2 = {4, 3, 6, 2, 1, 1};
        int[] twoElement1 = findTwoElement(arr2, arr2.length);
        System.out.println(Arrays.toString(twoElement1));

    }

    int[] findTwoElement(int arr[], int n) {
        int[] ans = new int[2];
        int missing = ( n * (n+1) )/2;
        for (int i=0;i<n;i++){
            int val = Math.abs(arr[i]);
            if(arr[val-1]>0){
                arr[val-1] = -arr[val-1];
                missing= missing - val;
                ans[0] = missing;
            } else {
                ans[1] = val;
            }
        }

        return ans;
    }

}
