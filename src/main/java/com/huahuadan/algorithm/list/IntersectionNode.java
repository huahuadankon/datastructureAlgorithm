package com.huahuadan.algorithm.list;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2025/3/3 18:09
 * @description 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 */
public class IntersectionNode {
    //方法一
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int alen = 0;
        int blen = 0;
        ListNode a = headA;
        ListNode b = headB;
        while(headA!=null){
            headA = headA.next;
            alen++;
        }
        while(headB!=null){
            headB = headB.next;
            blen++;
        }
        if(alen>=blen){
            for(int i = 0 ; i<alen-blen; i++){
                a = a.next;
            }

        }else{
            for(int i = 0 ; i<blen-alen; i++){
                b = b.next;
            }
        }
        while(a!=null && b!=null){
            if(a == b){
                return a;
            }
            a = a.next;
            b = b.next;
        }
        return null;
    }

    //方法2

}
