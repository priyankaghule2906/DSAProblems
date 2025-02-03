package sorting;

import org.junit.Test;



/*
*
*
* mergeSort(nums, 0, 7)                 -> [5, 3, 8, 6, 2, 7, 4, 1]
├── mergeSort(nums, 0, 3)             -> [5, 3, 8, 6]
│   ├── mergeSort(nums, 0, 1)         -> [5, 3]
│   │   ├── mergeSort(nums, 0, 0)     -> [5] (base case)
│   │   ├── mergeSort(nums, 1, 1)     -> [3] (base case)
│   │   └── merge(nums, 0, 0, 1)      -> [3, 5]
│   ├── mergeSort(nums, 2, 3)         -> [8, 6]
│   │   ├── mergeSort(nums, 2, 2)     -> [8] (base case)
│   │   ├── mergeSort(nums, 3, 3)     -> [6] (base case)
│   │   └── merge(nums, 2, 2, 3)      -> [6, 8]
│   └── merge(nums, 0, 1, 3)          -> [3, 5, 6, 8]
├── mergeSort(nums, 4, 7)             -> [2, 7, 4, 1]
│   ├── mergeSort(nums, 4, 5)         -> [2, 7]
│   │   ├── mergeSort(nums, 4, 4)     -> [2] (base case)
│   │   ├── mergeSort(nums, 5, 5)     -> [7] (base case)
│   │   └── merge(nums, 4, 4, 5)      -> [2, 7]
│   ├── mergeSort(nums, 6, 7)         -> [4, 1]
│   │   ├── mergeSort(nums, 6, 6)     -> [4] (base case)
│   │   ├── mergeSort(nums, 7, 7)     -> [1] (base case)
│   │   └── merge(nums, 6, 6, 7)      -> [1, 4]
│   └── merge(nums, 4, 5, 7)          -> [1, 2, 4, 7]
└── merge(nums, 0, 3, 7)              -> [1, 2, 3, 4, 5, 6, 7, 8]
*
*
* */
public class MergeSort {
    @Test
    public void test(){
        int[] nums = {5, 3, 8, 6, 2, 7, 4, 1};

        System.out.println(mergeSort(nums, 0, nums.length - 1));

    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) return 0;

        int mid = left + (right - left) / 2;

        // Recursively sort and count reverse pairs in the left and right halves
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);

        // Count reverse pairs across the two halves
        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && nums[i] > 2L * nums[j]) {
                j++;
            }
            count += (j - mid - 1);
        }

        // Merge the two halves
        merge(nums, left, mid, right);

        return count;
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        // Merge the two sorted subarrays
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // Copy remaining elements from the left subarray
        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        // Copy remaining elements from the right subarray
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        // Copy the sorted subarray back into the original array
        System.arraycopy(temp, 0, nums, left, temp.length);
    }
}
