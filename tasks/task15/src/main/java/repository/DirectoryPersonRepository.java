package repository;

import entity.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryPersonRepository implements Repository<Person> {

    private final File dir;

    public DirectoryPersonRepository(File dir) {
        this.dir = dir;

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("DirectoryPersonRepository required only directory");
        }
    }

    @Override
    public void save(Person obj) throws IOException {
        File file = new File(dir.getPath() + "/" + obj.getId());
        Person.saveTo(file, obj);
    }

    @Override
    public Person load(int id) throws IOException {
        File file = new File(dir.getPath() + "/" + id);
        return Person.loadFrom(file);
    }

    @Override
    public List<Person> load(List<Integer> ids) throws IOException {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            list.add(load(ids.get(i)));
        }
        return list;
    }

    // 9
    @Override
    public List<Person> loadAll() throws IOException {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }

        List<Person> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(Person.loadFrom(file));
        }
        return list;
    }
}
