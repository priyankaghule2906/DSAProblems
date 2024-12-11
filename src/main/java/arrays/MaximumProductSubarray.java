package arrays;

import org.junit.Test;

public class MaximumProductSubarray {

    @Test
    public void test(){
        int[] arr = new int[]{2,3,-2,-5,6,-1,4};
        System.out.println(getMaxProduct(arr));
    }

    public int getMaxProduct(int[] nums){
        int n = nums.length;

        int ans = nums[0];
        int max = nums[0];
        int min = nums[0];

        for(int i =1; i<n;i++){
            int num = nums[i];

            if(num <0 ){
                int temp = max;
                max = min;
                min = temp;
            }
             max = Math.max(num, max* num);

             min = Math.min(num, min*num);

             ans = Math.max(ans, max);
        }

        return ans;
    }
}
