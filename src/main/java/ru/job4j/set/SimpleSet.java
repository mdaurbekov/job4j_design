package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean rezult = !contains(value);
        if (rezult) {
            set.add(value);
        }
        return rezult;
    }

    @Override
    public boolean contains(T value) {
        boolean rezult = false;
        for (T t : set) {
            if (Objects.equals(value, t)) {
                rezult = true;
            }
        }
        return rezult;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}