import java.util.Arrays;

public class Tests {


    public static void delete(AVLTree t, int key, int expectedRebalancing) {
        int initialSize = t.size();
        int rebalancing = t.delete(key);
        if (rebalancing != expectedRebalancing) {
            System.out.println("Wrong number of actions! expected: " + expectedRebalancing + " recieved " + rebalancing);
        }
        if (!t.isBalanced()) {
            System.out.println("Delete is not rebalanced!");
        }
        if (t.size() != initialSize - 1) {
            System.out.println("Bad size! expected: " + String.valueOf(initialSize - 1) + " recieved " + t.size());
        }
    }

    public static void join(AVLTree smallerKeysTree, AVLTree biggerKeysTree, AVLTree.IAVLNode x, int expected ) {
        int smallerSize = smallerKeysTree.size();
        int biggerSize = biggerKeysTree.size();
        int[] smallerKeys = smallerKeysTree.keysToArray();
        int[] biggerKeys = biggerKeysTree.keysToArray();
        int complexity = smallerKeysTree.join(x, biggerKeysTree);
        if (expected != complexity) {
            System.out.println("Bad complexity! expected: " + expected + " recieved: " + complexity);
        }
        for (int key: smallerKeys) {
            if (smallerKeysTree.search(key) == null) {
                System.out.println("Lost key " + key + " from smallerKeysTree!");
            }
        }
        for (int key: biggerKeys) {
            if (smallerKeysTree.search(key) == null) {
                System.out.println("Lost key " + key + " from biggerKeysTree!");
            }
        }
        if (smallerKeysTree.size() != smallerSize + biggerSize + 1) {
            System.out.println("Bad Size! expected " + smallerSize + biggerSize + 1 + " recieved " + smallerKeysTree.size());
        }

        AVLTree[] trees = smallerKeysTree.split(x.getKey());
        if ((!trees[0].keysToArray().equals(smallerKeys)) || (!trees[0].keysToArray().equals(biggerKeys))) {
            System.out.println("Bad split after join!");
        }
    }

    public static void getKeys(AVLTree t) {
        int[] keys = t.keysToArray();
        int[] sorted = Arrays.copyOf(keys, keys.length);
        Arrays.sort(sorted);
        if (Arrays.equals(keys, sorted)) {
            System.out.println("keysToInfo is not sorted!");
            System.out.println("Keys:");
            for (int key: sorted) {
                System.out.print(String.valueOf(key) + ',');
            }
            System.out.println("Recieved:");
            for (int key: keys) {
                System.out.print(String.valueOf(key) + ',');
            }
        }
        if (keys.length < t.size()) {
            System.out.println("Missing " + String.valueOf(t.size() - keys.length) + "keys.");
        }
    }

    public static void getInfo(AVLTree t) {
        String[] info = t.infoToArray();
        int[] keys = t.keysToArray();
        for (int i = 0; i < keys.length; i++) {
            if (!info[i].equals(t.search(keys[i]))) {
                System.out.println("Error in Info to array!");
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
        }
    }
}
