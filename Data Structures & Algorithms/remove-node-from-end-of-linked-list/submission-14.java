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
    // Approach: two-pass - first find list length, then walk to the node
    // just before the one to remove and unlink it.
    // TC: O(n) - first pass counts length O(n), second pass walks to target O(n)
    // SC: O(1) - only pointers used, no extra space
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode temp = head;

        // first pass: count total number of nodes
        while(temp != null){
            temp = temp.next;
            len++;
        }

        // special case: removing the head itself (nth from end is the first node)
        if(len == n) return head.next;

        // index (0-based from head) of the node just BEFORE the one to remove.
        // node to remove is at index (len-n), so its predecessor is (len-n-1).
        len = (len - n - 1);

        // second pass: walk from head to the predecessor node
        temp = head;
        int c = 0;

        while(c != len){
            c++;
            temp = temp.next;
        }
        
        // skip over the target node, unlinking it from the list
        temp.next = temp.next.next;
        return head;
    }
}