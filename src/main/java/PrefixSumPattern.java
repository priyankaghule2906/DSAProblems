import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PrefixSumPattern {

    @Test
    public void testSubArraySum(){
        System.out.println(subarraySum(new int[]{1, 2, 4, 1, 3, 2, 5, 1}, 7));
    }
    public int subarraySum(int[] nums, int k) {
        // store the difference and frequency
        Map<Integer, Integer> map = new HashMap<>();
        // we say initially there is one entry whose sum is 0
        map.put(0, 1);
        int currentSum = 0, count =0;
        for(int i=0;i<nums.length;i++){
            currentSum+=nums[i];
            int difference = currentSum - k;
            if(map.containsKey(difference)){
                count+=map.get(difference);
            }
            map.merge(currentSum, 1, Integer::sum);
        }
        return count;
    }
}
