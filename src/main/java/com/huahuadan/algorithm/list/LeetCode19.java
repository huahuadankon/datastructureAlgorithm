package com.huahuadan.algorithm.list;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/15 18:43
 * @description 删除链表的倒数第n个节点
 */
public class LeetCode19 {

    //快慢指针的算法
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0,head);
        ListNode fast = dummy;
        ListNode slow = dummy;
        for(int i = 0 ; i<=n ;i++){
            fast = fast.next;
        }
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
    //方法二递归
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0,head);
        recursion(dummy,n);
        return dummy.next;
    }
    public int recursion(ListNode p, int n) {
        if( p == null){
            return 0;
        }
        int nth = recursion(p.next, n);
        if(nth == n){
            p.next = p.next.next;
        }
        return nth+1;

    }



    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 3, 4, 5);
//        ListNode head = ListNode.of(1,2);
        System.out.println(head);
        System.out.println(new LeetCode19()
                .removeNthFromEnd2(head, 5));
    }

}
