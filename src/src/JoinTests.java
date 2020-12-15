package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class JoinTests {
    public static boolean joinCase1() {
        AVLTree tree = new AVLTree();
        tree.insert(15,"15");
        AVLTree t2 = new AVLTree();
        t2.insert(17,"17");
        t2.insert(18,"18");
        t2.insert(19,"19");
        t2.insert(20,"20");
        t2.insert(21,"21");



        int size = 7;
        int min = 15;
        int max = 21;
        int root = 19;

        int expected = Math.abs(tree.getRoot().getRank() - t2.getRoot().getRank()) + 1;
//        AVLTree.print2D(lesser.getRoot());
        int complexity = tree.join(t2.createNode(16, "16"), t2);
        AVLTree.print2D(tree.getRoot());
//        AVLTree.print2D(tree.getRoot());
        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity > expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }

//        if (tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
//            return false;
//        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {
            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }

        if (!getInfo(tree)) {
            return false;
        }
        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.getRoot()) == false) {
            return false;
        }

        if (size != tree.size()) {
            return false;
        }

        if (keys.length != size) {
            return false;
        }

        if (!TestsUtil.areRanksHights(tree)) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(testSpecificCase());
        System.out.println(joinCase1());
        System.out.println(joinCase10());
        System.out.println(joinCase2());
        System.out.println(joinCase3());
        System.out.println(joinCaseThisEmpty());
        System.out.println(joinCaseOtherEmpty());
        System.out.println(joinCaseBothEmpty());
        System.out.println(joinCaseThisTallerBigger());
        System.out.println(joinCaseThisTallerSmaller());
        System.out.println(joinCaseThisShorterBigger());
        System.out.println(joinCaseThisShorterSmaller());
        System.out.println(joinCaseCompletelyBalanced());
        System.out.println(joinCaseCompletelyUnbalanced());
        System.out.println(joinCaseCompletelyUnbalancedOther());
        System.out.println(joinCaseCompletelyBalancedOther());
        System.out.println(randomTrees());
    }

    public static boolean getInfo(AVLTree t) {
        String[] info = t.infoToArray();
        int[] keys = t.keysToArray();
        for (int i = 0; i < keys.length; i++) {
            if (!info[i].equals(t.search(keys[i]))) {
                System.out.println("Error in Info to array!");
                System.out.println("Keys:");
                for (int key : keys) {
                    System.out.print(String.valueOf(key) + ',');
                }
                System.out.println("Recieved:");
                for (String str : info) {
                    System.out.print(str + ',');
                }
                return false;
            }
        }
        return true;
    }

    public static boolean joinCase10() {
        AVLTree tree = new AVLTree();
        for (int i = 11; i <= 20; i++) {
            tree.insert(i, String.valueOf(i));
        }

//        AVLTree.print2D(tree.getRoot());

        int size = 16;
        int min = 0;
        int max = 20;
//        int root = 4;

        AVLTree lesser = new AVLTree();
        for (int i = 0; i < 5; i++) {
            lesser.insert(i, String.valueOf(i));
        }
        int expected = tree.getRoot().getRank() - lesser.getRoot().getRank() + 1;
//        AVLTree.print2D(lesser.getRoot());
        int complexity = tree.join(tree.createNode(10, "10"), lesser);

//        AVLTree.print2D(tree.getRoot());
        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (tree.getRoot().getSize() != size) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }

//        if (tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
//            return false;
//        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }

        if (size != tree.size()) {
            return false;
        }

        if (keys.length != size) {
            return false;
        }

        if (!getInfo(tree)) {
            return false;
        }

        return true;
    }

    public static boolean joinCase2() {
        AVLTree tree = new AVLTree();
        tree.insert(2, "2");
        tree.insert(1, "1");

//        AVLTree.print2D(tree.getRoot());

        int size = 4;
        int min = 1;
        int max = 4;
//        int root = 4;

        AVLTree greater = new AVLTree();
        greater.insert(4, String.valueOf("4"));
        int expected = tree.getRoot().getRank() - greater.getRoot().getRank() + 1;
//        AVLTree.print2D(lesser.getRoot());
        int complexity = tree.join(tree.createNode(3, "3"), greater);

//        AVLTree.print2D(tree.getRoot());
        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }

        if (tree.getRoot().getSize() != size) {
            return false;
        }

        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.getRoot()) == false) {
            return false;
        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }

        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCaseThisEmpty() {
        int size = 3;
        int min = 3;
        int max = 10;
        int root = 4;

        AVLTree tree = new AVLTree();

        AVLTree greater = new AVLTree();
        greater.insert(4, String.valueOf("4"));
        greater.insert(3, String.valueOf("3"));
        int expected = greater.getRoot().getRank() - tree.getRoot().getRank() + 1;
        int complexity = tree.join(tree.createNode(10,"10"), greater);

//        AVLTree.print2D(tree.getRoot());
//        int complexity = tree.join(tree.createNode(3, "3"), greater);

//        AVLTree.print2D(tree.getRoot());
        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }

        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.getRoot()) == false) {
            return false;
        }

        if (tree.getRoot().getKey() != root) {
            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
            return false;
        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }
        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }
        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCaseOtherEmpty() {
        AVLTree tree = new AVLTree();
        tree.insert(2, "2");
        tree.insert(1, "1");
        tree.insert(3, "3");

//        AVLTree.print2D(tree.getRoot());

        int size = 4;
        int min = 1;
        int max = 4;
        int root = 2;

        AVLTree greater = new AVLTree();
        int expected = tree.getRoot().getRank() - greater.getRoot().getRank() + 1;
//        AVLTree.print2D(lesser.getRoot());
        int complexity = tree.join(tree.createNode(4, "4"), greater);

//        AVLTree.print2D(tree.getRoot());
        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }

        if (tree.getRoot().getKey() != root) {
            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
            return false;
        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }


        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }

        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCaseBothEmpty() {
        AVLTree tree = new AVLTree();
        int size = 1;
        int min = 3;
        int max = 3;
        int root = 3;

        AVLTree greater = new AVLTree();
        int expected = tree.getRoot().getRank() - greater.getRoot().getRank() + 1;
//        AVLTree.print2D(lesser.getRoot());
        int complexity = tree.join(tree.createNode(3, "3"), greater);

//        AVLTree.print2D(tree.getRoot());
        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }

//        if (tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
//            return false;
//        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }


        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCase3() {
        AVLTree tree = new AVLTree();
        AVLTree t = new AVLTree();

        for (int i = 9; i >= 6; i--) {
            t.insert(i, String.valueOf(i));
        }


        tree.insert(4, "4");

        int expected =  t.getRoot().getRank() - tree.getRoot().getRank() + 1;

        AVLTree.IAVLNode x = t.createNode(5, "5");
        int complexity = tree.join(x, t);
//        AVLTree.print2D(tree.getRoot());

        int size = 6;
        int min = 4;
        int max = 9;
        int root = 7;

        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }

//        if (tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
//            return false;
//        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }


        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCaseThisTallerBigger() {
        AVLTree tree = new AVLTree();
        AVLTree t = new AVLTree();

        for (int i = 40; i < 80; i++) {
            tree.insert(i, String.valueOf(i));
        }

        for (int i = 0; i < 20; i++) {
            t.insert(i, String.valueOf(i));
        }

        int size = tree.size() + t.size() + 1;
        int min = t.min.getKey();
        int max = tree.max.getKey();

        int expected =  Math.abs(t.getRoot().getRank() - tree.getRoot().getRank()) + 1;

        AVLTree.IAVLNode x = t.createNode(39, "39");
        int complexity = tree.join(x, t);

        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }

//        if (tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
//            return false;
//        }

        if (size != tree.size) {
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }

        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCaseThisShorterBigger() {
        AVLTree tree = new AVLTree();
        AVLTree t = new AVLTree();

        for (int i = 40; i < 60; i++) {
            tree.insert(i, String.valueOf(i));
        }

        for (int i = 0; i < 39; i++) {
            t.insert(i, String.valueOf(i));
        }

        int size = tree.size() + t.size() + 1;
        int min = t.min.getKey();
        int max = tree.max.getKey();

        int expected =  Math.abs(t.getRoot().getRank() - tree.getRoot().getRank()) + 1;

        AVLTree.IAVLNode x = t.createNode(39, "39");
        int complexity = tree.join(x, t);

        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }

        //        if (tree.getRoot().getKey() != root) {
        //            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
        //            return false;
        //        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }


        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCaseThisTallerSmaller() {
        AVLTree tree = new AVLTree();
        AVLTree t = new AVLTree();

        for (int i = 0; i < 39; i++) {
            tree.insert(i, String.valueOf(i));
        }

        for (int i = 40; i < 60; i++) {
            t.insert(i, String.valueOf(i));
        }

        int size = tree.size() + t.size() + 1;
        int min = tree.min.getKey();
        int max = t.max.getKey();

        int expected =  Math.abs(t.getRoot().getRank() - tree.getRoot().getRank()) + 1;

        AVLTree.IAVLNode x = t.createNode(39, "39");
        int complexity = tree.join(x, t);

        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }

        //        if (tree.getRoot().getKey() != root) {
        //            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
        //            return false;
        //        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }

        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCaseThisShorterSmaller()  {
        AVLTree t = new AVLTree();
        AVLTree tree = new AVLTree();

        for (int i = 0; i < 20; i++) {
            tree.insert(i, String.valueOf(i));
        }

        for (int i = 40; i < 80; i++) {
            t.insert(i, String.valueOf(i));
        }

        int size = tree.size() + t.size() + 1;
        int min = tree.min.getKey();
        int max = t.max.getKey();

        int expected =  Math.abs(t.getRoot().getRank() - tree.getRoot().getRank()) + 1;

        AVLTree.IAVLNode x = t.createNode(39, "39");
        int complexity = tree.join(x, t);

        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }

        //        if (tree.getRoot().getKey() != root) {
        //            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
        //            return false;
        //        }

        if (size != tree.size) {
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }


        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }

        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCaseCompletelyBalanced()  {
        AVLTree t = new AVLTree();
        AVLTree tree = new AVLTree();

        for (int i = 0; i < 39; i++) {
            tree.insert(i, String.valueOf(i));
        }

        for (int i = 40; i < 79; i++) {
            t.insert(i, String.valueOf(i));
        }

        int size = tree.size() + t.size() + 1;
        int min = tree.min.getKey();
        int max = t.max.getKey();
        int root = 39;

        int expected =  Math.abs(t.getRoot().getRank() - tree.getRoot().getRank()) + 1;

        AVLTree.IAVLNode x = t.createNode(39, "39");
        int complexity = tree.join(x, t);

        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }

        if (tree.getRoot().getKey() != root) {
            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
            return false;
        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }

        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCaseCompletelyBalancedOther()  {
        AVLTree t = new AVLTree();
        AVLTree tree = new AVLTree();

        for (int i = 0; i < 39; i++) {
            t.insert(i, String.valueOf(i));
        }

        for (int i = 40; i < 79; i++) {
            tree.insert(i, String.valueOf(i));
        }

        int size = tree.size() + t.size() + 1;
        int min = t.min.getKey();
        int max = tree.max.getKey();
        int root = 39;

        int expected =  Math.abs(t.getRoot().getRank() - tree.getRoot().getRank()) + 1;

        AVLTree.IAVLNode x = t.createNode(39, "39");
        int complexity = tree.join(x, t);

        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }

        if (tree.getRoot().getKey() != root) {
            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
            return false;
        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }

        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCaseCompletelyUnbalanced()  {
        AVLTree t = new AVLTree();
        AVLTree tree = new AVLTree();

        for (int i = 0; i < 39; i++) {
            tree.insert(i, String.valueOf(i));
        }

        for (int i = 40; i < 41; i++) {
            t.insert(i, String.valueOf(i));
        }

        int size = tree.size() + t.size() + 1;
        int min = tree.min.getKey();
        int max = t.max.getKey();

        int expected =  Math.abs(t.getRoot().getRank() - tree.getRoot().getRank()) + 1;

        AVLTree.IAVLNode x = t.createNode(39, "39");
        int complexity = tree.join(x, t);

        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }

        //        if (tree.getRoot().getKey() != root) {
        //            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
        //            return false;
        //        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }


        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean joinCaseCompletelyUnbalancedOther()  {
        AVLTree t = new AVLTree();
        AVLTree tree = new AVLTree();

        for (int i = 0; i < 39; i++) {
            t.insert(i, String.valueOf(i));
        }

        for (int i = 40; i < 41; i++) {
            tree.insert(i, String.valueOf(i));
        }

        int size = tree.size() + t.size() + 1;
        int min = t.min.getKey();
        int max = tree.max.getKey();

        int expected =  Math.abs(t.getRoot().getRank() - tree.getRoot().getRank()) + 1;

        AVLTree.IAVLNode x = t.createNode(39, "39");
        int complexity = tree.join(x, t);

        List<Integer> values = new LinkedList<>();
        for (int key: tree.keysToArray()) {
            values.add(key);
        }

        if (complexity != expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (tree.getRoot().getSize() != size) {
            return false;
        }


        if (TestsUtil.isBST(tree) == false) {
            return false;
        }

        if (TestsUtil.isBalanced(tree.root) == false) {
            return false;
        }

        //        if (tree.getRoot().getKey() != root) {
        //            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
        //            return false;
        //        }

        if (size != tree.size) {
            return false;
        }

        if (min != tree.min.getKey()) {
            return false;
        }

        if (max != tree.max.getKey()) {

            return false;
        }

        int[] keys = tree.keysToArray();

        if (keys.length != size) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                return false;
            }
        }


        if (!getInfo(tree)) {
            return false;
        }

        return true;

    }

    public static boolean checkJoin(AVLTree t1, AVLTree t2, int sepereator) {
        int size = t1.size() + t2.size() + 1;
        int min;
        int max;
        if ((t1.empty()) && t2.empty()) {
            min = sepereator;
            max = sepereator;
        }
        else if (t1.empty()) {
            min = t2.min.getKey();
            max = t2.max.getKey();
            if (t2.getRoot().getKey() > sepereator) {
                min = sepereator;
            }
            if (t2.getRoot().getKey() < sepereator) {
                max = sepereator;
            }
        } else if (t2.empty()) {
            min = t1.min.getKey();
            max = t1.max.getKey();
            if (t1.getRoot().getKey() > sepereator) {
                min = sepereator;
            }
            if (t1.getRoot().getKey() < sepereator) {
                max = sepereator;
            }
        } else {
            min = t1.min.getKey();
            max = t2.max.getKey();
            if (t1.min.getKey() > t2.max.getKey()) {
                min = t2.min.getKey();
                max = t1.max.getKey();
            }
        }
        List<Integer> allKeys = new ArrayList<>();
        for (int key: t1.keysToArray()) {
            allKeys.add(key);
        }
        allKeys.add(sepereator);
        for (int key: t2.keysToArray()) {
            allKeys.add(key);
        }


        int expected =  Math.abs(t1.getRoot().getRank() - t2.getRoot().getRank()) + 1;

        AVLTree.IAVLNode x = t1.createNode(sepereator, "sepereator");
        int complexity = t1.join(x, t2);

        List<Integer> values = new LinkedList<>();
        for (int key: t1.keysToArray()) {
            values.add(key);
        }

        if (complexity > expected) {
            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
            return false;
        }


        if (t1.getRoot().getSize() != size) {
            System.out.println("Bad root!, recieved " + t1.getRoot().getSize() + " expected " + size);
            return false;
        }


        if (TestsUtil.isBST(t1) == false) {
            System.out.println("Not BST!");
            return false;
        }

        if (TestsUtil.isBalanced(t1.root) == false) {
            System.out.println("Not Balanced!");
            return false;
        }

        //        if (tree.getRoot().getKey() != root) {
        //            System.out.println("Error with new key, recieved " + tree.getRoot().getKey());
        //            return false;
        //        }

        if (size != t1.size) {
            System.out.println("Bad size!");
            return false;
        }

        if (min != t1.min.getKey()) {
            System.out.println("Bad min! recieved " + t1.min.getKey() + " expected " + min);
            return false;
        }

        if (max != t1.max.getKey()) {
            System.out.println("Bad max! recieved " + t1.max.getKey() + " expected " + max);
            return false;
        }

        int[] keys = t1.keysToArray();

        if (keys.length != size) {
            System.out.println("Bad keys to array");
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                System.out.println("Bad keys to array");
                return false;
            }
        }


        if (!getInfo(t1)) {
            System.out.println("Bad get info");
            return false;
        }

        if (!TestsUtil.areRanksHights(t1)) {
            System.out.println("Bad are ranks heights");
            return false;
        }

        if (!testFindAll(t1, allKeys)) {
            System.out.println("Didn't find all keys");
            return false;
        }

        return true;
    }

    private static boolean testFindAll(AVLTree tree, List<Integer> allKeys) {
        for (int key: allKeys) {
            if (tree.search(key) == null) {
                return false;
            }
        }
        if (allKeys.size() != tree.keysToArray().length) {
            return false;
        }
        for (int key: tree.keysToArray()) {
            if (allKeys.indexOf(key) == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean randomTrees() {
        AVLTree t1;
        AVLTree t2;
        int maxSize = 1000;
        int[][][] combinations = new int[2][maxSize-1][maxSize];
        int seperator = ThreadLocalRandom.current().nextInt(0, maxSize);
        int t1Bigger;
        int smallerSize;
        int biggerSize;
        int[] smallerItems = new int[0];
        int[] biggerItems= new int[0];

        boolean works = true;
        for (int k = 0; k < 100000; k++) {
            seperator = ThreadLocalRandom.current().nextInt(1, maxSize);
            smallerSize = ThreadLocalRandom.current().nextInt(0, seperator);
            biggerSize = ThreadLocalRandom.current().nextInt(seperator, maxSize+1) - seperator;
            t1Bigger = ThreadLocalRandom.current().nextInt(0, 2);
            combinations[t1Bigger][smallerSize][biggerSize]++;
            System.out.println(k);
            smallerItems = getShuffledArray(smallerSize);
            AVLTree smaller = new AVLTree();
            for (int i: smallerItems) {
                smaller.insert(i, String.valueOf(i));
            }
            biggerItems = getShuffledArray(biggerSize);
            AVLTree bigger = new AVLTree();
            for (int i: biggerItems) {
                bigger.insert(i + 1 + seperator, String.valueOf(i + 1 + seperator));
            }
            t1 = smaller;
            t2 = bigger;
            if (t1Bigger == 1) {
                t1 = bigger;
                t2 = smaller;
            }
            works = checkJoin(t1, t2, seperator);
            if (!works) {
                break;
            }
        }
        System.out.println("Seperator: " + seperator);
        System.out.println("bigger");
        for (int i: biggerItems) {
            System.out.print(String.valueOf(i + 1 + seperator) + ",");
        }

        System.out.println();
        System.out.println("smaller");
        for (int i: smallerItems) {
            System.out.print(i + ",");
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i< combinations.length; i++) {
            System.out.println(i);
            System.out.print("  ");
            for (int k = 0; k< combinations[i][0].length; k++) {
                System.out.print(k + ",");
            }
            System.out.println();
            for (int j = 0; j< combinations[i].length; j++) {
                System.out.print(j + ")");
                for (int k = 0; k< combinations[i][j].length; k++) {
                    System.out.print(combinations[i][j][k] + ",");
                }
                System.out.println();
            }
        }

        return false;
    }

    public static boolean testSpecificCase() {
        int seperator = 63;
        int[] smallerItems = new int[] {};
        int[] biggerItems = new int[] {115,114,60,58,104,64,68,105,85,94,79,100,103,110,41,125,118,46,82,65,52,13,19,87,88,7,16,53,102,36,6,77,14,50,43,42,107,35,97,123,27,98,26,45,69,90,31,121,38,62,40,124,112,83,63,11,89,81,28,10,57,106,74,25,17,66,20,128,67,55,37,24,86,76,119,33,30,111,4,61,120,23,75,78,32,80,116,72,84,126,49,92,9,12,122,3,127,29,101,39,96,93,44,59,22,109,73,108,21,71,99,48,1,5,113,15,54,91,18,129,8,56,95,51,117,70,47,2,34};

        AVLTree smaller = new AVLTree();
        for (int i: smallerItems) {
            smaller.insert(i, String.valueOf(i));
        }
        AVLTree bigger = new AVLTree();
        for (int i: biggerItems) {
            bigger.insert(i + 1 + seperator, String.valueOf(i + 1 + seperator));
        }
        return checkJoin(bigger, smaller, seperator);
    }

    public static int[] getShuffledArray(int size) {
        ArrayList<Integer> lst = new ArrayList<Integer>();

        for (int i = 1; i <= size; i++) {
            lst.add(i);
        }

        java.util.Collections.shuffle(lst);

        int[] array = new int[size];
        int i = 0;

        for (int item : lst) {
            array[i] = item;
            i++;
        }

        return array;
    }
}

