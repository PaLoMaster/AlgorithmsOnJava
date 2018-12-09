package ru.khusyainov.hw7;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> vertexes = Arrays.asList(
                "Москва",
                "Тула", "Рязань", "Калуга",
                "Липецк", "Тамбов", "Орёл",
                "Воронеж", "Саратов", "Курск"
        );
        Graph<String> graph = new Graph<>(vertexes.size());
        vertexes.forEach(graph::addVertex);
        
        graph.addEdge("Москва", "Тула");
        graph.addEdge("Москва", "Рязань");
        graph.addEdge("Москва", "Калуга");
        graph.addEdge("Тула", "Липецк");
        graph.addEdge("Рязань", "Тамбов");
        graph.addEdge("Калуга", "Орёл");
        graph.addEdge("Липецк", "Воронеж");
        graph.addEdge("Тамбов", "Саратов");
        graph.addEdge("Орёл", "Курск");
        graph.addEdge("Саратов", "Воронеж");
        graph.addEdge("Курск", "Воронеж");
        
        System.out.println(graph);
        
        System.out.println("Обход графа в ширину:");
        graphsDfsWithDescription(graph, "Москва", "Воронеж");
        System.out.println("Встречался с подобным маршрутом в результатах поиска на сайте РЖД:");
        graphsDfsWithDescription(graph, "Москва", "Саратов");
        System.out.println(":)))");
        
        System.out.println("Обход графа в глубину:");
        graphsBfsWithDescription(graph, "Москва", "Воронеж");
        graphsBfsWithDescription(graph, "Москва", "Саратов");
        graphsBfsWithDescription(graph, "Москва", "Курск");
        System.out.println("из \"Москва\" (весь список в порядке обхода):\n\t" + graph.bfs("Москва"));
    }

    private static <T> void graphsDfsWithDescription(Graph<T> graph, T label1, T label2) {
        System.out.println("из \"" + label1 + "\" в \"" + label2 + "\":\n\t"
                + graph.dfs(label1, label2));
    }

    private static <T> void graphsBfsWithDescription(Graph<T> graph, T label1, T label2) {
        System.out.println("из \"" + label1 + "\" в \"" + label2 + "\":\n\t"
                + graph.bfs(label1, label2));
    }
}
