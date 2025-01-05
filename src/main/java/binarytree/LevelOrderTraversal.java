package binarytree;

import org.junit.Test;

import java.util.*;

public class LevelOrderTraversal {

    @Test
    public void test(){
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        printTestResultZigzag(root1, "Test Case 1");

        // Test Case 2: Single Node
        TreeNode root2 = new TreeNode(1);
        printTestResult(root2, "Test Case 2");

        // Test Case 3: Empty Tree
        TreeNode root3 = null;
        printTestResult(root3, "Test Case 3");

        // Test Case 4: Tree with Only Left Children
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        root4.left.left.left = new TreeNode(4);
        printTestResult(root4, "Test Case 4");

        // Test Case 5: Tree with Only Right Children
        TreeNode root5 = new TreeNode(1);
        root5.right = new TreeNode(2);
        root5.right.right = new TreeNode(3);
        root5.right.right.right = new TreeNode(4);
        printTestResult(root5, "Test Case 5");

        // Test Case 6: Unbalanced Tree
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(2);
        root6.right = new TreeNode(3);
        root6.left.right = new TreeNode(4);
        root6.right.right = new TreeNode(5);
        printTestResult(root6, "Test Case 6");
    }

    void printTestResult(TreeNode root, String testCaseName) {
        List<List<Integer>> result = levelOrder(root);
        System.out.println(testCaseName + ": " + result);
    }

    void printTestResultZigzag(TreeNode root, String testCaseName) {
        List<List<Integer>> result = zigzaglevelOrder(root);
        System.out.println(testCaseName + ": " + result);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root == null) return result;
        q.offer(root);

        while (!q.isEmpty()){
            int level = q.size();
            List<Integer> current = new ArrayList<>();

            for(int i=0;i<level;i++){
                TreeNode node = q.poll();
                current.add(node.val);

                if(node.left!=null){
                    q.offer(node.left);
                }
                if(node.right!=null){
                    q.offer(node.right);
                }
            }

            result.add(current);
        }
        return result;
    }


    public List<List<Integer>> zigzaglevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root == null) return result;
        q.offer(root);
        boolean leftToRight = true;
        while (!q.isEmpty()){
            int level = q.size();
            List<Integer> current = new ArrayList<>();

            for(int i=0;i<level;i++) {
                TreeNode node = q.poll();
                current.add(node.val);

                if(node.left!=null){
                    q.offer(node.left);
                }
                if(node.right!=null){
                    q.offer(node.right);
                }
            }

            if(!leftToRight){
                Collections.reverse(current);
            }

            result.add(current);
            leftToRight = !leftToRight;
        }


        return result;
    }
}
