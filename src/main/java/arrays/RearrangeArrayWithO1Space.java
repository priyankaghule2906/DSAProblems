package arrays;

import java.util.Arrays;

public class RearrangeArrayWithO1Space {

    static void rearrange(int arr[], int n)
    {
        // find the element at ith position
        // find its corresponding arr[arr[i]] value
        // modulo and multiply it
        // do the division to retrieve the original value

        for(int i=0;i<n;i++){
            long element = arr[i];
            long opElement = arr[(int)element];
            long formula = (opElement%n) * n;

            arr[i] += formula;
        }

        for(int i=0;i<n;i++){
            arr[i] /=n;
        }
    }

    // A utility function to print
    // contents of arr[0..n-1]
    static void printArray(int arr[], int n)
    {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

    // Driver code
    public static void main(String[] args)
    {
        int arr[] = { 2, 0, 1, 4, 5, 3 };
        int n = arr.length;

        System.out.println("Given array is : ");
        printArray(arr, n);

        rearrange(arr, n);

//        System.out.println("Modified array is :");
//        printArray(arr, n);
    }
}
