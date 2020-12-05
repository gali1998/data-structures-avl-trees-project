import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class IAVLNodeTests {

	private AVLTree tree = new AVLTree();
	private int size;
	private int min;
	private int max;
	private LinkedList<Integer> values;

	private boolean checkMin() {
		String realMin = this.tree.search(this.min);
		
		return realMin == this.tree.min();
	}
	
	private int getSize() {
		Random rand = new Random();

		int k = rand.nextInt(50);

		while (k == 0) {
			k = rand.nextInt(50);
		}

		return k;

	}

	private void getRandomTree() {
		Random rand = new Random();

		this.size = this.getSize();
		this.values = new LinkedList<Integer>();
		this.min = Integer.MAX_VALUE;
		this.max = Integer.MIN_VALUE;

		for (int i = 0; i < this.size; i++) {
			int val = rand.nextInt(1000);

			while (this.values.contains(val)) {
				val = rand.nextInt(1000);
			}

			this.values.add(val);

			this.tree.insert(val, "");

			if (val < min) {
				this.min = val;
			}

			if (val > max) {
				this.max = val;
			}
		}
	}

	private void insertSortedTree() {
		Random rand = new Random();

		this.size = this.getSize();
		this.max = this.size;
		this.min = 1;

		for (int i = 1; i <= this.size; i++) {
			this.tree.insert(i, "");
		}
	}

	private void insertSortedTreeBackwards() {
		Random rand = new Random();

		this.size = this.getSize();
		this.max = this.size;
		this.min = 1;

		for (int i = this.size; i > 0; i--) {
			this.tree.insert(i, "");
		}
	}

	private boolean checkSearch() {

		for (int val : this.values) {
			if (this.tree.search(val) == null) {
				return false;
			}
		}

		return true;

	}

	private boolean testRandomTree() {
		this.getRandomTree();

		if (this.tree.isBST() == false) {
			return false;
		}

		if (this.tree.isBalanced(this.tree.root) == false) {
			return false;
		}

		if (this.size != this.tree.size) {
			return false;
		}

		if (this.min != this.tree.min.getKey()) {
			return false;
		}

		if (this.max != this.tree.max.getKey()) {

			return false;
		}
		
		if (this.checkSearch() == false) {
			return false;
		}

		int[] keys = this.tree.keysToArray();

		if (keys.length != this.size) {
			return false;
		}
		
		for (int i = 0; i < keys.length; i++) {
			if (!this.values.contains(keys[i])) {
				return false;
			}
		}
		
		if (!this.checkMin()) {
			return false;
		}

		return true;
	}

	private boolean testSortedTree() {
		this.insertSortedTree();

		if (this.tree.isBST() == false) {
			return false;
		}

		if (this.tree.isBalanced(this.tree.root) == false) {
			return false;
		}

		if (this.size != this.tree.size) {
			System.out.println(this.size);
			System.out.println(this.tree.root.getKey());
			return false;
		}

		if (this.min != this.tree.min.getKey()) {
			return false;
		}

		if (this.max != this.tree.max.getKey()) {

			return false;
		}

		for (int i = 1; i <= this.size; i++) {
			if (this.tree.search(i) == null) {
				return false;
			}
		}
		
		int[] keys = this.tree.keysToArray();

		if (keys.length != this.size) {
			return false;
		}
		
		for (int i = 0; i < keys.length; i++) {
			if (!(keys[i] == i + 1)) {
				System.out.println("false");
				return false;
				
			}
		}
		
		if (!this.checkMin()) {
			return false;
		}

		return true;
	}

	private boolean testSortedBackwardsTree() {
		this.insertSortedTreeBackwards();

		if (this.tree.isBST() == false) {
			return false;
		}

		if (this.tree.isBalanced(this.tree.root) == false) {
			return false;
		}

		if (this.size != this.tree.size) {
			return false;
		}

		if (this.min != this.tree.min.getKey()) {
			return false;
		}

		if (this.max != this.tree.max.getKey()) {

			return false;
		}

		for (int i = 1; i <= this.size; i++) {
			if (this.tree.search(i) == null) {
				return false;
			}
		}
		
		int[] keys = this.tree.keysToArray();

		if (keys.length != this.size) {
			return false;
		}
		
		if (!this.checkMin()) {
			return false;
		} 

		return true;
	}

	public static void main(String[] args) {
		IAVLNodeTests tests = new IAVLNodeTests();

		if (tests.testRandomTree() == true) {
			System.out.println("Random tree is ok");
		}

		tests = new IAVLNodeTests();

		if (tests.testSortedTree() == true) {
			System.out.println("sorted tree is ok");
		}

		tests = new IAVLNodeTests();

		if (tests.testSortedBackwardsTree() == true) {
			System.out.println("sorted backwards tree is ok");
		}

		System.out.println("done");

	}
}
