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
    public ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        ListNode temp = head;
        while(temp != null){
            n++;
            temp = temp.next;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //track tail of previous reversed group
        ListNode prevGroupTail = dummy;
        // to track the head of current group
        ListNode groupStart = head; 

        while(n >= k){
           //Step 1: find the end of the current group
           ListNode groupEnd = groupStart;
           for(int i = 0; i< k-1; i++){
            groupEnd = groupEnd.next;
           }

           //Step 2: save the next group start before cutting the link
           ListNode nextGroupStart = groupEnd.next;

           //Step 3: cut the link between first k nodes of list and the rest of nodes
           groupEnd.next = null;

           //Strep 4: reverse the List with K nodes 1 -> 2 -> 3 -> null to 3 -> 2 -> 1 -> null;
           ListNode revListHead = reverse(groupStart);

           // Step 5 connect previous Group Tail to new revrsed List head
           prevGroupTail.next = revListHead;

           // group start ius now the tail after the revrseal
           groupStart.next = nextGroupStart;

           //Move pointers forward
           prevGroupTail = groupStart;
           groupStart = nextGroupStart;
           n -= k;
        }
        //if n < k, leave as it is , already connected via groupStart.next
        return dummy.next;
    }
}
