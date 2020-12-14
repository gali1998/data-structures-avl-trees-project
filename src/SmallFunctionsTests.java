package src;

import java.util.Arrays;

public class SmallFunctionsTests {
	private boolean checkEmpty() {
		AVLTree tree = new AVLTree();
		
		if (tree.empty() == false) {
			return false;
		}
		
		return true;
	}
	
	private boolean checkSearch() {
		AVLTree tree = TestsUtil.createTreeFrom1ToSize(50);
		
		for (int i = 1; i <= 50; i++) {
			if (!tree.search(i).equals(String.valueOf(i))) {
				return false;
			}
		}
		
		return tree.search(51) == null;
	}
	
	private boolean checkMinAndMaxAndSize() {
		AVLTree tree = TestsUtil.createTreeFrom1ToSize(50);
		
		return tree.size() == 50 && tree.min().equals(String.valueOf(1)) && tree.max().equals(String.valueOf(50));
	}
	
	private boolean checkKeysToArrayAndInfoToArray() {
		AVLTree tree = TestsUtil.createTreeFrom1ToSize(50);
		int[] keys = tree.keysToArray();
		String[] values = tree.infoToArray();
		
		for (int i = 0; i < 50; i++) {
			if (keys[i] != i + 1 || values[i].equals(String.valueOf(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	public void testSmallFunctions() {
		if (!checkEmpty()) {
			System.out.println("empty is not ok");
		}
		
		if (!checkKeysToArrayAndInfoToArray()) {
			System.out.println("keystoarray or infotoarray not ok");
		}
		
		if (!checkMinAndMaxAndSize()) {
			System.out.println("min or max or size not ok");
		}
		
		if (!checkSearch()) {
			System.out.println("search is not ok");
		}
	}
}
