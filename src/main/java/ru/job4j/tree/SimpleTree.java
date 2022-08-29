package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(parent).isPresent()) {
            Node<E> n = findBy(parent).get();
            if (findBy(child).isEmpty()) {
                rsl = n.children.add(new Node<>(child));
            }
        }

        return rsl;
    }

    public boolean isBinary() {
        return false;
    }

    //    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
//        return null;
//    }
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}