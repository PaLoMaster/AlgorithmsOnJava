package ru.khusyainov.hw5;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Возведение в степень:");
        powAndPrintResult(2, 3);
        powAndPrintResult(55, 3);
        powAndPrintResult(55, -3);
        powAndPrintResult(55, 5);
        powAndPrintResult(88, 10);

        System.out.println();

        int maxWeight = 7;
        List<KnapsackObject> toPack = Arrays.asList(new KnapsackObject[]{
            new KnapsackObjectImpl("Книга", 600, 1),
            new KnapsackObjectImpl("Бинокль", 5000, 2),
            new KnapsackObjectImpl("Аптечка", 1500, 4),
            new KnapsackObjectImpl("Ноутбук", 40000, 2),
            new KnapsackObjectImpl("Котелок", 500, 1)
        });
        KnapsackPackager pack = new KnapsackPackager(toPack, maxWeight);
        String circle = pack.circleWork();
        String recurse = pack.recurseWork();
        System.out.println("Упаковка через цикл:\n\n" + circle);
        System.out.println("Упаковка через рекурсию:\n\n" + recurse);
        System.out.println("Равенство упаковок: "
                + (circle.equals(recurse) ? "равны" : "отличаются"));
    }

    private static void powAndPrintResult(int val, int pow) {
        try {
            System.out.printf("%d в степени %d = %d\n", val, pow, pw(val, pow));
        } catch (Exception ex) {
            System.out.printf("%d в степени %d = %s\n", val, pow, ex.getMessage());
        }
    }

    private static long pw(int num, int pow) throws Exception {
        if (pow == 0) {
            return 1;
        }
        if (pow < 0) {
            throw new Exception("Возведение в отрицательную степень пока не возможно");
            // TODO: возведение в отрицательную степень: 1d / (num * pw(num, Math.abs(++pow)))
        }
        // TODO: возведение в дробную степень
        long rez = num * pw(num, --pow);
        if (rez >= num) {
            return rez;
        } else {
            throw new Exception("Результат вышел за граничные значения Long");
        }
    }
}
