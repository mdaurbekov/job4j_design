package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size] = value;
        size++;
        modCount++;

    }

    @Override
    public T set(int index, T newValue) {
        T t = get(index);
        container[index] = newValue;
        return t;

    }

    @Override
    public T remove(int index) {
        T t = get(index);
        System.arraycopy(container, index + 1, container, index, size - 2);
        size--;
        container[size] = null;
        modCount++;
        return t;

    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        int arrLength = container.length == 0 ? 1 : container.length;
        container = Arrays.copyOf(container, arrLength * 2);

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private int count = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }

        };
    }
}