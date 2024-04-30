package arrays;

import org.junit.Assert;
import org.junit.Test;

public class MaximumIndexDiff {

    @Test
    public void test(){
        Assert.assertEquals(6, maxIndexDiff(new int[]{34, 8, 10, 3, 2, 80, 30, 33, 1}));
    }

    public static int maxIndexDiff(int a[]){

        // create an array to store the min element from left and max elements from right
        int n = a.length;
        int []maxRight = new int[n];
        int []minLeft = new int[n];


        // start storing elements

        minLeft[0] = a[0];
        for(int i=1;i<n;i++){
            minLeft[i] = Math.min(minLeft[i-1], a[i]);
        }

        maxRight[n-1] = a[n-1];
        for(int i=n-2;i>=0;i--){
            maxRight[i] = Math.max(maxRight[i+1], a[i]);
        }

        // check for maxDiff

        int ans = 0;
        int i=0,j=0;
        while(i<n && j<n){
            if(minLeft[i]<=maxRight[j]){
                ans = Math.max(ans, j-i);
                j++;
            } else  {
                i++;
            }
        }

        return ans;
    }
}
