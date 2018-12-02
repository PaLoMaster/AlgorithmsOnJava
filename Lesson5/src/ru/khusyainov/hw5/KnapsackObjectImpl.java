package ru.khusyainov.hw5;

import java.util.Objects;

public class KnapsackObjectImpl implements KnapsackObject {

    private final String name;
    private final int weight;
    private int price;

    public KnapsackObjectImpl(String name, int price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + this.price;
        hash = 83 * hash + this.weight;
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
        final KnapsackObjectImpl other = (KnapsackObjectImpl) obj;
        if (price != other.price || weight != other.weight) {
            return false;
        }
        return name.equals(other.name);
    }

    @Override
    public String toString() {
        return name + ": цена - " + price + ", вес - " + weight;
    }
}
