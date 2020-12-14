package src;

import java.util.Random;

import src.AVLTree.IAVLNode;

public class InsertAndDeleteTests {
	private static int getSize() {
		Random rand = new Random();

		int k = rand.nextInt(50);

		while (k == 0) {
			k = rand.nextInt(50);
		}

		return k;
	}

	private static boolean testRandomTree() {
		int size = getSize();
		AVLTree tree = TestsUtil.getRandomTreeOfSize(size);

		if (TestsUtil.isBST(tree) == false) {
			return false;
		}

		if (TestsUtil.isBalanced(tree.getRoot()) == false) {
			return false;
		}

		if (size != tree.size()) {
			return false;
		}

		int[] keys = tree.keysToArray();

		if (keys.length != size) {
			return false;
		}

		if (!TestsUtil.areRanksHights(tree)) {
			return false;
		}

		return true;
	}

	private static boolean testSortedTree() {
		int size = 50;
		AVLTree tree = TestsUtil.createTreeFrom1ToSize(size);

		if (TestsUtil.isBST(tree) == false) {
			return false;
		}

		if (TestsUtil.isBalanced(tree.root) == false) {
			return false;
		}

		if (size != tree.size()) {
			return false;
		}

		if (1 != tree.min.getKey()) {
			return false;
		}

		if (size != tree.max.getKey()) {

			return false;
		}

		for (int i = 1; i <= size; i++) {
			if (tree.search(i) == null) {
				return false;
			}
		}

		int[] keys = tree.keysToArray();

		if (keys.length != size) {
			return false;
		}

		for (int i = 0; i < keys.length; i++) {
			if (!(keys[i] == i + 1)) {
				System.out.println("false");
				return false;

			}
		}

		if (!TestsUtil.areRanksHights(tree)) {
			return false;
		}

		return true;
	}

	private static boolean testSortedBackwards() {
		int size = 50;
		AVLTree tree = TestsUtil.createTreeFromSizeTo1(size);

		if (TestsUtil.isBST(tree) == false) {
			return false;
		}

		if (TestsUtil.isBalanced(tree.root) == false) {
			return false;
		}

		if (size != tree.size()) {
			return false;
		}

		if (1 != tree.min.getKey()) {
			return false;
		}

		if (size != tree.max.getKey()) {

			return false;
		}

		for (int i = 1; i <= size; i++) {
			if (tree.search(i) == null) {
				return false;
			}
		}

		int[] keys = tree.keysToArray();

		if (keys.length != size) {
			return false;
		}

		for (int i = 0; i < keys.length; i++) {
			if (!(keys[i] == i + 1)) {
				System.out.println("false");
				return false;

			}
		}

		if (!TestsUtil.areRanksHights(tree)) {
			return false;
		}
		
		return true;
	}

	private static boolean checkSeriesOfInsertsAndDeletes() {
		int size = 1;
		AVLTree tree = TestsUtil.createTreeFromSizeTo1(size);

		for (int i = 1; i <= size; i++) {
			tree.delete(i);

			if (TestsUtil.isBST(tree) == false) {
				return false;
			}

			if (TestsUtil.isBalanced(tree.root) == false) {
				return false;
			}

			if (size - i != tree.size()) {
				return false;
			}

			if (tree.empty() == false) {

				if (1 + i != tree.min.getKey()) {
					return false;
				}

				if (size != tree.max.getKey()) {
					return false;
				}
			}

			for (int j = 1 + i; j <= size; j++) {
				if (tree.search(j) == null) {
					return false;
				}
			}

			int[] keys = tree.keysToArray();

			if (keys.length != size - i) {
				return false;
			}

			for (int j = 0; j < keys.length; j++) {
				if (!(keys[j] == j + 1 + i)) {
					System.out.println(tree.max.getKey());
					System.out.println("false");
					return false;

				}
			}

			if (!TestsUtil.areRanksHights(tree)) {
				return false;
			}
		}
		
		if (!tree.empty() == true) {
			return false;
		}

		return true;
	}

	public static void testInsertAndDelete() {
		if (!testSortedBackwards()) {
			System.out.println("problem with sorted backwards insertion");
		}

		if (!testSortedTree()) {
			System.out.println("problem with sorted tree insertion");
		}

		if (!testRandomTree()) {
			System.out.println("problem with random tree insertion");
		}

		if (!checkSeriesOfInsertsAndDeletes()) {
			System.out.println("series of inserts and delete isn't ok");
		}
	}

	public static void main(String[] args) {
		SmallFunctionsTests tests = new SmallFunctionsTests();
		testInsertAndDelete();
		tests.testSmallFunctions();
		System.out.println("done");
	}

}
