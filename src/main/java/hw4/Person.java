package hw4;

import javax.persistence.*;
import java.util.Scanner;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Person create(Scanner scanner) {
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Введите возраст:");

        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите числовое значение для возраста:");
            scanner.next();
        }

        int age = scanner.nextInt();
        return new Person(name, age);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
