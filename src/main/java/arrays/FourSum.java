package arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    @Test
    public void test(){

//        System.out.println(fourSum(new int[]{1,0,-1,0,-2,2}, 0));
        System.out.println(fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296));
    }

//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        List<List<Integer>> result = new ArrayList<>();
//        if(nums==null || nums.length<4) return result;
//        int n = nums.length;
//
//        Arrays.sort(nums);
//        for(int i=0;i<n;i++){
//            for(int j=i+1;j<n;j++){
//                int left = j+1;
//                int right = n-1;
//                while(left<right){
//                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
//                    if(sum == target){
//                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
//                        left++;
//                        right--;
//                    } else if (sum < target) {
//                        left++;
//                    } else {
//                        right--;
//                    }
//                }
//            }
//        }
//
//        return  result;
//    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if(nums==null || nums.length<4) return result;

        int n = nums.length;
        Arrays.sort(nums);
        for(int i=0;i<n;i++){
            // to skip duplicates
            if(i>0 && nums[i]==nums[i-1]) continue;

            for(int j=i+1;j<n;j++){
                // to skip duplicates
                if(j > i+1 && (nums[j]== nums[j-1])) continue;

                int left = j+1;
                int right = n-1;

                while(left < right) {
                    long sum=nums[i]+nums[j];
                    sum+=nums[left];
                    sum+=nums[right];
                    if(sum < target) {
                        left++;

                    } else if(sum > target) {
                        right--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;

                        while(left < right && nums[left] == nums[left-1]) left++;
                        while(left < right && nums[right] == nums[right+1]) right--;
                    }
                }
            }

        }

        return result;
    }


}
