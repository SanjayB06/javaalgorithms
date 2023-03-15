public class AVL {
    
}

public class AVLTree<T extends Comparable<T>> {

    private class Node {
        T data;
        Node left;
        Node right;
        int height;
        
        Node(T data) {
            this.data = data;
            this.height = 1;
        }
    }
    
    private Node root;
    private BST<T> bst;

    public AVLTree() {
        this.root = null;
        this.bst = new BST<>();
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    private Node insert(Node node, T data) {
        if (node == null) {
            return new Node(data);
        }
        
        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }

        // Update height of ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));
        
        // Check if node is unbalanced
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && data.compareTo(node.left.data) < 0) {
            return rightRotate(node);
        }
        
        // Right Right Case
        if (balance < -1 && data.compareTo(node.right.data) > 0) {
            return leftRotate(node);
        }
        
        // Left Right Case
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        // Right Left Case
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        // No balance issues
        return node;
    }

    private AVLTree<T>.Node leftRotate(AVLTree<T>.Node node) {
        return null;
    }

    private AVLTree<T>.Node rightRotate(AVLTree<T>.Node node) {
        return null;
    }

    private int getBalance(AVLTree<T>.Node node) {
        return 0;
    }

    private int height(AVLTree<T>.Node left) {
        return 0;
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    private Node delete(Node node, T data) {
        if (node == null) {
            return node;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = delete(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = delete(node.right, data);
        } else {
            if ((node.left == null) || (node.right == null)) {
                Node temp = null;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = minValueNode(node.right);
                node.data = temp.data;
                node.right = delete(node.right, temp.data);
            }
        }

        if (node == null) {
            return node;
        }

        // Update height of ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Check if node is unbalanced
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
       
        }
    }

    private AVLTree<T>.Node minValueNode(AVLTree<T>.Node right) {
        return null;
    }
}
