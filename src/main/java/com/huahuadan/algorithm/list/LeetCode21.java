package com.huahuadan.algorithm.list;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/11 22:47
 * @description 合并两个有序链表
 */
public class LeetCode21 {
    // 方法1
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

    // 方法2
    public ListNode mergeTwoLists2(ListNode p1, ListNode p2) {
        if(p1 == null){
            return p2;
        }
        if(p2 == null){
            return p1;
        }
        if(p1.val < p2.val){
            p1.next = mergeTwoLists2(p1.next, p2);
            return p1;
        }else{
            p2.next = mergeTwoLists2(p1, p2.next);
            return p2;
        }
    }

    public static void main(String[] args) {
        ListNode p1 = ListNode.of(1, 3, 5, 7, 8);
        ListNode p2 = ListNode.of(2, 4, 6, 8);
        System.out.println(p1);
        System.out.println(p2);
        LeetCode21 solution = new LeetCode21();
        ListNode result = solution.mergeTwoLists2(p1, p2);
        System.out.println(result);

    }

}
