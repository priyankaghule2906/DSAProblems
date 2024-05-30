package binarysearch;

import org.junit.Test;

public class NthRootOfANum {

    @Test
    public void test(){
        System.out.println(NthRoot(4, 4096));
    }

    public int NthRoot(int n, int m)
    {
        int low=1, high=m;

        while(low<=high){
            int mid = (low+high) /2;
            int pow = (int)Math.pow(mid, n);
            if(pow == m){
                return mid;
            } else if(pow>m){
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return -1;
    }

    public int getPower(int mid, int n, int m){
        int ans =1;
        for(int i=1;i<=n;i++){
            ans = ans*mid;
            if(ans>m)  return 2;
        }

        if(ans == m){
            return 1;
        }
        return 0;
    }
}
