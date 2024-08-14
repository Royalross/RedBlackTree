import java.util.Random;

public class PerformanceTest {

    private static final int TEST_SIZE = 10;

    public static void main(String[] args) {
        // Measure performance for Red-Black Tree
        measureRedBlackTreePerformance();

        // Measure performance for Binary Search Tree
        measureBinarySearchTreePerformance();
    }

    private static void measureRedBlackTreePerformance() {
        RedBlackTree tree = new RedBlackTree(0);
        Random random = new Random();
        int[] keys = new int[TEST_SIZE];
        for (int i = 0; i < TEST_SIZE; i++) {
            keys[i] = random.nextInt(TEST_SIZE);
        }

        long startTime, endTime;

        // Measure insertion time
        startTime = System.nanoTime();
        for (int key : keys) {
            tree.insert(key);
        }
        // slower because of balancing operations
        endTime = System.nanoTime();
        System.out.println("Red-Black Tree - Insertion Time: " + (endTime - startTime) / 1_000_000 + " ms");

        // Measure search time
        startTime = System.nanoTime();
        for (int key : keys) {
            tree.search(tree.root, key);
        }
        endTime = System.nanoTime();
        System.out.println("Red-Black Tree - Search Time: " + (endTime - startTime) / 1_000_000 + " ms");

        // Measure deletion time
        startTime = System.nanoTime();
        for (int key : keys) {
            tree.deleteNode(key);
        }
        endTime = System.nanoTime();
        System.out.println("Red-Black Tree - Deletion Time: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    private static void measureBinarySearchTreePerformance() {
        BinarySearchTree tree = new BinarySearchTree();
        Random random = new Random();
        int[] keys = new int[TEST_SIZE];
        for (int i = 0; i < TEST_SIZE; i++) {
            keys[i] = random.nextInt(TEST_SIZE);
        }

        long startTime, endTime;

        // Measure insertion time
        startTime = System.nanoTime();
        for (int key : keys) {
            tree.insert(key);
        }
        endTime = System.nanoTime();
        System.out.println("Binary Search Tree - Insertion Time: " + (endTime - startTime) / 1_000_000 + " ms");

        // Measure search time
        startTime = System.nanoTime();
        for (int key : keys) {
            tree.search(tree.root, key);
        }
        endTime = System.nanoTime();
        System.out.println("Binary Search Tree - Search Time: " + (endTime - startTime) / 1_000_000 + " ms");

        // Measure deletion time
        startTime = System.nanoTime();
        for (int key : keys) {
            tree.deleteNode(key);
        }
        endTime = System.nanoTime();
        System.out.println("Binary Search Tree - Deletion Time: " + (endTime - startTime) / 1_000_000 + " ms");
    }
}
