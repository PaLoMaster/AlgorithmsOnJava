package ru.khusyainov.hw4;

public class Main {

    public static void main(String[] args) {
        testLinkedList();
    }

    private static void testLinkedList() {
        LinkedList<Character> lL = new SimpleLinkedList<>();
        String str = "tsiLdekniL  яицазилаеР";
        for (Character ch : str.toCharArray()) {
            lL.add(ch);
        }
        System.out.println("Удаление одного пробела (из двух): " + 
                (lL.remove(' ') ? "успешно" : "неудачно"));
        System.out.println("Удаление отсутствующей 'ё': " + 
                (lL.remove('ё') ? "успешно" : "неудачно"));
        while (!lL.isEmpty()) {
            System.out.print(lL.remove());
        }
        System.out.println("\n");
    }
}
