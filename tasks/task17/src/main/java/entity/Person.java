package entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    // 6.1
    @JsonCreator
    public Person(@JsonProperty("id") int id) {
        this.id = id;
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static void saveTo(File file, Person person) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            try (PrintWriter writer = new PrintWriter(outputStream)) {
                writer.println(person.id);
                writer.println(person.name);
            }
        }
    }

    public static Person loadFrom(File file) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            try (Scanner scanner = new Scanner(inputStream)) {
                Person result = new Person(Integer.parseInt(scanner.nextLine()));
                result.name = scanner.nextLine();
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
