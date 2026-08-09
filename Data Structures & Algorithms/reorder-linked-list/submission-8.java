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
    // Approach: find middle -> reverse second half -> merge both halves alternately
    // TC: O(n) - finding middle O(n), reversing second half O(n/2), merging O(n/2)
    // SC: O(1) - only pointer variables, nodes relinked in place, no extra structures
    public void reorderList(ListNode head) {
        // STEP 1: find the middle node using slow/fast pointers.
        // fast moves 2 steps for every 1 step slow moves, so when fast
        // reaches the end, slow is at the middle.
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // STEP 2: split the list into two halves at slow.
        // "second" becomes the head of the second half.
        // cutting slow.next = null terminates the first half cleanly.
        ListNode second = slow.next;
        slow.next = null;

        // STEP 3: reverse the second half in place (standard iterative reversal).
        // after this, prev points to the head of the reversed second half.
        ListNode prev = null;
        ListNode cur = second;

        while(cur != null){
            ListNode next = cur.next;   // save next node before overwriting link
            cur.next = prev;             // reverse the link
            prev = cur;                  // move prev forward
            cur = next;                  // move cur forward
        }
        
        // STEP 4: merge first half (starting at head) and reversed second half
        // (starting at prev) by alternating nodes: first1, second1, first2, second2, ...
        ListNode cur2 = prev;   // head of reversed second half
        ListNode cur1 = head;   // head of first half

        while(cur1 != null && cur2 != null){
            ListNode next1 = cur1.next;  // save first half's next node
            ListNode next2 = cur2.next;  // save second half's next node

            cur1.next = cur2;   // link first half node -> second half node
            cur1 = next1;       // advance first half pointer

            cur2.next = cur1;   // link second half node -> next first half node
            cur2 = next2;       // advance second half pointer
        }
        // loop naturally stops once the shorter half (reversed second half,
        // which has floor(n/2) nodes) is exhausted; any leftover node in the
        // first half (when n is odd) is already correctly linked as the tail.
    }
}