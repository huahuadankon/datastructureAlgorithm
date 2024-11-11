package com.huahuadan.datastructure.priorityqueue;

import com.huahuadan.datastructure.queue.Queue;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/11 21:05
 * @description 基于大顶堆实现优先级队列
 */
@SuppressWarnings("ALL")
public class PriorityQueue3<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size; //指针，指向数组最后一个索引加一
    public PriorityQueue3(int capacity) {
        array = new Priority[capacity];
    }

    private void swap(int i, int j) {
        Priority temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public boolean offer(E value) {
        if(isFull()){
            return false;
        }
        int child = size++;
        int parent = (child-1)/2;
        while (child>0 && value.priority() > array[parent].priority()){
            array[child] = array[parent];
            child = parent;
            parent = (child-1)/2;
        }
        array[child] = value;
        return true;
    }

    private void down(int parent) {
        int left = parent*2+1;
        int right = left+1;
        int max = parent;
        if(left<size && array[left].priority() > array[max].priority()){
            max = left;
        }
        if (right<size && array[right].priority() > array[max].priority()) {
            max = right;
        }

        if(parent!=max){
            swap(parent,max);
            down(parent);
        }

    }


    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        swap(0,size-1);
        size--;
        Priority removed = array[size];
        array[size] = null; //help GC

        down(0);
        return (E)removed;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return (E) array[0];
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
