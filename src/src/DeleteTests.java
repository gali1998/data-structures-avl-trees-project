package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DeleteTests {


    public static void main(String[] args) {
        System.out.println(testSpecificCase());
        System.out.println(randomTrees());
        System.out.println(multiDeleteRandomTrees());
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

    public static boolean checkDelete(AVLTree t1, int key) {
        int size = t1.size() - 1;
        int min = t1.keysToArray()[0];
        int max = t1.keysToArray()[size];
        if (key == min) {
            min = t1.keysToArray()[1];
        }
        if (key == max) {
            max = t1.keysToArray()[size - 2];
        }
        List<Integer> allKeys = new ArrayList<>();
        for (int k: t1.keysToArray()) {
            allKeys.add(k);
        }

        t1.delete(key);

        if (t1.getRoot().getSize() != size) {
            System.out.println("Bad root!, recieved " + t1.getRoot().getSize() + " expected " + size);
            System.out.println("Bad root!, recieved " + t1.getRoot().getKey());
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
        List<Integer> values = new ArrayList<>();
        for (int k: t1.keysToArray()) {
            values.add(k);
        }
        for (int i = 0; i < keys.length; i++) {
            if (!values.contains(keys[i])) {
                System.out.println("Bad keys to array " + keys[i]);
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

        if (!testFindAll(t1, allKeys, key)) {
            System.out.println("Didn't find all keys");
            return false;
        }

        return true;
    }

    private static boolean testFindAll(AVLTree tree, List<Integer> allKeys, int deleteKey) {
        for (int key: allKeys) {
            if (tree.search(key) == null) {
                if (key != deleteKey) {
                    System.out.println("didnt find key " + key);
                    return false;
                }
            }
        }
        for (int key: tree.keysToArray()) {
            if (allKeys.indexOf(key) == -1) {
                if (key != deleteKey) {
                    System.out.println("didnt find key " + key);
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean randomTrees() {
        AVLTree t1 = new AVLTree();
        int maxSize = 30;
        int[] combinations = new int[maxSize];
        int key = ThreadLocalRandom.current().nextInt(0, maxSize);
        int[] items = new int[0];

        boolean works = true;
        for (int k = 0; k < 10000; k++) {
            t1 = new AVLTree();
            key = ThreadLocalRandom.current().nextInt(1, maxSize);
            combinations[key]++;
            items = getShuffledArray(maxSize);
            for (int i: items) {
                t1.insert(i, String.valueOf(i));
            }

            works = checkDelete(t1, key);
            if (!works) {
                break;
            }
        }
        System.out.println("key: " + key);
        System.out.println("tree");
        for (int i: items) {
            System.out.print(String.valueOf(i) + ",");
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i< combinations.length; i++) {
            System.out.print(i);
            System.out.print("  ");
            System.out.print(combinations[i] + ",");
            System.out.println();
        }
        return works;
    }

    public static boolean multiDeleteRandomTrees() {
        AVLTree t1 = new AVLTree();
        int maxSize = 30;
        int lastKey = -1;
        int[] combinations = new int[maxSize];
        int[] items = new int[0];

        boolean works = true;
        for (int k = 0; k < 10000; k++) {
            int numDeletes = ThreadLocalRandom.current().nextInt(0, maxSize);
            List<Integer> deleteKeys = new ArrayList<>();
            int j = 0;
            while(j < numDeletes) {
                int deleteK = ThreadLocalRandom.current().nextInt(1, maxSize);
                if (!deleteKeys.contains(deleteK)) {
                    deleteKeys.add(deleteK);
                    j++;
                }
            }
            t1 = new AVLTree();

            items = getShuffledArray(maxSize);
            for (int i: items) {
                t1.insert(i, String.valueOf(i));
            }
            for (int key: deleteKeys) {
                works = checkDelete(t1, key);
                combinations[key]++;
                lastKey = key;
                if (!works) {
                    break;
                }
            }
            if (!works) {
                break;
            }
        }
        System.out.println("key: " + lastKey);
        System.out.println("tree");
        for (int i: items) {
            System.out.print(String.valueOf(i) + ",");
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i< combinations.length; i++) {
            System.out.print(i);
            System.out.print("  ");
            System.out.print(combinations[i] + ",");
            System.out.println();
        }
        return works;
    }

    public static boolean testSpecificCase() {
        int[] keys = new int [] {61,37,23,91,51,25,19,68,78,32,39,99,67,59,87,29,18,70,12,38,9,94,90,54,48,89,73,57,93,46,71,100,8,83,2,98,43,52,7,85,20,15,47,22,63,84,6,41,16,5,13,97,60,11,65,1,24,88,42,77,55,53,81,35,36,17,62,34,74,75,30,58,44,26,49,69,21,10,45,33,50,95,40,92,82,72,14,3,80,28,64,66,27,96,79,31,56,86,76,4};
        int key = 32;
        AVLTree t1 = new AVLTree();
        for (int k: keys) {
            t1.insert(k, String.valueOf(k));
        }
        return checkDelete(t1, key);
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
