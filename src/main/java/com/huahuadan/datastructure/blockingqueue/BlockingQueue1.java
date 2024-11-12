package com.huahuadan.datastructure.blockingqueue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/12 20:36
 * @description 单锁实现阻塞队列
 */
@SuppressWarnings("unchecked")
public class BlockingQueue1<E> implements BlockingQueue<E> {
    private E[] array = null;
    private int size = 0;
    int head = 0;
    int tail = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition tailWaits = lock.newCondition();//队列满
    Condition headWaits = lock.newCondition();//队列空
    public BlockingQueue1(int capacity) {
        array = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isFull()) {
                tailWaits.await();
            }
            array[tail] = e;
            if(++tail == array.length){
                tail = 0;
            }
            size++;
            headWaits.signal();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        lock.lockInterruptibly();
        long t = TimeUnit.MILLISECONDS.toNanos(timeout);
        try {
            while (isFull()) {
                if(t<=0){
                    return false;
                }
                t = tailWaits.awaitNanos(t);//会返回剩余时间
            }
            array[tail] = e;
            if(++tail == array.length){
                tail = 0;
            }
            size++;
            headWaits.signal();
            return true;
        }finally {
            lock.unlock();
        }

    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            E e = array[head];
            array[head] = null;//help gc
            if(++head == array.length){
                head = 0;
            }
            size--;
            tailWaits.signal();
            return e;
        }finally {
            lock.unlock();
        }
    }
    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue1<String> queue = new BlockingQueue1<>(3);
        queue.offer("任务1");

        new Thread(()->{
            try {
                queue.offer("任务2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();

        new Thread(()->{
            try {
                System.out.println(queue.poll());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll").start();
    }


}
