package leecode.demos;

import lombok.Data;

/**
 * Created by xiedong
 * 2023/6/6 12:32
 * 92. 反转链表 II
 * https://leetcode.cn/problems/reverse-linked-list-ii/
 */
public class Code92 {
    public static void main(String[] args) {
        Code92 code92 = new Code92();
        ListNode head = code92.buildListNode();
        ListNode reverse = code92.reverse(head);
        ListNode node = code92.reverseBetween(head, 2, 4);
        System.out.println();
    }

    private ListNode buildListNode() {
        int[] elements = {1, 2, 3, 4, 5};

        ListNode head = null;
        ListNode tail = null;

        // 使用循环构建链表
        for (int i = 0; i < elements.length; i++) {
            ListNode newNode = new ListNode(elements[i]);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        return head;
    }


    // 定义：输入一个单链表头结点，将该链表反转，返回新的头结点
    ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        return null;
    }

    @Data
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}


