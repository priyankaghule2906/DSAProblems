package arrays;

public class Sort012 {

    public static void sort012(int arr[], int n) {
        int low = 0;
        int mid = 0;
        int high = n - 1;
        int temp = 0;

        while (mid <= high) {

            switch (arr[mid]) {
                case 0:
                    temp = arr[mid];
                    arr[mid] = arr[low];
                    arr[low] = temp;
                    low++;
                    mid++;
                    break;

                case 1:
                    mid++;
                    break;
                case 2:
                    temp = arr[mid];
                    arr[mid] = arr[high];
                    arr[high] = temp;
                    high--;
                    break;

            }

        }
    }
}
