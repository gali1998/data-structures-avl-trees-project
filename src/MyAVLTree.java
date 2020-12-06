import java.util.LinkedList;
import java.util.List;

/**
 *
 * AVLTree
 *
 * An implementation of a AVL Tree with distinct integer keys and info
 *
 */

public class MyAVLTree {
	
	IAVLNode root;
	int size;

	/**
	 * public boolean empty()
	 *
	 * returns true if and only if the tree is empty
	 *
	 */
	public boolean empty() {
		return false; // to be replaced by student code
	}

	/**
	 * public String search(int k)
	 *
	 * returns the info of an item with key k if it exists in the tree otherwise,
	 * returns null
	 */
	public String search(int k) {	
		IAVLNode node = this.root;
		
		return this.search(node, k);
	}
	
	private String search(IAVLNode node, int k) {
		if (node == null) {
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

	/**
	 * public int insert(int k, String i)
	 *
	 * inserts an item with key k and info i to the AVL tree. the tree must remain
	 * valid (keep its invariants). returns the number of rebalancing operations, or
	 * 0 if no rebalancing operations were necessary. promotion/rotation - counted
	 * as one rebalnce operation, double-rotation is counted as 2. returns -1 if an
	 * item with key k already exists in the tree.
	 */
	public int insert(int k, String i) {
		return 42; // to be replaced by student code
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
	public String min() {
		if (this.empty()) {
			return null;
		}
		
		IAVLNode node = this.getRoot();
		
		while(node.getLeft() != null) {
			node = node.getLeft();
		}
		
		return node.getValue();
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
	public int[] keysToArray() {
		int[] arr = new int[this.size()];
		
		List<IAVLNode> inorder = this.inorderWalk();
		
		int index = 0;
		
		for(IAVLNode node : inorder) {
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
	
	private List<IAVLNode> inorderWalk() {
		List<IAVLNode> treeNodes = new LinkedList<MyAVLTree.IAVLNode>();
		IAVLNode node = this.getRoot();
		
		this.inorderWalk(node, treeNodes);
		
		return treeNodes;
	}
	
	private void inorderWalk(IAVLNode node, List<IAVLNode> treeNodes) {
		this.inorderWalk(node.getLeft(), treeNodes);
		treeNodes.add(node);
		this.inorderWalk(node.getRight(), treeNodes);
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
		
		public AVLNode() {
			this(-1, null);
			
			this.isVirtual = true;
			this.height = -1;
			this.rank = -1;
		}
		
		public AVLNode(int key, String info) {
			this.key = key;
			this.info = info;
			this.rank = 0;
			this.height = 0;
			this.isVirtual = false;
		}

		public int getKey() {
			return this.key;
		}

		public String getValue() {
			return this.info;
		}

		public void setLeft(IAVLNode node) {
			this.left = node;
			this.update();
		}

		public IAVLNode getLeft() {
			return this.left;
		}

		public void setRight(IAVLNode node) {
			this.right = node;
			this.update();
		}

		public IAVLNode getRight() {
			return this.right;
		}

		public void setParent(IAVLNode node) {
			this.parent = node;
			this.update();
		}

		public IAVLNode getParent() {
			return this.parent;
		}

		// Returns True if this is a non-virtual AVL node
		public boolean isRealNode() {
			return !this.isVirtual;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getHeight() {
			return this.height;
		}
		
		public int getSize() {
			return this.size;
		}
		
		public void promote() {
			this.rank++;
		}
		
		public void demote() {
			this.rank--;
		}
		
		public void update() {
			int size = 1;
			
			if (this.right.isRealNode()) {
				size += ((AVLNode)this.right).getSize();
			}
			
			if (this.left.isRealNode()) {
				size += ((AVLNode)this.left).getSize();
			}
		}
	}

}
