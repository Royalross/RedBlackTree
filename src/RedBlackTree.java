public class RedBlackTree {
    TreeNode root;
    final TreeNode nullNode;

    // Constructor
    public RedBlackTree(int rootData) {
        nullNode = new TreeNode(0);
        nullNode.isRed = false; // null nodes are black
        root = new TreeNode(rootData);
        root.isRed = false; // root is black
        root.leftChild = nullNode;
        root.rightChild = nullNode;
        root.parent = nullNode;
    }

    // Insert a new node
    public void insert(int data) {
        if (search(root, data) != null) {
            System.out.println("Duplicate value not allowed: " + data);
            return;
        }

        TreeNode newNode = new TreeNode(data);
        TreeNode parentNode = nullNode;
        TreeNode currentNode = root;

        while (currentNode != nullNode) {
            parentNode = currentNode;
            if (newNode.data < currentNode.data) {
                currentNode = currentNode.leftChild;
            } else {
                currentNode = currentNode.rightChild;
            }
        }
        newNode.parent = parentNode;
        if (parentNode == nullNode) {
            root = newNode;
        } else if (newNode.data < parentNode.data) {
            parentNode.leftChild = newNode;
        } else {
            parentNode.rightChild = newNode;
        }

        newNode.leftChild = nullNode;
        newNode.rightChild = nullNode;
        newNode.isRed = true;

        insertFixup(newNode);
    }

    // Fix the tree after insertion
    // correct the red-black tree been violated during insertion
    private void insertFixup(TreeNode node) {
        while (node.parent.isRed) {
            if (node.parent == node.parent.parent.leftChild) {
                TreeNode uncleNode = node.parent.parent.rightChild;
                if (uncleNode.isRed) {
                    node.parent.isRed = false;
                    uncleNode.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.rightChild) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rightRotate(node.parent.parent);
                }
            } else {
                TreeNode uncleNode = node.parent.parent.leftChild;
                if (uncleNode.isRed) {
                    node.parent.isRed = false;
                    uncleNode.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.leftChild) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.isRed = false;
    }

    // Delete a node
    public void deleteNode(int data) {
        TreeNode nodeToDelete = search(root, data);
        if (nodeToDelete == null) return;

        TreeNode successorNode = nodeToDelete;
        TreeNode childNode;
        boolean originalColor = successorNode.isRed;
        if (nodeToDelete.leftChild == nullNode) {
            childNode = nodeToDelete.rightChild;
            transplant(nodeToDelete, nodeToDelete.rightChild);
        } else if (nodeToDelete.rightChild == nullNode) {
            childNode = nodeToDelete.leftChild;
            transplant(nodeToDelete, nodeToDelete.leftChild);
        } else {
            successorNode = minimum(nodeToDelete.rightChild);
            originalColor = successorNode.isRed;
            childNode = successorNode.rightChild;
            if (successorNode.parent == nodeToDelete) {
                childNode.parent = successorNode;
            } else {
                transplant(successorNode, successorNode.rightChild);
                successorNode.rightChild = nodeToDelete.rightChild;
                successorNode.rightChild.parent = successorNode;
            }
            transplant(nodeToDelete, successorNode);
            successorNode.leftChild = nodeToDelete.leftChild;
            successorNode.leftChild.parent = successorNode;
            successorNode.isRed = nodeToDelete.isRed;
        }

        if (!originalColor) {
            deleteFixup(childNode);
        }
    }

    // Fix the tree after deletion
    // correct the red-black tree that been violated during deletion
    private void deleteFixup(TreeNode node) {
        while (node != root && !node.isRed) {
            if (node == node.parent.leftChild) {
                TreeNode siblingNode = node.parent.rightChild;
                if (siblingNode.isRed) {
                    siblingNode.isRed = false;
                    node.parent.isRed = true;
                    leftRotate(node.parent);
                    siblingNode = node.parent.rightChild;
                }
                if (!siblingNode.leftChild.isRed && !siblingNode.rightChild.isRed) {
                    siblingNode.isRed = true;
                    node = node.parent;
                } else {
                    if (!siblingNode.rightChild.isRed) {
                        siblingNode.leftChild.isRed = false;
                        siblingNode.isRed = true;
                        rightRotate(siblingNode);
                        siblingNode = node.parent.rightChild;
                    }
                    siblingNode.isRed = node.parent.isRed;
                    node.parent.isRed = false;
                    siblingNode.rightChild.isRed = false;
                    leftRotate(node.parent);
                    node = root;
                }
            } else {
                TreeNode siblingNode = node.parent.leftChild;
                if (siblingNode.isRed) {
                    siblingNode.isRed = false;
                    node.parent.isRed = true;
                    rightRotate(node.parent);
                    siblingNode = node.parent.leftChild;
                }
                if (!siblingNode.rightChild.isRed && !siblingNode.leftChild.isRed) {
                    siblingNode.isRed = true;
                    node = node.parent;
                } else {
                    if (!siblingNode.leftChild.isRed) {
                        siblingNode.rightChild.isRed = false;
                        siblingNode.isRed = true;
                        leftRotate(siblingNode);
                        siblingNode = node.parent.leftChild;
                    }
                    siblingNode.isRed = node.parent.isRed;
                    node.parent.isRed = false;
                    siblingNode.leftChild.isRed = false;
                    rightRotate(node.parent);
                    node = root;
                }
            }
        }
        node.isRed = false;
    }

    // Search for a node
    public TreeNode search(TreeNode node, int key) {
        while (node != nullNode && key != node.data) {
            if (key < node.data) {
                node = node.leftChild;
            } else {
                node = node.rightChild;
            }
        }
        return (node == nullNode) ? null : node; // Return null if the node is not found
    }
    // Rotations
    private void leftRotate(TreeNode node) {
        TreeNode rightChildNode = node.rightChild;
        node.rightChild = rightChildNode.leftChild;
        if (rightChildNode.leftChild != nullNode) {
            rightChildNode.leftChild.parent = node;
        }
        rightChildNode.parent = node.parent;
        if (node.parent == nullNode) {
            root = rightChildNode;
        } else if (node == node.parent.leftChild) {
            node.parent.leftChild = rightChildNode;
        } else {
            node.parent.rightChild = rightChildNode;
        }
        rightChildNode.leftChild = node;
        node.parent = rightChildNode;
    }

    private void rightRotate(TreeNode node) {
        TreeNode leftChildNode = node.leftChild;
        node.leftChild = leftChildNode.rightChild;
        if (leftChildNode.rightChild != nullNode) {
            leftChildNode.rightChild.parent = node;
        }
        leftChildNode.parent = node.parent;
        if (node.parent == nullNode) {
            root = leftChildNode;
        } else if (node == node.parent.rightChild) {
            node.parent.rightChild = leftChildNode;
        } else {
            node.parent.leftChild = leftChildNode;
        }
        leftChildNode.rightChild = node;
        node.parent = leftChildNode;
    }

    // Utility functions
    private void transplant(TreeNode oldNode, TreeNode newNode) {
        if (oldNode.parent == nullNode) {
            root = newNode;
        } else if (oldNode == oldNode.parent.leftChild) {
            oldNode.parent.leftChild = newNode;
        } else {
            oldNode.parent.rightChild = newNode;
        }
        newNode.parent = oldNode.parent;
    }

    private TreeNode minimum(TreeNode node) {
        while (node.leftChild != nullNode) {
            node = node.leftChild;
        }
        return node;
    }
}
