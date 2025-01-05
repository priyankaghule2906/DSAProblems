package recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    @Test
    public void test(){

        int[] candidates = {1,2,3};
        int target = 3;

        System.out.println("Finding combinations for candidates: " +
                Arrays.toString(candidates) + " with target: " + target);
        System.out.println("-----------------------------------------------");

        List<List<Integer>> result = combinationSum(candidates, target);

        System.out.println("-----------------------------------------------");
        System.out.println("Final Result: " + result);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result, 0);
        return result;
    }

    private void backtrack(int[] candidates, int remain, int start,
                           List<Integer> current, List<List<Integer>> result, int depth) {
        String indent = "  ".repeat(depth);  // For pretty printing

        // Log current state
        System.out.println(indent + "Current combination: " + current +
                ", Remaining target: " + remain +
                ", Starting index: " + start);

        // Base case 1: If remaining sum becomes negative
        if (remain < 0) {
            System.out.println(indent + "❌ Sum exceeded target, backtracking...");
            return;
        }

        // Base case 2: If remaining sum becomes zero (found valid combination)
        if (remain == 0) {
            System.out.println(indent + "✅ Found valid combination: " + current);
            result.add(new ArrayList<>(current));
            return;
        }

        // Try each candidate starting from 'start' index
        for (int i = start; i < candidates.length; i++) {
            int currentNum = candidates[i];
            System.out.println(indent + "👉 Trying candidate: " + currentNum);

            // Add current number to combination
            current.add(currentNum);
            System.out.println(indent + "➕ Added " + currentNum +
                    " to combination: " + current);

            // Recursive call with reduced remaining sum
            System.out.println(indent + "🔄 Making recursive call with remain = " +
                    (remain - currentNum));
            backtrack(candidates, remain - currentNum, i, current, result, depth + 1);

            // Backtrack by removing the last added number
            System.out.println(indent + "⬅️ Backtracking: removing " + currentNum +
                    " from combination");
            current.remove(current.size() - 1);
        }
    }
}
