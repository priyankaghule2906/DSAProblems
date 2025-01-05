package recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {

    @Test
    public void test(){
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = subsets(nums);

        // Print the result
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }

    public List<List<Integer>> powerSet(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();  // List to store all subsets
        List<Integer> current = new ArrayList<>();  // Temporary list to store the current subset
        backtrack(0, nums.length, nums, current, ans,0);  // Start the recursive process
        return ans;  // Return the list of all subsets
    }

    void backtrack(int index, int n, int[] nums, List<Integer> current, List<List<Integer>> ans, int depth) {

        // Indentation for visualizing the depth of recursion
        String indent = "  ".repeat(depth);

        if (index == n) {
            System.out.println(indent + "Adding to result: " + current);
            ans.add(new ArrayList<>(current));
            return;
        }

        // Explore without including the current number
        backtrack(index + 1, n, nums, current, ans, depth + 1);

        // Explore including the current number
        System.out.println(indent + "Including: " + nums[index]);
        current.add(nums[index]);
        System.out.println(indent + "added " + current);
        backtrack(index + 1, n, nums, current, ans, depth + 1);

        // Backtrack by removing the current number
       // System.out.println(indent + "removing " + current.get(current.size() - 1));
        System.out.println(indent + "Excluding: " + nums[index]);
        current.remove(current.size() - 1);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        System.out.println("Starting backtracking...");
        backtrack(0, nums.length, nums, current, ans);
        return ans;
    }

    void backtrack(int index, int n, int[] nums, List<Integer> current, List<List<Integer>> ans) {
        // Debug statement: Entry point of the backtrack method


        if (index == n) {
            ans.add(new ArrayList<>(current));
            return;
        }



        current.add(nums[index]);
        backtrack(index + 1, n, nums, current, ans);

        current.remove(current.size() - 1);
        backtrack(index + 1, n, nums, current, ans);

    }

}
