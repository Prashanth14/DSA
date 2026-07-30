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
/**
Intution behind the below solution
 * Find the midpoint of the LinkedList
 * Reverse the Second half of the LinkedList
 * Merge to Lists as required 
*/
class Solution {
     public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode curr = head;
        ListNode next;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public ListNode midPoint(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        ListNode sec = null;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        sec = slow.next;;
        slow.next = null;
        return sec;
    }
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;

        //step 1: find the midpoint of the list
        ListNode head2 = midPoint(head);

        //rerse the List
        ListNode rhead2 =  reverseList(head2);

        // merge first half list and reversed second list
        ListNode first = head;
        ListNode second = rhead2;

        while(first != null && second != null){
            ListNode next1 = first.next;
            ListNode next2 = second.next;

            first.next = second;
            second.next = next1;

            first = next1;
            second = next2;
        }
    }
}
