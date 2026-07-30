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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int listLen = 0;
        ListNode temp = head;

        while(temp != null){
            listLen += 1;
            temp = temp.next;
        }
        // if(listLen == 1 && n == 1) return null;

        listLen -= n;
        if(listLen == 0) return head.next;

        ListNode t = head; 
        for(int i = 1; i < listLen; i++){
            t = t.next;
        }
        t.next = t.next.next;
        return head;
    }
}
