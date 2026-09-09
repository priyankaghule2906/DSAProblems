package dsa2026.twopointer;

import java.util.ArrayList;
import java.util.List;

public class PancakeSort {

    public static void main(String[] args) {
        System.out.println(pancakeSort(new int[]{3,2,4,1}));
    }

    public static List<Integer> pancakeSort(int[] arr) {


        List<Integer> result = new ArrayList<>();
        int n = arr.length;

        for (int size = n; size > 1; size--) {
            // Find index of max element in arr[0...size-1]
            int maxIdx = findMaxIndex(arr, size);

            // Already in correct position, skip this round
            if (maxIdx == size - 1) {
                continue;
            }

            // Step 1: bring max to the front (skip if already there)
            if (maxIdx != 0) {
                flip(arr, maxIdx + 1);
                result.add(maxIdx + 1);
            }

            // Step 2: flip it from front to its correct position at 'size - 1'
            flip(arr, size);
            result.add(size);
        }

        return result;

//        int size = arr.length;
//        List<Integer> result = new ArrayList<>();
//        for(int end=size;end>=0;end--){
//            // find largest element index
//            int maxIndex = findMaxIndex(arr, end);
//            if(maxIndex == end-1) {
//                continue;
//            }
//            // bring the larger element at front
//            if(maxIndex!=0) {
//                flip(arr, maxIndex+1);
//                result.add(maxIndex+1);
//            }
//
//            // position larger element at its position
//            flip(arr, end);
//            result.add(end);
//
//
//        }
//        return result;
    }
//
//    private static int findMaxIndex(int[] arr, int size){
//        int maxIndex = 0;
//        for(int i=1;i<size;i++){
//            if(arr[i] > arr[maxIndex]){
//                maxIndex = i;
//            }
//        }
//        return maxIndex;
//    }

//    public static void flip(int[] arr, int k){
//        int left = 0;
//        int right = k-1;
//        while(left < right){
//            int temp = arr[left];
//            arr[left] = arr[right];
//            arr[right] = temp;
//            left++;
//            right--;
//        }
//    }




        private static int findMaxIndex(int[] arr, int size) {
            int maxIdx = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIdx]) {
                    maxIdx = i;
                }
            }
            return maxIdx;
        }

        private static void flip(int[] arr, int k) {
            int left = 0, right = k - 1;
            while (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
    }

