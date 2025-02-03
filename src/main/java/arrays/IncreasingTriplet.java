package arrays;

import org.junit.Test;

public class IncreasingTriplet {

    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) {
                first = num; // Update the smallest number
            } else if (num <= second) {
                second = num; // Update the second smallest number
            } else {
                // Found a number larger than both first and second
                return true;
            }
        }
        return false;
    }

    @Test
    public void test(){
        int[] arr = new int[]{2, 1, 5, 0, 4, 6};
        System.out.println(increasingTriplet(arr));
    }
}
