// Reverse Linked List II (LC 92) — TC: O(n), SC: O(1)
 
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
 
        // move prev to node just before position `left`
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
 
        // subListHead = start of window (will become tail after reversal)
        ListNode subListHead = prev.next;
        ListNode subListTail = subListHead;
 
        // advance subListTail to position `right`
        for (int i = 0; i < right - left; i++) {
            subListTail = subListTail.next;
        }
 
        // detach window from rest of list, save node after it
        ListNode nextNode = subListTail.next;
        subListTail.next = null;
 
        // reverse window, reattach before and after
        prev.next = reverseList(subListHead);
        subListHead.next = nextNode;
 
        return dummy.next;
    }
 
    // standard in-place reversal — TC: O(m), SC: O(1)
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
 
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}