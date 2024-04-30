package arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondLargest {

    @Test
    public void testLargestAndSecondLargest(){
//        int[] arr = {1,2,3,4,5};
        int[] arr = {2,1,2};
        ArrayList<Integer> largestAndSecondLargest = largestAndSecondLargest(3, arr);
        System.out.println(largestAndSecondLargest.toString());
    }



    public static ArrayList<Integer> largestAndSecondLargest(int sizeOfArray, int arr[])
    {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<sizeOfArray;i++){
            if(arr[i]>max){
                secondMax = max;
                max = arr[i];
            }
            else if (arr[i] > secondMax && arr[i] != max) {
                secondMax = arr[i];
            }

        }
        list.add(max);
        list.add(secondMax);
        return (ArrayList<Integer>) list;
    }



}
