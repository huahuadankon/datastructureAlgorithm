package com.huahuadan.datastructure.queue;

import java.util.Iterator;

/**
 * @author liuyichen
 * @version 1.0
 */
public class LinkedListQueue<E> implements Queue<E> , Iterable<E>{

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public boolean offer(E value) {



        return false;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
