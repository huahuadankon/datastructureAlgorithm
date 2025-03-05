package com.huahuadan.algorithm.list;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2025/3/3 16:44
 * @description 两两交换链表节点，注意是节点交换不是简单的值交换
 */
public class LeetCode24 {
    public static void main(String[] args) {
        ListNode listNode = ListNode.of(1, 3, 4, 5, 7);
        System.out.println(listNode);
        System.out.println("===========");
        ListNode listNode1 = swapPairs(listNode);
        System.out.println(listNode1);

    }
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1,head);
        ListNode cur = dummy;
        ListNode n1;
        ListNode n2;
        while(cur.next!=null && cur.next.next!=null){
            n1 = cur.next;
            n2 = cur.next.next;
            n1.next = n2.next;
            n2.next = n1;
            cur.next = n2;
            cur = n1;

        }
        return dummy.next;

    }

    public ListNode swapPairsRecursive(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = head.next;        // 新头结点为第二个节点
        head.next = swapPairs(newHead.next); // 递归处理剩余链表
        newHead.next = head;                 // 反转指针
        return newHead;
    }
}
