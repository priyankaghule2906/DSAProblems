package arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ReverseArrayInGroups {

    @Test
    public void testReverse(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
//        reverse(list,0, 10);
        reverseInGroups(list,10,3);
        System.out.println(list);
    }
    public void reverseInGroups(ArrayList<Integer> arr, int n, int k) {
        // code here
        int i=0;
        int j = k-1;

        while (i<n && j<n){
            reverse(arr,i,j);
            i+=k;
            j=j+k;
        }
        // last elements
        reverse(arr,i,n);

    }

    static void reverse(ArrayList<Integer> arr, int left, int right){

        while(left<right){
            int temp = arr.get(left);
            arr.set(left, arr.get(right));
            arr.set(right, temp);
            left++;
            right--;
        }

    }
}
