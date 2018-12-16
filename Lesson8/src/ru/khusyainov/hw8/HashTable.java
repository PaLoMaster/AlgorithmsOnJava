package ru.khusyainov.hw8;

import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<T> {

    public static final int HASH_CONST = 23;
    private LinkedList[] listsArr;
    private int maxSize;
    private int size;

    public HashTable(int maxSize) {
        this.maxSize = maxSize;
        listsArr = new LinkedList[maxSize];
    }

    public int getSize() {
        return size;
    }

    private int hashFunc(int key) {
        return key % maxSize;
    }

    @SuppressWarnings("unchecked")
    private void addVal(int index, T value) {
        listsArr[index].add(value);
    }

    public boolean add(T value) {
        int key = value.hashCode();
        int index = hashFunc(key);

        if (listsArr[index] == null) {
            listsArr[index] = new LinkedList();
        }

        addVal(index, value);
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    private LinkedList<T> getLL(int index) {
        return (LinkedList<T>) listsArr[index];
    }

    public T delete(int key) {
        int index = hashFunc(key);

        if (listsArr[index] == null) {
            return null;
        }

        Iterator<T> it = getLL(index).iterator();
        while (it.hasNext()) {
            T curVal = it.next();
            if (curVal.hashCode() == key) {
                it.remove();
                return curVal;
            }
        }

        return null;
    }

    public T find(int key) {
        int index = hashFunc(key);

        if (listsArr[index] == null) {
            return null;
        }

        Iterator<T> it = getLL(index).iterator();
        while (it.hasNext()) {
            T curVal = it.next();
            if (curVal.hashCode() == key) {
                return curVal;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listsArr.length; i++) {
            sb.append(i + ":\n"
                    + (listsArr[i] == null ? "пусто" : listsArr[i])
                    + "\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
