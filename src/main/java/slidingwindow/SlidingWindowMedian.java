package slidingwindow;

import org.junit.Test;

import java.util.*;

public class SlidingWindowMedian {

    PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> hi = new PriorityQueue<>();
    Map<Integer, Integer> delayed = new HashMap<>();
    int loSize =0, hiSize=0;

    // remove all the lazy deleted numbers sitting at the top of the heap
    private void prune(PriorityQueue<Integer> heap){
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.poll();
            int count = delayed.get(num);
            if(count == 1) {
                delayed.remove(num);
            } else {
                delayed.put(num, count -1);
            }
        }
    }

    private double getMedian(int k){
        // if window is odd, then we pick the mid element (max element from small heap as it contains max element at the top and small heap is allowed one extra element)
        if(k%2==1){
            return (double) lo.peek();
        }
        return (double)lo.peek() + (double) hi.peek() /2.0;
    }

    private void rebalance(){
        // this method is necessary to make sure both the heaps contains correct number of elements in it
        if(loSize > hiSize+1) {
            // remove from lo and put it into hi
            hi.offer(lo.poll());
            hiSize++;
            loSize--;
            prune(lo);
        } else if (loSize < hiSize){
            // remove from hi and put it into lo
            lo.offer(hi.poll());
            loSize++;
            hiSize--;
            prune(hi);
        }
    }



    @Test
    public void testSlidingWindowMedian(){

        int[] nums1 = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(medianSlidingWindow(nums1, 3)));
        // Expected: [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

    }


    PriorityQueue<Integer> small =
            new PriorityQueue<>(Collections.reverseOrder());

    PriorityQueue<Integer> large =
            new PriorityQueue<>();


    int smallSize = 0;
    int largeSize = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {

        int n = nums.length;
        double[] result = new double[n - k + 1];

        int index = 0;

        for (int i = 0; i < n; i++) {

            // 1. Add new element
            add(nums[i]);

            // 2. Once window reaches size k
            if (i >= k - 1) {

                // Remove invalid elements from heap tops
                prune(small);
                prune(large);

                // 3. Get median
                result[index++] = getMedian(k);

                // 4. Remove outgoing element
                int outgoing = nums[i - k + 1];
                remove(outgoing);
            }
        }

        return result;
    }

    private void add(int num) {

        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            smallSize++;
        } else {
            large.offer(num);
            largeSize++;
        }

        rebalance();
    }

    private void remove(int num) {

        delayed.put(num,
                delayed.getOrDefault(num, 0) + 1);

        if (num <= small.peek()) {
            smallSize--;
        } else {
            largeSize--;
        }

        prune(small);
        prune(large);

        rebalance();
    }




}