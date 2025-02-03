package stacksqueue;

import org.junit.Test;

import java.util.*;

public class DailyTemp {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int currDay = 0; currDay < temperatures.length; currDay++) {
            while (
                    !stack.isEmpty() &&
                            temperatures[currDay] > temperatures[stack.peek()]
            ) {
                int prevDay = stack.pop();
                ans[prevDay] = currDay - prevDay;
            }
            stack.add(currDay);
        }
        return ans;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
    }
}
