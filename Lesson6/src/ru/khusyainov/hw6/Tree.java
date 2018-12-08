package ru.khusyainov.hw6;

import java.util.function.Consumer;

public interface Tree<T> {

    void add(Node<T> newNode);

    T remove(int key);

    T find(int key);

    void traverse(TraverseMode mode, Consumer<? super T> action);
    
    boolean isBalanced();
}