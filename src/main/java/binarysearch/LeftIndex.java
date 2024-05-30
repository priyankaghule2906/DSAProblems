package binarysearch;

import org.junit.Test;

public class LeftIndex {

    @Test
    public void test(){
        System.out.println(leftIndex(10, new int[]{1,1,2,2,3,4,5,5,6,7}, 1));
//        System.out.println(leftIndex(7, new int[]{10,20,20,20,20,20,20}, 20));
    }

    static int leftIndex(int n, int arr[], int X)
    {
        int low =0;
        int high = n-1;
        int index = -1;
        while(low<=high){
            int mid = (low+ high) /2;

            if(X<=arr[mid]){
                index = mid;
                high = mid-1;
            } else if (X > arr[mid]){
                low = mid+1;
            }
        }
        return index;
    }
}
