package com.huahuadan.algorithm.list;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/14 21:11
 * @description 删除指定的链表元素
 */
public class LeetCode203 {

    //方法1
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0,head);

        ListNode p = dummy;
        while(p.next != null){
            if(p.next.val == val){
                p.next = p.next.next;
            }else{
                p = p.next;
            }
        }
        return dummy.next;
    }
    //方法2，递归
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return removeElements(head.next, val);
        } else {
            head.next = removeElements(head.next, val);
            return head;
        }
    }


}
