package stacksqueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {

    @Test
    public void testNGE(){

        long[] arr = {18,7,6,12,15};
        System.out.println(Arrays.toString(nextLargerElement(arr, 5)));
    }

    public static long[] nextLargerElement(long[] arr, int n)
    {
        Stack<Long> stack = new Stack<>();
        long[] result = new long[n];
        for(int i=n-1;i>=0;i--){
            //System.out.println(arr[i]);
            while (!stack.isEmpty() && stack.peek()<=arr[i]){
                stack.pop();
            }

            if(stack.isEmpty()){
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }

            stack.push(arr[i]);
        }
        return result;
    }

    public static long[] NGE(long[] arr, int n){
        Stack<Integer> stack = new Stack<>();
        long[] result = new long[n];
        for(int i=0;i<n;i++){

            while (!stack.isEmpty() && arr[i] >= stack.peek()){
                result[i] = arr[stack.pop()];

            }
            stack.push(i);
        }
        return null;
    }
}
