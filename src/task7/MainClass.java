package task7;

import task7.entity.Person;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {

    // 3
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want load or save data? (l/s)");
        String loadOrSave = scanner.nextLine();
        if (loadOrSave.equals("l")) {
            load(scanner);
        } else if (loadOrSave.equals("s")) {
            save(scanner);
        } else {
            System.out.println("Get wrong input from user. Should be 'l' or 's' but '" + loadOrSave + "'");
        }
    }

    private static void load(Scanner scanner) throws IOException {
        File file = tryGetFileFromUser(scanner, "Please type path to file for loading");

        if (file == null) {
            return;
        }

        Person person = Person.loadFrom(file);
        System.out.println("Loaded person: id = " + person.getId() + "; name = " + person.getName());
    }

    private static void save(Scanner scanner) throws IOException {
        System.out.println("Please write id for person");
        int id = scanner.nextInt();
        System.out.println("Please write person's name");
        String name = scanner.nextLine();

        Person person = new Person(id);
        person.setName(name);

        File file = tryGetFileFromUser(scanner, "Please type path to file for saving");
        if (file == null) {
            return;
        }

        Person.saveTo(file, person);
    }

    private static File tryGetFileFromUser(Scanner scanner, String msg) {
        System.out.println(msg);
        String pathToFile = scanner.nextLine();
        File file = new File(pathToFile);

        if (!file.exists()) {
            System.out.println("Can not found file by path: " + pathToFile);
            return null;
        }

        return file;
    }

}
