package task9.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 5
public abstract class MemoryRepository<T> implements Repository<T> {

    private final Map<Integer, T> storage = new HashMap<>();

    // 5.2
    @Override
    public void save(T obj) throws IOException {
        storage.put(getId(obj), obj);
    }

    // 5.3
    @Override
    public T load(int id) throws IOException {
        return storage.get(id);
    }

    // 5.4
    @Override
    public List<T> load(List<Integer> ids) throws IOException {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            list.add(load(ids.get(i)));
        }
        return list;
    }

    // 5.1
    protected abstract int getId(T obj);

}
