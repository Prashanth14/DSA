/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        int cnt = 0;

        while(cnt < k){
            if(prev.next == null){
                return head;
            }
            cnt++;
            prev = prev.next;
        }
        ListNode nextSubHead = prev.next;
        prev.next = null;

        ListNode head1 = dummy.next;
        ListNode revSubList = reverseSubList(head1);

        dummy.next = revSubList;
        head1.next = reverseKGroup(nextSubHead, k);
        return dummy.next;
    }

    public ListNode reverseSubList(ListNode head){
        ListNode prev = null;
        ListNode cur = head;

        while(cur != null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
