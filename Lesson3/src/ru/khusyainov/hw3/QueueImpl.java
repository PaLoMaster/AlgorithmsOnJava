
package ru.khusyainov.hw3;

public class QueueImpl<T> implements Queue<T> {

    protected static final int DEFAULT_FRONT = 0;
    protected static final int DEFAULT_REAR = -1;
    protected final Object[] queueArr;
    protected int front;
    protected int rear;
    protected int items;

    public QueueImpl(int maxSize) {
        queueArr = new Object[maxSize];
        front = DEFAULT_FRONT;
        rear = DEFAULT_REAR;
    }

    @Override
    public int size() {
        return items;
    }

    @Override
    public boolean isEmpty() {
        return items == 0;
    }

    @Override
    public boolean isFull() {
        return items == queueArr.length;
    }

    @Override
    public void insert(T value) {
        if (rear == queueArr.length - 1) {
            rear = DEFAULT_REAR;
        }
        queueArr[++rear] = value;
        items++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove() {
        if (front == queueArr.length) {
            front = DEFAULT_FRONT;
        }
        items--;
        return (T) queueArr[front++];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (front == queueArr.length) {
            return (T) queueArr[0];
        } else {
            return (T) queueArr[front];
        }
    }

}
