package ru.khusyainov.hw6;

import java.util.Objects;
import java.util.Stack;
import java.util.function.Consumer;

public class TreeImpl<T> implements Tree<T> {

    private final class NodeAndParent<T> {

        Node<T> currentNode;
        Node<T> parent;

        public NodeAndParent(Node<T> currentNode, Node<T> parent) {
            this.currentNode = currentNode;
            this.parent = parent;
        }
    }

    private Node<T> root;
    private final int maxDeep;

    public TreeImpl(int maxDeep) {
        this.maxDeep = maxDeep;
    }

    @Override
    public void add(Node<T> newNode) {
        NodeAndParent<T> nodeAndParent = findLeafNode(newNode.getKey());
        Node<T> parent = nodeAndParent.parent;

        if (parent == null) {
            root = newNode;
            newNode.setLevel(1);
            return;
        }

        if (parent.getLevel() + 1 > maxDeep) {
            return;
        }

        newNode.setLevel(parent.getLevel() + 1);
        if (parent.isLeftChild(newNode.getKey())) {
            parent.setLeftChild(newNode);
        } else if (parent.isRightChild(newNode.getKey())) {
            parent.setRightChild(newNode);
        }
    }

    private NodeAndParent<T> findLeafNode(int key) {
        Node<T> current = root;
        Node<T> parent = null;
        while (current != null) {
            parent = current;
            current = current.getChild(key);
        }
        return new NodeAndParent<>(null, parent);
    }

    @Override
    public T remove(int key) {
        NodeAndParent<T> nodeAndParent = findNodeAndParent(key);
        Node<T> removedNode = nodeAndParent.currentNode;
        Node<T> parent = nodeAndParent.parent;

        if (removedNode == null) {
            return null;
        }

        if (removedNode.isLeaf()) {
            removeLeafNode(removedNode, parent);
        } else if (removedNode.hasOnlyOneChildNode()) {
            removeNodeWithOneChild(removedNode, parent);
        } else {
            removeNodeWithBothChildrens(removedNode, parent);
        }

        return removedNode.getData();
    }

    private void removeNodeWithBothChildrens(Node<T> removedNode, Node<T> parent) {
        Node<T> successor = getSuccessor(removedNode);
        successor.setLeftChild(removedNode.getLeftChild());
        if (removedNode == root) {
            root = successor;
        } else {
            if (parent.isLeftChild(removedNode.getKey())) {
                parent.setLeftChild(successor);
            } else {
                parent.setRightChild(successor);
            }
        }
    }

    private Node<T> getSuccessor(Node<T> node) {
        Node<T> successorParent = node;
        Node<T> successor = node.getRightChild();
        Node<T> current = successor;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != node.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(node.getRightChild());
        }

        return successor;
    }

    private void removeNodeWithOneChild(Node<T> removedNode, Node<T> parent) {
        Node<T> removedChildNode
                = removedNode.getLeftChild() != null ? removedNode.getLeftChild() : removedNode.getRightChild();
        if (removedNode == root) {
            root = removedChildNode;
        } else if (parent.isLeftChild(removedNode.getKey())) {
            parent.setLeftChild(removedChildNode);
        } else {
            parent.setRightChild(removedChildNode);
        }
    }

    private void removeLeafNode(Node<T> removedNode, Node<T> parent) {
        if (removedNode == root) {
            root = null;
        } else if (parent.isLeftChild(removedNode.getKey())) {
            parent.setLeftChild(null);
        } else {
            parent.setRightChild(null);
        }
    }

    @Override
    public T find(int key) {
        Node<T> node = findNodeAndParent(key).currentNode;
        return node == null ? null : node.getData();
    }

    private NodeAndParent<T> findNodeAndParent(int id) {
        Node<T> current = root;
        Node<T> parent = null;
        while (current != null) {
            if (id == current.getKey()) {
                return new NodeAndParent<>(current, parent);
            }
            parent = current;
            current = current.getChild(id);
        }

        return new NodeAndParent<>(null, parent);
    }

    @Override
    public void traverse(TraverseMode mode, Consumer<? super T> action) {
        Objects.requireNonNull(action);
        switch (mode) {
            case IN_ORDER:
                inOrder(root, action);
                break;
            case PRE_ORDER:
                preOrder(root, action);
                break;
            case POST_ORDER:
                postOrder(root, action);
                break;
            default:
                throw new IllegalArgumentException("Unknown travers mode: " + mode);
        }
    }

    private void postOrder(Node<T> root, Consumer<? super T> action) {
        if (root != null) {
            postOrder(root.getLeftChild(), action);
            postOrder(root.getRightChild(), action);
            action.accept(root.getData());
        }
    }

    private void preOrder(Node<T> root, Consumer<? super T> action) {
        if (root != null) {
            action.accept(root.getData());
            preOrder(root.getLeftChild(), action);
            preOrder(root.getRightChild(), action);
        }
    }

    private void inOrder(Node<T> root, Consumer<? super T> action) {
        if (root != null) {
            inOrder(root.getLeftChild(), action);
            action.accept(root.getData());
            inOrder(root.getRightChild(), action);
        }
    }

    @Override
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node<T> node) {
        return (node == null)
                || (isBalanced(node.getLeftChild())
                && isBalanced(node.getRightChild())
                && Math.abs(height(node.getLeftChild()) - height(node.getRightChild())) <= 1);
    }

    private int height(Node<T> node) {
        return node == null ? 0 : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Stack<Node<T>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 50;

        boolean isRowEmpty = false;
        sb.append("................................................................\n");

        while (!isRowEmpty) {
            Stack<Node<T>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                sb.append(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<T> tempNode = globalStack.pop();
                if (tempNode != null) {
                    sb.append(tempNode.getData());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (!tempNode.isLeaf()) {
                        isRowEmpty = false;
                    }
                } else {
                    sb.append("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                if (!globalStack.isEmpty()) {
                    for (int j = 0; j < nBlanks * 2 - 2; j++) {
                        sb.append(" ");
                    }
                }
            }

            sb.append("\n");

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        return sb.append("................................................................").toString();
    }
}
