package ru.khusyainov.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex<T> {

    private final T label;
    private boolean isVisited;
    private final List<Vertex<T>> adjList;

    public Vertex(T label, int connectionsMaxSize) {
        this.label = label;
        adjList = new ArrayList<>(connectionsMaxSize);
    }

    public T getLabel() {
        return label;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean setConnection(Vertex<T> con) {
        return adjList.add(con);
    }

    public boolean setConnection(int index, Vertex<T> con) {
        if (index < 0 || index > adjList.size()) {
            throw new IndexOutOfBoundsException("Index is out of range"
                    + " (0 - " + adjList.size() + ")");
        } else if (index < adjList.size()) {
            adjList.set(index, con);
            return true;
        } else { //index == adjList.size()
            return adjList.add(con);
        }
    }

    public boolean hasConnections() {
        return !adjList.isEmpty();
    }

    public List<Vertex<T>> getConnections() {
        return adjList.isEmpty() ? null : adjList;
    }

    @Override
    public String toString() {
        return Objects.toString(label);
    }
}
