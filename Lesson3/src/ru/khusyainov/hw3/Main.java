package ru.khusyainov.hw3;

import java.util.List;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        testStack();
        testStackAndBrackets();
        testQueue();
        testPriorityQueue();
        testDequeAndStringReverse();
    }

    public static void testStackAndBrackets() {
        List<String> br = Arrays.asList(
                "()", "([])()", "{}()", "([{}])", //  правильные
                ")(", "())({)", "(", "])})", "([(])"); //  не правильные
        br.forEach(str -> System.out.println(new Brackets(str).check() + "\n"));
    }

    public static void testStack() {
        int size = 5;
        Stack<Float> stack = new Stack<>(size);
        float i = 1f;
        System.out.println("\tСтек. Push + Peek.");
        for (;;) {
            if (!stack.isFull()) {
                stack.push(i++);
                System.out.println("Введено в стек: " + stack.peek());
            } else {
                System.out.println("Не введено в стек: " + i + " (стек полон)");
                break;
            }
        }
        System.out.println("\tСтек. Pop.");
        while (!stack.isEmpty()) {
            System.out.println("Выведено из стека: " + stack.pop()
                    + " (осталось " + stack.size() + ")");
            if (i < size * 2) {
                stack.push(i++);
                System.out.println("Введено в стек: " + stack.peek());
            }
        }
        System.out.println();
    }

    private static void testQueue() {
        int size = 5;
        Queue<String> q = new QueueImpl<>(size);
        int i = 1;
        System.out.println("\tОчередь. Insert + Peek.");
        for (;;) {
            if (!q.isFull()) {
                q.insert(Integer.toString(i));
                System.out.println("Введено в очередь: " + i++);
            } else {
                System.out.println("Не введено в очередь: " + i + " (очередь заполнена)");
                System.out.println("Первый элемент очереди: " + q.peek());
                break;
            }
        }
        System.out.println("\tОчередь. Remove.");
        while (!q.isEmpty()) {
            System.out.println("Выведено из очереди: " + q.remove()
                    + " (осталось " + q.size() + ")");
            if (i < size * 2) {
                q.insert(Integer.toString(i));
                System.out.println("Введено в очередь: " + i++);
            }
        }
        System.out.println();
    }

    private static void testPriorityQueue() {
        int size = 5;
        Queue<Integer> pq = new PriorityQueue<>(size);
        int i = size;
        System.out.println("\tПриоритетная очередь. Insert + Peek.");
        for (;;) {
            if (!pq.isFull()) {
                pq.insert(i);
                System.out.println("Введено в очередь: " + i--);
            } else {
                System.out.println("Не введено в очередь: " + i + " (очередь заполнена)");
                System.out.println("Первый элемент очереди: " + pq.peek());
                break;
            }
        }
        System.out.println("\tПриоритетная очередь. Remove.");
        while (!pq.isEmpty()) {
            System.out.println("Выведено из очереди: " + pq.remove()
                    + " (осталось " + pq.size() + ")");
            if (i < size) {
                pq.insert(i);
                System.out.println("Введено в очередь: " + i++);
            }
        }
        System.out.println();
    }

    private static void testDequeAndStringReverse() {
        String task = "2. Создать программу, которая переворачивает вводимые строки (читает справа налево).";
        int size = task.length();
        Stack<Character> stack = new Stack<>(size);
        Deque<Character> deqS = new Deque<>(size);
        int i = 0;
        StringBuilder[] sb = new StringBuilder[2];
        sb[0] = new StringBuilder("\tСтек.\n");
        sb[1] = new StringBuilder("\tДек в качестве стека.\n");
        for (;;) {
            if (!stack.isFull()) {
                stack.push(task.charAt(i));
            }
            if (!deqS.isFull()) {
                deqS.insertRight(task.charAt(i++));
            }
            if (stack.isFull() && deqS.isFull()) {
                break;
            }
        }
        while (!stack.isEmpty()) {
            sb[0].append(stack.pop());
        }
        while (!deqS.isEmpty()) {
            sb[1].append(deqS.removeRight());
        }
        System.out.println(sb[0] + "\n" + sb[1] + "\n");
    }
}
