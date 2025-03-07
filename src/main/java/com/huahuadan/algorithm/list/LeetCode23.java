package com.huahuadan.algorithm.list;

import java.util.List;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/11 23:28
 * @description 合并多个有序链表，
 */
public class LeetCode23 {
    public static void main(String[] args) {

        ListNode[] lists = {
                ListNode.of(1, 4, 5),
                ListNode.of(1, 3, 4),
                ListNode.of(2, 6),
                null,
        };
        ListNode m = new LeetCode23().mergeKLists(lists);
        System.out.println(m);
    }
    //方法1，利用小顶堆实现的优先队列
    public ListNode mergeKLists1(ListNode[] lists) {
        MinHeap minHeap = new MinHeap(lists.length);
        //将链表的头节点加入minHeap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }
        //不断从minHeap移除元素，加入新的节点
        ListNode s = new ListNode(-1,null);
        ListNode t = s;
        while (!minHeap.isEmpty()) {
            ListNode min = minHeap.poll();
            t.next = min;
            t = t.next;
            if (t.next != null) {
                minHeap.offer(t.next);
            }
        }
        return s.next;
    }

    //方法2，多路递归，分而治之
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return splitKLists(lists, 0, lists.length - 1);
    }

    public ListNode splitKLists(ListNode[] lists, int i ,int j) {
        if(i==j){
            return lists[i];
        }
        int m =(i+j)>>>1;
        ListNode left = splitKLists(lists,i,m);
        ListNode right = splitKLists(lists,m+1,j);
        return mergeTwoLists(left,right);
    }

    public ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(-1, null);
        ListNode p = s;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            }else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if(p1 != null){
            p.next = p1;
        }
        if(p2 != null){
            p.next = p2;
        }
        return s.next;
    }

}
