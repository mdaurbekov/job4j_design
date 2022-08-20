package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        String id = model.getId();
        T t = storage.get(id);
        if (t == null) {
            storage.put(id, model);
        }

    }

    @Override
    public boolean replace(String id, T model) {
        T oldModel = storage.get(id);
        return storage.replace(id, oldModel, model);
    }

    @Override
    public boolean delete(String id) {
        T model = storage.get(id);
        return storage.remove(id, model);
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}