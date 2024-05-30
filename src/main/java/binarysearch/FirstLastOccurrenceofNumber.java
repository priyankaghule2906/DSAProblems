package binarysearch;

import org.junit.Test;

import java.util.Arrays;

public class FirstLastOccurrenceofNumber {

    @Test
    public void test(){
        int[] arr = {5,7,7,8,8,10};
        // answer has to be 3 and 7

        System.out.printf(Arrays.toString(getFirstLast(arr, 8)));
    }


    public static int[] getFirstLast(int[] nums, int target){
        int[] result = new int[2];
        int first = findOccurrence(nums, target, true);
        int last = findOccurrence(nums, target, false);
        result[0] = first;
        result[1] = last;
        return result;
    }

    public static int findOccurrence(int[] nums, int target, boolean first){
        int low =0;
        int high = nums.length;
        int i =-1;
        while (low<=high){
            int mid = (low+high)/2;
            if(nums[mid]>target){
                high = mid -1;
            } else if(nums[mid]<target){
                low = mid +1;
            } else {
                i = mid;
                if(first == true){
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            }
        }


        return i;
    }
}
