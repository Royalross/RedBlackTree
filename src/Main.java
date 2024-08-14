import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
    private RedBlackTree tree;

    public Main(RedBlackTree tree) {
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawTree(graphics, tree.root, getWidth() / 2, 30, getWidth() / 4);
    }

    private void drawTree(Graphics graphics, TreeNode node, int x, int y, int xOffset) {
        if (node == tree.nullNode) return;

        graphics.setColor(node.isRed ? Color.RED : Color.BLACK);
        graphics.fillOval(x - 15, y - 15, 30, 30);
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.valueOf(node.data), x - 10, y + 5);

        if (node.leftChild != tree.nullNode) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(x, y, x - xOffset, y + 50);
            drawTree(graphics, node.leftChild, x - xOffset, y + 50, xOffset / 2);
        }

        if (node.rightChild != tree.nullNode) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(x, y, x + xOffset, y + 50);
            drawTree(graphics, node.rightChild, x + xOffset, y + 50, xOffset / 2);
        }
    }

    public static void main(String[] args) {
        System.out.print("What number would be the root? ");
        try (Scanner scanner = new Scanner(System.in)) {
            int rootData = scanner.nextInt();
            scanner.nextLine();

            RedBlackTree tree = new RedBlackTree(rootData);

            JFrame frame = new JFrame();
            Main panel = new Main(tree);
            frame.add(panel);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            while (true) {
                System.out.println("Type 'add <#>', 'delete <#>' to modify the tree, or 'exit' to quit");
                String command = scanner.nextLine();
                if (command.equalsIgnoreCase("exit")) {
                    break;
                }
                StringTokenizer tokenizer = new StringTokenizer(command);

                if (tokenizer.hasMoreTokens()) {
                    String operation = tokenizer.nextToken();

                    if (operation.equalsIgnoreCase("add") && tokenizer.hasMoreTokens()) {
                        try {
                            int value = Integer.parseInt(tokenizer.nextToken());
                            tree.insert(value);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for add operation. Please enter a valid number.");
                        }
                    } else if (operation.equalsIgnoreCase("delete") && tokenizer.hasMoreTokens()) {
                        try {
                            int value = Integer.parseInt(tokenizer.nextToken());
                            tree.deleteNode(value);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for delete operation. Please enter a valid number.");
                        }
                    } else {
                        System.out.println("Invalid command. Please enter 'add <#>' or 'delete <#>'.");
                    }
                } else {
                    System.out.println("Invalid command. Please enter 'add <#>' or 'delete <#>'.");
                }

                panel.repaint();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for root data. Please enter a valid number.");
        }
    }
}
