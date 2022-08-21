package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private Node<E> head = null;
    private int modCount = 0;
    private int size = 0;


    @Override
    public void add(E value) {
        size++;
        modCount++;
        if (head == null) {
            head = new Node<E>(value);
            return;
        }
        Node<E> currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new Node<E>(value);

    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        int count = 0;
        E result = null;
        Node<E> currentNode = head;
        while (count < size) {
            if (count == index) {
                result = currentNode.item;
                break;
            }
            currentNode = currentNode.next;
            count++;
        }
        return result;
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            SimpleLinkedList.Node<E> currentNode = null;
            SimpleLinkedList.Node<E> nextNode = head;
            private int indexCount = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return indexCount < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                currentNode = nextNode;
                nextNode = nextNode.next;
                indexCount++;
                return currentNode.item;
            }
        };
    }


}