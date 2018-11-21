package ru.khusyainov.hw3;

import java.util.List;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        testStack();
        testStackAndBrackets();
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
}
