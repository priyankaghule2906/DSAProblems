package arrays;

import org.junit.Test;

public class SumClosestToX {

    @Test
    public void test(){
        System.out.println(sumClosest(new int[]{2,3,6,8,16,20,22,22,25,38,43,43,48,52,65,67,74,75,82,85,86,89,95,96}, 310 ));
    }

    int[] sumClosest(int[] arr, int x) {
        // code here
        int[] ans = new int[2];
        int left =0;
        int right = arr.length -1;
        int min = Integer.MAX_VALUE;

        while(left<right){

            if(Math.abs(arr[left] +  arr[right] - x) < min){
                min = Math.abs(arr[left] +  arr[right] - x);
                ans[0] = arr[left];
                ans[1] = arr[right];
            }

            if(arr[left] + arr[right] > x) {
                right--;
            } else {
                left++;
            }

        }

        return ans;
    }
}
