package task9.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Person {

    private final int id;

    private String name;

    private int age;

    public Person(int id) {
        this.id = id;
    }

    public static void saveTo(File file, Person person) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            try (PrintWriter writer = new PrintWriter(outputStream)) {
                writer.println(person.id);
                writer.println(person.name);
                writer.println(person.age);
            }
        }
    }

    public static Person loadFrom(File file) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            try (Scanner scanner = new Scanner(inputStream)) {
                Person result = new Person(Integer.parseInt(scanner.nextLine()));
                result.name = scanner.nextLine();
                result.age = Integer.parseInt(scanner.nextLine());
                return result;
            }
        }
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
}
