package task8.repository;

import task8.entity.Person;

import java.io.IOException;

// 2
public interface PersonRepository {

    // 2.1
    void save(Person person) throws IOException;

    // 2.2
    Person load(int id) throws IOException;

}
