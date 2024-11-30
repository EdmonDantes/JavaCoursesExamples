package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JsonFilesRepository<T> implements Repository<T> {

    private final ObjectMapper mapper;
    private final TypeReference<T> type;
    private final File dir;
    private Function<T, Integer> getIdLambda;

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

    @Override
    public void save(T obj) throws IOException {
        File file = new File(dir.getPath() + "/" + getIdLambda.apply(obj));
        mapper.writeValue(file, obj);
    }

    @Override
    public T load(int id) throws IOException {
        File file = new File(dir.getPath() + "/" + id);
        if (!file.exists()) {
            return null;
        }

        return mapper.readValue(file, type);
    }

    @Override
    public List<T> load(List<Integer> ids) throws IOException {
        List<T> result = new ArrayList<>(ids.size());
        for (Integer id : ids) {
            result.add(load(id));
        }
        return result;
    }

    @Override
    public List<T> loadAll() throws IOException {
        List<T> result = new ArrayList<>();
        for (File file : dir.listFiles()) {
            result.add(mapper.readValue(file, type));
        }
        return result;
    }
}
