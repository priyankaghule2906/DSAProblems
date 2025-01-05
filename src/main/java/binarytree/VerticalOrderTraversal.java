package binarytree;

import org.junit.Test;

import java.util.*;

public class VerticalOrderTraversal {

    class NodeInfo {
        TreeNode node;
        int row, col;

        NodeInfo(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }


    @Test
    public void test(){
        // Test Case 1
//        TreeNode root1 = new TreeNode(3);
//        root1.left = new TreeNode(9);
//        root1.right = new TreeNode(20);
//        root1.right.left = new TreeNode(15);
//        root1.right.right = new TreeNode(7);
//        System.out.println(verticalTraversal(root1)); // [[9], [3, 15], [20], [7]]

        // Test Case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(7);
        System.out.println(verticalTraversal(root2)); // [[4], [2], [1, 5, 6], [3], [7]]

    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // TreeMap to store column -> PriorityQueue (sorted by row, then value)
        TreeMap<Integer, PriorityQueue<NodeInfo>> colMap = new TreeMap<>();

        // Queue for level order traversal
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.offer(new NodeInfo(root, 0, 0));

        // Process nodes level by level
        while (!queue.isEmpty()) {
            NodeInfo curr = queue.poll();

            // Add current node to its column's priority queue
            colMap.putIfAbsent(curr.col, new PriorityQueue<>((a, b) -> {
                if (a.row != b.row) return a.row - b.row;
                return a.node.val - b.node.val;
            }));
            colMap.get(curr.col).offer(curr);

            // Process children
            if (curr.node.left != null) {
                queue.offer(new NodeInfo(curr.node.left, curr.row + 1, curr.col - 1));
            }
            if (curr.node.right != null) {
                queue.offer(new NodeInfo(curr.node.right, curr.row + 1, curr.col + 1));
            }
        }

        // Build result list from TreeMap
        List<List<Integer>> result = new ArrayList<>();
        for (PriorityQueue<NodeInfo> pq : colMap.values()) {
            List<Integer> colNodes = new ArrayList<>();
                while (!pq.isEmpty()) {
                    colNodes.add(pq.poll().node.val);
                }
            result.add(colNodes);
        }

        return result;
    }
}
