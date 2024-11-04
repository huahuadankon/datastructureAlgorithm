package com.huahuadan.datastructure.list;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author liuyichen
 * @version 1.0
 */
public class SingleList implements Iterable<Integer> {
    private Node head = null;



    /**
     * 节点类
     */
    private static class Node {
        int value; // 值
        Node next; // 下一个节点指针

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 向链表头部添加
     *
     * @param value 待添加值
     */
    public void addFirst(int value) {
        // 1. 链表为空
//        head = new Node(value, null);
        // 2. 链表非空
        head = new Node(value, head);
    }

    /**
     * 遍历链表三种
     */

    public void loop(){
        Node p = head;
        while(p != null){
            System.out.println(p.value);
            p = p.next;
        }
    }

    public void loop2(Consumer<Integer> consumer){
        Node p = head;
        while(p != null){
            consumer.accept(p.value);
            p = p.next;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Integer next() {
                int v = current.value;
                current = current.next;
                return v;
            }
        };
    }

    /**
     * 尾插法
     * @param value
     */
    public void addLast(int value) {
        Node last = findLast();
        if (last == null) { // 空链表
            addFirst(value);
            return;
        }

        last.next = new Node(value, null);
    }


    private Node findLast() {
        if (head == null) { // 空链表
            return null;
        }

        Node p;
        for (p = head; p.next != null; p = p.next) {

        }
        return p;
    }

    private Node findNode(int index){
        int i = 0;
        for (Node p = head; p != null; p = p.next,i++){
            if(i == index){
                return p;
            }
        }
        return null;
    }

    public int get(int index){
        Node p = findNode(index);
        if(p == null){
            extracted();
        }
        return p.value;
    }

    private static void extracted() {
        throw new IndexOutOfBoundsException();
    }

    /**
     * 插入节点
     * @param index
     * @param value
     */
    public void insert(int index, int value) {
        if (index == 0){
            addFirst(value);
            return;
        }
        Node prev = findNode(index-1);
        if(prev == null){
            extracted();
        }
        prev.next = new Node(value, prev.next);
    }

    public void removeFirst() {
        if(head == null){
            extracted();
            return;
        }
        head = head.next;

    }

    public void remove(int index) {
        if(index == 0){
            removeFirst();
            return;
        }
        Node prev = findNode(index - 1);
        if(prev == null || prev.next == null){
            extracted();
            return;
        }
        prev.next = prev.next.next;
    }

    public void recursion(Node current,Consumer<Integer> before
    ,Consumer<Integer> after){
        if(current == null){
            return;
        }
        before.accept(current.value);
        recursion(current.next,before,after);
        after.accept(current.value);
    }

    public static void main(String[] args) {
        SingleList list = new SingleList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.recursion(list.head,(before)->{
            System.out.println("before"+before);
        },after-> System.out.println("after"+after));
    }




}
