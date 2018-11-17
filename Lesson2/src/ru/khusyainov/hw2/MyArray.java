package ru.khusyainov.hw2;

import java.util.Arrays;

public class MyArray<T> {

    private final Object[] arr;
    private int curSize;

    public MyArray(int size) {
        arr = new Object[size];
    }

    public MyArray(MyArray copy) {
        curSize = copy.curSize;
        arr = Arrays.copyOf(copy.arr, copy.arr.length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < curSize; i++) {
            sb.append(arr[i]).append(", ");
        }
        return sb.append("]").toString().replace(", ]", "]");
    }

    public int getSize() {
        return curSize;
    }

    public void addElement(T value) {
        arr[curSize++] = value;
    }

    public boolean deleteElement(T value) {
        int i = findElement(value);
        if (i == -1) {
            return false;
        }
        if (i != curSize - 1) {
            System.arraycopy(arr, i + 1, arr, i, curSize - i - 1);
        }
        curSize--;
        return true;
    }

    public boolean contains(T value) {
        return findElement(value) != -1;
    }

    public int findElement(T value) {
        for (int i = 0; i < curSize; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private void change(int index1, int index2) {
        Object temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    @SuppressWarnings("unchecked")
    private int compare(int index1, int index2) {
        return ((Comparable) arr[index1]).compareTo(arr[index2]);
    }

    @SuppressWarnings("unchecked")
    private int compare(int index1, Object anotherVal) {
        return ((Comparable) arr[index1]).compareTo(anotherVal);
    }

    public void sortBubble() {
        int endOfSort = arr.length - 1;
        boolean haveUnsorderElements;
        do {
            haveUnsorderElements = false;
            for (int i = 0; i < endOfSort; i++) {
                if (compare(i, i + 1) > 0) {
                    change(i, i + 1);
                    haveUnsorderElements = true;
                }
            }
            endOfSort--;
        } while (haveUnsorderElements);
    }

    public void sortSelect() {
        for (int i = 0; i < curSize - 1; i++) {
            int mark = i;
            for (int j = i + 1; j < curSize; j++) {
                if (compare(j, mark) < 0) {
                    mark = j;
                }
            }
            if (i != mark) {
                change(i, mark);
            }
        }
    }

    public void sortInsert() {
        for (int i = 1; i < curSize; i++) {
            Object insertVal = arr[i];
            int insertPos = i;
            while (insertPos > 0 && compare(insertPos - 1, insertVal) >= 0) {
                arr[insertPos] = arr[insertPos - 1];
                insertPos--;
            }
            arr[insertPos] = insertVal;
        }
    }

    public boolean isSorted() {
        for (int i = 0; i < curSize - 1; i++) {
            if (compare(i, i + 1) > 0) {
                return false;
            }
        }
        return true;
    }
}
