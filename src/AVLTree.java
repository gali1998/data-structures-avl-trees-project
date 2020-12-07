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
		leaf.update();

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
		IAVLNode toDelete = this.searchNode(k);
		if (toDelete == null) {
			return -1;
		}
		IAVLNode unbalancedNode = this.bstDelete(toDelete);
		this.size--;
		if (k == this.min.getKey()) {
			this.min = this.findMin();
		}
		if (k == this.max.getKey()) {
			this.max = this.findMax();
		}
		// rebalancing and returning the number of rebalancing operations
		int numRebalancing = this.recursiveDeleteRebalancing(unbalancedNode);
		this.updatePath(unbalancedNode);
		return numRebalancing;
	}

	/**
	 * delete a node from a BST given the node to delete
	 * @param toDelete - the node to delete from the tree
	 * @return the deepest (potentially) unbalanced node
	 */
	private IAVLNode bstDelete(IAVLNode toDelete) {
		IAVLNode rebalancingStart = toDelete.getParent();
		IAVLNode successor;
		 // if we have a right child
		if (toDelete.getRight().isRealNode() && toDelete.getLeft().isRealNode()) {
			successor = toDelete.getRight();
			// due to AVL tree balance maintenance, if we don't have a left child, we won't enter the loop.
			// if we have a left child, we need to search for right's leftmost child - which will be a unary or leaf.
			while (successor.getLeft().isRealNode()) {
				successor = successor.getLeft();
			}
			// we return the successor's parent as the node to start from (deepest that may be unbalanced)
			if (successor.getParent() != toDelete) {
				rebalancingStart = successor.getParent();
			}
			successor.getParent().setLeft(successor.getRight());
			successor.getRight().setParent(successor.getParent());
			// In this case, since we have a binary node, and we replaced the content of the node to delete,
			// we need to update the successor's height
			successor.setHeight(toDelete.getHeight());
			successor.setRank(toDelete.getRank());
		} else if (toDelete.getRight().isRealNode()) {
			successor = toDelete.getRight();
			successor.setLeft(toDelete.getLeft());
		} else { // if we don't have a right, we either take the left child as successor (unary node), or a virtual node.
			successor = toDelete.getLeft();
			if (successor.isRealNode()) {
				successor.setRight(toDelete.getRight());
			}
		}
		if (toDelete != this.root) {
			if (toDelete.getParent().getRight() == toDelete) {
				toDelete.getParent().setRight(successor);
			} else {
				toDelete.getParent().setLeft(successor);
			}
		}
		if (successor.isRealNode()) {
			if (successor != toDelete.getRight()) {
				successor.setRight(toDelete.getRight());
				toDelete.getRight().setParent(successor);
			}
			if (successor != toDelete.getLeft()) {
				successor.setLeft(toDelete.getLeft());
				toDelete.getLeft().setParent(successor);
			}


			successor.setParent(toDelete.getParent());
			if (toDelete == this.root) {
				this.root = successor;
			}
		} else {
			if (toDelete == this.root) {
				this.root = null;
			}
		}

		return rebalancingStart;
	}

	/**
	 * recursively apply rebalancing operations after deletion
	 * @param unbalanced - the potentialy unbalanced node
	 * @return # of rebalancing operations
	 */
	private int recursiveDeleteRebalancing(IAVLNode unbalanced) {
		int numRebalancing = 0;
		if (unbalanced == null) { // terminal case for root
			return 0;
		}

		// case 1
		if ((unbalanced.rightRankDifference() == 2) && (unbalanced.leftRankDifference() == 2)) {
			unbalanced.demote();
			numRebalancing++;
		}

		if ((unbalanced.rightRankDifference() == 3) || (unbalanced.leftRankDifference() == 3)) {
			char deletedDir = 'L';
			char otherDir = 'R';
			if (unbalanced.rightRankDifference() == 3) {
				deletedDir = 'R';
				otherDir = 'L';
			}
			IAVLNode other = unbalanced.getChildByDir(otherDir);
			// case 2: rotate, promote & demote
			if ((other.rightRankDifference() == 1) &&
					(other.leftRankDifference() == 1)) {
				numRebalancing += rotate(unbalanced, other);
				unbalanced.demote();
				numRebalancing++;
				other.promote();
				numRebalancing++;
				// the only terminal rebalance;
				return numRebalancing;
			}
			// case 3: rotate, double demote
			else if (other.getRankDifferenceByDir(deletedDir) == 2) {
				numRebalancing += rotate(unbalanced, other);
				unbalanced.demote();
				numRebalancing++;
				unbalanced.demote();
				numRebalancing++;
				unbalanced = other;
			}
			// case 4: double rotate,
			else if (other.getRankDifferenceByDir(otherDir) == 2) {
				numRebalancing += rotate(other, other.getChildByDir(deletedDir));
				other.demote();
				numRebalancing++;
				other = other.getParent();
				other.promote();
				numRebalancing++;
				numRebalancing += rotate(unbalanced, other);
				unbalanced.demote();
				numRebalancing++;
				unbalanced.demote();
				numRebalancing++;
				unbalanced = other;
			}
		}

		// continue recursively until we reach a balanced node
		if (numRebalancing > 0) {
			numRebalancing += recursiveDeleteRebalancing(unbalanced.getParent());
		}
		return numRebalancing;
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
		if (this.empty()) {
			return null;
		}
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

		while ((node.getLeft() != null) && (node.getLeft().isRealNode())) {
			node = node.getLeft();
		}

		return node;
	}

	private IAVLNode findMax() {
		if (this.empty()) {
			return null;
		}

		IAVLNode node = this.getRoot();

		while ((node.getRight() != null) && (node.getRight().isRealNode())) {
			node = node.getRight();
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
		// If the tree is empty - return null
		if (this.empty()) {
			return null;
		}

		return this.max.getValue();
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

		// We search for a subtree of taller, with taller.height == shorter.height || taller.height == shorter.height - 1
		while (taller.getHeight() > shorter.getHeight()) {
			taller = taller.getLeft();
			complexity++; // increasing complexity for every node we visit.
		}
		x.setHeight(shorter.getHeight() + 1);
		x.setParent(taller.getParent());
		taller.getParent().setChildByDir(shortDir, x);
		x.setChildByDir(shortDir, shorter);
		x.setChildByDir(tallDir, taller);
		// We need to rebalance
		if (x.getHeight() == x.getParent().getHeight()) {
			// x 'tall side' child is 2 ranks below x -> we need to rebalance
			if (x.getHeight() - x.getChildByDir(tallDir).getHeight() == 2) {
				rotate(x.getParent(), x);
				x.promote();
			}
			// x 'short side' child is 2 ranks below x -> we need to rebalance
			else if (x.getHeight() - x.getChildByDir(shortDir).getHeight() == 2) {
				IAVLNode xTall = x.getChildByDir(tallDir);
				rotate(x, xTall);
				rotate(xTall.getParent(), xTall);
			}
		}
		return complexity;
	}



	public boolean isBalanced() {
		return isTreeBalanced(this.root);
	}

	public static boolean isTreeBalanced(IAVLNode root) {
		if (root == null) {
			return true;
		}
		boolean isNodeBalanced = isNodeBalanced(root);
		if (!isNodeBalanced) {
			System.out.println("Imbalanced Node!");
			System.out.println("Key: " + root.getKey() + " Height: " + root.getHeight());
			if (root.isRealNode()) {
				System.out.println("Right Key: " + root.getRight().getKey() + " Height: " + root.getRight().getHeight());
				System.out.println("Left Key: " + root.getLeft().getKey() + " Height: " + root.getLeft().getHeight());
			}
		}
		return isNodeBalanced && isTreeBalanced(root.getRight()) && isTreeBalanced(root.getLeft());
	}

	public static boolean isNodeBalanced(IAVLNode node) {
		if (node.isRealNode()) {
			return ((node.getHeight() - node.getRight().getHeight() == 1) && (node.getHeight() - node.getLeft().getHeight() == 1)) ||
					((node.getHeight() - node.getRight().getHeight() == 1) && (node.getHeight() - node.getLeft().getHeight() == 2)) ||
					((node.getHeight() - node.getRight().getHeight() == 2) && (node.getHeight() - node.getLeft().getHeight() == 1));

		}
		else {
			return ((node.getRight() == null) && (node.getLeft() == null));
		}
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

	/**
	 * rotates a parent and child node (decides the rotation direction according to the child's direction)
	 * if 'child' is not a direct child node of 'parent' - nothing happens.
	 * @param parent - parent node
	 * @param child - child node
	 */
	private int rotate(IAVLNode parent, IAVLNode child) {
		if (parent == null) {
			return 0;
		}
		if (parent == this.root) {
			this.root = child;
		}
		if (parent.getRight() == child) {
			this.rotateLeft(parent);
			return 1;
		} else if (parent.getLeft() == child) {
			this.rotateRight(parent);
			return 1;
		}
		return 0;
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

		public int getSize();

		public void promote();

		public void demote();

		public int getRank();

		public int rightRankDifference();

		public int leftRankDifference();

		public void update();

		public IAVLNode getChildByDir(char dir);

		public void setChildByDir(char dir, IAVLNode child);

		public int getRankDifferenceByDir(char dir);

		public void setRank(int rank);
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

		public IAVLNode getChildByDir(char dir) {
			switch(dir) {
				case 'R':
					return this.getRight();
				case 'L':
					return this.getLeft();
				default:
					return null;
			}
		}

		public void setChildByDir(char dir, IAVLNode child) {
			switch(dir) {
				case 'R':
					child.getRight().setParent(this.getRight());
					this.getRight().setRight(child.getRight());
					this.getRight().setParent(child);
					child.setRight(this.getRight());
					this.setRight(child.getRight().getRight());
					child.setParent(this);
				case 'L':
					child.getLeft().setParent(this.getLeft());
					this.getLeft().setLeft(child.getLeft());
					this.getLeft().setParent(child);
					child.setLeft(this.getLeft());
					this.setLeft(child.getLeft().getLeft());
					child.setParent(this);
			}
		}

		public int getRankDifferenceByDir(char dir) {
			switch (dir) {
				case 'R':
					return this.rightRankDifference();
				case 'L':
					return this.leftRankDifference();
				default:
					return -4; // completely un-achievable if tree is well maintained
			}
		}
		public void setRank(int rank) {
			this.rank = rank;
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
