package ru.khusyainov.hw6;

public class Node<T> {

    private final T data;
    private final int key;
    private int level;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(int key, T data) {
        this.key = key;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public int getKey() {
        return key;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public Node<T> getChild(int key) {
        if (isLeftChild(key)) {
            return getLeftChild();
        } else {
            return getRightChild();
        }
    }

    public boolean isLeftChild(int key) {
        return key < this.key;
    }

    public boolean isRightChild(int key) {
        return key > this.key;
    }

    public boolean isLeaf() {
        return getLeftChild() == null && getRightChild() == null;
    }

    public boolean hasOnlyOneChildNode() {
        return !isLeaf() && (getLeftChild() == null || getRightChild() == null);
    }
}
