package interval;

import org.junit.Test;

import java.util.Arrays;

public class NonOverlappingInterval {

    @Test
    public void test(){
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(eraseOverlapIntervals(intervals1)); // Output: 1

        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println(eraseOverlapIntervals(intervals2)); // Output: 2

        int[][] intervals3 = {{1, 2}, {2, 3}};
        System.out.println(eraseOverlapIntervals(intervals3));
    }


    public int eraseOverlapIntervals(int[][] intervals) {
        int count=0;
        // sort the matrix on the basis of end date
        Arrays.sort(intervals, (a,b)-> a[1] - b[1]);

        int prevEnd = intervals[0][1];
        for (int i=1;i<intervals.length;i++){
            // start of next
            int start = intervals[i][0];
            if(start < prevEnd){
                count++;
            }
                prevEnd = intervals[i][1];

        }
        return count;
    }
}
