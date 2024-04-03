package task12.factory;

public abstract class SimpleEntityFactory<T> implements EntityFactory<T> {
    private int nextId = 1;

    protected int getNextId() {
        return nextId++;
    }

}
