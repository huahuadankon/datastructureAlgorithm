package com.huahuadan.datastructure.list;

import java.util.Iterator;

/**
 * 双向链表(带哨兵)
 */
public class DoublyLinkedListSentinel implements Iterable<Integer> {
    static class Node {
        Node prev; // 上一个节点指针
        int value; // 值
        Node next; // 下一个节点指针

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node head; // 头哨兵
    private Node tail; // 尾哨兵

    public DoublyLinkedListSentinel() {
        head = new Node(null, 666, null);
        tail = new Node(null, 888, null);
        head.next = tail;
        tail.prev = head;
    }

    Node findNode(int index) {
        int i = -1;
        for (Node p = head; p!= tail;p = p.next,i++){
            if(i == index){
                return p;
            }
        }
        return null;
    }

    public void addFirst(int value) {
        Node next = head.next;
        Node first = new Node(head, value, next);
        head.next = first;
        next.prev = first;
    }

    public void removeFirst() {
        remove(0);
    }

    public void addLast(int value) {
        Node prev = tail.prev;
        Node last = new Node(prev, value, tail);
        prev.next = last;
        tail.prev = last;
    }

    public void removeLast() {
        Node removed = tail.prev;
        if(removed == head){
            throw illegalIndex(0);
        }
        removed.prev.next = tail;
        tail.prev = removed.prev;
    }

    public void insert(int index, int value) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw illegalIndex(index);
        }
        Node next = prev.next;
        Node newNode = new Node(prev, value, next);
        prev.next = newNode;
        next.prev = newNode;

    }

    public void remove(int index) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw illegalIndex(index);
        }

        Node removed = prev.next;
        if (removed == tail) {
            throw illegalIndex(index);
        }
        Node next = prev.next.next;
        prev.next = next;
        next.prev = prev;
    }



    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(
                String.format("index [%d] 不合法%n", index));
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head;
            @Override
            public boolean hasNext() {
                return p.next != tail;
            }

            @Override
            public Integer next() {
                int value = p.next.value;
                p = p.next;
                return value;
            }
        };
    }
}
