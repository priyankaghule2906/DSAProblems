package arrays;

import org.junit.Test;

public class MajorityElements {

    @Test
    public void test(){
        System.out.println(majorityElement(new int[]{3,1,3,3,2}, 5));
    }

    static int majorityElement(int a[], int size)
    {
        int element = a[0];
        int count = 1;

        for(int i=1;i<size;i++){
            if(element == a[i]){
                count++;
            } else {
                count--;

                if(count == 0){
                    element = a[i];
                    count = 1;
                }
            }

        }
        if(count > size/2){
            return element;
        }
        return -1;
    }

    static void findMajority(int arr[], int n)
    {
        int maxCount = 0;
        int index = -1; // sentinels
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j])
                    count++;
            }

            // update maxCount if count of
            // current element is greater
            if (count > maxCount) {
                maxCount = count;
                index = i;
            }
        }

        // if maxCount is greater than n/2
        // return the corresponding element
        if (maxCount > n / 2)
            System.out.println(arr[index]);

        else
            System.out.println("No Majority Element");
    }
}
