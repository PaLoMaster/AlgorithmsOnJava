package ru.khusyainov.hw4;

public interface LinkedList<T> {
    
    int size();
    
    boolean isEmpty();
    
    void add(T value);
    
    T remove();
    
    boolean remove(T value);
}
