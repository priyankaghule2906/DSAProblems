package binarysearch;

import org.junit.Test;

public class PeakElement {

    @Test
    public void test(){
        System.out.println(peakElement(new int[]{1,2,3}, 3));
    }

    public int peakElement(int[] arr,int n)
    {
        int low = 0;
        int high = n-1;
        int mid =0 ;

        while(low<=high){
            mid = low + (high-low)/2;

            if((mid== 0 || arr[mid-1] <= arr[mid]) && (mid == n-1 || arr[mid+1]<= arr[mid])){
                return mid;
            }

            if(mid  > 0 && arr[mid-1] > arr [mid]){
                high = mid-1;
            } else {
                low = mid+1;
            }
        }

        return mid;
    }
}
