import jdk.jshell.spi.ExecutionControl;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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

	/**
	 * public boolean empty()
	 *
	 * returns true if and only if the tree is empty
	 *
	 */
	public boolean empty() {
		// These are all equivalent expressions
		return ((root == null) || (!root.isRealNode())) && (size == 0);
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
		IAVLNode toDelete = this.searchNode(k);
		if (toDelete == null) {
			return -1;
		}
		// first we delete like every BST
		IAVLNode replacement = this.getDeletedReplacement(toDelete);
		IAVLNode parent = this.replace(toDelete, replacement);
		// rebalancing and returning the number of rebalancing operations
		return this.recursiveDeleteRebalancing(parent, replacement);
	}

	/**
	 * recursively apply rebalancing operations after deletion
	 * @param parent - parent of the deleted node
	 * @param deleteReplacement - the replacement node, AFTER it has been deleted from the tree and applied as
	 *                            parent's child
	 * @return # of rebalancing operations
	 */
	private int recursiveDeleteRebalancing(IAVLNode parent, IAVLNode deleteReplacement) {
		char otherDirection = 'L';
		char deleteDirection = 'R';
		int numRebalancing = 0;
		if (parent.getLeft() == deleteReplacement) {
			otherDirection = 'R';
			deleteDirection = 'L';
		}
		// case 1: demote
		if (parent.getHeight() - this.getChild(parent, deleteDirection).getHeight() == 2) {
			numRebalancing = demote(parent);

		}

		// case 2-4:
		if (parent.getHeight() - deleteReplacement.getHeight() == 3) {
			IAVLNode other = this.getChild(parent,otherDirection);
			// case 2: rotate, promote & demote
			if ((other.getHeight() - other.getRight().getHeight() == 1) &&
					(other.getHeight() - other.getLeft().getHeight() == 1)) {
				numRebalancing += rotate(parent, other);
				numRebalancing += demote(parent);
				numRebalancing += promote(other);
				// the only terminal rebalance;
				return numRebalancing;
			}
			else if (other.getHeight() - getChild(other, deleteDirection).getHeight() == 2) {
				numRebalancing += rotate(parent, other);
				numRebalancing += demote(parent);
				numRebalancing += demote(parent);
				parent = other;
			}
			else if (other.getHeight() - getChild(other, otherDirection).getHeight() == 2) {
				numRebalancing += rotate(other, getChild(other, deleteDirection));
				numRebalancing += demote(other);
				other = other.getParent();
				numRebalancing += promote(other);
				numRebalancing += rotate(parent, other);
				numRebalancing += demote(parent);
				numRebalancing += demote(parent);
				parent = other;
			}
		}

		// continue recursively until we reach a balanced node
		if (numRebalancing > 0) {
			numRebalancing += recursiveDeleteRebalancing(parent.getParent(), parent);
		}
		return numRebalancing;
	}

	/**
	 * replacing node's place in the tree with replacement
	 * @param node
	 * @param replacement
	 * @return prev(node.getParent())
	 */
	private IAVLNode replace(IAVLNode node, IAVLNode replacement) {
		IAVLNode parent = node.getParent();
		replacement.setParent(parent);
		replacement.setRight(node.getRight());
		replacement.setLeft(node.getLeft());
		if (parent.getKey() < node.getKey()) {
			parent.setRight(replacement);
		} else {
			parent.setLeft(replacement);
		}
		return parent;
	}

	/**
	 * helper function for delete, getting the node to delete replacement
	 * @param node
	 * @return if node is a leaf - a virtual node
	 * 		   if node is a unary node - it's child
	 * 		   if node is a binary node - it's successor
	 */
	private IAVLNode getDeletedReplacement(IAVLNode node) {
		if (!node.getRight().isRealNode()) {
			return node.getLeft();
		} else {
			// getting the successor
			IAVLNode successor = node.getRight();
			while (successor.getLeft().isRealNode()) {
				successor = successor.getLeft();
			}
			return successor;
		}
	}

	/**
	 * Searching for node with key k in entire tree
	 * @param k
	 * @return the requested node, or null if it is not in the tree.
	 */
	private IAVLNode searchNode(int k) {
		return searchNode(this.root, k);
	}

	/**
	 * bst searching @param node's subtree recursively for a node according to key k
	 * @param node - parent node
	 * @param k - key
	 * @return the requested node, or null if it is not in the tree.
	 */
	private IAVLNode searchNode(IAVLNode node, int k) {
		if (node.getKey() == k)
			return node;
		if (node.getKey() > k) {
			if (node.getLeft().isRealNode()) {
				return searchNode(node.getLeft(), k);
			} else {
				return null;
			}
		}
		else // meaning node.getKey() < k
		{
			if (node.getRight().isRealNode()) {
				return searchNode(node.getRight(), k);
			} else {
				return null;
			}
		}
	}

	/**
	 * Demotes a given node (using setHeight)
	 * @param node
	 * @return the compelxity of the action
	 */
	private static int demote(IAVLNode node) {
		node.setHeight(node.getHeight() - 1);
		return 1;
	}

	/**
	 * Promotes a given node (using setHeight)
	 * @param node
	 * @return the complexity of the action
	 */
	private static int promote(IAVLNode node) {
		node.setHeight(node.getHeight() + 1);
		return 1;
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
		// If the tree is empty - return null
		if (this.empty()) {
			return null;
		}
		// The tree isn't empty, meaning we have at least the root node with a value
		IAVLNode curr = this.root;
		// While the right child is not a virtual node, we descend down the rightmost branch of the tree, until
		// we reach the rightmost node. Meaning this is the node with the largest key in the tree.
		while (curr.getRight().isRealNode()) {
			curr = curr.getRight();
		}

		return curr.getValue();
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
		// The general idea of this function, is that we basically scan all of the subtrees of this tree, according to
		// the key value, in an ascending manner.
		// 1. We start from the tree's root (current = root)
		// 2. We set index from smallest (currIndex = 0)
		// 3. Until currIndex == tree.size():
		// 		3.1. We then travel to the next smallest key (the list is prioritized according to index):
		// 			3.1.1. current's leftmost child node (we save every node we visit on the way in a stack)
		//			3.1.2. current's next right child node
		// 			3.1.3. current's last unsaved parent (using the stack's top node)
		// 		3.2. We save it's data in position currIndex
		//		3.3. We increment currIndex
		// Since we visit every node at most twice (once when saving in the stack, second time when removing from the stack)
		// we reach a total of O(n)
		String[] arr = new String[this.size()];

		// If the tree is empty we return an empty array
		if (!this.empty()) {

			Stack<IAVLNode> nodeStack = new Stack<>();
			IAVLNode curr = this.root;
			int currIndex = 0;

			while (currIndex < this.size()) {
				// first we get the left branch, and we save every node until we reach the leftmost node of the branch.
				while (curr.getLeft().isRealNode()) {
					nodeStack.push(curr);
					curr = curr.getLeft();
				}
				// we save it at the leftmost open space in the array (as it is the smallest key in the subtree)
				// and we increment the leftmost array space's index
				arr[currIndex] = curr.getValue();
				currIndex++;

				// Now we ascend along the visited nodes, and store the value of each of them in the next open space
				// in the array, until we reach a node with a right child, or we ran out of parent nodes.
				while (!curr.getRight().isRealNode() && !nodeStack.empty()) {
					curr = nodeStack.pop();
					arr[currIndex] = curr.getValue();
					currIndex++;
				}
				// If there is a next right node, we move towards it.
				if (curr.getRight().isRealNode()) {
					curr = curr.getRight();
				}
			}
		}

		return arr;
	}
	
	private List<IAVLNode> inorderWalk() {
		List<IAVLNode> treeNodes = new LinkedList<>();
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
		return this.size;
	}

	/**
	 * public int getRoot()
	 *
	 * Returns the root AVL node, or null if the tree is empty
	 *
	 * precondition: none postcondition: none
	 */
	public IAVLNode getRoot() {
		return this.root;
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
		int complexity = 1;
		char shortDir = 'R';
		char tallDir = 'L';
		IAVLNode shorter = this.getRoot();
		IAVLNode taller = t.getRoot();
		if (shorter.getHeight() > taller.getHeight()) {
			shorter = t.getRoot();
			taller = t.getRoot();
		}
		if (shorter.getKey() < taller.getKey()) {
			shortDir = 'L';
			tallDir = 'R';
		}
		IAVLNode curr = taller;
		while (curr.getHeight() > shorter.getHeight()) {
			curr = curr.getLeft();
			complexity++; // increasing complexity for every node we visit.
		}
		x.setHeight(shorter.getHeight() + 1);
		x.setParent(taller.getParent());
		this.setChild(taller.getParent(), shortDir, x);
		this.setChild(x, shortDir, shorter);
		this.setChild(x, tallDir, taller);
		// We need to rebalance
		if (x.getHeight() == x.getParent().getHeight()) {
			// x 'tall side' child is 2 ranks below x -> we need to rebalance
			if (x.getHeight() - this.getChild(x, tallDir).getHeight() == 2) {
				rotate(x.getParent(), x);
			}
			// x 'short side' child is 2 ranks below x -> we need to rebalance
			else if (x.getHeight() - this.getChild(x, shortDir).getHeight() == 2) {
				IAVLNode xTall = this.getChild(x, tallDir);
				rotate(x, xTall);
				rotate(xTall.getParent(), xTall);
			}
		}
		return complexity;
	}

	/**
	 * rotates a parent and child node (decides the rotation direction according to the child's direction)
	 * if 'child' is not a direct child node of 'parent' - nothing happens.
	 * @param parent - parent node
	 * @param child - child node
	 */
	private int rotate(IAVLNode parent, IAVLNode child) {
		if (parent.getRight() == child) {
			parent.setRight(child.getLeft());
			child.setLeft(parent);
		} else if (parent.getLeft() == child) {
			parent.setLeft(child.getRight());
			child.setRight(parent);
		}
		return 1;
	}

	/**
	 * retrieves node's child according to the requested direction
	 * @param node - the parent node
	 * @param dir - 'R' for getRight, 'L' for getLeft
	 * @return the requested side's node, or null if not a valid direction.
	 */
	private IAVLNode getChild(IAVLNode node, char dir){
		switch(dir) {
			case 'R':
				return node.getRight();
			case 'L':
				return node.getLeft();
			default:
				return null;
		}
	}

	/**
	 * setting a child node 'child' in direction 'dir' for parent node 'node'
	 * prev( getChild(node, dir) ) == @post( getChild(child, dir ) )
	 * this is a helper method and DOES NOT COMPARE KEYS
	 * @param node - parent node
	 * @param dir - 'R' for setRight, 'L' for setLeft
	 * @param child - child node to set
	 */
	private void setChild(IAVLNode node, char dir, IAVLNode child){
		switch(dir) {
			case 'R':
				child.getRight().setParent(node.getRight());
				node.getRight().setRight(child.getRight());
				node.getRight().setParent(child);
				child.setRight(node.getRight());
				node.setRight(child.getRight().getRight());
				child.setParent(node);
			case 'L':
				child.getLeft().setParent(node.getLeft());
				node.getLeft().setLeft(child.getLeft());
				node.getLeft().setParent(child);
				child.setLeft(node.getLeft());
				node.setLeft(child.getLeft().getLeft());
				child.setParent(node);
		}
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
