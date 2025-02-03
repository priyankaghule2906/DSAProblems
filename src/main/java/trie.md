# Search Suggestions System

## Problem Statement

You are given an array of strings `products` and a string `searchWord`. Design a system that suggests at most three product names from `products` after each character of `searchWord` is typed. Suggested products should have a common prefix with `searchWord`. If there are more than three products with a common prefix, return the three lexicographically smallest products.

### Input
- `products`: An array of strings representing product names.
- `searchWord`: A string representing the word being searched.

### Output
- A list of lists, where each list contains up to three suggested products for every prefix of `searchWord`.

---

## Intuition

To solve this problem efficiently, we need a data structure that allows:
1. **Efficient prefix matching**: Quickly find products matching the prefix as we type each character of `searchWord`.
2. **Lexicographical order maintenance**: Ensure that the suggestions are returned in lexicographical order.

A **Trie (prefix tree)** is well-suited for this task. By inserting the sorted product list into the Trie, we can:
- Store suggestions at each node (up to 3 lexicographically smallest products).
- Traverse the Trie character by character for each prefix of `searchWord` to retrieve suggestions.

---

## Java Logic Using Trie

```java
import java.util.*;

class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        List<String> suggestion = new ArrayList<>();
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // Create a Trie structure
        TrieNode root = new TrieNode();
        // Sort products to ensure lexicographical order
        Arrays.sort(products);

        // Build the Trie
        for (String product : products) {
            TrieNode current = root;
            for (char c : product.toCharArray()) { // Iterate over characters of the product
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
                // Store up to 3 suggestions at each node
                if (current.suggestion.size() < 3) {
                    current.suggestion.add(product);
                }
            }
        }

        // Search for suggestions
        List<List<String>> result = new ArrayList<>();
        TrieNode current = root;

        for (char c : searchWord.toCharArray()) {
            int index = c - 'a';
            if (current != null) {
                current = current.children[index];
            }
            // Add suggestions for this prefix (empty if no match found)
            result.add(current == null ? new ArrayList<>() : current.suggestion);
        }

        return result;
    }
}
```

---

## Dry Run

### Input:
- `products = ["mobile", "mouse", "moneypot", "monitor", "mousepad"]`
- `searchWord = "mouse"`

### Steps:
1. **Sorting Products**:
    - Sorted `products`: `["mobile", "moneypot", "monitor", "mouse", "mousepad"]`.

2. **Building the Trie**:
    - Insert "mobile": Adds nodes for 'm', 'o', 'b', 'i', 'l', 'e', and updates suggestions at each node.
    - Insert "mouse": Adds nodes for 'm', 'o', 'u', 's', 'e', and updates suggestions at each node.
    - Similarly, "moneypot", "monitor", and "mousepad" are added.

3. **Processing `searchWord`**:
    - For prefix `"m"`, suggestions = `["mobile", "moneypot", "monitor"]`.
    - For prefix `"mo"`, suggestions = `["mobile", "moneypot", "monitor"]`.
    - For prefix `"mou"`, suggestions = `["mouse", "mousepad"]`.
    - For prefix `"mous"`, suggestions = `["mouse", "mousepad"]`.
    - For prefix `"mouse"`, suggestions = `["mouse", "mousepad"]`.

### Output:
```
[
    ["mobile", "moneypot", "monitor"],
    ["mobile", "moneypot", "monitor"],
    ["mouse", "mousepad"],
    ["mouse", "mousepad"],
    ["mouse", "mousepad"]
]
```

---

## Complexity Analysis

### Time Complexity
1. **Trie Construction**:
    - Inserting a product of length `L` into the Trie takes `O(L)`.
    - For `n` products with an average length `L`, the construction takes `O(n * L)`.
    - Sorting the `products` array takes `O(n * log(n))`.
    - Total: **`O(n * L + n * log(n))`**.

2. **Search Suggestions**:
    - For each of the `m` characters in `searchWord`, we traverse a single branch in the Trie.
    - At most, we look at 3 suggestions for each character.
    - Total: **`O(m * 3) = O(m)`**.

**Overall Time Complexity**: **`O(n * L + n * log(n) + m)`**.

### Space Complexity
- **Trie Size**:
    - Each character in a product creates a `TrieNode` if it doesn’t exist.
    - Worst case: all `n` products have unique prefixes, requiring `O(n * L)` nodes.
- **Suggestions Storage**:
    - Each node stores up to 3 product names.
    - Total suggestions stored: `O(n)`.

**Overall Space Complexity**: **`O(n * L)`**.

---

## Edge Cases
1. **Empty Input**:
    - `products = []` or `searchWord = ""`: Return an empty result.
2. **No Matching Prefix**:
    - If no product matches a prefix, append an empty list for that prefix.
3. **All Products Share the Same Prefix**:
    - The Trie handles this naturally by reusing nodes for shared prefixes.

