package heap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopKelements {

    @Test
    public void test(){

        System.out.println(topKFrequent(new int[]{3, 1, 4, 4, 5, 2, 6, 1}, 2));
        System.out.println(topKFrequent(new int[]{7, 10,11, 5, 2, 5, 5, 7, 11, 8, 9}, 4));

    }

    public ArrayList<Integer> topKFrequent(int[] arr, int k) {
        // Code here
        // collect the frequency of each element in the frequency map
        Map<Integer, Integer> frequencyMap = Arrays.stream(arr).boxed().collect(Collectors.toMap(num -> num, num -> 1, Integer::sum));

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );

        heap.stream().map(Map.Entry::getKey).limit(k).toArray();

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> frequencyMap.get(a) - frequencyMap.get(b) // Compare by frequency
        );

        for(int num : frequencyMap.keySet()){
            pq.offer(num);
            if(pq.size() > k){
                pq.poll();
            }
        }

        return new ArrayList<>(pq);



    }
}
