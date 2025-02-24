# Trie Data Structure

## Problem Statement
A **Trie** (pronounced as "try") or **prefix tree** is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the `Trie` class:
- `Trie()` Initializes the trie object.
- `void insert(String word)` Inserts the string `word` into the trie.
- `boolean search(String word)` Returns `true` if the string `word` is in the trie (i.e., was inserted before), and `false` otherwise.
- `boolean startsWith(String prefix)` Returns `true` if there is a previously inserted string `word` that has the prefix `prefix`, and `false` otherwise.

### Example:
```java
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
```

## Intuition
A **Trie** is useful for storing words and efficiently performing operations like:
- **Insert a word** by creating nodes for each character if they do not already exist.
- **Search for a word** by checking if all characters exist in sequence and if the last character is marked as the end of a word.
- **Check prefix existence** by ensuring that the sequence of characters exists in the Trie.

## Java Implementation
```java
class Trie {
    private TrieNode root;

    private class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {
            children = new TrieNode[26]; // For lowercase English letters
            isEndOfWord = false;
        }
    }

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    private TrieNode findNode(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
```

## Complexity Analysis
| Operation   | Time Complexity | Explanation |
|-------------|---------------|-------------|
| **Insert**  | O(N) | Traverse N characters and insert nodes if needed |
| **Search**  | O(N) | Traverse N characters, check if word ends |
| **Prefix**  | O(N) | Traverse N characters to check prefix |

**Space Complexity:** O(M * N), where:
- **M** is the number of words.
- **N** is the average length of a word.

## Dry Run Example
### Insert "apple"
```
(root)
  |
  a
  |
  p
  |
  p
  |
  l
  |
  e (endOfWord=true)
```

### Search "apple"
- Traverse: `a → p → p → l → e`
- `e` is marked as `endOfWord` → **Return `true`**

### Search "app"
- Traverse: `a → p → p`
- `p` is **not** marked as `endOfWord` → **Return `false`**

### startsWith("app")
- Traverse: `a → p → p`
- Path exists → **Return `true`**

### Insert "app"
```
(root)
  |
  a
  |
  p
  |
  p (endOfWord=true)
  |
  l
  |
  e (endOfWord=true)
```

### Search "app"
- Traverse: `a → p → p`
- `p` is marked as `endOfWord` → **Return `true`**

## Conclusion
- **Tries enable fast insertion and retrieval.**
- **Operations run in O(N) time, making them efficient for prefix-based operations.**
- **Common applications include autocomplete, spell checking, and dictionary lookups.**



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

