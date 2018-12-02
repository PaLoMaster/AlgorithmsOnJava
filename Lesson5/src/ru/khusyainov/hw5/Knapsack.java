package ru.khusyainov.hw5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Knapsack {

    private ArrayList<KnapsackObject> content;
    private final int maxWeight;
    private int objectsPrice;
    private int objectsWeight;

    public Knapsack(int maxWeight) {
        this.maxWeight = maxWeight;
        content = new ArrayList<>();
    }

    public Knapsack(int maxWeight, KnapsackObject object) {
        this(maxWeight);
        addObj(object);
    }

    public Knapsack(int maxWeight, Collection<KnapsackObject> objects) {
        this(maxWeight);
        objects.forEach(object -> add(object));
    }

    public int getContentsWeight() {
        return objectsWeight;
    }

    public int getContentsPrice() {
        return objectsPrice;
    }

    public boolean canPackaged(KnapsackObject object) {
        return objectsWeight + object.getWeight() <= maxWeight;
    }

    private boolean addObj(KnapsackObject object) {
        boolean rez = content.add(object);
        if (rez) {
            objectsPrice += object.getPrice();
            objectsWeight += object.getWeight();
        }
        return rez;
    }

    public boolean add(KnapsackObject object) {
        if (canPackaged(object)) {
            return addObj(object);
        }
        return false;
    }

    public void clear() {
        content = new ArrayList<>();
        objectsPrice = 0;
        objectsWeight = 0;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.content);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Knapsack other = (Knapsack) obj;
        if (content.size() != other.content.size()) {
            return false;
        }
        ArrayList<KnapsackObject> cont1 = new ArrayList<>(content);
        ArrayList<KnapsackObject> cont2 = new ArrayList<>(other.content);
        cont1.forEach(o1 -> {
            if (cont2.contains(o1)) {
                cont2.remove(o1);
            } 
        });
        return cont2.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Рюкзак/ранец ("
                + "макс. вместимость - " + maxWeight
                + "кг, цена текущего содержимого - " + objectsPrice
                + ", общий вес текущего содержимого - " + objectsWeight
                + ")\nСодержимое:\n");
        content.forEach(object -> sb.append(object.toString()).append("\n"));
        return sb.toString();
    }
}
