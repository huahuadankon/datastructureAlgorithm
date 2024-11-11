package com.huahuadan.datastructure.priorityqueue;



import com.huahuadan.datastructure.queue.Queue;

import java.util.Arrays;

/**
 * 基于<b>大顶堆</b>实现
 * @param <E> 队列中元素类型, 必须实现 Priority 接口
 */
@SuppressWarnings("all")
public class PriorityQueue4<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue4(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    @Override
    public boolean offer(E offered) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && offered.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = offered;
        return true;
    }


    private void swap(int i, int j) {
        Priority t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, --size);
        Priority e = array[size];
        array[size] = null; // help GC

        down(0, array[0]);

        return (E) e;
    }

    // 与 PriorityQueue3 不同在于没有用 swap 实现下潜
    //延迟赋值的过程：
    //array[parent] = array[max]; 的操作只是将 max 位置的值“复制”到 parent 位置，而 top 元素还未真正放到堆中。
    // 这样，top 仍然“悬而未决”，最终会找到一个合适的位置将它放下去。
    //
    //当递归到堆的正确位置（max 不再是 parent，或者没有符合条件的子节点）时，
    // 将 top 赋给 array[parent]。这一点在最后的 else 块中完成：array[parent] = top;。
    //减少交换次数
    void down(int parent, Priority top) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent;
        // 两个孩子里找个大的
        if (left < size) {
            max = left;
            if (right < size && array[right].priority() > array[left].priority()) {
                max = right;
            }
        }
        if (max != parent && array[max].priority() > top.priority()) {
            array[parent] = array[max];
            down(max, top);
        } else { // 没孩子, 或孩子没 top 大
            array[parent] = top;
        }
    }

    @Override
    public E peek() {
        if (isEmpty()) {
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
