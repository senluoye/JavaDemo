/**
 * @ClassName Solution
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-14 17:07
 */

import java.util.Deque;
import java.util.LinkedList;Definition for singly-linked list.
class ListNode {
    int val;
      ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
      }
  }


public class Solution {
    public void reorderList(ListNode head) {
        Deque<ListNode> first = new LinkedList<>();
        Deque<ListNode> second = new LinkedList<>();
        ListNode now = head;
        int f = 0, l = 0;
        while (now != null) {
            l++;
            now = now.next;
        }
        now = head;
        while (now != null) {
            if (f <= l / 2) {
                first.addLast(now);
            } else {
                second.addFirst(now);
            }
            l++;
            now = now.next;
        }
        ListNode pre = null;
        while (!first.isEmpty() && !second.isEmpty()) {
            ListNode node = first.pollFirst();
            ListNode nodeSecond = second.pollFirst();
            if (pre != null) {
                pre.next = node;
            }
            node.next = nodeSecond;
            pre = nodeSecond;
        }
    }
}