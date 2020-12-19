package src;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import src.JoinMeasureAVL.IAVLNode;
import src.JoinMeasureAVL;

public class JoinMeasurements {

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

    public static int rootPredecessorKey(JoinMeasureAVL tree) {
        IAVLNode curr = tree.getRoot();
        curr = curr.getLeft();
        while(curr.getRight().isRealNode()) {
            curr = curr.getRight();
        }
        return curr.getKey();
    }

    public static int randomKey(int sizeFactor) {
        return ThreadLocalRandom.current().nextInt(0, sizeFactor*10000);
    }

    public static JoinMeasureAVL getRandomTree(int sizeFactor) {
        JoinMeasureAVL tree = new JoinMeasureAVL();
        int[] shuffledArray = getShuffledArray(sizeFactor * 10000);

        for (int item: shuffledArray) {
            tree.insert(item, String.valueOf(item));
        }

        return tree;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            JoinMeasureAVL treeRand = getRandomTree(i);
            JoinMeasureAVL treePred = getRandomTree(i);

            int randKey = randomKey(i);
            int pred = rootPredecessorKey(treePred);
            List<Integer> randJoin = treeRand.split(randKey);
            List<Integer> predJoin = treePred.split(pred);

            System.out.println();
            System.out.print(String.valueOf(String.valueOf(i*10000) + " Random:,"));
            for (int cost: randJoin) {
                System.out.print(String.valueOf(cost) + ", ");
            }
            System.out.println();
            System.out.print(String.valueOf(String.valueOf(i*10000) + " Predecessor:,"));
            for (int cost: predJoin) {
                System.out.print(String.valueOf(cost) + ", ");
            }
        }
    }

    static final int COUNT = 5;

    public static void print2DUtil(MeasureAVL.IAVLNode root, int space) {
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
    public static void print2D(MeasureAVL.IAVLNode root) {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }
}