package slidingwindow;

import org.junit.Test;

import java.util.ArrayList;

public class FindIndexesOfSubarrayWithGivenSum {

    @Test
    public void test() {
        System.out.println(subarraySum(new int[]{1,4,20,3,10,5},6,33));
//        System.out.println(subarraySum(new int[]{1, 0}, 2, 0));
    }

    static ArrayList<Integer> subarraySum(int[] arr, int n, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        int currentSum = arr[0], start = 0, i;

        for (i = 1; i <= n; i++) {
            while (currentSum > sum && start < i - 1) {
                currentSum = currentSum - arr[start];
                start++;
            }
            if (currentSum == sum) {
                int p = i - 1;
                list.add(start);
                list.add(p);
                return list;
            }
            if (i < n)
                currentSum = currentSum + arr[i];
        }

        list.add(-1);
        return list;
    }

}


