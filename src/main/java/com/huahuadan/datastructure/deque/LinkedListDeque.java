package com.huahuadan.datastructure.deque;

import java.util.Iterator;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/10 20:55
 * @description 双向循环链表实现
 */
public class LinkedListDeque<E> implements Deque<E>, Iterable<E> {
    static class Node<E> {
        Node<E> prev;
        E value;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    int capacity;
    int size;
    Node<E> sentinel = new Node<>(null, null, null);

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    //a added b
    @Override
    public boolean offerFirst(E e) {
        if(isFull()){
            return false;
        }
        Node<E> a = sentinel;
        Node<E> b = sentinel.next;
        Node<E> added = new Node<>(a,e,b);
        sentinel.next = added;
        b.prev = added;
        size++;
        return true;
    }

    // a added b
    @Override
    public boolean offerLast(E e) {
        if(isFull()){
            return false;
        }
        Node<E> a = sentinel.prev;
        Node<E> b = sentinel;
        Node<E> added = new Node<>(a, e, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    // a removed b
    @Override
    public E pollFirst() {
        if(isEmpty()){
            return null;
        }
        Node<E> a = sentinel;
        Node<E> removed = sentinel.next;
        Node<E> b = removed.next;
        a.next = b;
        b.prev = a;
        size--;
        return removed.value;
    }

    // a removed b
    @Override
    public E pollLast() {
        if(isEmpty()){
            return null;
        }
        Node<E> b = sentinel;
        Node<E> removed = sentinel.prev;
        Node<E> a = removed.prev;
        a.next = b;
        b.prev = a;
        size--;
        return removed.value;
    }

    @Override
    public E peekFirst() {
        if(isEmpty()){
            return null;
        }

        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if(isEmpty()){
            return null;
        }
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() { //迭代器每一次返回的是当前节点
        return new Iterator<E>() {
            Node<E> p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public E next() {
                E val = p.value;
                p = p.next;
                return val;
            }
        };
    }
}
