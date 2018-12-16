package ru.khusyainov.hw8;

import java.text.MessageFormat;
import java.util.Objects;

public class Person {

    private String name;
    private int id;
    private int age;

    public Person(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.age != other.age) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return MessageFormat.format("ФИО: {0}, id: {1}, возраст: {2}",
                name, id, age);
    }
}
