package com.huahuadan.datastructure.deque;

import java.util.Iterator;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/10 21:33
 * @description 基于循环数组实现双端队列
 */
public class ArrayDeque1<E> implements Deque<E>,Iterable<E>{
    /*
        h - head
        t - tail

        h
            t
        0   1   2   3
        a

        offerLast(a)    先添加元素 tail++
        offerLast(b)
        offerFirst(c)   先 head-- 再添加元素

        pollFirst()     先获取要移除的值 head++
        pollLast()      先 tail-- 再获取要移除的值

        head == tail 空
        head ~ tail == 数组长度-1 满
     */
    E[] array;
    int head;
    int tail;

    @SuppressWarnings("all")
    public ArrayDeque1(int capacity) {
        array = (E[]) new Object[capacity + 1];
    }
    /*
                h
        t
        0   1   2   3
                a   b
     */
    static int inc(int i, int length) {
        if (i + 1 >= length) {
            return 0;
        }
        return i + 1;
    }

    /*
                    h
            t
        0   1   2   3
        a           b
     */
    static int dec(int i, int length) {
        if (i - 1 < 0) {
            return length - 1;
        }
        return i - 1;
    }


    @Override
    public boolean offerFirst(E e) {
        if(isFull()){
            return false;
        }
        head = dec(head, array.length);
        array[head] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if(isFull()){
            return false;
        }
        array[tail] = e;
        tail = inc(tail, array.length);
        return true;
    }

    @Override
    public E pollFirst() {
        if(isEmpty()){
            return null;
        }
        E e = array[head];
        array[head] = null;
        head = inc(head, array.length);
        return e;
    }

    @Override
    public E pollLast() {
        if(isEmpty()){
            return null;
        }
        tail = dec(tail, array.length);
        E e = array[tail];
        array[tail] = null;
        return e;
    }

    @Override
    public E peekFirst() {
        if(isEmpty()){
            return null;
        }
        return array[head];

    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return array[dec(tail, array.length)];
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public boolean isFull() {
        if(tail > head){
            return tail - head == array.length -1;
        } else if (head > tail) {
            return head - tail == 1;
        }else {
            return false;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E e = array[p];
                p = inc(p, array.length);
                return e;
            }
        };
    }
}
