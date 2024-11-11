package com.huahuadan.datastructure.priorityqueue;

import com.huahuadan.datastructure.queue.Queue;

import java.util.PriorityQueue;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/11 20:23
 * @description 基于无序数组实现优先级队列
 */
public class PriorityQueue1<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size;
    public PriorityQueue1(int capacity) {
        array = new Priority[capacity];
    }


    @Override
    public boolean offer(E value) {
        if(isFull()){
            return false;
        }
        array[size++] = value;
        return true;
    }
    private int selectMax(){
        int max = 0;
        for(int i = 1; i < size; i++){
            if(array[max].priority()<array[i].priority()){
                max = i;
            }
        }
        return max;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        int index = selectMax();
        Priority priority = array[index];
        System.arraycopy(array, index+1, array, index, size-index-1);

        return (E)priority;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        int index = selectMax();
        Priority priority = array[index];
        return (E)priority;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
