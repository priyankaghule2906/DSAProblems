package binarytree;



public class BSTDeletion {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        // Step 1: Find the node to delete
        if (key < root.val) {
            System.out.println("Going left: " + root.val);
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            System.out.println("Going right: " + root.val);
            root.right = deleteNode(root.right, key);
        } else {
            // Node found
            System.out.println("Found node to delete: " + root.val);

            // Case 1 & 2: Node has at most one child
            if (root.left == null) {
                System.out.println("Node has only right child or no child. Returning right subtree.");
                return root.right;
            }
            if (root.right == null) {
                System.out.println("Node has only left child. Returning left subtree.");
                return root.left;
            }

            // Case 3: Node has two children
            System.out.println("Node has two children. Finding inorder successor...");
            TreeNode successor = findMin(root.right);
            System.out.println("Inorder successor found: " + successor.val);

            // Step 4: Replace value of root with successor's value
            root.val = successor.val;
            System.out.println("Replaced node value with " + successor.val);

            // Step 5: Delete the inorder successor node
            root.right = deleteNode(root.right, successor.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        System.out.println("Finding inorder successor (smallest node in right subtree)...");
        while (node.left != null) {
            node = node.left;
        }
        System.out.println("Inorder successor is: " + node.val);
        return node;
    }

    // Helper function to print BST inorder
    public static void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        // Construct BST
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(8);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(20);

        // Print initial tree
        System.out.println("Initial BST (inorder):");
        inorderTraversal(root);
        System.out.println("\n");

        // Delete node 5
        BSTDeletion bst = new BSTDeletion();
        root = bst.deleteNode(root, 5);

        // Print final tree
        System.out.println("\nFinal BST (inorder) after deleting 5:");
        inorderTraversal(root);
        System.out.println();
    }
}

