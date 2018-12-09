package ru.khusyainov.hw7;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph<T> {

    private final List<Vertex<T>> vertexes;
    private final int maxSize;

    private int size;

    public Graph(int maxSize) {
        this.maxSize = maxSize;
        this.vertexes = new ArrayList<>(maxSize);
        this.size = 0;
    }

    public void addVertex(T label) {
        if (size + 1 > maxSize) {
            throw new IndexOutOfBoundsException("Limit exceeded (" + maxSize + " vertexes)");
        }
        vertexes.add(new Vertex<>(label, maxSize));
        size++;
    }

    private int indexOf(T label) {
        for (int i = 0; i < vertexes.size(); i++) {
            if (vertexes.get(i).getLabel().equals(label)) {
                return i;
            }
        }
        return -1;
    }

    public boolean addEdge(T labelA, T labelB) {
        int indexA = indexOf(labelA);
        int indexB = indexOf(labelB);

        if (indexA == -1 || indexB == -1) {
            return false;
        }

        addEdge(indexA, indexB);
        return true;
    }

    private void addEdge(int indexA, int indexB) {
        vertexes.get(indexA).setConnection(vertexes.get(indexB));
        vertexes.get(indexB).setConnection(vertexes.get(indexA));
    }

    private T visit(Collection<Vertex<T>> collection, Vertex<T> vertex) {
        vertex.setVisited(true);
        collection.add(vertex);
        return vertex.getLabel();
    }

    private Vertex<T> getUnvisitedLinkedVertex(Vertex<T> vertex) {
        for (Vertex<T> vert : vertex.getConnections()) {
            if (!vert.isVisited()) {
                return vert;
            }
        }
        return null;
    }

    private void resetVertexStates() {
        vertexes.forEach(vert -> vert.setVisited(false));
    }

    /**
     * Depth-first search from {@code fromLabel} over all vertexes.
     *
     * @param fromLabel
     * @return list of visited labels (inclusive) or {@code null} if graph
     * hasn't such label as {@code fromLabel}.
     */
    public List<T> dfs(T fromLabel) {
        return dfs(fromLabel, null);
    }

    /**
     * Depth-first search from {@code startLabel} to {@code toLabel}.
     *
     * @param fromLabel
     * @param toLabel
     * @return list of visited labels (inclusive) or {@code null} if graph
     * hasn't such label as {@code fromLabel}.
     */
    public List<T> dfs(T fromLabel, T toLabel) {
        int startIndex = indexOf(fromLabel);
        if (startIndex == -1) {
            return null;
        }

        Vertex<T> vertex = vertexes.get(startIndex);
        List<T> result = new ArrayList<>(maxSize);
        Stack<Vertex<T>> stack = new Stack<>();

        result.add(visit(stack, vertex));

        while (!stack.isEmpty()) {
            vertex = getUnvisitedLinkedVertex(stack.peek());
            if (vertex != null) {
                result.add(visit(stack, vertex));
                if (vertex.getLabel().equals(toLabel)) {
                    break;
                }
            } else {
                if (toLabel != null) {
                    result.remove(result.size() - 1);
                }
                stack.pop();
            }
        }

        resetVertexStates();
        return result;
    }

    /**
     * Breadth-first search from {@code fromLabel} over all vertexes.
     *
     * @param fromLabel
     * @return list of visited labels (inclusive, in order of visiting) or
     * {@code null} if graph hasn't such label as {@code fromLabel}.
     */
    public List<T> bfs(T fromLabel) {
        return bfs(fromLabel, null);
    }

    /**
     * Breadth-first search of shortest route from {@code startLabel} to
     * {@code toLabel}. If {@code toLabel == null} and vertexes hasn't such
     * label, then result same as {@link #bfs(java.lang.Object)}.
     *
     * @param fromLabel
     * @param toLabel
     * @return shortest list of visited labels (inclusive) or {@code null} if
     * graph hasn't such label as {@code fromLabel}.
     */
    public List<T> bfs(T fromLabel, T toLabel) {
        int startIndex = indexOf(fromLabel);
        if (startIndex == -1) {
            return null;
        }

        Vertex<T> vertex = vertexes.get(startIndex);
        List<T> visited = new ArrayList<>(maxSize);
        List<List<T>> routes = new ArrayList<>(maxSize);
        Queue<Vertex<T>> queue = new ArrayDeque<>();

        visited.add(visit(queue, vertex));
        int waveNumber = 0;
        routes.add(new ArrayList<>(maxSize));
        routes.get(waveNumber).add(vertex.getLabel());

        while (!queue.isEmpty()) {
            vertex = getUnvisitedLinkedVertex(queue.peek());
            if (vertex != null) {
                int cur = routes.size();
                routes.add(new ArrayList<>(maxSize));
                routes.get(cur).addAll(routes.get(waveNumber));
                routes.get(cur).add(vertex.getLabel());
                visited.add(visit(queue, vertex));
                if (vertex.getLabel().equals(toLabel)) {
                    waveNumber = cur;
                    break;
                }
            } else {
                queue.remove();
                waveNumber++;
            }
        }

        resetVertexStates();
        return toLabel == null ? visited : routes.get(waveNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graphs adjacency list\n");
        vertexes.forEach(vert -> {
            sb.append(vert.getLabel() + ": ");
            if (!vert.hasConnections()) {
                sb.append("--");
            } else {
                vert.getConnections().forEach(v -> sb.append(v + ", "));
                sb.setLength(sb.length() - 2);
            }
            sb.append("\n");
        });
        return sb.toString();
    }
}
