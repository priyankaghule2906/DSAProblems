# Cycle in a Directed Graph (Using DFS)

## Problem Statement
Given a Directed Graph with **V** vertices (numbered from `0` to `V-1`) and **E** edges, check whether it contains any cycle or not.

The graph is represented as an adjacency list, where `adj[i]` contains a list of vertices that are directly reachable from vertex `i`. Specifically, `adj[i][j]` represents an edge from vertex `i` to vertex `j`.

### Example 1
**Input:**
```
V = 4, adj = [[1], [2], [3], [3]]
```

**Output:**
```
1
```
**Explanation:**
There is a cycle `3 -> 3` in the graph.

### Example 2
**Input:**
```
V = 4, adj = [[1], [2], [3], []]
```

**Output:**
```
0
```
**Explanation:**
There is no cycle in the graph.

### Constraints:
- `1 ≤ V, E ≤ 10^5`

---

## Approach (Using DFS)

### Key Idea
To detect a cycle in a directed graph using **DFS (Depth First Search)**, we use the concept of **visited states**. Each vertex is classified into one of three states:

1. **Not Visited (0):** The vertex has not been visited yet.
2. **In Progress (1):** The vertex is being visited, meaning it's part of the current DFS recursion stack.
3. **Visited (2):** The vertex and all its descendants have been completely processed.

### Algorithm
1. **Initialization**:
    - Use a `visited` array to track the state of each vertex:
        - `0`: Not visited.
        - `1`: In Progress.
        - `2`: Visited.

2. **DFS Traversal**:
    - For each unvisited vertex, perform a DFS.
    - During DFS, mark the current vertex as **In Progress**.
    - If a neighboring vertex is already **In Progress**, a cycle is detected.
    - After processing all neighbors, mark the current vertex as **Visited**.

3. **Cycle Detection**:
    - If any vertex revisits an **In Progress** node, return `true` for cycle existence.

---

## Implementation (Java)

```java
import java.util.*;

public class DetectCycleDirectedGraph {
    public static boolean isCyclic(int V, List<List<Integer>> adj) {
        // Visited array: 0 = Not visited, 1 = In Progress, 2 = Visited
        int[] visited = new int[V];

        // Perform DFS for each vertex
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                if (dfs(i, adj, visited)) {
                    return true; // Cycle detected
                }
            }
        }
        return false; // No cycle detected
    }

    private static boolean dfs(int node, List<List<Integer>> adj, int[] visited) {
        // Mark the current node as In Progress
        visited[node] = 1;

        // Traverse all neighbors
        for (int neighbor : adj.get(node)) {
            if (visited[neighbor] == 0) {
                // Perform DFS on unvisited neighbor
                if (dfs(neighbor, adj, visited)) {
                    return true; // Cycle detected
                }
            } else if (visited[neighbor] == 1) {
                // If neighbor is In Progress, a cycle is detected
                return true;
            }
        }

        // Mark the current node as Visited
        visited[node] = 2;
        return false;
    }

    public static void main(String[] args) {
        // Example 1
        int V1 = 4;
        List<List<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < V1; i++) adj1.add(new ArrayList<>());
        adj1.get(0).add(1);
        adj1.get(1).add(2);
        adj1.get(2).add(3);
        adj1.get(3).add(3);
        System.out.println(isCyclic(V1, adj1) ? 1 : 0); // Output: 1 (Cycle exists)

        // Example 2
        int V2 = 4;
        List<List<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < V2; i++) adj2.add(new ArrayList<>());
        adj2.get(0).add(1);
        adj2.get(1).add(2);
        adj2.get(2).add(3);
        System.out.println(isCyclic(V2, adj2) ? 1 : 0); // Output: 0 (No cycle)
    }
}
```

---

## Dry Run

### Example 1:
- **Input:**
  ```
  V = 4, adj = [[1], [2], [3], [3]]
  ```
- **Process:**
    - Start DFS from `0`. Explore `1 -> 2 -> 3`.
    - From `3`, go to `3` (self-loop), which is already **In Progress**.
- **Output:**
  ```
  1 (Cycle detected)
  ```

### Example 2:
- **Input:**
  ```
  V = 4, adj = [[1], [2], [3], []]
  ```
- **Process:**
    - Start DFS from `0`. Explore `1 -> 2 -> 3`.
    - No back edge exists.
- **Output:**
  ```
  0 (No cycle)
  ```

---

## Complexity

1. **Time Complexity:**
    - \(O(V + E)\), where:
        - \(V\) is the number of vertices.
        - \(E\) is the number of edges.
    - Each vertex and edge is processed once in the DFS.

2. **Space Complexity:**
    - \(O(V)\) for the `visited` array and recursion stack.

---


# Detect Cycle in a Directed Graph Using BFS

To detect a cycle in a directed graph using **BFS**, we can utilize **Kahn's Algorithm for Topological Sorting**. The key idea here is to use **indegree counts** and check if all nodes can be processed. If some nodes are left unprocessed, it indicates the presence of a cycle.

---

## Intuition (Using BFS)

1. **Indegree of a Node**:
    - The indegree of a node is the number of incoming edges to that node.
    - A node with an indegree of `0` has no dependencies and can be safely added to the queue for processing.

2. **Cycle Detection**:
    - If the graph has a cycle, there will be at least one node whose indegree never becomes `0`.
    - By counting how many nodes are processed during BFS, we can determine if a cycle exists:
        - If the number of processed nodes equals the total number of vertices (`V`), there is no cycle.
        - Otherwise, a cycle exists.

---

## Steps

1. **Calculate Indegree**:
    - Traverse the graph and compute the indegree for each vertex.

2. **Initialize Queue**:
    - Add all vertices with an indegree of `0` to a queue (these nodes have no dependencies).

3. **Process Nodes (BFS)**:
    - While the queue is not empty:
        - Remove a node from the queue and process it.
        - Reduce the indegree of all its neighbors by 1.
        - If a neighbor's indegree becomes `0`, add it to the queue.
    - Keep a count of the processed nodes.

4. **Check for Cycle**:
    - If the count of processed nodes is less than `V`, a cycle exists.

---

## Java Code

```java
import java.util.*;

public class DetectCycleDirectedGraphBFS {
    public static boolean isCyclic(int V, List<List<Integer>> adj) {
        // Step 1: Calculate indegree for all vertices
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }

        // Step 2: Initialize a queue with all nodes having indegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 3: Process nodes using BFS
        int processedNodes = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            processedNodes++;

            // Reduce the indegree of all neighbors
            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 4: Check if all nodes were processed
        return processedNodes != V; // If not all nodes were processed, there is a cycle
    }

    public static void main(String[] args) {
        // Example 1
        int V1 = 4;
        List<List<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < V1; i++) adj1.add(new ArrayList<>());
        adj1.get(0).add(1);
        adj1.get(1).add(2);
        adj1.get(2).add(3);
        adj1.get(3).add(3);
        System.out.println(isCyclic(V1, adj1) ? 1 : 0); // Output: 1 (Cycle exists)

        // Example 2
        int V2 = 4;
        List<List<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < V2; i++) adj2.add(new ArrayList<>());
        adj2.get(0).add(1);
        adj2.get(1).add(2);
        adj2.get(2).add(3);
        System.out.println(isCyclic(V2, adj2) ? 1 : 0); // Output: 0 (No cycle)
    }
}
```

---

## Explanation

### Example 1:
- **Input**:
  ```
  V = 4, adj = [[1], [2], [3], [3]]
  ```
- **Indegree**:
  ```
  Indegree: [0, 1, 1, 2]
  ```
- **Process**:
    - Add nodes with indegree `0` to the queue: `[0]`.
    - Process `0`: Update indegree → `[0, 0, 1, 2]`.
    - Process `1`: Update indegree → `[0, 0, 0, 2]`.
    - Process `2`: Update indegree → `[0, 0, 0, 1]`.
    - Queue becomes empty, and not all nodes are processed.
- **Output**: `1` (Cycle exists).

### Example 2:
- **Input**:
  ```
  V = 4, adj = [[1], [2], [3], []]
  ```
- **Indegree**:
  ```
  Indegree: [0, 1, 1, 1]
  ```
- **Process**:
    - Add nodes with indegree `0` to the queue: `[0]`.
    - Process `0`: Update indegree → `[0, 0, 1, 1]`.
    - Process `1`: Update indegree → `[0, 0, 0, 1]`.
    - Process `2`: Update indegree → `[0, 0, 0, 0]`.
    - Process `3`: Update indegree → `[0, 0, 0, 0]`.
    - All nodes are processed.
- **Output**: `0` (No cycle).

---

## Complexity

1. **Time Complexity**: \(O(V + E)\)
    - Calculating indegree takes \(O(V + E)\).
    - Processing nodes with BFS takes \(O(V + E)\).

2. **Space Complexity**: \(O(V + E)\)
    - For the adjacency list and queue.

---

## Comparison to DFS
| Approach   | Key Concept       | Best Use Case                  | Complexity |
|------------|-------------------|--------------------------------|------------|
| DFS        | Recursion stack   | Smaller graphs, recursion-heavy | \(O(V + E)\) |
| BFS (Kahn) | Indegree & Queue  | Topological sort, larger graphs | \(O(V + E)\) |


# Find Eventual Safe States
## Problem statement 
There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.



Example 1:

Illustration of graph
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
Example 2:

Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.

## Approach (Using DFS)

Intuition
By exploring the graph using DFS, we can identify cycles. If a node is part of a cycle, it is not safe.

Nodes that are not part of any cycle and do not lead to any cycle are considered safe.

The check array helps track whether a node is part of a safe set.

After DFS, iterate through the check array, and add the nodes marked as safe to the result list.

Approach
We can approach this problem using depth-first search (DFS).

Create a recursive DFS function that starts at a node and explores its neighbors.

During DFS, maintain three arrays: visit, path, and check.

visit: Marks whether a node has been visited during DFS.
path: Marks whether a node is part of the current DFS path to detect cycles.
check: Marks whether a node is determined to be part of a safe set.

If a cycle is detected during DFS, mark the nodes in the cycle as unsafe (check[node] = 0).

After DFS, iterate through the nodes and add the nodes marked as safe to the result list.

## Steps in the Code:

DFS Function:

The dfs() function is used to:
Mark nodes as visited (visited[]).
Track nodes in the current path (path[]).
Detect cycles and mark nodes as unsafe (check[] = false).
Mark nodes as safe when all neighbors are safe.

Cycle Detection:
A cycle is detected if a neighbor is already in the current DFS path (path[neighbor] = true).
Marking Safe Nodes:
If all neighbors of a node are safe or it has no neighbors, the node is marked as safe (check[node] = true).
Iterating Through All Nodes:
Perform DFS on all unvisited nodes to determine their safety.
Collect all safe nodes into the result list.

## java code
```java
   public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
         // Visited array
        boolean[] visited = new boolean[n];        
        // Path Visited array
        boolean[] path = new boolean[n];        
        // To keep a check of safe nodes
        boolean[] check = new boolean[n];
        

        // dfs for not visited nodes
        for(int i=0;i<n;i++){
            if(!visited[i]){
                dfs(i, graph, visited,path, check);
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(check[i]){
                result.add(i);
            }
        }
        return result;

    }

    boolean dfs(int node, int[][] graph, boolean[] visited, boolean[] path, boolean[] check) {
        visited[node] = true;
        path[node] = true;
        check[node] = false;
    
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, graph, visited, path, check)) return true;
            } else if (path[neighbor]) {
                return true;
            }
        }
    
        path[node] = false;
        check[node] = true;
    
        return false;
    
    }


```

## Find Eventual Safe States using BFS

Approach:
Reverse the edges of the graph reversing the graph.
Find the topological sort of the reversed graph which will exclude automatically the unsafe nodes.
Sort the ordering returned by topological sort to obtain the eventually safe nodes in a sorted fashion.


```java
class Solution {
    public int[] eventualSafeNodes(int V, int[][] adj) {
      List<List<Integer>> reverse = new ArrayList<>();

      for(int i=0;i<V;i++){
        reverse.add(new ArrayList<>());
      }

      for(int i=0;i<V;i++){
        for(int node : adj[i]){
            reverse.get(node).add(i);
        }
      }

      int[] result = topoSort(V, reverse);
      Arrays.sort(result);
      return result;

    }

    int[] topoSort(int V, List<List<Integer>> adj){
        // count indegree
        int[] indegree = new int[V];
        for(int i=0;i<V;i++){
            for(int node: adj.get(i)){
                indegree[node]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<V;i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        int[] result = new int[V];
        int index =0;
        while(!q.isEmpty()){
            int node = q.poll();
            result[index++] = node;

            for(int neighbor : adj.get(node)){
                indegree[neighbor]--;
                if(indegree[neighbor]==0){
                    q.offer(neighbor);
                }
            }

        }

        return Arrays.copyOfRange(result, 0, index);

    }
}

```
```text
Time Complexity: O(V+E) + O(V*logV) (where V and E represents the number of nodes and edges in the given graph)

Reversing the graph takes O(E) time.
Finding topological sort using Kahn's algorithm takes O(V+E) time.
Sorting the nodes takes O(N*logN) time (where N is the number of safe nodes, which can go up to V in worst-case).
Space Complexity: O(V+E)

Storing the reversed graph takes O(E) space.
```

# Course Schedule Problem: Intuition and Java Solution

### Problem Statement
There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where:
```
prerequisites[i] = [ai, bi]
```
This indicates that you must take course `bi` first if you want to take course `ai`.

#### Example 1
Input:
```
numCourses = 2, prerequisites = [[1,0]]
```
Output:
```
true
```
**Explanation**: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

#### Example 2
Input:
```
numCourses = 2, prerequisites = [[1,0],[0,1]]
```
Output:
```
false
```
**Explanation**: There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

---

### Intuition
This problem is a classic **cycle detection in a directed graph** problem. Each course can be thought of as a node in a graph, and each prerequisite relationship as a directed edge.

#### Key Idea
To determine if you can finish all courses, you need to check if the prerequisite graph contains a cycle. If there is a cycle, it's impossible to complete the courses because you’ll always have a circular dependency. If there is no cycle, it’s possible to finish all courses.

The problem can be solved using two main approaches:
1. **Topological Sorting** (using Kahn's Algorithm)
2. **Depth-First Search (DFS)** (to detect cycles)

---

### Approach 1: Topological Sorting
1. Represent the graph using an adjacency list.
2. Compute the in-degree (number of incoming edges) for each node.
3. Use a queue to process nodes with an in-degree of 0 (no prerequisites).
4. Gradually remove nodes from the graph (by reducing the in-degree of their neighbors).
5. If all nodes are processed, the graph has no cycle, and you can finish all courses. Otherwise, it contains a cycle.

---

### Approach 2: DFS (Cycle Detection)
1. Use an adjacency list to represent the graph.
2. Maintain a `visited` array where:
    - `0` = Not visited
    - `1` = Visiting (node in the current DFS path)
    - `2` = Fully processed
3. Perform a DFS for each node:
    - Mark it as `1` (visiting).
    - Check its neighbors recursively.
    - If you encounter a node marked as `1`, there's a cycle.
    - Mark the node as `2` (processed) once all neighbors are checked.
4. If no cycle is detected after processing all nodes, you can finish all courses.

---

### Java Solution Using Topological Sorting
```java
import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Create the adjacency list and in-degree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prereqCourse = prereq[1];
            graph.get(prereqCourse).add(course);
            inDegree[course]++;
        }
        
        // Step 2: Add all nodes with in-degree 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        
        // Step 3: Process the graph
        int processedCourses = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            processedCourses++;
            
            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        // Step 4: Check if all courses have been processed
        return processedCourses == numCourses;
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        System.out.println(cs.canFinish(2, new int[][]{{1, 0}})); // true
        System.out.println(cs.canFinish(2, new int[][]{{1, 0}, {0, 1}})); // false
    }
}
```

---

### Java Solution Using DFS
```java
import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Create the adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }
        
        // Step 2: Initialize the visited array
        int[] visited = new int[numCourses];
        
        // Step 3: Perform DFS on each course
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(graph, visited, i)) {
                return false; // Cycle detected
            }
        }
        
        return true; // No cycle detected
    }
    
    private boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
        if (visited[course] == 1) {
            return true; // Cycle detected
        }
        if (visited[course] == 2) {
            return false; // Already processed
        }
        
        // Mark the course as visiting
        visited[course] = 1;
        
        // Visit all neighbors
        for (int neighbor : graph.get(course)) {
            if (hasCycle(graph, visited, neighbor)) {
                return true;
            }
        }
        
        // Mark the course as processed
        visited[course] = 2;
        return false;
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        System.out.println(cs.canFinish(2, new int[][]{{1, 0}})); // true
        System.out.println(cs.canFinish(2, new int[][]{{1, 0}, {0, 1}})); // false
    }
}
```

---

### Time and Space Complexity
- **Time Complexity**: \(O(V + E)\), where \(V\) is the number of courses and \(E\) is the number of prerequisites.
- **Space Complexity**: \(O(V + E)\) for the adjacency list, and \(O(V)\) for the in-degree array or visited array.

---

# How to Determine Edge Direction in Course Prerequisites Graph

### Understanding the Input Format
The `prerequisites` array is given as:
```
prerequisites[i] = [a, b]
```
This means:
- To take course `a`, you **must first take course `b`.**

So, course `b` must come **before** course `a`.

---

### Edge Direction
When constructing a graph:
1. **Node `b` (prerequisite course)** should point to **Node `a` (dependent course)**.
2. This creates a **directed edge from `b` to `a`** because `b` must be completed before you can take `a`.

---

### Why Use `graph.get(prereq[1]).add(prereq[0])`?
In the graph representation:
- `graph.get(x)` retrieves the list of courses dependent on course `x`.
- `graph.get(prereq[1]).add(prereq[0])` means:
    - Add `prereq[0]` (dependent course) to the list of courses that can be taken after completing `prereq[1]` (prerequisite course).

This direction ensures the graph accurately represents the dependency chain.

---

### Example Walkthrough

#### Input: `prerequisites = [[1, 0]]`
Interpretation: To take course `1`, you must first take course `0`.

1. **Create an edge:** From `0` (prerequisite) to `1` (dependent).
2. In the adjacency list representation:
    - `graph.get(0)` should include `1` because course `1` is dependent on course `0`.

#### Input: `prerequisites = [[1, 0], [2, 1], [3, 2]]`
Interpretation:
- To take course `1`, you must first take course `0`. (Edge: `0 -> 1`)
- To take course `2`, you must first take course `1`. (Edge: `1 -> 2`)
- To take course `3`, you must first take course `2`. (Edge: `2 -> 3`)

Adjacency list:
```
graph.get(0): [1]
graph.get(1): [2]
graph.get(2): [3]
```

---

### Key Rule
When iterating over `prerequisites`, always interpret `[a, b]` as:
- Create an edge from `b` to `a` (`graph.get(b).add(a)`).

This ensures the graph correctly models the dependency relationships.


# Course Schedule II

## Problem Description
There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [a_i, b_i]` indicates that you must take course `b_i` first if you want to take course `a_i`.

### Example 1:
Input:
```java
numCourses = 2, prerequisites = [[1,0]]
```
Output:
```java
[0,1]
```
Explanation: To take course 1, you should have finished course 0. The correct course order is `[0, 1]`.

### Example 2:
Input:
```java
numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
```
Output:
```java
[0,2,1,3]
```
Explanation: To take course 3, you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.

### Example 3:
Input:
```java
numCourses = 1, prerequisites = []
```
Output:
```java
[0]
```

### Constraints:
- `1 <= numCourses <= 2000`
- `0 <= prerequisites.length <= numCourses * (numCourses - 1)`
- `prerequisites[i].length == 2`
- `0 <= a_i, b_i < numCourses`
- `a_i != b_i`
- All the pairs `[a_i, b_i]` are distinct.

---

## Solution Approach

To solve this problem, you can use **Topological Sorting** on a **Directed Acyclic Graph (DAG)**. Below is the step-by-step approach:

### Steps:
1. **Graph Representation**:
    - Represent the courses and prerequisites as a graph, where each course is a node, and each prerequisite is a directed edge from one course to another.

2. **Indegree Array**:
    - Maintain an `indegree` array to count how many prerequisites each course has. A course with an indegree of `0` can be taken immediately.

3. **Queue for BFS**:
    - Use a queue to implement Breadth-First Search (BFS). Initially, add all courses with an `indegree` of `0` to the queue.

4. **Processing the Graph**:
    - Remove a course from the queue and add it to the result. For each course it unlocks (its neighbors), decrement their `indegree`. If any neighbor's `indegree` becomes `0`, add it to the queue.

5. **Check for Cycles**:
    - If you cannot process all courses (i.e., the result size is less than `numCourses`), then there is a cycle, and it is impossible to complete all courses.

---

## Java Solution
```java
import java.util.*;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Step 1: Initialize graph and indegree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];
            graph.get(prereq).add(course);
            indegree[course]++;
        }
        
        // Step 2: Add all courses with indegree 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Step 3: Perform BFS
        int[] order = new int[numCourses];
        int index = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order[index++] = current;
            
            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // Step 4: Check if all courses can be completed
        if (index == numCourses) {
            return order;
        } else {
            return new int[0]; // Return empty array if a cycle is detected
        }
    }

    public static void main(String[] args) {
        CourseScheduleII solution = new CourseScheduleII();
        // Test case 1
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println(Arrays.toString(solution.findOrder(numCourses1, prerequisites1)));

        // Test case 2
        int numCourses2 = 4;
        int[][] prerequisites2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(solution.findOrder(numCourses2, prerequisites2)));

        // Test case 3
        int numCourses3 = 1;
        int[][] prerequisites3 = {};
        System.out.println(Arrays.toString(solution.findOrder(numCourses3, prerequisites3)));
    }
}
```

---

## Explanation of the Code
1. **Graph Construction**: Use an adjacency list to represent the directed graph.
2. **Indegree Calculation**: Count the number of incoming edges for each node (course).
3. **BFS Traversal**: Use a queue to process nodes with no incoming edges. Gradually remove edges and update indegrees, adding nodes with an indegree of `0` to the queue.
4. **Cycle Detection**: If some courses remain unprocessed (indegree > 0), return an empty array.

---

## Complexity
- **Time Complexity**: \(O(V + E)\), where \(V\) is the number of courses and \(E\) is the number of prerequisites.
- **Space Complexity**: \(O(V + E)\), for the graph representation and the queue.

# Alien Dictionary

## Intuition:

How this problem can be identified as a Graph problem?
The problem suggests that there exists the ordering of different words based on the alien dictionary. Also, it is asked to find out the ordering of letters based on the dictionary. The concept of ordering of nodes can be solved using Topological sort which comes under the topic of Graphs.
How to form the graph?
Here, the letters can be represented as nodes of the graph.

To understand the edges, consider example 1 where
N=5, K=4, dict = {"baa", "abcd", "abca", "cab", "cad"}

Considering the first two words "baa" and "abcd", it is clear that they are differentiated by the first letter i.e. 'b' and 'a'. Thus, a directed edge can be inserted in the graph from node 'b' to node 'a' representing that letter 'b' must appear

## Java code
```java
class Solution {
    public String findOrder(String [] dict, int N, int k) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<k; i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0; i<N-1;i++){
            String s1 = dict[i];
            String s2 = dict[i+1];
            int min = Math.min(s1.length(), s2.length());

            for(int j=0;j<min;j++){
                if(s1.charAt(j)!=s2.charAt(j)){
                    adj.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    break;
                }
            }
        }

        List<Integer> topo = topoSort(k, adj);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < k; i++) {
            // Add the letter to the result
            ans.append((char) ('a' + topo.get(i)));
            ans.append(' ');
        }
         return ans.toString();


    }

    List<Integer> topoSort(int V, List<List<Integer>> adj){
        int[] inDegree = new int[V];
        for(int i=0;i<V;i++){
            for(int node: adj.get(i)){
                inDegree[node]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<V;i++){
            if(inDegree[i]==0){
                q.offer(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while(!q.isEmpty()){
            int node = q.poll();
            result.add(node);

            for(int neighbor: adj.get(node)){
                inDegree[neighbor]--;
                if(inDegree[neighbor]==0){
                    q.offer(neighbor);
                }
            }
        }
        return result;
    }
}
```

## Complexity Analysis:
Time Complexity: O(K+N) (where K and N represents the number of nodes and edges in the given graph)

Forming the graph takes O(N*len) time, where len is the average length of a word in the dictionary.
Finding topological sort takes O(K+N) time.
Space Complexity: O(K+N)

Storing the graph takes O(N) space.
Topological sorting algorithm uses extra O(K) computational space.

## When is the ordering of letters not possible:
If every character matches and the largest word appears before the shortest word: For example, if “abcd” appears before “abc”, we can say the ordering is not possible.
If there exists a cyclic dependency between the characters: For example, in the dictionary: dict: {“abc”, “bat”, “ade”} there exists a cyclic dependency between 'a' and 'b' because the dictionary states 'a' < 'b' < 'a', which is not possible.
    
# Sequence Reconstruction

https://algo.monster/liteproblems/444

# Keys and Rooms (Leetcode 841)

## Problem Statement
There are `n` rooms labeled from `0` to `n - 1`, and all the rooms are locked except for room `0`. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array `rooms` where `rooms[i]` is the set of keys that you can obtain if you visited room `i`, return `true` if you can visit all the rooms, or `false` otherwise.

### Constraints:
- `n == rooms.length`
- `2 <= n <= 1000`
- `0 <= rooms[i].length <= 1000`
- `1 <= sum(rooms[i].length) <= 3000`
- `0 <= rooms[i][j] < n`
- All the values of `rooms[i]` are unique.

## Approach / Intuition
The problem can be visualized as a **graph traversal** where:
- **Rooms are nodes**.
- **Keys define edges** (i.e., an edge exists between room `i` and `j` if room `i` has a key to `j`).
- The goal is to determine if we can visit **all nodes (rooms) starting from node `0`**.

This can be solved using:
1. **Depth-First Search (DFS)** (Recursive approach):
    - Start from room `0` and explore all reachable rooms recursively.
    - Maintain a `visited` array to track visited rooms.
    - Stop if all rooms are visited.
2. **Breadth-First Search (BFS)** (Iterative approach using Queue):
    - Start from room `0`.
    - Use a queue to process rooms level by level.
    - Visit each room and enqueue its keys.
    - Stop if all rooms are visited.

## Java Implementation

### DFS Solution
```java
import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfs(rooms, 0, visited);
        
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }
    
    private void dfs(List<List<Integer>> rooms, int room, boolean[] visited) {
        if (visited[room]) return;
        visited[room] = true;
        
        for (int key : rooms.get(room)) {
            dfs(rooms, key, visited);
        }
    }
}
```
#### **Time Complexity**: `O(N + E)`, where `N` is the number of rooms and `E` is the total number of keys.
#### **Space Complexity**: `O(N)`, due to the recursion stack in DFS.

### BFS Solution
```java
import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[0] = true;
        queue.add(0);
        
        while (!queue.isEmpty()) {
            int room = queue.poll();
            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    visited[key] = true;
                    queue.add(key);
                }
            }
        }
        
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }
}
```
#### **Time Complexity**: `O(N + E)`
#### **Space Complexity**: `O(N)`, due to the queue storage.

## Dry Run

### Example 1
#### **Input:**
```java
rooms = [[1], [2], [3], []]
```
#### **Execution Steps:**
1. Start from room `0`, collect key `[1]`.
2. Visit room `1`, collect key `[2]`.
3. Visit room `2`, collect key `[3]`.
4. Visit room `3`, no new keys found.
5. All rooms visited → return `true`.
#### **Output:**
```java
true
```

### Example 2
#### **Input:**
```java
rooms = [[1,3], [3,0,1], [2], [0]]
```
#### **Execution Steps:**
1. Start from room `0`, collect keys `[1,3]`.
2. Visit room `1`, collect keys `[3,0,1]` (already have `0` and `1`).
3. Visit room `3`, no new keys found.
4. Room `2` remains locked (its key is in `2` itself).
5. Not all rooms visited → return `false`.
#### **Output:**
```java
false
```

