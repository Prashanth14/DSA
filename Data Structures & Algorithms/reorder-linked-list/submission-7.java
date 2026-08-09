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
    public void reorderList(ListNode head) {
        // first find the mid of the LinkedList using slow and fast pointer
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // split the list into half
        ListNode second = slow.next;
        slow.next = null;


        // reverse the second half of the list
        ListNode prev = null;
        ListNode cur = second;

        while(cur != null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur =next;
        }
        
        //then merge first List and second reversed list
        ListNode cur2 = prev;
        ListNode cur1 = head;

        while(cur1 != null && cur2 != null){
            ListNode next1 = cur1.next;
            ListNode next2 = cur2.next;

            cur1.next = cur2;
            cur1 = next1;
            cur2.next = cur1;
            cur2 = next2; 
        }
    }
}
