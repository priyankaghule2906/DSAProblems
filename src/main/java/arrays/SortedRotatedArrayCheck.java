package arrays;

import org.junit.Assert;
import org.junit.Test;

public class SortedRotatedArrayCheck {

    @Test
    public void test(){
        Assert.assertTrue(check(new int[]{6,10,6}));
    }

    public boolean check(int[] nums) {
        int count=0;
        for(int i =0;i<nums.length-1;i++){
            if(nums[i] > nums[(i+1)]){
                count++;
            }
        }
        if(count==0 || (count==1 && nums[0]>=nums[nums.length-1])){
            return true;
        }
        return false;
    }
}
