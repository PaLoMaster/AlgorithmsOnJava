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
        testDeque();
    }

    public static void testStack() {
        int size = 5;
        Stack<Float> stack = new Stack<>(size);
        float i = 1f;
        System.out.print("\tСтек. Push + Peek.\nВведено в стек: ");
        for (;;) {
            if (!stack.isFull()) {
                stack.push(i++);
                System.out.print(stack.peek() + " ");
            } else {
                System.out.println("\nНе введено в стек: " + i + " (стек полон)");
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

    public static void testStackAndBrackets() {
        List<String> br = Arrays.asList(
                "()", "([])()", "{}()", "([{}])", //  правильные
                ")(", "())({)", "(", "])})", "([(])"); //  не правильные
        br.forEach(str -> System.out.println(new Brackets(str).check() + "\n"));
    }

    private static void testQueue() {
        int size = 5;
        Queue<String> q = new QueueImpl<>(size);
        int i = 1;
        System.out.print("\tОчередь. Insert + Peek.\nВведено в очередь: ");
        for (;;) {
            if (!q.isFull()) {
                q.insert(Integer.toString(i));
                System.out.print(i++ + " ");
            } else {
                System.out.println("\nНе введено в очередь: " + i + " (очередь заполнена)");
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
        System.out.print("\tПриоритетная очередь. Insert + Peek.\nВведено в очередь: ");
        for (;;) {
            if (!pq.isFull()) {
                pq.insert(i);
                System.out.print(i-- + " ");
            } else {
                System.out.println("\nНе введено в очередь: " + i + " (очередь заполнена)");
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

    private static void testDeque() {
        int size = 5;
        StringBuilder[] sb = new StringBuilder[2];
        sb[0] = new StringBuilder("\tОчередь.\n");
        sb[1] = new StringBuilder("\tДек в качестве очереди.\n");
        Queue<String> q = new QueueImpl<>(size);
        Deque<String> deqQ = new Deque<>(size);
        int i = 1;
        for (;;) {
            if (!q.isFull()) {
                q.insert(Integer.toString(i));
            }
            if (!deqQ.isFull()) {
                deqQ.insertRight(Integer.toString(i++));
            }
            if (q.isFull() && deqQ.isFull()) {
                break;
            }
        }
        while (!q.isEmpty() && !deqQ.isEmpty()) {
            if (!q.isEmpty()) {
                sb[0].append(q.remove() + " ");
            }
            if (!deqQ.isEmpty()) {
                sb[1].append(deqQ.removeLeft() + " ");
            }
            if (i < size * 2) {
                if (!q.isFull()) {
                    q.insert(Integer.toString(i));
                }
                if (!deqQ.isFull()) {
                    deqQ.insertRight(Integer.toString(i++));
                }
            }
        }
        System.out.println(sb[0] + "\n" + sb[1] + "\n");
    }
}
