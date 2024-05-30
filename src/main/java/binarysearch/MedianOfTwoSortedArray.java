package binarysearch;

import org.junit.Test;

public class MedianOfTwoSortedArray {

    @Test
    public void test(){
        int[] arr = {1,3,4,7,10,12};
        int[] brr = {2,3,6,15};



//        System.out.println(findMedian(arr, 6, brr, 4 ));
//        System.out.println(findMedianSortedArrays(arr, brr ));
//        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3,4} ));
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1} ));
    }

    public static int findMedian(int arr[], int n, int brr[], int m)
    {
        // brute force approach
        int crrSize = n+m;
        int[] crr = new int[crrSize];
        int i=0,j=0;
        int l=0;
        int ans;

        while (i<n && j<m){
            if(arr[i]<brr[j]){
                crr[l++] = arr[i++];
            } else {
                crr[l++] = brr[j++];
            }
        }
        while (i<n){
            crr[l++] = arr[i++];
        }
        while (j<m){
            crr[l++] = brr[j++];
        }
        if(crrSize%2==0){
            ans = (crr[crrSize/2] + crr[(crrSize/2) -1]) /2;
        } else {
            ans = crr[crrSize/2];
        }
        return ans;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length  + nums2.length;
        int i=0,j=0,k=0;
        int index1 = size/2;
        int index2 = (size/2)-1;
        int element1=-1;
        int element2=-1;
        double ans = 0;
        while (i<nums1.length && j<nums2.length){
            if(nums1[i]<nums2[j]){
                if(k == index1) element1= nums1[i];
                if(k == index2) element2= nums1[i];
                i++;
                k++;
            } else {
                if(k == index1) element1= nums2[j];
                if(k == index2) element2= nums2[j];
                j++;
                k++;
            }
        }

        while (nums1.length!=0 && i<=nums1.length){
            if(k == index1) element1= nums1[i];
            if(k == index2) element2= nums1[i];
            i++;
            k++;
        }
        while (nums2.length!=0 &&j<=nums2.length){
            if(k == index1) element1= nums2[j];
            if(k == index2) element2= nums2[j];
            j++;
            k++;
        }

        if(size%2==0){
            ans = (element1 + element2)/2;
        } else {
            ans = element1;
        }
        return ans;
    }
}
