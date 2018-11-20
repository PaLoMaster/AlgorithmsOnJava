package ru.khusyainov.hw3;

public class Stack<T> {

    private final Object[] stackArr;
    private int curSize;

    public Stack(int size) {
        stackArr = new Object[size];
    }

    public int size() {
        return curSize;
    }

    public boolean isEmpty() {
        return curSize == 0;
    }

    public boolean isFull() {
        return curSize == stackArr.length;
    }

    public void push(T value) {
        stackArr[curSize++] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        return (T) stackArr[--curSize];
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        return (T) stackArr[curSize - 1];
    }
}
