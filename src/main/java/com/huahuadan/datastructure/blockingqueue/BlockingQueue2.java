package com.huahuadan.datastructure.blockingqueue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/12 21:44
 * @description 双锁实现阻塞队列，大哥李写了一年的版本
 */
public class BlockingQueue2<E> implements BlockingQueue<E> {
    private final E[] array;
    private int head;
    private int tail;
    private AtomicInteger size = new AtomicInteger();

    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition();

    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();

    public BlockingQueue2(int capacity) {
        this.array = (E[]) new Object[capacity];
    }

    private boolean isEmpty() {
        return size.get() == 0;
    }

    private boolean isFull() {
        return size.get() == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {
        int c;//来记录阻塞队列size被操作前的大小，对唤醒队列有帮助
        tailLock.lockInterruptibly();
        try {
            while (isFull()){
                tailWaits.await();
            }
            array[tail] = e;
             c = size.getAndIncrement();//原子整数，对size的操作是原子的
            //加完前的值加一还小于队列长度，说明还有空余，则唤醒其他线程
            if(c+1<array.length){
                tailWaits.signal();
            }
            if(++tail == array.length){
                tail = 0;
            }
        } finally {
            tailLock.unlock();
        }
        //添加前阻塞队列的size为0，则取唤醒poll线程，然后让唤醒的poll线程去唤醒其他线程
        if(c == 0){
            headLock.lockInterruptibly();
            try {
                headWaits.signal();
            }finally {
                headLock.unlock();
            }
        }

    }



    @Override
    public E poll() throws InterruptedException {
        headLock.lockInterruptibly();
        E e;
        int c;
        try {
            while (isEmpty()){
                headWaits.await();
            }
             e = array[head];
            c = size.getAndDecrement();
            if(c > 1){ //size减一前的值大于1说明减完后还有剩余，唤醒其他线程
                headWaits.signal();
            }
            if(++head == array.length){
                head = 0;
            }

        } finally {
            headLock.unlock();
        }
        //在size减一前，队列满，去唤醒一个offer，再让offer线程唤醒其他offer线程
        if(c == array.length){
            tailLock.lockInterruptibly();
            try {
                tailWaits.signal();
            }finally {
                tailLock.unlock();
            }
        }
        return e;


    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue2<String> queue = new BlockingQueue2<>(3);
        queue.offer("元素1");
        queue.offer("元素2");
        queue.offer("元素3");

        new Thread(()->{
            try {
                queue.offer("元素4");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();

        new Thread(()->{
            try {
                queue.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll").start();
    }
}
