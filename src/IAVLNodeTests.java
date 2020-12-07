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

            this.tree.insert(val, String.valueOf(val));

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
            this.tree.insert(i, String.valueOf(i));
        }
    }

    private void insertSortedTreeBackwards() {
        Random rand = new Random();

        this.size = this.getSize();
        this.max = this.size;
        this.min = 1;

        for (int i = this.size; i > 0; i--) {
            this.tree.insert(i, String.valueOf(i));
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

        if (!getInfo(this.tree)) {
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

        if (!getInfo(this.tree)) {
            return false;
        }

        return true;
    }

    private boolean testDelete() {
        this.insertSortedTree();

        int[] keys = this.tree.keysToArray();
        Random rand = new Random();
        int key = keys[rand.nextInt(keys.length)];


        int complexity = this.tree.delete(key);

        System.out.println("delete complexity: " + complexity);
        complexity = this.tree.delete(this.tree.getRoot().getKey());

        System.out.println("delete complexity: " + complexity);

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

//        int[] keys = this.tree.keysToArray();

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

        if (!getInfo(this.tree)) {
            return false;
        }

        return true;
    }

    private boolean deleteCase1() {
        this.tree.insert(10, "10");
        this.tree.insert(5, "5");
        this.tree.insert(20, "20");
        this.tree.insert(8, "8");
        this.tree.insert(4, "4");
        this.tree.insert(16, "16");
        this.tree.insert(3, "3");
        this.size = this.tree.size - 1;
        this.min = 3;
        this.max = 20;

        int comp = this.tree.delete(8);
        this.values = new LinkedList<>();
        for (int key: this.tree.keysToArray()) {
            this.values.add(key);
        }


        if (comp != 4) {
            System.out.println("bad complexity " + comp);
            return false;
        }

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

        if (!getInfo(this.tree)) {
            return false;
        }

        return true;
    }

    private boolean deleteCase2() {
        this.tree.insert(10, "10");
        this.tree.insert(5, "5");
        this.tree.insert(20, "20");
        this.tree.insert(8, "8");
        this.tree.insert(4, "4");
        this.tree.insert(16, "16");
        this.tree.insert(3, "3");
        this.size = this.tree.size - 1;
        this.min = 4;
        this.max = 20;

        int comp = this.tree.delete(3);
        this.values = new LinkedList<>();
        for (int key: this.tree.keysToArray()) {
            this.values.add(key);
        }


        if (comp != 3) {
            System.out.println("bad complexity " + comp);
            return false;
        }

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

        if (!getInfo(this.tree)) {
            return false;
        }

        return true;
    }

    private boolean deleteCase3() {
        this.tree.insert(10, "10");
        this.tree.insert(5, "5");
        this.tree.insert(20, "20");
        this.tree.insert(8, "8");
        this.tree.insert(4, "4");
        this.tree.insert(16, "16");
        this.tree.insert(3, "3");
        this.size = this.tree.size - 1;
        this.min = 3;
        this.max = 16;
        int root = 5;

        int comp = this.tree.delete(20);
        this.values = new LinkedList<>();
        for (int key: this.tree.keysToArray()) {
            this.values.add(key);
        }


        if (comp != 3) {
            System.out.println("bad complexity " + comp);
            return false;
        }

        if (this.tree.isBST() == false) {
            return false;
        }

        if (this.tree.isBalanced(this.tree.root) == false) {
            return false;
        }

        if (this.tree.getRoot().getKey() != root) {
            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
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

        if (!getInfo(this.tree)) {
            return false;
        }

        return true;
    }

    private boolean deleteCase4() {
        this.tree.insert(3, "3");
        this.tree.insert(2, "2");
        this.tree.insert(4, "4");
        this.tree.insert(1, "1");
        this.tree.insert(5, "5");

        this.size = this.tree.size - 2;
        this.min = 3;
        this.max = 5;
        int root = 4;

        int comp = this.tree.delete(2);
        if (comp != 0) {
            System.out.println("bad complexity " + comp);
            return false;
        }
        comp += this.tree.delete(1);

        this.values = new LinkedList<>();
        for (int key: this.tree.keysToArray()) {
            this.values.add(key);
        }


        if (comp != 3) {
            System.out.println("bad complexity " + comp);
            return false;
        }

        if (this.tree.isBST() == false) {
            return false;
        }

        if (this.tree.isBalanced(this.tree.root) == false) {
            return false;
        }

        if (this.tree.getRoot().getKey() != root) {
            System.out.println("Error with new key, recieved " + this.tree.getRoot().getKey());
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

        if (!getInfo(this.tree)) {
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

        if (!getInfo(this.tree)) {
            return false;
        }

        return true;
    }

    public static boolean getInfo(AVLTree t) {
        boolean works = true;
        String[] info = t.infoToArray();
        int[] keys = t.keysToArray();
        for (int i = 0; i < keys.length; i++) {
            if (!info[i].equals(t.search(keys[i]))) {
                System.out.println("Error in Info to array!");
                works = false;
                System.out.println("Keys:");
                for (int key: keys) {
                    System.out.print(String.valueOf(key) + ',');
                }
                System.out.println("Recieved:");
                for (String str: info) {
                    System.out.print(str + ',');
                }
                break;
            }
        }


        if (info.length < t.size()) {
            System.out.println("Missing " + String.valueOf(t.size() - info.length) + "info items.");
            works = false;
        }

        return works;
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
//
//        if (tests.testDelete() == true) {
//            System.out.println("delete random is ok");
//        }


        tests = new IAVLNodeTests();

        if (tests.deleteCase1() == true) {
            System.out.println("delete case 1 is ok");
        }

        tests = new IAVLNodeTests();

        if (tests.deleteCase2() == true) {
            System.out.println("delete case 2 is ok");
        }

        tests = new IAVLNodeTests();

        if (tests.deleteCase3() == true) {
            System.out.println("delete case 3 is ok");
        }

        tests = new IAVLNodeTests();

        if (tests.deleteCase4() == true) {
            System.out.println("delete case 4 is ok");
        }


        System.out.println("done");

    }
}
