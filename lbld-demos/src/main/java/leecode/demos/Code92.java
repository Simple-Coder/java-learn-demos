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
//        ListNode reverse = code92.reverse(head);
        // 将链表的前 n 个节点反转（n <= 链表长度）
//        ListNode node1 = code92.reverseN(head, 2);
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


    ListNode successor = null;//后驱节点

    // 将链表的前 n 个节点反转（n <= 链表长度）
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }

        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);
        //反转
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;

        return last;
    }

    // 定义：输入一个单链表头结点，将该链表反转，返回新的头结点
    ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 递归地反转从下一个节点开始的链表部分
        ListNode last = reverse(head.next);
        // 将下一个节点的下一个指针指向当前节点
        head.next.next = head;
        // 将当前节点的下一个指针设置为null
        head.next = null;
        // 返回反转后链表的新头节点
        return last;
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            // 相当于反转前 n 个元素
            return reverseN(head, right);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
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


