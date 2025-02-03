
# Clone Graph Problem

## Problem Statement

Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.

Each node in the graph contains:
- A value (`int`).
- A list (`List[Node]`) of its neighbors.

### Example 1

**Input:**
```java
adjList = [[2,4],[1,3],[2,4],[1,3]]
```

**Output:**
```java
[[2,4],[1,3],[2,4],[1,3]]
```

**Explanation:**
There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

### Example 2

**Input:**
```java
adjList = [[]]
```

**Output:**
```java
[[]]
```

**Explanation:**
The input contains one empty list. The graph consists of only one node with `val = 1` and it does not have any neighbors.

### Example 3

**Input:**
```java
adjList = []
```

**Output:**
```java
[]
```

**Explanation:**
This is an empty graph; it does not have any nodes.

### Constraints:
- The number of nodes in the graph is in the range `[0, 100]`.
- `1 <= Node.val <= 100`
- `Node.val` is unique for each node.
- There are no repeated edges and no self-loops in the graph.
- The graph is connected, and all nodes can be visited starting from the given node.

---

## BFS Solution

### Approach
1. Use a `HashMap` to map original nodes to their corresponding cloned nodes.
2. Perform BFS starting from the given node to clone the graph.
3. For each node:
    - If it has not been cloned, clone it and add it to the `HashMap`.
    - Add its neighbors to the queue if they have not been visited.
    - Establish neighbor relationships for the cloned node.

### Code Implementation
```java
import java.util.*;

// Definition for a Node
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // A map to store cloned nodes
        Map<Node, Node> map = new HashMap<>();

        // Queue for BFS
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        // Clone the root node
        map.put(node, new Node(node.val));

        // BFS traversal
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Iterate through the neighbors
            for (Node neighbor : current.neighbors) {
                if (!map.containsKey(neighbor)) {
                    // Clone the neighbor if it hasn't been cloned
                    map.put(neighbor, new Node(neighbor.val));
                    // Add the neighbor to the queue for further traversal
                    queue.add(neighbor);
                }
                // Add the cloned neighbor to the current node's cloned neighbors
                map.get(current).neighbors.add(map.get(neighbor));
            }
        }

        // Return the cloned graph's root
        return map.get(node);
    }
}
```

### Explanation
1. **Node Definition**: The `Node` class is used to represent each node in the graph, with a `val` and a list of neighbors.
2. **BFS Traversal**:
    - Start with the given node.
    - Use a queue to explore nodes level by level.
    - Clone each node and its neighbors as you encounter them.
3. **HashMap**:
    - Tracks original nodes as keys and their corresponding cloned nodes as values.
    - Ensures each node is cloned only once, preventing infinite loops in cyclic graphs.
4. **Neighbor Relationships**:
    - After cloning a node, establish relationships with its cloned neighbors.

### Complexity
- **Time Complexity**: \(O(V + E)\), where \(V\) is the number of nodes and \(E\) is the number of edges. Each node and edge is processed once.
- **Space Complexity**: \(O(V)\) for the `HashMap` and queue.

---

## DFS Solution

### Approach
1. Use a `HashMap` to map original nodes to their corresponding cloned nodes.
2. Perform DFS starting from the given node.
3. For each node:
    - If it has not been cloned, clone it and store it in the `HashMap`.
    - Recursively clone all its neighbors and establish the connections.

### Code Implementation
```java
import java.util.*;

// Definition for a Node
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // A map to store the original node and its cloned counterpart
        Map<Node, Node> map = new HashMap<>();

        // Start DFS traversal to clone the graph
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        // If the node is already cloned, return the cloned node
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Clone the current node
        Node clone = new Node(node.val);
        map.put(node, clone);

        // Clone all the neighbors recursively
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, map));
        }

        return clone;
    }
}
```

### Explanation
1. **Node Definition**: The `Node` class is the same as in the BFS solution.
2. **DFS Traversal**:
    - Start from the given node.
    - Clone the current node if it hasn’t been cloned yet.
    - For each neighbor, recursively call the `dfs` function.
3. **HashMap**:
    - Tracks original nodes as keys and their cloned counterparts as values.
    - Ensures each node is cloned only once.
4. **Recursive Cloning**:
    - Each recursive call clones a node and its neighbors until the entire graph is cloned.

### Complexity
- **Time Complexity**: \(O(V + E)\), where \(V\) is the number of nodes and \(E\) is the number of edges. Each node and edge is visited once during DFS.
- **Space Complexity**: \(O(V)\) for the `HashMap` and the recursion stack (in case of a graph with depth equal to \(V\)).

---

## Comparison

| **Aspect**             | **BFS Solution**        | **DFS Solution**        |
|------------------------|-------------------------|-------------------------|
| **Traversal Method**   | Breadth-First Search    | Depth-First Search      |
| **Space Complexity**   | \(O(V)\) (queue)         | \(O(V)\) (recursion stack) |
| **Use Case Preference**| Iterative solutions or shallow graphs | Recursive solutions or deep graphs |

Both solutions produce the same results. Choose the one that best suits your coding style or problem constraints.
