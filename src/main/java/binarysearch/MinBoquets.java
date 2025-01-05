package binarysearch;

import org.junit.Test;

import java.util.Arrays;

public class MinBoquets {


    @Test
    public void test(){
        int[] nums ={7, 7, 7, 7, 13, 11, 12, 7};
        System.out.println(roseGarden(nums.length, nums, 3,2));
    }

    @Test
    public void testleet(){
        int[] nums ={1,10,3,10,2};
        System.out.println(minDays(nums, 3,1));
    }

    @Test
    public void testleet2(){
        int[] nums ={1,1,1,1};
        System.out.println(minDays(nums, 3,1));
    }

    public int roseGarden(int n, int[] nums, int k, int m) {
        if(n < m*k){
            return -1;
        }

        // find min and max range

        int min = Arrays.stream(nums).min().getAsInt();
        int max = Integer.MIN_VALUE;

        for(int i=0;i<n;i++) {
            max = Math.max(max, nums[i]);
            min = Math.max(min, nums[i]);
        }
        int result = 0;

        for(int i=min; i<=max;i++){
            if(canBoquetsBeMade(nums, k,m, i)){
                return i;
            }
        }

        return -1;


    }

    boolean canBoquetsBeMade(int[] arr, int k, int m, int day){
        int totalBoquetsMade =0;
        int adjBCount = 0;

        for(int i=0;i<arr.length;i++){
            if(arr[i]<=day){
                adjBCount++;
            } else {
                totalBoquetsMade += (adjBCount / k);
                adjBCount =0;
            }

        }

        totalBoquetsMade += (adjBCount / k);

        return (totalBoquetsMade >= m) ;

    }

    public int minDays(int[] bloomDay, int m, int k) {

        int n = bloomDay.length;
        if(n < m*k) { return -1;}

        int min = Arrays.stream(bloomDay).min().getAsInt();
        int max = Arrays.stream(bloomDay).max().getAsInt();

        int low = min;
        int high = max;
        int result = -1;

        while(low <= high){

            int mid = low + (high -low) /2;

            if(isPossible(bloomDay, m, k, mid)){
                result = mid;
                high = mid -1;
            } else {
                low = mid +1;
            }
        }

        return result>= m ? result : -1;



    }

    boolean isPossible(int[] arr, int m, int k, int day){
        int numberOfBoquets = 0;
        int adjacentFlowers =0;

        for(int bloomDay : arr){
            if(bloomDay <= day){
                adjacentFlowers++;
            } else {
                numberOfBoquets += (adjacentFlowers/k);
                adjacentFlowers =0;
            }
        }
        numberOfBoquets += (adjacentFlowers/k);

        return numberOfBoquets >= m;


    }
}
