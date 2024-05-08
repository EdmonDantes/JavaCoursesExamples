package repository;

import entity.Person;

import java.io.File;
import java.io.IOException;

// 3
public class DirectoryPersonRepository implements PersonRepository {

    private final File dir;

    // 3.1
    public DirectoryPersonRepository(File dir) {
        this.dir = dir;

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("DirectoryPersonRepository required only directory");
        }
    }

    // 3.2
    @Override
    public void save(Person person) throws IOException {
        File file = new File(dir.getPath() + "/" + person.getId());
        Person.saveTo(file, person);
    }

    // 3.2
    @Override
    public Person load(int id) throws IOException {
        File file = new File(dir.getPath() + "/" + id);
        return Person.loadFrom(file);
    }
}
