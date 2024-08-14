public class TreeNode {
    int data;
    TreeNode parent;
    TreeNode leftChild;
    TreeNode rightChild;
    boolean isRed;

    // Constructor
    public TreeNode(int data) {
        this.data = data;
        this.isRed = true; // New nodes are red by default
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }
}
