package src;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import src.AVLTree;
import src.AVLTree.IAVLNode;

public class TestsUtil {
	public static AVLTree createTreeFrom1ToSize(int size) {
		AVLTree tree = new AVLTree();

		for (int i = 1; i <= size; i++) {
			tree.insert(i, String.valueOf(i));
		}

		return tree;
	}
	
	/* @pre: x <= y*/
	public static AVLTree getTreeFromXToY(int x, int y) {
		AVLTree tree = new AVLTree();

		for (int i = x; i <= y; i++) {
			tree.insert(i, String.valueOf(i));
		}

		return tree;
	}

	public static AVLTree createTreeFromSizeTo1(int size) {
		AVLTree tree = new AVLTree();

		for (int i = size; i >= 1; i--) {
			tree.insert(i, String.valueOf(i));
		}

		return tree;
	}

	public static AVLTree getRandomTreeOfSize(int size) {
		Random rand = new Random();
		AVLTree tree = new AVLTree();
		List<Integer> values = new LinkedList<Integer>();

		for (int i = 0; i < size; i++) {
			int val = rand.nextInt(1000);

			while (values.contains(val)) {
				val = rand.nextInt(1000);
			}

			values.add(val);

			tree.insert(val, String.valueOf(val));
		}

		return tree;
	}
	
	public static boolean isBST(AVLTree tree)  { 
        return isBSTUtil(tree.root, Integer.MIN_VALUE, 
                               Integer.MAX_VALUE); 
    } 
  
    /* Returns true if the given tree is a BST and its 
      values are >= min and <= max. */
    public static boolean isBSTUtil(IAVLNode node, int min, int max) 
    { 
        /* an empty tree is BST */
        if (node == null || node.isRealNode() == false) 
            return true; 
  
        /* false if this node violates the min/max constraints */
        if (node.getKey() < min || node.getKey() > max) 
            return false; 
  
        /* otherwise check the subtrees recursively 
        tightening the min/max constraints */
        // Allow only distinct values 
        return (isBSTUtil(node.getLeft(), min, node.getKey()-1) && 
                isBSTUtil(node.getRight(), node.getKey()+1, max)); 
    } 
    
    public static boolean isBalanced(IAVLNode node) 
    { 
        int lh; /* for height of left subtree */
  
        int rh; /* for height of right subtree */
  
        /* If tree is empty then return true */
        if (node == null || node.isRealNode() == false) 
            return true; 
  
        /* Get the height of left and right sub trees */
        lh = node.getLeft().getHeight(); 
        rh = node.getRight().getHeight(); 
  
        if (Math.abs(lh - rh) <= 1
            && isBalanced(node.getLeft()) 
            && isBalanced(node.getRight())) 
            return true; 
  
        /* If we reach here then tree is not height-balanced */
		System.out.println("Not balanced on node " + node.getKey());
        return false;
    }
    
    static final int COUNT = 5;

	public static void print2DUtil(IAVLNode root, int space) {
		// Base case
		if (root == null)
			return;

		// Increase distance between levels
		space += COUNT;

		// Process right child first
		print2DUtil(root.getRight(), space);

		// Print current node after space
		// count
		System.out.print("\n");
		for (int i = COUNT; i < space; i++)
			System.out.print(" ");
		System.out.print(root.getKey() + "\n");

		// Process left child
		print2DUtil(root.getLeft(), space);
	}

	// Wrapper over print2DUtil()
	public static void print2D(IAVLNode root) {
		// Pass initial space count as 0
		print2DUtil(root, 0);
	}
	
	public static boolean areRanksHights(AVLTree tree) {
		return areRanksHights(tree.root);
	}
	
	private static boolean areRanksHights(IAVLNode node) {
		if (node == null) {
			return true;
		}
		
		if (node.getHeight() != node.getRank()) {
			return false;
		}
		
		return areRanksHights(node.getLeft()) && areRanksHights(node.getRight());
	}

}
