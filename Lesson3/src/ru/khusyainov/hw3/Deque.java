package ru.khusyainov.hw3;

public class Deque<T> extends QueueImpl<T> {

    public Deque(int maxSize) {
        super(maxSize);
    }

    //front == left
    public void insertLeft(T value) {
        if (front == DEFAULT_FRONT) {
            front = queueArr.length;
        }
        items++;
        queueArr[--front] = value;
    }

    //rear == right
    public void insertRight(T value) {
        super.insert(value);
    }

    //front == left
    public T removeLeft() {
        return super.remove();
    }

    //rear == right
    @SuppressWarnings("unchecked")
    public T removeRight() {
        if (rear == DEFAULT_REAR) {
            rear = queueArr.length - 1;
        }
        items--;
        return (T) queueArr[rear--];
    }

    //front == left
    public T peekLeft() {
        return super.peek();
    }

    //rear == right
    @SuppressWarnings("unchecked")
    public T peekRight() {
        if (rear == DEFAULT_REAR) {
            return (T) queueArr[queueArr.length - 1];
        } else {
            return (T) queueArr[rear - 1];
        }
    }
}
