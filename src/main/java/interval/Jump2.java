package interval;

import org.junit.Test;

public class Jump2 {

    @Test
    public void testMinJumps(){
        System.out.println(jump(new int[]{2,3,1,4,1,1,1,2}));
    }

    public int jump(int[] nums) {
        int leftRange =0, rightRange =0;
        int n= nums.length;
        int minJumps =0;

        while(rightRange < n-1) {
            int farthest =0;
            for(int i=leftRange;i<=rightRange;i++){
                farthest = Math.max(farthest, i+nums[i]);
            }
            leftRange = rightRange+1;
            rightRange = farthest;
            minJumps++;
        }

        return minJumps;

    }
}
