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
    private Node<E> head = new Node<E>(null, null); //哨兵节点
    private Node<E> tail = head;
    private int size = 0;
    private int capacity = Integer.MAX_VALUE;
    {
        head.next = tail;
    }
    public LinkedListQueue(int capacity){
        this.capacity = capacity;
    }
    public LinkedListQueue() {

    }

    @Override
    public boolean offer(E value) {
        if(isFull()){
            return false;
        }
        Node<E> eNode = new Node<>(value, head);
        tail.next = eNode;
        tail = eNode;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        if(head.next == tail){
            tail = head;
        }
        Node<E> fNode = head.next;
        head.next = fNode.next;
        size--;
        return fNode.value;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        Node<E> fNode = head.next;
        return fNode.value;

    }

    @Override
    public boolean isEmpty() {
        return head==tail;
    }

    @Override
    public boolean isFull() {
        return size==capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> fNode = head.next;
            @Override
            public boolean hasNext() {
                return fNode != head;
            }


            @Override
            public E next() {
                E value = fNode.value;
                fNode = fNode.next;
                return value;
            }
        };
    }
}
