package ru.job4j.list;


import java.util.NoSuchElementException;

public class SimpleStack<T> {
    Node<T> head;

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
         linked.addFirst(value);
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}