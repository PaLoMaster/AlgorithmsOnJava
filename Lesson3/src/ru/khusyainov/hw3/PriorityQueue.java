package ru.khusyainov.hw3;

import java.util.Arrays;

public class PriorityQueue<T> extends QueueImpl<T> {

    public PriorityQueue(int maxSize) {
        super(maxSize);
    }

    @SuppressWarnings("unchecked")
    private int compare(int index1, Object anotherVal) {
        return ((Comparable) queueArr[index1]).compareTo(anotherVal);
    }

    private void safeCycleAssignment(int i, Object value) {
        if (i + 1 == queueArr.length) {
            queueArr[0] = value;
        } else {
            queueArr[i + 1] = value;
        }
    }

    @Override
    public void insert(T value) {
        if (rear == queueArr.length - 1) {
            rear = DEFAULT_REAR;
        }
        if (items == 0) {
            queueArr[++rear] = value;
        } else {
            int i, j;
            for (i = rear++, j = items - 1; j >= 0; i--, j--) {
                if (i == DEFAULT_REAR) {
                    i = queueArr.length - 1;
                }
                if (compare(i, value) > 0) {
                    safeCycleAssignment(i, queueArr[i]);
                } else {
                    break;
                }
            }
            safeCycleAssignment(i, value);
        }
        items++;
    }
}
