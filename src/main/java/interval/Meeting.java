package interval;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Meeting {

    @Test
    public void test() {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};

        Assert.assertEquals(4, maxMeetings(start,end));
    }

    public int maxMeetings(int[] start, int[] end) {
        int count = 1;

        List<int[]> meetings = new ArrayList<>();
        int n = start.length;
        for (int i = 0; i < n; i++) {
            meetings.add(new int[]{start[i], end[i]});
        }
        Collections.sort(meetings, new MeetingComparator());

        // end time of the first meeting
        int limit = meetings.get(0)[1];

        // now we check that if the start time of the meeting is greater than last meeting if yes count++


        for(int i=1;i<n;i++){
            if(meetings.get(i)[0] > limit){
                count++;
                // change the end time to compare the next meeting slot
                limit = meetings.get(i)[1];
            }
        }
        return count;
    }

    class MeetingComparator implements Comparator<int[]> {

        @Override
        public int compare(int[] start, int[] end) {
            return start[1] - end[1];
        }
    }
}
