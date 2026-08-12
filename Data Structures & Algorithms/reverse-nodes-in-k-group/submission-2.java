// Reverse Nodes in k-Group (LC 25) — TC: O(n), SC: O(n/k) recursion stack
 
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
 
        // walk prev k hops from dummy to reach the k-th node (group boundary)
        while (cnt < k) {
            if (prev.next == null) {
                return head; // fewer than k nodes left -> leave unreversed
            }
            cnt++;
            prev = prev.next;
        }
 
        ListNode nextSubHead = prev.next; // rest of list after this group
        prev.next = null;                 // cut group off from the rest
 
        ListNode head1 = dummy.next;              // group head -> becomes tail after reversal
        ListNode revSubList = reverseSubList(head1);
 
        dummy.next = revSubList;                       // attach reversed group as new head
        head1.next = reverseKGroup(nextSubHead, k);     // recurse on remainder, attach to new tail
 
        return dummy.next;
        // TC: O(k) work at this level (counting + reversing) + recursion on rest
        // SC: O(1) at this level, O(n/k) cumulative via recursion depth
    }
 
    // standard in-place iterative reversal of a (sub)list
    public ListNode reverseSubList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
 
        while (cur != null) {
            ListNode next = cur.next; // save next before overwriting
            cur.next = prev;          // reverse the pointer
            prev = cur;                // advance prev
            cur = next;                // advance cur
        }
        return prev; // new head of the reversed group
        // TC: O(k) where k = group size
        // SC: O(1) -> iterative, no extra data structures
    }
}
 
// ----------------------------------------------------------------------------
// REVISION NOTES
// ----------------------------------------------------------------------------
// Overall Time  : O(n) -> each node touched a constant number of times total
//                 across the counting loop + reversal, spread over n/k groups.
// Overall Space : O(n/k) -> recursion stack depth = number of groups.
//                 (Would be O(1) if rewritten iteratively over groups.)
//
// Key invariants:
//   - prev must travel exactly k hops from dummy (not k-1) to land on the
//     group's last node.
//   - if fewer than k nodes remain, return head unchanged (base case).
//   - head1 (old group head) becomes the tail after reversal -> its .next
//     must be wired to the recursive result on the remainder.
// ----------------------------------------------------------------------------