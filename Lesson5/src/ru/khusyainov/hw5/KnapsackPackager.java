package ru.khusyainov.hw5;

import java.util.ArrayList;
import java.util.Collection;

public class KnapsackPackager {

    private final ArrayList<KnapsackObject> toPack;
    private ArrayList<Knapsack> result;
    private final int maxWeight;

    public KnapsackPackager(Collection<KnapsackObject> toPack, int maxWeight) {
        this.toPack = new ArrayList<>(toPack);
        this.maxWeight = maxWeight;
    }

    private Knapsack circlePack(Collection<KnapsackObject> newPack) {
        Knapsack newKnapsnack = new Knapsack(maxWeight);
        newPack.forEach(object -> newKnapsnack.add(object));
        return newKnapsnack;
    }

    private void checkPriseAndSavePackIfNeeded(Knapsack newKnapsnack) {
        if (!result.isEmpty() && newKnapsnack.getContentsPrice() > result.get(0).getContentsPrice()) {
            result = new ArrayList<>();
            result.add(newKnapsnack);
        } else if (result.isEmpty()) {
            result.add(newKnapsnack);
        }
    }

    public String circleWork() {
        result = new ArrayList<>();
        result.add(circlePack(toPack));
        for (int i = 0; i < toPack.size(); i++) {
            ArrayList<KnapsackObject> newPack = new ArrayList<>(toPack);
            newPack.remove(i);
            Knapsack newKnapsnack = new Knapsack(maxWeight, newPack);
            if (!result.contains(newKnapsnack)) {
                checkPriseAndSavePackIfNeeded(newKnapsnack);
            }
        }
        return toString();
    }

    private void recursePack(int newSize) {
        if (newSize == 1) {
            return;
        }
        for (int i = 0; i < newSize; i++) {
            recursePack(newSize - 1);
            Knapsack newKnapsnack = new Knapsack(maxWeight, toPack);
            if (newSize == 2 && !result.contains(newKnapsnack)) {
                checkPriseAndSavePackIfNeeded(newKnapsnack);
            }
            rotatePackage(newSize);
        }
    }

    private void rotatePackage(int newSize) {
        int pos = toPack.size() - newSize;
        KnapsackObject temp = toPack.remove(pos);
        toPack.add(temp);
    }

    public String recurseWork() {
        result = new ArrayList<>();
        recursePack(toPack.size());
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append("Сборка №" + (i + 1) + ":\n" + result.get(i).toString() + "\n");
        }
        return sb.toString();
    }

}
