package com.huahuadan.datastructure.deque;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/10 20:54
 * @description 双端队列接口
 */
public interface Deque<E>{
    boolean offerFirst(E e);

    boolean offerLast(E e);

    E pollFirst();

    E pollLast();

    E peekFirst();

    E peekLast();

    boolean isEmpty();

    boolean isFull();
}
