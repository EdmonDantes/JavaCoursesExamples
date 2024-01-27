package task9.repository;

import java.io.IOException;
import java.util.List;

// 1
public interface Repository<T> {

    // 1
    void save(T obj) throws IOException;

    // 1
    T load(int id) throws IOException;

    // 2
    List<T> load(List<Integer> ids) throws IOException;

}
