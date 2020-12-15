//package src;
//
//import java.util.LinkedList;
//import java.util.Random;
//
//public class IAVLNodeTests {
//
//    private AVLTree tree = new AVLTree();
//    private int size;
//    private int min;
//    private int max;
//    private LinkedList<Integer> values;
//
//    private boolean checkMin() {
//        String realMin = this.tree.search(this.min);
//
//        return realMin == this.tree.min();
//    }
//
//    private int getSize() {
//        Random rand = new Random();
//
//        int k = rand.nextInt(50);
//
//        while (k == 0) {
//            k = rand.nextInt(50);
//        }
//
//        return k;
//
//    }
//
//    private void getRandomTree() {
//        Random rand = new Random();
//
//        this.size = this.getSize();
//        this.values = new LinkedList<Integer>();
//        this.min = Integer.MAX_VALUE;
//        this.max = Integer.MIN_VALUE;
//
//        for (int i = 0; i < this.size; i++) {
//            int val = rand.nextInt(1000);
//
//            while (this.values.contains(val)) {
//                val = rand.nextInt(1000);
//            }
//
//            this.values.add(val);
//
//            this.tree.insert(val, String.valueOf(val));
//
//            if (val < min) {
//                this.min = val;
//            }
//
//            if (val > max) {
//                this.max = val;
//            }
//        }
//    }
//
//    private void insertSortedTree() {
//        Random rand = new Random();
//
//        this.size = this.getSize();
//        this.max = this.size;
//        this.min = 1;
//
//        for (int i = 1; i <= this.size; i++) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//    }
//
//    private void insertSortedTreeBackwards() {
//        Random rand = new Random();
//
//        this.size = this.getSize();
//        this.max = this.size;
//        this.min = 1;
//
//        for (int i = this.size; i > 0; i--) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//    }
//
//    private boolean checkSearch() {
//
//        for (int val : this.values) {
//            if (this.tree.search(val) == null) {
//                return false;
//            }
//        }
//
//        return true;
//
//    }
//
//    private boolean testRandomTree() {
//        this.getRandomTree();
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean testSortedTree() {
//        this.insertSortedTree();
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            System.out.println(this.size);
//            System.out.println(this.tree.root.getKey());
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        for (int i = 1; i <= this.size; i++) {
//            if (this.tree.search(i) == null) {
//                return false;
//            }
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!(keys[i] == i + 1)) {
//                System.out.println("false");
//                return false;
//
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean testDelete() {
//        this.insertSortedTree();
//
//        int[] keys = this.tree.keysToArray();
//        Random rand = new Random();
//        int key = keys[rand.nextInt(keys.length)];
//
//
//        int complexity = this.tree.delete(key);
//
//        System.out.println("delete complexity: " + complexity);
//        complexity = this.tree.delete(this.tree.getRoot().getKey());
//
//        System.out.println("delete complexity: " + complexity);
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
////        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean deleteCase1() {
//        this.tree.insert(10, "10");
//        this.tree.insert(5, "5");
//        this.tree.insert(20, "20");
//        this.tree.insert(8, "8");
//        this.tree.insert(4, "4");
//        this.tree.insert(16, "16");
//        this.tree.insert(3, "3");
//        this.size = this.tree.size - 1;
//        this.min = 3;
//        this.max = 20;
//
//        int comp = this.tree.delete(8);
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//
//        if (comp != 4) {
//            System.out.println("bad complexity " + comp);
//            return false;
//        }
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean deleteCase2() {
//        this.tree.insert(10, "10");
//        this.tree.insert(5, "5");
//        this.tree.insert(20, "20");
//        this.tree.insert(8, "8");
//        this.tree.insert(4, "4");
//        this.tree.insert(16, "16");
//        this.tree.insert(3, "3");
//        this.size = this.tree.size - 1;
//        this.min = 4;
//        this.max = 20;
//
//        int comp = this.tree.delete(3);
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//
//        if (comp != 3) {
//            System.out.println("bad complexity " + comp);
//            return false;
//        }
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean deleteCase3() {
//        this.tree.insert(10, "10");
//        this.tree.insert(5, "5");
//        this.tree.insert(20, "20");
//        this.tree.insert(8, "8");
//        this.tree.insert(4, "4");
//        this.tree.insert(16, "16");
//        this.tree.insert(3, "3");
//        this.size = this.tree.size - 1;
//        this.min = 3;
//        this.max = 16;
//        int root = 5;
//
//        int comp = this.tree.delete(20);
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//
//        if (comp != 3) {
//            System.out.println("bad complexity " + comp);
//            return false;
//        }
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean deleteCase4() {
//        this.tree.insert(3, "3");
//        this.tree.insert(2, "2");
//        this.tree.insert(4, "4");
//        this.tree.insert(1, "1");
//        this.tree.insert(5, "5");
//
//        this.size = this.tree.size - 2;
//        this.min = 3;
//        this.max = 5;
//        int root = 4;
//
//        int comp = this.tree.delete(2);
//        if (comp != 0) {
//            System.out.println("bad complexity " + comp);
//            return false;
//        }
//        comp += this.tree.delete(1);
//
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//
//        if (comp != 3) {
//            System.out.println("bad complexity " + comp);
//            return false;
//        }
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean deleteCaseNonEmptyRoot() {
//        this.tree.insert(3, "3");
//        this.tree.insert(2, "2");
//        this.tree.insert(4, "4");
//
//        this.size = this.tree.size - 1;
//        this.min = 2;
//        this.max = 4;
//        int root = 4;
//
//        int comp = this.tree.delete(3);
//        if (comp != 0) {
//            System.out.println("bad complexity " + comp);
//            return false;
//        }
////        comp += this.tree.delete(1);
//
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean deleteCaseEverythingInOrder() {
//        for (int i = 0; i < 39; i++) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//
//        for (int i = 0; i <39; i++) {
//            this.size = this.tree.size() - 1;
//            this.max = 38;
//            this.min = i+1;
//            this.tree.delete(i);
//
//            this.values = new LinkedList<>();
//            for (int key: this.tree.keysToArray()) {
//                this.values.add(key);
//            }
//
//            if (this.tree.isBST() == false) {
//                return false;
//            }
//
//            if (this.tree.isBalanced(this.tree.root) == false) {
//                return false;
//            }
//
//            if (this.size != this.tree.size) {
//                return false;
//            }
//
//            if (i != 38) {
//                if (this.min != this.tree.min.getKey()) {
//                    return false;
//                }
//                if (this.max != this.tree.max.getKey()) {
//                    return false;
//                }
//            }
//
//            if (this.checkSearch() == false) {
//                return false;
//            }
//
//            int[] keys = this.tree.keysToArray();
//
//            if (keys.length != this.size) {
//                return false;
//            }
//
//            for (int j = 0; j < keys.length; j++) {
//                if (!this.values.contains(keys[j])) {
//                    return false;
//                }
//            }
//
//            if (!getInfo(this.tree)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean deleteCaseAlwaysRoot() {
//        for (int i = 0; i < 39; i++) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//
//        for (int i = 0; i <39; i++) {
//            this.size = this.tree.size() - 1;
//            this.max = 38;
//            this.min = 0;
//            int currLastRoot = this.tree.getRoot().getKey();
//            this.tree.delete(currLastRoot);
//
//            if (this.tree.search(currLastRoot) != null) {
//                return false;
//            }
//
//            this.values = new LinkedList<>();
//            for (int key: this.tree.keysToArray()) {
//                this.values.add(key);
//            }
//
//            if (this.tree.isBST() == false) {
//                return false;
//            }
//
//            if (this.tree.isBalanced(this.tree.root) == false) {
//                AVLTree.print2D(this.tree.getRoot());
//                return false;
//            }
//
//            if (this.size != this.tree.size) {
//                return false;
//            }
//
//            if (i < 37) {
//                if (this.min != this.tree.min.getKey()) {
//                    return false;
//                }
//                if (this.max != this.tree.max.getKey()) {
//                    return false;
//                }
//            }
//
//            if (this.checkSearch() == false) {
//                return false;
//            }
//
//            int[] keys = this.tree.keysToArray();
//
//            if (keys.length != this.size) {
//                return false;
//            }
//
//            for (int j = 0; j < keys.length; j++) {
//                if (!this.values.contains(keys[j])) {
//                    return false;
//                }
//            }
//
//            if (!getInfo(this.tree)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean deleteCaseEverythingReverseOrder() {
//        for (int i = 0; i < 39; i++) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//
//        for (int i = 38; i >= 0; i--) {
//            this.size = this.tree.size() - 1;
//            this.max = i-1;
//            this.min = 0;
//            this.tree.delete(i);
//
//            this.values = new LinkedList<>();
//            for (int key: this.tree.keysToArray()) {
//                this.values.add(key);
//            }
//
//            if (this.tree.isBST() == false) {
//                return false;
//            }
//
//            if (this.tree.isBalanced(this.tree.root) == false) {
//                return false;
//            }
//
//            if (this.size != this.tree.size) {
//                return false;
//            }
//
//            if (i != 0) {
//                if (this.min != this.tree.min.getKey()) {
//                    return false;
//                }
//                if (this.max != this.tree.max.getKey()) {
//                    return false;
//                }
//            }
//
//            if (this.checkSearch() == false) {
//                return false;
//            }
//
//            int[] keys = this.tree.keysToArray();
//
//            if (keys.length != this.size) {
//                return false;
//            }
//
//            for (int j = 0; j < keys.length; j++) {
//                if (!this.values.contains(keys[j])) {
//                    return false;
//                }
//            }
//
//            if (!getInfo(this.tree)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean deleteCaseLeftRoot() {
//        this.tree.insert(3, "3");
//        this.tree.insert(2, "2");
//        this.tree.insert(4, "4");
//
//        this.size = this.tree.size - 1;
//        this.min = 3;
//        this.max = 4;
//        int root = 3;
//
//        int comp = this.tree.delete(2);
//        if (comp != 0) {
//            System.out.println("bad complexity " + comp);
//            return false;
//        }
////        comp += this.tree.delete(1);
//
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean deleteCaseRightRoot() {
//        this.tree.insert(3, "3");
//        this.tree.insert(2, "2");
//        this.tree.insert(4, "4");
//
//        this.size = this.tree.size - 1;
//        this.min = 2;
//        this.max = 3;
//        int root = 3;
//
//        int comp = this.tree.delete(4);
//        if (comp != 0) {
//            System.out.println("bad complexity " + comp);
//            return false;
//        }
////        comp += this.tree.delete(1);
//
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean deleteCaseRoot() {
//        this.tree.insert(3, "3");
//
//
//        this.size = this.tree.size - 1;
//
//        int comp = this.tree.delete(3);
//        if (comp != 0) {
//            System.out.println("bad complexity " + comp);
//            return false;
//        }
////        comp += this.tree.delete(1);
//
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean testSortedBackwardsTree() {
//        this.insertSortedTreeBackwards();
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        for (int i = 1; i <= this.size; i++) {
//            if (this.tree.search(i) == null) {
//                return false;
//            }
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    public static boolean getInfo(AVLTree t) {
//        boolean works = true;
//        String[] info = t.infoToArray();
//        int[] keys = t.keysToArray();
//        for (int i = 0; i < keys.length; i++) {
//            if (!info[i].equals(t.search(keys[i]))) {
//                System.out.println("Error in Info to array!");
//                works = false;
//                System.out.println("Keys:");
//                for (int key: keys) {
//                    System.out.print(String.valueOf(key) + ',');
//                }
//                System.out.println("Recieved:");
//                for (String str: info) {
//                    System.out.print(str + ',');
//                }
//                break;
//            }
//        }
//
//
//        if (info.length < t.size()) {
//            System.out.println("Missing " + String.valueOf(t.size() - info.length) + "info items.");
//            works = false;
//        }
//
//        return works;
//    }
//
//    public boolean joinCase1() {
////        this.tree.insert(3, "3");
////        this.tree.insert(2, "2");
////        this.tree.insert(4, "4");
////        this.tree.insert(1, "1");
////        this.tree.insert(5, "5");
//        for (int i = 11; i <= 20; i++) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//
////        AVLTree.print2D(this.tree.getRoot());
//
//        this.size = 16;
//        this.min = 0;
//        this.max = 20;
////        int root = 4;
//
//        AVLTree lesser = new AVLTree();
//        for (int i = 0; i < 5; i++) {
//            lesser.insert(i, String.valueOf(i));
//        }
//        int expected = this.tree.getRoot().getRank() - lesser.getRoot().getRank() + 1;
////        AVLTree.print2D(lesser.getRoot());
//        int complexity = this.tree.join(this.tree.createNode(10, "10"), lesser);
//
////        AVLTree.print2D(this.tree.getRoot());
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
////        if (this.tree.getRoot().getKey() != root) {
////            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
////            return false;
////        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    public boolean joinCase2() {
//        this.tree.insert(2, "2");
//        this.tree.insert(1, "1");
//
////        AVLTree.print2D(this.tree.getRoot());
//
//        this.size = 4;
//        this.min = 1;
//        this.max = 4;
////        int root = 4;
//
//        AVLTree greater = new AVLTree();
//        greater.insert(4, String.valueOf("4"));
//        int expected = this.tree.getRoot().getRank() - greater.getRoot().getRank() + 1;
////        AVLTree.print2D(lesser.getRoot());
//        int complexity = this.tree.join(this.tree.createNode(3, "3"), greater);
//
////        AVLTree.print2D(this.tree.getRoot());
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
////        if (this.tree.getRoot().getKey() != root) {
////            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
////            return false;
////        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public boolean joinCaseThisEmpty() {
//        this.size = 3;
//        this.min = 3;
//        this.max = 10;
//        int root = 4;
//
//        AVLTree greater = new AVLTree();
//        greater.insert(4, String.valueOf("4"));
//        greater.insert(3, String.valueOf("3"));
//        int expected = greater.getRoot().getRank() - this.tree.getRoot().getRank() + 1;
//        int complexity = this.tree.join(this.tree.createNode(10,"10"), greater);
//
////        AVLTree.print2D(this.tree.getRoot());
////        int complexity = this.tree.join(this.tree.createNode(3, "3"), greater);
//
////        AVLTree.print2D(this.tree.getRoot());
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public boolean joinCaseOtherEmpty() {
//        this.tree.insert(2, "2");
//        this.tree.insert(1, "1");
//        this.tree.insert(3, "3");
//
////        AVLTree.print2D(this.tree.getRoot());
//
//        this.size = 4;
//        this.min = 1;
//        this.max = 4;
//        int root = 2;
//
//        AVLTree greater = new AVLTree();
//        int expected = this.tree.getRoot().getRank() - greater.getRoot().getRank() + 1;
////        AVLTree.print2D(lesser.getRoot());
//        int complexity = this.tree.join(this.tree.createNode(4, "4"), greater);
//
////        AVLTree.print2D(this.tree.getRoot());
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public boolean joinCaseBothEmpty() {
//
//        this.size = 1;
//        this.min = 3;
//        this.max = 3;
//        int root = 3;
//
//        AVLTree greater = new AVLTree();
//        int expected = this.tree.getRoot().getRank() - greater.getRoot().getRank() + 1;
////        AVLTree.print2D(lesser.getRoot());
//        int complexity = this.tree.join(this.tree.createNode(3, "3"), greater);
//
////        AVLTree.print2D(this.tree.getRoot());
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
////        if (this.tree.getRoot().getKey() != root) {
////            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
////            return false;
////        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public boolean joinCase3() {
//        AVLTree t = new AVLTree();
//
//        for (int i = 9; i >= 6; i--) {
//            t.insert(i, String.valueOf(i));
//        }
//
//
//        this.tree.insert(4, "4");
//
//        int expected =  t.getRoot().getRank() - this.tree.getRoot().getRank() + 1;
//
//        AVLTree.IAVLNode x = t.createNode(5, "5");
//        int complexity = this.tree.join(x, t);
////        AVLTree.print2D(this.tree.getRoot());
//
//        this.size = 6;
//        this.min = 4;
//        this.max = 9;
//        int root = 7;
//
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
////        if (this.tree.getRoot().getKey() != root) {
////            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
////            return false;
////        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public boolean joinCaseThisTallerBigger() {
//        AVLTree t = new AVLTree();
//
//        for (int i = 40; i < 80; i++) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//
//        for (int i = 0; i < 20; i++) {
//            t.insert(i, String.valueOf(i));
//        }
//
//        this.size = this.tree.size() + t.size() + 1;
//        this.min = t.min.getKey();
//        this.max = this.tree.max.getKey();
//
//        int expected =  Math.abs(t.getRoot().getRank() - this.tree.getRoot().getRank()) + 1;
//
//        AVLTree.IAVLNode x = t.createNode(39, "39");
//        int complexity = this.tree.join(x, t);
//
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
////        if (this.tree.getRoot().getKey() != root) {
////            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
////            return false;
////        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public boolean joinCaseThisShorterBigger() {
//        AVLTree t = new AVLTree();
//
//            for (int i = 40; i < 60; i++) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//
//            for (int i = 0; i < 39; i++) {
//            t.insert(i, String.valueOf(i));
//        }
//
//            this.size = this.tree.size() + t.size() + 1;
//            this.min = t.min.getKey();
//            this.max = this.tree.max.getKey();
//
//        int expected =  Math.abs(t.getRoot().getRank() - this.tree.getRoot().getRank()) + 1;
//
//        AVLTree.IAVLNode x = t.createNode(39, "39");
//        int complexity = this.tree.join(x, t);
//
//            this.values = new LinkedList<>();
//            for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//            if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//            if (this.tree.isBST() == false) {
//            return false;
//        }
//
//            if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//    //        if (this.tree.getRoot().getKey() != root) {
//    //            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//    //            return false;
//    //        }
//
//            if (this.size != this.tree.size) {
//            return false;
//        }
//
//            if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//            if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//            if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//            if (keys.length != this.size) {
//            return false;
//        }
//
//            for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//            if (!this.checkMin()) {
//            return false;
//        }
//
//            if (!getInfo(this.tree)) {
//            return false;
//        }
//
//            return true;
//
//    }
//
//    public boolean joinCaseThisTallerSmaller() {
//        AVLTree t = new AVLTree();
//
//        for (int i = 0; i < 39; i++) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//
//        for (int i = 40; i < 60; i++) {
//            t.insert(i, String.valueOf(i));
//        }
//
//        this.size = this.tree.size() + t.size() + 1;
//        this.min = this.tree.min.getKey();
//        this.max = t.max.getKey();
//
//        int expected =  Math.abs(t.getRoot().getRank() - this.tree.getRoot().getRank()) + 1;
//
//        AVLTree.IAVLNode x = t.createNode(39, "39");
//        int complexity = this.tree.join(x, t);
//
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        //        if (this.tree.getRoot().getKey() != root) {
//        //            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//        //            return false;
//        //        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public boolean joinCaseThisShorterSmaller()  {
//        AVLTree t = new AVLTree();
//
//        for (int i = 0; i < 20; i++) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//
//        for (int i = 40; i < 80; i++) {
//            t.insert(i, String.valueOf(i));
//        }
//
//        this.size = this.tree.size() + t.size() + 1;
//        this.min = this.tree.min.getKey();
//        this.max = t.max.getKey();
//
//        int expected =  Math.abs(t.getRoot().getRank() - this.tree.getRoot().getRank()) + 1;
//
//        AVLTree.IAVLNode x = t.createNode(39, "39");
//        int complexity = this.tree.join(x, t);
//
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        //        if (this.tree.getRoot().getKey() != root) {
//        //            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//        //            return false;
//        //        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public boolean joinCaseCompletelyBalanced()  {
//        AVLTree t = new AVLTree();
//
//        for (int i = 0; i < 39; i++) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//
//        for (int i = 40; i < 79; i++) {
//            t.insert(i, String.valueOf(i));
//        }
//
//        this.size = this.tree.size() + t.size() + 1;
//        this.min = this.tree.min.getKey();
//        this.max = t.max.getKey();
//        int root = 39;
//
//        int expected =  Math.abs(t.getRoot().getRank() - this.tree.getRoot().getRank()) + 1;
//
//        AVLTree.IAVLNode x = t.createNode(39, "39");
//        int complexity = this.tree.join(x, t);
//
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        if (this.tree.getRoot().getKey() != root) {
//            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//            return false;
//        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public boolean joinCaseCompletelyUnbalanced()  {
//        AVLTree t = new AVLTree();
//
//        for (int i = 0; i < 39; i++) {
//            this.tree.insert(i, String.valueOf(i));
//        }
//
//        for (int i = 40; i < 41; i++) {
//            t.insert(i, String.valueOf(i));
//        }
//
//        this.size = this.tree.size() + t.size() + 1;
//        this.min = this.tree.min.getKey();
//        this.max = t.max.getKey();
//
//        int expected =  Math.abs(t.getRoot().getRank() - this.tree.getRoot().getRank()) + 1;
//
//        AVLTree.IAVLNode x = t.createNode(39, "39");
//        int complexity = this.tree.join(x, t);
//
//        this.values = new LinkedList<>();
//        for (int key: this.tree.keysToArray()) {
//            this.values.add(key);
//        }
//
//        if (complexity != expected) {
//            System.out.println("Bad complexity!, recieved " + complexity + " expected " + expected);
//            return false;
//        }
//
//
//        if (this.tree.isBST() == false) {
//            return false;
//        }
//
//        if (this.tree.isBalanced(this.tree.root) == false) {
//            return false;
//        }
//
//        //        if (this.tree.getRoot().getKey() != root) {
//        //            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
//        //            return false;
//        //        }
//
//        if (this.size != this.tree.size) {
//            return false;
//        }
//
//        if (this.min != this.tree.min.getKey()) {
//            return false;
//        }
//
//        if (this.max != this.tree.max.getKey()) {
//
//            return false;
//        }
//
//        if (this.checkSearch() == false) {
//            return false;
//        }
//
//        int[] keys = this.tree.keysToArray();
//
//        if (keys.length != this.size) {
//            return false;
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            if (!this.values.contains(keys[i])) {
//                return false;
//            }
//        }
//
//        if (!this.checkMin()) {
//            return false;
//        }
//
//        if (!getInfo(this.tree)) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//
//
//
//    public static void main(String[] args) {
//        IAVLNodeTests tests = new IAVLNodeTests();
//
//        if (tests.testRandomTree() == true) {
//            System.out.println("Random tree is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.testSortedTree() == true) {
//            System.out.println("sorted tree is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.testSortedBackwardsTree() == true) {
//            System.out.println("sorted backwards tree is ok");
//        }
////
////        if (tests.testDelete() == true) {
////            System.out.println("delete random is ok");
////        }
//
//
//        tests = new IAVLNodeTests();
//
//        if (tests.deleteCase1() == true) {
//            System.out.println("delete case 1 is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.deleteCase2() == true) {
//            System.out.println("delete case 2 is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.deleteCase3() == true) {
//            System.out.println("delete case 3 is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.deleteCase4() == true) {
//            System.out.println("delete case 4 is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.deleteCaseNonEmptyRoot() == true) {
//            System.out.println("delete case non empty root is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.deleteCaseLeftRoot() == true) {
//            System.out.println("delete case left root is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.deleteCaseRightRoot() == true) {
//            System.out.println("delete case right root is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.deleteCaseRoot() == true) {
//            System.out.println("delete case root is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.deleteCaseEverythingReverseOrder() == true) {
//            System.out.println("delete case everything reverse order is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.deleteCaseEverythingInOrder() == true) {
//            System.out.println("delete case everything in order is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.deleteCaseAlwaysRoot() == true) {
//            System.out.println("delete case always root is ok");
//        }
//
//
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCase1()) {
//            System.out.println("join case 1 is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCase2()) {
//            System.out.println("join case 2 is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCaseThisEmpty()) {
//            System.out.println("join case this empty is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCaseOtherEmpty()) {
//            System.out.println("join case other empty is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCaseBothEmpty()) {
//            System.out.println("join case both empty is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCase3()) {
//            System.out.println("join case 3 is ok");
//        }
//
//        System.out.println("########## NEW TESTS #############");
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCaseThisTallerBigger()) {
//            System.out.println("join joinCaseThisTallerBigger is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCaseThisShorterBigger()) {
//            System.out.println("join case joinCaseThisShorterBigger is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCaseThisTallerSmaller()) {
//            System.out.println("join case joinCaseThisTallerSmaller is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCaseThisShorterSmaller()) {
//            System.out.println("join case joinCaseThisShorterSmaller is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCaseCompletelyBalanced()) {
//            System.out.println("join case joinCaseCompletelyBalanced is ok");
//        }
//
//        tests = new IAVLNodeTests();
//
//        if (tests.joinCaseCompletelyUnbalanced()) {
//            System.out.println("join case joinCaseCompletelyUnbalanced is ok");
//        }
//
//        System.out.println("done");
//
//    }
//}
