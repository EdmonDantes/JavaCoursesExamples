package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// 12
public class JsonFilesRepository<T> implements Repository<T> {

    // 12.1
    private final ObjectMapper mapper;
    // 12.2
    private final TypeReference<T> type;
    // 12.3
    private final File dir;
    // 12.4
    private Function<T, Integer> getIdLambda;

    // 12.5
    public JsonFilesRepository(File dir, ObjectMapper mapper, TypeReference<T> type, Function<T, Integer> getIdLambda) {
        this.dir = dir;
        this.mapper = mapper;
        this.type = type;
        this.getIdLambda = getIdLambda;

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("DirectoryProductRepository required only directory");
        }
    }

    // 12.6
    @Override
    public void save(T obj) throws IOException {
        File file = new File(dir.getPath() + "/" + getIdLambda.apply(obj));
        mapper.writeValue(file, obj);
    }

    // 12.6
    @Override
    public T load(int id) throws IOException {
        File file = new File(dir.getPath() + "/" + id);
        if (!file.exists()) {
            return null;
        }

        return mapper.readValue(file, type);
    }

    // 12.6
    @Override
    public List<T> load(List<Integer> ids) throws IOException {
        List<T> result = new ArrayList<>(ids.size());
        for (Integer id : ids) {
            result.add(load(id));
        }
        return result;
    }

    // 12.6
    @Override
    public List<T> loadAll() throws IOException {
        List<T> result = new ArrayList<>();
        for (File file : dir.listFiles()) {
            result.add(mapper.readValue(file, type));
        }
        return result;
    }
}
