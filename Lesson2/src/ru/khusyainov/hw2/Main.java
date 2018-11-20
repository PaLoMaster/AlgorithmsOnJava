package ru.khusyainov.hw2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        int size = 20_000;
        MyArray<Integer> arr1 = new MyArray<>(size);
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr1.addElement(rand.nextInt(size));
        }
        MyArray<Integer> arr2 = new MyArray<>(arr1);
        MyArray<Integer> arr3 = new MyArray<>(arr1);

        System.out.println("Исходный массив:\n" + arr1);
        System.out.println("(" + arr1.getSize() + " элементов)");

        System.out.println("\nМассив 1 "
                + (arr1.isSorted() ? "" : "не ") + "отсортирован");
        System.out.print("Пузырьковая сортировка ");
        
        long startOfSort = System.nanoTime();
        arr1.sortBubble();
        long endOfSort = System.nanoTime();
        
        System.out.println("выполнена за " + TimeUnit.NANOSECONDS
                .toMillis(endOfSort - startOfSort) + " мсек.");
        System.out.println("Массив 1 "
                + (arr1.isSorted() ? "" : "не ") + "отсортирован");

        
        System.out.println("\nМассив 2 "
                + (arr2.isSorted() ? "" : "не ") + "отсортирован");
        System.out.print("Cортировка методом выбора ");
        
        startOfSort = System.nanoTime();
        arr2.sortSelect();
        endOfSort = System.nanoTime();
        
        System.out.println("выполнена за " + TimeUnit.NANOSECONDS
                .toMillis(endOfSort - startOfSort) + " мсек.");
        System.out.println("Массив 2 "
                + (arr2.isSorted() ? "" : "не ") + "отсортирован");

        
        System.out.println("\nМассив 3 "
                + (arr3.isSorted() ? "" : "не ") + "отсортирован");
        System.out.print("Cортировка методом вставки ");
        
        startOfSort = System.nanoTime();
        arr3.sortInsert();
        endOfSort = System.nanoTime();
        
        System.out.println("выполнена за " + TimeUnit.NANOSECONDS
                .toMillis(endOfSort - startOfSort) + " мсек.");
        System.out.println("Массив 3 "
                + (arr3.isSorted() ? "" : "не ") + "отсортирован");
    }
}
