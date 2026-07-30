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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Optimal solution with time complexity of o(n + m) where n, m = number of nodes in list1 and list2
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        ListNode temp1 = list1;
        ListNode temp2 = list2;

        while(temp1 != null && temp2 != null){
            if(temp1.val <= temp2.val){
                temp.next = temp1;
                temp = temp.next;
                temp1 = temp1.next;
                temp.next = null;
            }else{
                temp.next = temp2;
                temp = temp.next;
                temp2 = temp2.next;
                temp.next = null;
            }
        }

        while(temp1 != null){
            temp.next = temp1;
            temp1 = temp1.next;
            temp = temp.next;
        }

        while(temp2 != null){
            temp.next = temp2;
            temp2 = temp2.next;
            temp = temp.next;
        }

        return dummy.next;
    }
}