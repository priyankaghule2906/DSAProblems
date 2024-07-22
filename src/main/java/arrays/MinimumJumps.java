package arrays;

import org.junit.Test;

public class MinimumJumps {

    @Test
    public void test(){
        System.out.println(jump(new int[]{2,4,1,2,3,1,1,2}));

    }

    int jump(int[] nums) {
        int totalJumps = 0;

        // destination is last index
        int destination = nums.length - 1;

        int coverage = 0, lastJumpIdx = 0;

        // Base case
        if (nums.length == 1) return 0;

        // Greedy strategy: extend coverage as long as possible
        for (int i = 0; i < nums.length; i++) {

            coverage = Math.max(coverage, i + nums[i]);

            if (i == lastJumpIdx) {
                lastJumpIdx = coverage;
                totalJumps++;

                // check if we reached destination already
                if (coverage >= destination) {
                    return totalJumps;
                }
            }
        }

        return totalJumps;
    }

    static int minJumps(int[] arr, int n) {
        int farthest = 0;
        int curFarthest = 0;
        int jumps = 0;
        for(int i = 0; i < n - 1; i++) {
            curFarthest = Math.max(curFarthest, i + arr[i]);
            if(i == farthest) {
                farthest = curFarthest;
                jumps++;
            }
        }
        if(farthest >= n - 1)
            return jumps;
        return -1;
    }
}
