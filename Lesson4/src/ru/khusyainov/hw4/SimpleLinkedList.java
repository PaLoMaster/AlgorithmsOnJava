package ru.khusyainov.hw4;

public class SimpleLinkedList<T> implements LinkedList<T> {

    Node<T> lastAdded;
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
        newElem.previous = lastAdded;
        lastAdded = newElem;
        size++;
    }

    @Override
    public T remove() {
        Node<T> removed = lastAdded;
        lastAdded = lastAdded.previous;
        size--;
        return removed.value;
    }

    @Override
    public boolean remove(T value) {
        if (lastAdded.value.equals(value)) {
            return remove().equals(value);
        }
        Node<T> del = lastAdded;
        Node<T> prev = null;
        do {
            prev = del;
            del = del.previous;
        } while (del != null && !del.value.equals(value));
        if (del == null) {
            return false;
        }
        prev.previous = del.previous;
        size--;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> print = lastAdded;
        while (print != null) {
            sb.append(print.value).append(", ");
            print = print.previous;
        }
        return sb.append("]").toString().replace(", ]", "]");
    }
}
