package ru.khusyainov.hw4;

public class SimpleLinkedList<T> implements LinkedList<T> {

    Node<T> elem;
    int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T value) {
        Node<T> newElem = new Node<>();
        newElem.value = value;
        newElem.previous = elem;
        elem = newElem;
        size++;
    }

    @Override
    public T remove() {
        Node<T> removed = elem;
        elem = elem.previous;
        size--;
        return removed.value;
    }

    @Override
    public boolean remove(T value) {
        if (elem.value == value) {
            return remove() == value;
        }
        Node<T> del = elem;
        Node<T> prev = null;
        while (del != null && del.value != value) {
            prev = del;
            del = del.previous;
        }
        if (del == null) {
            return false;
        }
        if (prev != null) {
            prev.previous = del.previous;
            size--;
            return true;
        } else {
            return false;//чего-то не сооброжу, чтобы этот вариант был возможен
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> print = elem;
        while (print != null) {
            sb.append(print.value).append(", ");
            print = print.previous;
        }
        return sb.append("]").toString().replace(", ]", "]");
    }
}
