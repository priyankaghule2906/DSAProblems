package strings;

import java.util.*;

public class TopKFrequent {

    public List<String> topKFrequent(String[] words, int k) {
        // Step 1: Count the frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        List<String> result = new ArrayList<>(frequencyMap.keySet());
        // Step 2: Create a list from the map and sort it
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());

        // Sort by frequency (descending) and then by word (ascending)
        Collections.sort(result, (a, b) -> {
            Integer val1 = frequencyMap.get(a);
            Integer val2 = frequencyMap.get(b);
            if (val1.equals(val2)) {
                return a.compareTo(b); // Lexicographical order
            }
            return val1 - val2; // Frequency order
        });

        return result.subList(0, k);
    }


    public List<String> topKFrequent1(String[] words, int k) {
        Map<String, Integer> map = new TreeMap<>();
        for (String s : words) {
            map.merge(s, 1, Integer::sum);
        }
        return map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static void main(String[] args) {
        TopKFrequent solution = new TopKFrequent();

        // Example 1
        String[] words1 = {"i","love","leetcode","i","love","coding"};
        int k1 = 2;
        System.out.println(solution.topKFrequent(words1, k1)); // Output: ["i", "love"]

        // Example 2
        String[] words2 = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        int k2 = 4;
        System.out.println(solution.topKFrequent(words2, k2)); // Output: ["the", "is", "sunny", "day"]
    }
}


