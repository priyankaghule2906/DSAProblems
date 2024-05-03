package arrays;

import org.junit.Test;

public class TrappingRainWater {

    @Test
    public void test(){

//        System.out.println(trap(new int[]{4,2,0,3,2,5},6));
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1},12));

    }

    static long trap(int arr[], int n){
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = arr[0];
        for(int i=1; i<n; i++){
            left[i] = Math.max(left[i-1],arr[i]);
        }

        right[n-1] =arr[n-1];
        for(int i=n-2; i>=0; i--){
            right[i] = Math.max(right[i+1],arr[i]);
        }

        long ans = 0;
        for(int i=0; i<n; i++){
            ans += Math.min(left[i],right[i])-arr[i];
        }
        return ans;
    }
    static long trappingWatergfg(int arr[], int n) {
        // Your code here
        int i =0;
        int j = n-1;
        int leftmax = 0;
        int rightmax =0;
        int result =0;

        while(i<j){
            // left highest bar and right highest bar

            leftmax = Math.max(leftmax, arr[i]);
            rightmax = Math.max(rightmax, arr[j]);

            // now take minimum of two

            if(leftmax < rightmax){
                result += arr[i]-leftmax;
                i++;
            } else {
                result += arr[j]-rightmax;
                j--;
            }
        }

        return result;

    }

    static long trappingWaters(int arr[], int n) {
        //optimal solution
        int res = 0;
        int lhb =arr[0]; int rhb = arr[n-1];
        int left =0; int right = n-1;

        while (left<=right){
            if(lhb<=rhb){
                if(arr[left]>=lhb){
                    lhb = arr[left];
                } else {
                    res += lhb - arr[left];
                }
                left++;
            } else {

                if(arr[right]>=rhb){
                    rhb = arr[right];
                } else {
                    res += rhb - arr[right];
                }
                right++;
            }
        }
        return res;
    }

    static long trappingWater(int arr[], int n) {
        // naive solution
        // find the lb, rb
        // find minimum water level min(lb,rb)
        // find trapped water tw = wl height[i]
        // sum up the trapped water
        int res =0;
        for(int i =1;i<n-1;i++){
            int lb = arr[i];
            // find the greatest left bar on left side
            for(int j =0;j<i;j++){
                if(arr[j]>lb){
                    lb = arr[j];
                }
            }
            // find the greatest right bar on right side
            int rb = arr[i];
            for(int j = i+1;j<n;j++){
                if(arr[j]>rb){
                    rb = arr[j];
                }
            }
            int wl = Math.min(lb, rb);
            int tw = wl-arr[i];
            res += tw;
        }
        return res;
    }
}
