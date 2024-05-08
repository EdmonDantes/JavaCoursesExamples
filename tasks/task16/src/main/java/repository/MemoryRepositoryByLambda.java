package repository;

import java.util.function.Function;

public class MemoryRepositoryByLambda<T> extends MemoryRepository<T> {

    private Function<T, Integer> getIdLambda;

    public MemoryRepositoryByLambda(Function<T, Integer> getIdLambda) {
        this.getIdLambda = getIdLambda;
    }

    @Override
    protected int getId(T obj) {
        return getIdLambda.apply(obj);
    }
}
