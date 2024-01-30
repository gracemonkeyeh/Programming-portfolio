import com.sun.source.tree.Tree;

/**
 * ScapeGoat Tree class
 *
 * This class contains some of the basic code for implementing a ScapeGoat tree.
 * This version does not include any of the functionality for choosing which node
 * to scapegoat.  It includes only code for inserting a node, and the code for rebuilding
 * a subtree.
 */

public class SGTree {

    // Designates which child in a binary tree
    enum Child {LEFT, RIGHT}
    public static boolean startCount = true;
    public static boolean baseReached = false;

    public static int enumerate = 0; // enumerator for in order traversal
    public static int iterator = 0; // for build tree method
    public static int leftI = 0;
    public static int rightI = 0;

    /**
     * TreeNode class.
     *
     * This class holds the data for a node in a binary tree.
     *
     * Note: we have made things public here to facilitate problem set grading/testing.
     * In general, making everything public like this is a bad idea!
     *
     */
    public static class TreeNode {
        int key;
        public TreeNode left = null;
        public TreeNode right = null;

        TreeNode(int k) {
            key = k;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;

    /**
     * Counts the number of nodes in the specified subtree
     *
     * @param node  the parent node, not to be counted
     * @param child the specified subtree
     * @return number of nodes
     */
    public int countNodes(TreeNode node, Child child) {
        // TODO: Implement this

        if (startCount) {
            baseReached = false; // reset from previous test case
        }


        if (startCount) { // get the new subtree's root
            if (child == Child.LEFT) {
                node = node.left;
                startCount = false;
            } else if (child == Child.RIGHT) {
                node = node.right;
                startCount = false;
            }

            if (node == null) {
                startCount = true; // reset before returning
                return 0; // there is no new root to be called the child
            }
        }

        int count = bstSearch(node);
        startCount = true; // reset before returning

        return count;
    }

    public int bstSearch(TreeNode node) {
        if (node == null) {
            baseReached = true;
            return 0;
        }

        return 1 + bstSearch(node.left) + bstSearch(node.right);
    }

    /**
     * Builds an array of nodes in the specified subtree
     *
     * @param node  the parent node, not to be included in returned array
     * @param child the specified subtree
     * @return array of nodes
     */
    public TreeNode[] enumerateNodes(TreeNode node, Child child) {
        // TODO: Implement this
        int nodeCount = countNodes(node, child);
        TreeNode[] arr = new TreeNode[nodeCount];

        // get the new root for the in order traversal
        if (child == Child.LEFT) {
            node = node.left;
        } else if (child == Child.RIGHT) {
            node = node.right;
        }

        inOrder(node, arr); // the arr gets modified due to pass by ref
        enumerate = 0; // reset the enumeartor

        return arr;
    }

    public void inOrder(TreeNode node, TreeNode[] arr) {
        if (node == null) {
            return;
        }
        inOrder(node.left, arr);
        arr[enumerate] = node;
        enumerate = enumerate + 1;
        inOrder(node.right, arr);

    }

    /**
     * Builds a tree from the list of nodes
     * Returns the node that is the new root of the subtree
     *
     * @param nodeList ordered array of nodes
     * @return the new root node
     */
    public TreeNode buildTree(TreeNode[] nodeList) {
        // TODO: Implement this


        return buildBalanced(nodeList, 0, nodeList.length - 1);
    }

    public TreeNode buildBalanced(TreeNode[] nodeList, int left, int right) {

        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        TreeNode temp = new TreeNode(nodeList[mid].key);
        temp.left = buildBalanced(nodeList, left, mid - 1);
        temp.right = buildBalanced(nodeList, mid + 1, right);
        return temp;
        

    }

    /**
    * Rebuilds the specified subtree of a node
    * 
    * @param node the part of the subtree to rebuild
    * @param child specifies which child is the root of the subtree to rebuild
    */
    public void rebuild(TreeNode node, Child child) {
        // Error checking: cannot rebuild null tree
        if (node == null) return;
        // First, retrieve a list of all the nodes of the subtree rooted at child
        TreeNode[] nodeList = enumerateNodes(node, child);
        // Then, build a new subtree from that list
        TreeNode newChild = buildTree(nodeList);
        // Finally, replace the specified child with the new subtree
        if (child == Child.LEFT) {
            node.left = newChild;
        } else if (child == Child.RIGHT) {
            node.right = newChild;
        }
    }

    /**
    * Inserts a key into the tree
    *
    * @param key the key to insert
    */
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }

        TreeNode node = root;

        while (true) {
            if (key <= node.key) {
                if (node.left == null) break;
                node = node.left;
            } else {
                if (node.right == null) break;
                node = node.right;
            }
        }

        if (key <= node.key) {
            node.left = new TreeNode(key);
        } else {
            node.right = new TreeNode(key);
        }
    }


    // Simple main function for debugging purposes
    public static void main(String[] args) {
        SGTree tree = new SGTree(); // 5 nodes in right subtree
        for (int i = 0; i < 6; i++) {
            tree.insert(i);
        }
        /*tree.insert(20);
        tree.insert(35);
        tree.insert(100);
        tree.insert(19);
        tree.insert(18);
        tree.insert(17);*/
        int retVal = tree.countNodes(tree.root, Child.RIGHT);
        //System.out.println(retVal);


        SGTree tree2 = new SGTree(); // 7 nodes in left subtree
        for (int i = 7; i > 0; i--) {
            tree2.insert(i);
        }
        int retVal2 = tree2.countNodes(tree2.root, Child.LEFT);
        //System.out.println(retVal2);
        tree.enumerateNodes(tree.root, Child.RIGHT);




        //tree.rebuild(tree.root, Child.RIGHT);
    }
}
