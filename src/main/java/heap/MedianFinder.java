package heap;

import java.util.PriorityQueue;

public class MedianFinder {

    private PriorityQueue<Integer> maxHeap; // Max-heap for the smaller half
    private PriorityQueue<Integer> minHeap; // Min-heap for the larger half

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a); // Max-heap
        minHeap = new PriorityQueue<>();
        // Min-heap
    }

    public void addNum(int num) {
        // Add to maxHeap first
        maxHeap.offer(num);

        // Ensure maxHeap's max is less than or equal to minHeap's min
        minHeap.offer(maxHeap.poll());

        // Balance the heaps if minHeap has more elements
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
    public static void main(String[] args) {
        // Initialize MedianFinder object
        MedianFinder medianFinder = new MedianFinder();

        // Test case: Add numbers and check the median after each addition
        System.out.println("Adding 5: ");
        medianFinder.addNum(5);
        System.out.println("Median: " + medianFinder.findMedian()); // Expected: 5.0

        System.out.println("Adding 2: ");
        medianFinder.addNum(2);
        System.out.println("Median: " + medianFinder.findMedian()); // Expected: 3.5

        System.out.println("Adding 8: ");
        medianFinder.addNum(8);
        System.out.println("Median: " + medianFinder.findMedian()); // Expected: 5.0

        System.out.println("Adding 9: ");
        medianFinder.addNum(9);
        System.out.println("Median: " + medianFinder.findMedian()); // Expected: 5.0

        System.out.println("Adding 1: ");
        medianFinder.addNum(1);
        System.out.println("Median: " + medianFinder.findMedian()); // Expected: 3.5

        System.out.println("Adding 4: ");
        medianFinder.addNum(4);
        System.out.println("Median: " + medianFinder.findMedian()); // Expected: 4.0

        System.out.println("Adding 3: ");
        medianFinder.addNum(3);
        System.out.println("Median: " + medianFinder.findMedian()); // Expected: 4.0

        System.out.println("Adding 7: ");
        medianFinder.addNum(7);
        System.out.println("Median: " + medianFinder.findMedian()); // Expected: 4.5

        System.out.println("Adding 6: ");
        medianFinder.addNum(6);
        System.out.println("Median: " + medianFinder.findMedian()); // Expected: 5.0
    }
}
