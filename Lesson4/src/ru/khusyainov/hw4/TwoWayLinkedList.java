package ru.khusyainov.hw4;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<T> extends SimpleLinkedList<T> implements Iterable<T> {

    Node<T> firstAdded;
    int lastGetIndex;
    Node<T> lastGetNode;

    //from first to last: link by *.next
    //from last to first: link by *.previous
    @Override
    public void add(T value) {
        Node<T> newElem = new Node<>();
        newElem.value = value;
        newElem.previous = lastAdded;
        if (lastAdded != null) {
            lastAdded.next = newElem;
        }
        lastAdded = newElem;
        if (size == 0) {
            firstAdded = lastAdded;
        }
        size++;
    }

    @Override
    public T remove() {
        if (lastAdded.previous != null) {
            lastAdded.previous.next = null;
        }
        return super.remove();
    }

    @Override
    public boolean remove(T value) {
        if (lastAdded.value.equals(value)) {
            return removeLast().equals(value);
        } else if (firstAdded.value.equals(value)) {
            return removeFirst().equals(value);
        }
        Node<T> del = lastAdded;
        while (del != null && !del.value.equals(value)) {
            del = del.previous;
        }
        if (del == null) {
            return false;
        }
        return unlinkNode(del);
    }

    public void addBeforeFirst(T value) {
        linkBefore(firstAdded, value);
    }

    public void addAfterLast(T value) {
        add(value);
    }

    public T removeFirst() {
        Node<T> removed = firstAdded;
        if (firstAdded.next != null) {
            firstAdded.next.previous = null;
        }
        firstAdded = firstAdded.next;
        size--;
        return removed.value;
    }

    public T removeLast() {
        return remove();
    }

    public String toStringFromFirstAdded() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> print = firstAdded;
        while (print != null) {
            sb.append(print.value).append(", ");
            print = print.next;
        }
        return sb.append("]").toString().replace(", ]", "]");
    }

    public String toStringFromLastAdded() {
        return toString();
    }

    //by default: link from first to last
    @Override
    public Iterator<T> iterator() {
        return new L_L_Iterator();
    }

    public ListIterator<T> listIteratorFromFirst() {
        return new L_L_Iterator(firstAdded);
    }

    public ListIterator<T> listIteratorFromLast() {
        return new L_L_Iterator(lastAdded);
    }

    boolean unlinkNode(Node<T> del) {
//        System.out.println(del.value);
        if (del.next != null) {
            del.next.previous = del.previous;
            if (del == firstAdded) {
                firstAdded = del.next;
            }
        }
        if (del.previous != null) {
            del.previous.next = del.next;
            if (del == lastAdded) {
                lastAdded = del.previous;
            }
        }
        size--;
        return true;
    }

    void linkBefore(Node<T> next, T value) {
        Node<T> newElem = new Node<>();
        newElem.value = value;
        newElem.next = next;
        newElem.previous = next.previous;
        if (next.previous != null) {
            next.previous.next = newElem;
        } else if (next == firstAdded) {
            firstAdded = newElem;
        }
        next.previous = newElem;
        size++;
    }

    void linkAfter(Node<T> previous, T value) {
        Node<T> newElem = new Node<>();
        newElem.value = value;
        newElem.previous = previous;
        newElem.next = previous.next;
        if (previous.next != null) {
            previous.next.previous = newElem;
        } else if (previous == lastAdded) {
            lastAdded = newElem;
        }
        previous.next = newElem;
        size++;
    }

    private class L_L_Iterator implements ListIterator<T> {

        private Node<T> curElem;
        private Node<T> prevElem;

        L_L_Iterator() {
            this(firstAdded);
        }

        L_L_Iterator(Node<T> first) {
            prevElem = first;
        }

        @Override
        public boolean hasNext() {
            return prevElem != null && prevElem.next != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (curElem != null) {
                curElem = curElem.next;
            } else {
                curElem = prevElem;
            }
            prevElem = curElem;
            return curElem.value;
        }

        @Override
        public boolean hasPrevious() {
            return prevElem != null && prevElem.previous != null;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (curElem != null) {
                curElem = curElem.previous;
            } else {
                curElem = prevElem;
            }
            prevElem = curElem;
            return curElem.value;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void remove() {
            prevElem = curElem.previous;
            unlinkNode(curElem);
        }

        @Override
        public void set(T value) {
            curElem.value = value;
        }

        @Override
        public void add(T value) {
            linkBefore(curElem, value);
        }
    }
}
