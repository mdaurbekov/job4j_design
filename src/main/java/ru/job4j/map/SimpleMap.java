package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;


    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int i = indexFor(hash(hash));
        MapEntry<K, V> p = table[i];
        if (p == null) {
            table[i] = new MapEntry<>(hash, key, value, null);
            count++;
            result = true;
        } else if (p.key != null && p.hash == hash && p.key.equals(key)) {
            p.value = value;
        }
        if (LOAD_FACTOR <= count * 1.0f / capacity) {
            expand();
        }
        modCount++;

        return result;
    }

    private int hash(int hashCode) {
        return ((hashCode) ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                int hash = (oldTable[i].key == null) ? 0 : hash(oldTable[i].key.hashCode());
                table[indexFor(hash)] = oldTable[i];
            }

        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int hash = (key == null) ? 0 : hash(key.hashCode());
        MapEntry<K, V> first = table[indexFor(hash)];
        K k;
        if (first != null && first.hash == hash &&
                ((k = first.key) == key || (key != null && key.equals(k)))) {
            result = first.value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int hash = (key == null) ? 0 : hash(key.hashCode());
        MapEntry<K, V> first = table[indexFor(hash)];
        K k;
        if (first != null && first.hash == hash &&
                ((k = first.key) == key || (key != null && key.equals(k)))) {
            table[indexFor(hash)] = null;
            result = true;
            modCount++;
            count--;

        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        final int hash;
        final K key;
        V value;
        MapEntry<K, V> next;

        public MapEntry(int hash, K key, V value, MapEntry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}