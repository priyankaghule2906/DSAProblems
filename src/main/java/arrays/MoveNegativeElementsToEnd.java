package arrays;

import org.junit.Test;

import java.util.Arrays;

public class MoveNegativeElementsToEnd {

    @Test
    public void test(){
        int[] arr = {1, -1, 3, 2, -7, -5, 11, 6};
        segregateElements(arr, 8);

        System.out.println(Arrays.toString(arr));

    }


    public void segregateElements(int arr[], int n) {
        // Your code goes here

        int[] ans = new int[n];
        int k=0;
        for(int i=0;i<n;i++){
            if(arr[i] >= 0){
                ans[k++] = arr[i];
            }
        }
        for(int i=0;i<n;i++){
            if(arr[i] < 0){
                ans[k++] = arr[i];
            }
        }

        for(int i=0;i<n;i++){
            arr[i]= ans[i];
        }

    }
}
