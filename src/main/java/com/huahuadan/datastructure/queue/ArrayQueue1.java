package com.huahuadan.datastructure.queue;

import java.util.Iterator;

/**
 * @author liuyichen
 * @version 1.0
 *
 */
@SuppressWarnings("ALL")
public class ArrayQueue1<E> implements Queue<E>,Iterable<E> {
    private E[] array;
    private int front;
    private int rear;
    private int size;
    public ArrayQueue1(int size) {
        int capacity = size+1;//容量加一是为了更好的区分满与空的判断。当然也可以利用size与capacity的大小进行判断满满与空
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if(isFull()){
            return false;
        }
        array[rear] = value;
        rear = (rear+1)%array.length;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        E value = array[front];
        front = (front+1)%array.length;
        size--;
        return value;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        E value = array[front];
        return value;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public boolean isFull() {
        return (rear+1)%array.length == front;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = front;
            @Override
            public boolean hasNext() {
                return p != rear;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p+1)%array.length;
                return value;
            }
        };
    }
}
