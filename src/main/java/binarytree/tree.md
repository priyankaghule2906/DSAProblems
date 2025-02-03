# Leaf-Similar Trees

## Problem

Consider all the leaves of a binary tree. From left to right order, the values of those leaves form a leaf value sequence.

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return `true` if and only if the two given trees with head nodes `root1` and `root2` are leaf-similar.

### Example 1:
**Input:**  
`root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]`

**Output:**  
`true`

### Example 2:
**Input:**  
`root1 = [1,2,3], root2 = [1,3,2]`

**Output:**  
`false`

### Constraints:
- The number of nodes in each tree will be in the range `[1, 200]`.
- Both of the given trees will have values in the range `[0, 200]`.

---

## Intuition

The key to solving this problem is understanding that the problem boils down to extracting the **leaf sequences** from both trees and comparing them. We can use a recursive traversal (such as pre-order, in-order, or post-order) to collect the leaf values into a list, and then check if the two lists are equal.

### Steps:
1. Traverse the first tree and collect all its leaf node values in a list.
2. Traverse the second tree and do the same.
3. Compare the two lists. If they are identical (same size and same values in the same order), return `true`. Otherwise, return `false`.

---

## Java Code

```java
import java.util.*;

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // Get leaf sequences for both trees
        List<Integer> leafSequence1 = getLeafSequence(root1);
        List<Integer> leafSequence2 = getLeafSequence(root2);

        // Compare the two leaf sequences
        return leafSequence1.equals(leafSequence2);
    }

    // Helper method to get the leaf sequence of a tree
    private List<Integer> getLeafSequence(TreeNode root) {
        List<Integer> leafSequence = new ArrayList<>();
        collectLeaves(root, leafSequence);
        return leafSequence;
    }

    // Recursive helper method to collect leaf nodes
    private void collectLeaves(TreeNode node, List<Integer> leafSequence) {
        if (node == null) return;

        // Check if the node is a leaf
        if (node.left == null && node.right == null) {
            leafSequence.add(node.val);
            return;
        }

        // Recurse for left and right children
        collectLeaves(node.left, leafSequence);
        collectLeaves(node.right, leafSequence);
    }
}
```

---

## Complexity Analysis

### Time Complexity:
- **Traversal of both trees:** We visit each node in both trees exactly once. If the number of nodes in `root1` is `N` and in `root2` is `M`, the time complexity is:

  **`O(N + M)`**

### Space Complexity:
- **Recursion stack:** The recursion stack will hold at most `H` frames at any given time, where `H` is the height of the tree. For a balanced tree, `H = O(log N)`; for a skewed tree, `H = O(N)`.
- **Leaf lists:** The leaf sequences for both trees will take up `L1 + L2` space, where `L1` and `L2` are the number of leaf nodes in each tree.

  **Overall Space Complexity:**
  **`O(H + L1 + L2)`**



# Maximum Depth of Binary Tree - A Comprehensive Guide

## Problem Statement
Given the `root` of a binary tree, return *its maximum depth*.

A binary tree's **maximum depth** is the number of nodes along the longest path from the root node down to the farthest leaf node.

### Examples
**Example 1:**
```
Input: root = [3,9,20,null,null,15,7]
Output: 3
```

**Example 2:**
```
Input: root = [1,null,2]
Output: 2
```

### Constraints
* The number of nodes in the tree is in the range `[0, 104]`.
* `-100 <= Node.val <= 100`

## Intuition and Approach

The maximum depth of a binary tree represents the number of nodes along the longest path from the root to a leaf node. This problem is an excellent opportunity to understand recursive tree traversal.

There are two primary approaches to solve this:
1. Recursive Approach (Depth-First Search)
2. Iterative Approach (Breadth-First Search)

### Recursive Strategy
The key insights for recursively finding the maximum depth are:
- If the tree is empty (null), the depth is 0
- For any node, its depth is 1 + the maximum depth of its left and right subtrees
- This creates a natural recursive pattern where we explore each path and find its maximum length

## Java Solution with Detailed Explanation

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        // Base case: if the tree is empty, depth is 0
        if (root == null) {
            return 0;
        }
        
        // Recursively calculate the depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        // The depth of current node is 1 + max of left and right subtree depths
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
```

## Dry Run Walkthrough

### Visual Representation
```
    3
   / \
  9  20
     / \
    15  7
```

#### Step-by-Step Recursion

1. Start at root (3):
    - Call `maxDepth(3)`
    - Recursively explore left subtree (9)
    - Recursively explore right subtree (20)

2. Left Subtree (9):
    - `maxDepth(9)`
    - No children
    - Returns 1

3. Right Subtree (20):
    - `maxDepth(20)`
    - Has left child (15) and right child (7)
    - Will go deeper into 15 and 7

4. For nodes 15 and 7:
    - Both are leaf nodes
    - Each returns depth of 1

5. Back to node 20:
    - Max of left (15) and right (7) subtree depths
    - `1 + max(1, 1)` = 2

6. Final Calculation for root (3):
    - `1 + max(leftDepth, rightDepth)`
    - `1 + max(1, 2)` = 3

**Output: 3** ✅

## Time and Space Complexity
- **Time Complexity**: O(n), where n is the number of nodes
    - We visit each node exactly once
- **Space Complexity**: O(h), where h is the height of the tree
    - In the recursive call stack
    - Worst case O(n) for a skewed tree
    - Best case O(log n) for a balanced tree

## Follow-up Challenges

### 1. Iterative Solution using Queue (Breadth-First Search)

```java
class Solution {
    public int maxDepth(TreeNode root) {
        // If the tree is empty, return 0
        if (root == null) {
            return 0;
        }
        
        // Initialize a queue for level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        // Variable to track the current depth
        int depth = 0;
        
        // Perform level-order traversal
        while (!queue.isEmpty()) {
            // Increment depth for each level
            depth++;
            
            // Get the number of nodes at the current level
            int levelSize = queue.size();
            
            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                // Remove the current node
                TreeNode current = queue.poll();
                
                // Add left child to the queue if exists
                if (current.left != null) {
                    queue.offer(current.left);
                }
                
                // Add right child to the queue if exists
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        
        return depth;
    }
}
```

### 2. Minimum Depth Solution

```java
class Solution {
    public int minDepth(TreeNode root) {
        // If the tree is empty, return 0
        if (root == null) {
            return 0;
        }
        
        // If it's a leaf node, return 1
        if (root.left == null && root.right == null) {
            return 1;
        }
        
        // Initialize min depth to a large value
        int minDepth = Integer.MAX_VALUE;
        
        // Check left subtree if it exists
        if (root.left != null) {
            minDepth = Math.min(minDepth, minDepth(root.left));
        }
        
        // Check right subtree if it exists
        if (root.right != null) {
            minDepth = Math.min(minDepth, minDepth(root.right));
        }
        
        // Add 1 to account for current node
        return minDepth + 1;
    }
}
```

### 3. Tracking the Deepest Path

```java
class Solution {
    // Nested class to return both depth and path
    private class DepthPath {
        int depth;
        List<TreeNode> path;
        
        DepthPath(int depth, List<TreeNode> path) {
            this.depth = depth;
            this.path = path;
        }
    }
    
    public List<TreeNode> findDeepestPath(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        return findDeepestPathHelper(root).path;
    }
    
    private DepthPath findDeepestPathHelper(TreeNode node) {
        // Base case: null node
        if (node == null) {
            return new DepthPath(0, new ArrayList<>());
        }
        
        // Leaf node
        if (node.left == null && node.right == null) {
            List<TreeNode> path = new ArrayList<>();
            path.add(node);
            return new DepthPath(1, path);
        }
        
        // Recursive calls
        DepthPath leftResult = findDeepestPathHelper(node.left);
        DepthPath rightResult = findDeepestPathHelper(node.right);
        
        // Determine which path is deeper
        DepthPath deeperPath = leftResult.depth >= rightResult.depth ? leftResult : rightResult;
        
        // Add current node to the path
        deeperPath.path.add(0, node);
        deeperPath.depth++;
        
        return deeperPath;
    }
}
```

## Comparative Analysis

| Approach | Time Complexity | Space Complexity | Key Characteristic |
|----------|-----------------|------------------|-------------------|
| Recursive Max Depth | O(n) | O(h) | Simple, elegant |
| Iterative (BFS) | O(n) | O(w) | Level-by-level processing |
| Minimum Depth | O(n) | O(h) | Handles asymmetric trees |
| Deepest Path | O(n) | O(n) | Tracks actual route |

## Insights and Learning Points
1. Recursive and iterative solutions can solve the same problem differently
2. Depth can be tracked from top-down or bottom-up
3. Tree traversal techniques are versatile and adaptable
4. Custom data structures help solve complex problems

## Conclusion
The Maximum Depth of Binary Tree problem demonstrates the power of recursive and iterative approaches in tree traversal. By understanding these techniques, you can solve a wide variety of tree-related problems efficiently.


# **Invert Binary Tree**

## **Intuition**
To invert a binary tree, we essentially swap the left and right subtrees of each node recursively. The process begins at the root and continues for every node down the tree. This operation transforms the tree into its mirror image.

For example:
- Original Tree:
  ```
      4
     / \
    2   7
   / \ / \
  1  3 6  9
  ```
- Inverted Tree:
  ```
      4
     / \
    7   2
   / \ / \
  9  6 3  1
  ```

### **Steps**:
1. If the tree is empty (`root == null`), return `null`.
2. Recursively invert the left and right subtrees.
3. Swap the left and right children of the current node.

---

## **Java Logic**
```java
// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

public class Solution {
    public TreeNode invertTree(TreeNode root) {
        // Base case: if the tree is empty, return null
        if (root == null) {
            return null;
        }

        // Recursively invert the left and right subtrees
        TreeNode leftSubtree = invertTree(root.left);
        TreeNode rightSubtree = invertTree(root.right);

        // Swap the left and right children
        root.left = rightSubtree;
        root.right = leftSubtree;

        // Return the root of the inverted tree
        return root;
    }
}
```

---

## **Dry Run**

**Input**:  
`root = [4,2,7,1,3,6,9]`

**Step-by-step Execution**:
1. Start at the root (`4`):
    - Recursively invert left subtree (`2`).
    - Recursively invert right subtree (`7`).

2. Invert subtree rooted at `2`:
    - Recursively invert left subtree (`1` → returns `1` since it has no children).
    - Recursively invert right subtree (`3` → returns `3`).
    - Swap `1` and `3`.

   Result for subtree rooted at `2`:
   ```
       2
      / \
     3   1
   ```

3. Invert subtree rooted at `7`:
    - Recursively invert left subtree (`6` → returns `6` since it has no children).
    - Recursively invert right subtree (`9` → returns `9`).
    - Swap `6` and `9`.

   Result for subtree rooted at `7`:
   ```
       7
      / \
     9   6
   ```

4. Swap left and right subtrees of root (`4`):
   ```
       4
      / \
     7   2
    / \ / \
   9  6 3  1
   ```

**Output**: `[4,7,2,9,6,3,1]`

---

## **Time and Space Complexity**

1. **Time Complexity**:
    - Each node is visited once, and we perform \(O(1)\) work at each node (swapping pointers).
    - Total: \(O(n)\), where \(n\) is the number of nodes in the tree.

2. **Space Complexity**:
    - In a recursive implementation, the space required depends on the height of the tree due to the function call stack.
    - Worst case (skewed tree): \(O(n)\).
    - Best case (balanced tree): \(O(\log n)\).

# **Same Tree**

### **Intuition**

To determine if two binary trees are the same, we need to recursively compare:
1. The root values of the two trees.
2. The left subtrees of the two roots.
3. The right subtrees of the two roots.

If all these conditions are satisfied for all corresponding nodes in the two trees, the trees are the same. Otherwise, they are not.

### **Java Logic**
1. Base cases:
    - If both trees are `null`, they are the same.
    - If one tree is `null` and the other is not, they are not the same.
    - If the root values of the two trees are different, they are not the same.

2. Recursive case:
    - Recursively check if the left subtrees and right subtrees of the roots are the same.

### **Java Code**

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base cases
        if (p == null && q == null) return true; // Both are null
        if (p == null || q == null) return false; // One is null
        if (p.val != q.val) return false; // Values don't match

        // Recursive check for left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        // Example 1: p = [1,2,3], q = [1,2,3]
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);

        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);

        SameTree solution = new SameTree();
        System.out.println(solution.isSameTree(p1, q1)); // Output: true

        // Example 2: p = [1,2], q = [1,null,2]
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);

        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);

        System.out.println(solution.isSameTree(p2, q2)); // Output: false

        // Example 3: p = [1,2,1], q = [1,1,2]
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        p3.right = new TreeNode(1);

        TreeNode q3 = new TreeNode(1);
        q3.left = new TreeNode(1);
        q3.right = new TreeNode(2);

        System.out.println(solution.isSameTree(p3, q3)); // Output: false
    }
}
```

### **Dry Run**

#### Example 1: p = [1,2,3], q = [1,2,3]
- `isSameTree(p, q)`:
    - Compare roots: `p.val = 1`, `q.val = 1` → Proceed.
    - Compare left subtrees: `p.left = 2`, `q.left = 2` → Proceed.
        - Compare roots: `p.left.val = 2`, `q.left.val = 2` → Both subtrees null → Return `true`.
    - Compare right subtrees: `p.right = 3`, `q.right = 3` → Proceed.
        - Compare roots: `p.right.val = 3`, `q.right.val = 3` → Both subtrees null → Return `true`.
    - Return `true`.

#### Example 2: p = [1,2], q = [1,null,2]
- `isSameTree(p, q)`:
    - Compare roots: `p.val = 1`, `q.val = 1` → Proceed.
    - Compare left subtrees: `p.left = 2`, `q.left = null` → Return `false`.

#### Example 3: p = [1,2,1], q = [1,1,2]
- `isSameTree(p, q)`:
    - Compare roots: `p.val = 1`, `q.val = 1` → Proceed.
    - Compare left subtrees: `p.left = 2`, `q.left = 1` → Return `false`.

### **Complexity Analysis**

#### Time Complexity
- Each node in both trees is visited once.
- Let `n` be the total number of nodes in the larger tree.
- **Time Complexity:** O(n)

#### Space Complexity
- The space complexity depends on the depth of the recursion.
- In the worst case (skewed tree), the recursion depth is O(n).
- In the best case (balanced tree), the recursion depth is O(log n).
- **Space Complexity:** O(h), where `h` is the height of the tree.

### **Count Good Nodes in Binary Tree**

Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

#### Example 1:

```
Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.
```

#### Example 2:

```
Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
```

#### Example 3:

```
Input: root = [1]
Output: 1
Explanation: Root is considered as good.
```

#### Constraints:
- The number of nodes in the binary tree is in the range `[1, 10^5]`.
- Each node's value is between `[-10^4, 10^4]`.

---

### **Intuition**
A "good node" is defined as a node where, in the path from the root to the node, no other node has a value greater than the node itself. This means we need to track the maximum value encountered along the path from the root to the current node.

1. Start at the root node, which is always a "good node" because there are no other nodes along its path.
2. Traverse the tree using depth-first search (DFS) or breadth-first search (BFS).
3. At each node, compare its value to the maximum value seen so far on the path from the root. If the node's value is greater than or equal to this maximum, it is a "good node."
4. Update the maximum value for the subsequent nodes in the path.

### **Java Logic**
We can solve this problem using a recursive DFS approach. Here's the step-by-step logic:
1. Start at the root node with an initial maximum value equal to the root's value.
2. Check if the current node's value is greater than or equal to the maximum value seen so far.
3. If it is, increment the count of "good nodes."
4. Recurse into the left and right children, passing the updated maximum value.
5. Base case: If the current node is `null`, return 0.

### **Java Implementation**
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public int goodNodes(TreeNode root) {
        return countGoodNodes(root, Integer.MIN_VALUE);
    }

    private int countGoodNodes(TreeNode node, int maxSoFar) {
        if (node == null) return 0;

        int count = 0;

        // Check if the current node is "good"
        if (node.val >= maxSoFar) {
            count++;
        }

        // Update the maximum value for the path
        int newMax = Math.max(maxSoFar, node.val);

        // Recurse into left and right children
        count += countGoodNodes(node.left, newMax);
        count += countGoodNodes(node.right, newMax);

        return count;
    }
}
```

### **Dry Run**
#### Input: `root = [3,1,4,3,null,1,5]`
Tree structure:
```
        3
       / \
      1   4
     /   / \
    3   1   5
```
1. Start at the root (3), `maxSoFar = Integer.MIN_VALUE`.
    - Node 3 is good (`3 >= -∞`). `count = 1`.
    - Update `maxSoFar = 3`.

2. Move to the left child (1), `maxSoFar = 3`.
    - Node 1 is **not good** (`1 < 3`). `count = 0`.
    - Update `maxSoFar = 3`.

3. Move to the left child (3), `maxSoFar = 3`.
    - Node 3 is good (`3 >= 3`). `count = 1`.
    - No further children.

4. Move to the right child of root (4), `maxSoFar = 3`.
    - Node 4 is good (`4 >= 3`). `count = 1`.
    - Update `maxSoFar = 4`.

5. Move to the left child (1), `maxSoFar = 4`.
    - Node 1 is **not good** (`1 < 4`). `count = 0`.

6. Move to the right child (5), `maxSoFar = 4`.
    - Node 5 is good (`5 >= 4`). `count = 1`.

**Total good nodes = 4.**

### **Complexity Analysis**
1. **Time Complexity:**
    - Each node is visited exactly once, and at each node, we perform a constant amount of work (comparison and max update).
    - **O(n),** where `n` is the number of nodes in the binary tree.

2. **Space Complexity:**
    - In the worst case (skewed tree), the recursion stack will go as deep as the height of the tree.
    - Height of the tree is O(h), where `h` is the height of the tree.
    - In the best case (balanced tree), height is O(log n).
    - **O(h),** where `h` is the height of the tree.

### **Summary**
- Intuition: Track the maximum value seen so far on the path to identify "good nodes."
- Approach: Use DFS to traverse the tree, updating the maximum value at each step.
- Complexity:
    - Time: **O(n)**
    - Space: **O(h)**

# Longest Zigzag Path in a Binary Tree

## Problem Statement
Given the root of a binary tree, find the **longest ZigZag path** starting from any node.

A ZigZag path is defined as follows:
- You start from any node.
- You move to a child node, switching between left and right alternately.
- The path ends when movement is no longer possible.

Return the **length** of the longest ZigZag path.

---

## Intuition
1. **Use Depth-First Search (DFS)**: Traverse the tree recursively to explore all paths.
2. **Track ZigZag Movement**: Keep track of direction (`left` or `right`) and maintain the current path length.
3. **Update Global Maximum**: Store the longest ZigZag path encountered so far.
4. **Restart at Each Node**: If a ZigZag path is interrupted, restart counting from that child node.

---

## Java Solution
```java
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private int maxLength = 0;
    
    public int longestZigZag(TreeNode root) {
        if (root == null) return 0;
        dfs(root.left, 1, true);  // Start moving left
        dfs(root.right, 1, false); // Start moving right
        return maxLength;
    }

    private void dfs(TreeNode node, int length, boolean isLeft) {
        if (node == null) return;
        maxLength = Math.max(maxLength, length);
        
        if (isLeft) {
            dfs(node.right, length + 1, false); // Continue zigzag
            dfs(node.left, 1, true);  // Restart at left child (reset length to 1)
        } else {
            dfs(node.left, length + 1, true); // Continue zigzag
            dfs(node.right, 1, false); // Restart at right child (reset length to 1)
        }
    }
}
```

---

## Test Cases
### **Test Case 1: Basic Zigzag**
**Input Tree:**
```
        1
       / \
      2   3
       \  
        4  
       /  
      5  
```
**Expected Output:** `3`

---

### **Test Case 2: Perfect Binary Tree**
**Input Tree:**
```
        1
       / \
      2   3
     / \ / \
    4  5 6  7
```
**Expected Output:** `2`

---

### **Test Case 3: Straight Line (Skewed Tree)**
**Input Tree:**
```
    1
     \
      2
       \
        3
         \
          4
```
**Expected Output:** `1`

---

### **Test Case 4: Deep Zigzag**
**Input Tree:**
```
        1
       /
      2
       \
        3
       /
      4
       \
        5
```
**Expected Output:** `4`

---

### **Test Case 5: Single Node**
**Input Tree:**
```
    1
```
**Expected Output:** `0`

---

## Dry Run
### **Tree Example:**
```
        1
         \
          1
         / \
        1   1
           /
          1
         /
        1
```

### **Step-by-Step Execution:**
1. Start at root `1`:
    - Move **right** to `1` (`length = 1`).
2. Move **left** to `1` (`length = 2`).
3. Move **right** to `1` (`length = 3`). ✅

### **Final Answer:** `3`

---

## Complexity Analysis
- **Time Complexity:** `O(N)`, where `N` is the number of nodes (each node is visited once).
- **Space Complexity:** `O(H)`, where `H` is the height of the tree (recursion stack depth).


# Maximum Level Sum of a Binary Tree

## Problem Statement
Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

### Example 1:
```
Input: root = [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + (-8) = -1.
So we return the level with the maximum sum which is level 2.
```

### Example 2:
```
Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2
```

### Constraints:
- The number of nodes in the tree is in the range `[1, 10^4]`.
- `-10^5 <= Node.val <= 10^5`

---

### **Intuition**
The problem requires us to find the level in a binary tree with the maximum sum of node values. The key observation is:
1. The root is at level 1, its children at level 2, and so on.
2. We need to calculate the sum of all node values at each level and return the smallest level with the maximum sum.
3. A level-order traversal (BFS) naturally processes nodes level by level, making it an ideal approach.

---

### **Iterative Approach (BFS)**
**Algorithm:**
1. Use a queue to perform a **level-order traversal** (BFS).
2. Maintain a variable `maxSum` to track the maximum sum found so far.
3. Maintain a variable `minLevel` to store the level with the maximum sum.
4. At each level:
    - Compute the sum of all node values at that level.
    - Update `maxSum` and `minLevel` accordingly.
5. Return the `minLevel`.

**Time Complexity:** \( O(N) \), since we visit each node once.  
**Space Complexity:** \( O(N) \), for storing nodes in the queue.

#### **Java Code (Iterative - BFS)**
```java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class MaxLevelSum {
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1, minLevel = 1;
        int maxSum = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            if (levelSum > maxSum) {
                maxSum = levelSum;
                minLevel = level;
            }
            level++;
        }
        return minLevel;
    }
}
```

---

### **Recursive Approach (DFS)**
We can also solve this using **Depth-First Search (DFS)** by:
1. Using a **HashMap** to store the sum of values at each level.
2. Performing a DFS traversal, updating the level sums.
3. After traversal, finding the level with the maximum sum.

**Time Complexity:** \( O(N) \), since each node is visited once.  
**Space Complexity:** \( O(H) \), where \( H \) is the tree height (worst case \( O(N) \) for a skewed tree).

#### **Java Code (Recursive - DFS)**
```java
import java.util.*;

public class MaxLevelSumDFS {
    Map<Integer, Integer> levelSum = new HashMap<>();
    
    public int maxLevelSum(TreeNode root) {
        dfs(root, 1);

        int maxSum = Integer.MIN_VALUE, minLevel = 1;
        for (int key : levelSum.keySet()) {
            if (levelSum.get(key) > maxSum) {
                maxSum = levelSum.get(key);
                minLevel = key;
            }
        }
        return minLevel;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null) return;
        levelSum.put(level, levelSum.getOrDefault(level, 0) + node.val);
        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }
}
```

---

### **Comparison of Approaches**
| Approach  | Time Complexity | Space Complexity | When to Use |
|-----------|---------------|----------------|-------------|
| BFS (Iterative) | \( O(N) \) | \( O(N) \) | If memory is not a constraint, easy to implement |
| DFS (Recursive) | \( O(N) \) | \( O(H) \) (worst \( O(N) \)) | If recursion depth is not an issue, stores only necessary level sums |

**Final Thoughts:**
- BFS is more intuitive for level-based problems.
- DFS requires an extra HashMap but avoids an explicit queue.
- BFS is generally preferred due to its simplicity and clear level-order processing.





# Problem: Delete Node in a Binary Search Tree (BST)

### Problem Description:
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

The deletion can be divided into two stages:
1. Search for a node to remove.
2. If the node is found, delete the node.

---

### Intuition:
1. **Search the Tree**: Find the node that needs to be deleted.
2. **Handle Deletion**:
    - If the node has **no children**, just remove it.
    - If the node has **one child**, remove the node and replace it with its child.
    - If the node has **two children**, replace it with its **inorder successor** (smallest node in the right subtree) and then delete the successor.

---

### Algorithm:

1. **Search for the Node**:
    - If the value of the node to be deleted is smaller than the current node, search in the left subtree.
    - If the value of the node to be deleted is larger than the current node, search in the right subtree.

2. **Delete the Node**:
    - If the node has no children, simply return `null`.
    - If the node has one child, return that child.
    - If the node has two children, replace the node's value with its inorder successor and delete the successor.

3. **Find the Inorder Successor**:
    - The inorder successor of a node is the leftmost node in its right subtree.

4. **Return the updated root**.

---

### Java Logic:

```java
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class BSTDeletion {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        // Step 1: Find the node to delete
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node found
            
            // Case 1 & 2: Node has at most one child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 3: Node has two children
            TreeNode successor = findMin(root.right);
            root.val = successor.val;
            root.right = deleteNode(root.right, successor.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        // Test cases can be added here
    }
}
```

---

### Time Complexity:

- **Search**: O(h) where `h` is the height of the tree (in the worst case, this could be O(N) if the tree is skewed).
- **Deletion**:
    - If the node has no children or one child, it takes O(1).
    - If the node has two children, finding the inorder successor takes O(h) and deleting the successor also takes O(h).
- Therefore, the overall time complexity is **O(h)**, where `h` is the height of the tree.

---

### Space Complexity:
- **O(h)**, where `h` is the height of the tree due to the recursion stack.

---
# Binary Search Tree Deletion Examples

## Example 1: Deleting a Node with Two Children
### Original Tree
```
     5
    / \
   3   6
  / \   \
 2   4   7
```

### After Deleting 3

#### Using Successor (Minimum from Right Subtree)
```
     5
    / \
   4   6
  /     \
 2       7
```

#### Using Predecessor (Maximum from Left Subtree)
```
     5
    / \
   2   6
    \   \
     4   7
```

Both are valid BSTs, maintaining all BST properties.

## Example 2: Complex Case - Deleting Root
### Original Tree
```
        8
       / \
      4   12
     / \  / \
    2  6 10  14
```

### After Deleting 8

#### Using Successor (Minimum from Right Subtree)
```
        10
       /  \
      4    12
     / \     \
    2   6     14
```

#### Using Predecessor (Maximum from Left Subtree)
```
        6
       / \
      4   12
     /   / \
    2   10  14
```

## Example 3: Deleting Node with Chain Reaction
### Original Tree
```
     10
    /  \
   5    15
  / \     \
 3   7     20
    /     /
   6     17
```

### After Deleting 5

#### Using Successor (Minimum from Right Subtree)
```
     10
    /  \
   6    15
  / \     \
 3   7     20
          /
         17
```

#### Using Predecessor (Maximum from Left Subtree)
```
     10
    /  \
   3    15
    \     \
     7     20
    /     /
   6     17
```

## Example 4: Deleting from an Unbalanced Tree
### Original Tree
```
      10
     /
    8
   /
  6
 /
4
```

### After Deleting 8

#### Using Successor (Minimum from Right Subtree)
```
      10
     /
    6
   /
  4
```

#### Using Predecessor (Maximum from Left Subtree)
```
      10
     /
    6
   /
  4
```
Note: In this case, both approaches yield the same result due to the tree's structure.

## Key Observations:
1. Both successor and predecessor approaches maintain BST properties:
    - All left subtree nodes < parent node
    - All right subtree nodes > parent node

2. The choice between approaches can affect:
    - Tree balance
    - Height
    - Node distribution

3. Performance implications:
    - If the tree is significantly unbalanced, choosing the approach that works with the shorter subtree might be more efficient
    - In balanced trees, both approaches have similar performance characteristics

4. The resulting tree structure might be different, but both are equally valid BSTs



# Construct Binary Tree from Preorder and Inorder Traversal

## Problem Statement
Given two integer arrays `preorder` and `inorder` where:
- `preorder` is the preorder traversal of a binary tree.
- `inorder` is the inorder traversal of the same tree.

Construct and return the binary tree.

### Example 1:

#### **Input:**
```plaintext
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
```
#### **Output:**
```plaintext
[3,9,20,null,null,15,7]
```
#### **Visualization:**
```
        3
       / \
      9   20
         /  \
        15   7
```

### Example 2:

#### **Input:**
```plaintext
preorder = [-1]
inorder = [-1]
```
#### **Output:**
```plaintext
[-1]
```

## Approach & Intuition
### **Understanding the Traversals**
- **Preorder (Root-Left-Right):** First element is always the root.
- **Inorder (Left-Root-Right):** Helps determine the left and right subtrees.

### **Steps to Construct the Tree**
1. The first element in `preorder` is the root of the tree.
2. Locate this root in `inorder` to determine left and right subtree boundaries.
3. Recursively repeat the process for left and right subtrees using updated indices.
4. Use a **HashMap** for quick lookup of the root index in `inorder`.
5. Pass the updated **preorder index** to recursively determine subtree elements.

## Java Code
```java
import java.util.HashMap;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ConstructBinaryTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, inorderMap, 0, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, Map<Integer, Integer> inorderMap,
                                     int preorderIndex, int left, int right) {
        if (left > right) return null;

        int rootValue = preorder[preorderIndex];
        TreeNode root = new TreeNode(rootValue);

        int inorderIndex = inorderMap.get(rootValue);
        int leftSubtreeSize = inorderIndex - left;

        root.left = buildTreeHelper(preorder, inorderMap, preorderIndex + 1, left, inorderIndex - 1);
        root.right = buildTreeHelper(preorder, inorderMap, preorderIndex + 1 + leftSubtreeSize, inorderIndex + 1, right);

        return root;
    }

    public static void printInorder(TreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }
    }

    public static void main(String[] args) {
        ConstructBinaryTree treeBuilder = new ConstructBinaryTree();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = treeBuilder.buildTree(preorder, inorder);
        System.out.print("Inorder Traversal: ");
        printInorder(root);  // Expected output: 9 3 15 20 7
    }
}
```

## Dry Run (Step-by-Step Execution)
### **Input:**
```plaintext
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
```

### **Step 1: Root Selection from Preorder**
- `preorder[0] = 3` → This is the root of the tree.
- Find `3` in `inorder`, split into left (`[9]`) and right (`[15, 20, 7]`).

### **Step 2: Recursive Construction**
- Left subtree: `preorder[1] = 9`, `inorder = [9]` → Root `9`, no children.
- Right subtree: `preorder[2] = 20`, `inorder = [15, 20, 7]` → Root `20`.
- Continue for `20`:
    - Left: `preorder[3] = 15`, `inorder = [15]` → Root `15`.
    - Right: `preorder[4] = 7`, `inorder = [7]` → Root `7`.

### **Larger Example Dry Run:**
#### **Input:**
```plaintext
preorder = [10, 5, 1, 7, 40, 50]
inorder = [1, 5, 7, 10, 40, 50]
```
#### **Step-by-step Tree Construction:**
1. Root is `10`, found at index `3` in inorder. Left: `[1, 5, 7]`, Right: `[40, 50]`
2. Left subtree root is `5`, found at index `1`. Left: `[1]`, Right: `[7]`
3. `1` becomes left child of `5`, no children.
4. `7` becomes right child of `5`, no children.
5. Right subtree root is `40`, found at index `4`. Left: `[]`, Right: `[50]`
6. `50` becomes right child of `40`.

#### **Final Tree:**
```
        10
       /  \
      5    40
     / \     \
    1   7    50
```

## Complexity Analysis
- **Time Complexity:** `O(N)`, as we traverse each node once and use `O(1)` lookups.
- **Space Complexity:** `O(N)`, due to recursion and HashMap storage.

## Conclusion
This approach efficiently constructs the binary tree using preorder and inorder traversals by leveraging a HashMap for quick lookups and recursive function calls to maintain the preorder index dynamically.

# Construct Binary Tree from Inorder and Postorder Traversal

## Problem Statement
Given two integer arrays `inorder` and `postorder` where:
- `inorder` represents the **inorder traversal** of a binary tree.
- `postorder` represents the **postorder traversal** of the same tree.

Construct and return the binary tree.

### Example 1:
#### **Input**
```java
int[] inorder = {9,3,15,20,7};
int[] postorder = {9,15,7,20,3};
```
#### **Output (Tree Structure)**
```
       3
      / \
     9   20
        /  \
       15   7
```

### Example 2:
#### **Input**
```java
int[] inorder = {-1};
int[] postorder = {-1};
```
#### **Output (Tree Structure)**
```
   -1
```

## Intuition
1. **Postorder Property**: The last element in `postorder` is always the root of the tree.
2. **Inorder Property**: The root's index in `inorder` determines the left and right subtrees.
3. **Recursive Construction**:
    - Identify the root from `postorder`.
    - Find its index in `inorder`.
    - Recursively construct the **right subtree first**, then the **left subtree**.

## Java Solution
```java
import java.util.HashMap;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ConstructBinaryTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Store inorder indices for quick lookup
        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return constructTree(postorder, inorderMap, 0, inorder.length - 1, postorder.length - 1);
    }

    private TreeNode constructTree(int[] postorder, HashMap<Integer, Integer> inorderMap,
                                   int left, int right, int postIndex) {
        if (left > right) {
            return null;
        }

        // Pick the current root from postorder
        int rootVal = postorder[postIndex];
        TreeNode root = new TreeNode(rootVal);

        // Get index in inorder
        int inorderIndex = inorderMap.get(rootVal);

        // Calculate new postIndex for left and right subtree
        int rightSubtreeSize = right - inorderIndex;
        
        // Construct right subtree first
        root.right = constructTree(postorder, inorderMap, inorderIndex + 1, right, postIndex - 1);
        
        // Construct left subtree
        root.left = constructTree(postorder, inorderMap, left, inorderIndex - 1, postIndex - rightSubtreeSize - 1);

        return root;
    }
}
```

## Complexity Analysis
- **Building HashMap:** `O(n)`
- **Tree Construction (Each Node Processed Once):** `O(n)`
- **Total Complexity:** `O(n)`

## Dry Run (Larger Example)
#### **Input**
```java
int[] inorder = {4, 2, 5, 1, 6, 3, 7, 8};
int[] postorder = {4, 5, 2, 6, 7, 8, 3, 1};
```

### **Recursive Call Breakdown**

| Call # | Function Call (constructTree) | Postorder Element Processed | Root Node | Left | Right |
|--------|------------------------------|-----------------------------|-----------|------|-------|
| 1 | `constructTree(postorder, 0, 7, postIndex=7)` | 1 | **1** | `0` | `7` |
| 2 | `constructTree(postorder, 4, 7, postIndex=6)` | 3 | **3** | `4` | `7` |
| 3 | `constructTree(postorder, 6, 7, postIndex=5)` | 8 | **8** | `6` | `7` |
| 4 | `constructTree(postorder, 7, 7, postIndex=4)` | 7 | **7** | `7` | `7` |
| 5 | `constructTree(postorder, 8, 7, postIndex=3)` | - | **NULL** | Out of bounds | NULL |
| 6 | `constructTree(postorder, 6, 6, postIndex=3)` | 6 | **6** | `6` | `6` |
| 7 | `constructTree(postorder, 7, 6, postIndex=2)` | - | **NULL** | Out of bounds | NULL |
| 8 | `constructTree(postorder, 4, 4, postIndex=2)` | - | **NULL** | Out of bounds | NULL |
| 9 | `constructTree(postorder, 0, 2, postIndex=2)` | 2 | **2** | `0` | `2` |
| 10 | `constructTree(postorder, 2, 2, postIndex=1)` | 5 | **5** | `2` | `2` |
| 11 | `constructTree(postorder, 3, 2, postIndex=0)` | - | **NULL** | Out of bounds | NULL |
| 12 | `constructTree(postorder, 0, 0, postIndex=0)` | 4 | **4** | `0` | `0` |
| 13 | `constructTree(postorder, 1, 0, postIndex=-1)` | - | **NULL** | Out of bounds | NULL |

### **Final Constructed Tree**
```
        1
       / \
      2   3
     / \  / \
    4   5 6  8
           /
          7
```

## Conclusion
This approach ensures efficient construction of the binary tree in **O(n) time complexity** while maintaining clarity and avoiding global variables. 🚀


# Construct Binary Tree from Inorder and Postorder Traversal

## Problem Statement
Given two integer arrays `inorder` and `postorder` where:
- `inorder` represents the **inorder traversal** of a binary tree.
- `postorder` represents the **postorder traversal** of the same tree.

Construct and return the binary tree.

### Example 1:
#### **Input**
```java
int[] inorder = {9,3,15,20,7};
int[] postorder = {9,15,7,20,3};
```
#### **Output (Tree Structure)**
```
       3
      / \
     9   20
        /  \
       15   7
```

### Example 2:
#### **Input**
```java
int[] inorder = {-1};
int[] postorder = {-1};
```
#### **Output (Tree Structure)**
```
   -1
```

## Intuition
1. **Postorder Property**: The last element in `postorder` is always the root of the tree.
2. **Inorder Property**: The root's index in `inorder` determines the left and right subtrees.
3. **Recursive Construction**:
    - Identify the root from `postorder`.
    - Find its index in `inorder`.
    - Recursively construct the **right subtree first**, then the **left subtree**.

## Java Solution
```java
import java.util.HashMap;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ConstructBinaryTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Store inorder indices for quick lookup
        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return constructTree(postorder, inorderMap, 0, inorder.length - 1, postorder.length - 1);
    }

    private TreeNode constructTree(int[] postorder, HashMap<Integer, Integer> inorderMap,
                                   int left, int right, int postIndex) {
        if (left > right) {
            return null;
        }

        // Pick the current root from postorder
        int rootVal = postorder[postIndex];
        TreeNode root = new TreeNode(rootVal);

        // Get index in inorder
        int inorderIndex = inorderMap.get(rootVal);

        // Calculate new postIndex for left and right subtree
        int rightSubtreeSize = right - inorderIndex;
        
        // Construct right subtree first
        root.right = constructTree(postorder, inorderMap, inorderIndex + 1, right, postIndex - 1);
        
        // Construct left subtree
        root.left = constructTree(postorder, inorderMap, left, inorderIndex - 1, postIndex - rightSubtreeSize - 1);

        return root;
    }
}
```

## Complexity Analysis
- **Building HashMap:** `O(n)`
- **Tree Construction (Each Node Processed Once):** `O(n)`
- **Total Complexity:** `O(n)`

## Dry Run (Larger Example)
#### **Input**
```java
int[] inorder = {4, 2, 5, 1, 6, 3, 7, 8};
int[] postorder = {4, 5, 2, 6, 7, 8, 3, 1};
```

### **Recursive Call Breakdown**

| Call # | Function Call (constructTree) | Postorder Element Processed | Root Node | Left | Right |
|--------|------------------------------|-----------------------------|-----------|------|-------|
| 1 | `constructTree(postorder, 0, 7, postIndex=7)` | 1 | **1** | `0` | `7` |
| 2 | `constructTree(postorder, 4, 7, postIndex=6)` | 3 | **3** | `4` | `7` |
| 3 | `constructTree(postorder, 6, 7, postIndex=5)` | 8 | **8** | `6` | `7` |
| 4 | `constructTree(postorder, 7, 7, postIndex=4)` | 7 | **7** | `7` | `7` |
| 5 | `constructTree(postorder, 8, 7, postIndex=3)` | - | **NULL** | Out of bounds | NULL |
| 6 | `constructTree(postorder, 6, 6, postIndex=3)` | 6 | **6** | `6` | `6` |
| 7 | `constructTree(postorder, 7, 6, postIndex=2)` | - | **NULL** | Out of bounds | NULL |
| 8 | `constructTree(postorder, 4, 4, postIndex=2)` | - | **NULL** | Out of bounds | NULL |
| 9 | `constructTree(postorder, 0, 2, postIndex=2)` | 2 | **2** | `0` | `2` |
| 10 | `constructTree(postorder, 2, 2, postIndex=1)` | 5 | **5** | `2` | `2` |
| 11 | `constructTree(postorder, 3, 2, postIndex=0)` | - | **NULL** | Out of bounds | NULL |
| 12 | `constructTree(postorder, 0, 0, postIndex=0)` | 4 | **4** | `0` | `0` |
| 13 | `constructTree(postorder, 1, 0, postIndex=-1)` | - | **NULL** | Out of bounds | NULL |

### **Final Constructed Tree**
```
        1
       / \
      2   3
     / \  / \
    4   5 6  8
           /
          7
```

## Conclusion
This approach ensures efficient construction of the binary tree in **O(n) time complexity** while maintaining clarity and avoiding global variables. 🚀



