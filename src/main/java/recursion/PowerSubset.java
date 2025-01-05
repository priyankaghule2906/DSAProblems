package recursion;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PowerSubset {

    @Test
    public void  test(){
        int[] nums = new int[]{1,2,3};
        Assert.assertTrue(subsets(nums).size() == 8);

    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, new ArrayList<>(), nums, result);
        return result;
    }

    void backtrack(int index, ArrayList<Integer> current, int[] nums, List<List<Integer>> result) {
        System.out.println("\nDebug: Current subset: " + current);
        System.out.println("Debug: Current index: " + index);

        if(index == nums.length){

        }
    }



}
