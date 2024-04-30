package arrays;

import org.junit.Assert;
import org.junit.Test;

public class MaxOccurringElementinRange {

    @Test
    public void test(){
        Assert.assertEquals(5, maxOccured(new int[]{1,5,9,13,21}, new int[]{15,8,12,20,30},5, 32));
    }

    public static int maxOccured(int L[], int R[], int n, int maxx){

        // using prefix sum method
        int[] freq = new int[1000000];

        for(int i=0;i<n;i++){
            freq[L[i]] += 1;
            freq[R[i]+1] -= 1;
        }

        // do the sum of prefix and simultaneously find the max

        int max =0;
        int index =0;
        for(int i=1;i<maxx;i++){
            freq[i] = freq[i] + freq[i-1];
            if(freq[i] > max){
                max = freq[i];
                index = i;
            }

        }
        return index;

        //  HashMap<Integer,Integer> map = new HashMap<>();

        //  for(int i=0;i<n;i++){

        //      int left = L[i];
        //      int right = R[i];

        //      for(int j=left;j<=right;j++){
        //          if(map.containsKey(j)){
        //              map.put(j, map.get(j)+1);
        //          } else {
        //              map.put(j, 1);
        //          }
        //      }

        //  }

        //  int maxValue = Integer.MIN_VALUE;
        //  int maxKey = 0;
        //     for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        //         if (entry.getValue() > maxValue) {
        //             maxValue = entry.getValue();
        //             maxKey = entry.getKey();
        //         }
        //     }

        //     return maxKey;

    }
}
