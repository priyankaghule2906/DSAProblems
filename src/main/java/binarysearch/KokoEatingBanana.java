package binarysearch;

import org.junit.Test;

import java.util.Arrays;

public class KokoEatingBanana {

    @Test
    public void test(){
        int arr[] = {3,6,7,11};
//        System.out.println(minEatingSpeed(arr, 4, 8));
        System.out.println(minEatingSpeed(arr, 8));

    }

    // brute force
    public static int minEatingSpeed(int[] arr, int n, int hour){
        // find the max from the given array
        int max = 0;
        int k=-1;
        for(int i=0;i<n;i++){
            if(arr[i]>max){
                max = arr[i];
            }
        }

        for(int i=1;i<max;i++){
           int time = 0;
           for(int j =0;j<n;j++){
                time += Math.ceil((double) arr[j]/i);
           }
//            System.out.println("i "+i + " time "+time);
           if(time <=hour) {
               return i;
           }
        }

        return k;
    }



    // optimal solution

    public int minEatingSpeed(int[] piles, int h) {
        int low =1;
        int high = Arrays.stream(piles).max().getAsInt();
        int res = high;

        while (low<=high){
            int mid = (low + high) /2;
            if(canEatInHours(piles, h, mid)){
                high = mid-1;
                res = Math.min(res, mid);
            } else {
                low = mid +1;
            }
        }

        return res;
    }

    public boolean canEatInHours(int[] arr, int h, int tot){
        int time =0;
        for(int i=0;i<arr.length;i++){
            time += Math.ceil((double) arr[i]/tot);
        }
        return time<=h;
    }
}
