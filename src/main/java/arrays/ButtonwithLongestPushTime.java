package arrays;

import org.junit.Test;

public class ButtonwithLongestPushTime {

    @Test
    public void test(){
        int[][] arr = new int[][]{{1,2},{2,5},{3,9},{1,15}};
        System.out.println(buttonWithLongestTime(arr));
    }

    public int buttonWithLongestTime(int[][] events) {
        int max = events[0][1];  // 2
        int ans = 0;
        int n = events.length;
        for(int i=1;i<n;i++){
            int time = events[i][1];
            if(max<time-max){
                max = time-max;
                ans = events[i][0];
            }
        }
        return ans;
    }
}
