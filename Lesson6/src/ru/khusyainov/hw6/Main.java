package ru.khusyainov.hw6;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static final int MAX_DEEP = 4;
    private static final int MAX_VAL = 20;
    private static final int MAX_ADDS = 40;
    private static final int TREES = 20;

    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<Tree<Integer>> trees = new ArrayList<>(TREES);
        int balancedCount = 0;
        System.out.println("Деревья:");
        for (int i = 0; i < TREES; i++) {
            trees.add(new TreeImpl<>(MAX_DEEP));
            for (int j = 0; j < MAX_ADDS; j++) {
                int val = rand.nextInt(MAX_VAL * 2) - MAX_VAL;
                trees.get(i).add(new Node<>(val, val));
            }
            System.out.println(trees.get(i).toString() + "\nДерево " + 
                    (trees.get(i).isBalanced() ? "" : "не ") + "сбалансированное");
            System.out.println("Состав дерева в порядке возрастания:");
            trees.get(i).traverse(TraverseMode.IN_ORDER, (val -> System.out.print(val + ", ")));
            System.out.println("\n");
            balancedCount += trees.get(i).isBalanced() ? 1 : 0;
        }
        System.out.println("\nСбалансированные: " + (balancedCount * 100 / TREES) + "%");
    }
}
