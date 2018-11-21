package ru.khusyainov.hw3;

public interface Queue<T> {

    public int size();

    public boolean isEmpty();

    public boolean isFull();

    public void insert(T value);

    public T remove();

    public T peek();
}
