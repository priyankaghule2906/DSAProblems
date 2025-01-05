package recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetWithDuplicates {

    @Test
    public void test() {
        int[] arr = new int[]{1,2,2};

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if(index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // include the result
        current.add(nums[index]);


    }

}

