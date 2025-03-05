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
        ListNode last = reverseList2(p.next);//找到最后一个节点
        p.next.next = p; //反转
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


    //方法4，双指针法
    public ListNode reverseList4(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    //递归的另一种写法
    public ListNode reverseList5(ListNode head) {
       return reverse(null,head);
    }
    private ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode temp = cur.next;//保存下一个节点
        cur.next = pre;
        return reverse(cur,temp);
    }


}
