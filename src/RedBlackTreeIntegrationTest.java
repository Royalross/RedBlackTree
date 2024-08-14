import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RedBlackTreeIntegrationTest {
    private RedBlackTree tree;

    @Before
    public void setUp() {
        tree = new RedBlackTree(50);
    }

    @Test
    public void testInsertAndDelete() {
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        assertNotNull(tree.search(tree.root, 30));
        assertNotNull(tree.search(tree.root, 70));
        assertNotNull(tree.search(tree.root, 20));
        assertNotNull(tree.search(tree.root, 40));
        assertNotNull(tree.search(tree.root, 60));
        assertNotNull(tree.search(tree.root, 80));

        tree.deleteNode(70);
        tree.deleteNode(30);

        assertNull(tree.search(tree.root, 70));
        assertNull(tree.search(tree.root, 30));
    }

    @Test
    public void testTreeProperties() {
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        // Check Red-Black Tree properties
        assertFalse(tree.root.isRed); // Root is always black

        checkRedBlackProperties(tree.root);
    }

    private void checkRedBlackProperties(TreeNode node) {
        if (node == tree.nullNode) return;

        // Check that no red node has a red child
        if (node.isRed) {
            assertFalse(node.leftChild.isRed);
            assertFalse(node.rightChild.isRed);
        }

        // Recursively check properties for left and right children
        checkRedBlackProperties(node.leftChild);
        checkRedBlackProperties(node.rightChild);
    }
}
