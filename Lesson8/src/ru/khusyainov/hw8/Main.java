package ru.khusyainov.hw8;

public class Main {
    
    public static void main(String[] args) {
        int maxArrSize = 10;
        int addsStep = 3;
        int addsLimit = 21;
        HashTable<Person> personsBase = new HashTable<>(maxArrSize);
        
        for (int i = 0; i <= addsLimit; i += addsStep) {
            personsBase.add(new Person("Test name " + i, i, 20 + i / 3));
        }
        
        System.out.println(personsBase.toString());
    }
}
