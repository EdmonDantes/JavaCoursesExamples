package repository;

import java.util.function.Function;

// 1
public class MemoryRepositoryByLambda<T> extends MemoryRepository<T> {

    private Function<T, Integer> getIdLambda;

    // 1.1
    public MemoryRepositoryByLambda(Function<T, Integer> getIdLambda) {
        this.getIdLambda = getIdLambda;
    }

    // 1.2
    @Override
    protected int getId(T obj) {
        return getIdLambda.apply(obj);
    }
}
