package binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ZigZagTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        traverse(root, 0, result);
        return result;
    }

    private void traverse(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        // Ensure the result has a list for the current level
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }

        // Add the current node value to the appropriate position
        if (level % 2 == 0) {
            result.get(level).add(node.val); // Add to the end for even levels
        } else {
            result.get(level).add(0, node.val); // Add to the front for odd levels (less efficient with ArrayList)
        }

        // Recur for left and right subtrees
        traverse(node.left, level + 1, result);
        traverse(node.right, level + 1, result);
    }

    // Example Usage
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ZigZagTraversal traversal = new ZigZagTraversal();
        List<List<Integer>> result = traversal.zigzagLevelOrder(root);

        System.out.println(result);
    }


}
