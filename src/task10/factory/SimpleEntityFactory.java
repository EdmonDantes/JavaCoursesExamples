package task10.factory;

// 7
public abstract class SimpleEntityFactory<T> implements EntityFactory<T> {

    // 7.1
    private int nextId = 1;

    // 7.2
    protected int getNextId() {
        return nextId++;
    }

}
