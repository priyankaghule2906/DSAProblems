package binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumDepthBinaryTree {
    @Test
    public void test(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Maximum Depth: " + maxDepth(root)); // Output: 3
    }

    // iterative solution using bfs or level order traversal
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth =0;
        while (!q.isEmpty()){
            int levelSize = q.size();
            depth++;
            for(int i=0;i<levelSize;i++) {
                TreeNode node = q.poll();

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }


        }

        return depth;
    }

}

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

/*
*
*
Time Complexity: 𝑂(𝑛)
O(n), where n is the number of nodes in the tree. Each node is processed exactly once.
Space Complexity: 𝑂(𝑤)
O(w), where w is the maximum width of the tree (number of nodes at the widest level). In the worst case (e.g., a complete binary tree),
𝑤≈𝑛/2
w≈n/2.
This iterative BFS approach is efficient and avoids the risk of stack overflow that could happen in a recursive DFS approach for very deep trees.
* */