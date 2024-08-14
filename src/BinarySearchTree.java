class BinarySearchTree {
    TreeNode root;

    public void insert(int key) {
        root = insert(root, key);
    }
    private TreeNode insert(TreeNode node, int key) {
        if (node == null) {
            return new TreeNode(key);
        }
        if (key < node.data) {
            node.leftChild = insert(node.leftChild, key);
        } else if (key > node.data) {
            node.rightChild = insert(node.rightChild, key);
        } else {
            // Duplicate value found
            System.out.println("Duplicate value not allowed: " + key);
        }
        return node;
    }
    public TreeNode search(TreeNode root, int key) {
        TreeNode current = root;
        while (current != null && current.data != key) {
            if (key < current.data) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return current;
    }

    public void deleteNode(int key) {
        root = deleteNode(root, key);
    }

    private TreeNode deleteNode(TreeNode node, int key) {
        if (node == null) {
            return node;
        }

        if (key < node.data) {
            node.leftChild = deleteNode(node.leftChild, key);
        } else if (key > node.data) {
            node.rightChild = deleteNode(node.rightChild, key);
        } else {
            if (node.leftChild == null) {
                return node.rightChild;
            } else if (node.rightChild == null) {
                return node.leftChild;
            }

            node.data = minValue(node.rightChild);
            node.rightChild = deleteNode(node.rightChild, node.data);
        }
        return node;
    }

    private int minValue(TreeNode node) {
        int minValue = node.data;
        while (node.leftChild != null) {
            minValue = node.leftChild.data;
            node = node.leftChild;
        }
        return minValue;
    }

}
