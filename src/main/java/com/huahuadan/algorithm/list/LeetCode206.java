package com.huahuadan.algorithm.list;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/14 20:44
 * @description 反转单链表
 */
public class LeetCode206 {
    public static void main(String[] args) {

    }
    //方法一，直接new一个新链表
    public ListNode reverseList(ListNode o1) {
        ListNode n1 = null;
        ListNode p = o1;
        while (p != null) {
            n1 = new ListNode(p.val, n1);
            p = p.next;
        }
        return n1;
    }
    //方法二，递归
    public ListNode reverseList2(ListNode p) {
        if (p == null || p.next == null) { // 不足两个节点
            return p; // 最后一个节点
        }
        ListNode last = reverseList(p.next);
        p.next.next = p;
        p.next = null;
        return last;
    }

    //方法3，不断将原链表的第二个值往头部移动
    public ListNode reverseList3(ListNode o1) {
        if (o1 == null || o1.next == null) { // 不足两个节点
            return o1;
        }
        ListNode o2 = o1.next;
        ListNode n1 = o1;
        while (o2 != null) {
            o1.next = o2.next;//断开第二个节点
            o2.next = n1; //将移到头部
            n1 = o2; //更新指针
            o2 = o1.next; //更新原来o2
        }
        return n1;
    }

    //方法4，创建新链表，只不过不是new，而是利用指针的移动，不断让旧链表头部
    public ListNode reverseList4(ListNode o1) {
        if (o1 == null || o1.next == null) {
            return o1;
        }
        ListNode n1 = null;
        while (o1 != null) {
            ListNode o2 = o1.next; //记录o2
            o1.next = n1; //旧链表头部插入到新链表头部之前
            n1 = o1; //更新新链表头指针
            o1 = o2; //更新旧链表头指针
        }
        return n1;
    }

}
