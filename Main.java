class Node {
    int key;
    Node left, right;
    public int height;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}


class BST {
    Node root;

    public BST() {
        root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    public Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    public void inorder() {
        inorderRec(root);
    }

    public void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }

    // public static void main(String[] args) {
    //     BST tree = new BST();

    //     tree.insert(50);
    //     tree.insert(30);
    //     tree.insert(20);
    //     tree.insert(40);
    //     tree.insert(70);
    //     tree.insert(60);
    //     tree.insert(80);

    //     tree.inorder();
    // }
}


class AVLTree extends BST  {
    Node root;

    public AVLTree() {
        super();
        root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    public void inorder() {
        inorderRec(root);
    }

    @Override
    public Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
        else // Duplicate keys not allowed
            return root;

        // Update height of current node
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Get the balance factor of this node
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && key < root.left.key)
            return rightRotate(root);

        // Right Right Case
        if (balance < -1 && key > root.right.key)
            return leftRotate(root);

        // Left Right Case
        if (balance > 1 && key > root.left.key) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && key < root.right.key) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Function is being inherited from parent BST class

    /*public void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }*/

    // Utility functions to get height and balance factor of a node
    public int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    public int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    // Rotation functions
    public Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    public Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // public static void main(String[] args) {
    //     AVLTree tree = new AVLTree();

    //     tree.insert(50);
    //     tree.insert(53);
    //     tree.insert(20);
    //     tree.insert(40);
    //     tree.insert(1);
    //     tree.insert(60);
    //     tree.insert(614);

    //     tree.inorder();
    // }



}


public class Main {
    public static void main(String[] args) {
        AVLTree avltree = new AVLTree();
        avltree.insert(5);
        avltree.insert(10);
        avltree.insert(15);
        avltree.insert(20);
        avltree.insert(25);
        avltree.insert(30);

        System.out.println("AVL Output: ");
        avltree.inorder();
        System.out.println(" ");
        System.out.println("BST Output: ");
        BST bsttree = new BST();
        bsttree.insert(5);
        bsttree.insert(10);
        bsttree.insert(15);
        bsttree.insert(20);
        bsttree.insert(25);
        bsttree.insert(30);

        bsttree.inorder();

        
    }
}