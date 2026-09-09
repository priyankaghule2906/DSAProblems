package dsa2026.twopointer;

public class RemoveDuplicatesII {
    public static void main(String[] args){

        RemoveDuplicatesII obj = new RemoveDuplicatesII();
       // int n = obj.removeDuplicates(new int[]{1,1,1,2,2,3,4,4,4,5,5});

        System.out.println(obj.sortArrayByParity(new int[]{3,1,2,4}));
    }

    public int removeDuplicates(int[] nums){
        int k=0;
        for(int num: nums){
            if(k<2 || num != nums[k-2]){
                nums[k++] = num;
            }
        }
        return k;
    }

    public int[] sortArrayByParity(int[] nums) {
        if(nums == null || nums.length ==0) {return null;}

        int evenptr = 0;
        int oddptr = nums.length -1;

        for(int i=0;i<nums.length-1;i++){
            if(nums[i]%2 ==0){
                nums[evenptr++] = nums[i];
            } else {
                int temp = nums[oddptr];
                nums[oddptr] = nums[evenptr];
                nums[evenptr] = temp;
                oddptr--;
            }
        }
        return nums;
    }

}
