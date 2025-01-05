package dp;


import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
*
* 403. Frog Jump
Hard
Topics
Companies
A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.

If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.



Example 1:

Input: stones = [0,1,3,5,6,8,12,17]
Output: true
Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

Input: stones = [0,1,2,3,4,8,9,11]
Output: false
Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.

*
* */
public class FrogJump403 {

    @Test
    public void test(){

        System.out.println(canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));

    }

    public boolean canCross(int[] stones) {
        // Map to store stone position -> possible jump lengths that can reach this position

        Map<Integer, HashSet<Integer>> map = new HashMap<>();

        // Initialize the HashMap for all stone positions
        for(int stone: stones){
            map.put(stone, new HashSet<>());
        }

        /*
        *  {
    0: [1],     // Initial position can only jump 1
    1: [],      // Empty initially
    3: [],
    5: [],
    6: [],
    8: [],
    12: [],
    17: []      // Target position
}
        * */

        map.get(0).add(1);


        for(int i=0;i<stones.length-1;i++){
            // For each possible jump length at current position
            int stone = stones[i];
            HashSet<Integer> lastJumps = map.get(stone);
             for(int jump : lastJumps){
                int reach = stone + jump;
                 // If we can reach a stone with this jump
                if(map.containsKey(reach)){
                    // Add possible next jumps
                    if(jump-1>0) map.get(reach).add(jump-1);
                    map.get(reach).add(jump);
                    map.get(reach).add(jump+1);
                }
            }
        }

        // Check if last stone has any possible jumps (meaning it can be reached)
        return !map.get(stones[stones.length - 1]).isEmpty();
    }

//    public boolean canCross(int[] stones) {
//        // Map to store stone position -> possible jump lengths that can reach this position
//        HashMap<Integer, HashSet<Integer>> dp = new HashMap<>();
//
//        // Initialize the HashMap for all stone positions
//        for (int stone : stones) {
//            dp.put(stone, new HashSet<>());
//        }
//
//        // First jump must be 1
//        dp.get(0).add(1);
//
//        // For each stone position
//        for (int i = 0; i < stones.length - 1; i++) {
//            int currentStone = stones[i];
//
//            // For each possible jump length at current position
//            for (int jump : dp.get(currentStone)) {
//                int reach = currentStone + jump;
//
//                // If we can reach a stone with this jump
//                if (dp.containsKey(reach)) {
//                    // Add possible next jumps
//                    if (jump - 1 > 0) dp.get(reach).add(jump - 1);
//                    dp.get(reach).add(jump);
//                    dp.get(reach).add(jump + 1);
//                }
//            }
//        }
//
//        // Check if last stone has any possible jumps (meaning it can be reached)
//        return !dp.get(stones[stones.length - 1]).isEmpty();
//    }
}
