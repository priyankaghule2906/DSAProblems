package binarysearch;

import org.junit.Test;

public class FloorInSortedArray {

    @Test
    public void test(){

        long[] arr = {71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112};
        System.out.println(findFloor(arr,42,68));

    }

    static int findFloor(long[] arr, int N, int x) {
        int left = 0, right = N - 1;
        int floorIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] <= x) {
                floorIndex = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return floorIndex;
    }
}
