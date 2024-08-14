import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RedBlackTreeTest {
    private RedBlackTree tree;

    @Before
    public void setUp() {
        tree = new RedBlackTree(10); // Initialize with a root node
    }

    @Test
    public void testInsert() {
        tree.insert(15);
        tree.insert(5);

        assertNotNull(tree.search(tree.root, 10));
        assertNotNull(tree.search(tree.root, 15));
        assertNotNull(tree.search(tree.root, 5));
    }

    @Test
    public void testDuplicateInsert() {
        tree.insert(10); // Duplicate insert
        assertEquals(10, tree.root.data);
    }

    @Test
    public void testDelete() {
        tree.insert(15);
        tree.insert(5);
        tree.deleteNode(15);
        tree.deleteNode(5);

        assertNull(tree.search(tree.root, 15));
        assertNull(tree.search(tree.root, 5));
    }

    @Test
    public void testLeftRotate() {
        tree.insert(20);
        tree.insert(30);

        // Assuming the tree performs rotations correctly,
        // after inserting 30, the tree should perform a left rotate to maintain balance.
        TreeNode root = tree.root;
        assertEquals(20, root.data);
        assertEquals(10, root.leftChild.data);
        assertEquals(30, root.rightChild.data);
    }

    @Test
    public void testRightRotate() {
        tree.insert(5);
        tree.insert(1);

        // Assuming the tree performs rotations correctly,
        // after inserting 1, the tree should perform a right rotate to maintain balance.
        TreeNode root = tree.root;
        assertEquals(5, root.data);
        assertEquals(1, root.leftChild.data);
        assertEquals(10, root.rightChild.data);
    }

    @Test
    public void testSearch() {
        tree.insert(15);
        tree.insert(5);

        assertNotNull(tree.search(tree.root, 15));
        assertNotNull(tree.search(tree.root, 5));
        assertNull(tree.search(tree.root, 20));
    }
}
