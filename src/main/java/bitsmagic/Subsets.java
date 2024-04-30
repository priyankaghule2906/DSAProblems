package bitsmagic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Subsets {

    @Test
    public void testPowerSet(){
        System.out.println(subsets(new int[]{1, 2,3}));
    }

    HashSet<HashSet<Integer>> getSubSets(int[] arr, int n){
        HashSet<HashSet<Integer>> result = new HashSet<>();
        int subSetSize = 1 << n;

        for(int i=0; i<subSetSize; i++){
            HashSet<Integer> set = new HashSet();
            for(int bit=0; bit<n; bit++ ){
                System.out.println("left shit answer" + Integer.toBinaryString(i & (1 << bit)));
                if ((i & (1 << bit)) != 0) {
                    set.add(arr[bit]);
                }
                result.add(set);
            }

        }

        return  result;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        int n = nums.length;

        for (int i = (1<<n) ; i < (1<<n+1); ++i) {
            // generate bitmask, from 0..00 to 1..11
            String bitmask = Integer.toBinaryString(i).substring(1);
            System.out.println(bitmask);

            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') curr.add(nums[j]);
            }
            output.add(curr);
        }
        return output;
    }

}
