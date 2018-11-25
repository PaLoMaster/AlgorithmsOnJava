package ru.khusyainov.hw4;

import java.util.Iterator;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {
        testLinkedList();
        testTwoWayLinkedList();
        testTwoWayLinkedListIterator();// + stack && queue
    }

    private static void testLinkedList() {
        LinkedList<Character> lL = new SimpleLinkedList<>();
        String str = "tsiLdekniL  яицазилаеР";
        for (Character ch : str.toCharArray()) {
            lL.add(ch);
        }
        System.out.println("Содержимое LinkedList:\n" + lL.toString());
        System.out.println("Удаление одного пробела (из двух): "
                + (lL.remove(' ') ? "успешно" : "неудачно"));
        System.out.println("Удаление отсутствующей 'ё': "
                + (lL.remove('ё') ? "успешно" : "неудачно"));
        while (!lL.isEmpty()) {
            System.out.print(lL.remove());
        }
        System.out.println("\n");
    }

    private static void testTwoWayLinkedList() {
        TwoWayLinkedList<Character> tWLL = new TwoWayLinkedList<>();
        String str = "й)elpmiS как_итчоп yaWowT( tsiLdekniL _ яицазилаеРй";
        for (char ch : str.toCharArray()) {
            tWLL.add(ch);
        }
        System.out.println("Содержимое TwoWayLinkedList:\n" + tWLL.toString());
        System.out.println("Удаление одной 'й': " + rezultOfRemove(tWLL, 'й'));
        System.out.println("Удаление другой 'й': " + rezultOfRemove(tWLL, 'й'));
        System.out.println("Удаление отсутствующей 'й': " + rezultOfRemove(tWLL, 'й'));
        System.out.println("Удаление одного пробела (из двух): " + rezultOfRemove(tWLL, ' '));
        while (!tWLL.isEmpty()) {
            System.out.print(tWLL.remove());
        }
        System.out.println("\n");
    }

    private static <T> String rezultOfRemove(TwoWayLinkedList<T> obj, T remove) {
        return obj.remove(remove) ? "успешно" : "неудачно";
    }

    private static void testTwoWayLinkedListIterator() {
        TwoWayLinkedList<Character> tWLL = new TwoWayLinkedList<>();
        String str = "й)rotareti yaWowT( tsiLdekniL _ яицазилаеРй";
        for (char ch : str.toCharArray()) {
            tWLL.add(ch);
        }
        
        System.out.println("Итератор для (в порядке от последнего):\n" + tWLL.toStringFromLastAdded() + 
                "\nСодержимое TwoWayLinkedList (через for-each - в порядке добавления):");
        for (char ch : tWLL) {
            System.out.print(ch);
        }
        System.out.println("\nТихое удаление одного ' ' (удаление двух 'й' и '_' - при выводе как из стека).");
        tWLL.remove(' ');

        System.out.println("\nВывод из TwoWayLinkedList как из стека:");
        ListIterator<Character> queue = tWLL.listIteratorFromLast();
        while (queue.hasPrevious()) {
            char ch = queue.previous();
            if (ch == 'й' || ch == '_') {
                queue.remove();
            } else {
                System.out.print(ch);
            }
        }

        System.out.println("\n\nВывод из TwoWayLinkedList как из очереди:");
        Iterator<Character> stack = tWLL.iterator();
        while (stack.hasNext()) {
            System.out.print(stack.next());
        }
        System.out.println("\n");
    }
}
