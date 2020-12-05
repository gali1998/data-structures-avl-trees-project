import java.util.LinkedList;

import java.util.List;

/**
 *
 * AVLTree
 *
 * An implementation of a AVL Tree with distinct integer keys and info
 *
 */

public class AVLTree {

	IAVLNode root;
	int size;
	IAVLNode min;
	IAVLNode max;

	/**
	 * public boolean empty()
	 *
	 * returns true if and only if the tree is empty
	 *
	 */
	public boolean empty() { // Complexity: O(1)
		return this.root == null;
	}

	/**
	 * public String search(int k)
	 *
	 * returns the info of an item with key k if it exists in the tree otherwise,
	 * returns null
	 */
	public String search(int k) { // Complexity: O(logn)
		IAVLNode node = this.root;

		return this.search(node, k);
	}

	/**
	 * public int insert(int k, String i)
	 *
	 * inserts an item with key k and info i to the AVL tree. the tree must remain
	 * valid (keep its invariants). returns the number of rebalancing operations, or
	 * 0 if no rebalancing operations were necessary. promotion/rotation - counted
	 * as one rebalnce operation, double-rotation is counted as 2. returns -1 if an
	 * item with key k already exists in the tree.
	 */
	public int insert(int k, String i) { // Complexity: O(logn)
		IAVLNode newNode = new AVLNode(k, i);

		if (this.insertNodeToBinaryTree(newNode, k) == -1) {
			return -1;
		}
		
		
		// maintain members in O(1)
		this.size++;
		
		if (this.min == null || this.min.getKey() > newNode.getKey()) {
			this.min = newNode;
		}
		
		if (this.max == null || this.max.getKey() < newNode.getKey()) {
			this.max = newNode;
		}
		
		int numOfActions = this.rebalance(newNode);
		
		// Make sure each node is correct
		this.updatePath(newNode);

		return numOfActions;
	}

	private int rebalance(IAVLNode node) { // Complexity: O(logn)
		int count = 0;
		IAVLNode newRoot = this.root;

		while (node != null) {
			AVLNode treeNode = (AVLNode) node;

			if (treeNode.leftRankDifference() == 0) {
				if (treeNode.rightRankDifference() == 1) { // Case 1
					treeNode.promote();
					count++;
				}

				else {
					AVLNode child = (AVLNode) node.getLeft();

					// Case 2
					if (child.leftRankDifference() == 1 && child.rightRankDifference() == 2) {
						treeNode.demote();
						this.rotateRight(node);

						count += 2;
					}

					else { // case 3
						treeNode.demote();
						child.demote();
						child = (AVLNode) this.rotateLeft(child);
						child.promote();
						this.rotateRight(node);
						count += 5;
					}
				}

			}

			else if (treeNode.rightRankDifference() == 0) {
				if (treeNode.leftRankDifference() == 1) { // Case 1
					treeNode.promote();
					count++;
				}

				else {
					AVLNode child = (AVLNode) node.getRight();

					// Case 3
					if (child.leftRankDifference() == 1 && child.rightRankDifference() == 2) {
						treeNode.demote();
						child.demote();
						child = (AVLNode) this.rotateRight(child);
						child.promote();
						this.rotateLeft(node);
						count += 5;
					}
					//Case 2
					else {
						treeNode.demote();
						this.rotateLeft(node);
						count += 2;
					}
				}
			}
			
			// update root if needed
			if (node.getParent() == null) {
				newRoot = node;
			}

			node = node.getParent();
		}

		this.root = newRoot;

		return count;
	}

	// This function keeps the nodes along the insertion/ deletion path updated
	private void updatePath(IAVLNode leaf) { // Complexity: O(logn)
		AVLNode node = (AVLNode) leaf;

		while (node != null) {
			node.update();
			node = (AVLNode) node.getParent();
		}
	}

	// This function inserts a new node into the tree, without regard to the balance
	private int insertNodeToBinaryTree(IAVLNode node, int k) { // Complexity: O(logn)
		if (this.empty()) {
			this.root = node;

			return 0;
		}

		IAVLNode leaf = this.findLeaf(this.root, k);

		// The node is already in the tree
		if (leaf == null) {
			return -1;
		}

		if (leaf.getKey() < k) {
			leaf.setRight(node);
		} else {
			leaf.setLeft(node);
		}

		node.setParent(leaf);

		return 0;
	}

	// return null if k is in the tree
	// else return k's parent
	private IAVLNode findLeaf(IAVLNode node, int k) { // Complexity: O(logn)
		if (node.getKey() == k) {
			return null;
		}

		if (node.getKey() < k && node.getRight().isRealNode() == false) {
			return node;
		}

		if (node.getKey() > k && node.getLeft().isRealNode() == false) {
			return node;
		}

		if (node.getKey() < k) {
			return this.findLeaf(node.getRight(), k);
		}

		return this.findLeaf(node.getLeft(), k);
	}

	/**
	 * public int delete(int k)
	 *
	 * deletes an item with key k from the binary tree, if it is there; the tree
	 * must remain valid (keep its invariants). returns the number of rebalancing
	 * operations, or 0 if no rebalancing operations were needed. demotion/rotation
	 * - counted as one rebalnce operation, double-rotation is counted as 2. returns
	 * -1 if an item with key k was not found in the tree.
	 */
	public int delete(int k) {
		return 42; // to be replaced by student code
	}

	/**
	 * public String min()
	 *
	 * Returns the info of the item with the smallest key in the tree, or null if
	 * the tree is empty
	 */
	public String min() { // Complexity: O(1)
		if (this.empty()) {
			return null;
		}

		return this.min.getValue();
	}
	
	// Finds the minimum of the tree, this is good for deletion
	private IAVLNode findMin() { // Complexity: O(logn)
		if (this.empty()) {
			return null;
		}
		
		IAVLNode node = this.getRoot();

		while (node.getLeft() != null) {
			node = node.getLeft();
		}

		return node;
	}

	/**
	 * public String max()
	 *
	 * Returns the info of the item with the largest key in the tree, or null if the
	 * tree is empty
	 */
	public String max() {
		return "42"; // to be replaced by student code
	}

	/**
	 * public int[] keysToArray()
	 *
	 * Returns a sorted array which contains all keys in the tree, or an empty array
	 * if the tree is empty.
	 */
	public int[] keysToArray() { // Complexity: O(n)
		int[] arr = new int[this.size];

		List<IAVLNode> inorder = this.inorderWalk();
		int index = 0;

		for (IAVLNode node : inorder) {
			arr[index] = node.getKey();
			index++;
		}

		return arr;
	}

	/**
	 * public String[] infoToArray()
	 *
	 * Returns an array which contains all info in the tree, sorted by their
	 * respective keys, or an empty array if the tree is empty.
	 */
	public String[] infoToArray() {
		String[] arr = new String[42]; // to be replaced by student code
		return arr; // to be replaced by student code
	}

	/**
	 * public int size()
	 *
	 * Returns the number of nodes in the tree.
	 *
	 * precondition: none postcondition: none
	 */
	public int size() {
		return 42; // to be replaced by student code
	}

	/**
	 * public int getRoot()
	 *
	 * Returns the root AVL node, or null if the tree is empty
	 *
	 * precondition: none postcondition: none
	 */
	public IAVLNode getRoot() {
		return null;
	}

	/**
	 * public string split(int x)
	 *
	 * splits the tree into 2 trees according to the key x. Returns an array [t1,
	 * t2] with two AVL trees. keys(t1) < x < keys(t2). precondition: search(x) !=
	 * null (i.e. you can also assume that the tree is not empty) postcondition:
	 * none
	 */
	public AVLTree[] split(int x) {
		return null;
	}

//	private AVLTree[] split(IAVLNode root, int x) {
//		if (root == null) {
//			
//		}
//	}

	/**
	 * public join(IAVLNode x, AVLTree t)
	 *
	 * joins t and x with the tree. Returns the complexity of the operation
	 * (|tree.rank - t.rank| + 1). precondition: keys(x,t) < keys() or keys(x,t) >
	 * keys(). t/tree might be empty (rank = -1). postcondition: none
	 */
	public int join(IAVLNode x, AVLTree t) {
		return 0;
	}

	/**
	 * 
	 * ********************* UTIL METHODS*********************************
	 * 
	 * 
	 */

	// Returns an ordered list of nodes
	private List<IAVLNode> inorderWalk() { // Complexity: O(n)
		LinkedList<IAVLNode> treeNodes = new LinkedList<>();
		
		
		this.inorderWalk(this.root, treeNodes);
		
		return treeNodes;
	}
	
	private void inorderWalk(IAVLNode node, List<IAVLNode> nodes) { // Complexity: O(n)
		if (node == null || !node.isRealNode()) {
			return;
		}
		
		if (node.getLeft().isRealNode()) {
			this.inorderWalk(node.getLeft(), nodes);
		}
		
		nodes.add(node);
		
		if (node.getRight().isRealNode()) {
			this.inorderWalk(node.getRight(), nodes);
		}
	}

	// Binary search on the tree
	private String search(IAVLNode node, int k) { // Complexity: O(logn)
		if (node == null || node.isRealNode() == false) {
			return null;
		}

		if (node.getKey() == k) {
			return node.getValue();
		}

		if (node.getKey() < k) {
			return this.search(node.getRight(), k);
		}

		return this.search(node.getLeft(), k);
	}

	// Rotate right a tree rooted at y
	public IAVLNode rotateRight(IAVLNode y) { // Complexity: O(1)
		IAVLNode x = y.getLeft();
		IAVLNode z = x.getRight();
		IAVLNode newParent = y.getParent();
		
		if (newParent != null) {
			if (newParent.getRight() == y) {
				newParent.setRight(x);
			} else {
				newParent.setLeft(x);
			}
		}

		x.setRight(y);
		y.setParent(x);
		y.setLeft(z);
		z.setParent(y);
		x.setParent(newParent);

		return x;
	}

	// Rotate left a tree rooted at y
	public IAVLNode rotateLeft(IAVLNode y) { // Complexity: O(1)
		
		IAVLNode x = y.getRight();
		IAVLNode z = x.getLeft();
		IAVLNode newParent = y.getParent();
		
		if (newParent != null) {
			if (newParent.getRight() == y) {
				newParent.setRight(x);
			} else {
				newParent.setLeft(x);
			}
		}

		x.setLeft(y);
		y.setParent(x);
		y.setRight(z);
		z.setParent(y);
		x.setParent(newParent);

		return x;

	}

	static final int COUNT = 10;

	static void print2DUtil(IAVLNode root, int space) {
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
	static void print2D(IAVLNode root) {
		// Pass initial space count as 0
		print2DUtil(root, 0);
	}

	/**
	 * public interface IAVLNode ! Do not delete or modify this - otherwise all
	 * tests will fail !
	 */
	public interface IAVLNode {
		public int getKey(); // returns node's key (for virtuval node return -1)

		public String getValue(); // returns node's value [info] (for virtuval node return null)

		public void setLeft(IAVLNode node); // sets left child

		public IAVLNode getLeft(); // returns left child (if there is no left child return null)

		public void setRight(IAVLNode node); // sets right child

		public IAVLNode getRight(); // returns right child (if there is no right child return null)

		public void setParent(IAVLNode node); // sets parent

		public IAVLNode getParent(); // returns the parent (if there is no parent return null)

		public boolean isRealNode(); // Returns True if this is a non-virtual AVL node

		public void setHeight(int height); // sets the height of the node

		public int getHeight(); // Returns the height of the node (-1 for virtual nodes)
	}

	/**
	 * public class AVLNode
	 *
	 * If you wish to implement classes other than AVLTree (for example AVLNode), do
	 * it in this file, not in another file. This class can and must be modified.
	 * (It must implement IAVLNode)
	 */
	public class AVLNode implements IAVLNode {
		private int key;
		private String info;
		private boolean isVirtual;
		private IAVLNode left;
		private IAVLNode right;
		private IAVLNode parent;
		private int height;
		private int size;
		private int rank;

		public AVLNode() { // Complexity: O(1)
			this.key = -1;
			this.info = null;
			this.isVirtual = true;
			this.height = -1;
			this.rank = -1;
			this.size = 0;
			this.left = null;
			this.right = null;
		}

		public AVLNode(int key, String info) { // Complexity: O(1)
			this.key = key;
			this.info = info;
			this.size = 1;
			this.height = 0;
			this.rank = 0;
			this.isVirtual = false;
			this.left = new AVLNode();
			this.right = new AVLNode();
		}

		public int getKey() { // Complexity: O(1)
			return this.key;
		}

		public String getValue() { // Complexity: O(1)
			return this.info;
		}

		public void setLeft(IAVLNode node) { // Complexity: O(1)
			this.left = node;
			this.update();
		}

		public IAVLNode getLeft() { // Complexity: O(1)
			return this.left;
		}

		public void setRight(IAVLNode node) { // Complexity: O(1)
			this.right = node;
			this.update();
		}

		public IAVLNode getRight() { // Complexity: O(1)
			return this.right;
		}

		public void setParent(IAVLNode node) { // Complexity: O(1)
			this.parent = node;
		}

		public IAVLNode getParent() { // Complexity: O(1)
			return this.parent;
		}

		// Returns True if this is a non-virtual AVL node
		public boolean isRealNode() { // Complexity: O(1)
			return !this.isVirtual;
		}

		public void setHeight(int height) { // Complexity: O(1)
			this.height = height;
		}

		public int getHeight() { // Complexity: O(1)
			return this.height;
		}

		public int getSize() { // Complexity: O(1)
			return this.size;
		}

		public void promote() { // Complexity: O(1)
			this.rank++;
		}

		public void demote() { // Complexity: O(1)
			this.rank--;
		}

		public int getRank() { // Complexity: O(1)
			return this.rank;
		}

		public int rightRankDifference() { // Complexity: O(1)
			if (this.isRealNode() == false) {
				return 0;
			}

			return this.rank - ((AVLNode) this.getRight()).getRank();
		}

		public int leftRankDifference() { // Complexity: O(1)
			if (this.isRealNode() == false) {
				return 0;
			}

			return this.rank - ((AVLNode) this.getLeft()).getRank();
		}

		public void update() { // Complexity: O(1)
			int size = 1;

			if (this.right.isRealNode()) {
				size += ((AVLNode) this.right).getSize();
			}

			if (this.left.isRealNode()) {
				size += ((AVLNode) this.left).getSize();
			}

			this.size = size;

			this.setHeight(1 + Math.max(this.getLeft().getHeight(), this.getRight().getHeight()));
		}
	}
	
	// Remove this
	
	boolean isBST()  { 
        return isBSTUtil(this.root, Integer.MIN_VALUE, 
                               Integer.MAX_VALUE); 
    } 
  
    /* Returns true if the given tree is a BST and its 
      values are >= min and <= max. */
    boolean isBSTUtil(IAVLNode node, int min, int max) 
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
    
    boolean isBalanced(IAVLNode node) 
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
        return false; 
    } 


}
